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
package org.openmodelica.modelicaml.helper.handlers;

import java.util.HashMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.gen.modelica.popupactions.GenerateModelicaCodeFromEntireModelicaMLModelAction;
import org.openmodelica.modelicaml.helper.analyzers.SimulationResultsAnalyzer;
import org.openmodelica.modelicaml.helper.dialogs.AnalyseSimulationResultsOptionsDialog;
import org.openmodelica.modelicaml.helper.dialogs.ScenarioBasedVerificationReportDialog;
import org.openmodelica.modelicaml.helper.dialogs.SelectScenarioToReqRelationsToCreateDialog;
import org.openmodelica.modelicaml.helper.simulation.SimulatorOMC;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;

public class AnalyzeSimulationResultsToolbarHandler extends AbstractHandler{

	protected GeneratedModelsData generatedModelsData;
	
	private boolean onlyRecordRequirementStatusAndClients = false;
	private boolean simulate = false;
	private boolean analyzeFiles = false;
	private String resultsFileFolderPath; 
		
	private EObject rootElement;
	
	private String projectPath;
	
	// map of generated models to their simulation results file.
	private HashMap<Element, String> simulationResultFileAbsolutePaths = new HashMap<Element, String>();
	
	// mode indicates which report dialog should be opened. by default this is the automatic scenario-based verification report dialog.
	private int mode = Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION;
	
