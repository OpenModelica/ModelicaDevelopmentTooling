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
package org.openmodelica.modelicaml.tabbedproperties.editors.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.State;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;

// TODO: Auto-generated Javadoc
/**
 * The Class AlgorithmSectionCodeSectionFilter.
 */
public class AlgorithmSectionCodeSectionFilter implements IFilter {

	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.profile.utils.UmlElementFilter#select(java.lang.Object)
	 */
	@Override
	public boolean select(Object object) {
		EObject element = null;

		// Get the selected element
        EObject selectedElement = ModelicaMLServices.adaptSelectedElement(object);
        if (selectedElement instanceof Element) {
        	element = (Element)selectedElement;
		}
		
		// Decide if an editor tab should appear
		// For UML OpaqueBehavior can include Modelica equation or algorithm code
		if ( element instanceof OpaqueBehavior && !(element instanceof FunctionBehavior) ){
			if (((OpaqueBehavior)element).getAppliedStereotype("ModelicaML::ModelicaBehaviorConstructs::Algorithm(Code)") != null
					|| ((OpaqueBehavior)element).getOwner() instanceof State ) { 
				return true;
			}
		}
		// For UML OpaqueAction, in an Activity (diagram), that can include Modelica equation or algorithm code
		if ( element instanceof OpaqueAction ){
			if (((OpaqueAction)element).getActivity().getAppliedStereotype("ModelicaML::ModelicaBehaviorConstructs::ConditionalAlgorithm(Diagram)") != null) { 
				return true; 
			}
		}
		
		if ( element instanceof FunctionBehavior ){
			if (((FunctionBehavior)element).getAppliedStereotype(Constants.stereotypeQName_Function) != null) { 
				return true; 
			}
		}
		
		return false;
	}
}
