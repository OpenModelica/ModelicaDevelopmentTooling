/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */

package org.modelica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.omg.CORBA.ORB;

// TODO: Auto-generated Javadoc
/**
 * The OMCProxy is the glue between the OpenModelica Compiler and MDT.
 * It uses the interactive API of OMC to get information about classes
 * and to load classes into OMC.
 * 
 * @author Andreas Remar
 * @author Adrian Pop
 */
public class OMCProxy 
{
	
	/* the CORBA object */
	/** The omcc. */
	private static OmcCommunication omcc;
	
	/** The corba session name. */
	private static String corbaSessionName = "ECLIPSE";
		
	/**
	 * The Enum osType.
	 */
	enum osType { /** The WINDOWS. */
 WINDOWS, /** The UNIX. */
 UNIX };
	
	/* what Operating System we're running on */
	/** The os. */
	private static osType os;
	
	/* indicates if we've setup the communication with OMC */
	/** The has initialized. */
	private static boolean hasInitialized = false;
	
	/* indicates if the Modelica System Library has been loaded */
	/** The system library loaded. */
	private boolean systemLibraryLoaded = false;

	/** The standard library packages. */
	private String[] standardLibraryPackages = { "Modelica" };

	/* debug options  */
	/* should we trace the calls to sendExpression? */
	/** The trace omc calls. */
	private static boolean traceOMCCalls = true;
	
	/** The trace omc status. */
	private static boolean traceOMCStatus = true;

	/** The existing corba file is new. */
	private static boolean existingCorbaFileIsNew = false;
	
	/**
	 * Instantiates a new oMC proxy.
	 */
	public OMCProxy()
	{
		
	}

	/**
	 * Reads in the OMC CORBA object reference from a file on disk.
	 * 
	 * @return the object reference as a <code>String</code>
	 * @throws ConnectException
	 *             the connect exception
	 */
	private static String readObjectFromFile() throws ConnectException
	{
		File f = new File(getPathToObject());
		String stringifiedObjectReference = null;

		BufferedReader br = null;
		FileReader fr = null;
		try
		{
			fr = new FileReader(f);
		}
		catch(IOException e)
		{
			throw new ConnectException("Unable to read OpenModelica Compiler CORBA object from " + f.toString());
		}

		br = new BufferedReader(fr);
			
		try
		{
			stringifiedObjectReference = br.readLine();
		}
		catch(IOException e)
		{
			throw new ConnectException("Unable to read OpenModelica Compiler"
					+ " CORBA object from " + getPathToObject());
		}
		return stringifiedObjectReference;
	}
	
	/**
	 * Checks if is checks for initialized.
	 * 
	 * @return true, if is checks for initialized
	 */
	public static boolean isHasInitialized() {
		return hasInitialized;
	}

	/**
	 * Sets the checks for initialized.
	 * 
	 * @param has
	 *            the new checks for initialized
	 */
	public static void setHasInitialized(boolean has) {
		hasInitialized = has;
	}
	
	/**
	 * Checks if is existing corba file is new.
	 * 
	 * @return true, if is existing corba file is new
	 */
	public static boolean isExistingCorbaFileIsNew() {
		return existingCorbaFileIsNew;
	}

	/**
	 * Sets the existing corba file is new.
	 * 
	 * @param existingCorbaFileIsNew
	 *            the new existing corba file is new
	 */
	public static void setExistingCorbaFileIsNew(boolean existingCorbaFileIsNew) {
		OMCProxy.existingCorbaFileIsNew = existingCorbaFileIsNew;
	}

	/**
	 * Gets the path to object.
	 * 
	 * @return Returns the path to the OMC CORBA object that is stored on disk.
	 */
	private static String getPathToObject()
	{
		String fileName = null;

		/* This mirrors the way OMC creates the object file. */		
		switch (os)
		{
		case UNIX:
			String username = System.getenv("USER");
			if(username == null)
			{
				username = "nobody";
			}
			fileName = "/tmp/openmodelica." + username + ".objid." + corbaSessionName;
			break;
		case WINDOWS:
			String temp = System.getenv("TMP");			
			fileName = temp + "\\openmodelica.objid." + corbaSessionName;
			break;
		default:
			logBug("org.modelica", "os variable set to unexpected os-type");
		}
		
		logOMCStatus("Will look for OMC object reference in '" 
				+ fileName + "'.");
		
		return fileName;
	}
	
