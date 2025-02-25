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
package org.openmodelica.modelicaml.helper.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.helper.datacollection.VerificationScenariosCollector;
import org.openmodelica.modelicaml.helper.generators.CreatorValueBinding;

public class VeMScenarioReqCombinationsCreator {
	
	/* 
	 * Package were to search for value mediators  
	 */
	private Package valueMediatorsPackage;
	
	
	/* 
	 * Selected system model to generate a simulation model.
	 * The generated simulation model will include one verification scenario, 
	 * all requirements that can be verified using this verification scenario,
	 * all models that are required in addition. 
	 */
	private Element systemModel;


	/*
	 * All required models that are referenced (directly or indirectly) by the selected system model 
	 */
	private HashSet<Element> requiredModels_systemModel = new HashSet<Element>();

	/* 
	 * verification scenario that is used to stimulate the system model
	 */
	private Element scenario;



	/*
	 * All required models that are referenced (directly or indirectly) by the verification scenario model 
	 */
	private HashSet<Element> requiredModels_scenario = new HashSet<Element>();
	
	/*
	 * A set of requirements to be verified
	 */
	private HashSet<Element> requirements = new HashSet<Element>();

	/*
	 * All models that are required in addition based on the given set of requirements to be verified.
	 */
	private HashMap<Element, HashSet<Element>> requiredModels_requirements = new HashMap<Element, HashSet<Element>>();

	// log messages
	private String log = "";

	// Set of models that constitutes the combination to be analyzed
	private HashSet<Element> initialSetOfModels = new HashSet<Element>();

	// All models that was found by required additional models search
	private HashSet<Element> allCollectedAdditionalModels = new HashSet<Element>();

	// Models that are found by the additional models search and that should always be instantiated.
	private HashSet<Element> alwaysInclude = new HashSet<Element>();

	//Any model that was considered for the required models search
	private HashSet<Element> alreadyConsideredForAdditionalModelsSearch = new HashSet<Element>();
	
	// all models that were found in the defined root model/package that should always be instantiated
	private HashSet<Element> allAlwaysIncludeFound = new HashSet<Element>();
	// all models that were found in the defined root model/package that have models that are required in addition
	private HashMap<Element, HashSet<Element>> allModelsAndTheirRequiredModelsFound = new HashMap<Element, HashSet<Element>>();
	
	// All instantiated models
	private HashSet<TreeParent> allModelInstantiations = new HashSet<TreeParent>();

	// All models and their instantiation graphs
	private HashMap<Element, ClassInstantiation> modelToItsInstantiation = new HashMap<Element, ClassInstantiation>();
	
	// Virtual instantiation root. Its direct children are the instantiation roots of all models provided and collected. 
	private TreeParent virtualInstantiationTreeRoot;

	// contains tree objects (instantiation roots of models) with all mandatory clients that are NOT satisfied.
	private HashMap<TreeParent, HashSet<TreeObject>> mandatoryClients_unsatisfied = new HashMap<TreeParent, HashSet<TreeObject>>();

	// models and their clients.
	private HashMap<TreeParent, HashSet<TreeObject>>  modelClients = new HashMap<TreeParent, HashSet<TreeObject>> ();
	
	
	// collector of verification data (scenarios, requirements etc.)
	private VerificationScenariosCollector collector;


	HashMap<Element, ClassInstantiation> preparedModelsToInstantiations = new HashMap<Element, ClassInstantiation>();
	
	/*
	 *  Indicates if this combination is discarded.
	 *  The combination should be discarded if not all mandatory clients of the system model are satisfied
	 *  or if none of the verification scenario providers is used in the virtual instantiation.
	 */
	private boolean isDiscarded = false;

	/*
	 *  Indicates that an error occurred during analysis
	 */
	private boolean isError = false;
	
