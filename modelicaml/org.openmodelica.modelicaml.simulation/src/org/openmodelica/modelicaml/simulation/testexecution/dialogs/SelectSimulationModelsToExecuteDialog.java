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
package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.eclipse.swt.layout.GridLayout;

public class SelectSimulationModelsToExecuteDialog extends TitleAreaDialog {

	// test scenarios that are appropriate for the system model
	private HashSet<Element> selectedTestSimulationModels;
	
	// the final selection done by user
	private HashSet<Element> userSelectedTestSimulationModels = new HashSet<Element>();
	
//	// the result of the collection
//	private String collectionLog;
	
	/*  All tree items to iterate over when required.
	 *  Note, this should be an ordered list in order to enable a simple run trough when "SelectAll"/"DeselectAll" is clicked 
	 */
	private List<TreeItem> treeItems = new ArrayList<TreeItem>();

//	private static final int DECORATION_WARNING = 0 ;
//	private static final int DECORATION_ERROR = 1 ;
	
	private final ImageDescriptor warningImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	private final ImageDescriptor errorImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/error_ovr.gif");

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectSimulationModelsToExecuteDialog(Shell parentShell,
			HashSet<Element> selectedTestSimulationModels) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		
		this.selectedTestSimulationModels = selectedTestSimulationModels;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
//		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/execute.png"));
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/tscriptrun.gif"));
		newShell.setText("Simulation Models Selection");
	}
	
	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
//		setTitleImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/tscriptrun.gif"));
		setMessage("Note: you can select all models that are in a package by selecting the package.");
//		setTitle("Test Scenarios and Requirements Selection");
		setTitle("Select simulation models that should be executed.");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_tabFolder.widthHint = 626;
		tabFolder.setLayoutData(gd_tabFolder);

		// TAB: Pre-selected test scenarios
		TabItem tbtmPreSelectedTestSimulationModels = new TabItem(tabFolder, SWT.NONE);
//		tbtmTestScenarios.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/elcl16/close_view.gif"));
		String metricPreSelected = "("+selectedTestSimulationModels.size() + ")";
		tbtmPreSelectedTestSimulationModels.setText("Preselected Scenarios " + metricPreSelected);
		
		final Tree treePreSelectedTestSimulationModels = new Tree(tabFolder, SWT.CHECK);
		buildTree(treePreSelectedTestSimulationModels);
		
		// Add listeners
		treePreSelectedTestSimulationModels.addSelectionListener(new CheckboxTreeSelectionListener());
//		treePreSelectedTestSimulationModels.addListener(SWT.MouseDoubleClick, new Listener() {
//		      public void handleEvent(Event event) {
//		        Point point = new Point(event.x, event.y);
//		        TreeItem item = treePreSelectedTestSimulationModels.getItem(point);
//		        if (item != null) {
//		        	openDescription(item);
//		        }
//		      }
//		    });
	
		tbtmPreSelectedTestSimulationModels.setControl(treePreSelectedTestSimulationModels);
		
		Button btnSelectAll = new Button(container, SWT.NONE);
		btnSelectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem treeItem : treeItems) {
					treeItem.setChecked(true);
					// add to maps.
					TreeItemData data = (TreeItemData) treeItem.getData();
					if (data.isTestSimulationModel) {
						userSelectedTestSimulationModels.add(data.getTestSimulationModelElement());
					}
				}
			}
		});
		btnSelectAll.setText("Select All");

		Button btnDeselectAll = new Button(container, SWT.NONE);
		btnDeselectAll.setEnabled(true);
		btnDeselectAll.setText("Deselect All");
		btnDeselectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem treeItem : treeItems) {
					
					treeItem.setChecked(false);
					// remove
					TreeItemData data = (TreeItemData) treeItem.getData();
					if (data.isTestSimulationModel) {
						userSelectedTestSimulationModels.remove(data.getTestSimulationModelElement());
					}
				}
			}
		});
		
