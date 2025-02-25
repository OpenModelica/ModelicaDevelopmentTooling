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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.simulation.xml;

import java.io.File;
import java.io.IOException;

import org.openmodelica.modelicaml.simulation.xml.TestSession.TestModel;


import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

public class TestSessionXML_Reader {


	/** The simpro. */
	//public static ArrayList<String> invalidDataLength = new ArrayList<String>();

	/**
	 * Reads a XML file, creates a TestSession object and initializes it.
	 * The XML file can be a "test_session.txt" or a "verification_session.txt"
	 *
	 * @param xml_file the xml_file
	 * @return complete initialized TestSession object
	 */
	public static TestSession readFromXML(File xml_file) {
		return createTestSession(xml_file);
	}
	
	
	/**
	 * Reads a XML file, creates a TestSession object and initializes it.
	 * The XML file can be a "test_session.txt" or a "verification_session.txt"
	 *
	 * @param xml_file_path the xml_file_path
	 * @return complete initialized TestSession object
	 */
	public static TestSession readFromXML(String xml_file_path) {
		return createTestSession(new File(xml_file_path));
	}
	
	/**
	 * Creates the TestSession.
	 *
	 * @param xmlFile the xml file
	 */
	private static TestSession createTestSession(File xmlFile) {
		
		Document project_xml = parseXML_File(xmlFile);
//		System.out.println(project_xml.getRootElement().getValue());
		Element root = project_xml.getRootElement(); //TestSessionConfiguration
		
		Elements eProject = root.getChildElements("project");
		String packageMoFile = eProject.get(0).getChildElements("packageFileRelativePath").get(0).getValue();
		TestSession ts = new TestSession(packageMoFile);
		

		Element eTestModels = root.getChildElements("testModels").get(0);
		Elements eTestModelList = eTestModels.getChildElements();
		createTestModels(packageMoFile, ts, eTestModelList);
		
		return ts;
	}

	private static void createTestModels(String packageMoFile, TestSession ts, Elements eTestModels) {
		
		int i = 0;
		while (i < eTestModels.size()) {
			Element eTestModel = eTestModels.get(i);
			
			String qualifiedName = eTestModel.getChildElements("qualifiedName").get(0).getValue();
			
			Elements eSimulationData = eTestModel.getChildElements("simulationData");
				String start = eSimulationData.get(0).getChildElements("start").get(0).getValue();
				String stop = eSimulationData.get(0).getChildElements("stop").get(0).getValue();
				String numberOfIntervals = eSimulationData.get(0).getChildElements("numberOfIntervals").get(0).getValue();
				String tolerance = eSimulationData.get(0).getChildElements("tolerance").get(0).getValue();
				String solver = eSimulationData.get(0).getChildElements("solver").get(0).getValue();
				String outputFormat = eSimulationData.get(0).getChildElements("outputFormat").get(0).getValue();
			
			TestSession.TestModel tm = new TestSession.TestModel(qualifiedName, start, stop, numberOfIntervals, tolerance, solver, outputFormat);
			
			ts.testModels.add(tm);
			
			++i;
		}
	}
	
	/**
	 * parse a XML file.
	 *
	 * @param file the file
	 * @return the doc which generated from the file or null if there is any
	 * error
	 */
	private static Document parseXML_File(File file) {

		try {
			Builder parser = new Builder();
			Document doc = parser.build(file);
			return doc;
		} catch (ParsingException ex) {
			System.err.println("The XML file is malformed. Pleas check it");
		} catch (IOException ex) {
			System.err
					.println("Could not read the file. The file may not exists.");
		}
		return null;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		String pathToSession = "C:/Projects/ModelicaML/runtime-New_configuration/modelicaml.example.potableWaterSystem_v30/verification-gen/verification-session_20120124110026/";
		
		String testSessionObj = (pathToSession + "verification_session.xml");
		
		
		TestSession ts = readFromXML(testSessionObj);
		System.out.println(ts.packageFileRelativePath);
		for(TestModel tempModel : ts.testModels){
			System.out.println(tempModel.qualifiedName + ", " + tempModel.start + ", " + tempModel.stop + ", " + tempModel.numberOfIntervals + ", " + tempModel.tolerance + ", " + tempModel.solver + ", " + tempModel.outputFormat);
		}
	}
}