	public VeMScenarioReqCombinationsCreator(Class systemModel, 
			Class scenario, 
			HashSet<Element> requirements,
			Package valueMediatorsPackage,
			HashSet<Element> allAlwaysIncludeFound,
			HashMap<Element, HashSet<Element>> allModelsAndTheirRequiredModelsFound,
			HashMap<Element, ClassInstantiation> preparedModelsToInstantiations,
			VerificationScenariosCollector collector){
		
		this.collector = collector;
		
		this.systemModel = systemModel;
		this.scenario = scenario;
		this.requirements = requirements;
		this.valueMediatorsPackage = valueMediatorsPackage;
		
		this.allAlwaysIncludeFound.addAll(allAlwaysIncludeFound);
		this.allModelsAndTheirRequiredModelsFound.putAll(allModelsAndTheirRequiredModelsFound);
		
		// initial information for the log
		initializeLog();
		
		this.preparedModelsToInstantiations = preparedModelsToInstantiations;
		// use the pre-instantiated models in order to avoid instantiating models multiple times
		if (preparedModelsToInstantiations != null) {
			getModelToItsInstantiation().putAll(preparedModelsToInstantiations);
		}
		
		/*
		 *  Initial set of models provided.
		 *  This is used to indicate that these models should not be considered as additional models.   
		 */
		initialSetOfModels.add(systemModel);
		initialSetOfModels.add(scenario);
		initialSetOfModels.addAll(requirements);
		
		// collect all models that are required in addition (deep search)
		requiredModels_systemModel.addAll(findAdditionModels(systemModel));
		requiredModels_scenario.addAll(findAdditionModels(scenario));
		
		for (Element requirement : requirements) {
			requiredModels_requirements.put(requirement, findAdditionModels(requirement));
		}

		// virtual instantiation root 
		virtualInstantiationTreeRoot = new TreeParent("Virtual Instantiation", null, null, "", false, true, null, null, true);
		
		// instantiate all models in order to enable checks
		HashSet<Element> allModels = new HashSet<Element>();
		allModels.addAll(initialSetOfModels);
		allModels.addAll(allCollectedAdditionalModels);
		
		instantiateAll(allModels);
		
		// validate this combination (checks only the system model and the verification scenario)
		validateCombinationScenarioAndSystem();
		
		// validate requirements 
		validateRequirementsConsistency();
	}
	
	
	private HashSet<Element> findAdditionModels(Element sourceElement){
		
		HashSet<Element> collectedAdditionalModels = new HashSet<Element>();
		
		// if this model was not yet considered
		if (!alreadyConsideredForAdditionalModelsSearch.contains(sourceElement) && sourceElement instanceof Class) {
			
			TreeParent sourceElementInstantiated;
			/*
			 * First look if the graph for this model was already created (i.e. if there is an instantiation available for that model)
			 * If not -> create a new instantiation
			 */
			if ( modelToItsInstantiation.get(sourceElement) != null) {
				sourceElementInstantiated = getModelToItsInstantiation().get(sourceElement).getTreeRoot();
			}
			else {
				// TODO: check there if no issue if we do not create predefined type properties for each primitive variable
				ClassInstantiation ci_model = new ClassInstantiation((Class) sourceElement, true, false, collector.getAllMediators(), false);
//				ci_model.setAllMediators(collector.getAllMediators());
				ci_model.createTree();
				ci_model.collectBindingsDataFromUmlModel();
				sourceElementInstantiated = ci_model.getTreeRoot();
				
				// add to model -> its instantiation map
				modelToItsInstantiation.put(sourceElement, ci_model);
			}
			
			//Instantiate the source model in order to collect all classes that are used in its instance tree
//			ClassInstantiation ci_sourceModel = new ClassInstantiation((Class) sourceElement, true);	
			HashSet<Element> allModelsInInstantiationTree = new HashSet<Element>();
//			ci_sourceModel.createTree();
			
			allModelsInInstantiationTree.addAll(getAllTreeItemsClasses(sourceElementInstantiated));
			
			// mark all classes collected from the instantiation tree in order to avoid dead lock search
			alreadyConsideredForAdditionalModelsSearch.addAll(allModelsInInstantiationTree);
			
			// find additional models that are referenced directly by one of the class found in the instantiation tree
			for (Element element : allModelsInInstantiationTree) {
				if (element instanceof NamedElement) {

					HashSet<Element> requiredModels = this.allModelsAndTheirRequiredModelsFound.get(element);
					if (requiredModels != null) {
						for (Element requiredModelFound : requiredModels) {
							
							// check if this model should be discarded.
							if (initialSetOfModels.contains(requiredModelFound)) {
									if (requiredModelFound instanceof NamedElement) {
									
									String message = "DISCARDED (01): " +
											"The additional model search found '"+((NamedElement)requiredModelFound).getQualifiedName()
											+ "'\n referenced by '"+((NamedElement)element).getQualifiedName()+"'.\n"
											+ "This model was already found once. This reference is discarded.";
									
									addToLog(message);
								}
							}
							else {
								// add to the collected additional models 
								collectedAdditionalModels.add( requiredModelFound );
								
								// add to the overall list of collected additional models
								allCollectedAdditionalModels.add( requiredModelFound );
								
								if (allAlwaysIncludeFound.contains( requiredModelFound )) {
									alwaysInclude.add( requiredModelFound );
								}
							}
						}
					}
				}
			}
			
			// recursive call -> iterate over all collected models and find other models that are required in addition
			for (Element newSourceModel : collectedAdditionalModels) {
				if (newSourceModel instanceof Class) {
					collectedAdditionalModels.addAll(findAdditionModels((Class) newSourceModel));	
				}
			}
		}
		
		return collectedAdditionalModels;
	}
	
