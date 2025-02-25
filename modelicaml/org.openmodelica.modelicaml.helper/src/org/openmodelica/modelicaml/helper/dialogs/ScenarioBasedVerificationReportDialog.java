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
package org.openmodelica.modelicaml.helper.dialogs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.papyrus.infra.core.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.jfree.data.xy.XYSeries;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.helpers.VerificationServices;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.helper.Activator;
import org.openmodelica.modelicaml.helper.report.OntologyGenerator;
import org.openmodelica.modelicaml.helper.report.XMLReportGenerator;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;
import org.openmodelica.modelicaml.simulation.simresults.ReadMatlab4;
import org.openmodelica.modelicaml.simulation.testexecution.actions.PlotResultsFileAction;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


public class ScenarioBasedVerificationReportDialog extends Dialog {

	private GeneratedModelsData gmd;

	private final ImageDescriptor warningImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	private final ImageDescriptor errorImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/error_ovr.gif");
	private final ImageDescriptor okStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/success_ovr.gif");
	private final ImageDescriptor questionStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/question_ov.gif");
	
	private final static String TAB_TITLE_Violated_Requirements = "Violated";
	private final static String TAB_TITLE_Not_Violated_Requirements = "Not Violated";
	private final static String TAB_TITLE_Not_Evaluated_Requirement =  "Not Evaluated";
	private final static String TAB_TITLE_Not_Implemented_Requirements =  "Not Implemented";
	private final static String TAB_TITLE_Not_Used_Scenarios =  "Not Used";
	private final static String TAB_TITLE_Simulation_Errors =  "Not Simulated";
	
	private TabFolder tabFolder;

	private Tree treeViolatedRequirements;
	private Tree treeNotViolatedRequirement;
	private Tree treeNotEvaluatedRequirements;
	private Tree failedSimulationModelsTree;
	private Button btnNewRelations;
	
	boolean openedFromNewRelationsDialog = false;
	private TabItem tbtmViolatedRequirements;
	private TabItem tbtmNotViolatedRequirements;
	private TabItem tbtmNotEvaluatedRequirements;
	private TabItem tbtmNotSimulated;
	private TabItem tbtmNotImplementedRequirements;
	private TabItem tbtmNotUsedScenarios;

	private Tree treeNotImplementedRequirements;

	private Tree treeNotUsedScenarios;
	
	// map of models and the corresponding simulation result paths (absolute)
	private HashMap<Element,String> simulationResultsFiles = new HashMap<Element, String>();
	private Button btnPlot;
	private Button btnLocate;
//	private Button btnQuickPlot;
	
	private QuickPlotDialog quickPlotDialog = new QuickPlotDialog(getShell());

	// variables that are on the quick plot
	private HashSet<String> quickPlottedVariables = new HashSet<String>();
	
	private EObject rootElement;

	
	public ScenarioBasedVerificationReportDialog(Shell parentShell, 
			GeneratedModelsData gmd, 
			boolean openedFromNewRelationsDialog,
			EObject rootElement) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		
		this.setGmd(gmd);
		
		this.setSimulationResultsFiles(gmd.getSimulationResultsFile());
		this.setOpenedFromNewRelationsDialog(openedFromNewRelationsDialog);
		
		this.rootElement = rootElement;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/automaticScenarioBasedVerification.gif"));
		newShell.setText("Scenario-Based Verification: Report");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
//		setMessage("Note that you can select all children by selecting the parent item.");
//		setTitle("Select relations that should created between scenarios and requirements.");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(7, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1);
		gd_tabFolder.widthHint = 626;
		tabFolder.setLayoutData(gd_tabFolder);

		/*
		 * TAB: Violated requirements 
		 */
		tbtmViolatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmViolatedRequirements.setText(TAB_TITLE_Violated_Requirements);
		if (gmd.getScenariosWithViolatedRequirements().size() > 0) {
			tbtmViolatedRequirements.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		treeViolatedRequirements = new Tree(tabFolder, SWT.NONE);
		treeViolatedRequirements.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				manageQuickPlot();
			}
		});
		treeViolatedRequirements.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setButtonsEnablement();
			}
		});
//		buildTree(treeViolatedRequirements, true, false, false);
		getEvaluatedRequirementItems(treeViolatedRequirements, true, false, false);
		tbtmViolatedRequirements.setControl(treeViolatedRequirements);
		
		/*
		 * TAB: Not violated requirements 
		 */
		tbtmNotViolatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmNotViolatedRequirements.setText(TAB_TITLE_Not_Violated_Requirements);
		if (gmd.getScenariosWithNotViolatedRequirements().size() > 0) {
			tbtmNotViolatedRequirements.setImage(decorateOK(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		treeNotViolatedRequirement = new Tree(tabFolder, SWT.NONE);
		treeNotViolatedRequirement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				manageQuickPlot();
			}
		});
		treeNotViolatedRequirement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setButtonsEnablement();
			}
		});
//		buildTree(treeNotViolatedRequirement, false, true, false);
		getEvaluatedRequirementItems(treeNotViolatedRequirement, false, true, false);
		tbtmNotViolatedRequirements.setControl(treeNotViolatedRequirement);
		
		/*
		 * TAB: Not evaluated requirements 
		 */
		tbtmNotEvaluatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmNotEvaluatedRequirements.setText(TAB_TITLE_Not_Evaluated_Requirement);
		if (gmd.getNotEvaluatedRequirementElements().size() > 0) {
			tbtmNotEvaluatedRequirements.setImage(decorateQuestion(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		treeNotEvaluatedRequirements = new Tree(tabFolder, SWT.NONE);
		treeNotEvaluatedRequirements.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				manageQuickPlot();
			}
		});
		treeNotEvaluatedRequirements.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setButtonsEnablement();
			}
		});
