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
package org.openmodelica.modelicaml.profile.handlers;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.profile.Activator;


// TODO: Auto-generated Javadoc
/**
 * The Class CreateModelicaAssertHandler.
 */
public class CreateModelicaAssertHandler extends AbstractHandler {

//### START: adapt it for a new command handler
	/** The stereotype path. */
private String stereotypePath = Platform.getResourceString(Activator.getDefault().getBundle(), "%p_path_ModelicaBehaviorConstructs");
	
	/** The stereotype name. */
	private String stereotypeName = Platform.getResourceString(Activator.getDefault().getBundle(), "%s_assert");
//### END: adapt it for a new command handler
	
	/** The selected element. */
private EObject selectedElement = null;
	
	/**
	 * Execute Command. Get the property from the selection, and apply the
	 * semantic command on each selection.
	 * 
	 * 
	 * event
	 * 
	 * @param event
	 *            the event
	 * @return the object
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TransactionalEditingDomain editingDomain = EditorServices.getPapyrusEditingDomain();
		editingDomain.getCommandStack().execute(getCommand(editingDomain));
		return null;
	}

	/**
	 * Gets the command.
	 * 
	 * @param editingDomain
	 *            the editing domain
	 * @return the command
	 */
	protected Command getCommand(TransactionalEditingDomain editingDomain) {
		
		CompoundCommand cc = new CompoundCommand(stereotypeName);
		
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				Constraint Ct = null;
				Stereotype stereotype = null;
				
//### START: adapt it for a new command handler
				if (selectedElement instanceof Namespace) {
					//TODO: Should the name get a post-fix number (e.g. ModelicaModel1, ModelicaModel2, etc.)?
					Ct = (Constraint) ((org.eclipse.uml2.uml.Namespace)selectedElement).createOwnedRule(stereotypeName, UMLPackage.Literals.CONSTRAINT);
					stereotype = Ct.getApplicableStereotype(stereotypePath+"::"+stereotypeName);
				}
				else {
					System.err.println("Could not create a Modelica " + stereotypeName + " in " + selectedElement.getClass().getName());
				}
				
				// apply ModelicaML stereotype
				if (stereotype == null) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					MessageDialog.openError(shell, "Error:", "Cannot apply ModelicaML stereotype " + stereotypeName +" to " + Ct.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
				}
				else {
					Ct.applyStereotype(stereotype);
				}
				
				// locate in Papyrus
				if ( Ct != null) {
					EditorServices.locateInModelExplorer(Ct, true);
				}
//### END: adapt it for a new command handler
				
			}
		};
		cc.append(command);
		return (cc.unwrap());
	}
	
	/**
	 * Returns a <code>List</code> containing the currently selected objects.
	 * 
	 * @param event
	 *            the event
	 * @return A List containing the currently selected objects.
	 * @throws ExecutionException
	 *             the execution exception
	 */
	@SuppressWarnings("unchecked")
	protected List<Object> getSelectedElements(ExecutionEvent event) throws ExecutionException {
		
		ISelection selection = getSelection(event);
		if (!( selection instanceof IStructuredSelection))
			return Collections.emptyList();
		return (List<Object>)((IStructuredSelection) selection).toList();
	}

	/**
	 * Gets the current selection.
	 * 
	 * @param event
	 *            the event
	 * @return The current selection.
	 * @throws ExecutionException
	 *             the execution exception
	 */
	protected ISelection getSelection(ExecutionEvent event) throws ExecutionException {
		return HandlerUtil.getCurrentSelectionChecked(event);
	}

	/**
	 * Gets the current selections.
	 * 
	 * @return the current selections
	 */
	@SuppressWarnings("unchecked")
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
	
		return null;
	}

	/**
	 * Adapt the selected element to an EObject, in case there is intermediate
	 * construct (like notation.View)
	 * 
	 * @param selection
	 *            the selection
	 * @return the e object
	 */
	protected EObject adaptSelectedElement( Object selection) {

		EObject eObject = null;
	
		if(selection != null) {
	
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
	
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}

	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	public boolean isEnabled() {
		
//		for( Object selection : getCurrentSelections()) {
//			EObject converted = adaptSelectedElement(selection);
//			
//			if(converted instanceof Class)
//				return true;
//		}
//		return false;
		return true;
	}

	
	/**
	 * Checks if is visible.
	 * 
	 * @return true, if is visible
	 */
	public boolean isVisible() {
//		for( Object selection : getCurrentSelections()) {
//			EObject converted = adaptSelectedElement(selection);
//			
//			if(converted instanceof Class)
//				return true;
//		}
//		return false;
		return true;
	}

}