	/**
	 * With the help of voodoo magic determines the path to the
	 * omc binary that user (probably) wants to use and the working
	 * direcoty of where that binary (most likely) should be started in
	 * 
	 * This will returns for example 'c:\openmodelica132\omc.exe'
	 * or '/usr/local/share/openmodelica/omc' depending on
	 * such factors as: OS type, environment variables settings,
	 * plugin user preferences, where the first matching
	 * binary found and the weather outside. 
	 * 
	 * @return full path to the omc binary  
	 * @throws ConnectException if the path could not be determined
	 */
	private static File[] getOmcBinaryPaths() throws ConnectException
	{
		String binaryName = "omc";
		
		if (os == osType.WINDOWS)
		{
			binaryName += ".exe";
		}
		
		File omcBinary = null;
		File omcWorkingDirectory = null;
		/*
		 * user specified that standard path to omc should be used,
		 * try to determine the omc path via the OPENMODELICAHOME and
		 * by checking in it's varius subdirectory for the omc binary file
		 */
		logOMCStatus("Using OPENMODELICAHOME environment variable to find omc-binary");
		
		/* 
		 * Standard path to omc (or omc.exe) binary is encoded in OPENMODELICAHOME
		 * variable. 
		 */
		String openModelicaHome = System.getenv("OPENMODELICAHOME");
		if(openModelicaHome == null)
		{
			final String m = "Environment variable OPENMODELICAHOME not set";
			logOMCStatus("Environment variable OPENMODELICAHOME not set,"+
					" don't know how to start OMC from standard path.");
			throw new ConnectException(m);
		}
		
		omcWorkingDirectory = new File(openModelicaHome);
		
		/* the subdirectories where omc binary may be located, hurray for standards! */
		String[] subdirs = { "", "bin", "Compiler" };
		
		for (String subdir : subdirs)
		{
		
			String path = omcWorkingDirectory.getAbsolutePath() + File.separator;
			path += subdir.equals("") ? binaryName :  subdir + File.separator + binaryName;

			File file = new File(path); 

			if (file.exists())
			{
				omcBinary = file;
				logOMCStatus("Using omc-binary at '" + omcBinary.getAbsolutePath() + "'");
				break;
			}
			else
			{
				logOMCStatus("No omc binary at: [" + path + "]");
			}
		}
		
		if (omcBinary == null)
		{
			logOMCStatus("Could not fine omc-binary on the OPENMODELICAHOME path");
			throw new ConnectException("Unable to start the OpenModelica Compiler, binary not found");
		}

		return new File[] {omcBinary, omcWorkingDirectory};
	}
	
	/**
	 * Start a new OMC server.
	 * 
	 * @throws ConnectException
	 *             the connect exception
	 */
	private static void startServer() throws ConnectException
	{
		File tmp[] = getOmcBinaryPaths();

		File omcBinary = tmp[0];
		File workingDirectory = new File(".");

		
		/* 
		 * Delete old object reference file. We need to do this because we're
		 * checking if the file exists to determine if the server has started
		 * or not (further down). 
		 */
		File f = new File(getPathToObject());
		long lastModified = 0;		
		if(f.exists())
		{
			lastModified = f.lastModified();
			logOMCStatus("OMC object reference file is already on disk, we try to use it.");
			if (existingCorbaFileIsNew) return;
			logOMCStatus("OMC object reference file is already on disk, but is old, start a new server.");
		}
		
		String command[] = { omcBinary.getAbsolutePath(), "+c=" + corbaSessionName, "+d=interactiveCorba" };
		try
		{
			logOMCStatus("Running command " + command[0] + " " + command[1] + " " + command[2]);
			logOMCStatus("Setting working directory to " + workingDirectory.getAbsolutePath());
			ProcessStartThread pt = new ProcessStartThread(command, workingDirectory);
			pt.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logOMCStatus("Error running command " + e.getMessage());
			logOMCStatus("Unable to start OMC, giving up."); 
			throw new ConnectException("Unable to start the OpenModelica Compiler. ");
		}			

		logOMCStatus("Waiting for OMC CORBA object reference to appear on disk.");
		existingCorbaFileIsNew = true;
		
		/*
		 * Wait until the object exists on disk, but if it takes longer than
		 * 5 seconds, abort. (Very arbitrary 5 seconds..)
		 */
		int ticks = 0;
		while(!f.exists() || (f.exists() && lastModified == f.lastModified()) )
		{
			try
			{
				logOMCStatus("Waiting for OMC CORBA object reference to appear on disk ... for "+ (ticks+1) + " seconds");
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				/* ignore */
			}
			ticks++;
			
			/* If we've waited for around 5 seconds, abort the wait for OMC */
			if(ticks > 50)
			{
				logOMCStatus("No OMC object reference file created after approximately 50 seconds.");
				logOMCStatus("It seems OMC does not want to start, giving up.");
				throw new ConnectException("Unable to start the Open Modelica Compiler. Waited for 5 seconds, but it didn't respond.");
			}
		}
		logOMCStatus("The new OMC object reference found.");
	}
	