//		Button btnSeeLog = new Button(container, SWT.NONE);
//		btnSeeLog.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseDown(MouseEvent e) {
//				String infoText = "Data Collection for Simulation Models Generation Log: ";
//				DialogMessage dialog = new DialogMessage(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", infoText, collectionLog);
//				dialog.open();
//			}
//		});
//		btnSeeLog.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/log.png"));
//		btnSeeLog.setBounds(558, 331, 68, 23);
//		btnSeeLog.setText("Log");
		
		Button btnRestore = new Button(container, SWT.NONE);
		btnRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				treePreSelectedTestSimulationModels.removeAll();
				clearAllLists();
				buildTree(treePreSelectedTestSimulationModels);
			}
		});
		btnRestore.setImage(ResourceManager.getPluginImage("org.eclipse.emf.common.ui", "/org/eclipse/emf/common/ui/Restore.gif"));
		btnRestore.setText("Restore");

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(642, 509);
	}

	
	private void buildTree(Tree treeRoot){
		
		HashSet<Element> testSimulationModels = new HashSet<Element>();
		if (this.selectedTestSimulationModels!= null ) {
			testSimulationModels.addAll(this.selectedTestSimulationModels);
		}
		
		List<Element> packagesOfTheSelectedTestScenariosSorted = ModelicaMLServices.getSortedByName(getTestSimulationModelsPackages(testSimulationModels));
		createPkgTreeItems(treeRoot, packagesOfTheSelectedTestScenariosSorted, true);
		
		List<Element> testSimulationModelsSorted = ModelicaMLServices.getSortedByName(testSimulationModels);
		for (Element testSimulationModel : testSimulationModelsSorted) {
			if (testSimulationModel instanceof NamedElement) {
				createTestSimulationModelTreeItem(treeRoot, testSimulationModel);
				// add to the initial pre-selection
				userSelectedTestSimulationModels.add(testSimulationModel);
			}
		}
	}

	private HashSet<Element> getTestSimulationModelsPackages(HashSet<Element> testSimulationModels){
		HashSet<Element> pkgList = new HashSet<Element>();
		if (testSimulationModels != null ) {
			for (Element testScenario : testSimulationModels) {
				pkgList.add(testScenario.getOwner());
			}
		}
		return pkgList;
	}
	
	private void createPkgTreeItems(Tree treeRoot, List<Element> pkgElements, boolean isPreSelected){
		// create package nodes at the 1 level
		for (Element pkg : pkgElements) {

			TreeItem pkgItem = new TreeItem(treeRoot, 0);
			treeItems.add(pkgItem);
			
			TreeItemData data = new TreeItemData();
			data.setIsPackage(true);
			data.setPackageElement(pkg);

			pkgItem.setData(data);
			
			pkgItem.setText(((NamedElement)pkg).getName() + "  ("+((NamedElement)pkg).getQualifiedName()+")");
			pkgItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif"));
			
			// preselect
			pkgItem.setChecked(isPreSelected);
		}
	}
	
	private TreeItem getPackageTreeItem(Tree treeRoot, Element testSimulationModel){
		
		TreeItem pkgItem = null;
		// find the right package node at the 1 tree-level.
		TreeItem[] pkgItems = treeRoot.getItems();
		for (TreeItem treeItem : pkgItems) {
			TreeItemData data = (TreeItemData) treeItem.getData();
			if (data.isPackage && !data.isDiscarded && data.getPackageElement().equals(testSimulationModel.getOwner())) {
				return treeItem; 
			}
		}
		return pkgItem;
	}
	
	private void createTestSimulationModelTreeItem(Tree treeRoot, Element testSimulationModel){

		TreeItem pkgItem = getPackageTreeItem(treeRoot, testSimulationModel);
		TreeItem testSimulationModelItem = null;
		if (pkgItem != null) {
			testSimulationModelItem = new TreeItem(pkgItem, 0);	
		}
		else {
			testSimulationModelItem = new TreeItem(treeRoot, 0);
		}
		
		treeItems.add(testSimulationModelItem);
		
		TreeItemData data = new TreeItemData();
		data.setIsTestSimulationModel(true);
		data.setTestSimulationModelElement(testSimulationModel);
		
		testSimulationModelItem.setData(data);
		
		testSimulationModelItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
		testSimulationModelItem.setChecked(true);

		testSimulationModelItem.setText(((NamedElement)testSimulationModel).getName());
		
	}
	
	
	// Utls ************************************************************************ 

	private void clearAllLists(){
		userSelectedTestSimulationModels.clear();
		treeItems.clear();
	}
	
	
	// Image handling ************************************************************************

