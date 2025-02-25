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
package org.openmodelica.modelicaml.common.helpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;


public class VerificationServices {
	
	private static Date date = null;
	 
	
	// Papyrus ModelicaML project verification session folder path
	public static String verificationSessionFolderAbsolutePath = "";
	
	// Papyrus ModelicaML project name
	public static String projectName = "";
	
	// Papyrus ModelicaML project absolute path
	public static String projectAbsolutePath = "";
	
	// model to be executed
	public static HashSet<Element> verificationModels = new HashSet<Element>();
	
	// used to separate the qualified name of UML element and the component path in a locate html link 
	public static String linkDelimiter = Constants.linkDelimiter;
	
	
	/*
	 * Time stamp handling
	 */
	public static void setDate() {
		Calendar c1 = Calendar.getInstance(); // today
		Date date = c1.getTime();
		VerificationServices.date = date;
	}
	
	public static String getTime(Element elt){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String getTime(){
		Calendar c1 = Calendar.getInstance(); // today
		Date date = c1.getTime();
		VerificationServices.date = date;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String getTimeStamp(String timeStamp){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	public static String getTimeStamp(){
		Calendar c1 = Calendar.getInstance(); // today
		Date date = c1.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	
	
	/*
	 * Folder and file names and paths handling
	 */
	public static String getTestSessionFolderProjectPath(Element umlElement){
		return Constants.folderName_verification_gen + "/"+Constants.folderName_verification_session+"_"+getTimeStamp("");
	}
	
	public static String getTestSessionFileName(Element umlElement){
		return Constants.fileName_verification_session;
	}
	
	//Path
	public static void setTestSessionFolderAbsolutePath(String path){
		verificationSessionFolderAbsolutePath = path;
	}
	
	public static void setProjectFolderAbsolutePath(String path){
		projectAbsolutePath = path;
	}
	
	public static void setProjectName(String name){
		projectName = name;
	}
	
	public static String getTestSessionFolderAbsolutePath(Element umlElement){
		return verificationSessionFolderAbsolutePath;
	}
	
	public static String getCodeGenFolderName(Element elt){
		return Constants.folderName_code_gen;
	}
	public static String getCodeIncFolderName(Element elt){
		return Constants.folderName_code_sync;
	}
	
	public static String getTopLevelModelicaFilePath(Element umlElement){
//		String projectName = umlElement.getModel().eResource().getURI().segment(1);
//		
//		IWorkspace workspace = ResourcesPlugin.getWorkspace();
//		IWorkspaceRoot root = workspace.getRoot();
//		IProject iProject = root.getProject(projectName);
//		
//		String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
//		String packageMoFilePath = projectAbsolutePath+"/"+Constants.folderName_code_gen+"/"+ StringUtls.replaceSpecChar(umlElement.getModel().getName()) + "/" +"package.mo";
		String packageMoFilePath = verificationSessionFolderAbsolutePath+"/"+Constants.folderName_code_gen+"/"+ StringUtls.replaceSpecChar(umlElement.getModel().getName()) + "/" +"package.mo";
		return packageMoFilePath;
	}
	

	public static String getLinkDelimiterString(Element elt) {
		return VerificationServices.linkDelimiter;
	}
	
	
	
	
	/*
	 * Property and MACRO Names
	 */
	public static String getReqTestVerdictPropertyName(Element elt){
		return Constants.propertyName_requirementsVerificationVerdict;
	}
	public static String getReqVerificationVerdictName(Element umlElement){
		return Constants.propertyName_requirementsVerificationVerdict;
	}
	
	
	public static String getTestPassedPropertyName(Element umlElement){
		return Constants.propertyName_testPassed;
	}
	public static String getAllRequirementsEvaluatedPropertyName(Element umlElement){
		return Constants.propertyName_allRequirementsEvaluated;
	}
	public static String getSomeRequirementsViolatedPropertyName(Element umlElement){
		return Constants.propertyName_someRequirementsViolated;
	}
	
	public static String getMACROatLeastOneTimeTrue(Element umlElement){
		return Constants.MACRO_atLeastOneTimeTrue;
	}
	public static String getMACROatLeastOneTimeFalse(Element umlElement){
		return Constants.MACRO_atLeastOneTimeFalse;
	}
	public static String getMACROalwaysTrue(Element umlElement){
		return Constants.MACRO_alwaysTrue;
	}
	public static String getMACROalwaysFalse(Element umlElement){
		return Constants.MACRO_alwaysFalse;
	}
	public static String getMACROchangedItsValue(Element umlElement){
		return Constants.MACRO_changedItsValue;
	}
	
	
	/*
	 * Verification models getter
	 */
	
	public static List<Element> getTestModels(Element umlElement){
		List<Element> sortedList = ModelicaMLServices.getSortedByName(verificationModels);
		return sortedList;
	}
	
	public static int getTestModelsNumber(Element umlElement){
		return verificationModels.size();
	}
	
	
	
	
	/*
	 * Verification models data getters
	 */
	
	public static String getTestModelQName(Element umlElement){
		if (umlElement instanceof NamedElement) {
			String temp_modelElementWoSpecChar = StringUtls.replaceSpecCharExceptThis(((NamedElement)umlElement).getQualifiedName(), "::");
			
			String modelElementDotPath = temp_modelElementWoSpecChar.replaceAll("::", ".");
			return modelElementDotPath;
		}
		return "";
	}
	
	
	public static String getStartTimeReportString (Element umlElement){
		if (umlElement instanceof NamedElement) {
			NamedElement namedElement = ((NamedElement)umlElement); 
			Stereotype stereotype = namedElement.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if (stereotype != null ) {
				Object value = namedElement.getValue(stereotype, Constants.propertyName_startTime);
				if (value != null) {
					return value.toString();
				}
			}
		}
		return "default";
	}
	
	public static String getStopTimeReportString (Element umlElement){
		if (umlElement instanceof NamedElement) {
			NamedElement namedElement = ((NamedElement)umlElement); 
			Stereotype stereotype = namedElement.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if (stereotype != null ) {
				Object value = namedElement.getValue(stereotype, Constants.propertyName_stopTime);
				if (value != null) {
					return value.toString();
				}
			}
		}
		return "default";
	}
	
	public static String getToleranceReportString (Element umlElement){
		if (umlElement instanceof NamedElement) {
			NamedElement namedElement = ((NamedElement)umlElement); 
			Stereotype stereotype = namedElement.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if (stereotype != null ) {
				Object value = namedElement.getValue(stereotype, Constants.propertyName_tolerance);
				if (value != null) {
					return value.toString();
				}
			}
		}
		return "default";
	}
	
	public static String getNumberOfIntervalsReportString (Element umlElement){
		if (umlElement instanceof NamedElement) {
			NamedElement namedElement = ((NamedElement)umlElement); 
			Stereotype stereotype = namedElement.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if (stereotype != null ) {
				Object value = namedElement.getValue(stereotype, Constants.propertyName_numberOfIntervals);
				if (value != null) {
					return value.toString();
				}
			}
		}
		return "default";
	}
	
	
	
	
	/*
	 * Simulation settings
	 */
	public static String getStartTime(Element elt){
		if (elt instanceof NamedElement) {
			Stereotype s = elt.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if ( s != null ) {
				Object o = elt.getValue(s, Constants.propertyName_startTime);
				if (o != null) {
					return o.toString();
				}
			}
		}
		return Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_startTime, "0", null);
	}
	
	public static String getStopTime(Element elt){
		if (elt instanceof NamedElement) {
			Stereotype s = elt.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if ( s != null ) {
				Object o = elt.getValue(s, Constants.propertyName_stopTime);
				if (o != null) {
					if (!o.toString().trim().equals("0")) {
						return o.toString();
					}
				}
			}
		}
		return Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_stopTime, "10", null);
	}
	
	
	public static String getTolerance(Element elt){
		if (elt instanceof NamedElement) {
			Stereotype s = elt.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if ( s != null ) {
				Object o = elt.getValue(s, Constants.propertyName_tolerance);
				if (o != null) {
					if (!o.toString().trim().equals("0")) {
						return o.toString();
					}
				}
			}
		}
		return Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_tolerance, "0.000001", null);
	}
	
	public static String getInterval(Element elt){
		if (elt instanceof NamedElement) {
			Stereotype s = elt.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if ( s != null ) {
				Object o = elt.getValue(s, Constants.propertyName_numberOfIntervals);
				if (o != null) {
					if (!o.toString().trim().equals("0")) {
						return o.toString();
					}
				}
			}
		}
		return Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_numberOfIntervals, "500", null);
	}
	
