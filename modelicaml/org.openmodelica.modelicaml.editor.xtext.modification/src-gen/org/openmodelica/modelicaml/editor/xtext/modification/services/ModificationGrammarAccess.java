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

package org.openmodelica.modelicaml.editor.xtext.modification.services;

import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;
import org.openmodelica.modelicaml.editor.xtext.model.services.ModeleditorGrammarAccess;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ModificationGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class Modification_alternativesElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "modification_alternatives");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final RuleCall cLeft_hand_component_referenceParserRuleCall_0_0 = (RuleCall)cGroup_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_0_1 = (Keyword)cGroup_0.eContents().get(1);
		private final Assignment cExprAssignment_0_2 = (Assignment)cGroup_0.eContents().get(2);
		private final RuleCall cExprExpressionParserRuleCall_0_2_0 = (RuleCall)cExprAssignment_0_2.eContents().get(0);
		private final Assignment cModification_RuleAssignment_1 = (Assignment)cAlternatives.eContents().get(1);
		private final RuleCall cModification_RuleModificationParserRuleCall_1_0 = (RuleCall)cModification_RuleAssignment_1.eContents().get(0);
		
		//////////////////////////////////////Modification///////////////////////////
		//// ModelicaML specific. All rules are made optional in order to not create validation error markers if nothing was entered by the user.
		//modification_alternatives:
		//	(left_hand_component_reference "=" Expr=expression)? //a simple component equality assignment modification rule without "(" and ")". Added for better code completion support (see the left_hand_component_reference that returns only the subcomponents of the component being modified)
		//	// standard modelica modification rule including "(" and ")" and class extension modifications
		//	| Modification_Rule=modification?;
		public ParserRule getRule() { return rule; }

		//(left_hand_component_reference "=" Expr=expression)? //a simple component equality assignment modification rule without "(" and ")". Added for better code completion support (see the left_hand_component_reference that returns only the subcomponents of the component being modified)
		//// standard modelica modification rule including "(" and ")" and class extension modifications
		//| Modification_Rule=modification?
		public Alternatives getAlternatives() { return cAlternatives; }

		//(left_hand_component_reference "=" Expr=expression)?
		public Group getGroup_0() { return cGroup_0; }

		//left_hand_component_reference
		public RuleCall getLeft_hand_component_referenceParserRuleCall_0_0() { return cLeft_hand_component_referenceParserRuleCall_0_0; }

		//"="
		public Keyword getEqualsSignKeyword_0_1() { return cEqualsSignKeyword_0_1; }

		//Expr=expression
		public Assignment getExprAssignment_0_2() { return cExprAssignment_0_2; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_0_2_0() { return cExprExpressionParserRuleCall_0_2_0; }

		//Modification_Rule=modification?
		public Assignment getModification_RuleAssignment_1() { return cModification_RuleAssignment_1; }

		//modification
		public RuleCall getModification_RuleModificationParserRuleCall_1_0() { return cModification_RuleModificationParserRuleCall_1_0; }
	}

	public class Left_hand_component_referenceElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "left_hand_component_reference");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cFullStopKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cRefAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cRefIDENTTerminalRuleCall_1_0 = (RuleCall)cRefAssignment_1.eContents().get(0);
		private final Assignment cSubscripts1Assignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cSubscripts1Array_subscriptsParserRuleCall_2_0 = (RuleCall)cSubscripts1Assignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cFullStopKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cRef1Assignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cRef1IDENTTerminalRuleCall_3_1_0 = (RuleCall)cRef1Assignment_3_1.eContents().get(0);
		private final Assignment cSubscriptsAssignment_3_2 = (Assignment)cGroup_3.eContents().get(2);
		private final RuleCall cSubscriptsArray_subscriptsParserRuleCall_3_2_0 = (RuleCall)cSubscriptsAssignment_3_2.eContents().get(0);
		
		//// ModelicaML specific. 
		//left_hand_component_reference:
		//	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
		public ParserRule getRule() { return rule; }

		//"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Group getGroup() { return cGroup; }

		//"."?
		public Keyword getFullStopKeyword_0() { return cFullStopKeyword_0; }

		//ref=IDENT
		public Assignment getRefAssignment_1() { return cRefAssignment_1; }

		//IDENT
		public RuleCall getRefIDENTTerminalRuleCall_1_0() { return cRefIDENTTerminalRuleCall_1_0; }

		//subscripts1=array_subscripts?
		public Assignment getSubscripts1Assignment_2() { return cSubscripts1Assignment_2; }

		//array_subscripts
		public RuleCall getSubscripts1Array_subscriptsParserRuleCall_2_0() { return cSubscripts1Array_subscriptsParserRuleCall_2_0; }

		//("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Group getGroup_3() { return cGroup_3; }

		//"."
		public Keyword getFullStopKeyword_3_0() { return cFullStopKeyword_3_0; }

		//ref1+=IDENT
		public Assignment getRef1Assignment_3_1() { return cRef1Assignment_3_1; }

		//IDENT
		public RuleCall getRef1IDENTTerminalRuleCall_3_1_0() { return cRef1IDENTTerminalRuleCall_3_1_0; }

		//subscripts+=array_subscripts?
		public Assignment getSubscriptsAssignment_3_2() { return cSubscriptsAssignment_3_2; }

		//array_subscripts
		public RuleCall getSubscriptsArray_subscriptsParserRuleCall_3_2_0() { return cSubscriptsArray_subscriptsParserRuleCall_3_2_0; }
	}

	public class ModificationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "modification");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final RuleCall cClass_modificationParserRuleCall_0_0 = (RuleCall)cGroup_0.eContents().get(0);
		private final Group cGroup_0_1 = (Group)cGroup_0.eContents().get(1);
		private final Keyword cEqualsSignKeyword_0_1_0 = (Keyword)cGroup_0_1.eContents().get(0);
		private final Assignment cExprAssignment_0_1_1 = (Assignment)cGroup_0_1.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_0_1_1_0 = (RuleCall)cExprAssignment_0_1_1.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Keyword cEqualsSignKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cExprAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_1_1_0 = (RuleCall)cExprAssignment_1_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cAlternatives.eContents().get(2);
		private final Keyword cColonEqualsSignKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cExprAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_2_1_0 = (RuleCall)cExprAssignment_2_1.eContents().get(0);
		
		//modification:
		//	class_modification ("=" Expr=expression)? | "=" Expr=expression | ":=" Expr=expression;
		public ParserRule getRule() { return rule; }

		//class_modification ("=" Expr=expression)? | "=" Expr=expression | ":=" Expr=expression
		public Alternatives getAlternatives() { return cAlternatives; }

		//class_modification ("=" Expr=expression)?
		public Group getGroup_0() { return cGroup_0; }

		//class_modification
		public RuleCall getClass_modificationParserRuleCall_0_0() { return cClass_modificationParserRuleCall_0_0; }

		//("=" Expr=expression)?
		public Group getGroup_0_1() { return cGroup_0_1; }

		//"="
		public Keyword getEqualsSignKeyword_0_1_0() { return cEqualsSignKeyword_0_1_0; }

		//Expr=expression
		public Assignment getExprAssignment_0_1_1() { return cExprAssignment_0_1_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_0_1_1_0() { return cExprExpressionParserRuleCall_0_1_1_0; }

		//"=" Expr=expression
		public Group getGroup_1() { return cGroup_1; }

		//"="
		public Keyword getEqualsSignKeyword_1_0() { return cEqualsSignKeyword_1_0; }

		//Expr=expression
		public Assignment getExprAssignment_1_1() { return cExprAssignment_1_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_1_1_0() { return cExprExpressionParserRuleCall_1_1_0; }

		//":=" Expr=expression
		public Group getGroup_2() { return cGroup_2; }

		//":="
		public Keyword getColonEqualsSignKeyword_2_0() { return cColonEqualsSignKeyword_2_0; }

		//Expr=expression
		public Assignment getExprAssignment_2_1() { return cExprAssignment_2_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_2_1_0() { return cExprExpressionParserRuleCall_2_1_0; }
	}

	public class Class_modificationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "class_modification");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cClass_modification_actionAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cArgAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cArgArgument_listParserRuleCall_2_0 = (RuleCall)cArgAssignment_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		////argument
		//class_modification:
		//	{class_modification_action} "(" Arg=argument_list? ")";
		public ParserRule getRule() { return rule; }

		//{class_modification_action} "(" Arg=argument_list? ")"
		public Group getGroup() { return cGroup; }

		//{class_modification_action}
		public Action getClass_modification_actionAction_0() { return cClass_modification_actionAction_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//Arg=argument_list?
		public Assignment getArgAssignment_2() { return cArgAssignment_2; }

		//argument_list
		public RuleCall getArgArgument_listParserRuleCall_2_0() { return cArgArgument_listParserRuleCall_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class Argument_listElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "argument_list");
		private final RuleCall cArgumentParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		//argument_list: //argument  (',' Arg+=argument )* 	// ModelicaML specific, to allow only one entry. This is mainly needed for class components tree view.
		//	argument;
		public ParserRule getRule() { return rule; }

		////argument  (',' Arg+=argument )* 	// ModelicaML specific, to allow only one entry. This is mainly needed for class components tree view.
		//argument
		public RuleCall getArgumentParserRuleCall() { return cArgumentParserRuleCall; }
	}

	public class ArgumentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "argument");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cElement_modification_or_replaceableParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cElement_redeclarationParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//argument:
		//	element_modification_or_replaceable | element_redeclaration;
		public ParserRule getRule() { return rule; }

		//element_modification_or_replaceable | element_redeclaration
		public Alternatives getAlternatives() { return cAlternatives; }

		//element_modification_or_replaceable
		public RuleCall getElement_modification_or_replaceableParserRuleCall_0() { return cElement_modification_or_replaceableParserRuleCall_0; }

		//element_redeclaration
		public RuleCall getElement_redeclarationParserRuleCall_1() { return cElement_redeclarationParserRuleCall_1; }
	}

	public class Element_modification_or_replaceableElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "element_modification_or_replaceable");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cElement_modification_or_replaceableAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cEachKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cFinalKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Alternatives cAlternatives_3 = (Alternatives)cGroup.eContents().get(3);
		private final Assignment cElement_modAssignment_3_0 = (Assignment)cAlternatives_3.eContents().get(0);
		private final RuleCall cElement_modElement_modificationParserRuleCall_3_0_0 = (RuleCall)cElement_modAssignment_3_0.eContents().get(0);
		private final Assignment cElement_repAssignment_3_1 = (Assignment)cAlternatives_3.eContents().get(1);
		private final RuleCall cElement_repElement_replaceableParserRuleCall_3_1_0 = (RuleCall)cElement_repAssignment_3_1.eContents().get(0);
		
		//element_modification_or_replaceable:
		//	{element_modification_or_replaceable} "each"? "final"? (Element_mod=element_modification |
		//	Element_rep=element_replaceable);
		public ParserRule getRule() { return rule; }

		//{element_modification_or_replaceable} "each"? "final"? (Element_mod=element_modification |
		//Element_rep=element_replaceable)
		public Group getGroup() { return cGroup; }

		//{element_modification_or_replaceable}
		public Action getElement_modification_or_replaceableAction_0() { return cElement_modification_or_replaceableAction_0; }

		//"each"?
		public Keyword getEachKeyword_1() { return cEachKeyword_1; }

		//"final"?
		public Keyword getFinalKeyword_2() { return cFinalKeyword_2; }

		//Element_mod=element_modification | Element_rep=element_replaceable
		public Alternatives getAlternatives_3() { return cAlternatives_3; }

		//Element_mod=element_modification
		public Assignment getElement_modAssignment_3_0() { return cElement_modAssignment_3_0; }

		//element_modification
		public RuleCall getElement_modElement_modificationParserRuleCall_3_0_0() { return cElement_modElement_modificationParserRuleCall_3_0_0; }

		//Element_rep=element_replaceable
		public Assignment getElement_repAssignment_3_1() { return cElement_repAssignment_3_1; }

		//element_replaceable
		public RuleCall getElement_repElement_replaceableParserRuleCall_3_1_0() { return cElement_repElement_replaceableParserRuleCall_3_1_0; }
	}

	public class Element_modificationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "element_modification");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameNameParserRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Assignment cModificationAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cModificationModificationParserRuleCall_1_0 = (RuleCall)cModificationAssignment_1.eContents().get(0);
		private final RuleCall cString_commentParserRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		
		//element_modification:
		//	Name=name Modification=modification? string_comment;
		public ParserRule getRule() { return rule; }

		//Name=name Modification=modification? string_comment
		public Group getGroup() { return cGroup; }

		//Name=name
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }

		//name
		public RuleCall getNameNameParserRuleCall_0_0() { return cNameNameParserRuleCall_0_0; }

		//Modification=modification?
		public Assignment getModificationAssignment_1() { return cModificationAssignment_1; }

		//modification
		public RuleCall getModificationModificationParserRuleCall_1_0() { return cModificationModificationParserRuleCall_1_0; }

		//string_comment
		public RuleCall getString_commentParserRuleCall_2() { return cString_commentParserRuleCall_2; }
	}

	public class Element_replaceableElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "element_replaceable");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cElement_replaceableAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cReplaceableKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Alternatives cAlternatives_2 = (Alternatives)cGroup.eContents().get(2);
		private final Assignment cClass_DefAssignment_2_0 = (Assignment)cAlternatives_2.eContents().get(0);
		private final RuleCall cClass_DefClass_definitionParserRuleCall_2_0_0 = (RuleCall)cClass_DefAssignment_2_0.eContents().get(0);
		private final Assignment cComp_clause2Assignment_2_1 = (Assignment)cAlternatives_2.eContents().get(1);
		private final RuleCall cComp_clause2Component_clause1ParserRuleCall_2_1_0 = (RuleCall)cComp_clause2Assignment_2_1.eContents().get(0);
		private final Assignment cConstrain_ClauseAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cConstrain_ClauseConstraining_clauseParserRuleCall_3_0 = (RuleCall)cConstrain_ClauseAssignment_3.eContents().get(0);
		
		//element_replaceable:
		//	{element_replaceable} "replaceable" (Class_Def=class_definition | Comp_clause2=component_clause1)
		//	Constrain_Clause=constraining_clause?;
		public ParserRule getRule() { return rule; }

		//{element_replaceable} "replaceable" (Class_Def=class_definition | Comp_clause2=component_clause1)
		//Constrain_Clause=constraining_clause?
		public Group getGroup() { return cGroup; }

		//{element_replaceable}
		public Action getElement_replaceableAction_0() { return cElement_replaceableAction_0; }

		//"replaceable"
		public Keyword getReplaceableKeyword_1() { return cReplaceableKeyword_1; }

		//Class_Def=class_definition | Comp_clause2=component_clause1
		public Alternatives getAlternatives_2() { return cAlternatives_2; }

		//Class_Def=class_definition
		public Assignment getClass_DefAssignment_2_0() { return cClass_DefAssignment_2_0; }

		//class_definition
		public RuleCall getClass_DefClass_definitionParserRuleCall_2_0_0() { return cClass_DefClass_definitionParserRuleCall_2_0_0; }

		//Comp_clause2=component_clause1
		public Assignment getComp_clause2Assignment_2_1() { return cComp_clause2Assignment_2_1; }

		//component_clause1
		public RuleCall getComp_clause2Component_clause1ParserRuleCall_2_1_0() { return cComp_clause2Component_clause1ParserRuleCall_2_1_0; }

		//Constrain_Clause=constraining_clause?
		public Assignment getConstrain_ClauseAssignment_3() { return cConstrain_ClauseAssignment_3; }

		//constraining_clause
		public RuleCall getConstrain_ClauseConstraining_clauseParserRuleCall_3_0() { return cConstrain_ClauseConstraining_clauseParserRuleCall_3_0; }
	}

	public class Constraining_clauseElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "constraining_clause");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cConstrainedbyKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cClass_ModAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cClass_ModClass_modificationParserRuleCall_2_0 = (RuleCall)cClass_ModAssignment_2.eContents().get(0);
		
		//constraining_clause:
		//	"constrainedby" Name=name Class_Mod=class_modification?;
		public ParserRule getRule() { return rule; }

		//"constrainedby" Name=name Class_Mod=class_modification?
		public Group getGroup() { return cGroup; }

		//"constrainedby"
		public Keyword getConstrainedbyKeyword_0() { return cConstrainedbyKeyword_0; }

		//Name=name
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//name
		public RuleCall getNameNameParserRuleCall_1_0() { return cNameNameParserRuleCall_1_0; }

		//Class_Mod=class_modification?
		public Assignment getClass_ModAssignment_2() { return cClass_ModAssignment_2; }

		//class_modification
		public RuleCall getClass_ModClass_modificationParserRuleCall_2_0() { return cClass_ModClass_modificationParserRuleCall_2_0; }
	}

	public class Element_redeclarationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "element_redeclaration");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cElement_redeclarationAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cRedeclareKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cEachKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Keyword cFinalKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Alternatives cAlternatives_4 = (Alternatives)cGroup.eContents().get(4);
		private final Alternatives cAlternatives_4_0 = (Alternatives)cAlternatives_4.eContents().get(0);
		private final Assignment cClass_DefAssignment_4_0_0 = (Assignment)cAlternatives_4_0.eContents().get(0);
		private final RuleCall cClass_DefClass_definitionParserRuleCall_4_0_0_0 = (RuleCall)cClass_DefAssignment_4_0_0.eContents().get(0);
		private final Assignment cComp_clause1Assignment_4_0_1 = (Assignment)cAlternatives_4_0.eContents().get(1);
		private final RuleCall cComp_clause1Component_clause1ParserRuleCall_4_0_1_0 = (RuleCall)cComp_clause1Assignment_4_0_1.eContents().get(0);
		private final Assignment cElement_RAssignment_4_1 = (Assignment)cAlternatives_4.eContents().get(1);
		private final RuleCall cElement_RElement_replaceableParserRuleCall_4_1_0 = (RuleCall)cElement_RAssignment_4_1.eContents().get(0);
		
		//////////////////////////////////////////Element Redeclaration///////////////
		//// TODO:  ( Class_Def=class_definition | Comp_clause1=component_clause1) does not work! Is it an error in the Modelica spec.?
		//element_redeclaration:
		//	{element_redeclaration} "redeclare" "each"? "final"? ((Class_Def=class_definition | Comp_clause1=component_clause1) |
		//	Element_R=element_replaceable);
		public ParserRule getRule() { return rule; }

		//{element_redeclaration} "redeclare" "each"? "final"? ((Class_Def=class_definition | Comp_clause1=component_clause1) |
		//Element_R=element_replaceable)
		public Group getGroup() { return cGroup; }

		//{element_redeclaration}
		public Action getElement_redeclarationAction_0() { return cElement_redeclarationAction_0; }

		//"redeclare"
		public Keyword getRedeclareKeyword_1() { return cRedeclareKeyword_1; }

		//"each"?
		public Keyword getEachKeyword_2() { return cEachKeyword_2; }

		//"final"?
		public Keyword getFinalKeyword_3() { return cFinalKeyword_3; }

		//(Class_Def=class_definition | Comp_clause1=component_clause1) | Element_R=element_replaceable
		public Alternatives getAlternatives_4() { return cAlternatives_4; }

		//Class_Def=class_definition | Comp_clause1=component_clause1
		public Alternatives getAlternatives_4_0() { return cAlternatives_4_0; }

		//Class_Def=class_definition
		public Assignment getClass_DefAssignment_4_0_0() { return cClass_DefAssignment_4_0_0; }

		//class_definition
		public RuleCall getClass_DefClass_definitionParserRuleCall_4_0_0_0() { return cClass_DefClass_definitionParserRuleCall_4_0_0_0; }

		//Comp_clause1=component_clause1
		public Assignment getComp_clause1Assignment_4_0_1() { return cComp_clause1Assignment_4_0_1; }

		//component_clause1
		public RuleCall getComp_clause1Component_clause1ParserRuleCall_4_0_1_0() { return cComp_clause1Component_clause1ParserRuleCall_4_0_1_0; }

		//Element_R=element_replaceable
		public Assignment getElement_RAssignment_4_1() { return cElement_RAssignment_4_1; }

		//element_replaceable
		public RuleCall getElement_RElement_replaceableParserRuleCall_4_1_0() { return cElement_RElement_replaceableParserRuleCall_4_1_0; }
	}

	public class Component_clauseElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "component_clause");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cType_prefixParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final RuleCall cType_specifierParserRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Assignment cArray_SubsAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cArray_SubsArray_subscriptsParserRuleCall_2_0 = (RuleCall)cArray_SubsAssignment_2.eContents().get(0);
		private final Assignment cCom_ListAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cCom_ListComponent_listParserRuleCall_3_0 = (RuleCall)cCom_ListAssignment_3.eContents().get(0);
		
		///////////////////////////////////////////component_clause///////////////////
		//component_clause:
		//	type_prefix type_specifier Array_Subs=array_subscripts? Com_List=component_list;
		public ParserRule getRule() { return rule; }

		//type_prefix type_specifier Array_Subs=array_subscripts? Com_List=component_list
		public Group getGroup() { return cGroup; }

		//type_prefix
		public RuleCall getType_prefixParserRuleCall_0() { return cType_prefixParserRuleCall_0; }

		//type_specifier
		public RuleCall getType_specifierParserRuleCall_1() { return cType_specifierParserRuleCall_1; }

		//Array_Subs=array_subscripts?
		public Assignment getArray_SubsAssignment_2() { return cArray_SubsAssignment_2; }

		//array_subscripts
		public RuleCall getArray_SubsArray_subscriptsParserRuleCall_2_0() { return cArray_SubsArray_subscriptsParserRuleCall_2_0; }

		//Com_List=component_list
		public Assignment getCom_ListAssignment_3() { return cCom_ListAssignment_3; }

		//component_list
		public RuleCall getCom_ListComponent_listParserRuleCall_3_0() { return cCom_ListComponent_listParserRuleCall_3_0; }
	}

	public class Component_listElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "component_list");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cComponent_declarationParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cCommaKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cComponent_DecAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cComponent_DecComponent_declarationParserRuleCall_1_1_0 = (RuleCall)cComponent_DecAssignment_1_1.eContents().get(0);
		
		//////////////////////////////////////////component_list//////////////////////
		//component_list:
		//	component_declaration ("," component_Dec+=component_declaration)*;
		public ParserRule getRule() { return rule; }

		//component_declaration ("," component_Dec+=component_declaration)*
		public Group getGroup() { return cGroup; }

		//component_declaration
		public RuleCall getComponent_declarationParserRuleCall_0() { return cComponent_declarationParserRuleCall_0; }

		//("," component_Dec+=component_declaration)*
		public Group getGroup_1() { return cGroup_1; }

		//","
		public Keyword getCommaKeyword_1_0() { return cCommaKeyword_1_0; }

		//component_Dec+=component_declaration
		public Assignment getComponent_DecAssignment_1_1() { return cComponent_DecAssignment_1_1; }

		//component_declaration
		public RuleCall getComponent_DecComponent_declarationParserRuleCall_1_1_0() { return cComponent_DecComponent_declarationParserRuleCall_1_1_0; }
	}

	public class Component_declarationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "component_declaration");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cDecAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cDecDeclarationParserRuleCall_0_0 = (RuleCall)cDecAssignment_0.eContents().get(0);
		private final Assignment cConditional_AttAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cConditional_AttConditional_attributeParserRuleCall_1_0 = (RuleCall)cConditional_AttAssignment_1.eContents().get(0);
		private final RuleCall cCommentParserRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		
		//component_declaration:
		//	Dec=declaration Conditional_Att=conditional_attribute? comment;
		public ParserRule getRule() { return rule; }

		//Dec=declaration Conditional_Att=conditional_attribute? comment
		public Group getGroup() { return cGroup; }

		//Dec=declaration
		public Assignment getDecAssignment_0() { return cDecAssignment_0; }

		//declaration
		public RuleCall getDecDeclarationParserRuleCall_0_0() { return cDecDeclarationParserRuleCall_0_0; }

		//Conditional_Att=conditional_attribute?
		public Assignment getConditional_AttAssignment_1() { return cConditional_AttAssignment_1; }

		//conditional_attribute
		public RuleCall getConditional_AttConditional_attributeParserRuleCall_1_0() { return cConditional_AttConditional_attributeParserRuleCall_1_0; }

		//comment
		public RuleCall getCommentParserRuleCall_2() { return cCommentParserRuleCall_2; }
	}

	public class Conditional_attributeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "conditional_attribute");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cIfKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cExprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_1_0 = (RuleCall)cExprAssignment_1.eContents().get(0);
		
		//conditional_attribute:
		//	"if" Expr=expression;
		public ParserRule getRule() { return rule; }

		//"if" Expr=expression
		public Group getGroup() { return cGroup; }

		//"if"
		public Keyword getIfKeyword_0() { return cIfKeyword_0; }

		//Expr=expression
		public Assignment getExprAssignment_1() { return cExprAssignment_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_1_0() { return cExprExpressionParserRuleCall_1_0; }
	}

	public class Component_clause1Elements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "component_clause1");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cType_prefixParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final RuleCall cType_specifierParserRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Assignment cCom_DecAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cCom_DecComponent_declaration1ParserRuleCall_2_0 = (RuleCall)cCom_DecAssignment_2.eContents().get(0);
		
		//////////////////////////////////////////component_clause1///////////////////
		//component_clause1:
		//	type_prefix type_specifier Com_Dec=component_declaration1;
		public ParserRule getRule() { return rule; }

		//type_prefix type_specifier Com_Dec=component_declaration1
		public Group getGroup() { return cGroup; }

		//type_prefix
		public RuleCall getType_prefixParserRuleCall_0() { return cType_prefixParserRuleCall_0; }

		//type_specifier
		public RuleCall getType_specifierParserRuleCall_1() { return cType_specifierParserRuleCall_1; }

		//Com_Dec=component_declaration1
		public Assignment getCom_DecAssignment_2() { return cCom_DecAssignment_2; }

		//component_declaration1
		public RuleCall getCom_DecComponent_declaration1ParserRuleCall_2_0() { return cCom_DecComponent_declaration1ParserRuleCall_2_0; }
	}

	public class Component_declaration1Elements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "component_declaration1");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cDeclarationParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final RuleCall cCommentParserRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		
		//component_declaration1:
		//	declaration comment;
		public ParserRule getRule() { return rule; }

		//declaration comment
		public Group getGroup() { return cGroup; }

		//declaration
		public RuleCall getDeclarationParserRuleCall_0() { return cDeclarationParserRuleCall_0; }

		//comment
		public RuleCall getCommentParserRuleCall_1() { return cCommentParserRuleCall_1; }
	}

	public class Type_specifierElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "type_specifier");
		private final Assignment cNameAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cNameNameParserRuleCall_0 = (RuleCall)cNameAssignment.eContents().get(0);
		
		//type_specifier:
		//	Name=name;
		public ParserRule getRule() { return rule; }

		//Name=name
		public Assignment getNameAssignment() { return cNameAssignment; }

		//name
		public RuleCall getNameNameParserRuleCall_0() { return cNameNameParserRuleCall_0; }
	}

	public class DeclarationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "declaration");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cDeclarationAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cRedeclaredComponentNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cRedeclaredComponentNameIDENTTerminalRuleCall_1_0 = (RuleCall)cRedeclaredComponentNameAssignment_1.eContents().get(0);
		private final Assignment cArraySubsAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cArraySubsArray_subscriptsParserRuleCall_2_0 = (RuleCall)cArraySubsAssignment_2.eContents().get(0);
		private final Assignment cModAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cModModificationParserRuleCall_3_0 = (RuleCall)cModAssignment_3.eContents().get(0);
		
		//////////////////////////////////////////Declaration/////////////////////////
		//declaration:
		//	{declaration} redeclaredComponentName=IDENT ArraySubs=array_subscripts? Mod=modification?;
		public ParserRule getRule() { return rule; }

		//{declaration} redeclaredComponentName=IDENT ArraySubs=array_subscripts? Mod=modification?
		public Group getGroup() { return cGroup; }

		//{declaration}
		public Action getDeclarationAction_0() { return cDeclarationAction_0; }

		//redeclaredComponentName=IDENT
		public Assignment getRedeclaredComponentNameAssignment_1() { return cRedeclaredComponentNameAssignment_1; }

		//IDENT
		public RuleCall getRedeclaredComponentNameIDENTTerminalRuleCall_1_0() { return cRedeclaredComponentNameIDENTTerminalRuleCall_1_0; }

		//ArraySubs=array_subscripts?
		public Assignment getArraySubsAssignment_2() { return cArraySubsAssignment_2; }

		//array_subscripts
		public RuleCall getArraySubsArray_subscriptsParserRuleCall_2_0() { return cArraySubsArray_subscriptsParserRuleCall_2_0; }

		//Mod=modification?
		public Assignment getModAssignment_3() { return cModAssignment_3; }

		//modification
		public RuleCall getModModificationParserRuleCall_3_0() { return cModModificationParserRuleCall_3_0; }
	}

	public class Class_definitionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "class_definition");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cEncapsulatedKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cPartialKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Alternatives cAlternatives_2 = (Alternatives)cGroup.eContents().get(2);
		private final Keyword cClassKeyword_2_0 = (Keyword)cAlternatives_2.eContents().get(0);
		private final Keyword cModelKeyword_2_1 = (Keyword)cAlternatives_2.eContents().get(1);
		private final Keyword cRecordKeyword_2_2 = (Keyword)cAlternatives_2.eContents().get(2);
		private final Keyword cBlockKeyword_2_3 = (Keyword)cAlternatives_2.eContents().get(3);
		private final Group cGroup_2_4 = (Group)cAlternatives_2.eContents().get(4);
		private final Keyword cExpandableKeyword_2_4_0 = (Keyword)cGroup_2_4.eContents().get(0);
		private final Keyword cConnectorKeyword_2_4_1 = (Keyword)cGroup_2_4.eContents().get(1);
		private final Keyword cTypeKeyword_2_5 = (Keyword)cAlternatives_2.eContents().get(5);
		private final Keyword cPackageKeyword_2_6 = (Keyword)cAlternatives_2.eContents().get(6);
		private final Keyword cFunctionKeyword_2_7 = (Keyword)cAlternatives_2.eContents().get(7);
		private final Keyword cOperatorKeyword_2_8 = (Keyword)cAlternatives_2.eContents().get(8);
		private final Group cGroup_2_9 = (Group)cAlternatives_2.eContents().get(9);
		private final Keyword cOperatorKeyword_2_9_0 = (Keyword)cGroup_2_9.eContents().get(0);
		private final Keyword cFunctionKeyword_2_9_1 = (Keyword)cGroup_2_9.eContents().get(1);
		private final Group cGroup_2_10 = (Group)cAlternatives_2.eContents().get(10);
		private final Keyword cOperatorKeyword_2_10_0 = (Keyword)cGroup_2_10.eContents().get(0);
		private final Keyword cRecordKeyword_2_10_1 = (Keyword)cGroup_2_10.eContents().get(1);
		private final RuleCall cClass_specifierParserRuleCall_3 = (RuleCall)cGroup.eContents().get(3);
		
		//////////////////////////////////////////Class Definition////////////////////
		//class_definition:
		//	"encapsulated"? "partial"? ("class" | "model" | "record" | "block" | "expandable"? "connector" | "type" | "package" |
		//	"function" | "operator" | "operator" "function" | "operator" "record") class_specifier;
		public ParserRule getRule() { return rule; }

		//"encapsulated"? "partial"? ("class" | "model" | "record" | "block" | "expandable"? "connector" | "type" | "package" |
		//"function" | "operator" | "operator" "function" | "operator" "record") class_specifier
		public Group getGroup() { return cGroup; }

		//"encapsulated"?
		public Keyword getEncapsulatedKeyword_0() { return cEncapsulatedKeyword_0; }

		//"partial"?
		public Keyword getPartialKeyword_1() { return cPartialKeyword_1; }

		//"class" | "model" | "record" | "block" | "expandable"? "connector" | "type" | "package" | "function" | "operator" |
		//"operator" "function" | "operator" "record"
		public Alternatives getAlternatives_2() { return cAlternatives_2; }

		//"class"
		public Keyword getClassKeyword_2_0() { return cClassKeyword_2_0; }

		//"model"
		public Keyword getModelKeyword_2_1() { return cModelKeyword_2_1; }

		//"record"
		public Keyword getRecordKeyword_2_2() { return cRecordKeyword_2_2; }

		//"block"
		public Keyword getBlockKeyword_2_3() { return cBlockKeyword_2_3; }

		//"expandable"? "connector"
		public Group getGroup_2_4() { return cGroup_2_4; }

		//"expandable"?
		public Keyword getExpandableKeyword_2_4_0() { return cExpandableKeyword_2_4_0; }

		//"connector"
		public Keyword getConnectorKeyword_2_4_1() { return cConnectorKeyword_2_4_1; }

		//"type"
		public Keyword getTypeKeyword_2_5() { return cTypeKeyword_2_5; }

		//"package"
		public Keyword getPackageKeyword_2_6() { return cPackageKeyword_2_6; }

		//"function"
		public Keyword getFunctionKeyword_2_7() { return cFunctionKeyword_2_7; }

		//"operator"
		public Keyword getOperatorKeyword_2_8() { return cOperatorKeyword_2_8; }

		//"operator" "function"
		public Group getGroup_2_9() { return cGroup_2_9; }

		//"operator"
		public Keyword getOperatorKeyword_2_9_0() { return cOperatorKeyword_2_9_0; }

		//"function"
		public Keyword getFunctionKeyword_2_9_1() { return cFunctionKeyword_2_9_1; }

		//"operator" "record"
		public Group getGroup_2_10() { return cGroup_2_10; }

		//"operator"
		public Keyword getOperatorKeyword_2_10_0() { return cOperatorKeyword_2_10_0; }

		//"record"
		public Keyword getRecordKeyword_2_10_1() { return cRecordKeyword_2_10_1; }

		//class_specifier
		public RuleCall getClass_specifierParserRuleCall_3() { return cClass_specifierParserRuleCall_3; }
	}

	public class Class_specifierElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "class_specifier");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cClass_specifierAction_0 = (Action)cGroup.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cAlternatives_1.eContents().get(0);
		private final RuleCall cIDENTTerminalRuleCall_1_0_0 = (RuleCall)cGroup_1_0.eContents().get(0);
		private final RuleCall cString_commentParserRuleCall_1_0_1 = (RuleCall)cGroup_1_0.eContents().get(1);
		private final Assignment cCompAssignment_1_0_2 = (Assignment)cGroup_1_0.eContents().get(2);
		private final RuleCall cCompCompositionParserRuleCall_1_0_2_0 = (RuleCall)cCompAssignment_1_0_2.eContents().get(0);
		private final Keyword cEndKeyword_1_0_3 = (Keyword)cGroup_1_0.eContents().get(3);
		private final RuleCall cIDENTTerminalRuleCall_1_0_4 = (RuleCall)cGroup_1_0.eContents().get(4);
		private final Group cGroup_1_1 = (Group)cAlternatives_1.eContents().get(1);
		private final RuleCall cIDENTTerminalRuleCall_1_1_0 = (RuleCall)cGroup_1_1.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_1_1 = (Keyword)cGroup_1_1.eContents().get(1);
		private final RuleCall cBase_prefixParserRuleCall_1_1_2 = (RuleCall)cGroup_1_1.eContents().get(2);
		private final Assignment cNameAssignment_1_1_3 = (Assignment)cGroup_1_1.eContents().get(3);
		private final RuleCall cNameNameParserRuleCall_1_1_3_0 = (RuleCall)cNameAssignment_1_1_3.eContents().get(0);
		private final Assignment cArray_SubsAssignment_1_1_4 = (Assignment)cGroup_1_1.eContents().get(4);
		private final RuleCall cArray_SubsArray_subscriptsParserRuleCall_1_1_4_0 = (RuleCall)cArray_SubsAssignment_1_1_4.eContents().get(0);
		private final Assignment cClass_modAssignment_1_1_5 = (Assignment)cGroup_1_1.eContents().get(5);
		private final RuleCall cClass_modClass_modificationParserRuleCall_1_1_5_0 = (RuleCall)cClass_modAssignment_1_1_5.eContents().get(0);
		private final RuleCall cCommentParserRuleCall_1_1_6 = (RuleCall)cGroup_1_1.eContents().get(6);
		private final Group cGroup_1_2 = (Group)cAlternatives_1.eContents().get(2);
		private final Assignment cVarAssignment_1_2_0 = (Assignment)cGroup_1_2.eContents().get(0);
		private final RuleCall cVarIDENTTerminalRuleCall_1_2_0_0 = (RuleCall)cVarAssignment_1_2_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_2_1 = (Keyword)cGroup_1_2.eContents().get(1);
		private final Keyword cEnumerationKeyword_1_2_2 = (Keyword)cGroup_1_2.eContents().get(2);
		private final Keyword cLeftParenthesisKeyword_1_2_3 = (Keyword)cGroup_1_2.eContents().get(3);
		private final Alternatives cAlternatives_1_2_4 = (Alternatives)cGroup_1_2.eContents().get(4);
		private final Assignment cEnum_listAssignment_1_2_4_0 = (Assignment)cAlternatives_1_2_4.eContents().get(0);
		private final RuleCall cEnum_listEnum_listParserRuleCall_1_2_4_0_0 = (RuleCall)cEnum_listAssignment_1_2_4_0.eContents().get(0);
		private final Keyword cColonKeyword_1_2_4_1 = (Keyword)cAlternatives_1_2_4.eContents().get(1);
		private final Keyword cRightParenthesisKeyword_1_2_5 = (Keyword)cGroup_1_2.eContents().get(5);
		private final RuleCall cCommentParserRuleCall_1_2_6 = (RuleCall)cGroup_1_2.eContents().get(6);
		private final Group cGroup_1_3 = (Group)cAlternatives_1.eContents().get(3);
		private final RuleCall cIDENTTerminalRuleCall_1_3_0 = (RuleCall)cGroup_1_3.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_3_1 = (Keyword)cGroup_1_3.eContents().get(1);
		private final Keyword cDerKeyword_1_3_2 = (Keyword)cGroup_1_3.eContents().get(2);
		private final Keyword cLeftParenthesisKeyword_1_3_3 = (Keyword)cGroup_1_3.eContents().get(3);
		private final Assignment cNameAssignment_1_3_4 = (Assignment)cGroup_1_3.eContents().get(4);
		private final RuleCall cNameNameParserRuleCall_1_3_4_0 = (RuleCall)cNameAssignment_1_3_4.eContents().get(0);
		private final Keyword cCommaKeyword_1_3_5 = (Keyword)cGroup_1_3.eContents().get(5);
		private final RuleCall cIDENTTerminalRuleCall_1_3_6 = (RuleCall)cGroup_1_3.eContents().get(6);
		private final Group cGroup_1_3_7 = (Group)cGroup_1_3.eContents().get(7);
		private final Keyword cCommaKeyword_1_3_7_0 = (Keyword)cGroup_1_3_7.eContents().get(0);
		private final RuleCall cIDENTTerminalRuleCall_1_3_7_1 = (RuleCall)cGroup_1_3_7.eContents().get(1);
		private final Keyword cRightParenthesisKeyword_1_3_8 = (Keyword)cGroup_1_3.eContents().get(8);
		private final RuleCall cCommentParserRuleCall_1_3_9 = (RuleCall)cGroup_1_3.eContents().get(9);
		private final Group cGroup_1_4 = (Group)cAlternatives_1.eContents().get(4);
		private final Keyword cExtendsKeyword_1_4_0 = (Keyword)cGroup_1_4.eContents().get(0);
		private final RuleCall cIDENTTerminalRuleCall_1_4_1 = (RuleCall)cGroup_1_4.eContents().get(1);
		private final Assignment cClass_modAssignment_1_4_2 = (Assignment)cGroup_1_4.eContents().get(2);
		private final RuleCall cClass_modClass_modificationParserRuleCall_1_4_2_0 = (RuleCall)cClass_modAssignment_1_4_2.eContents().get(0);
		private final RuleCall cString_commentParserRuleCall_1_4_3 = (RuleCall)cGroup_1_4.eContents().get(3);
		private final Assignment cCompAssignment_1_4_4 = (Assignment)cGroup_1_4.eContents().get(4);
		private final RuleCall cCompCompositionParserRuleCall_1_4_4_0 = (RuleCall)cCompAssignment_1_4_4.eContents().get(0);
		private final Keyword cEndKeyword_1_4_5 = (Keyword)cGroup_1_4.eContents().get(5);
		private final RuleCall cIDENTTerminalRuleCall_1_4_6 = (RuleCall)cGroup_1_4.eContents().get(6);
		
		//////////////////////////////////////////Class Specifier////////////////////
		//class_specifier:
		//	{class_specifier} (IDENT string_comment Comp=composition "end" IDENT | IDENT "=" base_prefix Name=name
		//	Array_Subs=array_subscripts? Class_mod=class_modification? comment | var+=IDENT "=" "enumeration" "("
		//	(Enum_list=enum_list? | ":") ")" comment | IDENT "=" "der" "(" Name=name "," IDENT ("," IDENT)* ")" comment |
		//	"extends" IDENT Class_mod=class_modification? string_comment Comp=composition "end" IDENT);
		public ParserRule getRule() { return rule; }

		//{class_specifier} (IDENT string_comment Comp=composition "end" IDENT | IDENT "=" base_prefix Name=name
		//Array_Subs=array_subscripts? Class_mod=class_modification? comment | var+=IDENT "=" "enumeration" "("
		//(Enum_list=enum_list? | ":") ")" comment | IDENT "=" "der" "(" Name=name "," IDENT ("," IDENT)* ")" comment | "extends"
		//IDENT Class_mod=class_modification? string_comment Comp=composition "end" IDENT)
		public Group getGroup() { return cGroup; }

		//{class_specifier}
		public Action getClass_specifierAction_0() { return cClass_specifierAction_0; }

		//IDENT string_comment Comp=composition "end" IDENT | IDENT "=" base_prefix Name=name Array_Subs=array_subscripts?
		//Class_mod=class_modification? comment | var+=IDENT "=" "enumeration" "(" (Enum_list=enum_list? | ":") ")" comment |
		//IDENT "=" "der" "(" Name=name "," IDENT ("," IDENT)* ")" comment | "extends" IDENT Class_mod=class_modification?
		//string_comment Comp=composition "end" IDENT
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//IDENT string_comment Comp=composition "end" IDENT
		public Group getGroup_1_0() { return cGroup_1_0; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_0_0() { return cIDENTTerminalRuleCall_1_0_0; }

		//string_comment
		public RuleCall getString_commentParserRuleCall_1_0_1() { return cString_commentParserRuleCall_1_0_1; }

		//Comp=composition
		public Assignment getCompAssignment_1_0_2() { return cCompAssignment_1_0_2; }

		//composition
		public RuleCall getCompCompositionParserRuleCall_1_0_2_0() { return cCompCompositionParserRuleCall_1_0_2_0; }

		//"end"
		public Keyword getEndKeyword_1_0_3() { return cEndKeyword_1_0_3; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_0_4() { return cIDENTTerminalRuleCall_1_0_4; }

		//IDENT "=" base_prefix Name=name Array_Subs=array_subscripts? Class_mod=class_modification? comment
		public Group getGroup_1_1() { return cGroup_1_1; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_1_0() { return cIDENTTerminalRuleCall_1_1_0; }

		//"="
		public Keyword getEqualsSignKeyword_1_1_1() { return cEqualsSignKeyword_1_1_1; }

		//base_prefix
		public RuleCall getBase_prefixParserRuleCall_1_1_2() { return cBase_prefixParserRuleCall_1_1_2; }

		//Name=name
		public Assignment getNameAssignment_1_1_3() { return cNameAssignment_1_1_3; }

		//name
		public RuleCall getNameNameParserRuleCall_1_1_3_0() { return cNameNameParserRuleCall_1_1_3_0; }

		//Array_Subs=array_subscripts?
		public Assignment getArray_SubsAssignment_1_1_4() { return cArray_SubsAssignment_1_1_4; }

		//array_subscripts
		public RuleCall getArray_SubsArray_subscriptsParserRuleCall_1_1_4_0() { return cArray_SubsArray_subscriptsParserRuleCall_1_1_4_0; }

		//Class_mod=class_modification?
		public Assignment getClass_modAssignment_1_1_5() { return cClass_modAssignment_1_1_5; }

		//class_modification
		public RuleCall getClass_modClass_modificationParserRuleCall_1_1_5_0() { return cClass_modClass_modificationParserRuleCall_1_1_5_0; }

		//comment
		public RuleCall getCommentParserRuleCall_1_1_6() { return cCommentParserRuleCall_1_1_6; }

		//var+=IDENT "=" "enumeration" "(" (Enum_list=enum_list? | ":") ")" comment
		public Group getGroup_1_2() { return cGroup_1_2; }

		//var+=IDENT
		public Assignment getVarAssignment_1_2_0() { return cVarAssignment_1_2_0; }

		//IDENT
		public RuleCall getVarIDENTTerminalRuleCall_1_2_0_0() { return cVarIDENTTerminalRuleCall_1_2_0_0; }

		//"="
		public Keyword getEqualsSignKeyword_1_2_1() { return cEqualsSignKeyword_1_2_1; }

		//"enumeration"
		public Keyword getEnumerationKeyword_1_2_2() { return cEnumerationKeyword_1_2_2; }

		//"("
		public Keyword getLeftParenthesisKeyword_1_2_3() { return cLeftParenthesisKeyword_1_2_3; }

		//Enum_list=enum_list? | ":"
		public Alternatives getAlternatives_1_2_4() { return cAlternatives_1_2_4; }

		//Enum_list=enum_list?
		public Assignment getEnum_listAssignment_1_2_4_0() { return cEnum_listAssignment_1_2_4_0; }

		//enum_list
		public RuleCall getEnum_listEnum_listParserRuleCall_1_2_4_0_0() { return cEnum_listEnum_listParserRuleCall_1_2_4_0_0; }

		//":"
		public Keyword getColonKeyword_1_2_4_1() { return cColonKeyword_1_2_4_1; }

		//")"
		public Keyword getRightParenthesisKeyword_1_2_5() { return cRightParenthesisKeyword_1_2_5; }

		//comment
		public RuleCall getCommentParserRuleCall_1_2_6() { return cCommentParserRuleCall_1_2_6; }

		//IDENT "=" "der" "(" Name=name "," IDENT ("," IDENT)* ")" comment
		public Group getGroup_1_3() { return cGroup_1_3; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_3_0() { return cIDENTTerminalRuleCall_1_3_0; }

		//"="
		public Keyword getEqualsSignKeyword_1_3_1() { return cEqualsSignKeyword_1_3_1; }

		//"der"
		public Keyword getDerKeyword_1_3_2() { return cDerKeyword_1_3_2; }

		//"("
		public Keyword getLeftParenthesisKeyword_1_3_3() { return cLeftParenthesisKeyword_1_3_3; }

		//Name=name
		public Assignment getNameAssignment_1_3_4() { return cNameAssignment_1_3_4; }

		//name
		public RuleCall getNameNameParserRuleCall_1_3_4_0() { return cNameNameParserRuleCall_1_3_4_0; }

		//","
		public Keyword getCommaKeyword_1_3_5() { return cCommaKeyword_1_3_5; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_3_6() { return cIDENTTerminalRuleCall_1_3_6; }

		//("," IDENT)*
		public Group getGroup_1_3_7() { return cGroup_1_3_7; }

		//","
		public Keyword getCommaKeyword_1_3_7_0() { return cCommaKeyword_1_3_7_0; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_3_7_1() { return cIDENTTerminalRuleCall_1_3_7_1; }

		//")"
		public Keyword getRightParenthesisKeyword_1_3_8() { return cRightParenthesisKeyword_1_3_8; }

		//comment
		public RuleCall getCommentParserRuleCall_1_3_9() { return cCommentParserRuleCall_1_3_9; }

		//"extends" IDENT Class_mod=class_modification? string_comment Comp=composition "end" IDENT
		public Group getGroup_1_4() { return cGroup_1_4; }

		//"extends"
		public Keyword getExtendsKeyword_1_4_0() { return cExtendsKeyword_1_4_0; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_4_1() { return cIDENTTerminalRuleCall_1_4_1; }

		//Class_mod=class_modification?
		public Assignment getClass_modAssignment_1_4_2() { return cClass_modAssignment_1_4_2; }

		//class_modification
		public RuleCall getClass_modClass_modificationParserRuleCall_1_4_2_0() { return cClass_modClass_modificationParserRuleCall_1_4_2_0; }

		//string_comment
		public RuleCall getString_commentParserRuleCall_1_4_3() { return cString_commentParserRuleCall_1_4_3; }

		//Comp=composition
		public Assignment getCompAssignment_1_4_4() { return cCompAssignment_1_4_4; }

		//composition
		public RuleCall getCompCompositionParserRuleCall_1_4_4_0() { return cCompCompositionParserRuleCall_1_4_4_0; }

		//"end"
		public Keyword getEndKeyword_1_4_5() { return cEndKeyword_1_4_5; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_4_6() { return cIDENTTerminalRuleCall_1_4_6; }
	}

	public class Base_prefixElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "base_prefix");
		private final RuleCall cType_prefixParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		//base_prefix:
		//	type_prefix;
		public ParserRule getRule() { return rule; }

		//type_prefix
		public RuleCall getType_prefixParserRuleCall() { return cType_prefixParserRuleCall; }
	}

	public class Type_prefixElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "type_prefix");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Alternatives cAlternatives_0 = (Alternatives)cGroup.eContents().get(0);
		private final Keyword cFlowKeyword_0_0 = (Keyword)cAlternatives_0.eContents().get(0);
		private final Keyword cStreamKeyword_0_1 = (Keyword)cAlternatives_0.eContents().get(1);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Keyword cDiscreteKeyword_1_0 = (Keyword)cAlternatives_1.eContents().get(0);
		private final Keyword cParameterKeyword_1_1 = (Keyword)cAlternatives_1.eContents().get(1);
		private final Keyword cConstantKeyword_1_2 = (Keyword)cAlternatives_1.eContents().get(2);
		private final Alternatives cAlternatives_2 = (Alternatives)cGroup.eContents().get(2);
		private final Keyword cInputKeyword_2_0 = (Keyword)cAlternatives_2.eContents().get(0);
		private final Keyword cOutputKeyword_2_1 = (Keyword)cAlternatives_2.eContents().get(1);
		
		//type_prefix:
		//	("flow" | "stream")? ("discrete" | "parameter" | "constant")? ("input" | "output")?;
		public ParserRule getRule() { return rule; }

		//("flow" | "stream")? ("discrete" | "parameter" | "constant")? ("input" | "output")?
		public Group getGroup() { return cGroup; }

		//("flow" | "stream")?
		public Alternatives getAlternatives_0() { return cAlternatives_0; }

		//"flow"
		public Keyword getFlowKeyword_0_0() { return cFlowKeyword_0_0; }

		//"stream"
		public Keyword getStreamKeyword_0_1() { return cStreamKeyword_0_1; }

		//("discrete" | "parameter" | "constant")?
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//"discrete"
		public Keyword getDiscreteKeyword_1_0() { return cDiscreteKeyword_1_0; }

		//"parameter"
		public Keyword getParameterKeyword_1_1() { return cParameterKeyword_1_1; }

		//"constant"
		public Keyword getConstantKeyword_1_2() { return cConstantKeyword_1_2; }

		//("input" | "output")?
		public Alternatives getAlternatives_2() { return cAlternatives_2; }

		//"input"
		public Keyword getInputKeyword_2_0() { return cInputKeyword_2_0; }

		//"output"
		public Keyword getOutputKeyword_2_1() { return cOutputKeyword_2_1; }
	}

	public class Enum_listElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "enum_list");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cEnum_listAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cEnnum_LitAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cEnnum_LitEnumeration_literalParserRuleCall_1_0 = (RuleCall)cEnnum_LitAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cCommaKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cE_literalAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cE_literalEnumeration_literalParserRuleCall_2_1_0 = (RuleCall)cE_literalAssignment_2_1.eContents().get(0);
		
		//////////////////////////////////////////Enumerations///////////////////////
		//enum_list:
		//	{enum_list} Ennum_Lit=enumeration_literal ("," E_literal+=enumeration_literal)*;
		public ParserRule getRule() { return rule; }

		//{enum_list} Ennum_Lit=enumeration_literal ("," E_literal+=enumeration_literal)*
		public Group getGroup() { return cGroup; }

		//{enum_list}
		public Action getEnum_listAction_0() { return cEnum_listAction_0; }

		//Ennum_Lit=enumeration_literal
		public Assignment getEnnum_LitAssignment_1() { return cEnnum_LitAssignment_1; }

		//enumeration_literal
		public RuleCall getEnnum_LitEnumeration_literalParserRuleCall_1_0() { return cEnnum_LitEnumeration_literalParserRuleCall_1_0; }

		//("," E_literal+=enumeration_literal)*
		public Group getGroup_2() { return cGroup_2; }

		//","
		public Keyword getCommaKeyword_2_0() { return cCommaKeyword_2_0; }

		//E_literal+=enumeration_literal
		public Assignment getE_literalAssignment_2_1() { return cE_literalAssignment_2_1; }

		//enumeration_literal
		public RuleCall getE_literalEnumeration_literalParserRuleCall_2_1_0() { return cE_literalEnumeration_literalParserRuleCall_2_1_0; }
	}

	public class Enumeration_literalElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "enumeration_literal");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cIDENTTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final RuleCall cCommentParserRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		
		//enumeration_literal:
		//	IDENT comment;
		public ParserRule getRule() { return rule; }

		//IDENT comment
		public Group getGroup() { return cGroup; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_0() { return cIDENTTerminalRuleCall_0; }

		//comment
		public RuleCall getCommentParserRuleCall_1() { return cCommentParserRuleCall_1; }
	}

	public class CompositionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "composition");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cE_List_InitialAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cE_List_InitialElement_listParserRuleCall_0_0 = (RuleCall)cE_List_InitialAssignment_0.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cAlternatives_1.eContents().get(0);
		private final Keyword cPublicKeyword_1_0_0 = (Keyword)cGroup_1_0.eContents().get(0);
		private final Assignment cE_List_PublicAssignment_1_0_1 = (Assignment)cGroup_1_0.eContents().get(1);
		private final RuleCall cE_List_PublicElement_listParserRuleCall_1_0_1_0 = (RuleCall)cE_List_PublicAssignment_1_0_1.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cAlternatives_1.eContents().get(1);
		private final Keyword cProtectedKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final Assignment cE_List_ProtectedAssignment_1_1_1 = (Assignment)cGroup_1_1.eContents().get(1);
		private final RuleCall cE_List_ProtectedElement_listParserRuleCall_1_1_1_0 = (RuleCall)cE_List_ProtectedAssignment_1_1_1.eContents().get(0);
		private final Assignment cEqn_sectionAssignment_1_2 = (Assignment)cAlternatives_1.eContents().get(2);
		private final RuleCall cEqn_sectionEquation_sectionParserRuleCall_1_2_0 = (RuleCall)cEqn_sectionAssignment_1_2.eContents().get(0);
		private final Assignment cAlg_sectionAssignment_1_3 = (Assignment)cAlternatives_1.eContents().get(3);
		private final RuleCall cAlg_sectionAlgorithm_sectionParserRuleCall_1_3_0 = (RuleCall)cAlg_sectionAssignment_1_3.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cExternalKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cLang_SpecAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cLang_SpecLanguage_specificationParserRuleCall_2_1_0 = (RuleCall)cLang_SpecAssignment_2_1.eContents().get(0);
		private final Assignment cExternal_F_CAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cExternal_F_CExternal_function_callParserRuleCall_2_2_0 = (RuleCall)cExternal_F_CAssignment_2_2.eContents().get(0);
		private final Assignment cAnnotation1Assignment_2_3 = (Assignment)cGroup_2.eContents().get(3);
		private final RuleCall cAnnotation1AnnotationParserRuleCall_2_3_0 = (RuleCall)cAnnotation1Assignment_2_3.eContents().get(0);
		private final Keyword cSemicolonKeyword_2_4 = (Keyword)cGroup_2.eContents().get(4);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cAnnotation2Assignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cAnnotation2AnnotationParserRuleCall_3_0_0 = (RuleCall)cAnnotation2Assignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		
		//////////////////////////////////////////Composition////////////////////////
		//composition:
		//	E_List_Initial=element_list ("public" E_List_Public+=element_list | "protected" E_List_Protected+=element_list |
		//	Eqn_section+=equation_section | Alg_section+=algorithm_section)* ("external" Lang_Spec=language_specification?
		//	External_F_C=external_function_call? Annotation1=annotation? ";")? (Annotation2=annotation ";")?;
		public ParserRule getRule() { return rule; }

		//E_List_Initial=element_list ("public" E_List_Public+=element_list | "protected" E_List_Protected+=element_list |
		//Eqn_section+=equation_section | Alg_section+=algorithm_section)* ("external" Lang_Spec=language_specification?
		//External_F_C=external_function_call? Annotation1=annotation? ";")? (Annotation2=annotation ";")?
		public Group getGroup() { return cGroup; }

		//E_List_Initial=element_list
		public Assignment getE_List_InitialAssignment_0() { return cE_List_InitialAssignment_0; }

		//element_list
		public RuleCall getE_List_InitialElement_listParserRuleCall_0_0() { return cE_List_InitialElement_listParserRuleCall_0_0; }

		//("public" E_List_Public+=element_list | "protected" E_List_Protected+=element_list | Eqn_section+=equation_section |
		//Alg_section+=algorithm_section)*
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//"public" E_List_Public+=element_list
		public Group getGroup_1_0() { return cGroup_1_0; }

		//"public"
		public Keyword getPublicKeyword_1_0_0() { return cPublicKeyword_1_0_0; }

		//E_List_Public+=element_list
		public Assignment getE_List_PublicAssignment_1_0_1() { return cE_List_PublicAssignment_1_0_1; }

		//element_list
		public RuleCall getE_List_PublicElement_listParserRuleCall_1_0_1_0() { return cE_List_PublicElement_listParserRuleCall_1_0_1_0; }

		//"protected" E_List_Protected+=element_list
		public Group getGroup_1_1() { return cGroup_1_1; }

		//"protected"
		public Keyword getProtectedKeyword_1_1_0() { return cProtectedKeyword_1_1_0; }

		//E_List_Protected+=element_list
		public Assignment getE_List_ProtectedAssignment_1_1_1() { return cE_List_ProtectedAssignment_1_1_1; }

		//element_list
		public RuleCall getE_List_ProtectedElement_listParserRuleCall_1_1_1_0() { return cE_List_ProtectedElement_listParserRuleCall_1_1_1_0; }

		//Eqn_section+=equation_section
		public Assignment getEqn_sectionAssignment_1_2() { return cEqn_sectionAssignment_1_2; }

		//equation_section
		public RuleCall getEqn_sectionEquation_sectionParserRuleCall_1_2_0() { return cEqn_sectionEquation_sectionParserRuleCall_1_2_0; }

		//Alg_section+=algorithm_section
		public Assignment getAlg_sectionAssignment_1_3() { return cAlg_sectionAssignment_1_3; }

		//algorithm_section
		public RuleCall getAlg_sectionAlgorithm_sectionParserRuleCall_1_3_0() { return cAlg_sectionAlgorithm_sectionParserRuleCall_1_3_0; }

		//("external" Lang_Spec=language_specification? External_F_C=external_function_call? Annotation1=annotation? ";")?
		public Group getGroup_2() { return cGroup_2; }

		//"external"
		public Keyword getExternalKeyword_2_0() { return cExternalKeyword_2_0; }

		//Lang_Spec=language_specification?
		public Assignment getLang_SpecAssignment_2_1() { return cLang_SpecAssignment_2_1; }

		//language_specification
		public RuleCall getLang_SpecLanguage_specificationParserRuleCall_2_1_0() { return cLang_SpecLanguage_specificationParserRuleCall_2_1_0; }

		//External_F_C=external_function_call?
		public Assignment getExternal_F_CAssignment_2_2() { return cExternal_F_CAssignment_2_2; }

		//external_function_call
		public RuleCall getExternal_F_CExternal_function_callParserRuleCall_2_2_0() { return cExternal_F_CExternal_function_callParserRuleCall_2_2_0; }

		//Annotation1=annotation?
		public Assignment getAnnotation1Assignment_2_3() { return cAnnotation1Assignment_2_3; }

		//annotation
		public RuleCall getAnnotation1AnnotationParserRuleCall_2_3_0() { return cAnnotation1AnnotationParserRuleCall_2_3_0; }

		//";"
		public Keyword getSemicolonKeyword_2_4() { return cSemicolonKeyword_2_4; }

		//(Annotation2=annotation ";")?
		public Group getGroup_3() { return cGroup_3; }

		//Annotation2=annotation
		public Assignment getAnnotation2Assignment_3_0() { return cAnnotation2Assignment_3_0; }

		//annotation
		public RuleCall getAnnotation2AnnotationParserRuleCall_3_0_0() { return cAnnotation2AnnotationParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }
	}

	public class Element_listElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "element_list");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cElement_listAction_0 = (Action)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cElementAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final RuleCall cElementElementParserRuleCall_1_0_0 = (RuleCall)cElementAssignment_1_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		
		//////////////////////////////////////////Element////////////////////////////
		//element_list:
		//	{element_list} (Element+=element ";")*;
		public ParserRule getRule() { return rule; }

		//{element_list} (Element+=element ";")*
		public Group getGroup() { return cGroup; }

		//{element_list}
		public Action getElement_listAction_0() { return cElement_listAction_0; }

		//(Element+=element ";")*
		public Group getGroup_1() { return cGroup_1; }

		//Element+=element
		public Assignment getElementAssignment_1_0() { return cElementAssignment_1_0; }

		//element
		public RuleCall getElementElementParserRuleCall_1_0_0() { return cElementElementParserRuleCall_1_0_0; }

		//";"
		public Keyword getSemicolonKeyword_1_1() { return cSemicolonKeyword_1_1; }
	}

	public class ElementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "element");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cImport_clauseParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cExtends_clauseParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final Group cGroup_2 = (Group)cAlternatives.eContents().get(2);
		private final Keyword cRedeclareKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cFinalKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Keyword cInnerKeyword_2_2 = (Keyword)cGroup_2.eContents().get(2);
		private final Keyword cOuterKeyword_2_3 = (Keyword)cGroup_2.eContents().get(3);
		private final Alternatives cAlternatives_2_4 = (Alternatives)cGroup_2.eContents().get(4);
		private final Alternatives cAlternatives_2_4_0 = (Alternatives)cAlternatives_2_4.eContents().get(0);
		private final Assignment cClass_DefAssignment_2_4_0_0 = (Assignment)cAlternatives_2_4_0.eContents().get(0);
		private final RuleCall cClass_DefClass_definitionParserRuleCall_2_4_0_0_0 = (RuleCall)cClass_DefAssignment_2_4_0_0.eContents().get(0);
		private final Assignment cComp_ClauseAssignment_2_4_0_1 = (Assignment)cAlternatives_2_4_0.eContents().get(1);
		private final RuleCall cComp_ClauseComponent_clauseParserRuleCall_2_4_0_1_0 = (RuleCall)cComp_ClauseAssignment_2_4_0_1.eContents().get(0);
		private final Group cGroup_2_4_1 = (Group)cAlternatives_2_4.eContents().get(1);
		private final Keyword cReplaceableKeyword_2_4_1_0 = (Keyword)cGroup_2_4_1.eContents().get(0);
		private final Alternatives cAlternatives_2_4_1_1 = (Alternatives)cGroup_2_4_1.eContents().get(1);
		private final Assignment cClass_DefAssignment_2_4_1_1_0 = (Assignment)cAlternatives_2_4_1_1.eContents().get(0);
		private final RuleCall cClass_DefClass_definitionParserRuleCall_2_4_1_1_0_0 = (RuleCall)cClass_DefAssignment_2_4_1_1_0.eContents().get(0);
		private final Assignment cComp_ClauseAssignment_2_4_1_1_1 = (Assignment)cAlternatives_2_4_1_1.eContents().get(1);
		private final RuleCall cComp_ClauseComponent_clauseParserRuleCall_2_4_1_1_1_0 = (RuleCall)cComp_ClauseAssignment_2_4_1_1_1.eContents().get(0);
		private final Group cGroup_2_4_1_2 = (Group)cGroup_2_4_1.eContents().get(2);
		private final Assignment cConstrain_ClauseAssignment_2_4_1_2_0 = (Assignment)cGroup_2_4_1_2.eContents().get(0);
		private final RuleCall cConstrain_ClauseConstraining_clauseParserRuleCall_2_4_1_2_0_0 = (RuleCall)cConstrain_ClauseAssignment_2_4_1_2_0.eContents().get(0);
		private final RuleCall cCommentParserRuleCall_2_4_1_2_1 = (RuleCall)cGroup_2_4_1_2.eContents().get(1);
		
		//element:
		//	import_clause | extends_clause | "redeclare"? "final"? "inner"? "outer"? ((Class_Def=class_definition |
		//	Comp_Clause=component_clause) | "replaceable" (Class_Def=class_definition | Comp_Clause=component_clause)
		//	(Constrain_Clause=constraining_clause comment)?);
		public ParserRule getRule() { return rule; }

		//import_clause | extends_clause | "redeclare"? "final"? "inner"? "outer"? ((Class_Def=class_definition |
		//Comp_Clause=component_clause) | "replaceable" (Class_Def=class_definition | Comp_Clause=component_clause)
		//(Constrain_Clause=constraining_clause comment)?)
		public Alternatives getAlternatives() { return cAlternatives; }

		//import_clause
		public RuleCall getImport_clauseParserRuleCall_0() { return cImport_clauseParserRuleCall_0; }

		//extends_clause
		public RuleCall getExtends_clauseParserRuleCall_1() { return cExtends_clauseParserRuleCall_1; }

		//"redeclare"? "final"? "inner"? "outer"? ((Class_Def=class_definition | Comp_Clause=component_clause) | "replaceable"
		//(Class_Def=class_definition | Comp_Clause=component_clause) (Constrain_Clause=constraining_clause comment)?)
		public Group getGroup_2() { return cGroup_2; }

		//"redeclare"?
		public Keyword getRedeclareKeyword_2_0() { return cRedeclareKeyword_2_0; }

		//"final"?
		public Keyword getFinalKeyword_2_1() { return cFinalKeyword_2_1; }

		//"inner"?
		public Keyword getInnerKeyword_2_2() { return cInnerKeyword_2_2; }

		//"outer"?
		public Keyword getOuterKeyword_2_3() { return cOuterKeyword_2_3; }

		//(Class_Def=class_definition | Comp_Clause=component_clause) | "replaceable" (Class_Def=class_definition |
		//Comp_Clause=component_clause) (Constrain_Clause=constraining_clause comment)?
		public Alternatives getAlternatives_2_4() { return cAlternatives_2_4; }

		//Class_Def=class_definition | Comp_Clause=component_clause
		public Alternatives getAlternatives_2_4_0() { return cAlternatives_2_4_0; }

		//Class_Def=class_definition
		public Assignment getClass_DefAssignment_2_4_0_0() { return cClass_DefAssignment_2_4_0_0; }

		//class_definition
		public RuleCall getClass_DefClass_definitionParserRuleCall_2_4_0_0_0() { return cClass_DefClass_definitionParserRuleCall_2_4_0_0_0; }

		//Comp_Clause=component_clause
		public Assignment getComp_ClauseAssignment_2_4_0_1() { return cComp_ClauseAssignment_2_4_0_1; }

		//component_clause
		public RuleCall getComp_ClauseComponent_clauseParserRuleCall_2_4_0_1_0() { return cComp_ClauseComponent_clauseParserRuleCall_2_4_0_1_0; }

		//"replaceable" (Class_Def=class_definition | Comp_Clause=component_clause) (Constrain_Clause=constraining_clause
		//comment)?
		public Group getGroup_2_4_1() { return cGroup_2_4_1; }

		//"replaceable"
		public Keyword getReplaceableKeyword_2_4_1_0() { return cReplaceableKeyword_2_4_1_0; }

		//Class_Def=class_definition | Comp_Clause=component_clause
		public Alternatives getAlternatives_2_4_1_1() { return cAlternatives_2_4_1_1; }

		//Class_Def=class_definition
		public Assignment getClass_DefAssignment_2_4_1_1_0() { return cClass_DefAssignment_2_4_1_1_0; }

		//class_definition
		public RuleCall getClass_DefClass_definitionParserRuleCall_2_4_1_1_0_0() { return cClass_DefClass_definitionParserRuleCall_2_4_1_1_0_0; }

		//Comp_Clause=component_clause
		public Assignment getComp_ClauseAssignment_2_4_1_1_1() { return cComp_ClauseAssignment_2_4_1_1_1; }

		//component_clause
		public RuleCall getComp_ClauseComponent_clauseParserRuleCall_2_4_1_1_1_0() { return cComp_ClauseComponent_clauseParserRuleCall_2_4_1_1_1_0; }

		//(Constrain_Clause=constraining_clause comment)?
		public Group getGroup_2_4_1_2() { return cGroup_2_4_1_2; }

		//Constrain_Clause=constraining_clause
		public Assignment getConstrain_ClauseAssignment_2_4_1_2_0() { return cConstrain_ClauseAssignment_2_4_1_2_0; }

		//constraining_clause
		public RuleCall getConstrain_ClauseConstraining_clauseParserRuleCall_2_4_1_2_0_0() { return cConstrain_ClauseConstraining_clauseParserRuleCall_2_4_1_2_0_0; }

		//comment
		public RuleCall getCommentParserRuleCall_2_4_1_2_1() { return cCommentParserRuleCall_2_4_1_2_1; }
	}

	public class Equation_sectionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "equation_section");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cEquation_sectionAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cInitialKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cEquationKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cEqnAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cEqnEquationParserRuleCall_3_0_0 = (RuleCall)cEqnAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		
		//////////////////////////////////////////Equation///////////////////////////
		//equation_section:
		//	{equation_section} "initial"? "equation" (Eqn+=equation ";")*;
		public ParserRule getRule() { return rule; }

		//{equation_section} "initial"? "equation" (Eqn+=equation ";")*
		public Group getGroup() { return cGroup; }

		//{equation_section}
		public Action getEquation_sectionAction_0() { return cEquation_sectionAction_0; }

		//"initial"?
		public Keyword getInitialKeyword_1() { return cInitialKeyword_1; }

		//"equation"
		public Keyword getEquationKeyword_2() { return cEquationKeyword_2; }

		//(Eqn+=equation ";")*
		public Group getGroup_3() { return cGroup_3; }

		//Eqn+=equation
		public Assignment getEqnAssignment_3_0() { return cEqnAssignment_3_0; }

		//equation
		public RuleCall getEqnEquationParserRuleCall_3_0_0() { return cEqnEquationParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }
	}

	public class EquationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "equation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Alternatives cAlternatives_0 = (Alternatives)cGroup.eContents().get(0);
		private final Group cGroup_0_0 = (Group)cAlternatives_0.eContents().get(0);
		private final Assignment cSimAssignment_0_0_0 = (Assignment)cGroup_0_0.eContents().get(0);
		private final RuleCall cSimSimple_expressionParserRuleCall_0_0_0_0 = (RuleCall)cSimAssignment_0_0_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_0_0_1 = (Keyword)cGroup_0_0.eContents().get(1);
		private final Assignment cExprAssignment_0_0_2 = (Assignment)cGroup_0_0.eContents().get(2);
		private final RuleCall cExprExpressionParserRuleCall_0_0_2_0 = (RuleCall)cExprAssignment_0_0_2.eContents().get(0);
		private final Assignment cIF_EQNAssignment_0_1 = (Assignment)cAlternatives_0.eContents().get(1);
		private final RuleCall cIF_EQNIf_equationParserRuleCall_0_1_0 = (RuleCall)cIF_EQNAssignment_0_1.eContents().get(0);
		private final Assignment cFOR_EQNAssignment_0_2 = (Assignment)cAlternatives_0.eContents().get(2);
		private final RuleCall cFOR_EQNFor_equationParserRuleCall_0_2_0 = (RuleCall)cFOR_EQNAssignment_0_2.eContents().get(0);
		private final Assignment cConAssignment_0_3 = (Assignment)cAlternatives_0.eContents().get(3);
		private final RuleCall cConConnect_clauseParserRuleCall_0_3_0 = (RuleCall)cConAssignment_0_3.eContents().get(0);
		private final Assignment cWHEN_EQNAssignment_0_4 = (Assignment)cAlternatives_0.eContents().get(4);
		private final RuleCall cWHEN_EQNWhen_equationParserRuleCall_0_4_0 = (RuleCall)cWHEN_EQNAssignment_0_4.eContents().get(0);
		private final Assignment cCommentAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cCommentCommentParserRuleCall_1_0 = (RuleCall)cCommentAssignment_1.eContents().get(0);
		
		//equation:
		//	(sim=simple_expression "=" expr=expression //| Fun_ID=IDENT fun=function_call_args
		//	| IF_EQN=if_equation | FOR_EQN=for_equation | Con=connect_clause | WHEN_EQN=when_equation) Comment=comment;
		public ParserRule getRule() { return rule; }

		//(sim=simple_expression "=" expr=expression //| Fun_ID=IDENT fun=function_call_args
		//| IF_EQN=if_equation | FOR_EQN=for_equation | Con=connect_clause | WHEN_EQN=when_equation) Comment=comment
		public Group getGroup() { return cGroup; }

		//sim=simple_expression "=" expr=expression //| Fun_ID=IDENT fun=function_call_args
		//| IF_EQN=if_equation | FOR_EQN=for_equation | Con=connect_clause | WHEN_EQN=when_equation
		public Alternatives getAlternatives_0() { return cAlternatives_0; }

		//sim=simple_expression "=" expr=expression
		public Group getGroup_0_0() { return cGroup_0_0; }

		//sim=simple_expression
		public Assignment getSimAssignment_0_0_0() { return cSimAssignment_0_0_0; }

		//simple_expression
		public RuleCall getSimSimple_expressionParserRuleCall_0_0_0_0() { return cSimSimple_expressionParserRuleCall_0_0_0_0; }

		//"="
		public Keyword getEqualsSignKeyword_0_0_1() { return cEqualsSignKeyword_0_0_1; }

		//expr=expression
		public Assignment getExprAssignment_0_0_2() { return cExprAssignment_0_0_2; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_0_0_2_0() { return cExprExpressionParserRuleCall_0_0_2_0; }

		//IF_EQN=if_equation
		public Assignment getIF_EQNAssignment_0_1() { return cIF_EQNAssignment_0_1; }

		//if_equation
		public RuleCall getIF_EQNIf_equationParserRuleCall_0_1_0() { return cIF_EQNIf_equationParserRuleCall_0_1_0; }

		//FOR_EQN=for_equation
		public Assignment getFOR_EQNAssignment_0_2() { return cFOR_EQNAssignment_0_2; }

		//for_equation
		public RuleCall getFOR_EQNFor_equationParserRuleCall_0_2_0() { return cFOR_EQNFor_equationParserRuleCall_0_2_0; }

		//Con=connect_clause
		public Assignment getConAssignment_0_3() { return cConAssignment_0_3; }

		//connect_clause
		public RuleCall getConConnect_clauseParserRuleCall_0_3_0() { return cConConnect_clauseParserRuleCall_0_3_0; }

		//WHEN_EQN=when_equation
		public Assignment getWHEN_EQNAssignment_0_4() { return cWHEN_EQNAssignment_0_4; }

		//when_equation
		public RuleCall getWHEN_EQNWhen_equationParserRuleCall_0_4_0() { return cWHEN_EQNWhen_equationParserRuleCall_0_4_0; }

		//Comment=comment
		public Assignment getCommentAssignment_1() { return cCommentAssignment_1; }

		//comment
		public RuleCall getCommentCommentParserRuleCall_1_0() { return cCommentCommentParserRuleCall_1_0; }
	}

	public class When_equationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "when_equation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cWhenKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cWhenEprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cWhenEprExpressionParserRuleCall_1_0 = (RuleCall)cWhenEprAssignment_1.eContents().get(0);
		private final Keyword cThenKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cEqn_ThenAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cEqn_ThenEquationParserRuleCall_3_0_0 = (RuleCall)cEqn_ThenAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cElsewhenKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cElseWhenEprAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cElseWhenEprExpressionParserRuleCall_4_1_0 = (RuleCall)cElseWhenEprAssignment_4_1.eContents().get(0);
		private final Keyword cThenKeyword_4_2 = (Keyword)cGroup_4.eContents().get(2);
		private final Group cGroup_4_3 = (Group)cGroup_4.eContents().get(3);
		private final Assignment cEqn_ThenAssignment_4_3_0 = (Assignment)cGroup_4_3.eContents().get(0);
		private final RuleCall cEqn_ThenEquationParserRuleCall_4_3_0_0 = (RuleCall)cEqn_ThenAssignment_4_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_4_3_1 = (Keyword)cGroup_4_3.eContents().get(1);
		private final Keyword cEndKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Keyword cWhenKeyword_6 = (Keyword)cGroup.eContents().get(6);
		
		////////////////////////////////////////////Conditions and Loop Section For Equation//////////////////////
		//when_equation:
		//	"when" whenEpr=expression "then" (Eqn_Then+=equation ";")* ("elsewhen" elseWhenEpr+=expression "then"
		//	(Eqn_Then+=equation ";")*)* "end" "when";
		public ParserRule getRule() { return rule; }

		//"when" whenEpr=expression "then" (Eqn_Then+=equation ";")* ("elsewhen" elseWhenEpr+=expression "then"
		//(Eqn_Then+=equation ";")*)* "end" "when"
		public Group getGroup() { return cGroup; }

		//"when"
		public Keyword getWhenKeyword_0() { return cWhenKeyword_0; }

		//whenEpr=expression
		public Assignment getWhenEprAssignment_1() { return cWhenEprAssignment_1; }

		//expression
		public RuleCall getWhenEprExpressionParserRuleCall_1_0() { return cWhenEprExpressionParserRuleCall_1_0; }

		//"then"
		public Keyword getThenKeyword_2() { return cThenKeyword_2; }

		//(Eqn_Then+=equation ";")*
		public Group getGroup_3() { return cGroup_3; }

		//Eqn_Then+=equation
		public Assignment getEqn_ThenAssignment_3_0() { return cEqn_ThenAssignment_3_0; }

		//equation
		public RuleCall getEqn_ThenEquationParserRuleCall_3_0_0() { return cEqn_ThenEquationParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//("elsewhen" elseWhenEpr+=expression "then" (Eqn_Then+=equation ";")*)*
		public Group getGroup_4() { return cGroup_4; }

		//"elsewhen"
		public Keyword getElsewhenKeyword_4_0() { return cElsewhenKeyword_4_0; }

		//elseWhenEpr+=expression
		public Assignment getElseWhenEprAssignment_4_1() { return cElseWhenEprAssignment_4_1; }

		//expression
		public RuleCall getElseWhenEprExpressionParserRuleCall_4_1_0() { return cElseWhenEprExpressionParserRuleCall_4_1_0; }

		//"then"
		public Keyword getThenKeyword_4_2() { return cThenKeyword_4_2; }

		//(Eqn_Then+=equation ";")*
		public Group getGroup_4_3() { return cGroup_4_3; }

		//Eqn_Then+=equation
		public Assignment getEqn_ThenAssignment_4_3_0() { return cEqn_ThenAssignment_4_3_0; }

		//equation
		public RuleCall getEqn_ThenEquationParserRuleCall_4_3_0_0() { return cEqn_ThenEquationParserRuleCall_4_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_4_3_1() { return cSemicolonKeyword_4_3_1; }

		//"end"
		public Keyword getEndKeyword_5() { return cEndKeyword_5; }

		//"when"
		public Keyword getWhenKeyword_6() { return cWhenKeyword_6; }
	}

	public class If_equationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "if_equation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cIfKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cExprtrueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cExprtrueExpressionParserRuleCall_1_0 = (RuleCall)cExprtrueAssignment_1.eContents().get(0);
		private final Keyword cThenKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cEqnAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cEqnEquationParserRuleCall_3_0_0 = (RuleCall)cEqnAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cElseifKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cExprStilltrueAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cExprStilltrueExpressionParserRuleCall_4_1_0 = (RuleCall)cExprStilltrueAssignment_4_1.eContents().get(0);
		private final Keyword cThenKeyword_4_2 = (Keyword)cGroup_4.eContents().get(2);
		private final Group cGroup_4_3 = (Group)cGroup_4.eContents().get(3);
		private final Assignment cThenEqnAssignment_4_3_0 = (Assignment)cGroup_4_3.eContents().get(0);
		private final RuleCall cThenEqnEquationParserRuleCall_4_3_0_0 = (RuleCall)cThenEqnAssignment_4_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_4_3_1 = (Keyword)cGroup_4_3.eContents().get(1);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cElseKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Group cGroup_5_1 = (Group)cGroup_5.eContents().get(1);
		private final Assignment cElseEqnAssignment_5_1_0 = (Assignment)cGroup_5_1.eContents().get(0);
		private final RuleCall cElseEqnEquationParserRuleCall_5_1_0_0 = (RuleCall)cElseEqnAssignment_5_1_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_1_1 = (Keyword)cGroup_5_1.eContents().get(1);
		private final Keyword cEndKeyword_6 = (Keyword)cGroup.eContents().get(6);
		private final Keyword cIfKeyword_7 = (Keyword)cGroup.eContents().get(7);
		
		//if_equation:
		//	"if" exprtrue=expression "then" (Eqn+=equation ";")* ("elseif" exprStilltrue+=expression "then" (thenEqn+=equation
		//	";")*)* ("else" (elseEqn+=equation ";")*)? "end" "if";
		public ParserRule getRule() { return rule; }

		//"if" exprtrue=expression "then" (Eqn+=equation ";")* ("elseif" exprStilltrue+=expression "then" (thenEqn+=equation
		//";")*)* ("else" (elseEqn+=equation ";")*)? "end" "if"
		public Group getGroup() { return cGroup; }

		//"if"
		public Keyword getIfKeyword_0() { return cIfKeyword_0; }

		//exprtrue=expression
		public Assignment getExprtrueAssignment_1() { return cExprtrueAssignment_1; }

		//expression
		public RuleCall getExprtrueExpressionParserRuleCall_1_0() { return cExprtrueExpressionParserRuleCall_1_0; }

		//"then"
		public Keyword getThenKeyword_2() { return cThenKeyword_2; }

		//(Eqn+=equation ";")*
		public Group getGroup_3() { return cGroup_3; }

		//Eqn+=equation
		public Assignment getEqnAssignment_3_0() { return cEqnAssignment_3_0; }

		//equation
		public RuleCall getEqnEquationParserRuleCall_3_0_0() { return cEqnEquationParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//("elseif" exprStilltrue+=expression "then" (thenEqn+=equation ";")*)*
		public Group getGroup_4() { return cGroup_4; }

		//"elseif"
		public Keyword getElseifKeyword_4_0() { return cElseifKeyword_4_0; }

		//exprStilltrue+=expression
		public Assignment getExprStilltrueAssignment_4_1() { return cExprStilltrueAssignment_4_1; }

		//expression
		public RuleCall getExprStilltrueExpressionParserRuleCall_4_1_0() { return cExprStilltrueExpressionParserRuleCall_4_1_0; }

		//"then"
		public Keyword getThenKeyword_4_2() { return cThenKeyword_4_2; }

		//(thenEqn+=equation ";")*
		public Group getGroup_4_3() { return cGroup_4_3; }

		//thenEqn+=equation
		public Assignment getThenEqnAssignment_4_3_0() { return cThenEqnAssignment_4_3_0; }

		//equation
		public RuleCall getThenEqnEquationParserRuleCall_4_3_0_0() { return cThenEqnEquationParserRuleCall_4_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_4_3_1() { return cSemicolonKeyword_4_3_1; }

		//("else" (elseEqn+=equation ";")*)?
		public Group getGroup_5() { return cGroup_5; }

		//"else"
		public Keyword getElseKeyword_5_0() { return cElseKeyword_5_0; }

		//(elseEqn+=equation ";")*
		public Group getGroup_5_1() { return cGroup_5_1; }

		//elseEqn+=equation
		public Assignment getElseEqnAssignment_5_1_0() { return cElseEqnAssignment_5_1_0; }

		//equation
		public RuleCall getElseEqnEquationParserRuleCall_5_1_0_0() { return cElseEqnEquationParserRuleCall_5_1_0_0; }

		//";"
		public Keyword getSemicolonKeyword_5_1_1() { return cSemicolonKeyword_5_1_1; }

		//"end"
		public Keyword getEndKeyword_6() { return cEndKeyword_6; }

		//"if"
		public Keyword getIfKeyword_7() { return cIfKeyword_7; }
	}

	public class For_equationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "for_equation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cForKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cFor_loopAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cFor_loopFor_indicesParserRuleCall_1_0 = (RuleCall)cFor_loopAssignment_1.eContents().get(0);
		private final Keyword cLoopKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cFor_EqnAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cFor_EqnEquationParserRuleCall_3_0_0 = (RuleCall)cFor_EqnAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Keyword cEndKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cForKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//for_equation:
		//	"for" for_loop=for_indices "loop" (For_Eqn+=equation ";") "end" "for";
		public ParserRule getRule() { return rule; }

		//"for" for_loop=for_indices "loop" (For_Eqn+=equation ";") "end" "for"
		public Group getGroup() { return cGroup; }

		//"for"
		public Keyword getForKeyword_0() { return cForKeyword_0; }

		//for_loop=for_indices
		public Assignment getFor_loopAssignment_1() { return cFor_loopAssignment_1; }

		//for_indices
		public RuleCall getFor_loopFor_indicesParserRuleCall_1_0() { return cFor_loopFor_indicesParserRuleCall_1_0; }

		//"loop"
		public Keyword getLoopKeyword_2() { return cLoopKeyword_2; }

		//For_Eqn+=equation ";"
		public Group getGroup_3() { return cGroup_3; }

		//For_Eqn+=equation
		public Assignment getFor_EqnAssignment_3_0() { return cFor_EqnAssignment_3_0; }

		//equation
		public RuleCall getFor_EqnEquationParserRuleCall_3_0_0() { return cFor_EqnEquationParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//"end"
		public Keyword getEndKeyword_4() { return cEndKeyword_4; }

		//"for"
		public Keyword getForKeyword_5() { return cForKeyword_5; }
	}

	public class Connect_clauseElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "connect_clause");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cConnectKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cConnector1Assignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cConnector1Component_referenceParserRuleCall_2_0 = (RuleCall)cConnector1Assignment_2.eContents().get(0);
		private final Keyword cCommaKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cConnector2Assignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cConnector2Component_referenceParserRuleCall_4_0 = (RuleCall)cConnector2Assignment_4.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//connect_clause:
		//	"connect" "(" connector1=component_reference "," connector2=component_reference ")";
		public ParserRule getRule() { return rule; }

		//"connect" "(" connector1=component_reference "," connector2=component_reference ")"
		public Group getGroup() { return cGroup; }

		//"connect"
		public Keyword getConnectKeyword_0() { return cConnectKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//connector1=component_reference
		public Assignment getConnector1Assignment_2() { return cConnector1Assignment_2; }

		//component_reference
		public RuleCall getConnector1Component_referenceParserRuleCall_2_0() { return cConnector1Component_referenceParserRuleCall_2_0; }

		//","
		public Keyword getCommaKeyword_3() { return cCommaKeyword_3; }

		//connector2=component_reference
		public Assignment getConnector2Assignment_4() { return cConnector2Assignment_4; }

		//component_reference
		public RuleCall getConnector2Component_referenceParserRuleCall_4_0() { return cConnector2Component_referenceParserRuleCall_4_0; }

		//")"
		public Keyword getRightParenthesisKeyword_5() { return cRightParenthesisKeyword_5; }
	}

	public class Algorithm_sectionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "algorithm_section");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cAlgorithm_clauseAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cInitialKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cAlgorithmKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cAlgorithmAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cAlgorithmStatementParserRuleCall_3_0_0 = (RuleCall)cAlgorithmAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		
		//////////////////////////////////////////Algorithm//////////////////////////
		//algorithm_section:
		//	{algorithm_clause} "initial"? "algorithm" (Algorithm+=statement ";")*;
		public ParserRule getRule() { return rule; }

		//{algorithm_clause} "initial"? "algorithm" (Algorithm+=statement ";")*
		public Group getGroup() { return cGroup; }

		//{algorithm_clause}
		public Action getAlgorithm_clauseAction_0() { return cAlgorithm_clauseAction_0; }

		//"initial"?
		public Keyword getInitialKeyword_1() { return cInitialKeyword_1; }

		//"algorithm"
		public Keyword getAlgorithmKeyword_2() { return cAlgorithmKeyword_2; }

		//(Algorithm+=statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//Algorithm+=statement
		public Assignment getAlgorithmAssignment_3_0() { return cAlgorithmAssignment_3_0; }

		//statement
		public RuleCall getAlgorithmStatementParserRuleCall_3_0_0() { return cAlgorithmStatementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }
	}

	public class StatementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cStatementAction_0 = (Action)cGroup.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Assignment cIf_statementAssignment_1_0 = (Assignment)cAlternatives_1.eContents().get(0);
		private final RuleCall cIf_statementIf_statementParserRuleCall_1_0_0 = (RuleCall)cIf_statementAssignment_1_0.eContents().get(0);
		private final Assignment cState_OutputExprListAssignment_1_1 = (Assignment)cAlternatives_1.eContents().get(1);
		private final RuleCall cState_OutputExprListState_OutputExprListParserRuleCall_1_1_0 = (RuleCall)cState_OutputExprListAssignment_1_1.eContents().get(0);
		private final Keyword cReturnKeyword_1_2 = (Keyword)cAlternatives_1.eContents().get(2);
		private final Assignment cState_Comp_RefAssignment_1_3 = (Assignment)cAlternatives_1.eContents().get(3);
		private final RuleCall cState_Comp_RefState_Comp_RefParserRuleCall_1_3_0 = (RuleCall)cState_Comp_RefAssignment_1_3.eContents().get(0);
		private final Assignment cFor_statementAssignment_1_4 = (Assignment)cAlternatives_1.eContents().get(4);
		private final RuleCall cFor_statementFor_statementParserRuleCall_1_4_0 = (RuleCall)cFor_statementAssignment_1_4.eContents().get(0);
		private final Assignment cWhen_statementAssignment_1_5 = (Assignment)cAlternatives_1.eContents().get(5);
		private final RuleCall cWhen_statementWhen_statementParserRuleCall_1_5_0 = (RuleCall)cWhen_statementAssignment_1_5.eContents().get(0);
		private final Assignment cWhile_statementAssignment_1_6 = (Assignment)cAlternatives_1.eContents().get(6);
		private final RuleCall cWhile_statementWhile_statementParserRuleCall_1_6_0 = (RuleCall)cWhile_statementAssignment_1_6.eContents().get(0);
		private final Keyword cBreakKeyword_1_7 = (Keyword)cAlternatives_1.eContents().get(7);
		private final RuleCall cCommentParserRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		
		//statement:
		//	{statement} (If_statement=if_statement //| Algo_M_G=Algorithm_Macros_GEN_SIGNAL
		//	//| Algo_G_I=Algorithm_Macros_GEN_INC
		//	//| Algo_G_C=Algorithm_Macros_GEN_CHANGE
		//	| State_OutputExprList=state_OutputExprList | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
		//	When_statement=when_statement | While_statement=while_statement | "break") comment;
		public ParserRule getRule() { return rule; }

		//{statement} (If_statement=if_statement //| Algo_M_G=Algorithm_Macros_GEN_SIGNAL
		////| Algo_G_I=Algorithm_Macros_GEN_INC
		////| Algo_G_C=Algorithm_Macros_GEN_CHANGE
		//| State_OutputExprList=state_OutputExprList | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
		//When_statement=when_statement | While_statement=while_statement | "break") comment
		public Group getGroup() { return cGroup; }

		//{statement}
		public Action getStatementAction_0() { return cStatementAction_0; }

		//If_statement=if_statement //| Algo_M_G=Algorithm_Macros_GEN_SIGNAL
		////| Algo_G_I=Algorithm_Macros_GEN_INC
		////| Algo_G_C=Algorithm_Macros_GEN_CHANGE
		//| State_OutputExprList=state_OutputExprList | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
		//When_statement=when_statement | While_statement=while_statement | "break"
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//If_statement=if_statement
		public Assignment getIf_statementAssignment_1_0() { return cIf_statementAssignment_1_0; }

		//if_statement
		public RuleCall getIf_statementIf_statementParserRuleCall_1_0_0() { return cIf_statementIf_statementParserRuleCall_1_0_0; }

		//State_OutputExprList=state_OutputExprList
		public Assignment getState_OutputExprListAssignment_1_1() { return cState_OutputExprListAssignment_1_1; }

		//state_OutputExprList
		public RuleCall getState_OutputExprListState_OutputExprListParserRuleCall_1_1_0() { return cState_OutputExprListState_OutputExprListParserRuleCall_1_1_0; }

		//"return"
		public Keyword getReturnKeyword_1_2() { return cReturnKeyword_1_2; }

		//state_Comp_Ref=state_Comp_Ref
		public Assignment getState_Comp_RefAssignment_1_3() { return cState_Comp_RefAssignment_1_3; }

		//state_Comp_Ref
		public RuleCall getState_Comp_RefState_Comp_RefParserRuleCall_1_3_0() { return cState_Comp_RefState_Comp_RefParserRuleCall_1_3_0; }

		//For_statement=for_statement
		public Assignment getFor_statementAssignment_1_4() { return cFor_statementAssignment_1_4; }

		//for_statement
		public RuleCall getFor_statementFor_statementParserRuleCall_1_4_0() { return cFor_statementFor_statementParserRuleCall_1_4_0; }

		//When_statement=when_statement
		public Assignment getWhen_statementAssignment_1_5() { return cWhen_statementAssignment_1_5; }

		//when_statement
		public RuleCall getWhen_statementWhen_statementParserRuleCall_1_5_0() { return cWhen_statementWhen_statementParserRuleCall_1_5_0; }

		//While_statement=while_statement
		public Assignment getWhile_statementAssignment_1_6() { return cWhile_statementAssignment_1_6; }

		//while_statement
		public RuleCall getWhile_statementWhile_statementParserRuleCall_1_6_0() { return cWhile_statementWhile_statementParserRuleCall_1_6_0; }

		//"break"
		public Keyword getBreakKeyword_1_7() { return cBreakKeyword_1_7; }

		//comment
		public RuleCall getCommentParserRuleCall_2() { return cCommentParserRuleCall_2; }
	}

	public class State_OutputExprListElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "state_OutputExprList");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftParenthesisKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cOutput_expression_listAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cOutput_expression_listOutput_expression_listParserRuleCall_1_0 = (RuleCall)cOutput_expression_listAssignment_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Keyword cColonEqualsSignKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cComponent_refAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cComponent_refComponent_referenceParserRuleCall_4_0 = (RuleCall)cComponent_refAssignment_4.eContents().get(0);
		private final Assignment cFun_call_argsAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cFun_call_argsFunction_call_argsParserRuleCall_5_0 = (RuleCall)cFun_call_argsAssignment_5.eContents().get(0);
		
		/////////////////////////////////////Statements//////////////////////////////
		//state_OutputExprList:
		//	"(" output_expression_list=output_expression_list ")" ":=" Component_ref=component_reference
		//	fun_call_args=function_call_args;
		public ParserRule getRule() { return rule; }

		//"(" output_expression_list=output_expression_list ")" ":=" Component_ref=component_reference
		//fun_call_args=function_call_args
		public Group getGroup() { return cGroup; }

		//"("
		public Keyword getLeftParenthesisKeyword_0() { return cLeftParenthesisKeyword_0; }

		//output_expression_list=output_expression_list
		public Assignment getOutput_expression_listAssignment_1() { return cOutput_expression_listAssignment_1; }

		//output_expression_list
		public RuleCall getOutput_expression_listOutput_expression_listParserRuleCall_1_0() { return cOutput_expression_listOutput_expression_listParserRuleCall_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_2() { return cRightParenthesisKeyword_2; }

		//":="
		public Keyword getColonEqualsSignKeyword_3() { return cColonEqualsSignKeyword_3; }

		//Component_ref=component_reference
		public Assignment getComponent_refAssignment_4() { return cComponent_refAssignment_4; }

		//component_reference
		public RuleCall getComponent_refComponent_referenceParserRuleCall_4_0() { return cComponent_refComponent_referenceParserRuleCall_4_0; }

		//fun_call_args=function_call_args
		public Assignment getFun_call_argsAssignment_5() { return cFun_call_argsAssignment_5; }

		//function_call_args
		public RuleCall getFun_call_argsFunction_call_argsParserRuleCall_5_0() { return cFun_call_argsFunction_call_argsParserRuleCall_5_0; }
	}

	public class State_Comp_RefElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "state_Comp_Ref");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_0_0 = (RuleCall)cComponent_referenceAssignment_0.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cAlternatives_1.eContents().get(0);
		private final Keyword cColonEqualsSignKeyword_1_0_0 = (Keyword)cGroup_1_0.eContents().get(0);
		private final Assignment cExprAssignment_1_0_1 = (Assignment)cGroup_1_0.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_1_0_1_0 = (RuleCall)cExprAssignment_1_0_1.eContents().get(0);
		private final Assignment cFun_call_argsAssignment_1_1 = (Assignment)cAlternatives_1.eContents().get(1);
		private final RuleCall cFun_call_argsFunction_call_argsParserRuleCall_1_1_0 = (RuleCall)cFun_call_argsAssignment_1_1.eContents().get(0);
		
		//state_Comp_Ref:
		//	component_reference=component_reference (":=" expr=expression | fun_call_args=function_call_args);
		public ParserRule getRule() { return rule; }

		//component_reference=component_reference (":=" expr=expression | fun_call_args=function_call_args)
		public Group getGroup() { return cGroup; }

		//component_reference=component_reference
		public Assignment getComponent_referenceAssignment_0() { return cComponent_referenceAssignment_0; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_0_0() { return cComponent_referenceComponent_referenceParserRuleCall_0_0; }

		//":=" expr=expression | fun_call_args=function_call_args
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//":=" expr=expression
		public Group getGroup_1_0() { return cGroup_1_0; }

		//":="
		public Keyword getColonEqualsSignKeyword_1_0_0() { return cColonEqualsSignKeyword_1_0_0; }

		//expr=expression
		public Assignment getExprAssignment_1_0_1() { return cExprAssignment_1_0_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_1_0_1_0() { return cExprExpressionParserRuleCall_1_0_1_0; }

		//fun_call_args=function_call_args
		public Assignment getFun_call_argsAssignment_1_1() { return cFun_call_argsAssignment_1_1; }

		//function_call_args
		public RuleCall getFun_call_argsFunction_call_argsParserRuleCall_1_1_0() { return cFun_call_argsFunction_call_argsParserRuleCall_1_1_0; }
	}

	public class When_statementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "when_statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cWhenKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cWhen_exprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cWhen_exprExpressionParserRuleCall_1_0 = (RuleCall)cWhen_exprAssignment_1.eContents().get(0);
		private final Keyword cThenKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cWhen_stat_trueAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cWhen_stat_trueStatementParserRuleCall_3_0_0 = (RuleCall)cWhen_stat_trueAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cElsewhenKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cElse_When_exprAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cElse_When_exprExpressionParserRuleCall_4_1_0 = (RuleCall)cElse_When_exprAssignment_4_1.eContents().get(0);
		private final Keyword cThenKeyword_4_2 = (Keyword)cGroup_4.eContents().get(2);
		private final Group cGroup_4_3 = (Group)cGroup_4.eContents().get(3);
		private final Assignment cThen_statementAssignment_4_3_0 = (Assignment)cGroup_4_3.eContents().get(0);
		private final RuleCall cThen_statementStatementParserRuleCall_4_3_0_0 = (RuleCall)cThen_statementAssignment_4_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_4_3_1 = (Keyword)cGroup_4_3.eContents().get(1);
		private final Keyword cEndKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Keyword cWhenKeyword_6 = (Keyword)cGroup.eContents().get(6);
		
		//when_statement:
		//	"when" When_expr=expression "then" (When_stat_true+=statement ";")* ("elsewhen" Else_When_expr+=expression "then"
		//	(Then_statement+=statement ";")*)* "end" "when";
		public ParserRule getRule() { return rule; }

		//"when" When_expr=expression "then" (When_stat_true+=statement ";")* ("elsewhen" Else_When_expr+=expression "then"
		//(Then_statement+=statement ";")*)* "end" "when"
		public Group getGroup() { return cGroup; }

		//"when"
		public Keyword getWhenKeyword_0() { return cWhenKeyword_0; }

		//When_expr=expression
		public Assignment getWhen_exprAssignment_1() { return cWhen_exprAssignment_1; }

		//expression
		public RuleCall getWhen_exprExpressionParserRuleCall_1_0() { return cWhen_exprExpressionParserRuleCall_1_0; }

		//"then"
		public Keyword getThenKeyword_2() { return cThenKeyword_2; }

		//(When_stat_true+=statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//When_stat_true+=statement
		public Assignment getWhen_stat_trueAssignment_3_0() { return cWhen_stat_trueAssignment_3_0; }

		//statement
		public RuleCall getWhen_stat_trueStatementParserRuleCall_3_0_0() { return cWhen_stat_trueStatementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//("elsewhen" Else_When_expr+=expression "then" (Then_statement+=statement ";")*)*
		public Group getGroup_4() { return cGroup_4; }

		//"elsewhen"
		public Keyword getElsewhenKeyword_4_0() { return cElsewhenKeyword_4_0; }

		//Else_When_expr+=expression
		public Assignment getElse_When_exprAssignment_4_1() { return cElse_When_exprAssignment_4_1; }

		//expression
		public RuleCall getElse_When_exprExpressionParserRuleCall_4_1_0() { return cElse_When_exprExpressionParserRuleCall_4_1_0; }

		//"then"
		public Keyword getThenKeyword_4_2() { return cThenKeyword_4_2; }

		//(Then_statement+=statement ";")*
		public Group getGroup_4_3() { return cGroup_4_3; }

		//Then_statement+=statement
		public Assignment getThen_statementAssignment_4_3_0() { return cThen_statementAssignment_4_3_0; }

		//statement
		public RuleCall getThen_statementStatementParserRuleCall_4_3_0_0() { return cThen_statementStatementParserRuleCall_4_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_4_3_1() { return cSemicolonKeyword_4_3_1; }

		//"end"
		public Keyword getEndKeyword_5() { return cEndKeyword_5; }

		//"when"
		public Keyword getWhenKeyword_6() { return cWhenKeyword_6; }
	}

	public class While_statementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "while_statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cWhileKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cWhile_exprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cWhile_exprExpressionParserRuleCall_1_0 = (RuleCall)cWhile_exprAssignment_1.eContents().get(0);
		private final Keyword cLoopKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cWhile_statAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cWhile_statWhile_statementParserRuleCall_3_0_0 = (RuleCall)cWhile_statAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Keyword cEndKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cWhileKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//while_statement:
		//	"while" while_expr=expression "loop" (While_stat+=while_statement ";")* "end" "while";
		public ParserRule getRule() { return rule; }

		//"while" while_expr=expression "loop" (While_stat+=while_statement ";")* "end" "while"
		public Group getGroup() { return cGroup; }

		//"while"
		public Keyword getWhileKeyword_0() { return cWhileKeyword_0; }

		//while_expr=expression
		public Assignment getWhile_exprAssignment_1() { return cWhile_exprAssignment_1; }

		//expression
		public RuleCall getWhile_exprExpressionParserRuleCall_1_0() { return cWhile_exprExpressionParserRuleCall_1_0; }

		//"loop"
		public Keyword getLoopKeyword_2() { return cLoopKeyword_2; }

		//(While_stat+=while_statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//While_stat+=while_statement
		public Assignment getWhile_statAssignment_3_0() { return cWhile_statAssignment_3_0; }

		//while_statement
		public RuleCall getWhile_statWhile_statementParserRuleCall_3_0_0() { return cWhile_statWhile_statementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//"end"
		public Keyword getEndKeyword_4() { return cEndKeyword_4; }

		//"while"
		public Keyword getWhileKeyword_5() { return cWhileKeyword_5; }
	}

	public class For_statementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "for_statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cForKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cFor_indicesAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cFor_indicesFor_indicesParserRuleCall_1_0 = (RuleCall)cFor_indicesAssignment_1.eContents().get(0);
		private final Keyword cLoopKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cFor_statementAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cFor_statementFor_statementParserRuleCall_3_0_0 = (RuleCall)cFor_statementAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Keyword cEndKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cForKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//for_statement:
		//	"for" For_indices=for_indices "loop" (For_statement+=for_statement ";")* "end" "for";
		public ParserRule getRule() { return rule; }

		//"for" For_indices=for_indices "loop" (For_statement+=for_statement ";")* "end" "for"
		public Group getGroup() { return cGroup; }

		//"for"
		public Keyword getForKeyword_0() { return cForKeyword_0; }

		//For_indices=for_indices
		public Assignment getFor_indicesAssignment_1() { return cFor_indicesAssignment_1; }

		//for_indices
		public RuleCall getFor_indicesFor_indicesParserRuleCall_1_0() { return cFor_indicesFor_indicesParserRuleCall_1_0; }

		//"loop"
		public Keyword getLoopKeyword_2() { return cLoopKeyword_2; }

		//(For_statement+=for_statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//For_statement+=for_statement
		public Assignment getFor_statementAssignment_3_0() { return cFor_statementAssignment_3_0; }

		//for_statement
		public RuleCall getFor_statementFor_statementParserRuleCall_3_0_0() { return cFor_statementFor_statementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//"end"
		public Keyword getEndKeyword_4() { return cEndKeyword_4; }

		//"for"
		public Keyword getForKeyword_5() { return cForKeyword_5; }
	}

	public class If_statementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "if_statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cIfKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cExprtrueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cExprtrueExpressionParserRuleCall_1_0 = (RuleCall)cExprtrueAssignment_1.eContents().get(0);
		private final Keyword cThenKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cStateAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cStateStatementParserRuleCall_3_0_0 = (RuleCall)cStateAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cElseifKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cExprStilltrueAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cExprStilltrueExpressionParserRuleCall_4_1_0 = (RuleCall)cExprStilltrueAssignment_4_1.eContents().get(0);
		private final Keyword cThenKeyword_4_2 = (Keyword)cGroup_4.eContents().get(2);
		private final Group cGroup_4_3 = (Group)cGroup_4.eContents().get(3);
		private final Assignment cStateAssignment_4_3_0 = (Assignment)cGroup_4_3.eContents().get(0);
		private final RuleCall cStateStatementParserRuleCall_4_3_0_0 = (RuleCall)cStateAssignment_4_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_4_3_1 = (Keyword)cGroup_4_3.eContents().get(1);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cElseKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Group cGroup_5_1 = (Group)cGroup_5.eContents().get(1);
		private final Assignment cStateAssignment_5_1_0 = (Assignment)cGroup_5_1.eContents().get(0);
		private final RuleCall cStateStatementParserRuleCall_5_1_0_0 = (RuleCall)cStateAssignment_5_1_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_1_1 = (Keyword)cGroup_5_1.eContents().get(1);
		private final Keyword cEndKeyword_6 = (Keyword)cGroup.eContents().get(6);
		private final Keyword cIfKeyword_7 = (Keyword)cGroup.eContents().get(7);
		
		//if_statement:
		//	"if" exprtrue=expression "then" (state+=statement ";")* ("elseif" exprStilltrue+=expression "then" (state+=statement
		//	";")*)* ("else" (state+=statement ";")*)? "end" "if";
		public ParserRule getRule() { return rule; }

		//"if" exprtrue=expression "then" (state+=statement ";")* ("elseif" exprStilltrue+=expression "then" (state+=statement
		//";")*)* ("else" (state+=statement ";")*)? "end" "if"
		public Group getGroup() { return cGroup; }

		//"if"
		public Keyword getIfKeyword_0() { return cIfKeyword_0; }

		//exprtrue=expression
		public Assignment getExprtrueAssignment_1() { return cExprtrueAssignment_1; }

		//expression
		public RuleCall getExprtrueExpressionParserRuleCall_1_0() { return cExprtrueExpressionParserRuleCall_1_0; }

		//"then"
		public Keyword getThenKeyword_2() { return cThenKeyword_2; }

		//(state+=statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//state+=statement
		public Assignment getStateAssignment_3_0() { return cStateAssignment_3_0; }

		//statement
		public RuleCall getStateStatementParserRuleCall_3_0_0() { return cStateStatementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//("elseif" exprStilltrue+=expression "then" (state+=statement ";")*)*
		public Group getGroup_4() { return cGroup_4; }

		//"elseif"
		public Keyword getElseifKeyword_4_0() { return cElseifKeyword_4_0; }

		//exprStilltrue+=expression
		public Assignment getExprStilltrueAssignment_4_1() { return cExprStilltrueAssignment_4_1; }

		//expression
		public RuleCall getExprStilltrueExpressionParserRuleCall_4_1_0() { return cExprStilltrueExpressionParserRuleCall_4_1_0; }

		//"then"
		public Keyword getThenKeyword_4_2() { return cThenKeyword_4_2; }

		//(state+=statement ";")*
		public Group getGroup_4_3() { return cGroup_4_3; }

		//state+=statement
		public Assignment getStateAssignment_4_3_0() { return cStateAssignment_4_3_0; }

		//statement
		public RuleCall getStateStatementParserRuleCall_4_3_0_0() { return cStateStatementParserRuleCall_4_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_4_3_1() { return cSemicolonKeyword_4_3_1; }

		//("else" (state+=statement ";")*)?
		public Group getGroup_5() { return cGroup_5; }

		//"else"
		public Keyword getElseKeyword_5_0() { return cElseKeyword_5_0; }

		//(state+=statement ";")*
		public Group getGroup_5_1() { return cGroup_5_1; }

		//state+=statement
		public Assignment getStateAssignment_5_1_0() { return cStateAssignment_5_1_0; }

		//statement
		public RuleCall getStateStatementParserRuleCall_5_1_0_0() { return cStateStatementParserRuleCall_5_1_0_0; }

		//";"
		public Keyword getSemicolonKeyword_5_1_1() { return cSemicolonKeyword_5_1_1; }

		//"end"
		public Keyword getEndKeyword_6() { return cEndKeyword_6; }

		//"if"
		public Keyword getIfKeyword_7() { return cIfKeyword_7; }
	}

	public class Extends_clauseElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "extends_clause");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cExtendsKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cClass_ModAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cClass_ModClass_modificationParserRuleCall_2_0 = (RuleCall)cClass_ModAssignment_2.eContents().get(0);
		private final Assignment cAnnotationAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cAnnotationAnnotationParserRuleCall_3_0 = (RuleCall)cAnnotationAssignment_3.eContents().get(0);
		
		//////////////////////////////////////////Extend/////////////////////////////
		//extends_clause:
		//	"extends" Name=name Class_Mod=class_modification? Annotation=annotation?;
		public ParserRule getRule() { return rule; }

		//"extends" Name=name Class_Mod=class_modification? Annotation=annotation?
		public Group getGroup() { return cGroup; }

		//"extends"
		public Keyword getExtendsKeyword_0() { return cExtendsKeyword_0; }

		//Name=name
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//name
		public RuleCall getNameNameParserRuleCall_1_0() { return cNameNameParserRuleCall_1_0; }

		//Class_Mod=class_modification?
		public Assignment getClass_ModAssignment_2() { return cClass_ModAssignment_2; }

		//class_modification
		public RuleCall getClass_ModClass_modificationParserRuleCall_2_0() { return cClass_ModClass_modificationParserRuleCall_2_0; }

		//Annotation=annotation?
		public Assignment getAnnotationAssignment_3() { return cAnnotationAssignment_3; }

		//annotation
		public RuleCall getAnnotationAnnotationParserRuleCall_3_0() { return cAnnotationAnnotationParserRuleCall_3_0; }
	}

	public class AnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "annotation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cAnnotationKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cClass_ModAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cClass_ModClass_modificationParserRuleCall_1_0 = (RuleCall)cClass_ModAssignment_1.eContents().get(0);
		
		//////////////////////////////////////////Annotation/////////////////////////
		//annotation:
		//	"annotation" Class_Mod=class_modification;
		public ParserRule getRule() { return rule; }

		//"annotation" Class_Mod=class_modification
		public Group getGroup() { return cGroup; }

		//"annotation"
		public Keyword getAnnotationKeyword_0() { return cAnnotationKeyword_0; }

		//Class_Mod=class_modification
		public Assignment getClass_ModAssignment_1() { return cClass_ModAssignment_1; }

		//class_modification
		public RuleCall getClass_ModClass_modificationParserRuleCall_1_0() { return cClass_ModClass_modificationParserRuleCall_1_0; }
	}

	public class Import_clauseElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "import_clause");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cImportKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cAlternatives_1.eContents().get(0);
		private final RuleCall cIDENTTerminalRuleCall_1_0_0 = (RuleCall)cGroup_1_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_0_1 = (Keyword)cGroup_1_0.eContents().get(1);
		private final Assignment cNameAssignment_1_0_2 = (Assignment)cGroup_1_0.eContents().get(2);
		private final RuleCall cNameNameParserRuleCall_1_0_2_0 = (RuleCall)cNameAssignment_1_0_2.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cAlternatives_1.eContents().get(1);
		private final Assignment cNameAssignment_1_1_0 = (Assignment)cGroup_1_1.eContents().get(0);
		private final RuleCall cNameNameParserRuleCall_1_1_0_0 = (RuleCall)cNameAssignment_1_1_0.eContents().get(0);
		private final Group cGroup_1_1_1 = (Group)cGroup_1_1.eContents().get(1);
		private final Keyword cFullStopKeyword_1_1_1_0 = (Keyword)cGroup_1_1_1.eContents().get(0);
		private final Keyword cAsteriskKeyword_1_1_1_1 = (Keyword)cGroup_1_1_1.eContents().get(1);
		private final RuleCall cCommentParserRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		
		//////////////////////////////////////////Import/////////////////////////////
		//import_clause:
		//	"import" (IDENT "=" Name=name | Name=name ("." "*")?) comment;
		public ParserRule getRule() { return rule; }

		//"import" (IDENT "=" Name=name | Name=name ("." "*")?) comment
		public Group getGroup() { return cGroup; }

		//"import"
		public Keyword getImportKeyword_0() { return cImportKeyword_0; }

		//IDENT "=" Name=name | Name=name ("." "*")?
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//IDENT "=" Name=name
		public Group getGroup_1_0() { return cGroup_1_0; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_1_0_0() { return cIDENTTerminalRuleCall_1_0_0; }

		//"="
		public Keyword getEqualsSignKeyword_1_0_1() { return cEqualsSignKeyword_1_0_1; }

		//Name=name
		public Assignment getNameAssignment_1_0_2() { return cNameAssignment_1_0_2; }

		//name
		public RuleCall getNameNameParserRuleCall_1_0_2_0() { return cNameNameParserRuleCall_1_0_2_0; }

		//Name=name ("." "*")?
		public Group getGroup_1_1() { return cGroup_1_1; }

		//Name=name
		public Assignment getNameAssignment_1_1_0() { return cNameAssignment_1_1_0; }

		//name
		public RuleCall getNameNameParserRuleCall_1_1_0_0() { return cNameNameParserRuleCall_1_1_0_0; }

		//("." "*")?
		public Group getGroup_1_1_1() { return cGroup_1_1_1; }

		//"."
		public Keyword getFullStopKeyword_1_1_1_0() { return cFullStopKeyword_1_1_1_0; }

		//"*"
		public Keyword getAsteriskKeyword_1_1_1_1() { return cAsteriskKeyword_1_1_1_1; }

		//comment
		public RuleCall getCommentParserRuleCall_2() { return cCommentParserRuleCall_2; }
	}

	public class Language_specificationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "language_specification");
		private final Assignment cStrAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cStrSTRINGTerminalRuleCall_0 = (RuleCall)cStrAssignment.eContents().get(0);
		
		//////////////////////////////////////////External///////////////////////////
		//language_specification:
		//	Str=STRING;
		public ParserRule getRule() { return rule; }

		//Str=STRING
		public Assignment getStrAssignment() { return cStrAssignment; }

		//STRING
		public RuleCall getStrSTRINGTerminalRuleCall_0() { return cStrSTRINGTerminalRuleCall_0; }
	}

	public class External_function_callElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "external_function_call");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cExternal_function_callAction_0 = (Action)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_1_0_0 = (RuleCall)cComponent_referenceAssignment_1_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final RuleCall cIDENTTerminalRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		private final Keyword cLeftParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cE_ListAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cE_ListExpression_listParserRuleCall_4_0 = (RuleCall)cE_ListAssignment_4.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//external_function_call:
		//	{external_function_call} (component_reference=component_reference "=")? IDENT "(" E_List=expression_list? ")";
		public ParserRule getRule() { return rule; }

		//{external_function_call} (component_reference=component_reference "=")? IDENT "(" E_List=expression_list? ")"
		public Group getGroup() { return cGroup; }

		//{external_function_call}
		public Action getExternal_function_callAction_0() { return cExternal_function_callAction_0; }

		//(component_reference=component_reference "=")?
		public Group getGroup_1() { return cGroup_1; }

		//component_reference=component_reference
		public Assignment getComponent_referenceAssignment_1_0() { return cComponent_referenceAssignment_1_0; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_1_0_0() { return cComponent_referenceComponent_referenceParserRuleCall_1_0_0; }

		//"="
		public Keyword getEqualsSignKeyword_1_1() { return cEqualsSignKeyword_1_1; }

		//IDENT
		public RuleCall getIDENTTerminalRuleCall_2() { return cIDENTTerminalRuleCall_2; }

		//"("
		public Keyword getLeftParenthesisKeyword_3() { return cLeftParenthesisKeyword_3; }

		//E_List=expression_list?
		public Assignment getE_ListAssignment_4() { return cE_ListAssignment_4; }

		//expression_list
		public RuleCall getE_ListExpression_listParserRuleCall_4_0() { return cE_ListExpression_listParserRuleCall_4_0; }

		//")"
		public Keyword getRightParenthesisKeyword_5() { return cRightParenthesisKeyword_5; }
	}
	
	
	private Modification_alternativesElements pModification_alternatives;
	private Left_hand_component_referenceElements pLeft_hand_component_reference;
	private ModificationElements pModification;
	private Class_modificationElements pClass_modification;
	private Argument_listElements pArgument_list;
	private ArgumentElements pArgument;
	private Element_modification_or_replaceableElements pElement_modification_or_replaceable;
	private Element_modificationElements pElement_modification;
	private Element_replaceableElements pElement_replaceable;
	private Constraining_clauseElements pConstraining_clause;
	private Element_redeclarationElements pElement_redeclaration;
	private Component_clauseElements pComponent_clause;
	private Component_listElements pComponent_list;
	private Component_declarationElements pComponent_declaration;
	private Conditional_attributeElements pConditional_attribute;
	private Component_clause1Elements pComponent_clause1;
	private Component_declaration1Elements pComponent_declaration1;
	private Type_specifierElements pType_specifier;
	private DeclarationElements pDeclaration;
	private Class_definitionElements pClass_definition;
	private Class_specifierElements pClass_specifier;
	private Base_prefixElements pBase_prefix;
	private Type_prefixElements pType_prefix;
	private Enum_listElements pEnum_list;
	private Enumeration_literalElements pEnumeration_literal;
	private CompositionElements pComposition;
	private Element_listElements pElement_list;
	private ElementElements pElement;
	private Equation_sectionElements pEquation_section;
	private EquationElements pEquation;
	private When_equationElements pWhen_equation;
	private If_equationElements pIf_equation;
	private For_equationElements pFor_equation;
	private Connect_clauseElements pConnect_clause;
	private Algorithm_sectionElements pAlgorithm_section;
	private StatementElements pStatement;
	private State_OutputExprListElements pState_OutputExprList;
	private State_Comp_RefElements pState_Comp_Ref;
	private When_statementElements pWhen_statement;
	private While_statementElements pWhile_statement;
	private For_statementElements pFor_statement;
	private If_statementElements pIf_statement;
	private Extends_clauseElements pExtends_clause;
	private AnnotationElements pAnnotation;
	private Import_clauseElements pImport_clause;
	private Language_specificationElements pLanguage_specification;
	private External_function_callElements pExternal_function_call;
	
	private final GrammarProvider grammarProvider;

	private ModeleditorGrammarAccess gaModeleditor;

	@Inject
	public ModificationGrammarAccess(GrammarProvider grammarProvider,
		ModeleditorGrammarAccess gaModeleditor) {
		this.grammarProvider = grammarProvider;
		this.gaModeleditor = gaModeleditor;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	public ModeleditorGrammarAccess getModeleditorGrammarAccess() {
		return gaModeleditor;
	}

	
	//////////////////////////////////////Modification///////////////////////////
	//// ModelicaML specific. All rules are made optional in order to not create validation error markers if nothing was entered by the user.
	//modification_alternatives:
	//	(left_hand_component_reference "=" Expr=expression)? //a simple component equality assignment modification rule without "(" and ")". Added for better code completion support (see the left_hand_component_reference that returns only the subcomponents of the component being modified)
	//	// standard modelica modification rule including "(" and ")" and class extension modifications
	//	| Modification_Rule=modification?;
	public Modification_alternativesElements getModification_alternativesAccess() {
		return (pModification_alternatives != null) ? pModification_alternatives : (pModification_alternatives = new Modification_alternativesElements());
	}
	
	public ParserRule getModification_alternativesRule() {
		return getModification_alternativesAccess().getRule();
	}

	//// ModelicaML specific. 
	//left_hand_component_reference:
	//	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
	public Left_hand_component_referenceElements getLeft_hand_component_referenceAccess() {
		return (pLeft_hand_component_reference != null) ? pLeft_hand_component_reference : (pLeft_hand_component_reference = new Left_hand_component_referenceElements());
	}
	
	public ParserRule getLeft_hand_component_referenceRule() {
		return getLeft_hand_component_referenceAccess().getRule();
	}

	//modification:
	//	class_modification ("=" Expr=expression)? | "=" Expr=expression | ":=" Expr=expression;
	public ModificationElements getModificationAccess() {
		return (pModification != null) ? pModification : (pModification = new ModificationElements());
	}
	
	public ParserRule getModificationRule() {
		return getModificationAccess().getRule();
	}

	////argument
	//class_modification:
	//	{class_modification_action} "(" Arg=argument_list? ")";
	public Class_modificationElements getClass_modificationAccess() {
		return (pClass_modification != null) ? pClass_modification : (pClass_modification = new Class_modificationElements());
	}
	
	public ParserRule getClass_modificationRule() {
		return getClass_modificationAccess().getRule();
	}

	//argument_list: //argument  (',' Arg+=argument )* 	// ModelicaML specific, to allow only one entry. This is mainly needed for class components tree view.
	//	argument;
	public Argument_listElements getArgument_listAccess() {
		return (pArgument_list != null) ? pArgument_list : (pArgument_list = new Argument_listElements());
	}
	
	public ParserRule getArgument_listRule() {
		return getArgument_listAccess().getRule();
	}

	//argument:
	//	element_modification_or_replaceable | element_redeclaration;
	public ArgumentElements getArgumentAccess() {
		return (pArgument != null) ? pArgument : (pArgument = new ArgumentElements());
	}
	
	public ParserRule getArgumentRule() {
		return getArgumentAccess().getRule();
	}

	//element_modification_or_replaceable:
	//	{element_modification_or_replaceable} "each"? "final"? (Element_mod=element_modification |
	//	Element_rep=element_replaceable);
	public Element_modification_or_replaceableElements getElement_modification_or_replaceableAccess() {
		return (pElement_modification_or_replaceable != null) ? pElement_modification_or_replaceable : (pElement_modification_or_replaceable = new Element_modification_or_replaceableElements());
	}
	
	public ParserRule getElement_modification_or_replaceableRule() {
		return getElement_modification_or_replaceableAccess().getRule();
	}

	//element_modification:
	//	Name=name Modification=modification? string_comment;
	public Element_modificationElements getElement_modificationAccess() {
		return (pElement_modification != null) ? pElement_modification : (pElement_modification = new Element_modificationElements());
	}
	
	public ParserRule getElement_modificationRule() {
		return getElement_modificationAccess().getRule();
	}

	//element_replaceable:
	//	{element_replaceable} "replaceable" (Class_Def=class_definition | Comp_clause2=component_clause1)
	//	Constrain_Clause=constraining_clause?;
	public Element_replaceableElements getElement_replaceableAccess() {
		return (pElement_replaceable != null) ? pElement_replaceable : (pElement_replaceable = new Element_replaceableElements());
	}
	
	public ParserRule getElement_replaceableRule() {
		return getElement_replaceableAccess().getRule();
	}

	//constraining_clause:
	//	"constrainedby" Name=name Class_Mod=class_modification?;
	public Constraining_clauseElements getConstraining_clauseAccess() {
		return (pConstraining_clause != null) ? pConstraining_clause : (pConstraining_clause = new Constraining_clauseElements());
	}
	
	public ParserRule getConstraining_clauseRule() {
		return getConstraining_clauseAccess().getRule();
	}

	//////////////////////////////////////////Element Redeclaration///////////////
	//// TODO:  ( Class_Def=class_definition | Comp_clause1=component_clause1) does not work! Is it an error in the Modelica spec.?
	//element_redeclaration:
	//	{element_redeclaration} "redeclare" "each"? "final"? ((Class_Def=class_definition | Comp_clause1=component_clause1) |
	//	Element_R=element_replaceable);
	public Element_redeclarationElements getElement_redeclarationAccess() {
		return (pElement_redeclaration != null) ? pElement_redeclaration : (pElement_redeclaration = new Element_redeclarationElements());
	}
	
	public ParserRule getElement_redeclarationRule() {
		return getElement_redeclarationAccess().getRule();
	}

	///////////////////////////////////////////component_clause///////////////////
	//component_clause:
	//	type_prefix type_specifier Array_Subs=array_subscripts? Com_List=component_list;
	public Component_clauseElements getComponent_clauseAccess() {
		return (pComponent_clause != null) ? pComponent_clause : (pComponent_clause = new Component_clauseElements());
	}
	
	public ParserRule getComponent_clauseRule() {
		return getComponent_clauseAccess().getRule();
	}

	//////////////////////////////////////////component_list//////////////////////
	//component_list:
	//	component_declaration ("," component_Dec+=component_declaration)*;
	public Component_listElements getComponent_listAccess() {
		return (pComponent_list != null) ? pComponent_list : (pComponent_list = new Component_listElements());
	}
	
	public ParserRule getComponent_listRule() {
		return getComponent_listAccess().getRule();
	}

	//component_declaration:
	//	Dec=declaration Conditional_Att=conditional_attribute? comment;
	public Component_declarationElements getComponent_declarationAccess() {
		return (pComponent_declaration != null) ? pComponent_declaration : (pComponent_declaration = new Component_declarationElements());
	}
	
	public ParserRule getComponent_declarationRule() {
		return getComponent_declarationAccess().getRule();
	}

	//conditional_attribute:
	//	"if" Expr=expression;
	public Conditional_attributeElements getConditional_attributeAccess() {
		return (pConditional_attribute != null) ? pConditional_attribute : (pConditional_attribute = new Conditional_attributeElements());
	}
	
	public ParserRule getConditional_attributeRule() {
		return getConditional_attributeAccess().getRule();
	}

	//////////////////////////////////////////component_clause1///////////////////
	//component_clause1:
	//	type_prefix type_specifier Com_Dec=component_declaration1;
	public Component_clause1Elements getComponent_clause1Access() {
		return (pComponent_clause1 != null) ? pComponent_clause1 : (pComponent_clause1 = new Component_clause1Elements());
	}
	
	public ParserRule getComponent_clause1Rule() {
		return getComponent_clause1Access().getRule();
	}

	//component_declaration1:
	//	declaration comment;
	public Component_declaration1Elements getComponent_declaration1Access() {
		return (pComponent_declaration1 != null) ? pComponent_declaration1 : (pComponent_declaration1 = new Component_declaration1Elements());
	}
	
	public ParserRule getComponent_declaration1Rule() {
		return getComponent_declaration1Access().getRule();
	}

	//type_specifier:
	//	Name=name;
	public Type_specifierElements getType_specifierAccess() {
		return (pType_specifier != null) ? pType_specifier : (pType_specifier = new Type_specifierElements());
	}
	
	public ParserRule getType_specifierRule() {
		return getType_specifierAccess().getRule();
	}

	//////////////////////////////////////////Declaration/////////////////////////
	//declaration:
	//	{declaration} redeclaredComponentName=IDENT ArraySubs=array_subscripts? Mod=modification?;
	public DeclarationElements getDeclarationAccess() {
		return (pDeclaration != null) ? pDeclaration : (pDeclaration = new DeclarationElements());
	}
	
	public ParserRule getDeclarationRule() {
		return getDeclarationAccess().getRule();
	}

	//////////////////////////////////////////Class Definition////////////////////
	//class_definition:
	//	"encapsulated"? "partial"? ("class" | "model" | "record" | "block" | "expandable"? "connector" | "type" | "package" |
	//	"function" | "operator" | "operator" "function" | "operator" "record") class_specifier;
	public Class_definitionElements getClass_definitionAccess() {
		return (pClass_definition != null) ? pClass_definition : (pClass_definition = new Class_definitionElements());
	}
	
	public ParserRule getClass_definitionRule() {
		return getClass_definitionAccess().getRule();
	}

	//////////////////////////////////////////Class Specifier////////////////////
	//class_specifier:
	//	{class_specifier} (IDENT string_comment Comp=composition "end" IDENT | IDENT "=" base_prefix Name=name
	//	Array_Subs=array_subscripts? Class_mod=class_modification? comment | var+=IDENT "=" "enumeration" "("
	//	(Enum_list=enum_list? | ":") ")" comment | IDENT "=" "der" "(" Name=name "," IDENT ("," IDENT)* ")" comment |
	//	"extends" IDENT Class_mod=class_modification? string_comment Comp=composition "end" IDENT);
	public Class_specifierElements getClass_specifierAccess() {
		return (pClass_specifier != null) ? pClass_specifier : (pClass_specifier = new Class_specifierElements());
	}
	
	public ParserRule getClass_specifierRule() {
		return getClass_specifierAccess().getRule();
	}

	//base_prefix:
	//	type_prefix;
	public Base_prefixElements getBase_prefixAccess() {
		return (pBase_prefix != null) ? pBase_prefix : (pBase_prefix = new Base_prefixElements());
	}
	
	public ParserRule getBase_prefixRule() {
		return getBase_prefixAccess().getRule();
	}

	//type_prefix:
	//	("flow" | "stream")? ("discrete" | "parameter" | "constant")? ("input" | "output")?;
	public Type_prefixElements getType_prefixAccess() {
		return (pType_prefix != null) ? pType_prefix : (pType_prefix = new Type_prefixElements());
	}
	
	public ParserRule getType_prefixRule() {
		return getType_prefixAccess().getRule();
	}

	//////////////////////////////////////////Enumerations///////////////////////
	//enum_list:
	//	{enum_list} Ennum_Lit=enumeration_literal ("," E_literal+=enumeration_literal)*;
	public Enum_listElements getEnum_listAccess() {
		return (pEnum_list != null) ? pEnum_list : (pEnum_list = new Enum_listElements());
	}
	
	public ParserRule getEnum_listRule() {
		return getEnum_listAccess().getRule();
	}

	//enumeration_literal:
	//	IDENT comment;
	public Enumeration_literalElements getEnumeration_literalAccess() {
		return (pEnumeration_literal != null) ? pEnumeration_literal : (pEnumeration_literal = new Enumeration_literalElements());
	}
	
	public ParserRule getEnumeration_literalRule() {
		return getEnumeration_literalAccess().getRule();
	}

	//////////////////////////////////////////Composition////////////////////////
	//composition:
	//	E_List_Initial=element_list ("public" E_List_Public+=element_list | "protected" E_List_Protected+=element_list |
	//	Eqn_section+=equation_section | Alg_section+=algorithm_section)* ("external" Lang_Spec=language_specification?
	//	External_F_C=external_function_call? Annotation1=annotation? ";")? (Annotation2=annotation ";")?;
	public CompositionElements getCompositionAccess() {
		return (pComposition != null) ? pComposition : (pComposition = new CompositionElements());
	}
	
	public ParserRule getCompositionRule() {
		return getCompositionAccess().getRule();
	}

	//////////////////////////////////////////Element////////////////////////////
	//element_list:
	//	{element_list} (Element+=element ";")*;
	public Element_listElements getElement_listAccess() {
		return (pElement_list != null) ? pElement_list : (pElement_list = new Element_listElements());
	}
	
	public ParserRule getElement_listRule() {
		return getElement_listAccess().getRule();
	}

	//element:
	//	import_clause | extends_clause | "redeclare"? "final"? "inner"? "outer"? ((Class_Def=class_definition |
	//	Comp_Clause=component_clause) | "replaceable" (Class_Def=class_definition | Comp_Clause=component_clause)
	//	(Constrain_Clause=constraining_clause comment)?);
	public ElementElements getElementAccess() {
		return (pElement != null) ? pElement : (pElement = new ElementElements());
	}
	
	public ParserRule getElementRule() {
		return getElementAccess().getRule();
	}

	//////////////////////////////////////////Equation///////////////////////////
	//equation_section:
	//	{equation_section} "initial"? "equation" (Eqn+=equation ";")*;
	public Equation_sectionElements getEquation_sectionAccess() {
		return (pEquation_section != null) ? pEquation_section : (pEquation_section = new Equation_sectionElements());
	}
	
	public ParserRule getEquation_sectionRule() {
		return getEquation_sectionAccess().getRule();
	}

	//equation:
	//	(sim=simple_expression "=" expr=expression //| Fun_ID=IDENT fun=function_call_args
	//	| IF_EQN=if_equation | FOR_EQN=for_equation | Con=connect_clause | WHEN_EQN=when_equation) Comment=comment;
	public EquationElements getEquationAccess() {
		return (pEquation != null) ? pEquation : (pEquation = new EquationElements());
	}
	
	public ParserRule getEquationRule() {
		return getEquationAccess().getRule();
	}

	////////////////////////////////////////////Conditions and Loop Section For Equation//////////////////////
	//when_equation:
	//	"when" whenEpr=expression "then" (Eqn_Then+=equation ";")* ("elsewhen" elseWhenEpr+=expression "then"
	//	(Eqn_Then+=equation ";")*)* "end" "when";
	public When_equationElements getWhen_equationAccess() {
		return (pWhen_equation != null) ? pWhen_equation : (pWhen_equation = new When_equationElements());
	}
	
	public ParserRule getWhen_equationRule() {
		return getWhen_equationAccess().getRule();
	}

	//if_equation:
	//	"if" exprtrue=expression "then" (Eqn+=equation ";")* ("elseif" exprStilltrue+=expression "then" (thenEqn+=equation
	//	";")*)* ("else" (elseEqn+=equation ";")*)? "end" "if";
	public If_equationElements getIf_equationAccess() {
		return (pIf_equation != null) ? pIf_equation : (pIf_equation = new If_equationElements());
	}
	
	public ParserRule getIf_equationRule() {
		return getIf_equationAccess().getRule();
	}

	//for_equation:
	//	"for" for_loop=for_indices "loop" (For_Eqn+=equation ";") "end" "for";
	public For_equationElements getFor_equationAccess() {
		return (pFor_equation != null) ? pFor_equation : (pFor_equation = new For_equationElements());
	}
	
	public ParserRule getFor_equationRule() {
		return getFor_equationAccess().getRule();
	}

	//connect_clause:
	//	"connect" "(" connector1=component_reference "," connector2=component_reference ")";
	public Connect_clauseElements getConnect_clauseAccess() {
		return (pConnect_clause != null) ? pConnect_clause : (pConnect_clause = new Connect_clauseElements());
	}
	
	public ParserRule getConnect_clauseRule() {
		return getConnect_clauseAccess().getRule();
	}

	//////////////////////////////////////////Algorithm//////////////////////////
	//algorithm_section:
	//	{algorithm_clause} "initial"? "algorithm" (Algorithm+=statement ";")*;
	public Algorithm_sectionElements getAlgorithm_sectionAccess() {
		return (pAlgorithm_section != null) ? pAlgorithm_section : (pAlgorithm_section = new Algorithm_sectionElements());
	}
	
	public ParserRule getAlgorithm_sectionRule() {
		return getAlgorithm_sectionAccess().getRule();
	}

	//statement:
	//	{statement} (If_statement=if_statement //| Algo_M_G=Algorithm_Macros_GEN_SIGNAL
	//	//| Algo_G_I=Algorithm_Macros_GEN_INC
	//	//| Algo_G_C=Algorithm_Macros_GEN_CHANGE
	//	| State_OutputExprList=state_OutputExprList | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
	//	When_statement=when_statement | While_statement=while_statement | "break") comment;
	public StatementElements getStatementAccess() {
		return (pStatement != null) ? pStatement : (pStatement = new StatementElements());
	}
	
	public ParserRule getStatementRule() {
		return getStatementAccess().getRule();
	}

	/////////////////////////////////////Statements//////////////////////////////
	//state_OutputExprList:
	//	"(" output_expression_list=output_expression_list ")" ":=" Component_ref=component_reference
	//	fun_call_args=function_call_args;
	public State_OutputExprListElements getState_OutputExprListAccess() {
		return (pState_OutputExprList != null) ? pState_OutputExprList : (pState_OutputExprList = new State_OutputExprListElements());
	}
	
	public ParserRule getState_OutputExprListRule() {
		return getState_OutputExprListAccess().getRule();
	}

	//state_Comp_Ref:
	//	component_reference=component_reference (":=" expr=expression | fun_call_args=function_call_args);
	public State_Comp_RefElements getState_Comp_RefAccess() {
		return (pState_Comp_Ref != null) ? pState_Comp_Ref : (pState_Comp_Ref = new State_Comp_RefElements());
	}
	
	public ParserRule getState_Comp_RefRule() {
		return getState_Comp_RefAccess().getRule();
	}

	//when_statement:
	//	"when" When_expr=expression "then" (When_stat_true+=statement ";")* ("elsewhen" Else_When_expr+=expression "then"
	//	(Then_statement+=statement ";")*)* "end" "when";
	public When_statementElements getWhen_statementAccess() {
		return (pWhen_statement != null) ? pWhen_statement : (pWhen_statement = new When_statementElements());
	}
	
	public ParserRule getWhen_statementRule() {
		return getWhen_statementAccess().getRule();
	}

	//while_statement:
	//	"while" while_expr=expression "loop" (While_stat+=while_statement ";")* "end" "while";
	public While_statementElements getWhile_statementAccess() {
		return (pWhile_statement != null) ? pWhile_statement : (pWhile_statement = new While_statementElements());
	}
	
	public ParserRule getWhile_statementRule() {
		return getWhile_statementAccess().getRule();
	}

	//for_statement:
	//	"for" For_indices=for_indices "loop" (For_statement+=for_statement ";")* "end" "for";
	public For_statementElements getFor_statementAccess() {
		return (pFor_statement != null) ? pFor_statement : (pFor_statement = new For_statementElements());
	}
	
	public ParserRule getFor_statementRule() {
		return getFor_statementAccess().getRule();
	}

	//if_statement:
	//	"if" exprtrue=expression "then" (state+=statement ";")* ("elseif" exprStilltrue+=expression "then" (state+=statement
	//	";")*)* ("else" (state+=statement ";")*)? "end" "if";
	public If_statementElements getIf_statementAccess() {
		return (pIf_statement != null) ? pIf_statement : (pIf_statement = new If_statementElements());
	}
	
	public ParserRule getIf_statementRule() {
		return getIf_statementAccess().getRule();
	}

	//////////////////////////////////////////Extend/////////////////////////////
	//extends_clause:
	//	"extends" Name=name Class_Mod=class_modification? Annotation=annotation?;
	public Extends_clauseElements getExtends_clauseAccess() {
		return (pExtends_clause != null) ? pExtends_clause : (pExtends_clause = new Extends_clauseElements());
	}
	
	public ParserRule getExtends_clauseRule() {
		return getExtends_clauseAccess().getRule();
	}

	//////////////////////////////////////////Annotation/////////////////////////
	//annotation:
	//	"annotation" Class_Mod=class_modification;
	public AnnotationElements getAnnotationAccess() {
		return (pAnnotation != null) ? pAnnotation : (pAnnotation = new AnnotationElements());
	}
	
	public ParserRule getAnnotationRule() {
		return getAnnotationAccess().getRule();
	}

	//////////////////////////////////////////Import/////////////////////////////
	//import_clause:
	//	"import" (IDENT "=" Name=name | Name=name ("." "*")?) comment;
	public Import_clauseElements getImport_clauseAccess() {
		return (pImport_clause != null) ? pImport_clause : (pImport_clause = new Import_clauseElements());
	}
	
	public ParserRule getImport_clauseRule() {
		return getImport_clauseAccess().getRule();
	}

	//////////////////////////////////////////External///////////////////////////
	//language_specification:
	//	Str=STRING;
	public Language_specificationElements getLanguage_specificationAccess() {
		return (pLanguage_specification != null) ? pLanguage_specification : (pLanguage_specification = new Language_specificationElements());
	}
	
	public ParserRule getLanguage_specificationRule() {
		return getLanguage_specificationAccess().getRule();
	}

	//external_function_call:
	//	{external_function_call} (component_reference=component_reference "=")? IDENT "(" E_List=expression_list? ")";
	public External_function_callElements getExternal_function_callAccess() {
		return (pExternal_function_call != null) ? pExternal_function_call : (pExternal_function_call = new External_function_callElements());
	}
	
	public ParserRule getExternal_function_callRule() {
		return getExternal_function_callAccess().getRule();
	}

	//////////////////////////////////////////Expressions////////////////////////
	//expression:
	//	Expr=simple_expression | conditional_expr;
	public ModeleditorGrammarAccess.ExpressionElements getExpressionAccess() {
		return gaModeleditor.getExpressionAccess();
	}
	
	public ParserRule getExpressionRule() {
		return getExpressionAccess().getRule();
	}

	//simple_expression:
	//	Log_Exp=logical_expression (":" S_Logical_expression=logical_expression (":"
	//	L_Logical_expression=logical_expression)?)?;
	public ModeleditorGrammarAccess.Simple_expressionElements getSimple_expressionAccess() {
		return gaModeleditor.getSimple_expressionAccess();
	}
	
	public ParserRule getSimple_expressionRule() {
		return getSimple_expressionAccess().getRule();
	}

	//conditional_expr:
	//	"if" ifexpr=expression "then" thenexpr=expression ("elseif" elseifexpr+=expression "then" trueexpr+=expression)*
	//	("else" falseexpr=expression);
	public ModeleditorGrammarAccess.Conditional_exprElements getConditional_exprAccess() {
		return gaModeleditor.getConditional_exprAccess();
	}
	
	public ParserRule getConditional_exprRule() {
		return getConditional_exprAccess().getRule();
	}

	//logical_expression:
	//	logical_term ("or" Logical_term+=logical_term)*;
	public ModeleditorGrammarAccess.Logical_expressionElements getLogical_expressionAccess() {
		return gaModeleditor.getLogical_expressionAccess();
	}
	
	public ParserRule getLogical_expressionRule() {
		return getLogical_expressionAccess().getRule();
	}

	//logical_term:
	//	logical_factor ("and" Logical_factor+=logical_factor)*;
	public ModeleditorGrammarAccess.Logical_termElements getLogical_termAccess() {
		return gaModeleditor.getLogical_termAccess();
	}
	
	public ParserRule getLogical_termRule() {
		return getLogical_termAccess().getRule();
	}

	//logical_factor:
	//	"not"? Relation=relation;
	public ModeleditorGrammarAccess.Logical_factorElements getLogical_factorAccess() {
		return gaModeleditor.getLogical_factorAccess();
	}
	
	public ParserRule getLogical_factorRule() {
		return getLogical_factorAccess().getRule();
	}

	//relation:
	//	arithmetic_expression (op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal |
	//	rel_op_assignment | rel_op_Oper) Arithmetic_expression=arithmetic_expression)?;
	public ModeleditorGrammarAccess.RelationElements getRelationAccess() {
		return gaModeleditor.getRelationAccess();
	}
	
	public ParserRule getRelationRule() {
		return getRelationAccess().getRule();
	}

	//arithmetic_expression:
	//	opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)? Term=term (Oper1+=(add_op_plus | add_op_minus |
	//	add_op_dotplus | add_op_dotminus) Term1+=term)*;
	public ModeleditorGrammarAccess.Arithmetic_expressionElements getArithmetic_expressionAccess() {
		return gaModeleditor.getArithmetic_expressionAccess();
	}
	
	public ParserRule getArithmetic_expressionRule() {
		return getArithmetic_expressionAccess().getRule();
	}

	//term:
	//	factor (op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*;
	public ModeleditorGrammarAccess.TermElements getTermAccess() {
		return gaModeleditor.getTermAccess();
	}
	
	public ParserRule getTermRule() {
		return getTermAccess().getRule();
	}

	//factor:
	//	primary (("^" | ".^") Primary=primary)?;
	public ModeleditorGrammarAccess.FactorElements getFactorAccess() {
		return gaModeleditor.getFactorAccess();
	}
	
	public ParserRule getFactorRule() {
		return getFactorAccess().getRule();
	}

	////////////////////////////////////////////////////Primary///////////////////////////////
	//primary:
	//	num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | name_Function | initial_ref | ExprDer |
	//	Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
	//	Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
	//	End="end";
	public ModeleditorGrammarAccess.PrimaryElements getPrimaryAccess() {
		return gaModeleditor.getPrimaryAccess();
	}
	
	public ParserRule getPrimaryRule() {
		return getPrimaryAccess().getRule();
	}

	//name_Function:
	//	name Function_call_args=function_call_args;
	public ModeleditorGrammarAccess.Name_FunctionElements getName_FunctionAccess() {
		return gaModeleditor.getName_FunctionAccess();
	}
	
	public ParserRule getName_FunctionRule() {
		return getName_FunctionAccess().getRule();
	}

	//initial_ref:
	//	"initial" function_call_args;
	public ModeleditorGrammarAccess.Initial_refElements getInitial_refAccess() {
		return gaModeleditor.getInitial_refAccess();
	}
	
	public ParserRule getInitial_refRule() {
		return getInitial_refAccess().getRule();
	}

	//ExprDer:
	//	"der" functionArgs=function_call_args;
	public ModeleditorGrammarAccess.ExprDerElements getExprDerAccess() {
		return gaModeleditor.getExprDerAccess();
	}
	
	public ParserRule getExprDerRule() {
		return getExprDerAccess().getRule();
	}

	//function_call_args:
	//	{function_call_args} "(" f_arg=function_arguments? ")";
	public ModeleditorGrammarAccess.Function_call_argsElements getFunction_call_argsAccess() {
		return gaModeleditor.getFunction_call_argsAccess();
	}
	
	public ParserRule getFunction_call_argsRule() {
		return getFunction_call_argsAccess().getRule();
	}

	//expression_list:
	//	expr=expression ("," Expre+=expression)*;
	public ModeleditorGrammarAccess.Expression_listElements getExpression_listAccess() {
		return gaModeleditor.getExpression_listAccess();
	}
	
	public ParserRule getExpression_listRule() {
		return getExpression_listAccess().getRule();
	}

	/////////////////////////////////Component Reference////////////////////////
	//name:
	//	"."? name_ID=IDENT ("." nam_ID+=IDENT)*;
	public ModeleditorGrammarAccess.NameElements getNameAccess() {
		return gaModeleditor.getNameAccess();
	}
	
	public ParserRule getNameRule() {
		return getNameAccess().getRule();
	}

	//component_reference:
	//	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
	public ModeleditorGrammarAccess.Component_referenceElements getComponent_referenceAccess() {
		return gaModeleditor.getComponent_referenceAccess();
	}
	
	public ParserRule getComponent_referenceRule() {
		return getComponent_referenceAccess().getRule();
	}

	//output_expression_list:
	//	{output_expression_list} epr=expression? ("," Expr+=expression?)*;
	public ModeleditorGrammarAccess.Output_expression_listElements getOutput_expression_listAccess() {
		return gaModeleditor.getOutput_expression_listAccess();
	}
	
	public ParserRule getOutput_expression_listRule() {
		return getOutput_expression_listAccess().getRule();
	}

	//array_subscripts:
	//	"[" Sub=subscript ("," Subscript+=subscript)* "]";
	public ModeleditorGrammarAccess.Array_subscriptsElements getArray_subscriptsAccess() {
		return gaModeleditor.getArray_subscriptsAccess();
	}
	
	public ParserRule getArray_subscriptsRule() {
		return getArray_subscriptsAccess().getRule();
	}

	//subscript:
	//	{subscript} ":" | expr=expression;
	public ModeleditorGrammarAccess.SubscriptElements getSubscriptAccess() {
		return gaModeleditor.getSubscriptAccess();
	}
	
	public ParserRule getSubscriptRule() {
		return getSubscriptAccess().getRule();
	}

	//function_arguments:
	//	{function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)? |
	//	name_arg=named_arguments;
	public ModeleditorGrammarAccess.Function_argumentsElements getFunction_argumentsAccess() {
		return gaModeleditor.getFunction_argumentsAccess();
	}
	
	public ParserRule getFunction_argumentsRule() {
		return getFunction_argumentsAccess().getRule();
	}

	//Fun_Arguments_exp:
	//	"," Args=function_arguments;
	public ModeleditorGrammarAccess.Fun_Arguments_expElements getFun_Arguments_expAccess() {
		return gaModeleditor.getFun_Arguments_expAccess();
	}
	
	public ParserRule getFun_Arguments_expRule() {
		return getFun_Arguments_expAccess().getRule();
	}

	//Fun_Arguments_for:
	//	"for" For_indices=for_indices;
	public ModeleditorGrammarAccess.Fun_Arguments_forElements getFun_Arguments_forAccess() {
		return gaModeleditor.getFun_Arguments_forAccess();
	}
	
	public ParserRule getFun_Arguments_forRule() {
		return getFun_Arguments_forAccess().getRule();
	}

	//named_arguments:
	//	named_argument ("," Named_arguments=named_arguments)?;
	public ModeleditorGrammarAccess.Named_argumentsElements getNamed_argumentsAccess() {
		return gaModeleditor.getNamed_argumentsAccess();
	}
	
	public ParserRule getNamed_argumentsRule() {
		return getNamed_argumentsAccess().getRule();
	}

	//named_argument:
	//	arg=IDENT "=" expr=expression;
	public ModeleditorGrammarAccess.Named_argumentElements getNamed_argumentAccess() {
		return gaModeleditor.getNamed_argumentAccess();
	}
	
	public ParserRule getNamed_argumentRule() {
		return getNamed_argumentAccess().getRule();
	}

	//for_indices:
	//	for_index ("," For_index+=for_index)*;
	public ModeleditorGrammarAccess.For_indicesElements getFor_indicesAccess() {
		return gaModeleditor.getFor_indicesAccess();
	}
	
	public ParserRule getFor_indicesRule() {
		return getFor_indicesAccess().getRule();
	}

	//for_index:
	//	index=IDENT ("in" expr=expression)?;
	public ModeleditorGrammarAccess.For_indexElements getFor_indexAccess() {
		return gaModeleditor.getFor_indexAccess();
	}
	
	public ParserRule getFor_indexRule() {
		return getFor_indexAccess().getRule();
	}

	/////////////////////////////////Operators/////////////////////////////////////////
	//mul_op_mul:
	//	"*";
	public ModeleditorGrammarAccess.Mul_op_mulElements getMul_op_mulAccess() {
		return gaModeleditor.getMul_op_mulAccess();
	}
	
	public ParserRule getMul_op_mulRule() {
		return getMul_op_mulAccess().getRule();
	}

	//mul_op_div:
	//	"/";
	public ModeleditorGrammarAccess.Mul_op_divElements getMul_op_divAccess() {
		return gaModeleditor.getMul_op_divAccess();
	}
	
	public ParserRule getMul_op_divRule() {
		return getMul_op_divAccess().getRule();
	}

	//mul_op_dotmul:
	//	".*";
	public ModeleditorGrammarAccess.Mul_op_dotmulElements getMul_op_dotmulAccess() {
		return gaModeleditor.getMul_op_dotmulAccess();
	}
	
	public ParserRule getMul_op_dotmulRule() {
		return getMul_op_dotmulAccess().getRule();
	}

	//mul_op_dotdiv:
	//	"./";
	public ModeleditorGrammarAccess.Mul_op_dotdivElements getMul_op_dotdivAccess() {
		return gaModeleditor.getMul_op_dotdivAccess();
	}
	
	public ParserRule getMul_op_dotdivRule() {
		return getMul_op_dotdivAccess().getRule();
	}

	//add_op_plus:
	//	"+";
	public ModeleditorGrammarAccess.Add_op_plusElements getAdd_op_plusAccess() {
		return gaModeleditor.getAdd_op_plusAccess();
	}
	
	public ParserRule getAdd_op_plusRule() {
		return getAdd_op_plusAccess().getRule();
	}

	//add_op_minus:
	//	"-";
	public ModeleditorGrammarAccess.Add_op_minusElements getAdd_op_minusAccess() {
		return gaModeleditor.getAdd_op_minusAccess();
	}
	
	public ParserRule getAdd_op_minusRule() {
		return getAdd_op_minusAccess().getRule();
	}

	//add_op_dotplus:
	//	".+";
	public ModeleditorGrammarAccess.Add_op_dotplusElements getAdd_op_dotplusAccess() {
		return gaModeleditor.getAdd_op_dotplusAccess();
	}
	
	public ParserRule getAdd_op_dotplusRule() {
		return getAdd_op_dotplusAccess().getRule();
	}

	//add_op_dotminus:
	//	".-";
	public ModeleditorGrammarAccess.Add_op_dotminusElements getAdd_op_dotminusAccess() {
		return gaModeleditor.getAdd_op_dotminusAccess();
	}
	
	public ParserRule getAdd_op_dotminusRule() {
		return getAdd_op_dotminusAccess().getRule();
	}

	//rel_op_Less_then:
	//	"<";
	public ModeleditorGrammarAccess.Rel_op_Less_thenElements getRel_op_Less_thenAccess() {
		return gaModeleditor.getRel_op_Less_thenAccess();
	}
	
	public ParserRule getRel_op_Less_thenRule() {
		return getRel_op_Less_thenAccess().getRule();
	}

	//rel_op_Less_equal:
	//	"<=";
	public ModeleditorGrammarAccess.Rel_op_Less_equalElements getRel_op_Less_equalAccess() {
		return gaModeleditor.getRel_op_Less_equalAccess();
	}
	
	public ParserRule getRel_op_Less_equalRule() {
		return getRel_op_Less_equalAccess().getRule();
	}

	//rel_op_greater_then:
	//	">";
	public ModeleditorGrammarAccess.Rel_op_greater_thenElements getRel_op_greater_thenAccess() {
		return gaModeleditor.getRel_op_greater_thenAccess();
	}
	
	public ParserRule getRel_op_greater_thenRule() {
		return getRel_op_greater_thenAccess().getRule();
	}

	//rel_op_greater_equal:
	//	">=";
	public ModeleditorGrammarAccess.Rel_op_greater_equalElements getRel_op_greater_equalAccess() {
		return gaModeleditor.getRel_op_greater_equalAccess();
	}
	
	public ParserRule getRel_op_greater_equalRule() {
		return getRel_op_greater_equalAccess().getRule();
	}

	//rel_op_assignment:
	//	"==";
	public ModeleditorGrammarAccess.Rel_op_assignmentElements getRel_op_assignmentAccess() {
		return gaModeleditor.getRel_op_assignmentAccess();
	}
	
	public ParserRule getRel_op_assignmentRule() {
		return getRel_op_assignmentAccess().getRule();
	}

	//rel_op_Oper:
	//	"<>";
	public ModeleditorGrammarAccess.Rel_op_OperElements getRel_op_OperAccess() {
		return gaModeleditor.getRel_op_OperAccess();
	}
	
	public ParserRule getRel_op_OperRule() {
		return getRel_op_OperAccess().getRule();
	}

	/////////////////////////////////Comments/////////////////////////////////////
	//comment:
	//	string_comment;
	public ModeleditorGrammarAccess.CommentElements getCommentAccess() {
		return gaModeleditor.getCommentAccess();
	}
	
	public ParserRule getCommentRule() {
		return getCommentAccess().getRule();
	}

	//string_comment:
	//	(STRING ("+" STRING)*)?;
	public ModeleditorGrammarAccess.String_commentElements getString_commentAccess() {
		return gaModeleditor.getString_commentAccess();
	}
	
	public ParserRule getString_commentRule() {
		return getString_commentAccess().getRule();
	}

	/////////////////////////////////////////// Terminals////////////////////////
	//terminal UNSIGNED_NUMBER:
	//	"0".."9"+ "." "0".."9"* (("E" | "e") ("+" | "-")? "0".."9"+)? | "0".."9"+ ("E" | "e") ("+" | "-")? "0".."9"+;
	public TerminalRule getUNSIGNED_NUMBERRule() {
		return gaModeleditor.getUNSIGNED_NUMBERRule();
	} 

	//terminal BOOL_VAL:
	//	"true" | "false";
	public TerminalRule getBOOL_VALRule() {
		return gaModeleditor.getBOOL_VALRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaModeleditor.getINTRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"";
	public TerminalRule getSTRINGRule() {
		return gaModeleditor.getSTRINGRule();
	} 

	//terminal IDENT:
	//	("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9" | ".")*;
	public TerminalRule getIDENTRule() {
		return gaModeleditor.getIDENTRule();
	} 

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaModeleditor.getIDRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaModeleditor.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaModeleditor.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaModeleditor.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaModeleditor.getANY_OTHERRule();
	} 
}