	/**
	 * Initializes an ORB, converts the stringified OMC object to a real CORBA
	 * object, and then narrows that object to an OmcCommunication object.
	 * 
	 * @param stringifiedObjectReference
	 *            the new up omcc
	 */
	private static void setupOmcc(String stringifiedObjectReference)
	{
		/* Can't remember why this is needed. But it is. */
		String args[] = {null};
		
		ORB orb;
		orb = ORB.init(args, null);
		
		/* Convert string to object. */
		org.omg.CORBA.Object obj = orb.string_to_object(stringifiedObjectReference);
		logOMCStatus("Corba IOR:" + stringifiedObjectReference);
		/* Convert object to OmcCommunication object. */
		omcc = OmcCommunicationHelper.narrow(obj);
	}
	
	/**
	 * Gets the os.
	 * 
	 * @return the name of the operating system. If an unknown os is found, the
	 *         default is Unix.
	 */
	private static osType getOs()
	{
		String osName = System.getProperty("os.name");
		if(osName.contains("Linux"))
		{
			return osType.UNIX;
		}
		else if(osName.contains("Windows"))
		{
			return osType.WINDOWS;
		}
		else
		{
			logWarning("'" + osName + "' not officialy supported OS");
			/* If the OS is not GNU/Linux or Windows, default to Unix */
			return osType.UNIX;
		}
	}

	/**
	 * Initialize the communication with OMC.
	 * 
	 * @throws ConnectException
	 *             if we're unable to start communicating with the server
	 */
	private void init() throws ConnectException
	{
		/* 
		 * Get type of operating system, used for finding object
		 * reference and starting OMC if the reference is faulty 
		 */
		os = getOs();
		
		/* See if an OMC server is already running */
		File f = new File(getPathToObject());
		String stringifiedObjectReference = null;
		if(!f.exists())
		{
			/* If a server isn't running, start it */
			logOMCStatus("No OMC object reference found, starting server.");
			startServer();
		}
		else
		{
			logOMCStatus("Old OMC CORBA object reference present," + " assuming OMC is running.");
		}
		
		/* Read in the CORBA OMC object from a file on disk */
		stringifiedObjectReference = readObjectFromFile();

		/*
		 * Setup up OMC object reference by initializing ORB and then
		 * converting the string object to a real CORBA object.
		 */
		setupOmcc(stringifiedObjectReference);

		try
		{
			/*
			 * Test the server by trying to send an expression to it. 
			 * This might fail if the object reference found on disk didn't
			 * have a corresponding server running. If a server is missing,
			 * catch an exception and try starting a server.
			 */
			logOMCStatus("Trying to send expression to OMC.");
			omcc.sendExpression("1+1");
			logOMCStatus("Expression sent successfully.");
		}
		catch(org.omg.CORBA.COMM_FAILURE e)
		{
			/* Start server and set up omcc */
			logOMCStatus("Failed sending expression, will try to start OMC.");
			existingCorbaFileIsNew = false;
			startServer();
			stringifiedObjectReference = readObjectFromFile();
			setupOmcc(stringifiedObjectReference);

			try
			{
				/* Once again try to send an expression to OMC. If it fails this
				 * time it's time to send back an exception to the caller of
				 * this function. */
				logOMCStatus("Trying to send expression to OMC.");
				omcc.sendExpression("1+1");
				logOMCStatus("Expression sent successfully.");
			}
			catch(org.omg.CORBA.COMM_FAILURE x)
			{
				logOMCStatus("Failed sending expression, giving up.");
				throw new ConnectException("Unable to start the OpenModelica Compiler.");
			}
		}

		hasInitialized = true;
	}
	