	public static String getOutputFormat(Element elt){
//		return "plt";
		return Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_outputFormat, "mat", null);
	}
	
	public static String getSolver(Element elt){
		return Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_solver, "dassl", null);
	}
	
	
	
	
	
	
	
	
	public static String getRequirementID(TreeObject treeItem){
		Element element = treeItem.getUmlElement();
		if (element instanceof Property) {
			Type type = ((Property)element).getType();
			if (type != null) {
				Stereotype s = type.getAppliedStereotype(Constants.stereotypeQName_Requirement);
				if (s != null ) {
					Object o = type.getValue(s, Constants.propertyName_id);
					if (o != null) {
						return o.toString();
					}
				}
			}
		}
		return "Not defined.";
	}
	
	public static String getVariability(TreeObject treeItem){
		Element element = treeItem.getUmlElement();
		if (element instanceof Property) {
			Stereotype s = element.getAppliedStereotype(Constants.stereotypeQName_Variable);
			if (s != null ) {
				Object o = element.getValue(s, Constants.propertyName_variability);
				if (o instanceof EnumerationLiteral) {
					return ((EnumerationLiteral)o).getName();
				}
			}
		}
		return "Not defined.";
	}
	
	public static String getRequirementText(TreeObject treeItem){
		Element element = treeItem.getUmlElement();
		if (element instanceof Property) {
			Type type = ((Property)element).getType();
			if (type != null) {
				Stereotype s = type.getAppliedStereotype(Constants.stereotypeQName_Requirement);
				if (s != null ) {
					Object o = type.getValue(s, Constants.propertyName_text);
					if (o != null) {
						return o.toString();
					}
				}
			}
		}
		return "Not defined.";
	}
	
	public static String getComponentIndicator(TreeObject treeItem){
		Element element = treeItem.getUmlElement();
		if (element instanceof Property) {
			Type type = ((Property)element).getType();
			if (type != null) {
				Stereotype s = type.getAppliedStereotype(Constants.stereotypeQName_Requirement);
				if (s != null ) { return "Requirement"; }
				
				s = type.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario);
				if (s != null ) { return "Verification Scenario"; }
				
				s = type.getAppliedStereotype(Constants.stereotypeQName_CalculationModel);
				if (s != null ) { return "Calculation Model"; }
				
				if (treeItem.getName().startsWith(Constants.systemModelPropertyNamePrefix)) {
					return "Model to be verified";
				}
			}
		}
		return "Model";
	}
	
	
	/*
	 * Verification Models components getters
	 */
	
	public static EList<TreeObject> getRequirements(Element element){
		EList<TreeObject> treeItems = new BasicEList<TreeObject>();
		if (element instanceof Class) {
			
			// instantiate
			ClassInstantiation ci = new ClassInstantiation((Class) element, true, false, null, true);
			ci.setUmlModel(element.getModel());
			ci.createTree();
			ci.collectBindingsDataFromUmlModel();

			if (ci.getTreeRoot().hasChildren()) {
				TreeObject[] children = ci.getTreeRoot().getChildren();
				for (int i = 0; i < children.length; i++) {
					TreeObject treeObject = children[i];
					if (treeObject.getUmlElement() instanceof Property) {
						Type type = ((Property)treeObject.getUmlElement()).getType();
						if (type != null) {
							Stereotype s = type.getAppliedStereotype(Constants.stereotypeQName_Requirement);
							if (s != null) {
								treeItems.add(treeObject);
							}
						}
					}
				}
			}
		}
		return treeItems;
	}
	
	
	public static EList<TreeObject> getComponents(Element element){
		EList<TreeObject> treeItems = new BasicEList<TreeObject>();
		if (element instanceof Class) {

			// instantiate
			ClassInstantiation ci = new ClassInstantiation((Class) element, true, false, null, true);
			ci.setUmlModel(element.getModel());
			ci.createTree();
			ci.collectBindingsDataFromUmlModel();
			
			if (ci.getTreeRoot().hasChildren()) {
				TreeObject[] children = ci.getTreeRoot().getChildren();
				for (int i = 0; i < children.length; i++) {
					TreeObject treeObject = children[i];
					if (treeObject.getUmlElement() instanceof Property) {
						Type type = ((Property)treeObject.getUmlElement()).getType();
						if (type != null) {
							Stereotype s = type.getAppliedStereotype(Constants.stereotypeQName_Requirement);
							// if it is not a Requirement and not the verification verdict
							if (s == null && !treeObject.getName().startsWith(Constants.propertyName_requirementsVerificationVerdict)) { 
								treeItems.add(treeObject);
							}
						}
					}
				}
			}
		}
		return treeItems;
	}
	
	public static String getTreeItemTypeName(TreeObject treeItem){
		Element element = treeItem.getUmlElement();
		if (element instanceof Property) {
			Type type = ((Property)element).getType();
			if (type != null) {
				return type.getName();				
			}
		}
		return "Unknown";
	}
	
	public static String getTreeItemTypeQName(TreeObject treeItem){
		Element element = treeItem.getUmlElement();
		if (element instanceof Property) {
			Type type = ((Property)element).getType();
			if (type != null) {
				return type.getQualifiedName();				
			}
		}
		return "Unknown";
	}
	
	@Deprecated
	public static List<TreeObject> getRequiredClientsTreeItems(TreeObject treeItem, HashSet<TreeObject> collectedItems) {
		HashSet<TreeObject> collectedItem_temp = new HashSet<TreeObject>();
		collectedItem_temp.addAll(collectedItems);
		
		if (treeItem != null && treeItem instanceof TreeParent && ((TreeParent)treeItem).hasChildren()) {
			TreeObject[] children =  ((TreeParent)treeItem).getChildren();
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];
				
				/*
				 * TODO: this is not accurate. If the tree object (the actual client) is referenced in
				 * client operation then we need to track this and find the actual client instead of#
				 * taking the client that has the operation...    
				 */
				
				if (!collectedItem_temp.contains(treeObject) && treeObject.isValueClient_required()) {
					collectedItem_temp.add(treeObject);
				}
				// recursive call
				if (treeObject instanceof TreeParent) {
					collectedItem_temp.addAll(getRequiredClientsTreeItems(treeObject, collectedItem_temp));
				}
			}
		}
		
		List<TreeObject> sortedList = ModelicaMLServices.getSortedByDotPath(collectedItem_temp);
		
		return sortedList;
	}
	
	
	private static TreeObject findTreeObject(ClassInstantiation ci, TreeObject objectFromOtherClassInstantiation){
		
		for (TreeObject treeObject : ci.getAllTreeObjects()) {
			
			String name = treeObject.getName();
			String dotPath = treeObject.getDotPath();
			Element element = treeObject.getUmlElement();
			
			if (name.equals(objectFromOtherClassInstantiation.getName())
					&& dotPath.equals(objectFromOtherClassInstantiation.getDotPath())
					&& element.equals(objectFromOtherClassInstantiation.getUmlElement())) {
				return treeObject;
			}
		}
		return null;
	}
	
	
	public static List<TreeObject> getClientsTreeItems(ClassInstantiation ci, TreeObject treeItem, HashSet<TreeObject> collectedItems, boolean onlyMandatoryClients) {
		
		/*
		 * TODO: sometimes treeItem is null. find the bug...
		 */
		if (treeItem == null) {
			return new ArrayList<TreeObject>();
		}

		/*
		 * Sometimes the passed treeItem is not contained within the passed instantiation.
		 * This may happen when instantiations were cashed and or recreated before being passed. 
		 * In order to avoid confusion we first make sure that we find the 
		 * right treeObject inside the passed instantiation based on
		 * the name, dotPath and the UML element of the passed treeItem.  
		 */
		if (!ci.getAllTreeObjects().contains(treeItem)) {
			treeItem = findTreeObject(ci, treeItem);
		}

		if (treeItem == null) {
			// empty
			return new ArrayList<TreeObject>();
		}
		
		HashSet<TreeObject> collectedItem_temp = new HashSet<TreeObject>();
		collectedItem_temp.addAll(collectedItems);
		
		if (treeItem != null && treeItem instanceof TreeParent && ((TreeParent)treeItem).hasChildren()) {
			TreeObject[] children =  ((TreeParent)treeItem).getChildren();
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];

				/*
				 * If the tree object (the actual client) is referenced in
				 * client operation then we need to track this and find the actual client instead of#
				 * taking the client that has the operation...    
				 */
				
				if (!collectedItem_temp.contains(treeObject) && treeObject.isValueClient()) {
					if (onlyMandatoryClients && treeObject.isValueClient_required()) {
						collectedItem_temp.addAll(getActualClients(ci, treeObject));
					}
					else if (treeObject.isValueClient()) {
						collectedItem_temp.addAll(getActualClients(ci, treeObject));
					}
				}
				
				// recursive call
				if (treeObject instanceof TreeParent) {
					collectedItem_temp.addAll(getClientsTreeItems(ci, treeObject, collectedItem_temp, onlyMandatoryClients));
				}
			}
		}
		
		List<TreeObject> sortedList = ModelicaMLServices.getSortedByDotPath(collectedItem_temp);
		
		return sortedList;
	}
	
	
	public static HashSet<TreeObject> getActualClients(ClassInstantiation ci, TreeObject upperClientTeeItem){

		Element element = upperClientTeeItem.getProperty();
		HashSet<String> operations = new HashSet<String>();

		if (element != null) {
			// add all client operations
			if (ci.getValueBindingsDataCollector().getReferencedClients().contains(element)) {
				HashSet<String> clientOperations = ci.getValueBindingsDataCollector().getClientOperations().get(element);
				if (clientOperations != null) {
					operations.addAll(clientOperations);
				}
			}
		}
		
		HashSet<String> clientPaths = new HashSet<String>();
		
		for (String operation : operations) {
			String[] scriptCodeSplitted = operation.split(";");
			if ( scriptCodeSplitted.length > 0) { // if there is at least one line that ends with ";"
				for (int i = 0; i < scriptCodeSplitted.length; i++) { // the next will always overwrite the previous, i.e. the last one is always taken. 
					
					if ( !scriptCodeSplitted[i].trim().equals("") ) { // if it is not an empty line 
						String[] bindingEqationParts = scriptCodeSplitted[i].split("=");
						if (bindingEqationParts.length == 2 ) {// it is a binding equation with left and right part 
							
							// get the left and right parts and trim them
							String leftHand = bindingEqationParts[0].trim();
//							String rightHand = bindingEqationParts[1].trim();
							
							String expandedLeftHand = leftHand.replaceFirst(Constants.MACRO_clientPath, upperClientTeeItem.getDotPath()).trim();
							clientPaths.add(expandedLeftHand);
						}
					}
				}
			}
		}
		
		HashSet<TreeObject> actualClients = getTreeObjects(ci, clientPaths);
		
		if (actualClients.size() > 0) {
			return actualClients;
		}
		else {
			// return the upper level client passed
			actualClients = new HashSet<TreeObject>();
			actualClients.add(upperClientTeeItem);
		}
		
		return actualClients;
	}
	
	
	
	/*
	 * get tree objects by path
	 */
	private static HashSet<TreeObject> getTreeObjects(ClassInstantiation ci, HashSet<String> paths){
		HashSet<TreeObject> treeObjectsFound = new HashSet<TreeObject>();
		for (TreeObject treeObject : ci.getAllTreeObjects()) {
			if (paths.contains(treeObject.getDotPath())) {
				treeObjectsFound.add(treeObject);
			}
		}
		return treeObjectsFound;
	}
	
	
	
	
	/*
	 * HTML generation
	 */
	
	public static String getRequirementsHTML(Element elt){
		EList<TreeObject> requirements = getRequirements(elt);
		String html = "";
		if (elt instanceof Class) {
			for (TreeObject treeObject : requirements) {
				html = html + "\n" + getRequirementHTML(treeObject, (Class) elt);
			}
		}
		return html;
	}
	
	public static String getComponentsHTML(Element elt){
		EList<TreeObject> components = getComponents(elt);
		String html = "";
		if (elt instanceof Class) {
			for (TreeObject treeObject : components) {
				html = html + "\n" + getComponentHTML(treeObject, (Class) elt);
			}
		}
		return html;
	}
	
	public static String getComponentHTML(TreeObject treeItem, Class testModel){
		String html = "";
		html = 
		"<!-- "+getComponentIndicator(treeItem)+" '" + getTreeItemTypeName(treeItem) +  "(" + getTreeItemTypeQName(treeItem) + ")" +  "' -->" + "\n" + 
		"<div style='margin-left:10px; margin-right:10px; border:1px #aaa9a9 solid; background-color:#F9F9F9; '>" + "\n" + 
		"	<div style='padding:5px;'>" + "\n" + 
			"	<span style='color:#000000; margin-left:0px;'>" + getComponentIndicator(treeItem)+": "+"\n" + 
			
			"		<script type='text/javascript'>" + "\n" +
			"			writeLink('locate:"+testModel.getQualifiedName()+linkDelimiter +treeItem.getDotPath()+ "', '<strong>" + getTreeItemTypeName(treeItem) + "</strong>');" + "\n" + 
			"		</script>" + "\n" + 
					
			"	</span> <br>" + "\n" + 
			"	<span style='color:#000000; font-size:10px;'>("+getTreeItemTypeQName(treeItem)+")</span><br />		" + "\n" + 
		"	</div>" +

		"	" + getRequiredClientsHTML(treeItem, testModel) + 
		
		"</div>" + "\n" + 
		"<br />" + "\n" + 
		"";
		return html;
	}

	
	
	public static String getRequirementHTML(TreeObject treeItem, Class testModel){
		String html = "";
		html = 
		"<!-- "+getComponentIndicator(treeItem)+" '" + getTreeItemTypeName(treeItem) +  "(" + getTreeItemTypeQName(treeItem) + ")" +  "' -->" + "\n" + 
		"<div style='margin-left:10px; margin-right:10px; border:1px #aaa9a9 solid; background-color:#F9F9F9; '>" + "\n" + 
			" <!-- Verdicts START  -->" + "\n" + 
			
			"	<div style='padding:5px;'>" + "\n" + 
				"	<span style='color:#000000;'>" + "\n" + 
				
//				"		<script type='text/javascript'>" + "\n" + 
//				"			writeRequirementPassedString(data['"+testModel.getQualifiedName()+"']['" +treeItem.getDotPath()+ "."+Constants.propertyName_evaluated+"']['"+Constants.MACRO_atLeastOneTimeTrue+"']," + "\n" + 
//				"			data['"+testModel.getQualifiedName()+"']['" +treeItem.getDotPath()+ "."+Constants.propertyName_violated+"']['"+Constants.MACRO_alwaysFalse+"']);" + "\n" + 
//				"		</script>" + "\n" + 
				
				"		<script type='text/javascript'>" + "\n" + 
				"			writeRequirementPassedString(data['"+testModel.getQualifiedName()+"']['" +treeItem.getDotPath()+ "."+Constants.propertyName_mStatus+"-"+Constants.propertyName_evaluated+"']['"+Constants.MACRO_hadNotAlwaysValue+"(0)']," + "\n" + 
				"			data['"+testModel.getQualifiedName()+"']['" +treeItem.getDotPath()+ "."+Constants.propertyName_mStatus+"-"+Constants.propertyName_violated+"']['"+Constants.MACRO_hadAtLeastOnceValue+"(2)']);" + "\n" + 
				"		</script>" + "\n" + 

				"	</span>" + "\n" + 
				"	<span style='color:#000000; margin-left:10px;'>" + getComponentIndicator(treeItem)+": "+"\n" + 
				
				"		<script type='text/javascript'>" + "\n" +
				"			writeLink('locate:"+testModel.getQualifiedName()+linkDelimiter +treeItem.getDotPath()+ "', '<strong>"+getTreeItemTypeName(treeItem)+" (ID "+getRequirementID(treeItem)+")</strong>');" + "\n" + 
				"		</script>" + "\n" + 
						
				"	</span> <br>" + "\n" + 
				"	<span style='color:#000000; font-size:10px;'>("+getTreeItemTypeQName(treeItem)+")</span><br />		" + "\n" + 
				"	<span><strong>Text:</strong> "+getRequirementText(treeItem)+"</span><br />" + "\n" + 
			"	</div>" +
			
			"	<div style='padding:5px;'>" + "\n" +
				"	<table cellpadding='0' cellspacing='0' border='0'>" + "\n" + 
				"		<tr>" + "\n" + 
				"			<td><i>verdict</i>" + "\n" +
				
//				"				<script type='text/javascript'>" + "\n" + 
//				"					writeLink('locate:"+testModel.getQualifiedName()+"#" +treeItem.getDotPath()+ "."+Constants.propertyName_evaluated+"', '<strong>"+Constants.propertyName_evaluated+"</strong>');" + "\n" + 
//				"				</script>" + "\n" +
//				
//				"				<td>&nbsp;:&nbsp;</td>" + "\n" + 
//				"				<td>" + "\n" + 
//				
//				"				<script type='text/javascript'>	" + "\n" + 
//				"					writeAtLeastOneTimeTrueString(data['"+testModel.getQualifiedName()+"']['" +treeItem.getDotPath()+ "."+Constants.propertyName_evaluated+"']['"+Constants.MACRO_atLeastOneTimeTrue+"']);" + "\n" + 
//				"				</script>" + "\n" +
				
				"				<script type='text/javascript'>" + "\n" + 
				"					writeLink('locate:"+testModel.getQualifiedName()+linkDelimiter +treeItem.getDotPath()+ "."+Constants.propertyName_mStatus+"', '<strong>"+Constants.propertyName_evaluated+"</strong>');" + "\n" + 
				"				</script>" + "\n" +
				
				"				<td>&nbsp;:&nbsp;</td>" + "\n" + 
				"				<td>" + "\n" + 
				
				"				<script type='text/javascript'>	" + "\n" + 
				"					writeBooleanGreenYesString(data['"+testModel.getQualifiedName()+"']['" +treeItem.getDotPath()+ "."+Constants.propertyName_mStatus+"-"+Constants.propertyName_evaluated+"']['"+Constants.MACRO_hadNotAlwaysValue+"(0)']);" + "\n" + 
				"				</script>" + "\n" +
				
				"			</td>" + "\n" + 
				"		</tr>" + "\n" + 
				"		<tr>" + "\n" + 
				"			<td><i>verdict</i>" + "\n" + 
//				"				<script type='text/javascript'>" + "\n" + 
//				"					writeLink('locate:"+testModel.getQualifiedName()+"#" +treeItem.getDotPath()+ "."+Constants.propertyName_violated+"', '<strong>"+Constants.propertyName_violated+"</strong>');" + "\n" + 
	//				"			</script>" + "\n" + 
	//				
	//				"			</td>" + "\n" +  "\n" + 
	//				"			<td>&nbsp;:&nbsp;</td>" + "\n" + 
	//				"			<td>" + "\n" + 
	//				
	//				"			<script type='text/javascript'>" + "\n" + 
	//				"				writeAlwaysFalseString(data['"+testModel.getQualifiedName()+"']['" +treeItem.getDotPath()+ "."+Constants.propertyName_violated+"']['"+Constants.MACRO_alwaysFalse+"']);" + "\n" + 
	//				"			</script>" + "\n" + 
					
					"			<script type='text/javascript'>" + "\n" + 
					"				writeLink('locate:"+testModel.getQualifiedName()+linkDelimiter +treeItem.getDotPath()+ "."+Constants.propertyName_mStatus+"', '<strong>"+Constants.propertyName_violated+"</strong>');" + "\n" + 
					"			</script>" + "\n" + 
					
					"			</td>" + "\n" +  "\n" + 
					"			<td>&nbsp;:&nbsp;</td>" + "\n" + 
					"			<td>" + "\n" + 
					
					"			<script type='text/javascript'>" + "\n" + 
					"				writeBooleanRedYesString(data['"+testModel.getQualifiedName()+"']['" +treeItem.getDotPath()+ "."+Constants.propertyName_mStatus+"-"+Constants.propertyName_violated+"']['"+Constants.MACRO_hadAtLeastOnceValue+"(2)']);" + "\n" + 
					"			</script>" + "\n" +
					
					"			</td>" + "\n" + 
				"		</tr>" + "\n" + 
				"	</table>" + "\n" + 
				"</div>" + "\n" +
			"	<br />" + "\n" + 
			
			" <!-- Verdicts END -->" + "\n" + 
			
			"	" + getRequiredClientsHTML(treeItem, testModel) +
			
			"</div>" + "\n" +
			"<br />" + "\n" + 
			"";
		return html;
	}
	
	private static String getRequiredClientsHTML(TreeObject treeItem, Class testModel) {
		String html = "";
		List<TreeObject> requiredClients = getRequiredClientsTreeItems(treeItem, new HashSet<TreeObject>());
		html = html  + "\n" + "<!-- madantory clients START  -->" + "\n";
		
		for (TreeObject treeObject : requiredClients) {
			html = html + 
			"	<div style='border: position:relative; margin-left:5px;'><i>madantory client</i>: " + "\n" + 
			"		<strong><script type='text/javascript'>writeLink('locate:"+testModel.getQualifiedName()+linkDelimiter+treeObject.getDotPath()+"', '"+treeObject.getDotPath()+"');</script></strong>" + "\n" + 
			"		" + "\n" + 
			"		(<script type='text/javascript'>" + "\n" + 
			"			writeChangedItsValueString(data['"+testModel.getQualifiedName()+"']['"+treeObject.getDotPath()+"']['"+Constants.MACRO_changedItsValue+"']);" + "\n" + 
			"		</script>)" + "\n" + 
			"		" + "\n" + 
			"		<br>" + "\n" + 
			"		<table cellpadding='0' cellspacing='0' border='0' style='position:relative; left:10px; padding-top:5px;'>" + "\n" + 
			"			<tr>" + "\n" + 
			"				<td valign='top'>Type</td>" + "\n" + 
			"				<td valign='top'>&nbsp;:&nbsp;</td>" + "\n" + 
			"				<td valign='top'>= "+getTreeItemTypeName(treeObject)+"</td>" + "\n" + 
			"			</tr>" + "\n" +
			"			<tr>" + "\n" + 
			"				<td valign='top'>Variability</td>" + "\n" + 
			"				<td valign='top'>&nbsp;:&nbsp;</td>" + "\n" + 
			"				<td valign='top'>= "+getVariability(treeObject)+"</td>" + "\n" + 
			"			</tr>" + "\n" +
			"			<tr>" + "\n" + 
			"				<td valign='top'>Binding code</td>" + "\n" + 
			"				<td valign='top'>&nbsp;:&nbsp;</td>" + "\n" + 
			"				<td valign='top'>= "+treeObject.getFinalModificationRightHand()+"</td>" + "\n" + 
			"			</tr>" + "\n" + 
			"		</table>" + "\n" + 
			"	</div>" + "\n" + 
			"	<!-- *************************************************************************  -->" + "\n" + 
			"	<br />" + "\n";
		}
		
		html = html + "<!-- madantory clients  END -->" + "\n" + "\n";
		return html;
	}
	
	
	
	/*
	 * JavaScript generation
	 */
	
	public static String getRequirementsJS(Element elt){
		EList<TreeObject> requirements = getRequirements(elt);
		String js = "";
		if (elt instanceof Class) {
			for (TreeObject treeObject : requirements) {
				js = js + "/* ~~~~~ Requirement "+treeObject.getDotPath()+": verdicts ~~~~~ */" + "\n" + 
//				"data['"+((Class)elt).getQualifiedName()+"']['"+treeObject.getDotPath()+"."+Constants.propertyName_evaluated+"'] = new Array();" + "\n" + 
//				"data['"+((Class)elt).getQualifiedName()+"']['"+treeObject.getDotPath()+"."+Constants.propertyName_evaluated+"']['"+Constants.MACRO_atLeastOneTimeTrue+"'] = '###"+treeObject.getDotPath()+"."+Constants.propertyName_evaluated+":"+Constants.MACRO_atLeastOneTimeTrue+"###';" + "\n" + 
//				"data['"+((Class)elt).getQualifiedName()+"']['"+treeObject.getDotPath()+"."+Constants.propertyName_violated+"'] = new Array();" + "\n" + 
//				"data['"+((Class)elt).getQualifiedName()+"']['"+treeObject.getDotPath()+"."+Constants.propertyName_violated+"']['"+Constants.MACRO_alwaysFalse+"'] = '###"+treeObject.getDotPath()+"."+Constants.propertyName_violated+":"+Constants.MACRO_alwaysFalse+"###';" + "\n" +
				
				// Use the property mStatus and check evaluated = hadNotAlwaysValue(0), violated = hadNeverValue(2); 
				"data['"+((Class)elt).getQualifiedName()+"']['"+treeObject.getDotPath()+"."+Constants.propertyName_mStatus+"-"+Constants.propertyName_evaluated+"'] = new Array();" + "\n" + 
				"data['"+((Class)elt).getQualifiedName()+"']['"+treeObject.getDotPath()+"."+Constants.propertyName_mStatus+"-"+Constants.propertyName_evaluated+"']['"+Constants.MACRO_hadNotAlwaysValue+"(0)'] = '###"+treeObject.getDotPath()+"."+Constants.propertyName_mStatus+":"+Constants.MACRO_hadNotAlwaysValue +"(0)" + "###';" + "\n" + 
				"data['"+((Class)elt).getQualifiedName()+"']['"+treeObject.getDotPath()+"."+Constants.propertyName_mStatus+"-"+Constants.propertyName_violated+"'] = new Array();" + "\n" + 
				"data['"+((Class)elt).getQualifiedName()+"']['"+treeObject.getDotPath()+"."+Constants.propertyName_mStatus+"-"+Constants.propertyName_violated+"']['"+Constants.MACRO_hadAtLeastOnceValue+"(2)'] = '###"+treeObject.getDotPath()+"."+Constants.propertyName_mStatus+":"+Constants.MACRO_hadAtLeastOnceValue + "(2)" +"###';" + "\n" +

				"" ;
				
				
				js = js + "\n" + getRequiredClientsJS(treeObject, (Class) elt);
			}
		}
		return js;
	}
	
	
	public static String getComponentsJS(Element elt){
		EList<TreeObject> components = getComponents(elt);
		String js = "";
		if (elt instanceof Class) {
			for (TreeObject treeObject : components) {
				js = js + "\n" + getRequiredClientsJS(treeObject, (Class) elt);
			}
		}
		return js;
	}
	
	
	private static String getRequiredClientsJS(TreeObject treeItem, Class testModel) {
		String js = "";
		List<TreeObject> requiredClients = getRequiredClientsTreeItems(treeItem, new HashSet<TreeObject>());
		js = js  + "\n" + "/* ~~~~~ " + getTreeItemTypeQName(treeItem) + ": madantory clients START~~~~~ */" + "\n";
		
		/* "testModel: required clients */
		for (TreeObject treeObject : requiredClients) {
			js = js  + "data['" + testModel.getQualifiedName() + "']['"+treeObject.getDotPath()+"'] = new Array();" + "\n" + 
			"data['" + testModel.getQualifiedName() + "']['"+treeObject.getDotPath()+"']['"+Constants.MACRO_changedItsValue+"'] = '###"+treeObject.getDotPath()+":changedItsValue###';" + "\n" + 
			"";
		}
		js = js  + "\n" + "/* ~~~~~ " + getTreeItemTypeQName(treeItem) + ": madantory clients END~~~~~ */" + "\n" + "\n";
		return js;
	}

	
}
