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

package org.openmodelica.modelicaml.editor.xtext.state.services;

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
public class StatetransitionguardexpressionGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class GuardExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "guardExpression");
		private final Assignment cExpressionAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cExpressionExpressionParserRuleCall_0 = (RuleCall)cExpressionAssignment.eContents().get(0);
		
		//guardExpression:
		//	Expression=expression?;
		public ParserRule getRule() { return rule; }

		//Expression=expression?
		public Assignment getExpressionAssignment() { return cExpressionAssignment; }

		//expression
		public RuleCall getExpressionExpressionParserRuleCall_0() { return cExpressionExpressionParserRuleCall_0; }
	}

	public class ExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "expression");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cExpressionAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cSimple_ExprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cSimple_ExprSimple_expressionParserRuleCall_1_0 = (RuleCall)cSimple_ExprAssignment_1.eContents().get(0);
		
		//expression:
		//	{expression} simple_Expr=simple_expression;
		public ParserRule getRule() { return rule; }

		//{expression} simple_Expr=simple_expression
		public Group getGroup() { return cGroup; }

		//{expression}
		public Action getExpressionAction_0() { return cExpressionAction_0; }

		//simple_Expr=simple_expression
		public Assignment getSimple_ExprAssignment_1() { return cSimple_ExprAssignment_1; }

		//simple_expression
		public RuleCall getSimple_ExprSimple_expressionParserRuleCall_1_0() { return cSimple_ExprSimple_expressionParserRuleCall_1_0; }
	}

	public class PrimaryElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "primary");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Assignment cNumAssignment_0 = (Assignment)cAlternatives.eContents().get(0);
		private final RuleCall cNumUNSIGNED_NUMBERTerminalRuleCall_0_0 = (RuleCall)cNumAssignment_0.eContents().get(0);
		private final Assignment cIntAssignment_1 = (Assignment)cAlternatives.eContents().get(1);
		private final RuleCall cIntINTTerminalRuleCall_1_0 = (RuleCall)cIntAssignment_1.eContents().get(0);
		private final Assignment cStrAssignment_2 = (Assignment)cAlternatives.eContents().get(2);
		private final RuleCall cStrSTRINGTerminalRuleCall_2_0 = (RuleCall)cStrAssignment_2.eContents().get(0);
		private final Assignment cBoolAssignment_3 = (Assignment)cAlternatives.eContents().get(3);
		private final RuleCall cBoolBOOL_VALTerminalRuleCall_3_0 = (RuleCall)cBoolAssignment_3.eContents().get(0);
		private final Assignment cName_FunctionAssignment_4 = (Assignment)cAlternatives.eContents().get(4);
		private final RuleCall cName_FunctionName_FunctionParserRuleCall_4_0 = (RuleCall)cName_FunctionAssignment_4.eContents().get(0);
		private final Assignment cInitial_refAssignment_5 = (Assignment)cAlternatives.eContents().get(5);
		private final RuleCall cInitial_refInitial_refParserRuleCall_5_0 = (RuleCall)cInitial_refAssignment_5.eContents().get(0);
		private final Assignment cExprAssignment_6 = (Assignment)cAlternatives.eContents().get(6);
		private final RuleCall cExprExprDerParserRuleCall_6_0 = (RuleCall)cExprAssignment_6.eContents().get(0);
		private final Assignment cMac_AAssignment_7 = (Assignment)cAlternatives.eContents().get(7);
		private final RuleCall cMac_AMacro_AfterParserRuleCall_7_0 = (RuleCall)cMac_AAssignment_7.eContents().get(0);
		private final Assignment cMac_EAssignment_8 = (Assignment)cAlternatives.eContents().get(8);
		private final RuleCall cMac_EMacro_EVENTParserRuleCall_8_0 = (RuleCall)cMac_EAssignment_8.eContents().get(0);
		private final Assignment cMac_CAssignment_9 = (Assignment)cAlternatives.eContents().get(9);
		private final RuleCall cMac_CMacro_CHANGEParserRuleCall_9_0 = (RuleCall)cMac_CAssignment_9.eContents().get(0);
		private final Assignment cMac_SAssignment_10 = (Assignment)cAlternatives.eContents().get(10);
		private final RuleCall cMac_SMacro_SIGNALParserRuleCall_10_0 = (RuleCall)cMac_SAssignment_10.eContents().get(0);
		private final Assignment cComponent_referenceAssignment_11 = (Assignment)cAlternatives.eContents().get(11);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_11_0 = (RuleCall)cComponent_referenceAssignment_11.eContents().get(0);
		private final Group cGroup_12 = (Group)cAlternatives.eContents().get(12);
		private final Keyword cLeftParenthesisKeyword_12_0 = (Keyword)cGroup_12.eContents().get(0);
		private final Assignment cOutput_expr_listAssignment_12_1 = (Assignment)cGroup_12.eContents().get(1);
		private final RuleCall cOutput_expr_listOutput_expression_listParserRuleCall_12_1_0 = (RuleCall)cOutput_expr_listAssignment_12_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_12_2 = (Keyword)cGroup_12.eContents().get(2);
		private final Group cGroup_13 = (Group)cAlternatives.eContents().get(13);
		private final Keyword cLeftSquareBracketKeyword_13_0 = (Keyword)cGroup_13.eContents().get(0);
		private final Assignment cExpre_listAssignment_13_1 = (Assignment)cGroup_13.eContents().get(1);
		private final RuleCall cExpre_listExpression_listParserRuleCall_13_1_0 = (RuleCall)cExpre_listAssignment_13_1.eContents().get(0);
		private final Group cGroup_13_2 = (Group)cGroup_13.eContents().get(2);
		private final Keyword cSemicolonKeyword_13_2_0 = (Keyword)cGroup_13_2.eContents().get(0);
		private final Assignment cExpression_listAssignment_13_2_1 = (Assignment)cGroup_13_2.eContents().get(1);
		private final RuleCall cExpression_listExpression_listParserRuleCall_13_2_1_0 = (RuleCall)cExpression_listAssignment_13_2_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_13_3 = (Keyword)cGroup_13.eContents().get(3);
		private final Group cGroup_14 = (Group)cAlternatives.eContents().get(14);
		private final Keyword cLeftCurlyBracketKeyword_14_0 = (Keyword)cGroup_14.eContents().get(0);
		private final Assignment cF_argumentsAssignment_14_1 = (Assignment)cGroup_14.eContents().get(1);
		private final RuleCall cF_argumentsFunction_argumentsParserRuleCall_14_1_0 = (RuleCall)cF_argumentsAssignment_14_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_14_2 = (Keyword)cGroup_14.eContents().get(2);
		private final Assignment cEndAssignment_15 = (Assignment)cAlternatives.eContents().get(15);
		private final Keyword cEndEndKeyword_15_0 = (Keyword)cEndAssignment_15.eContents().get(0);
		private final Assignment cElseAssignment_16 = (Assignment)cAlternatives.eContents().get(16);
		private final Keyword cElseElseKeyword_16_0 = (Keyword)cElseAssignment_16.eContents().get(0);
		
		////////////////////////////////////////////////////Primary///////////////////////////////
		//
		//primary:
		//	num=UNSIGNED_NUMBER //| Mac_B=Macro_BEFORE
		//
		//	| int=INT | str=STRING | Bool=BOOL_VAL | Name_Function=name_Function | Initial_ref=initial_ref | Expr=ExprDer |
		//	Mac_A=Macro_After | Mac_E=Macro_EVENT | Mac_C=Macro_CHANGE | Mac_S=Macro_SIGNAL |
		//	Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
		//	Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
		//	End="end" | Else="else";
		public ParserRule getRule() { return rule; }

		//num=UNSIGNED_NUMBER //| Mac_B=Macro_BEFORE
		//
		//| int=INT | str=STRING | Bool=BOOL_VAL | Name_Function=name_Function | Initial_ref=initial_ref | Expr=ExprDer |
		//Mac_A=Macro_After | Mac_E=Macro_EVENT | Mac_C=Macro_CHANGE | Mac_S=Macro_SIGNAL |
		//Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
		//Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
		//End="end" | Else="else"
		public Alternatives getAlternatives() { return cAlternatives; }

		//num=UNSIGNED_NUMBER
		public Assignment getNumAssignment_0() { return cNumAssignment_0; }

		//UNSIGNED_NUMBER
		public RuleCall getNumUNSIGNED_NUMBERTerminalRuleCall_0_0() { return cNumUNSIGNED_NUMBERTerminalRuleCall_0_0; }

		//int=INT
		public Assignment getIntAssignment_1() { return cIntAssignment_1; }

		//INT
		public RuleCall getIntINTTerminalRuleCall_1_0() { return cIntINTTerminalRuleCall_1_0; }

		//str=STRING
		public Assignment getStrAssignment_2() { return cStrAssignment_2; }

		//STRING
		public RuleCall getStrSTRINGTerminalRuleCall_2_0() { return cStrSTRINGTerminalRuleCall_2_0; }

		//Bool=BOOL_VAL
		public Assignment getBoolAssignment_3() { return cBoolAssignment_3; }

		//BOOL_VAL
		public RuleCall getBoolBOOL_VALTerminalRuleCall_3_0() { return cBoolBOOL_VALTerminalRuleCall_3_0; }

		//Name_Function=name_Function
		public Assignment getName_FunctionAssignment_4() { return cName_FunctionAssignment_4; }

		//name_Function
		public RuleCall getName_FunctionName_FunctionParserRuleCall_4_0() { return cName_FunctionName_FunctionParserRuleCall_4_0; }

		//Initial_ref=initial_ref
		public Assignment getInitial_refAssignment_5() { return cInitial_refAssignment_5; }

		//initial_ref
		public RuleCall getInitial_refInitial_refParserRuleCall_5_0() { return cInitial_refInitial_refParserRuleCall_5_0; }

		//Expr=ExprDer
		public Assignment getExprAssignment_6() { return cExprAssignment_6; }

		//ExprDer
		public RuleCall getExprExprDerParserRuleCall_6_0() { return cExprExprDerParserRuleCall_6_0; }

		//Mac_A=Macro_After
		public Assignment getMac_AAssignment_7() { return cMac_AAssignment_7; }

		//Macro_After
		public RuleCall getMac_AMacro_AfterParserRuleCall_7_0() { return cMac_AMacro_AfterParserRuleCall_7_0; }

		//Mac_E=Macro_EVENT
		public Assignment getMac_EAssignment_8() { return cMac_EAssignment_8; }

		//Macro_EVENT
		public RuleCall getMac_EMacro_EVENTParserRuleCall_8_0() { return cMac_EMacro_EVENTParserRuleCall_8_0; }

		//Mac_C=Macro_CHANGE
		public Assignment getMac_CAssignment_9() { return cMac_CAssignment_9; }

		//Macro_CHANGE
		public RuleCall getMac_CMacro_CHANGEParserRuleCall_9_0() { return cMac_CMacro_CHANGEParserRuleCall_9_0; }

		//Mac_S=Macro_SIGNAL
		public Assignment getMac_SAssignment_10() { return cMac_SAssignment_10; }

		//Macro_SIGNAL
		public RuleCall getMac_SMacro_SIGNALParserRuleCall_10_0() { return cMac_SMacro_SIGNALParserRuleCall_10_0; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_11() { return cComponent_referenceAssignment_11; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_11_0() { return cComponent_referenceComponent_referenceParserRuleCall_11_0; }

		//"(" output_expr_list=output_expression_list ")"
		public Group getGroup_12() { return cGroup_12; }

		//"("
		public Keyword getLeftParenthesisKeyword_12_0() { return cLeftParenthesisKeyword_12_0; }

		//output_expr_list=output_expression_list
		public Assignment getOutput_expr_listAssignment_12_1() { return cOutput_expr_listAssignment_12_1; }

		//output_expression_list
		public RuleCall getOutput_expr_listOutput_expression_listParserRuleCall_12_1_0() { return cOutput_expr_listOutput_expression_listParserRuleCall_12_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_12_2() { return cRightParenthesisKeyword_12_2; }

		//"[" Expre_list=expression_list (";" Expression_list+=expression_list)* "]"
		public Group getGroup_13() { return cGroup_13; }

		//"["
		public Keyword getLeftSquareBracketKeyword_13_0() { return cLeftSquareBracketKeyword_13_0; }

		//Expre_list=expression_list
		public Assignment getExpre_listAssignment_13_1() { return cExpre_listAssignment_13_1; }

		//expression_list
		public RuleCall getExpre_listExpression_listParserRuleCall_13_1_0() { return cExpre_listExpression_listParserRuleCall_13_1_0; }

		//(";" Expression_list+=expression_list)*
		public Group getGroup_13_2() { return cGroup_13_2; }

		//";"
		public Keyword getSemicolonKeyword_13_2_0() { return cSemicolonKeyword_13_2_0; }

		//Expression_list+=expression_list
		public Assignment getExpression_listAssignment_13_2_1() { return cExpression_listAssignment_13_2_1; }

		//expression_list
		public RuleCall getExpression_listExpression_listParserRuleCall_13_2_1_0() { return cExpression_listExpression_listParserRuleCall_13_2_1_0; }

		//"]"
		public Keyword getRightSquareBracketKeyword_13_3() { return cRightSquareBracketKeyword_13_3; }

		//"{" f_arguments=function_arguments "}"
		public Group getGroup_14() { return cGroup_14; }

		//"{"
		public Keyword getLeftCurlyBracketKeyword_14_0() { return cLeftCurlyBracketKeyword_14_0; }

		//f_arguments=function_arguments
		public Assignment getF_argumentsAssignment_14_1() { return cF_argumentsAssignment_14_1; }

		//function_arguments
		public RuleCall getF_argumentsFunction_argumentsParserRuleCall_14_1_0() { return cF_argumentsFunction_argumentsParserRuleCall_14_1_0; }

		//"}"
		public Keyword getRightCurlyBracketKeyword_14_2() { return cRightCurlyBracketKeyword_14_2; }

		//End="end"
		public Assignment getEndAssignment_15() { return cEndAssignment_15; }

		//"end"
		public Keyword getEndEndKeyword_15_0() { return cEndEndKeyword_15_0; }

		//Else="else"
		public Assignment getElseAssignment_16() { return cElseAssignment_16; }

		//"else"
		public Keyword getElseElseKeyword_16_0() { return cElseElseKeyword_16_0; }
	}

	public class Macro_AfterElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Macro_After");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cAFTERKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Alternatives cAlternatives_2 = (Alternatives)cGroup.eContents().get(2);
		private final Assignment cIntAssignment_2_0 = (Assignment)cAlternatives_2.eContents().get(0);
		private final RuleCall cIntINTTerminalRuleCall_2_0_0 = (RuleCall)cIntAssignment_2_0.eContents().get(0);
		private final Assignment cNumAssignment_2_1 = (Assignment)cAlternatives_2.eContents().get(1);
		private final RuleCall cNumUNSIGNED_NUMBERTerminalRuleCall_2_1_0 = (RuleCall)cNumAssignment_2_1.eContents().get(0);
		private final Assignment cComponent_referenceAssignment_2_2 = (Assignment)cAlternatives_2.eContents().get(2);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_2_2_0 = (RuleCall)cComponent_referenceAssignment_2_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		////////////////////////////////////////MACRO//////////////////////////////
		//
		//Macro_After:
		//	"AFTER" "(" (int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference) ")";
		public ParserRule getRule() { return rule; }

		//"AFTER" "(" (int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference) ")"
		public Group getGroup() { return cGroup; }

		//"AFTER"
		public Keyword getAFTERKeyword_0() { return cAFTERKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference
		public Alternatives getAlternatives_2() { return cAlternatives_2; }

		//int=INT
		public Assignment getIntAssignment_2_0() { return cIntAssignment_2_0; }

		//INT
		public RuleCall getIntINTTerminalRuleCall_2_0_0() { return cIntINTTerminalRuleCall_2_0_0; }

		//num=UNSIGNED_NUMBER
		public Assignment getNumAssignment_2_1() { return cNumAssignment_2_1; }

		//UNSIGNED_NUMBER
		public RuleCall getNumUNSIGNED_NUMBERTerminalRuleCall_2_1_0() { return cNumUNSIGNED_NUMBERTerminalRuleCall_2_1_0; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_2_2() { return cComponent_referenceAssignment_2_2; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_2_2_0() { return cComponent_referenceComponent_referenceParserRuleCall_2_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class Macro_BEFOREElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Macro_BEFORE");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cBEFOREKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Alternatives cAlternatives_2 = (Alternatives)cGroup.eContents().get(2);
		private final Assignment cIntAssignment_2_0 = (Assignment)cAlternatives_2.eContents().get(0);
		private final RuleCall cIntINTTerminalRuleCall_2_0_0 = (RuleCall)cIntAssignment_2_0.eContents().get(0);
		private final Assignment cNumAssignment_2_1 = (Assignment)cAlternatives_2.eContents().get(1);
		private final RuleCall cNumUNSIGNED_NUMBERTerminalRuleCall_2_1_0 = (RuleCall)cNumAssignment_2_1.eContents().get(0);
		private final Assignment cComponent_referenceAssignment_2_2 = (Assignment)cAlternatives_2.eContents().get(2);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_2_2_0 = (RuleCall)cComponent_referenceAssignment_2_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Macro_BEFORE:
		//	"BEFORE" "(" (int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference) ")";
		public ParserRule getRule() { return rule; }

		//"BEFORE" "(" (int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference) ")"
		public Group getGroup() { return cGroup; }

		//"BEFORE"
		public Keyword getBEFOREKeyword_0() { return cBEFOREKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference
		public Alternatives getAlternatives_2() { return cAlternatives_2; }

		//int=INT
		public Assignment getIntAssignment_2_0() { return cIntAssignment_2_0; }

		//INT
		public RuleCall getIntINTTerminalRuleCall_2_0_0() { return cIntINTTerminalRuleCall_2_0_0; }

		//num=UNSIGNED_NUMBER
		public Assignment getNumAssignment_2_1() { return cNumAssignment_2_1; }

		//UNSIGNED_NUMBER
		public RuleCall getNumUNSIGNED_NUMBERTerminalRuleCall_2_1_0() { return cNumUNSIGNED_NUMBERTerminalRuleCall_2_1_0; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_2_2() { return cComponent_referenceAssignment_2_2; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_2_2_0() { return cComponent_referenceComponent_referenceParserRuleCall_2_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class Macro_SIGNALElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Macro_SIGNAL");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cSIGNALKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_2_0 = (RuleCall)cComponent_referenceAssignment_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Macro_SIGNAL:
		//	"SIGNAL" "(" Component_reference=component_reference ")";
		public ParserRule getRule() { return rule; }

		//"SIGNAL" "(" Component_reference=component_reference ")"
		public Group getGroup() { return cGroup; }

		//"SIGNAL"
		public Keyword getSIGNALKeyword_0() { return cSIGNALKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_2() { return cComponent_referenceAssignment_2; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_2_0() { return cComponent_referenceComponent_referenceParserRuleCall_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class Macro_EVENTElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Macro_EVENT");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cEVENTKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_2_0 = (RuleCall)cComponent_referenceAssignment_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Macro_EVENT:
		//	"EVENT" "(" Component_reference=component_reference ")";
		public ParserRule getRule() { return rule; }

		//"EVENT" "(" Component_reference=component_reference ")"
		public Group getGroup() { return cGroup; }

		//"EVENT"
		public Keyword getEVENTKeyword_0() { return cEVENTKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_2() { return cComponent_referenceAssignment_2; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_2_0() { return cComponent_referenceComponent_referenceParserRuleCall_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class Macro_CHANGEElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Macro_CHANGE");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCHANGEKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_2_0 = (RuleCall)cComponent_referenceAssignment_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Macro_CHANGE:
		//	"CHANGE" "(" Component_reference=component_reference ")";
		public ParserRule getRule() { return rule; }

		//"CHANGE" "(" Component_reference=component_reference ")"
		public Group getGroup() { return cGroup; }

		//"CHANGE"
		public Keyword getCHANGEKeyword_0() { return cCHANGEKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_2() { return cComponent_referenceAssignment_2; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_2_0() { return cComponent_referenceComponent_referenceParserRuleCall_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}
	
	
	private GuardExpressionElements pGuardExpression;
	private ExpressionElements pExpression;
	private PrimaryElements pPrimary;
	private Macro_AfterElements pMacro_After;
	private Macro_BEFOREElements pMacro_BEFORE;
	private Macro_SIGNALElements pMacro_SIGNAL;
	private Macro_EVENTElements pMacro_EVENT;
	private Macro_CHANGEElements pMacro_CHANGE;
	
	private final GrammarProvider grammarProvider;

	private ModeleditorGrammarAccess gaModeleditor;

	@Inject
	public StatetransitionguardexpressionGrammarAccess(GrammarProvider grammarProvider,
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

	
	//guardExpression:
	//	Expression=expression?;
	public GuardExpressionElements getGuardExpressionAccess() {
		return (pGuardExpression != null) ? pGuardExpression : (pGuardExpression = new GuardExpressionElements());
	}
	
	public ParserRule getGuardExpressionRule() {
		return getGuardExpressionAccess().getRule();
	}

	//expression:
	//	{expression} simple_Expr=simple_expression;
	public ExpressionElements getExpressionAccess() {
		return (pExpression != null) ? pExpression : (pExpression = new ExpressionElements());
	}
	
	public ParserRule getExpressionRule() {
		return getExpressionAccess().getRule();
	}

	////////////////////////////////////////////////////Primary///////////////////////////////
	//
	//primary:
	//	num=UNSIGNED_NUMBER //| Mac_B=Macro_BEFORE
	//
	//	| int=INT | str=STRING | Bool=BOOL_VAL | Name_Function=name_Function | Initial_ref=initial_ref | Expr=ExprDer |
	//	Mac_A=Macro_After | Mac_E=Macro_EVENT | Mac_C=Macro_CHANGE | Mac_S=Macro_SIGNAL |
	//	Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
	//	Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
	//	End="end" | Else="else";
	public PrimaryElements getPrimaryAccess() {
		return (pPrimary != null) ? pPrimary : (pPrimary = new PrimaryElements());
	}
	
	public ParserRule getPrimaryRule() {
		return getPrimaryAccess().getRule();
	}

	////////////////////////////////////////MACRO//////////////////////////////
	//
	//Macro_After:
	//	"AFTER" "(" (int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference) ")";
	public Macro_AfterElements getMacro_AfterAccess() {
		return (pMacro_After != null) ? pMacro_After : (pMacro_After = new Macro_AfterElements());
	}
	
	public ParserRule getMacro_AfterRule() {
		return getMacro_AfterAccess().getRule();
	}

	//Macro_BEFORE:
	//	"BEFORE" "(" (int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference) ")";
	public Macro_BEFOREElements getMacro_BEFOREAccess() {
		return (pMacro_BEFORE != null) ? pMacro_BEFORE : (pMacro_BEFORE = new Macro_BEFOREElements());
	}
	
	public ParserRule getMacro_BEFORERule() {
		return getMacro_BEFOREAccess().getRule();
	}

	//Macro_SIGNAL:
	//	"SIGNAL" "(" Component_reference=component_reference ")";
	public Macro_SIGNALElements getMacro_SIGNALAccess() {
		return (pMacro_SIGNAL != null) ? pMacro_SIGNAL : (pMacro_SIGNAL = new Macro_SIGNALElements());
	}
	
	public ParserRule getMacro_SIGNALRule() {
		return getMacro_SIGNALAccess().getRule();
	}

	//Macro_EVENT:
	//	"EVENT" "(" Component_reference=component_reference ")";
	public Macro_EVENTElements getMacro_EVENTAccess() {
		return (pMacro_EVENT != null) ? pMacro_EVENT : (pMacro_EVENT = new Macro_EVENTElements());
	}
	
	public ParserRule getMacro_EVENTRule() {
		return getMacro_EVENTAccess().getRule();
	}

	//Macro_CHANGE:
	//	"CHANGE" "(" Component_reference=component_reference ")";
	public Macro_CHANGEElements getMacro_CHANGEAccess() {
		return (pMacro_CHANGE != null) ? pMacro_CHANGE : (pMacro_CHANGE = new Macro_CHANGEElements());
	}
	
	public ParserRule getMacro_CHANGERule() {
		return getMacro_CHANGEAccess().getRule();
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
