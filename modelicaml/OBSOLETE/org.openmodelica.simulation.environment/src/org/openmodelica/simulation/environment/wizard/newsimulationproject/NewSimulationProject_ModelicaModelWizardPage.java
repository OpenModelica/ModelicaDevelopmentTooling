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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.simulation.environment.wizard.newsimulationproject;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

// TODO: Auto-generated Javadoc
/**
 * A page to add information about the model and its developer.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 */
public class NewSimulationProject_ModelicaModelWizardPage  extends WizardPage {

	 {
		 //Register as a resource user - SWTResourceManager will
		 //handle the obtaining and disposing of resources
		 //SWTResourceManager.registerResourceUser(this);
	 }
	 
	/** The text version. */
	private Text textVersion;
	
	/** The text author. */
	private Text textAuthor;
	
	/** The text company. */
	private Text textCompany;
	
	/** The styled text comment. */
	StyledText styledTextComment;

	/** The composite attributes. */
	private Composite compositeAttributes;
	
	/** The label comment. */
	private Label labelComment;
	
	/** The label company. */
	private Label labelCompany;
	
	/** The label author. */
	private Label labelAuthor;
	
	/** The label version. */
	private Label labelVersion;

//	private ISelection selection;

	/**
 * Constructor for SampleNewWizardPage.
 *
 */
	public NewSimulationProject_ModelicaModelWizardPage() {
		super("New Modelica Model Wizard");
		setTitle("Create a new Simulation Project");
		setDescription("Give more informatin about the Modelica model");
		
//		this.selection = selection;
	}

	/**
	 * Creates the control.
	 *
	 * @param parent the parent
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.makeColumnsEqualWidth = true;
		container.setLayout(layout);
		createCompositeAttributes(container);
		initialize(container);
		setControl(container);
	}
	
	/**
	 * Creates the composite attributes.
	 *
	 * @param container the container
	 */
	private void createCompositeAttributes(Composite container) {
		compositeAttributes = new Composite(container, SWT.NONE);
		GridLayout compositeAttributesLayout = new GridLayout();
		compositeAttributesLayout.makeColumnsEqualWidth = true;
		compositeAttributesLayout.numColumns = 3;
		GridData compositeAttributesLData = new GridData();
		compositeAttributesLData.grabExcessHorizontalSpace = true;
		compositeAttributesLData.horizontalAlignment = GridData.FILL;
		compositeAttributesLData.verticalAlignment = GridData.FILL;
		compositeAttributes.setLayoutData(compositeAttributesLData);
		compositeAttributes.setLayout(compositeAttributesLayout);
		{
			labelVersion = new Label(compositeAttributes, SWT.NONE);
			labelVersion.setText("Version:");
			GridData labelVersionLData = new GridData();
			labelVersion.setLayoutData(labelVersionLData);
			labelVersion.setBounds(5, 12, 60, 30);
		}
		{
			textVersion = new Text(compositeAttributes, SWT.BORDER | SWT.SINGLE);
			textVersion.setText("1.0");
			GridData textVersionLData = new GridData();
			textVersionLData.horizontalAlignment = GridData.FILL;
			textVersionLData.grabExcessHorizontalSpace = true;
			textVersionLData.horizontalSpan = 2;
			textVersion.setLayoutData(textVersionLData);
			textVersion.setBounds(71, 22, 60, 30);
		}
		{
			labelAuthor = new Label(compositeAttributes, SWT.NONE);
			GridData labelAuthorLData = new GridData();
			labelAuthor.setLayoutData(labelAuthorLData);
			labelAuthor.setText("Author:");
		}
		{
			GridData textAuthorLData = new GridData();
			textAuthorLData.horizontalAlignment = GridData.FILL;
			textAuthorLData.grabExcessHorizontalSpace = true;
			textAuthorLData.horizontalSpan = 2;
			textAuthor = new Text(compositeAttributes, SWT.BORDER | SWT.SINGLE);
			textAuthor.setLayoutData(textAuthorLData);
		}
		{
			labelCompany = new Label(compositeAttributes, SWT.NONE);
			GridData labelCompanyLData = new GridData();
			labelCompany.setLayoutData(labelCompanyLData);
			labelCompany.setText("Company:");
		}
		{
			GridData textCompanyLData = new GridData();
			textCompanyLData.horizontalAlignment = GridData.FILL;
			textCompanyLData.grabExcessHorizontalSpace = true;
			textCompanyLData.horizontalSpan = 2;
			textCompany = new Text(compositeAttributes, SWT.BORDER | SWT.SINGLE);
			textCompany.setLayoutData(textCompanyLData);
		}
		{
			labelComment = new Label(compositeAttributes, SWT.NONE);
			GridData labelCommentLData = new GridData();
			labelCommentLData.horizontalIndent = 1;
			labelCommentLData.horizontalAlignment = GridData.FILL;
			labelCommentLData.grabExcessHorizontalSpace = true;
			labelComment.setLayoutData(labelCommentLData);
			labelComment.setText("Comment:");
		}
		{
			styledTextComment = new StyledText(compositeAttributes, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			GridData styledTextCommentLData = new GridData();
			styledTextCommentLData.horizontalSpan = 2;
			styledTextCommentLData.horizontalAlignment = GridData.FILL;
			styledTextCommentLData.grabExcessHorizontalSpace = true;
			styledTextCommentLData.heightHint = 100;
			styledTextComment.setLayoutData(styledTextCommentLData);
			styledTextComment.setText("no comment yet");
			styledTextComment.setSize(144, 100);
		}
	}

	/**
	 * Initialize.
	 *
	 * @param container the container
	 */
	private void initialize(Composite container) {
		
		
	}

	/**
	 * Gets the model version.
	 *
	 * @return the model version
	 */
	public String getModelVersion() {
		return textVersion.getText();
	}

	/**
	 * Gets the model author.
	 *
	 * @return the model author
	 */
	public String getModelAuthor() {
		return textAuthor.getText();
	}

	/**
	 * Gets the model company.
	 *
	 * @return the model company
	 */
	public String getModelCompany() {
		return textCompany.getText();
	}
	
	/**
	 * Gets the model comment.
	 *
	 * @return the model comment
	 */
	public String getModelComment(){
		return styledTextComment.getText();
	}
}