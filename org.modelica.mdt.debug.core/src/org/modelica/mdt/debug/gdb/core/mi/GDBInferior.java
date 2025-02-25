/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.gdb.core.mi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.mi.command.CLIExecAbort;
import org.modelica.mdt.debug.gdb.core.mi.command.CLIInfoProc;
import org.modelica.mdt.debug.gdb.core.mi.command.CLIInfoProgram;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecInterrupt;
import org.modelica.mdt.debug.gdb.core.mi.command.MIGDBShowExitCode;
import org.modelica.mdt.debug.gdb.core.mi.event.MIInferiorExitEvent;
import org.modelica.mdt.debug.gdb.core.mi.output.CLIInfoProcInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.CLIInfoProgramInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIGDBShowExitCodeInfo;

/**
 * Represents the actual debugged process.
 * 
 * @author Adeel Asghar
 *
 */
public class GDBInferior extends Process {
	
	final static int SUSPENDED = 1;
	final static int RUNNING = 2;
	final static int TERMINATED = 4;

	boolean connected = false;
	boolean exitCodeKnown = false;
	int exitCode = 0;
	int state = 0;
	int inferiorPID;

	MISession session;

	OutputStream out;
	InputStream in;

	PipedOutputStream inPiped;
	PipedInputStream err;
	PipedOutputStream errPiped;

	public GDBInferior(MISession mi) {
		session = mi;
	}

	/**
	 * @see java.lang.Process#getOutputStream()
	 */
	public OutputStream getOutputStream() {
		if (out == null) {
			out = new OutputStream() {
				public void write(int b) throws IOException {
					if (!isRunning()) {
						throw new IOException(MDTDebugCorePlugin.getResourceString("GDBInferior.getOutputStream.target_is_suspended"));
					}
					OutputStream channel = session.getChannelOutputStream();
					if (channel == null) {
						throw new IOException(MDTDebugCorePlugin.getResourceString("GDBInferior.getOutputStream.no_session"));
					}
					channel.write(b);
				}
			};
		}
		return out;
	}

	/**
	 * @see java.lang.Process#getInputStream()
	 */
	public InputStream getInputStream() {
		if (in == null) {
			try {
				inPiped = new PipedOutputStream();
				in = new PipedInputStream(inPiped);
			} catch (IOException e) {
			}
		}
		return in;
	}

	/**
	 * @see java.lang.Process#getErrorStream()
	 */
	public InputStream getErrorStream() {
		// FIXME: We do not have any err stream from gdb/mi
		// so this gdb err channel instead.
		if (err == null) {
			try {
				errPiped = new PipedOutputStream();
				err = new PipedInputStream(errPiped);
			} catch (IOException e) {
			}
		}
		return err;
	}

	public synchronized void waitForSync() throws InterruptedException {
		while (state != TERMINATED) {
			wait();
		}		
	}

	/**
	 * @see java.lang.Process#waitFor()
	 */
	public int waitFor() throws InterruptedException {
		waitForSync();
		return exitValue();
	}

	/**
	 * @see java.lang.Process#exitValue()
	 */
	public int exitValue() {
		if (isTerminated()) {
			if (!session.isTerminated()) {
				if (!exitCodeKnown) {
					CommandFactory factory = session.getCommandFactory();
					MIGDBShowExitCode showExitCodeCmd = factory.createMIGDBShowExitCode();
					try {
						session.postCommand(showExitCodeCmd, null);
						MIGDBShowExitCodeInfo info = showExitCodeCmd.getMIGDBShowExitCodeInfo();
						exitCode = info.getCode();
					} catch (MIException e) {
						// no rethrown.
					}
					exitCodeKnown = true;
				}
			}
			return exitCode;
		}
		throw new IllegalThreadStateException();
	}

	/**
	 * @see java.lang.Process#destroy()
	 */
	public void destroy() {
		try {
			terminate();
		} catch (MIException e) {
			// do nothing.
		}
	}

	/**
	 * Terminates the running inferior.
	 * @throws MIException
	 */
	public void terminate() throws MIException {
		// An inferior will be destroy():interrupt and kill if
		//   the inferior was not disconnected yet (no need to try
		//   to kill a disconnected program).
		//   if the inferior was not terminated.
		if ((isConnected()) || (!isTerminated())) {
			// Try to interrupt the inferior, first.
			if (isRunning()) {
				interrupt();
			}
			int token = 0;
			if (isSuspended()) {
				try {
					CommandFactory factory = session.getCommandFactory();
					CLIExecAbort execAbortCmd = factory.createCLIExecAbort();
					session.postCommand(execAbortCmd, null);
					// do not wait for the answer.
					//abort.getMIInfo();
					token = execAbortCmd.getToken();
				} catch (MIException e) {
					// ignore the error
				}
			}
			setTerminated(token, true);
		}
	}

