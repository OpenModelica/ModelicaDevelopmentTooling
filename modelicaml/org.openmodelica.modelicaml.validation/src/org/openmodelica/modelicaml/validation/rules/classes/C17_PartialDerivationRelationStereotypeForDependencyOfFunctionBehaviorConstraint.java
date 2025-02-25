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
package org.openmodelica.modelicaml.validation.rules.classes;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C17:
 * 	Rule :	UML FunctionBehvaior with ModelicaML stereotype applied can only have one 
 * 			UML Dependency with the stereotype <<PartialDerivativeRelation>> which must 
 * 			point to a UML Function Behavior with the ModelicaML  stereotype 
 * 			Function applied
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C17_PartialDerivationRelationStereotypeForDependencyOfFunctionBehaviorConstraint
		extends AbstractModelConstraint {

	public C17_PartialDerivationRelationStereotypeForDependencyOfFunctionBehaviorConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch Mode
		if(eType == EMFEventType.NULL) {
			if(eObj instanceof FunctionBehavior) {
				
				FunctionBehavior functionBehavior = (FunctionBehavior) eObj;
				
				if(Utility.isElementHaveModelicaMLStereotypeApplied(functionBehavior)) {
					
					List<Dependency> dependenciesList = functionBehavior.getClientDependencies();
					boolean havingPartialDerivativeRelationDependency = false;
					
					for (Dependency dependency : dependenciesList) {
						
						if(dependency.getAppliedStereotype(Constants.stereotypeQName_PartialDerivativeOfFunctionRelation) != null){ 
							
							if(havingPartialDerivativeRelationDependency == true){
								return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + 
										": FunctionBehavior has more than one UML::Dependency with <<PartialDerivativeRelation>> stereotype applied."});
							}
							else {
								havingPartialDerivativeRelationDependency = true;
								
								List<Element> elementList = dependency.getTargets();
								
								for (Element element : elementList) {
									
									if(element instanceof FunctionBehavior && (element.getAppliedStereotype(Constants.stereotypeQName_Function) == null)){
										return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + 
												": FunctionBehavior having UML::Dependency with <<PartialDerivativeRelation>> stereotype applied " +
												"must point to a FunctionBehavior with <<Function>> stereotype applied."});
									}
								}
							}
						}
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
