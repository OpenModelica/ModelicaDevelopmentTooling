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
package org.openmodelica.modelicaml.helper.tree.model;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

public class VerificationScenariosAndRequirementsSelectionTreeObject  {
	private String name;
	private VerificationScenariosAndRequirementsSelectionTreeParent parent;
	private Element umlElement;
	
	private boolean isTestScenario = false;
	private boolean isRequirement = false;

	private boolean isReadOnly = false;
	
	
	public VerificationScenariosAndRequirementsSelectionTreeObject(String name) {
		this.name = name;
	}
	
	public String getName() {
		if ( !this.name.equals("") ) {
			return this.name;	
		}
		else if (getUmlElement() instanceof NamedElement) {
			return ((NamedElement)getUmlElement()).getName();
		}
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setParent(VerificationScenariosAndRequirementsSelectionTreeParent parent) {
		this.parent = parent;
	}
	public VerificationScenariosAndRequirementsSelectionTreeParent getParent() {
		return parent;
	}
	public String toString() {
		return getName();
	}
	
	

	public void setUmlElement(Element umlElement) {
		this.umlElement = umlElement;
	}
	public Element getUmlElement() {
		return umlElement;
	}

	public void setTestScenario(boolean isTestScenario) {
		this.isTestScenario = isTestScenario;
	}

	public boolean isTestScenario() {
		return isTestScenario;
	}

	public void setRequirement(boolean isRequirement) {
		this.isRequirement = isRequirement;
	}

	public boolean isRequirement() {
		return isRequirement;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}
	

}