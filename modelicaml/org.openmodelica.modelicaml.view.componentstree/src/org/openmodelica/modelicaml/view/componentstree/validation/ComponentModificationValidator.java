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
package org.openmodelica.modelicaml.view.componentstree.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.infra.core.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.instantiation.ModificationManager;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.editor.xtext.modification.ui.internal.ModificationActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;

import com.google.inject.Injector;

//public class ValueBindingsValidator implements IRunnableWithProgress {
public class ComponentModificationValidator {
	
	List<TreeObject> firstLevelComponents = new ArrayList<TreeObject>();
	
	private Injector injector;
	private String fileExtension;
	private PropertiesSectionXtextEditorHelper editor;
	
	// Composite used temporary for instantiating editors that are never shown ...
	private Composite tempComposite = new Composite(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.NONE);
	
	private ExtendedUmlModel umlModel;
	private IProject iProject;
	
//	private String textToEdit;
	
//	private Element classToBeValidated = null;

	public ComponentModificationValidator(TreeParent parent){
		
//		this.classToBeValidated = parent.getSelectedClass();
		
		// collect client, mediator and provider tree nodes
		collectItems(parent);
		
		// set up marker data
		umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
		String projectName = umlModel.getResource().getURI().segment(1);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		iProject = root.getProject(projectName);
	}
	
	private String stringLog = "";
	
	public void validate(){

		// delete all old markers
		deleteValidationMarkers(iProject);

		if (firstLevelComponents.size() > 0) {
			validateLeaves();
		}
		// TODO: validate the array size and declarations, conditinal expressions, etc?! 
	}
	
	private void validateLeaves(){
		
		for (TreeObject treeObject : firstLevelComponents) {
			
			if (treeObject.getUmlElement() instanceof Property) {
				
				HashSet<String> modifications = ModificationManager.getModifications(treeObject.getUmlElement());
				
				for (String textToEdit : modifications) {
					if (!textToEdit.trim().equals("")) {
			    		
						injector = ModificationActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.modification.Modification");
			    		fileExtension = "" + ".modelicamlmodification"; 
						
						editor = new PropertiesSectionXtextEditorHelper(treeObject.getFirstLevelComponent(), injector, null, textToEdit, fileExtension);
						// TODO: note, refactor the glue code in order to avoid using composites (UI) for the purpose of simple code validation
						editor.showEditor(tempComposite, SWT.NONE);

						// Code completion support
						// for modification: this is used to obtain the list of the modified component attributes.
						if (treeObject.getFirstLevelComponent() instanceof Property) {
							ModelicaMLContentAssist.setPropertyName( StringUtls.replaceSpecChar( ((Property)treeObject.getFirstLevelComponent()).getName()) );

							Element owningClass = treeObject.getFirstLevelComponent().getOwner(); 
							if (owningClass instanceof Class) { // components of the owner of the property being modified
								ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
							}
						}
						
						// Generate marker and set hasErrors for the tree items
						if (editor.isDocumentHasErrors()) {
							String[] splited = textToEdit.split("=");
							String leftHandComponentReferenceWithFirstLevelComponent = treeObject.getDotPath() + "." + splited[0].trim();
							createMarker(treeObject, leftHandComponentReferenceWithFirstLevelComponent, "error", 
									"'"+leftHandComponentReferenceWithFirstLevelComponent+"' has errors in its modification code.");
						}
					}
				}
			}
		}
	}

	

	
	public void collectItems(TreeParent parent){
		if (parent.hasChildren()) {
			TreeObject[] children = parent.getChildren();	
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];
				if (treeObject.getFirstLevelComponent() == treeObject.getUmlElement()) {
					firstLevelComponents.add(treeObject);

					// TODO: collect all items that has array size in an separate set and validate them separately.
					
				}
				// recursive call
				if (treeObject instanceof TreeParent) {
					collectItems((TreeParent) treeObject);
				}
			}
		}
	}
	
	
	
	// Marker *****************************************
	
	private String markerType = Constants.MARKERTYPE_COMPONENT_MODIFICATION;
	
	public IMarker createMarker(TreeObject treeObject, String sourceID, String criticality, String msg){
		Element elt = treeObject.getUmlElement();
		
		if (elt != null) {
			IResource r = null;
			URI eUri = elt.eResource().getURI();
			
			if (eUri.isPlatformResource()) {
				String platformString = eUri.toPlatformString(true);
				r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
//				r = ResourcesPlugin.getWorkspace().getRoot().findMember(umlModel.getResource().getURI().toPlatformString(true));
			}
			try {
				
				IMarker marker = r.createMarker(markerType);
				marker.setAttribute(IMarker.MESSAGE, msg);
				if ( criticality.equals("error") ) 	{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);	}
				else 								{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) ; }
				marker.setAttribute(IMarker.SOURCE_ID, sourceID);
				
				if (elt instanceof NamedElement) {
					marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());	
				}
				else{
					marker.setAttribute(IMarker.LOCATION, elt.toString());
				}
				marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());

				return marker;
				
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	public void deleteValidationMarkers(IProject iProject) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
				for (IMarker marker : markers) {
					marker.delete();
				}
			}

		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}

	public void setStringLog(String stringLog) {
		this.stringLog = stringLog;
	}

	public String getStringLog() {
		return stringLog;
	}
	
	
	// Utls *******************************************

	// Getters ****************************************

	
//	// Progress monitor ****************************************
//	
//	// The total sleep time
//	private static final int TOTAL_TIME = 100;
//	// The increment sleep time
//	private static final int INCREMENT = 10;
//	// process time�is unknown
//	private boolean indeterminate = true; 
//	
//	private String progressMonitorTitle = "Value Bindings Operation Validation";
//	private String monitorText1 = "Collecting data ... ";
//	private String monitorText2 = "Validating operations... ";
//	
//	@Override
//	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//		
//		monitor.beginTask(progressMonitorTitle + " is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
//	    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
//	      Thread.sleep(INCREMENT);
//	      monitor.worked(INCREMENT);
//	      if (total == TOTAL_TIME / 10) monitor.subTask(monitorText1);
//	      if (total == TOTAL_TIME / 2) monitor.subTask(monitorText2);
//	    }
//	    monitor.done();
//	    if (monitor.isCanceled()){
//	    	throw new InterruptedException(progressMonitorTitle + " was cancelled.");
//	    }   
//	}
}