	private void instantiateAll(HashSet<Element> models){

		// remove all instantiations from the root
		for (TreeParent instantiatedModel : allModelInstantiations) {
			virtualInstantiationTreeRoot.removeChild(instantiatedModel);
		}

		// new instantiations
		for (Element model : models) {
			if (model instanceof Class) {
				
				TreeParent newChild = null;
				
				/*
				 * First look if the graph for this model was already craeted (i.e. if there is an instantiation available for that model)
				 * If not -> create a new instantiation
				 */
				if ( modelToItsInstantiation.get(model) != null) {
					newChild = getModelToItsInstantiation().get(model).getTreeRoot();
				}
				else {
					ClassInstantiation ci_model = new ClassInstantiation((Class) model, true, false, collector.getAllMediators(), false);
//					ci_model.setAllMediators(collector.getAllMediators());
					ci_model.createTree();
					ci_model.collectBindingsDataFromUmlModel();
					
					newChild = ci_model.getTreeRoot();
					
					// add to model -> its instantiation map
					modelToItsInstantiation.put(model, ci_model);
				}
				
				// add the instantiated model to the root
				virtualInstantiationTreeRoot.addChild(newChild);
				
				// add  the instantiation object to the map. 
				allModelInstantiations.add(newChild);
				
			}
		}
	}
	