//		buildTree(treeNotEvaluatedRequirements, false, false, true);
		getNotEvaluatedRequirementItems(treeNotEvaluatedRequirements);
		tbtmNotEvaluatedRequirements.setControl(treeNotEvaluatedRequirements);

		
		/*
		 * TAB: Not implemented requirements 
		 */
		tbtmNotImplementedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmNotImplementedRequirements.setText(TAB_TITLE_Not_Implemented_Requirements);
		treeNotImplementedRequirements = new Tree(tabFolder, SWT.NONE);
		treeNotImplementedRequirements.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setButtonsEnablement();
			}
		});
//		buildTree(treeNotEvaluatedRequirements, false, false, true);
		getNotImplementedRequirementItems(treeNotImplementedRequirements);
		tbtmNotImplementedRequirements.setControl(treeNotImplementedRequirements);
		
		/*
		 * TAB: Not used scenarios
		 */
		tbtmNotUsedScenarios = new TabItem(tabFolder, SWT.NONE);
		tbtmNotUsedScenarios.setText(TAB_TITLE_Not_Used_Scenarios);
		treeNotUsedScenarios = new Tree(tabFolder, SWT.NONE);
		treeNotUsedScenarios.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setButtonsEnablement();
			}
		});
//		buildTree(treeNotEvaluatedRequirements, false, false, true);
		getNotUsedScenarioItems(treeNotUsedScenarios);
		tbtmNotUsedScenarios.setControl(treeNotUsedScenarios);
		
		
		/*
		 * TAB: Simulation errors 
		 */
		
		
		tbtmNotSimulated = new TabItem(tabFolder, SWT.NONE);
		tbtmNotSimulated.setText(TAB_TITLE_Simulation_Errors);
		if (gmd.getSimulationFailedList().size() > 0 ) {
			tbtmNotSimulated.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/obj16/error_tsk.gif"));
		}

		failedSimulationModelsTree = new Tree(tabFolder, SWT.NONE);
		failedSimulationModelsTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setButtonsEnablement();
			}
		});
		createNotSimulatedItems(failedSimulationModelsTree);
		tbtmNotSimulated.setControl(failedSimulationModelsTree);
		
		
		
		/*
		 * Open relations dialog
		 */
		btnNewRelations = new Button(container, SWT.NONE);
		btnNewRelations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				SelectScenarioToReqRelationsToCreateDialog dialog = new SelectScenarioToReqRelationsToCreateDialog(new Shell(), gmd, true, rootElement);
				dialog.open();
			}
		});
		btnNewRelations.setText("New Relations");
		btnNewRelations.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "/resources/icons/icons16/correlationset.gif"));
		if (gmd.getNewRelationsCount(true) > 0 || gmd.getNewRelationsCount(false) > 0) {
			btnNewRelations.setEnabled(true);
		}
		else {
			btnNewRelations.setEnabled(false);
		}
		// disable this button if dialog was opened from another dialog that can be opened from this dialog to avoid cycles
		if (isOpenedFromNewRelationsDialog()) {
			btnNewRelations.setEnabled(false);
		}
		
		
		btnLocate = new Button(container, SWT.NONE);
		btnLocate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				
				Tree activeTree = getActiveTree();
				
				if (activeTree != null) {
					TreeItem[] items = activeTree.getSelection();
					for (TreeItem treeItem : items) {
						TreeItemData data = (TreeItemData) treeItem.getData();
						if (data != null && data.getUMLElement() != null) {
							// locate in model explorer
							Element elementToLocate = data.getUMLElement();
							EditorServices.locateInModelExplorer(elementToLocate, true);
						}
					}
				}
				
				TreeObject treeObjectToLocate = null;
				Element VeMToLocate = null;
				
				if (activeTree != null) {
					TreeItem[] items = activeTree.getSelection();
					for (TreeItem treeItem : items) {
						TreeItemData data = (TreeItemData) treeItem.getData();
						if (data != null) {

							// VeM
							if (data.isVeM()) {
								VeMToLocate = data.getUMLElement();
							}
							
							// a property of the VeM to plot
							TreeObject treeObject = data.getTreeObject();
							if (treeObject != null) {
								
								if (data.isClientProperty()) {
									
									treeObjectToLocate = treeObject;
									
									// get the VeM (2 levels up)
									TreeItem VeMItem = treeItem.getParentItem().getParentItem();
									VeMToLocate = ((TreeItemData)VeMItem.getData()).getUMLElement();
									
								}
								else if (data.isProperty()) {

									treeObjectToLocate = treeObject;
									
									// get the VeM (2 levels up)
									TreeItem VeMItem = treeItem.getParentItem();
									VeMToLocate = ((TreeItemData)VeMItem.getData()).getUMLElement();
								}
							}
							
							if (VeMToLocate != null && treeObjectToLocate != null) {
								
								// locate in model explorer
								EditorServices.locateInModelExplorer(VeMToLocate, true);

								// locate in components tree
								EditorServices.locateInComponentsTreeView(treeObjectToLocate.getDotPath());
								
							}
						}
					}
				}
			}
		});
		btnLocate.setText("Locate");
		btnLocate.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png"));
		// disable by default until a tree item was selected
		btnLocate.setEnabled(false);

		btnPlot = new Button(container, SWT.NONE);
		btnPlot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Tree activeTree = getActiveTree();
				
				if (activeTree != null) {
					TreeItem[] items = activeTree.getSelection();
					for (TreeItem treeItem : items) {
						TreeItemData data = (TreeItemData) treeItem.getData();
						if (data != null) {
							String filePath= null;
							String dotPath= null;
							
							// VeM
							if (data.isVeM()) {
								Element VeMElement = data.getUMLElement();
								filePath = getSimulationResultsFiles().get(VeMElement);
							}
							
							// a property of the VeM to plot
							TreeObject treeObject = data.getTreeObject();
							if (treeObject != null) {
								
								if (data.isClientProperty()) {
									// plot client property
									dotPath = treeObject.getDotPath();
									
									// get the VeM (2 levels up)
									TreeItem VeMItem = treeItem.getParentItem().getParentItem();
									Element VeMElement = ((TreeItemData)VeMItem.getData()).getUMLElement();
									filePath = getSimulationResultsFiles().get(VeMElement);
									
								}
								else if (data.isProperty()) {

									// for a requirement plot the status attribute
									if (data.isRequirement()) {
										dotPath = treeObject.getDotPath() + "." + gmd.requirementStatusPropertyName;
									}
									// for other models -> preselect the plot tree
									else {
										dotPath = treeObject.getDotPath();
									}
									
									// get the VeM (1 level up)
									TreeItem VeMItem = treeItem.getParentItem();
									Element VeMElement = ((TreeItemData)VeMItem.getData()).getUMLElement();
									filePath = getSimulationResultsFiles().get(VeMElement);
								}
							}
							
							if (filePath != null) {
								// plot
								PlotResultsFileAction plotAction = new PlotResultsFileAction();
								plotAction.setFilePath(filePath);
								HashSet<String> preselectedVariablesToPlot = new HashSet<String>();
								preselectedVariablesToPlot.add(dotPath);
								if (dotPath != null) {
									plotAction.setPreSelectedVariablesToPlot(preselectedVariablesToPlot);
								}
								plotAction.run(null);
							}
						}
					}
				}
			}
		});
		btnPlot.setText("Plot");
		btnPlot.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "/icons/plot.png"));
		
		// disable until an item is selected
		btnPlot.setEnabled(false);

		
			
