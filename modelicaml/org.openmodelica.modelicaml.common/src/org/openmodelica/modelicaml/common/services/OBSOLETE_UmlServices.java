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
package org.openmodelica.modelicaml.common.services;

import java.util.Iterator;
import java.util.List;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Stereotype;



/**
 * The Class UmlServices.
 */
public class OBSOLETE_UmlServices {


	/**
	 * Get the body of OpaqueBehavior ob with index corresponding to the
	 * language index. We replace all \n text by newline.
	 *
	 * @param ob an Opaque behavior.
	 * @param language the language to find.
	 * @return the content body.
	 */
	@Deprecated
	public String getBody(OpaqueBehavior ob, String language){
		List<String> languages = ob.getLanguages();
		int i = 0;
		for (Iterator<String> iter2 = languages.iterator(); iter2.hasNext();) {
			String lang = iter2.next().toString();
			if (lang.equals(language)) {
				if (ob.getBodies().size() != 0) {
					Object body = ob.getBodies().get(i);
					if (body != null) {
						return body.toString();
					} else {
						return null;
					}
				}
			}
			i++;
		}
		return null;
	}

	/**
	 * Return the stereotype if is applied to element.
	 *
	 * @param elt Element used.
	 * @param stereotype Stereotype to return.
	 * @return the stereotype.
	 */
	@Deprecated
	public static Stereotype getStereotype(Element elt, String stereotype){
		Stereotype result = null;
		// search with real stereotype
		for ( Iterator<Stereotype> iter = elt.getAppliedStereotypes().iterator(); iter.hasNext(); ) {
			Stereotype element = iter.next();
			if (element.getName().equals(stereotype)) {
				result = element;
			}
		}
		return result;
	}

	/**
	 * Return the stereotype value if is applied to element.
	 *
	 * @param elt Element used.
	 * @param stereotype Stereotype to return.
	 * @param propertyName the property name
	 * @return Object contain the value.
	 */
	@Deprecated
	public static Object getStereotypeValue(Element elt, String stereotype,
			String propertyName) {
		// search with real stereotype
		Stereotype stereotypeFound = getStereotype(elt, stereotype);
		if (stereotypeFound == null) {
			return null;
		} else {
			return elt.getValue(stereotypeFound, propertyName);
		}
	}
	
	/**
	 * Verify if an Element have a stereotype. Use keyword and profile to find
	 * stereotype. Multiple stereotype are allow.
	 *
	 * @param elt Element used.
	 * @param stereotype Stereotype to search.
	 * @return true if found. false else.
	 */
	@Deprecated
	public static boolean hasStereotype(Element elt, String stereotype){
		return (getStereotype(elt, stereotype) != null);
	}

	/**
	 * Test if aClass is a behavior element.
	 *
	 * @param aClass Class to.
	 * @return Boolean return true if aClass is a behavior.
	 */
	@Deprecated
	public Boolean isBehavior(Class aClass) {
		if (aClass instanceof Behavior) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