//	private void propagateDecoration(TreeItem item, int decoration){
//		Image image = item.getImage();
//		if (image != null) {
//			if (decoration == DECORATION_ERROR) {
//				item.setImage(decorateError(image));
//			}
//			else if (decoration == DECORATION_WARNING) {
//				item.setImage(decorateError(image));
//			}
//		}
//		
//		if (item.getParentItem() != null) {
//			propagateDecoration(item.getParentItem(), decoration);
//		}
//	}

	public Image decorateWarning(Image image) {
		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateError(Image image) {
		return new DecorationOverlayIcon(image, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}

	
	// Listener ************************************************************************
	
	class CheckboxTreeSelectionListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			if (event.detail == SWT.CHECK && event.item instanceof TreeItem) {
				
				// requirement item
				if (event.item.getData() instanceof TreeItemData) {
					TreeItemData data = (TreeItemData) event.item.getData(); 
					
					// test scenario item.
					if (data.isTestSimulationModel) {
						Element tesSimulationModel = data.getTestSimulationModelElement();
						
						if ( ((TreeItem)event.item).getChecked() ) {
//							System.err.println("ADD test scenario to map.");
							userSelectedTestSimulationModels.add(tesSimulationModel);
						}
						else {
							userSelectedTestSimulationModels.remove(tesSimulationModel);
//							System.err.println("remove test scenario from map");	
						}
					}
					// package item
					else if (data.isPackage) {
						// Check or uncheck all test simulation models underneath
						if ( ((TreeItem)event.item).getChecked() ) {
							TreeItem[] testSimulationModelsItems = ((TreeItem)event.item).getItems();
							for (int i = 0; i < testSimulationModelsItems.length; i++) {
								TreeItem testScenarioItem = testSimulationModelsItems[i];
								testScenarioItem.setChecked(true);
								TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
								userSelectedTestSimulationModels.add(tsData.getTestSimulationModelElement());
							}
						}
						else {
							TreeItem[] testScenariosItems = ((TreeItem)event.item).getItems();
							for (int i = 0; i < testScenariosItems.length; i++) {
								TreeItem testScenarioItem = testScenariosItems[i];
								testScenarioItem.setChecked(false);
								TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
								userSelectedTestSimulationModels.remove(tsData.getTestSimulationModelElement());
							}
						}
					}
				}
			}
		}
	}
	
	// Data structure ************************************************************************
	
	class TreeItemData{
		
		private boolean isTestSimulationModel = false;
		private boolean isPackage = false;
		
		private boolean isDiscarded = false;
		private Element TestSimulationModelElement;
		private Element PackageElement;
		
		
		public void setIsTestSimulationModel(boolean isTestScenario) {
			this.isTestSimulationModel = isTestScenario;
		}
		public boolean isTestSimulationModels() {
			return isTestSimulationModel;
		}
		public void setIsPackage(boolean isPackage) {
			this.isPackage = isPackage;
		}
		public boolean isPackage() {
			return isPackage;
		}
		public void setTestSimulationModelElement(Element testScenarioElement) {
			TestSimulationModelElement = testScenarioElement;
		}
		public Element getTestSimulationModelElement() {
			return TestSimulationModelElement;
		}
		public void setPackageElement(Element packageElement) {
			PackageElement = packageElement;
		}
		public Element getPackageElement() {
			return PackageElement;
		}
	}
	
	// GETTER/SETTER ************************************************************************
	public HashSet<Element> getUserSelectedTestSimulationModels() {
		return userSelectedTestSimulationModels;
	}
	
}