	private void validateCombinationScenarioAndSystem(){
//		System.err.println("Starting the validation of combination of scenario and system model combination...");

		// Note, isDiscarded is false by default. There is no need to set it to false.
		TreeParent systemModelInstantiationTreeRoot = modelToItsInstantiation.get(this.systemModel).getTreeRoot();
		TreeParent scenarioInstantiationTreeRoot = modelToItsInstantiation.get(this.scenario).getTreeRoot();
		
		if (systemModelInstantiationTreeRoot != null && scenarioInstantiationTreeRoot != null) {
			
			if (!areAllMandatoryClientsSatisfied(virtualInstantiationTreeRoot, systemModelInstantiationTreeRoot)) {

				// discard this combination
				setDiscarded(true);
				
				String message = "DISCARDED(02): The combination " +
						"\n   - Scenario: '" + ((NamedElement)this.scenario).getQualifiedName() + "'" +
						"\n   - System Model: '" + ((NamedElement)this.systemModel).getQualifiedName() + "'" +
						"\nis discarded because the following mandatory clients of the system model are not satisfied: " +
						"\n" + getClientsDotPathAsString(mandatoryClients_unsatisfied.get(systemModelInstantiationTreeRoot));
				addToLog(message);
			}
			/*
			 * TODO: What if all clients of the system model are satisfied by additional models, which in 
			 * turn are satisfied by scenarios? Then the code below would discard this combination.
			 * Should we consider this case?
			 */
			if (!isAtLeastOneProviderUsed(virtualInstantiationTreeRoot, scenarioInstantiationTreeRoot)) {
				
				// discard this combination
				setDiscarded(true);
				
				String message = "DISCARDED(03): The combination " +
						"\n   - Scenario: '" + ((NamedElement)this.scenario).getQualifiedName() + "'" +
						"\n   - System Model: '" + ((NamedElement)this.systemModel).getQualifiedName() + "'" +
						"\nis discarded because none of the scenario providers is used to stimulate the model. ";
				addToLog(message);
			}
		}
		else {

			String message = "NOT VALID(04): The system model or the scenario are not found.";
			addToLog(message);
			
			setDiscarded(true);
			setError(true);
		}
	
//		System.err.println("Completed the validation of combination of scenario and system model combination.");
	}
	
	
	private void validateRequirementsConsistency(){
//		System.err.println("Starting the validation of system model, scenario and requirements.");
		
		for (Element requirement : requirements) {
			
			TreeParent requirementInstantiationTreeRootItem = modelToItsInstantiation.get(requirement).getTreeRoot();
			
			if (requirementInstantiationTreeRootItem != null) {

				// this method validates and collects the unsatisfied mandatory clients at the same time.
				areAllMandatoryClientsSatisfied(virtualInstantiationTreeRoot, requirementInstantiationTreeRootItem);
				
				/*
				 * check if if requirement has clients at all and all mandatory are satisfied
				 */
				
				// add unsatisfied mandatory clients to log
				HashSet<TreeObject> unsatisfiedClients = getUnsatisfiedMandatoryClients(requirement); 
				if (unsatisfiedClients != null) {
					String message = "PROBLEM(05): Requirement " 
							+ ModelicaMLServices.getRequirementID(requirement) + ": " 
							+ ModelicaMLServices.getName(requirement) 
							+ " (" + ((NamedElement)requirement).getQualifiedName() + ")" +
					"\n has the following mandatory clients which are not satisfied: " +
					"\n" +getClientsDotPathAsString(unsatisfiedClients);
					
					addToLog(message);
				}
				else if (getModelClients(requirement) == null) {
					String message = "PROBLEM(05.1): Requirement " 
							+ ModelicaMLServices.getRequirementID(requirement) + ": " 
							+ ModelicaMLServices.getName(requirement) 
							+ " (" + ((NamedElement)requirement).getQualifiedName() + ")" +
					"\n has no clients at all.";
					
					addToLog(message);
				}
			}
		}
		
//		System.err.println("Completed the validation of system model, scenario and requirements.");
	}
	
	
	private boolean isAtLeastOneProviderUsed(TreeParent virtualInstantiationTreeRoot, TreeParent treeParentToStartTheCheckOn){
		
		boolean atLeastOneProviderIsUsed = false;
		TreeObject[] children = virtualInstantiationTreeRoot.getChildren();
		
		for (int i = 0; i < children.length; i++) {
			TreeObject modelInstnatiationTreeRootItem = children[i];

			// discard itself
			if (modelInstnatiationTreeRootItem != treeParentToStartTheCheckOn && modelInstnatiationTreeRootItem instanceof TreeParent) {

				CreatorValueBinding vbc = new CreatorValueBinding();
				// pass the pre-collected mediators in order to avoid searching once again 
				vbc.setAllMediators(collector.getAllMediators()); 

				/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
				 * so that no modifications are created in components because we only want to analyze possible bindings.
				 */
				vbc.updateAllBindings(valueMediatorsPackage, null, (TreeParent) modelInstnatiationTreeRootItem, virtualInstantiationTreeRoot, false, true, false, true);

				// check if at least one of the providers is in the subtree
				atLeastOneProviderIsUsed = treeContainsOneOf(vbc.getUsedProviders(), treeParentToStartTheCheckOn);

				// stop the check as soon as at least one used provider was found.
				if (atLeastOneProviderIsUsed) {
					return true;
				}
			}
		}
		
		return atLeastOneProviderIsUsed;
	}
	
	
	private boolean areAllMandatoryClientsSatisfied(TreeParent virtualInstantiationTreeRoot, TreeParent treeParentToStartTheCheckOn){
		
		/* 
		 * Get the list of clients for which the code could be derived 
		 * (even if a user interaction would be necessary) 
		*/
		CreatorValueBinding vbc = new CreatorValueBinding();
		
		// pass the pre-collected mediators in oder to avoid searching once again 
		vbc.setAllMediators(collector.getAllMediators()); 
		
		
		boolean allMandatoryClientsAreSatisfied = false;
		
		/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
		 * so that no modifications are created in components because we only want to analyze possible bindings.
		 */
		// TODO: check the performance 
		vbc.updateAllBindings(valueMediatorsPackage, null, treeParentToStartTheCheckOn, virtualInstantiationTreeRoot, false, true, false, true);
		
		/*
		 * If there are no clients at all: This case is considered 
		 * being not a valid combination.
		 */
		HashSet<TreeObject> allClients = vbc.getAllClientsFound();  
		
		if (allClients == null || allClients.size() == 0) {
			return false;
		}
		/*
		 * Collect all models that have clients in order to be able to
		 * determine if, for example, a requirement should be discarded because it has no clients at all.  
		 */
		else if (allClients != null){
			modelClients.put(treeParentToStartTheCheckOn, allClients);
		}
		

		// check if all mandatory clients are satisfied
		if ( vbc.getAllMandatoryClientsFound().size() > 0 
				&& vbc.getAllClientsWithPossibleBindingCodeDerivation().containsAll(vbc.getAllMandatoryClientsFound())) {
			allMandatoryClientsAreSatisfied = true;
		}
		else if (vbc.getAllClientsFound().size() > 0) {
			/*
			 * If there are optional clients then we conclude that all mandatory are satisfied.
			 * After that we will check of at least one client is used from the scenario. 
			 * This is used in order to avoid rejecting of combinations in which 
			 * the system model has only optional clients. 
			 */
			allMandatoryClientsAreSatisfied = true;
		}
		
		// Collect all mandatory clients that are not satisfied.
		HashSet<TreeObject> unsatisfiedRequiredClients = new HashSet<TreeObject>();
		unsatisfiedRequiredClients.addAll(vbc.getAllMandatoryClientsFound());
		unsatisfiedRequiredClients.removeAll(vbc.getAllClientsWithPossibleBindingCodeDerivation());

		if (unsatisfiedRequiredClients.size() > 0 ) {
			mandatoryClients_unsatisfied.put(treeParentToStartTheCheckOn, unsatisfiedRequiredClients);
		}
		
		if (!allMandatoryClientsAreSatisfied) {
			System.err.println("HIER");
		}
		
		return allMandatoryClientsAreSatisfied;
	}
	
	
//	private boolean hasClients(TreeParent virtualInstantiationTreeRoot, TreeParent treeParentToStartTheCheckOn){
//		
//		/* 
//		 * Get the list of clients for which the code could be derived 
//		 * (even if a user interaction would be necessary) 
//		*/
//		CreatorValueBinding vbc = new CreatorValueBinding();
//		
//		// pass the pre-collected mediators in oder to avoid searching once again 
//		vbc.setAllMediators(collector.getAllMediators()); 
//		
//		
//		/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
//		 * so that no modifications are created in components because we only want to analyze possible bindings.
//		 */
//		// TODO: check the performance 
//		vbc.updateAllBindings(valueMediatorsPackage, null, treeParentToStartTheCheckOn, virtualInstantiationTreeRoot, false, true, false, true);
//		
//		return vbc.getAllClientsFound().size() > 0;
//	}
	
	
	public void removeNotUsedRequirements(HashSet<Element> newRequirementsSubSet){
		
		HashSet<Element> notUsedRequirements = new HashSet<Element>();
		// add all requirements
		notUsedRequirements.addAll(this.requirements);
		// remove all form the new set in order to get the remaining requirements that are not used and should be removed. 
		notUsedRequirements.removeAll(newRequirementsSubSet);
		
		// remove all instantiations.
		for (Element requirement : notUsedRequirements) {
			TreeParent requirementInstantiation = modelToItsInstantiation.get(requirement).getTreeRoot();
			if (requirementInstantiation != null) {
				this.virtualInstantiationTreeRoot.removeChild(requirementInstantiation);	
			}
			// add to log.
			String message = "REMOVED(06): Requirement '" + ((NamedElement)requirement).getQualifiedName()+ "' was unselected and is removed.";
			addToLog(message);
		}
		// remove the elements
		this.requirements.removeAll(notUsedRequirements);
	}
	
