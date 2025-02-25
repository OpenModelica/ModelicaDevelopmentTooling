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
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.openmodelica.modelicaml.common.constants.Constants;

/**	
 * State Machines
 * 
 * C33:
 * 	Rule :	A UML Transition to UML PseudoState of kind fork must have UML State as source
 * 			 which must be in the same UML Region.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */
 
public class C33_TransitionToForkFromStateMustBeSameRegionConstraint extends AbstractModelConstraint {

	public C33_TransitionToForkFromStateMustBeSameRegionConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		
			if(eObj instanceof Transition) {
				Transition transition = (Transition) eObj;
				
				if(	transition.getTarget() != null 
						&& transition.getSource() != null && transition.getTarget() instanceof Pseudostate 
						&& ((Pseudostate)transition.getTarget()).getKind().getValue() == PseudostateKind.FORK ) {
					
					State source = (State) transition.getSource();
					Pseudostate target = (Pseudostate) transition.getTarget();
					
					if(source.getOwner() != null && target.getOwner() != null 
							&& source.getOwner() != target.getOwner()) {
						
						return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Transition '" 
								+ transition.getName()+"' from '"+source.getName()+"' to '"+target.getName()+"' fork state must be in same region. "});
					}
				}
			}
		
		return ctx.createSuccessStatus();
	}

}