	/**
	 * Send expression to OMC. If communication is not initialized, it is
	 * initialized here.
	 * 
	 * @param exp
	 *            the expression to send to OMC
	 * @return the string
	 * @throws ConnectException
	 *             if we're unable to start communicating with the server
	 */
	// TODO add synchronization so that two threads don't fudge up each others
	// communication with OMC
	// old synchronization aka 'private synchronized String sendExpression(String exp)'
	// doesnt work when there is possibility of multiple instances of OMCProxy objects
	public String sendExpression(String exp)
		throws ConnectException
	{
		String retval = null;
		
		if(hasInitialized == false)
		{
			init();
		}
		
		try
		{
			logOMCCall(exp);
			retval = omcc.sendExpression(exp);
			logOMCReply(retval);
		}
		catch(org.omg.CORBA.COMM_FAILURE x)
		{
			logOMCCallError("Error while sending expression " + exp + " ["+x+"]");
			/* lost connection to OMC or something */
			throw new ConnectException("Couldn't send expression to the "+
					"OpenModelica Compiler. Tried sending: " + exp);
		}
		
		return retval;
	}
	
	/**
	 * Logs the expression sent to OMC if the tracing flag (traceOMCCalls) is
	 * set.
	 * 
	 * @param expression
	 *            the expression that is about to be sent to OMC
	 */
	public static void logOMCCall(String expression)
	{
		if (!traceOMCCalls)
		{
			return;
		}
		System.out.println(">> " + expression);
	}
	
	/**
	 * outputs the message about a call error that occured when communicating
	 * with omc.
	 * 
	 * @param message
	 *            the message to log
	 */
	public static void logOMCCallError(String message)
	{
		if(!traceOMCCalls)
		{
			return;
		}
		System.out.println(message);
	}
	
	/**
	 * loggs the message conserning OMC status if the tracing flag
	 * traceOMCStatus is set.
	 * 
	 * @param message
	 *            the message to log
	 */
	public static void logOMCStatus(String message)
	{
		if (!traceOMCStatus)
		{
			return;
		}
		System.out.println("OMCSTATUS: " + message);
	}

	/**
	 * Logs the reply received from OMC if the tracing flag (traceOMCCalls) is
	 * set.
	 * 
	 * @param reply
	 *            the reply recieved from the OMC
	 */
	public static void logOMCReply(String reply)
	{
		if (!traceOMCCalls)
		{
			return;
		}

		StringTokenizer tokenizer = new StringTokenizer(reply, "\n");
		
		while (tokenizer.hasMoreTokens())
		{
			System.out.println("<< " + tokenizer.nextToken());
		}
	}
	
	/**
	 * Get the classes contained in a class (a package is a class..)
	 * 
	 * @param className
	 *            full class name where to look for packages
	 * @return a <code>List</code> of subclasses defined (and loaded into OMC)
	 *         inside the class named className.
	 * @throws ConnectException
	 *             the connect exception
	 */	
	public String getClassNames(String className) throws ConnectException
	{
		String retval = sendExpression("getClassNames("+className+")");
		
		/* fetch error string but ignore it */
		getErrorString();
				
		return retval;
	}

	/**
	 * Gets the type of restriction of a class.
	 * 
	 * @param className
	 *            fully qualified class name
	 * @return the type of restriction of the class
	 * @throws ConnectException
	 *             the connect exception
	 */
	public String getRestriction(String className) throws ConnectException
	{
		String reply = sendExpression("getClassRestriction(" + className + ")");

		/* remove " around the reply */
		reply = reply.trim();
				
		return reply;
	}
	
	/**
	 * Fetches the error string from OMC. This should be called after an "Error"
	 * is received. (Or whenever the queue of errors should be emptied.)
	 * 
	 * @return the <code>String</code> of errors
	 * @throws ConnectException
	 *             the connect exception
	 */
	private String getErrorString() throws ConnectException
	{
		String res = sendExpression("getErrorString()");
		
		/* Make sure the error string isn't empty */
		if(res != null && res.length() > 2)
		{
			res = res.trim();
			return res.substring(1, res.length() - 1);
		}
		else
			return "";
	}
	