	public HashSet<Element> getAdditionalSystemModelModels(boolean prune){
		if (!prune) {
			return this.requiredModels_systemModel;
		}
		HashSet<Element> additionalModels = new HashSet<Element>();
		for (Element additionalModel : this.requiredModels_systemModel) {
			TreeParent additionalModelInstantiation = this.modelToItsInstantiation.get(additionalModel).getTreeRoot();
			if (additionalModelInstantiation != null) {
				if (alwaysInclude.contains(additionalModel) 
						|| isAtLeastOneProviderUsed(virtualInstantiationTreeRoot, additionalModelInstantiation)) {
					additionalModels.add(additionalModel);
				}
				else { // -> prune this model
					String message = "REMOVED(07): Additional Model '" + ((NamedElement)additionalModel).getQualifiedName() + "'" +
					"\nwas removed because none of its providers is used.";
					addToLog(message);
				}
			}
		}
		
		return additionalModels;
	}
	
	public HashSet<Element> getAdditionalScenarioModels(boolean prune){
		if (!prune) {
			return this.requiredModels_scenario;
		}
		HashSet<Element> additionalModels = new HashSet<Element>();
		for (Element additionalModel : this.requiredModels_scenario) {
			TreeParent additionalModelInstantiation = this.modelToItsInstantiation.get(additionalModel).getTreeRoot();
			if (additionalModelInstantiation != null) {
				if (alwaysInclude.contains(additionalModel) 
						|| isAtLeastOneProviderUsed(virtualInstantiationTreeRoot, additionalModelInstantiation)) {
					additionalModels.add(additionalModel);
				}
				else { // -> prune this model
					String message = "REMOVED(08): Additional Model '" + ((NamedElement)additionalModel).getQualifiedName() + "'" +
					"\nwas removed because none of its providers is used.";
					addToLog(message);
				}
			}
		}
		
		return additionalModels;
	}
	