	private Job analysisJob;
	private JobChangeAdapter analysisJobChangeAdaptor = new JobChangeAdapter() {
		public void done(IJobChangeEvent event) {
            if (event.getResult().isOK()) {
            		openReport();
            	}
            }
         };
	
	
	 protected void openReport(){
		// Use this to open a Shell in the UI thread
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
//				if (getGeneratedModelsData() != null && getGeneratedModelsData().getSimulatedModels().size() > 0) {
				if (getGeneratedModelsData() != null) {
					if (getMode() == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY) {
						ModelicaMLServices.notify("ModelicaML Report Generation", "The report was opened in a separate window.", 0, 2);

						SelectScenarioToReqRelationsToCreateDialog dialog = new SelectScenarioToReqRelationsToCreateDialog(ModelicaMLServices.getShell(), getGeneratedModelsData(), false, rootElement);
						dialog.open();
					}
					else if (getMode() == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
						ModelicaMLServices.notify("ModelicaML Report Generation", "The report was opened in a separate window.", 0, 2);

						ScenarioBasedVerificationReportDialog dialog = new ScenarioBasedVerificationReportDialog(ModelicaMLServices.getShell(), getGeneratedModelsData(), false, rootElement);
			     		dialog.open();
					}
				}
				else {
					DialogMessage dialog = new DialogMessage(ModelicaMLServices.getShell(), "Simulation Errors", null, "Could not analyze simulation results. The report is empty."
							+ "\n\nSimulation Log:\n" + getGeneratedModelsData().getLog(), true);
					dialog.open();
				}
			}
		});
	}

	 
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Element generatedModelsPackage = null;
		// if the generated models data was passed (i.e. a generator ran in advance)
		if (getGeneratedModelsData() != null) {
			generatedModelsPackage = getGeneratedModelsData().getGeneratedModelsPackage();
		}
		
		AnalyseSimulationResultsOptionsDialog dialog = new AnalyseSimulationResultsOptionsDialog(new Shell(), generatedModelsPackage);
		// pass the data object in order to avoid new search of elements
		dialog.setGeneratedModelsData(getGeneratedModelsData());
		dialog.open();
		
		if (dialog.getReturnCode() == IDialogConstants.OK_ID) {
			
			boolean go = setUpProjectData();
			
			if (go) {
				
				setSimulate(dialog.isSimulate());
				setOnlyRecordRequirementStatusAndClients(dialog.isRecordOnlyRequirements());

				setAnalyzeFiles(!dialog.isSimulate());
				setResultsFileFolderPath(dialog.getResultFilesFolderPath());

				generatedModelsData = dialog.getGeneratedModelsData();
				
				rootElement = dialog.getRootElement();
				
				proceed();
			}
			else {
				MessageDialog.openError(new Shell(), "Simulation Results Analysis Error", "Could not access the ModelicaML model. Please open it in editor.");
			}
		}
		return null;
	}

	
	private void proceed(){
		
		/*
		 * simulate models with OMC and analyze files
		 */
		if ( isSimulate() ) {

			/*
			 * Note, the simulator should be created outside a job. 
			 * Otherwise the CORBA connection may take too long ad the job will not wait for it to be established.
			 */
			final SimulatorOMC simulator = new SimulatorOMC(getGeneratedModelsData(), getProjectPath(), isOnlyRecordRequirementStatusAndClients());

			// generate code
			GenerateModelicaCodeFromEntireModelicaMLModelAction cgAction = new GenerateModelicaCodeFromEntireModelicaMLModelAction();
			try {
				
				// generate code
				cgAction.execute(null);
				
				// when finished start the simulation job
				cgAction.getJob().addJobChangeListener(new JobChangeAdapter() {
					public void done(IJobChangeEvent event) {
			            if (event.getResult().isOK()) {
			            	
							ModelicaMLServices.notify("ModelicaML Simulation", "Simulation is running in background. \nIt does not block the editor. \nYou can continue working ...", 0, 1);
							simulator.simulate();
							
							simulator.getSimulationJob().addJobChangeListener(new JobChangeAdapter() {
								public void done(IJobChangeEvent event) {
						            if (event.getResult().isOK()) {
						            	// start simulation job
						            	analysisJob = new Job("Analysing results ...") {
											
											@Override
											protected IStatus run(IProgressMonitor monitor) {
												
												if (monitor.isCanceled()){
													return Status.CANCEL_STATUS;
												}
//												simulationResultsFiles = simulator.getSimulationResultsFile();
//												notSimulatedModels = simulator.getNotSimulatedModels();
												
												generatedModelsData = simulator.getGmd();
												
												// set the results folder path used by simulator
												setResultsFileFolderPath(simulator.getSimulationResultsFolderPath());
												
												SimulationResultsAnalyzer analyzer = new SimulationResultsAnalyzer(generatedModelsData, monitor);
												analyzer.analyze();
												
												return Status.OK_STATUS;
											}
										};
										
										analysisJob.addJobChangeListener(analysisJobChangeAdaptor);
										analysisJob.setUser(true);
										analysisJob.schedule();
						            	}
						            }
						         });
			            	}
			            }
			         });
				
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		 * Analyze files
		 */
//		else if (isAnalyzeFiles() && getResultsFileFolderPath() != null && generatedModelsData != null) {
		else if (isAnalyzeFiles() && getGeneratedModelsData().getSimulationResultsFile().keySet().size() > 0) {
			
//			HashMap<Element, String> simulationResultFiles = new HashMap<Element, String>();
//			for (Element VeM : generatedModelsData.getGeneratedModels()) {
//				String resultFilePath = resultsFileFolderPath + "/" + ModelicaMLServices.getOMCSimulationResultsFileName((NamedElement) VeM);
//				simulationResultFiles.put(VeM, resultFilePath);
//			}
//			generatedModelsData.setSimulationResultsFile(simulationResultFiles);
			
			analysisJob = new Job("Analysing results ...") {
				
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					
					if (monitor.isCanceled()){
						return Status.CANCEL_STATUS;
					}
					
					SimulationResultsAnalyzer analyzer = new SimulationResultsAnalyzer(generatedModelsData, monitor);
					analyzer.analyze();
					
					return Status.OK_STATUS;
				}
			};
			
			analysisJob.addJobChangeListener(analysisJobChangeAdaptor);
			analysisJob.setUser(true);
			analysisJob.schedule();
		}
	}
	
	
	private boolean setUpProjectData(){
		
		// get UML model data
		UmlModel umlModel = UmlUtils.getUmlModel();
//		String umlModelFileURI = umlModel.getResourceURI().toString();

		if (umlModel != null) {
			// get project data
			String projectName = umlModel.getResource().getURI().segment(1);
			
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject iProject = root.getProject(projectName);
			
			setProjectPath(iProject.getLocationURI().toString().replaceFirst("file:\\/", ""));
			
			if (getProjectPath() != null) {
				return true;
			}
		}
		else {
			MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Model Access", "Could not access the ModelicaML model. Please open it in editor.");
		}
		
		return false;
	}
	
	
	
	public Job getAnalysisJob() {
		return analysisJob;
	}

	public void setAnalysisJob(Job analysisJob) {
		this.analysisJob = analysisJob;
	}

	public boolean isSimulate() {
		return simulate;
	}

	public void setSimulate(boolean simulate) {
		this.simulate = simulate;
	}

	public boolean isAnalyzeFiles() {
		return analyzeFiles;
	}

	public void setAnalyzeFiles(boolean analyzeFiles) {
		this.analyzeFiles = analyzeFiles;
	}

	public String getResultsFileFolderPath() {
		return resultsFileFolderPath;
	}

	public void setResultsFileFolderPath(String resultsFileFolderPath) {
		this.resultsFileFolderPath = resultsFileFolderPath;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	
	public GeneratedModelsData getGeneratedModelsData() {
		return generatedModelsData;
	}

	public void setGeneratedModelsData(GeneratedModelsData generatedModelsData) {
		this.generatedModelsData = generatedModelsData;
	}

	public boolean isOnlyRecordRequirementStatusAndClients() {
		return onlyRecordRequirementStatusAndClients;
	}

	public void setOnlyRecordRequirementStatusAndClients(boolean onlyRecordRequirementStatusAndClients) {
		this.onlyRecordRequirementStatusAndClients = onlyRecordRequirementStatusAndClients;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}


	public HashMap<Element, String> getSimulationResultFileAbsolutePaths() {
		return simulationResultFileAbsolutePaths;
	}


	public void setSimulationResultFileAbsolutePaths(
			HashMap<Element, String> simulationResultFileAbsolutePaths) {
		this.simulationResultFileAbsolutePaths = simulationResultFileAbsolutePaths;
	}
	
}