	/**
	 * Tries to load file into OMC which causes it to be parsed and the syntax
	 * checked.
	 * 
	 * @param file
	 *            the file we want to load
	 * @return a <code>ParseResult</code> containing the classes found in the
	 *         file and the error messages from OMC
	 * @throws ConnectException
	 *             the connect exception
	 */
	public String loadSourceFile(String file) throws ConnectException
	{
		String retval =	sendExpression("loadFileInteractiveQualified(\"" + file + "\")");
		retval = retval.trim();
		// String errorString = getErrorString();
		/*
		 * See if there were parse errors, an empty list {} also denotes error
		 */
		return retval;
	}

	/**
	 * Gets the location (file, starting and ending line number and column
	 * number) of a Modelica element.
	 * 
	 * @param className
	 *            the element we want to get location of
	 * @return an <code>ElementLocation</code> containing the file, starting and
	 *         ending line number and column number of the given class
	 * @throws ConnectException
	 *             the connect exception
	 * @throws InvocationError
	 *             the invocation error
	 */
	public String getClassLocation(String className) throws ConnectException, InvocationError 
	{
		String retval = sendExpression("getCrefInfo(" + className + ")");
		
		/* fetch error string but ignore it */
		getErrorString();
		
		if(retval.contains("Error") || retval.contains("error"))
		{
			throw new 
				InvocationError("Fetching file position of " + className, "getCrefInfo(" + className + ")");
		}
		
		
		/*
		 * The getCrefInfo reply has the following format:
		 * 
		 * <file path>,<something>,<start line>,<start column>,<end line>,<end column>
		 * 
		 * for example:
		 * /foo/Modelica/package.mo,writable,1,1,1029,13
		 */

		/* For some reason, the list returned doesn't contain curly braces. */
		retval = retval.trim();
		retval = "{" + retval + "}"; 
				
		return retval;
	}
	
	/**
	 * Queries the compiler if a particular modelica class/package is a package.
	 * 
	 * @param className
	 *            fully qualified name of the class/package
	 * @return true if className is a package, false otherwise
	 * @throws ConnectException
	 *             the connect exception
	 */
	public boolean isPackage(String className) throws ConnectException 
	{
		String retval = sendExpression("isPackage(" + className + ")");

		/* fetch error string but ignore it */
		getErrorString();
		
		return retval.contains("true");
	}
	
	/**
	 * Uses the OMC API call getElementsInfo to fetch lots of information about
	 * a class definition. See interactive_api.txt in the OMC source tree.
	 * 
	 * @param className
	 *            the fully qualified name of a class
	 * @return a <code>Collection</code> (of <code>ElementsInfo</code>)
	 *         containing the information about className
	 * @throws ConnectException
	 *             the connect exception
	 * @throws InvocationError
	 *             the invocation error
	 */
	public String getElements(String className)	throws ConnectException, InvocationError
	{
		String retval = sendExpression("getElementsInfo("+ className +")");
		
		/* fetch error string */
		getErrorString();
		
		return retval;
	}

	/**
	 * Gets the class info.
	 * 
	 * @param className
	 *            the class name
	 * @return the class info
	 * @throws ConnectException
	 *             the connect exception
	 */
	public String getClassInfo(String className) throws ConnectException
	{
		String retval = sendExpression("getClassInformation("+ className +")");
		
		/* fetch error string but ignore it */
		getErrorString();
				
		return  retval;
	}

	
	/**
	 * Gets the compiler name.
	 * 
	 * @return the name of the compiler that this plugin tries to communicate
	 *         with (at least it tries...)
	 */
	public String getCompilerName()
	{
		return "OpenModelica Compiler";
	}

	/**
	 * Loads in the Modelica System Library and returns names of the top-level
	 * packages.
	 * 
	 * @return the standard library
	 * @throws ConnectException
	 *             if we're unable to start communicating with the server
	 */	
	public String[] getStandardLibrary() throws ConnectException
	{
		if (!systemLibraryLoaded)
		{
			sendExpression("loadModel(Modelica)");
			
			/* fetch error string but ignore it */
			getErrorString();
			
			systemLibraryLoaded = true;
		}

		return standardLibraryPackages;
	}
	
	/**
	 * Log bug.
	 * 
	 * @param where
	 *            the where
	 * @param message
	 *            the message
	 */
	public static void logBug(String where, String message)
	{
		System.out.println("Error: " + where + " Message: "+ message);
	}

	/**
	 * Log warning.
	 * 
	 * @param message
	 *            the message
	 */
	public static void logWarning(String message)
	{
		System.out.println("Warning: " + message);
	}

}