	public HashSet<Element> getAdditionalRequirementModels(Element requirement, boolean prune){
		if (!prune) {
			return this.requiredModels_requirements.get(requirement);
		}
		
		HashSet<Element> additionalModels = new HashSet<Element>();
		for (Element additionalModel : this.requiredModels_requirements.get(requirement)) {
			TreeParent additionalModelInstantiation = this.modelToItsInstantiation.get(additionalModel).getTreeRoot();
			if (additionalModelInstantiation != null) {
				if (alwaysInclude.contains(additionalModel) 
						|| isAtLeastOneProviderUsed(virtualInstantiationTreeRoot, additionalModelInstantiation)) {
					additionalModels.add(additionalModel);
				}
				else { // -> prune this model
					String message = "REMOVED(09): Additional Model '" + ((NamedElement)additionalModel).getQualifiedName() + "'" +
					"\nwas removed because none of its providers is used.";
					addToLog(message);
				}
			}
		}
		
		return additionalModels;
	}
	
	// Utls **********************************************************************
	
	public HashSet<TreeObject> getUnsatisfiedMandatoryClients(Element model){
		if (model != null) {
			TreeParent modelInstantiation = modelToItsInstantiation.get(model).getTreeRoot();
			
			if (modelInstantiation != null) {
				HashSet<TreeObject> unsatisfiedClients = mandatoryClients_unsatisfied.get(modelInstantiation);

				if (unsatisfiedClients != null && unsatisfiedClients.size() > 0 ) {
					return unsatisfiedClients;
				}
			}
		}
		return null;
	}
	
	
	public HashSet<TreeObject> getModelClients(Element model) {
		if (model != null) {
			TreeParent modelInstantiation = modelToItsInstantiation.get(model).getTreeRoot();
		
			if (modelInstantiation != null) {
				HashSet<TreeObject> allClients = modelClients.get(modelInstantiation);

				if (allClients != null && allClients.size() > 0 ) {
					return allClients;
				}
			}
		}
		return null;
	}

	
	
	public String getClientsDotPathAsString(HashSet<TreeObject> items) {
		if (items != null) {
			String unsatisfiedRequiredClientList = "";
			
			List<String> dotPathList = new ArrayList<String>();
			for (TreeObject treeObject: items) {
				dotPathList.add(treeObject.getDotPath());
			}
			Collections.sort(dotPathList);
			
			for (String string : dotPathList) {
				unsatisfiedRequiredClientList = unsatisfiedRequiredClientList + "- " + string + "\n"; 
			}
			
			return unsatisfiedRequiredClientList;
		}
		return "";
	}
	
