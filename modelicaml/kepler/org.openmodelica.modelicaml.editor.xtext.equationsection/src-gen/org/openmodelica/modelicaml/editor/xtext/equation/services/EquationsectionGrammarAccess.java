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

package org.openmodelica.modelicaml.editor.xtext.equation.services;

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
public class EquationsectionGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class Equation_sectionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "equation_section");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cEquation_sectionAction_0 = (Action)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cEqnAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final RuleCall cEqnEquationParserRuleCall_1_0_0 = (RuleCall)cEqnAssignment_1_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		
		//////////////////////////////////////////Equation///////////////////////////
		//equation_section:
		//	{equation_section} (Eqn+=equation ";")*;
		public ParserRule getRule() { return rule; }

		//{equation_section} (Eqn+=equation ";")*
		public Group getGroup() { return cGroup; }

		//{equation_section}
		public Action getEquation_sectionAction_0() { return cEquation_sectionAction_0; }

		//(Eqn+=equation ";")*
		public Group getGroup_1() { return cGroup_1; }

		//Eqn+=equation
		public Assignment getEqnAssignment_1_0() { return cEqnAssignment_1_0; }

		//equation
		public RuleCall getEqnEquationParserRuleCall_1_0_0() { return cEqnEquationParserRuleCall_1_0_0; }

		//";"
		public Keyword getSemicolonKeyword_1_1() { return cSemicolonKeyword_1_1; }
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
		private final RuleCall cIf_equationParserRuleCall_0_1 = (RuleCall)cAlternatives_0.eContents().get(1);
		private final RuleCall cFor_equationParserRuleCall_0_2 = (RuleCall)cAlternatives_0.eContents().get(2);
		private final RuleCall cConnect_clauseParserRuleCall_0_3 = (RuleCall)cAlternatives_0.eContents().get(3);
		private final RuleCall cWhen_equationParserRuleCall_0_4 = (RuleCall)cAlternatives_0.eContents().get(4);
		private final Assignment cCommentAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cCommentCommentParserRuleCall_1_0 = (RuleCall)cCommentAssignment_1.eContents().get(0);
		
		//equation:
		//	(sim=simple_expression "=" expr=expression | if_equation | for_equation | connect_clause | when_equation)
		//	Comment=comment;
		public ParserRule getRule() { return rule; }

		//(sim=simple_expression "=" expr=expression | if_equation | for_equation | connect_clause | when_equation)
		//Comment=comment
		public Group getGroup() { return cGroup; }

		//sim=simple_expression "=" expr=expression | if_equation | for_equation | connect_clause | when_equation
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

		//if_equation
		public RuleCall getIf_equationParserRuleCall_0_1() { return cIf_equationParserRuleCall_0_1; }

		//for_equation
		public RuleCall getFor_equationParserRuleCall_0_2() { return cFor_equationParserRuleCall_0_2; }

		//connect_clause
		public RuleCall getConnect_clauseParserRuleCall_0_3() { return cConnect_clauseParserRuleCall_0_3; }

		//when_equation
		public RuleCall getWhen_equationParserRuleCall_0_4() { return cWhen_equationParserRuleCall_0_4; }

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
		//	"for" for_loop=for_indices "loop" (For_Eqn+=equation ";")* "end" "for";
		public ParserRule getRule() { return rule; }

		//"for" for_loop=for_indices "loop" (For_Eqn+=equation ";")* "end" "for"
		public Group getGroup() { return cGroup; }

		//"for"
		public Keyword getForKeyword_0() { return cForKeyword_0; }

		//for_loop=for_indices
		public Assignment getFor_loopAssignment_1() { return cFor_loopAssignment_1; }

		//for_indices
		public RuleCall getFor_loopFor_indicesParserRuleCall_1_0() { return cFor_loopFor_indicesParserRuleCall_1_0; }

		//"loop"
		public Keyword getLoopKeyword_2() { return cLoopKeyword_2; }

		//(For_Eqn+=equation ";")*
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
	
	
	private Equation_sectionElements pEquation_section;
	private EquationElements pEquation;
	private When_equationElements pWhen_equation;
	private If_equationElements pIf_equation;
	private For_equationElements pFor_equation;
	private Connect_clauseElements pConnect_clause;
	
	private final GrammarProvider grammarProvider;

	private ModeleditorGrammarAccess gaModeleditor;

	@Inject
	public EquationsectionGrammarAccess(GrammarProvider grammarProvider,
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

	
	//////////////////////////////////////////Equation///////////////////////////
	//equation_section:
	//	{equation_section} (Eqn+=equation ";")*;
	public Equation_sectionElements getEquation_sectionAccess() {
		return (pEquation_section != null) ? pEquation_section : (pEquation_section = new Equation_sectionElements());
	}
	
	public ParserRule getEquation_sectionRule() {
		return getEquation_sectionAccess().getRule();
	}

	//equation:
	//	(sim=simple_expression "=" expr=expression | if_equation | for_equation | connect_clause | when_equation)
	//	Comment=comment;
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
	//	"for" for_loop=for_indices "loop" (For_Eqn+=equation ";")* "end" "for";
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