	/**
	 * Interrupts the running inferior.
	 * @throws MIException
	 */
	public void interrupt() throws MIException {
		// Check if they can handle the interrupt
		// Try the exec-interrupt; this will be for "gdb --async"
		CommandFactory factory = session.getCommandFactory();
		MIExecInterrupt execInterruptCmd = factory.createMIExecInterrupt();
		if (execInterruptCmd != null) {
			try {
				session.postCommand(execInterruptCmd, null);
				// call getMIInfo() even if we discard the value;
				execInterruptCmd.getMIInfo();
				// Allow MI command timeout for the interrupt to propagate.
				long maxSec = session.getCommandTimeout()/1000 + 1;
				synchronized(this) {
					for (int i = 0;(state == RUNNING) && i < maxSec; i++) {
						try {
							wait(1000);
						} catch (InterruptedException e) {
						}
					}
				}
			} catch (MIException e) {
			}
		}

		// If we've failed throw an exception up.
		if (state == RUNNING) {
			throw new MIException(MDTDebugCorePlugin.getResourceString("GDBInferior.interrupt.no_session"));
		}
	}

	/**
	 * Returns the SUSPENDED state
	 * @return SUSPENDED state
	 */
	public boolean isSuspended() {
		return state == SUSPENDED;
	}

	/**
	 * Returns the RUNNING state
	 * @return RUNNING state
	 */
	public boolean isRunning() {
		return state == RUNNING;
	}

	/**
	 * Returns the TERMINATED state
	 * @return TERMINATED state
	 */
	public boolean isTerminated() {
		return state == TERMINATED;
	}

	/**
	 * Returns true is connected
	 * @return connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Sets connected true.
	 */
	public synchronized void setConnected() {
		connected = true;
	}

	/**
	 * Sets connected false.
	 */
	public synchronized void setDisconnected() {
		connected = false;
	}

	/**
	 * Sets the SUSPENDED state
	 */
	public synchronized void setSuspended() {
		state = SUSPENDED;
		notifyAll();
	}

	/**
	 * Sets the RUNNING state
	 */
	public synchronized void setRunning() {
		state = RUNNING;
		notifyAll();
	}

	/**
	 * Sets the TERMINATED state
	 */
	public synchronized void setTerminated() {
		setTerminated(0, true);
	}

	/**
	 * Sets the TERMINATED state
	 */
	synchronized void setTerminated(int token, boolean fireEvent) {
		state = TERMINATED;
		// Close the streams.
		try {
			if (inPiped != null) {
				inPiped.close();
				inPiped = null;
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		try {
			if (errPiped != null) {
				errPiped.close();
				errPiped = null;
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		if (fireEvent) {
			session.fireEvent(new MIInferiorExitEvent(session, token));
		}
		notifyAll();
	}

	/**
	 * Returns the piped output stream
	 * @return piped output stream
	 */
	public OutputStream getPipedOutputStream() {
		return inPiped;
	}

	/**
	 * Returns the piped error stream
	 * @return piped error stream
	 */
	public OutputStream getPipedErrorStream() {
		return errPiped;
	}

	/**
	 * Requests the inferior process ID from GDB and stores it.
	 */
	public void update() {
		if (getInferiorPID() == 0) {
			int pid = 0;
			// Do not try this on attach session.
			if (!isConnected()) {
				// Try to discover the pid using GDB/CLI Command "info proc"
				CommandFactory factory = session.getCommandFactory();
				CLIInfoProc infoProcCmd = factory.createCLIInfoProc();
				infoProcCmd.setQuiet(true);
				try {
					RxThread rxThread = session.getRxThread();
					rxThread.setEnableConsole(false);
					session.postCommand(infoProcCmd, null); 
					CLIInfoProcInfo infoProc = infoProcCmd.getMIInfoProcInfo();
					pid = infoProc.getPID();
				} catch (MIException e) {
					// no rethrown.
				}
				
				// Try to discover the pid using GDB/CLI Command "info program" if "info proc" failed
				// "info proc" can fail on certain platforms like Windows :)
				try {
					if(pid <= 0){ 
					CLIInfoProgram infoProgramCmd = factory.createCLIInfoProgram();
					infoProgramCmd.setQuiet(true);
					session.postCommand(infoProgramCmd, null);
					CLIInfoProgramInfo info = infoProgramCmd.getMIInfoProgramInfo();
					pid = info.getPID();
					}
				} catch (MIException e) {
					// no rethrown.
				} finally {
					RxThread rxThread = session.getRxThread();
					rxThread.setEnableConsole(true);					
				}
			}
			// We fail permantely.
			setInferiorPID((pid == 0) ? -1: pid);
		}
	}

	/**
	 * Resets the stored process ID.
	 * @return process id
	 */
	public int resetInferiorPID() {
		int pid = inferiorPID;
		inferiorPID = 0;
		return pid;
	}

	/**
	 * Sets the inferior process ID.
	 * @param pid
	 */
	public void setInferiorPID(int pid) {
		inferiorPID = pid;
	}

	/**
	 * Returns the inferior process ID.
	 * @return process ID
	 */
	public int getInferiorPID() {
		return inferiorPID;
	}
}