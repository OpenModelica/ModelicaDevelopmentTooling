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
package org.openmodelica.modelicaml.gen.modelica.listeners;

import org.eclipse.uml2.uml.Element;
import org.modelica.ConnectException;
import org.modelica.OMCProxy;

// TODO: Auto-generated Javadoc
/**
 * The Class OMCCommandExecuter.
 */
public class OMCCommandExecuter {
	
	/** The status. */
	private String status;
	
	/** The proxy. */
	private OMCProxy proxy = new OMCProxy();
	
	/** The simulation parameters. */
	private String simulationParameters = "";
	
	/** The dir. */
	private String dir;
	
	/** The error string. */
	private String errorString = "";
	
	/** The elt. */
	private Element elt;
	
	
	/**
	 * Instantiates a new oMC command executer.
	 * 
	 * @param elt
	 *            the elt
	 * @param folderPath
	 *            the folder path
	 * @param modelFilePath
	 *            the model file path
	 * @param modelElementQualifiedName
	 *            the model element qualified name
	 * @param plotCommand
	 *            the plot command
	 * @param simPar
	 *            the sim par
	 */
	public OMCCommandExecuter(Element elt, String folderPath, String modelFilePath, String modelElementQualifiedName, String plotCommand, String simPar) {
		this.dir = folderPath;
		this.elt = elt;
		executeOMCCommand(dir, modelFilePath,modelElementQualifiedName, plotCommand, simPar);
	}

	/**
	 * Execute omc command.
	 * 
	 * @param folderPath
	 *            the folder path
	 * @param modelFilePath
	 *            the model file path
	 * @param modelElementQualifiedName
	 *            the model element qualified name
	 * @param plotCommand
	 *            the plot command
	 * @param simPar
	 *            the sim par
	 * @return the string
	 */
	private String executeOMCCommand(String folderPath, String modelFilePath, String modelElementQualifiedName, String plotCommand, String simPar) {
		
		if (simPar != null) {
			simulationParameters = "," + simPar;
		}
		
		try {

			status = proxy.sendExpression("clear()");
			
			//proxy.sendExpression("cd(\"" + dir + "\")");
			
			if (status == null) {
				System.err.println("No connection to OMC! ");
				//MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "No connection to OMC! ");
			}

			status = proxy.sendExpression("loadFile(\"" + modelFilePath + "\")");
			
			if (status.contains("error") || status.contains("false")) {
				System.err.println("Cannot find the package " + modelFilePath + "!");
				//MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "Cannot find the package " + modelFilePath + "!");
			}
			
			errorString = proxy.sendExpression("getErrorString()");
			if (!errorString.equals("")) {
				System.err.println(errorString);
			}
			
			status = proxy.sendExpression("checkModel("
					+ modelElementQualifiedName + ")");
			
			errorString = proxy.sendExpression("getErrorString()");
			if (!errorString.equals("")) {
				System.err.println(errorString);
			}
			

		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}