//		btnQuickPlot = new Button(container, SWT.TOGGLE);
//		GridData gd_btnQuickPlot = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
//		gd_btnQuickPlot.widthHint = 28;
//		btnQuickPlot.setLayoutData(gd_btnQuickPlot);
//		btnQuickPlot.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				if (btnQuickPlot.getSelection()) {
//					// show quick plot view 
//					quickPlotDialog.open();
//					
//				}
//				else {
//					// hide quick plot view
//					quickPlotDialog.close();
//				}
//			}
//		});
//		btnQuickPlot.setText("");
//		btnQuickPlot.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "/icons/plot.png"));
////		btnQuickPlot.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "/icons/quickassist.gif"));
		
		/*
		 * Save as File 
		 */
		
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		Button btnSaveasfile = new Button(container, SWT.NONE);
		btnSaveasfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ExtendedUmlModel umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
				if (umlModel != null) {
					// get project data
					String projectName = umlModel.getResource().getURI().segment(1);
//					IWorkspace workspace = ResourcesPlugin.getWorkspace();
//					IWorkspaceRoot root = workspace.getRoot();
//					IProject iProject = root.getProject(projectName);
					
					// set folder name and file name
					String folderName = Constants.folderName_automaticScenarioBasedDesignVerificationDiscovery;
					
					// create report
					XMLReportGenerator reportGenerator = new XMLReportGenerator(gmd, rootElement, XMLReportGenerator.XMLContent);
					String filePath = null;
					
					try {
						filePath = reportGenerator.createReport(projectName, folderName, false);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CoreException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					//********************************************************************
					// TODO: find a better place for this invocation 
					// create ontology report
//					String ontologyFilePath = null;
//					OntologyGenerator ontologyGenerator = new OntologyGenerator(gmd, rootElement);
//					try {
//						try {
//							// take the folder where the report.html is: 
//							Path path = new Path(filePath);
////							String strToRemove = path.lastSegment();
//							IPath folderNameOfReportFile = path.removeLastSegments(1);
//							ontologyFilePath = ontologyGenerator.createReport(projectName, folderNameOfReportFile.toOSString() , false);
//							
//						} catch (OWLOntologyCreationException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						} catch (OWLOntologyStorageException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					} catch (URISyntaxException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (CoreException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					//********************************************************************
					
					
					
					if (filePath != null) {
						String message = "The file was stored: \n"+filePath+"";
						DialogMessage dialog = new DialogMessage(new Shell(), "Report Generation", "", message, false);
						dialog.open();
					}

				}
				else {
					MessageDialog.openError(new Shell(), "Report Generation", "Could not access the ModelicaML model in order to determine the project name. Please open a Papyrus model. ");
				}
			}
		});
		btnSaveasfile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnSaveasfile.setText("Create Report");
		btnSaveasfile.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "/icons/report.gif"));
		new Label(container, SWT.NONE);

		return area;
	}

	
	private void setButtonsEnablement(){

		Tree activeTree = getActiveTree();
		
		if (activeTree != null) {
			TreeItem[] items = activeTree.getSelection();
			for (TreeItem treeItem : items) {
				TreeItemData data = (TreeItemData) treeItem.getData();
				if (data != null) {
					
					btnLocate.setEnabled(true);
					
					String filePath= null;
					
					// VeM
					if (data.isVeM()) {
						Element VeMElement = data.getUMLElement();
						filePath = getSimulationResultsFiles().get(VeMElement);
					}
					
					// a property of the VeM to plot
					TreeObject treeObject = data.getTreeObject();
					if (treeObject != null) {
						if (data.isClientProperty()) {
							// get the VeM (2 levels up)
							TreeItem VeMItem = treeItem.getParentItem().getParentItem();
							Element VeMElement = ((TreeItemData)VeMItem.getData()).getUMLElement();
							filePath = getSimulationResultsFiles().get(VeMElement);
							
						}
						else if (data.isProperty()) {
							// get the VeM (1 level up)
							TreeItem VeMItem = treeItem.getParentItem();
							Element VeMElement = ((TreeItemData)VeMItem.getData()).getUMLElement();
							filePath = getSimulationResultsFiles().get(VeMElement);
						}
					}
					
					// enable/disable plot button
					if (filePath != null) {
						btnPlot.setEnabled(true);
					}
					else {
						btnPlot.setEnabled(false);
					}
				}
				else {
					btnLocate.setEnabled(true);
				}
			}
		}
	}
	
	
	private void manageQuickPlot(){

		String filePath= null;
		String dotPath= null;
		String modelName = null;
		
		TreeItem selectedItem = getSelectedItem()[0];
		
		List<String> fileAndVariableData = getFilePathAndDotPath();
		if ( fileAndVariableData != null && fileAndVariableData.size() == 3 ) {
			filePath = fileAndVariableData.get(0);
			dotPath = fileAndVariableData.get(1);
			modelName = fileAndVariableData.get(2);
			
			if (filePath != null && dotPath != null && modelName != null) {
				String key = modelName + " - " + dotPath;
				
				ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(getShell());
				progressMonitorDialog.getProgressMonitor().setTaskName("Reading file: " + filePath);
				progressMonitorDialog.open();

				ReadMatlab4 reader = null;
				try {
					reader = new ReadMatlab4(filePath);
				} catch (Exception e2) {
					MessageDialog.openError(new Shell(), "Plotting Error", "Cannot read the file: "+filePath+"");
				}
				
				XYSeries series = null;

				if (reader != null) {
					double[] time;
					double[] values;
					try {
						time = reader.getTimeValues();
						values = reader.getValues(dotPath);

						series = getSerie(time, values, key);
						
					} catch (Exception e) {
						e.printStackTrace();
						MessageDialog.openError(new Shell(), "Plotting Error", "Cannot read the values for '"+dotPath+"'");
					}
					
					try {
						// close the file
						reader.releaseFile();
					} catch (IOException e) {
						MessageDialog.openError(new Shell(), "Plotting Error", "Could not release the file: '"+filePath+"'");
					}
				}
				
				progressMonitorDialog.close();
				
				// if the values are on plot -> remove series
				if (quickPlottedVariables.contains(key)) {
					quickPlottedVariables.remove(key);

//					getQuickPlotDialog().getShell().setBounds(selectedItem.getBounds().x, selectedItem.getBounds().y, 400, 300);
					if (series != null) {
						getQuickPlotDialog().getPlot().removeValues(series);
						selectedItem.setForeground(new Color(null, new RGB(0,0,0)));
						getQuickPlotDialog().getShell().setFocus();
					}
				}
				
				// plot variable 
				else {
					quickPlottedVariables.add(key);
					if (series != null) {
						getQuickPlotDialog().getPlot().addValues(series);
						selectedItem.setForeground(new Color(null, new RGB(255,0,0)));
						getQuickPlotDialog().getShell().setFocus();
					}
				}
			}
			else {
				if ( (filePath == null && dotPath == null) || (filePath != null && dotPath == null)) {
					MessageDialog.openError(getShell(), "Plotting Error", "You can only plot variables.");
				}
				else if (filePath == null) {
					MessageDialog.openError(getShell(), "Plotting Error", "Cannot open the file '" + filePath + "'");
				}
			}
		}
	}
	
	
	private QuickPlotDialog getQuickPlotDialog(){
		if (quickPlotDialog != null) {
			quickPlotDialog.open();
		}
		else {
			quickPlotDialog = new QuickPlotDialog(getShell());
			quickPlotDialog.open();
			quickPlotDialog.getShell().setFocus();
		}
		return quickPlotDialog;
	}
	
	private XYSeries getSerie(double[] time, double[] values, String key){
		XYSeries series = null;
		series = new XYSeries(key);
		
		if (time != null && values != null) {
			for (int i = 0; i < time.length; i++) {
				double t = time[i];
				series.add(t, values[i]);
			}
		}
		return series;
	}
	
	
	
	
	private TreeItem[] getSelectedItem(){
		Tree activeTree = getActiveTree();
		TreeItem[] items = null;
		if (activeTree != null) {
			items = activeTree.getSelection();
		}
		return items;
	}
	
	private List<String> getFilePathAndDotPath(){
		List<String> fileAndVariableData = new ArrayList<String>();
		
		Tree activeTree = getActiveTree();
		
		if (activeTree != null) {
			TreeItem[] items = activeTree.getSelection();
			for (TreeItem treeItem : items) {
				TreeItemData data = (TreeItemData) treeItem.getData();
				if (data != null) {
					
					String filePath= null;
					String dotPath= null;
					String modelName = null;
					
					// VeM
					if (data.isVeM()) {
						Element VeMElement = data.getUMLElement();
						filePath = getSimulationResultsFiles().get(VeMElement);
					}
					
					// a property of the VeM to plot
					TreeObject treeObject = data.getTreeObject();
					if (treeObject != null) {
						
						if (data.isClientProperty()) {
							// plot client property
							dotPath = treeObject.getDotPath();
							
							// get the VeM (2 levels up)
							TreeItem VeMItem = treeItem.getParentItem().getParentItem();
							Element VeMElement = ((TreeItemData)VeMItem.getData()).getUMLElement();
							modelName = ModelicaMLServices.getName(VeMElement);
							
							filePath = getSimulationResultsFiles().get(VeMElement);
							
						}
						else if (data.isProperty()) {

							// for a requirement plot the status attribute
							if (data.isRequirement()) {
								dotPath = treeObject.getDotPath() + "." + gmd.requirementStatusPropertyName;
							}
							// for other models -> preselect the plot tree
							else {
								dotPath = treeObject.getDotPath();
							}
							
							// get the VeM (1 level up)
							TreeItem VeMItem = treeItem.getParentItem();
							Element VeMElement = ((TreeItemData)VeMItem.getData()).getUMLElement();
							modelName = ModelicaMLServices.getName(VeMElement);
							
							filePath = getSimulationResultsFiles().get(VeMElement);
						}
					}
					
					if (filePath != null) {
						fileAndVariableData.add(filePath);
						
						HashSet<String> preselectedVariablesToPlot = new HashSet<String>();
						preselectedVariablesToPlot.add(dotPath);
						if (dotPath != null) {
							fileAndVariableData.add(dotPath);
						}
						
						if (modelName != null) {
							fileAndVariableData.add(modelName);
						}
					}
				}
			}
		}
		
		return fileAndVariableData;
	}
	
	private Tree getActiveTree() {
		TabItem selectedTab = tabFolder.getSelection()[0];
		if (selectedTab != null) {
			if ( selectedTab.getText().startsWith(TAB_TITLE_Violated_Requirements)) {
				return treeViolatedRequirements;
			}
			else if (selectedTab.getText().startsWith(TAB_TITLE_Not_Violated_Requirements)) {
				return treeNotViolatedRequirement;
			}
			else if (selectedTab.getText().startsWith(TAB_TITLE_Not_Evaluated_Requirement)) {
				return treeNotEvaluatedRequirements;
			}
			else if (selectedTab.getText().startsWith(TAB_TITLE_Simulation_Errors)) {
				return failedSimulationModelsTree;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
//		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
//				true);
//		createButton(parent, IDialogConstants.CANCEL_ID,
//				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(797, 509);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Build tree
	 */
	
	private void getEvaluatedRequirementItems(Tree parent, boolean onlyViolatedRequirements, boolean onlyNotViolatedRequirements, boolean onlyNotEvaluatedRequirements){

		// counter
		int i = 0;
		
		HashSet<Element> evaluatedRequirements = new HashSet<Element>();
		if (onlyViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsViolatedInScenarios();

		}
		else if (onlyNotViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsNotViolatedInScenarios();
		}

		for (Element evaluatedRequirement : ModelicaMLServices.getSortedByRequirementId(evaluatedRequirements)) {
			
			i++ ;
			
			TreeItem item = new TreeItem(parent, SWT.NONE);
			String name = ModelicaMLServices.getRequirementID(evaluatedRequirement) + " " +  ModelicaMLServices.getName(evaluatedRequirement);
			item.setText(name);
			
			item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
			if (onlyViolatedRequirements) {
				item.setImage(decorateError(item.getImage()));
			}
			else if (onlyNotViolatedRequirements) {
				item.setImage(decorateOK(item.getImage()));
			}
			
			TreeItemData data = new TreeItemData();
			data.setRequirementElement(evaluatedRequirement);
			data.setIsRequirement(true);
			item.setData(data);
			
			// loop over all VeM and see if this requirement is in the 
			getVeMForRequirement(item, evaluatedRequirement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
		}
		
		// update the count of the tree tab
		if (onlyViolatedRequirements) {
			tbtmViolatedRequirements.setText(tbtmViolatedRequirements.getText() + " (" + i + " of " + gmd.getAllFoundRequirements().size() + ")");
		}
		else if (onlyNotViolatedRequirements) {
			tbtmNotViolatedRequirements.setText(tbtmNotViolatedRequirements.getText()  + " (" + i + " of " + gmd.getAllFoundRequirements().size() + ")");
		}
	}

	
	private void getNotEvaluatedRequirementItems(Tree parent){
		
		int i = 0;
		boolean showOnlyViolated = false;
		boolean showOnlyNotViolated = false;
		boolean showOnlyNotEvaluated = true;
		
		HashSet<Element> evaluatedRequirements = gmd.getNotEvaluatedRequirementElements();

		for (Element evaluatedRequirement : ModelicaMLServices.getSortedByRequirementId(evaluatedRequirements)) {
			
			i++ ;
			
			TreeItem item = new TreeItem(parent, SWT.NONE);
			String name = ModelicaMLServices.getRequirementID(evaluatedRequirement) + " " +  ModelicaMLServices.getName(evaluatedRequirement);
			item.setText(name);
			
			item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
			item.setImage(decorateQuestion(item.getImage()));
			
			TreeItemData data = new TreeItemData();
			data.setRequirementElement(evaluatedRequirement);
			data.setIsRequirement(true);
			item.setData(data);
			
			// loop over all VeM and see if this requirement is in the 
			getVeMForRequirement(item, evaluatedRequirement, showOnlyViolated, showOnlyNotViolated, showOnlyNotEvaluated);
		}
		
		// update the count of the tree tab
		tbtmNotEvaluatedRequirements.setText(tbtmNotEvaluatedRequirements.getText()  + " (" + i + " of " + gmd.getAllFoundRequirements().size() + ")");

	}
	
	
	private void getNotImplementedRequirementItems(Tree parent){
		int counter = 0;
		
		HashSet<Element> allFoundRequirements = new HashSet<Element>();
		allFoundRequirements.addAll(gmd.getAllFoundRequirements());
		HashSet<Element> allUsedRequirements = new HashSet<Element>();
		allUsedRequirements.addAll(gmd.getAllUsedRequirements());
		
		allFoundRequirements.removeAll(allUsedRequirements);
		
		HashSet<Element> allNotImplementedRequirements = allFoundRequirements;
		if (allNotImplementedRequirements != null && allNotImplementedRequirements.size() > 0) {
			
			for (Element requirement : ModelicaMLServices.getSortedByRequirementId(allNotImplementedRequirements)) {
				
				// counter 
				counter++ ;
				
				TreeItem item = new TreeItem(parent, SWT.NONE);
				String name = ModelicaMLServices.getRequirementID(requirement) + " " +  ModelicaMLServices.getName(requirement);
				item.setText(name);
				
				item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
				item.setImage(decorateQuestion(item.getImage()));
				
				TreeItemData data = new TreeItemData();
				data.setRequirementElement(requirement);
				data.setIsRequirement(true);
				item.setData(data);
			}
			
			// update the count of the tree tab
			tbtmNotImplementedRequirements.setText(tbtmNotImplementedRequirements.getText() + " (" + counter + " of " + gmd.getAllFoundRequirements().size() + ")");
		}
		if (counter > 0) {
			tbtmNotImplementedRequirements.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
		}

	}
	
	
	private String getNotUsedScenarioItems(Tree parent){
		String string = "";
		int counter = 0;

		// NOTE, this does not work because these are pointers so that removeAll manipulates the original set!
//		HashSet<Element> allFoundScenarios = gmd.getAllFoundScenarios();
//		HashSet<Element> allUsedScenarios = gmd.getAllScenarios();
		
		HashSet<Element> allFoundScenarios = new HashSet<Element>(); 
		allFoundScenarios.addAll(gmd.getAllFoundScenarios());
		HashSet<Element> allUsedScenarios = new HashSet<Element>(); 
		allUsedScenarios.addAll(gmd.getAllScenarios());
		
		allFoundScenarios.removeAll(allUsedScenarios);
		
		HashSet<Element> allNotUsedScenarios = allFoundScenarios;
		if (allNotUsedScenarios != null && allNotUsedScenarios.size() > 0) {
			
			for (Element scenario : ModelicaMLServices.getSortedByName(allNotUsedScenarios)) {
				// counter
				counter++;
				
				TreeItem item = new TreeItem(parent, SWT.NONE);
				String name = ModelicaMLServices.getName(scenario);
				item.setText(name);
				
				item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif"));
				item.setImage(decorateQuestion(item.getImage()));
				
				TreeItemData data = new TreeItemData();
				data.setScenarioElement(scenario);
				data.setScenario(true);
				item.setData(data);
			}
		}
		
		// update the count of the tree tab
		tbtmNotUsedScenarios.setText(tbtmNotUsedScenarios.getText()  + " (" + counter + " of " + gmd.getAllFoundScenarios().size() + ")");
		if (counter > 0) {
			tbtmNotUsedScenarios.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif"));
		}
		
		return string;
	}
	
	
	
	private void getVeMForRequirement(TreeItem parent, Element evaluatedRequirement, boolean onlyViolatedRequirements, boolean onlyNotViolatedRequirements, boolean onlyNotEvaluatedRequirements){
		
		for (Element VeM : ModelicaMLServices.getSortedByName(gmd.getGeneratedModels())) {
			HashSet<TreeObject> requirements = gmd.getRequirements(VeM);
			boolean modelContains  = false;
			if (requirements != null) {
				for (TreeObject requirementTreeObject : ModelicaMLServices.getSortedByDotPath(requirements)) {
					Element type = requirementTreeObject.getComponentType();
					if (evaluatedRequirement.equals(type)) {
						
						String statusDotPath = requirementTreeObject.getDotPath().trim() + "." + gmd.requirementStatusPropertyName;
						String key = gmd.getModelToTreeItemKeyString(VeM, statusDotPath);

						boolean isViolated = gmd.getViolatedRequirements().contains(key);
						boolean isEvaluated = gmd.getEvaluatedRequirements().contains(key);
						boolean isNotEvaluated = gmd.getNotEvaluatedRequirements().contains(key);
						
						// if only violated requirements should be shown
						if (onlyViolatedRequirements && isViolated) {
							modelContains = true;
						}
						// if only not violated should be shown
						else if (onlyNotViolatedRequirements && isEvaluated && !isViolated) {
							modelContains = true; 
						}
						// of only not evaluated should be shown
						else if (onlyNotEvaluatedRequirements && isNotEvaluated) {
							modelContains = true; 
						} 
					}
				}
			}
			
			if (modelContains) {
				getVeMItem(parent, VeM, evaluatedRequirement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
			}
		}
	}
	
	
	private void getVeMItem(TreeItem parent, 
			Element model, 
			Element filterForRequirementElement, 
			boolean onlyViolatedRequirements, 
			boolean onlyNotViolatedRequirements, 
			boolean onlyNotEvaluatedRequirements){
		

		TreeItem item = new TreeItem(parent, SWT.NONE);
		String name = ModelicaMLServices.getName(model);
		item.setText(name);
		
		item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif"));
		
		TreeItemData data = new TreeItemData();

		data.setIsVeM(true);
		data.setVeMElement(model);
		item.setData(data);
		
		ClassInstantiation ci = ModelicaMLServices.getModelInstantiation(model, gmd.getPreparedInstantiations(), gmd.getAllFoundMediators(), false);
		
		getSystemModelItems(item, ci);
		
		getScenarioItems(item, ci);
		
		getRequirementItems(item, ci, filterForRequirementElement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
	}
	
	
	private void getSystemModelItems(TreeItem parent, ClassInstantiation ci){

		Element VeM = ci.getSelectedClass();
		for (TreeObject treeObject : ModelicaMLServices.getSortedByDotPath(ci.getAllTreeObjects())) {
			
			Element type = treeObject.getComponentType();
			
			if (gmd.getSystemModels().contains(type)) {
				
				TreeItem item = new TreeItem(parent, SWT.NONE);
				String name = treeObject.getDotPath() + " ("+ModelicaMLServices.getName(type)+")";
				item.setText(name);
				
				item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Property.gif"));
				
				TreeItemData data = new TreeItemData();

				data.setIsProperty(true);
				data.setPropertyElement(treeObject.getUmlElement());
				data.setTreeObject(treeObject);
				item.setData(data);
				
				// Add clients (isMandatory, binding, plot link)
				getClientItems(ci, item, VeM, treeObject);
			}
		}
	}
	
	
	
	private void getScenarioItems(TreeItem parent, ClassInstantiation ci){
		
		Element VeM = ci.getSelectedClass();
		
		if (VeM != null) {
			HashSet<TreeObject> scenarios = gmd.getScenarios(VeM);
			for (TreeObject scenario : ModelicaMLServices.getSortedByDotPath(scenarios)) {
				
				TreeItem item = new TreeItem(parent, SWT.NONE);
				String name = scenario.getDotPath() + " ("+ModelicaMLServices.getName(scenario.getComponentType())+")";
				item.setText(name);
				
				item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Property.gif"));
				
				TreeItemData data = new TreeItemData();

				data.setIsProperty(true);
				data.setPropertyElement(scenario.getUmlElement());
				data.setTreeObject(scenario);
				item.setData(data);

				// Add clients (isMandatory, binding, plot link)
				getClientItems(ci, item, VeM, scenario);
			}
		}
	}
	
	
	private void  getRequirementItems(TreeItem parent,
			ClassInstantiation ci, 
			Element filterForRequirementElement, 
			boolean onlyViolatedRequirements, 
			boolean onlyNotViolatedRequirements,
			boolean onlyNotEvaluatedRequirements){
	
	Element VeM = ci.getSelectedClass();
	HashSet<TreeObject> requirements = gmd.getRequirements(VeM);
	
	for (TreeObject requirement : ModelicaMLServices.getSortedByDotPath(requirements)) {
		
		Element type = requirement.getComponentType();
		String statusDotPath = requirement.getDotPath().trim() + "." + gmd.requirementStatusPropertyName;
		String key = gmd.getModelToTreeItemKeyString(VeM, statusDotPath);

		boolean isViolated = gmd.getViolatedRequirements().contains(key);
		boolean isEvaluated = gmd.getEvaluatedRequirements().contains(key);
		boolean isNotEvaluated = gmd.getNotEvaluatedRequirements().contains(key);
		
		
		String isViolatedString = "";
		if (isViolated) {
			isViolatedString = "!";
		}
		
		String isEvaluatedString = " NOT EVAL.";
		if (isEvaluated) {
			isEvaluatedString = "";
		}
		
		boolean skip = false;
		// if the requirements list should only include requirements with type "filterForRequirementElement"
		if (filterForRequirementElement != null && !type.equals(filterForRequirementElement)) {
			skip = true;
		}
		// if only violated requirements should be shown
		else if (onlyViolatedRequirements && !isViolated) {
			skip = true;
		}
		// if only not violated should be shown
		else if (onlyNotViolatedRequirements && !gmd.getNotViolatedRequirements().contains(key)) {
			skip = true;
		}
		// of only not evaluated should be shown
		else if (onlyNotEvaluatedRequirements && !isNotEvaluated) {
			 skip = true;
		}
		
		if (!skip) {

			TreeItem item = new TreeItem(parent, SWT.NONE);
			String name = isViolatedString + isEvaluatedString + " " + requirement.getDotPath() + " ("+ModelicaMLServices.getName(type)+")";
			item.setText(name);
			
			item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Property.gif"));
			if (isViolated) {
				item.setImage(decorateError(item.getImage()));
				propagateError(item);
			}
			else if (!isViolated && isEvaluated) {
				item.setImage(decorateOK(item.getImage()));
			}
			else if (isNotEvaluated) {
				item.setImage(decorateQuestion(item.getImage()));
			}
			
			TreeItemData data = new TreeItemData();

			data.setIsProperty(true);
			data.setIsRequirement(true);
			
			data.setTreeObject(requirement);
			data.setPropertyElement(requirement.getUmlElement());
			
			item.setData(data);

			// Add clients
			getClientItems(ci, item, VeM, requirement);
			
			}
		}
	}
	
	private void getClientItems(ClassInstantiation ci, TreeItem parent, Element VeM, TreeObject treeObject){
		
		List<TreeObject> actualClients = VerificationServices.getClientsTreeItems(ci, treeObject, new HashSet<TreeObject>(), false);
		
		for (TreeObject client : actualClients) {
			String isMandatory = "";
			if (client.isValueClient_required()) {
				isMandatory = " (!)";
			}
			
			TreeItem item = new TreeItem(parent, SWT.NONE);
			String modification = "";
			if (client.getFinalModificationRightHand() != null) {
				modification = client.getFinalModificationRightHand();
			}
			String name = "Client" + isMandatory  + ": " + client.getDotPath() + " = " + modification;
			item.setText(name);
			
			item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Property.gif"));
			
			TreeItemData data = new TreeItemData();

			data.setIsProperty(true);
			data.setClientProperty(true);
			data.setTreeObject(client);
			data.setPropertyElement(client.getUmlElement());
			item.setData(data);
		}
	}
	
	
	
	private void createNotSimulatedItems(Tree treeRoot){

		int count = 0;
		for (Element modelElement : ModelicaMLServices.getSortedByName(gmd.getGeneratedModels())) {
			if (!gmd.getSimulationResultsFile().keySet().contains(modelElement)) {
				
				count ++; 
				TreeItem failedModelItem = new TreeItem(treeRoot,SWT.NONE);
				
				failedModelItem.setText(getModelName(modelElement) + "  ("+getModelQName(modelElement)+")");
				// decorate with an error overlay
				failedModelItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif")));
				TreeItemData data = new TreeItemData();
				data.setIsVeM(true);
				data.setVeMElement(modelElement);
				data.setUMLElement(modelElement);
				failedModelItem.setData(data);
			}
		}
		tbtmNotSimulated.setText(tbtmNotSimulated.getText() + " (" + count + " of " + gmd.getGeneratedModels().size());
		if (count > 0 ) {
			tbtmNotSimulated.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif")));
		}
	}
	
	

	// Utls ************************************************************************ 

	/*
	 * Propagates 2 levels up
	 */
	private void propagateError(TreeItem requirementItem){
		TreeItem parentItem1 = requirementItem.getParentItem();
		TreeItem parentItem2 = parentItem1.getParentItem();
		
		parentItem1.setImage(decorateError(parentItem1.getImage()));
		parentItem2.setImage(decorateError(parentItem2.getImage()));
	}
	
	
	private String getModelQName(Element model) {
		if (model instanceof NamedElement && ((NamedElement)model).getQualifiedName() != null) {
			return StringUtls.replaceSpecCharExceptThis( ((NamedElement)model).getQualifiedName(), "::").replaceAll("::", ".");
		}
		else {
			return "Qualified name is unknown ...";
		}
	}
	
	private String getModelName(Element model) {
		if (model instanceof NamedElement && ((NamedElement)model).getName() != null) {
			return StringUtls.replaceSpecCharExceptThis( ((NamedElement)model).getName(), "::").replaceAll("::", ".");
		}
		else {
			return "Qualified name is unknown ...";
		}
	}

	
	// Image handling ************************************************************************


	
	public Image decorateWarning(Image image) {
		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateOK(Image image) {
		return new DecorationOverlayIcon(image, okStateImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateQuestion(Image image) {
		return new DecorationOverlayIcon(image, questionStateImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateError(Image image) {
		return new DecorationOverlayIcon(image, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}

	// Data structure ************************************************************************
	
	class TreeItemData{
		
		private boolean isScenario = false;
		private boolean isVeM = false;

		private boolean isRequirement = false;
		private boolean isPackage = false;
		
		private boolean isProperty = false;
		private boolean isClientProperty = false;
		
		private Element UMLElement;
		private TreeObject treeObject;
		
		private Element scenarioElement;
		private Element requirementElement;
		private Element packageElement;
		private Element VeMElement;
		private Element propertyElement;
		
		
		
		public boolean isScenario() {
			return isScenario;
		}
		public void setScenario(boolean isScenario) {
			this.isScenario = isScenario;
		}

		public void setIsPackage(boolean isPackage) {
			this.isPackage = isPackage;
		}
		public boolean isPackage() {
			return isPackage;
		}
		
		public boolean isRequirement() {
			return isRequirement;
		}
		public void setIsRequirement(boolean isRequirement) {
			this.isRequirement = isRequirement;
		}
		public boolean isProperty() {
			return isProperty;
		}
		public void setIsProperty(boolean isProperty) {
			this.isProperty = isProperty;
		}
		public boolean isVeM() {
			return isVeM;
		}
		public void setIsVeM(boolean isModel) {
			this.isVeM = isModel;
		}
		public boolean isClientProperty() {
			return isClientProperty;
		}
		public void setClientProperty(boolean isClientProperty) {
			this.isClientProperty = isClientProperty;
		}
				
		

		public void setPackageElement(Element _packageElement) {
			packageElement = _packageElement;
			setUMLElement(_packageElement);
		}
		public Element getPackageElement() {
			return packageElement;
		}

		public Element getScenarioElement() {
			return scenarioElement;
		}
		public void setScenarioElement(Element scenarioElement) {
			this.scenarioElement = scenarioElement;
			setUMLElement(scenarioElement);
		}
		public Element getRequirementElement() {
			return requirementElement;
		}
		public void setRequirementElement(Element requirementElement) {
			this.requirementElement = requirementElement;
			setUMLElement(requirementElement);
		}

		public Element getVeMElement() {
			return VeMElement;
		}
		public void setVeMElement(Element modelElement) {
			this.VeMElement = modelElement;
			setUMLElement(modelElement);
		}
		public Element getPropertyElement() {
			return propertyElement;
		}
		public void setPropertyElement(Element propertyElement) {
			this.propertyElement = propertyElement;
			setUMLElement(propertyElement);
		}
		public Element getUMLElement() {
			return UMLElement;
		}
		public void setUMLElement(Element uMLElement) {
			UMLElement = uMLElement;
		}
		public TreeObject getTreeObject() {
			return treeObject;
		}
		public void setTreeObject(TreeObject treeObject) {
			this.treeObject = treeObject;
		}

	}
	
	
	
	
	public boolean isOpenedFromNewRelationsDialog() {
		return openedFromNewRelationsDialog;
	}

	public void setOpenedFromNewRelationsDialog(boolean openedFromNewRelationsDialog) {
		this.openedFromNewRelationsDialog = openedFromNewRelationsDialog;
	}

	public HashMap<Element,String> getSimulationResultsFiles() {
		return simulationResultsFiles;
	}

	public void setSimulationResultsFiles(HashMap<Element,String> simulationResultsFiles) {
		this.simulationResultsFiles = simulationResultsFiles;
	}
	
	public GeneratedModelsData getGmd() {
		return gmd;
	}

	public void setGmd(GeneratedModelsData gmd) {
		this.gmd = gmd;
	}

}