	private boolean treeContainsOneOf(HashSet<TreeObject> treeItems, TreeParent treeParent){
		HashSet<TreeObject> allTreeItems = getAllTreeItems(treeParent);
		
		for (TreeObject treeObject : treeItems) {
			if (allTreeItems.contains(treeObject)) {
				return true;
			}
		}
		return false;
	}
	
	private HashSet<TreeObject> getAllTreeItems(TreeParent treeParent){
		HashSet<TreeObject> allTreeItems = new HashSet<TreeObject>();
		allTreeItems.add(treeParent);
		
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			allTreeItems.add(children[i]);
			if (children[i] instanceof TreeParent) {
				allTreeItems.addAll(getAllTreeItems((TreeParent)children[i]));
			}
		}
		
		return allTreeItems;
	}
	
	private HashSet<Element> getAllTreeItemsClasses(TreeParent treeParent){
		HashSet<Element> allTreeItems = new HashSet<Element>();
		
		// If it is the root then add the root class
		if (treeParent.isRoot() && treeParent.getSelectedClass() != null) {
			allTreeItems.add(treeParent.getSelectedClass());
		}
		
		// add all classes used in children
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i].getUmlElement() instanceof TypedElement && ((TypedElement)children[i].getUmlElement()).getType() instanceof Classifier) {
				allTreeItems.add(((TypedElement)children[i].getUmlElement()).getType());
			}
			if (children[i] instanceof TreeParent) {
				allTreeItems.addAll(getAllTreeItemsClasses((TreeParent)children[i]));
			}
		}
		return allTreeItems;
	}
	
	private void initializeLog(){
		this.log = "\n----------------------------------------------" +
//		"---------------------------------------------------" +
		"--------------------------------------------------- \n" +
		"Log for the combination:" +
			"\n   - Scenario '" + ((NamedElement)this.scenario).getQualifiedName() + "'" +
			"\n   - System Model '" + ((NamedElement)this.systemModel).getQualifiedName() + "'" +
			"\n";
	}
	
	private void addToLog(String msg){
		this.log = this.log + "\n" + msg + "\n";
	}
	
	// Getter/Setter **************************************************************
	
	public Element getSystemModel() {
		return systemModel;
	}
	
	public Element getTestScenario() {
		return scenario;
	}
	
	public String getLog() {
		return log;
	}
	
	public HashSet<Element> getRequiredModels_systemModel() {
		return requiredModels_systemModel;
	}

	public HashSet<Element> getRequiredModels_scenario() {
		return requiredModels_scenario;
	}

	public HashMap<Element, HashSet<Element>> getRequiredModels_requirements() {
		return requiredModels_requirements;
	}

	public HashSet<Element> getAllCollectedAdditionalModels() {
		return allCollectedAdditionalModels;
	}
	
	public HashSet<Element> getDiscardTheseModelsWhenCollectingAdditionalModels() {
		return initialSetOfModels;
	}
	
	public void setDiscardTheseModelsWhenCollectingAdditionalModels(
			HashSet<Element> discardTheseModelsWhenCollectingAdditionalModels) {
		this.initialSetOfModels = discardTheseModelsWhenCollectingAdditionalModels;
	}

	public void setDiscarded(boolean isDiscarded) {
		this.isDiscarded = isDiscarded;
	}

	public boolean isDiscarded() {
		return isDiscarded;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public boolean isError() {
		return isError;
	}
	

	public HashMap<TreeParent, HashSet<TreeObject>> getRequiredClients_unsatisfied() {
		return mandatoryClients_unsatisfied;
	}


	public HashSet<Element> getRequirements() {
		return requirements;
	}


	public void setRequirements(HashSet<Element> requirements) {
		this.requirements = requirements;
	}
	

	public HashMap<Element, ClassInstantiation> getModelToItsInstantiation() {
		return modelToItsInstantiation;
	}
	
	public void setModelToItsInstantiation(
			HashMap<Element, ClassInstantiation> modelToItsInstantiation) {
		this.modelToItsInstantiation = modelToItsInstantiation;
	}

	
	
	public HashSet<Element> getAlwaysInclude() {
		return alwaysInclude;
	}

}
