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

package org.openmodelica.modelicaml.editor.xtext.model.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class ModeleditorGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class ExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "expression");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Assignment cExprAssignment_0 = (Assignment)cAlternatives.eContents().get(0);
		private final RuleCall cExprSimple_expressionParserRuleCall_0_0 = (RuleCall)cExprAssignment_0.eContents().get(0);
		private final RuleCall cConditional_exprParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//////////////////////////////////////////Expressions////////////////////////
		//expression:
		//	Expr=simple_expression | conditional_expr;
		public ParserRule getRule() { return rule; }

		//Expr=simple_expression | conditional_expr
		public Alternatives getAlternatives() { return cAlternatives; }

		//Expr=simple_expression
		public Assignment getExprAssignment_0() { return cExprAssignment_0; }

		//simple_expression
		public RuleCall getExprSimple_expressionParserRuleCall_0_0() { return cExprSimple_expressionParserRuleCall_0_0; }

		//conditional_expr
		public RuleCall getConditional_exprParserRuleCall_1() { return cConditional_exprParserRuleCall_1; }
	}

	public class Simple_expressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "simple_expression");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cLog_ExpAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cLog_ExpLogical_expressionParserRuleCall_0_0 = (RuleCall)cLog_ExpAssignment_0.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cColonKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cS_Logical_expressionAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cS_Logical_expressionLogical_expressionParserRuleCall_1_1_0 = (RuleCall)cS_Logical_expressionAssignment_1_1.eContents().get(0);
		private final Group cGroup_1_2 = (Group)cGroup_1.eContents().get(2);
		private final Keyword cColonKeyword_1_2_0 = (Keyword)cGroup_1_2.eContents().get(0);
		private final Assignment cL_Logical_expressionAssignment_1_2_1 = (Assignment)cGroup_1_2.eContents().get(1);
		private final RuleCall cL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0 = (RuleCall)cL_Logical_expressionAssignment_1_2_1.eContents().get(0);
		
		//simple_expression:
		//	Log_Exp=logical_expression (":" S_Logical_expression=logical_expression (":"
		//	L_Logical_expression=logical_expression)?)?;
		public ParserRule getRule() { return rule; }

		//Log_Exp=logical_expression (":" S_Logical_expression=logical_expression (":" L_Logical_expression=logical_expression)?)?
		public Group getGroup() { return cGroup; }

		//Log_Exp=logical_expression
		public Assignment getLog_ExpAssignment_0() { return cLog_ExpAssignment_0; }

		//logical_expression
		public RuleCall getLog_ExpLogical_expressionParserRuleCall_0_0() { return cLog_ExpLogical_expressionParserRuleCall_0_0; }

		//(":" S_Logical_expression=logical_expression (":" L_Logical_expression=logical_expression)?)?
		public Group getGroup_1() { return cGroup_1; }

		//":"
		public Keyword getColonKeyword_1_0() { return cColonKeyword_1_0; }

		//S_Logical_expression=logical_expression
		public Assignment getS_Logical_expressionAssignment_1_1() { return cS_Logical_expressionAssignment_1_1; }

		//logical_expression
		public RuleCall getS_Logical_expressionLogical_expressionParserRuleCall_1_1_0() { return cS_Logical_expressionLogical_expressionParserRuleCall_1_1_0; }

		//(":" L_Logical_expression=logical_expression)?
		public Group getGroup_1_2() { return cGroup_1_2; }

		//":"
		public Keyword getColonKeyword_1_2_0() { return cColonKeyword_1_2_0; }

		//L_Logical_expression=logical_expression
		public Assignment getL_Logical_expressionAssignment_1_2_1() { return cL_Logical_expressionAssignment_1_2_1; }

		//logical_expression
		public RuleCall getL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0() { return cL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0; }
	}

	public class Conditional_exprElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "conditional_expr");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cIfKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cIfexprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cIfexprExpressionParserRuleCall_1_0 = (RuleCall)cIfexprAssignment_1.eContents().get(0);
		private final Keyword cThenKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cThenexprAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cThenexprExpressionParserRuleCall_3_0 = (RuleCall)cThenexprAssignment_3.eContents().get(0);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cElseifKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cElseifexprAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cElseifexprExpressionParserRuleCall_4_1_0 = (RuleCall)cElseifexprAssignment_4_1.eContents().get(0);
		private final Keyword cThenKeyword_4_2 = (Keyword)cGroup_4.eContents().get(2);
		private final Assignment cTrueexprAssignment_4_3 = (Assignment)cGroup_4.eContents().get(3);
		private final RuleCall cTrueexprExpressionParserRuleCall_4_3_0 = (RuleCall)cTrueexprAssignment_4_3.eContents().get(0);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cElseKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Assignment cFalseexprAssignment_5_1 = (Assignment)cGroup_5.eContents().get(1);
		private final RuleCall cFalseexprExpressionParserRuleCall_5_1_0 = (RuleCall)cFalseexprAssignment_5_1.eContents().get(0);
		
		//conditional_expr:
		//	"if" ifexpr=expression "then" thenexpr=expression ("elseif" elseifexpr+=expression "then" trueexpr+=expression)*
		//	("else" falseexpr=expression);
		public ParserRule getRule() { return rule; }

		//"if" ifexpr=expression "then" thenexpr=expression ("elseif" elseifexpr+=expression "then" trueexpr+=expression)* ("else"
		//falseexpr=expression)
		public Group getGroup() { return cGroup; }

		//"if"
		public Keyword getIfKeyword_0() { return cIfKeyword_0; }

		//ifexpr=expression
		public Assignment getIfexprAssignment_1() { return cIfexprAssignment_1; }

		//expression
		public RuleCall getIfexprExpressionParserRuleCall_1_0() { return cIfexprExpressionParserRuleCall_1_0; }

		//"then"
		public Keyword getThenKeyword_2() { return cThenKeyword_2; }

		//thenexpr=expression
		public Assignment getThenexprAssignment_3() { return cThenexprAssignment_3; }

		//expression
		public RuleCall getThenexprExpressionParserRuleCall_3_0() { return cThenexprExpressionParserRuleCall_3_0; }

		//("elseif" elseifexpr+=expression "then" trueexpr+=expression)*
		public Group getGroup_4() { return cGroup_4; }

		//"elseif"
		public Keyword getElseifKeyword_4_0() { return cElseifKeyword_4_0; }

		//elseifexpr+=expression
		public Assignment getElseifexprAssignment_4_1() { return cElseifexprAssignment_4_1; }

		//expression
		public RuleCall getElseifexprExpressionParserRuleCall_4_1_0() { return cElseifexprExpressionParserRuleCall_4_1_0; }

		//"then"
		public Keyword getThenKeyword_4_2() { return cThenKeyword_4_2; }

		//trueexpr+=expression
		public Assignment getTrueexprAssignment_4_3() { return cTrueexprAssignment_4_3; }

		//expression
		public RuleCall getTrueexprExpressionParserRuleCall_4_3_0() { return cTrueexprExpressionParserRuleCall_4_3_0; }

		//"else" falseexpr=expression
		public Group getGroup_5() { return cGroup_5; }

		//"else"
		public Keyword getElseKeyword_5_0() { return cElseKeyword_5_0; }

		//falseexpr=expression
		public Assignment getFalseexprAssignment_5_1() { return cFalseexprAssignment_5_1; }

		//expression
		public RuleCall getFalseexprExpressionParserRuleCall_5_1_0() { return cFalseexprExpressionParserRuleCall_5_1_0; }
	}

	public class Logical_expressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "logical_expression");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cLogical_termParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cOrKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cLogical_termAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cLogical_termLogical_termParserRuleCall_1_1_0 = (RuleCall)cLogical_termAssignment_1_1.eContents().get(0);
		
		//logical_expression:
		//	logical_term ("or" Logical_term+=logical_term)*;
		public ParserRule getRule() { return rule; }

		//logical_term ("or" Logical_term+=logical_term)*
		public Group getGroup() { return cGroup; }

		//logical_term
		public RuleCall getLogical_termParserRuleCall_0() { return cLogical_termParserRuleCall_0; }

		//("or" Logical_term+=logical_term)*
		public Group getGroup_1() { return cGroup_1; }

		//"or"
		public Keyword getOrKeyword_1_0() { return cOrKeyword_1_0; }

		//Logical_term+=logical_term
		public Assignment getLogical_termAssignment_1_1() { return cLogical_termAssignment_1_1; }

		//logical_term
		public RuleCall getLogical_termLogical_termParserRuleCall_1_1_0() { return cLogical_termLogical_termParserRuleCall_1_1_0; }
	}

	public class Logical_termElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "logical_term");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cLogical_factorParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cAndKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cLogical_factorAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cLogical_factorLogical_factorParserRuleCall_1_1_0 = (RuleCall)cLogical_factorAssignment_1_1.eContents().get(0);
		
		//logical_term:
		//	logical_factor ("and" Logical_factor+=logical_factor)*;
		public ParserRule getRule() { return rule; }

		//logical_factor ("and" Logical_factor+=logical_factor)*
		public Group getGroup() { return cGroup; }

		//logical_factor
		public RuleCall getLogical_factorParserRuleCall_0() { return cLogical_factorParserRuleCall_0; }

		//("and" Logical_factor+=logical_factor)*
		public Group getGroup_1() { return cGroup_1; }

		//"and"
		public Keyword getAndKeyword_1_0() { return cAndKeyword_1_0; }

		//Logical_factor+=logical_factor
		public Assignment getLogical_factorAssignment_1_1() { return cLogical_factorAssignment_1_1; }

		//logical_factor
		public RuleCall getLogical_factorLogical_factorParserRuleCall_1_1_0() { return cLogical_factorLogical_factorParserRuleCall_1_1_0; }
	}

	public class Logical_factorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "logical_factor");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cNotKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cRelationAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cRelationRelationParserRuleCall_1_0 = (RuleCall)cRelationAssignment_1.eContents().get(0);
		
		//logical_factor:
		//	"not"? Relation=relation;
		public ParserRule getRule() { return rule; }

		//"not"? Relation=relation
		public Group getGroup() { return cGroup; }

		//"not"?
		public Keyword getNotKeyword_0() { return cNotKeyword_0; }

		//Relation=relation
		public Assignment getRelationAssignment_1() { return cRelationAssignment_1; }

		//relation
		public RuleCall getRelationRelationParserRuleCall_1_0() { return cRelationRelationParserRuleCall_1_0; }
	}

	public class RelationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "relation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cArithmetic_expressionParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cOpAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final Alternatives cOpAlternatives_1_0_0 = (Alternatives)cOpAssignment_1_0.eContents().get(0);
		private final RuleCall cOpRel_op_Less_thenParserRuleCall_1_0_0_0 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(0);
		private final RuleCall cOpRel_op_Less_equalParserRuleCall_1_0_0_1 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(1);
		private final RuleCall cOpRel_op_greater_thenParserRuleCall_1_0_0_2 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(2);
		private final RuleCall cOpRel_op_greater_equalParserRuleCall_1_0_0_3 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(3);
		private final RuleCall cOpRel_op_assignmentParserRuleCall_1_0_0_4 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(4);
		private final RuleCall cOpRel_op_OperParserRuleCall_1_0_0_5 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(5);
		private final Assignment cArithmetic_expressionAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0 = (RuleCall)cArithmetic_expressionAssignment_1_1.eContents().get(0);
		
		//relation:
		//	arithmetic_expression (op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal |
		//	rel_op_assignment | rel_op_Oper) Arithmetic_expression=arithmetic_expression)?;
		public ParserRule getRule() { return rule; }

		//arithmetic_expression (op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal |
		//rel_op_assignment | rel_op_Oper) Arithmetic_expression=arithmetic_expression)?
		public Group getGroup() { return cGroup; }

		//arithmetic_expression
		public RuleCall getArithmetic_expressionParserRuleCall_0() { return cArithmetic_expressionParserRuleCall_0; }

		//(op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal | rel_op_assignment |
		//rel_op_Oper) Arithmetic_expression=arithmetic_expression)?
		public Group getGroup_1() { return cGroup_1; }

		//op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal | rel_op_assignment | rel_op_Oper)
		public Assignment getOpAssignment_1_0() { return cOpAssignment_1_0; }

		//rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal | rel_op_assignment | rel_op_Oper
		public Alternatives getOpAlternatives_1_0_0() { return cOpAlternatives_1_0_0; }

		//rel_op_Less_then
		public RuleCall getOpRel_op_Less_thenParserRuleCall_1_0_0_0() { return cOpRel_op_Less_thenParserRuleCall_1_0_0_0; }

		//rel_op_Less_equal
		public RuleCall getOpRel_op_Less_equalParserRuleCall_1_0_0_1() { return cOpRel_op_Less_equalParserRuleCall_1_0_0_1; }

		//rel_op_greater_then
		public RuleCall getOpRel_op_greater_thenParserRuleCall_1_0_0_2() { return cOpRel_op_greater_thenParserRuleCall_1_0_0_2; }

		//rel_op_greater_equal
		public RuleCall getOpRel_op_greater_equalParserRuleCall_1_0_0_3() { return cOpRel_op_greater_equalParserRuleCall_1_0_0_3; }

		//rel_op_assignment
		public RuleCall getOpRel_op_assignmentParserRuleCall_1_0_0_4() { return cOpRel_op_assignmentParserRuleCall_1_0_0_4; }

		//rel_op_Oper
		public RuleCall getOpRel_op_OperParserRuleCall_1_0_0_5() { return cOpRel_op_OperParserRuleCall_1_0_0_5; }

		//Arithmetic_expression=arithmetic_expression
		public Assignment getArithmetic_expressionAssignment_1_1() { return cArithmetic_expressionAssignment_1_1; }

		//arithmetic_expression
		public RuleCall getArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0() { return cArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0; }
	}

	public class Arithmetic_expressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "arithmetic_expression");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cOprAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final Alternatives cOprAlternatives_0_0 = (Alternatives)cOprAssignment_0.eContents().get(0);
		private final RuleCall cOprAdd_op_plusParserRuleCall_0_0_0 = (RuleCall)cOprAlternatives_0_0.eContents().get(0);
		private final RuleCall cOprAdd_op_minusParserRuleCall_0_0_1 = (RuleCall)cOprAlternatives_0_0.eContents().get(1);
		private final RuleCall cOprAdd_op_dotplusParserRuleCall_0_0_2 = (RuleCall)cOprAlternatives_0_0.eContents().get(2);
		private final RuleCall cOprAdd_op_dotminusParserRuleCall_0_0_3 = (RuleCall)cOprAlternatives_0_0.eContents().get(3);
		private final Assignment cTermAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cTermTermParserRuleCall_1_0 = (RuleCall)cTermAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Assignment cOper1Assignment_2_0 = (Assignment)cGroup_2.eContents().get(0);
		private final Alternatives cOper1Alternatives_2_0_0 = (Alternatives)cOper1Assignment_2_0.eContents().get(0);
		private final RuleCall cOper1Add_op_plusParserRuleCall_2_0_0_0 = (RuleCall)cOper1Alternatives_2_0_0.eContents().get(0);
		private final RuleCall cOper1Add_op_minusParserRuleCall_2_0_0_1 = (RuleCall)cOper1Alternatives_2_0_0.eContents().get(1);
		private final RuleCall cOper1Add_op_dotplusParserRuleCall_2_0_0_2 = (RuleCall)cOper1Alternatives_2_0_0.eContents().get(2);
		private final RuleCall cOper1Add_op_dotminusParserRuleCall_2_0_0_3 = (RuleCall)cOper1Alternatives_2_0_0.eContents().get(3);
		private final Assignment cTerm1Assignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cTerm1TermParserRuleCall_2_1_0 = (RuleCall)cTerm1Assignment_2_1.eContents().get(0);
		
		//arithmetic_expression:
		//	opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)? Term=term (Oper1+=(add_op_plus | add_op_minus |
		//	add_op_dotplus | add_op_dotminus) Term1+=term)*;
		public ParserRule getRule() { return rule; }

		//opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)? Term=term (Oper1+=(add_op_plus | add_op_minus |
		//add_op_dotplus | add_op_dotminus) Term1+=term)*
		public Group getGroup() { return cGroup; }

		//opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)?
		public Assignment getOprAssignment_0() { return cOprAssignment_0; }

		//add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus
		public Alternatives getOprAlternatives_0_0() { return cOprAlternatives_0_0; }

		//add_op_plus
		public RuleCall getOprAdd_op_plusParserRuleCall_0_0_0() { return cOprAdd_op_plusParserRuleCall_0_0_0; }

		//add_op_minus
		public RuleCall getOprAdd_op_minusParserRuleCall_0_0_1() { return cOprAdd_op_minusParserRuleCall_0_0_1; }

		//add_op_dotplus
		public RuleCall getOprAdd_op_dotplusParserRuleCall_0_0_2() { return cOprAdd_op_dotplusParserRuleCall_0_0_2; }

		//add_op_dotminus
		public RuleCall getOprAdd_op_dotminusParserRuleCall_0_0_3() { return cOprAdd_op_dotminusParserRuleCall_0_0_3; }

		//Term=term
		public Assignment getTermAssignment_1() { return cTermAssignment_1; }

		//term
		public RuleCall getTermTermParserRuleCall_1_0() { return cTermTermParserRuleCall_1_0; }

		//(Oper1+=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus) Term1+=term)*
		public Group getGroup_2() { return cGroup_2; }

		//Oper1+=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)
		public Assignment getOper1Assignment_2_0() { return cOper1Assignment_2_0; }

		//add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus
		public Alternatives getOper1Alternatives_2_0_0() { return cOper1Alternatives_2_0_0; }

		//add_op_plus
		public RuleCall getOper1Add_op_plusParserRuleCall_2_0_0_0() { return cOper1Add_op_plusParserRuleCall_2_0_0_0; }

		//add_op_minus
		public RuleCall getOper1Add_op_minusParserRuleCall_2_0_0_1() { return cOper1Add_op_minusParserRuleCall_2_0_0_1; }

		//add_op_dotplus
		public RuleCall getOper1Add_op_dotplusParserRuleCall_2_0_0_2() { return cOper1Add_op_dotplusParserRuleCall_2_0_0_2; }

		//add_op_dotminus
		public RuleCall getOper1Add_op_dotminusParserRuleCall_2_0_0_3() { return cOper1Add_op_dotminusParserRuleCall_2_0_0_3; }

		//Term1+=term
		public Assignment getTerm1Assignment_2_1() { return cTerm1Assignment_2_1; }

		//term
		public RuleCall getTerm1TermParserRuleCall_2_1_0() { return cTerm1TermParserRuleCall_2_1_0; }
	}

	public class TermElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "term");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cFactorParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cOpAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final Alternatives cOpAlternatives_1_0_0 = (Alternatives)cOpAssignment_1_0.eContents().get(0);
		private final RuleCall cOpMul_op_mulParserRuleCall_1_0_0_0 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(0);
		private final RuleCall cOpMul_op_divParserRuleCall_1_0_0_1 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(1);
		private final RuleCall cOpMul_op_dotmulParserRuleCall_1_0_0_2 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(2);
		private final RuleCall cOpMul_op_dotdivParserRuleCall_1_0_0_3 = (RuleCall)cOpAlternatives_1_0_0.eContents().get(3);
		private final Assignment cFactorAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cFactorFactorParserRuleCall_1_1_0 = (RuleCall)cFactorAssignment_1_1.eContents().get(0);
		
		//term:
		//	factor (op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*;
		public ParserRule getRule() { return rule; }

		//factor (op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*
		public Group getGroup() { return cGroup; }

		//factor
		public RuleCall getFactorParserRuleCall_0() { return cFactorParserRuleCall_0; }

		//(op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*
		public Group getGroup_1() { return cGroup_1; }

		//op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv)
		public Assignment getOpAssignment_1_0() { return cOpAssignment_1_0; }

		//mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv
		public Alternatives getOpAlternatives_1_0_0() { return cOpAlternatives_1_0_0; }

		//mul_op_mul
		public RuleCall getOpMul_op_mulParserRuleCall_1_0_0_0() { return cOpMul_op_mulParserRuleCall_1_0_0_0; }

		//mul_op_div
		public RuleCall getOpMul_op_divParserRuleCall_1_0_0_1() { return cOpMul_op_divParserRuleCall_1_0_0_1; }

		//mul_op_dotmul
		public RuleCall getOpMul_op_dotmulParserRuleCall_1_0_0_2() { return cOpMul_op_dotmulParserRuleCall_1_0_0_2; }

		//mul_op_dotdiv
		public RuleCall getOpMul_op_dotdivParserRuleCall_1_0_0_3() { return cOpMul_op_dotdivParserRuleCall_1_0_0_3; }

		//Factor+=factor
		public Assignment getFactorAssignment_1_1() { return cFactorAssignment_1_1; }

		//factor
		public RuleCall getFactorFactorParserRuleCall_1_1_0() { return cFactorFactorParserRuleCall_1_1_0; }
	}

	public class FactorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "factor");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cPrimaryParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Alternatives cAlternatives_1_0 = (Alternatives)cGroup_1.eContents().get(0);
		private final Keyword cCircumflexAccentKeyword_1_0_0 = (Keyword)cAlternatives_1_0.eContents().get(0);
		private final Keyword cFullStopCircumflexAccentKeyword_1_0_1 = (Keyword)cAlternatives_1_0.eContents().get(1);
		private final Assignment cPrimaryAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cPrimaryPrimaryParserRuleCall_1_1_0 = (RuleCall)cPrimaryAssignment_1_1.eContents().get(0);
		
		//factor:
		//	primary (("^" | ".^") Primary=primary)?;
		public ParserRule getRule() { return rule; }

		//primary (("^" | ".^") Primary=primary)?
		public Group getGroup() { return cGroup; }

		//primary
		public RuleCall getPrimaryParserRuleCall_0() { return cPrimaryParserRuleCall_0; }

		//(("^" | ".^") Primary=primary)?
		public Group getGroup_1() { return cGroup_1; }

		//"^" | ".^"
		public Alternatives getAlternatives_1_0() { return cAlternatives_1_0; }

		//"^"
		public Keyword getCircumflexAccentKeyword_1_0_0() { return cCircumflexAccentKeyword_1_0_0; }

		//".^"
		public Keyword getFullStopCircumflexAccentKeyword_1_0_1() { return cFullStopCircumflexAccentKeyword_1_0_1; }

		//Primary=primary
		public Assignment getPrimaryAssignment_1_1() { return cPrimaryAssignment_1_1; }

		//primary
		public RuleCall getPrimaryPrimaryParserRuleCall_1_1_0() { return cPrimaryPrimaryParserRuleCall_1_1_0; }
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
		private final RuleCall cName_FunctionParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		private final RuleCall cInitial_refParserRuleCall_5 = (RuleCall)cAlternatives.eContents().get(5);
		private final RuleCall cExprDerParserRuleCall_6 = (RuleCall)cAlternatives.eContents().get(6);
		private final Assignment cComponent_referenceAssignment_7 = (Assignment)cAlternatives.eContents().get(7);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_7_0 = (RuleCall)cComponent_referenceAssignment_7.eContents().get(0);
		private final Group cGroup_8 = (Group)cAlternatives.eContents().get(8);
		private final Keyword cLeftParenthesisKeyword_8_0 = (Keyword)cGroup_8.eContents().get(0);
		private final Assignment cOutput_expr_listAssignment_8_1 = (Assignment)cGroup_8.eContents().get(1);
		private final RuleCall cOutput_expr_listOutput_expression_listParserRuleCall_8_1_0 = (RuleCall)cOutput_expr_listAssignment_8_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_8_2 = (Keyword)cGroup_8.eContents().get(2);
		private final Group cGroup_9 = (Group)cAlternatives.eContents().get(9);
		private final Keyword cLeftSquareBracketKeyword_9_0 = (Keyword)cGroup_9.eContents().get(0);
		private final Assignment cExpre_listAssignment_9_1 = (Assignment)cGroup_9.eContents().get(1);
		private final RuleCall cExpre_listExpression_listParserRuleCall_9_1_0 = (RuleCall)cExpre_listAssignment_9_1.eContents().get(0);
		private final Group cGroup_9_2 = (Group)cGroup_9.eContents().get(2);
		private final Keyword cSemicolonKeyword_9_2_0 = (Keyword)cGroup_9_2.eContents().get(0);
		private final Assignment cExpression_listAssignment_9_2_1 = (Assignment)cGroup_9_2.eContents().get(1);
		private final RuleCall cExpression_listExpression_listParserRuleCall_9_2_1_0 = (RuleCall)cExpression_listAssignment_9_2_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_9_3 = (Keyword)cGroup_9.eContents().get(3);
		private final Group cGroup_10 = (Group)cAlternatives.eContents().get(10);
		private final Keyword cLeftCurlyBracketKeyword_10_0 = (Keyword)cGroup_10.eContents().get(0);
		private final Assignment cF_argumentsAssignment_10_1 = (Assignment)cGroup_10.eContents().get(1);
		private final RuleCall cF_argumentsFunction_argumentsParserRuleCall_10_1_0 = (RuleCall)cF_argumentsAssignment_10_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_10_2 = (Keyword)cGroup_10.eContents().get(2);
		private final Assignment cEndAssignment_11 = (Assignment)cAlternatives.eContents().get(11);
		private final Keyword cEndEndKeyword_11_0 = (Keyword)cEndAssignment_11.eContents().get(0);
		
		////////////////////////////////////////////////////Primary///////////////////////////////
		//primary:
		//	num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | name_Function | initial_ref | ExprDer |
		//	Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
		//	Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
		//	End="end";
		public ParserRule getRule() { return rule; }

		//num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | name_Function | initial_ref | ExprDer |
		//Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
		//Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
		//End="end"
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

		//name_Function
		public RuleCall getName_FunctionParserRuleCall_4() { return cName_FunctionParserRuleCall_4; }

		//initial_ref
		public RuleCall getInitial_refParserRuleCall_5() { return cInitial_refParserRuleCall_5; }

		//ExprDer
		public RuleCall getExprDerParserRuleCall_6() { return cExprDerParserRuleCall_6; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_7() { return cComponent_referenceAssignment_7; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_7_0() { return cComponent_referenceComponent_referenceParserRuleCall_7_0; }

		//"(" output_expr_list=output_expression_list ")"
		public Group getGroup_8() { return cGroup_8; }

		//"("
		public Keyword getLeftParenthesisKeyword_8_0() { return cLeftParenthesisKeyword_8_0; }

		//output_expr_list=output_expression_list
		public Assignment getOutput_expr_listAssignment_8_1() { return cOutput_expr_listAssignment_8_1; }

		//output_expression_list
		public RuleCall getOutput_expr_listOutput_expression_listParserRuleCall_8_1_0() { return cOutput_expr_listOutput_expression_listParserRuleCall_8_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_8_2() { return cRightParenthesisKeyword_8_2; }

		//"[" Expre_list=expression_list (";" Expression_list+=expression_list)* "]"
		public Group getGroup_9() { return cGroup_9; }

		//"["
		public Keyword getLeftSquareBracketKeyword_9_0() { return cLeftSquareBracketKeyword_9_0; }

		//Expre_list=expression_list
		public Assignment getExpre_listAssignment_9_1() { return cExpre_listAssignment_9_1; }

		//expression_list
		public RuleCall getExpre_listExpression_listParserRuleCall_9_1_0() { return cExpre_listExpression_listParserRuleCall_9_1_0; }

		//(";" Expression_list+=expression_list)*
		public Group getGroup_9_2() { return cGroup_9_2; }

		//";"
		public Keyword getSemicolonKeyword_9_2_0() { return cSemicolonKeyword_9_2_0; }

		//Expression_list+=expression_list
		public Assignment getExpression_listAssignment_9_2_1() { return cExpression_listAssignment_9_2_1; }

		//expression_list
		public RuleCall getExpression_listExpression_listParserRuleCall_9_2_1_0() { return cExpression_listExpression_listParserRuleCall_9_2_1_0; }

		//"]"
		public Keyword getRightSquareBracketKeyword_9_3() { return cRightSquareBracketKeyword_9_3; }

		//"{" f_arguments=function_arguments "}"
		public Group getGroup_10() { return cGroup_10; }

		//"{"
		public Keyword getLeftCurlyBracketKeyword_10_0() { return cLeftCurlyBracketKeyword_10_0; }

		//f_arguments=function_arguments
		public Assignment getF_argumentsAssignment_10_1() { return cF_argumentsAssignment_10_1; }

		//function_arguments
		public RuleCall getF_argumentsFunction_argumentsParserRuleCall_10_1_0() { return cF_argumentsFunction_argumentsParserRuleCall_10_1_0; }

		//"}"
		public Keyword getRightCurlyBracketKeyword_10_2() { return cRightCurlyBracketKeyword_10_2; }

		//End="end"
		public Assignment getEndAssignment_11() { return cEndAssignment_11; }

		//"end"
		public Keyword getEndEndKeyword_11_0() { return cEndEndKeyword_11_0; }
	}

	public class Name_FunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "name_Function");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cNameParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Assignment cFunction_call_argsAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cFunction_call_argsFunction_call_argsParserRuleCall_1_0 = (RuleCall)cFunction_call_argsAssignment_1.eContents().get(0);
		
		//name_Function:
		//	name Function_call_args=function_call_args;
		public ParserRule getRule() { return rule; }

		//name Function_call_args=function_call_args
		public Group getGroup() { return cGroup; }

		//name
		public RuleCall getNameParserRuleCall_0() { return cNameParserRuleCall_0; }

		//Function_call_args=function_call_args
		public Assignment getFunction_call_argsAssignment_1() { return cFunction_call_argsAssignment_1; }

		//function_call_args
		public RuleCall getFunction_call_argsFunction_call_argsParserRuleCall_1_0() { return cFunction_call_argsFunction_call_argsParserRuleCall_1_0; }
	}

	public class Initial_refElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "initial_ref");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cInitialKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cFunction_call_argsParserRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		
		//initial_ref:
		//	"initial" function_call_args;
		public ParserRule getRule() { return rule; }

		//"initial" function_call_args
		public Group getGroup() { return cGroup; }

		//"initial"
		public Keyword getInitialKeyword_0() { return cInitialKeyword_0; }

		//function_call_args
		public RuleCall getFunction_call_argsParserRuleCall_1() { return cFunction_call_argsParserRuleCall_1; }
	}

	public class ExprDerElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ExprDer");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cDerKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cFunctionArgsAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cFunctionArgsFunction_call_argsParserRuleCall_1_0 = (RuleCall)cFunctionArgsAssignment_1.eContents().get(0);
		
		//ExprDer:
		//	"der" functionArgs=function_call_args;
		public ParserRule getRule() { return rule; }

		//"der" functionArgs=function_call_args
		public Group getGroup() { return cGroup; }

		//"der"
		public Keyword getDerKeyword_0() { return cDerKeyword_0; }

		//functionArgs=function_call_args
		public Assignment getFunctionArgsAssignment_1() { return cFunctionArgsAssignment_1; }

		//function_call_args
		public RuleCall getFunctionArgsFunction_call_argsParserRuleCall_1_0() { return cFunctionArgsFunction_call_argsParserRuleCall_1_0; }
	}

	public class Function_call_argsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "function_call_args");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cFunction_call_argsAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cF_argAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cF_argFunction_argumentsParserRuleCall_2_0 = (RuleCall)cF_argAssignment_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//function_call_args:
		//	{function_call_args} "(" f_arg=function_arguments? ")";
		public ParserRule getRule() { return rule; }

		//{function_call_args} "(" f_arg=function_arguments? ")"
		public Group getGroup() { return cGroup; }

		//{function_call_args}
		public Action getFunction_call_argsAction_0() { return cFunction_call_argsAction_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//f_arg=function_arguments?
		public Assignment getF_argAssignment_2() { return cF_argAssignment_2; }

		//function_arguments
		public RuleCall getF_argFunction_argumentsParserRuleCall_2_0() { return cF_argFunction_argumentsParserRuleCall_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class Expression_listElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "expression_list");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cExprAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cExprExpressionParserRuleCall_0_0 = (RuleCall)cExprAssignment_0.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cCommaKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cExpreAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cExpreExpressionParserRuleCall_1_1_0 = (RuleCall)cExpreAssignment_1_1.eContents().get(0);
		
		//expression_list:
		//	expr=expression ("," Expre+=expression)*;
		public ParserRule getRule() { return rule; }

		//expr=expression ("," Expre+=expression)*
		public Group getGroup() { return cGroup; }

		//expr=expression
		public Assignment getExprAssignment_0() { return cExprAssignment_0; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_0_0() { return cExprExpressionParserRuleCall_0_0; }

		//("," Expre+=expression)*
		public Group getGroup_1() { return cGroup_1; }

		//","
		public Keyword getCommaKeyword_1_0() { return cCommaKeyword_1_0; }

		//Expre+=expression
		public Assignment getExpreAssignment_1_1() { return cExpreAssignment_1_1; }

		//expression
		public RuleCall getExpreExpressionParserRuleCall_1_1_0() { return cExpreExpressionParserRuleCall_1_1_0; }
	}

	public class NameElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "name");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cFullStopKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cName_IDAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cName_IDIDENTTerminalRuleCall_1_0 = (RuleCall)cName_IDAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cFullStopKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cNam_IDAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cNam_IDIDENTTerminalRuleCall_2_1_0 = (RuleCall)cNam_IDAssignment_2_1.eContents().get(0);
		
		/////////////////////////////////Component Reference////////////////////////
		//name:
		//	"."? name_ID=IDENT ("." nam_ID+=IDENT)*;
		public ParserRule getRule() { return rule; }

		//"."? name_ID=IDENT ("." nam_ID+=IDENT)*
		public Group getGroup() { return cGroup; }

		//"."?
		public Keyword getFullStopKeyword_0() { return cFullStopKeyword_0; }

		//name_ID=IDENT
		public Assignment getName_IDAssignment_1() { return cName_IDAssignment_1; }

		//IDENT
		public RuleCall getName_IDIDENTTerminalRuleCall_1_0() { return cName_IDIDENTTerminalRuleCall_1_0; }

		//("." nam_ID+=IDENT)*
		public Group getGroup_2() { return cGroup_2; }

		//"."
		public Keyword getFullStopKeyword_2_0() { return cFullStopKeyword_2_0; }

		//nam_ID+=IDENT
		public Assignment getNam_IDAssignment_2_1() { return cNam_IDAssignment_2_1; }

		//IDENT
		public RuleCall getNam_IDIDENTTerminalRuleCall_2_1_0() { return cNam_IDIDENTTerminalRuleCall_2_1_0; }
	}

	public class Component_referenceElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "component_reference");
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
		
		//component_reference:
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

	public class Output_expression_listElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "output_expression_list");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOutput_expression_listAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cEprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cEprExpressionParserRuleCall_1_0 = (RuleCall)cEprAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cCommaKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cExprAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_2_1_0 = (RuleCall)cExprAssignment_2_1.eContents().get(0);
		
		//output_expression_list:
		//	{output_expression_list} epr=expression? ("," Expr+=expression?)*;
		public ParserRule getRule() { return rule; }

		//{output_expression_list} epr=expression? ("," Expr+=expression?)*
		public Group getGroup() { return cGroup; }

		//{output_expression_list}
		public Action getOutput_expression_listAction_0() { return cOutput_expression_listAction_0; }

		//epr=expression?
		public Assignment getEprAssignment_1() { return cEprAssignment_1; }

		//expression
		public RuleCall getEprExpressionParserRuleCall_1_0() { return cEprExpressionParserRuleCall_1_0; }

		//("," Expr+=expression?)*
		public Group getGroup_2() { return cGroup_2; }

		//","
		public Keyword getCommaKeyword_2_0() { return cCommaKeyword_2_0; }

		//Expr+=expression?
		public Assignment getExprAssignment_2_1() { return cExprAssignment_2_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_2_1_0() { return cExprExpressionParserRuleCall_2_1_0; }
	}

	public class Array_subscriptsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "array_subscripts");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftSquareBracketKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cSubAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cSubSubscriptParserRuleCall_1_0 = (RuleCall)cSubAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cCommaKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cSubscriptAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cSubscriptSubscriptParserRuleCall_2_1_0 = (RuleCall)cSubscriptAssignment_2_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//array_subscripts:
		//	"[" Sub=subscript ("," Subscript+=subscript)* "]";
		public ParserRule getRule() { return rule; }

		//"[" Sub=subscript ("," Subscript+=subscript)* "]"
		public Group getGroup() { return cGroup; }

		//"["
		public Keyword getLeftSquareBracketKeyword_0() { return cLeftSquareBracketKeyword_0; }

		//Sub=subscript
		public Assignment getSubAssignment_1() { return cSubAssignment_1; }

		//subscript
		public RuleCall getSubSubscriptParserRuleCall_1_0() { return cSubSubscriptParserRuleCall_1_0; }

		//("," Subscript+=subscript)*
		public Group getGroup_2() { return cGroup_2; }

		//","
		public Keyword getCommaKeyword_2_0() { return cCommaKeyword_2_0; }

		//Subscript+=subscript
		public Assignment getSubscriptAssignment_2_1() { return cSubscriptAssignment_2_1; }

		//subscript
		public RuleCall getSubscriptSubscriptParserRuleCall_2_1_0() { return cSubscriptSubscriptParserRuleCall_2_1_0; }

		//"]"
		public Keyword getRightSquareBracketKeyword_3() { return cRightSquareBracketKeyword_3; }
	}

	public class SubscriptElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "subscript");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Action cSubscriptAction_0_0 = (Action)cGroup_0.eContents().get(0);
		private final Keyword cColonKeyword_0_1 = (Keyword)cGroup_0.eContents().get(1);
		private final Assignment cExprAssignment_1 = (Assignment)cAlternatives.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_1_0 = (RuleCall)cExprAssignment_1.eContents().get(0);
		
		//subscript:
		//	{subscript} ":" | expr=expression;
		public ParserRule getRule() { return rule; }

		//{subscript} ":" | expr=expression
		public Alternatives getAlternatives() { return cAlternatives; }

		//{subscript} ":"
		public Group getGroup_0() { return cGroup_0; }

		//{subscript}
		public Action getSubscriptAction_0_0() { return cSubscriptAction_0_0; }

		//":"
		public Keyword getColonKeyword_0_1() { return cColonKeyword_0_1; }

		//expr=expression
		public Assignment getExprAssignment_1() { return cExprAssignment_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_1_0() { return cExprExpressionParserRuleCall_1_0; }
	}

	public class Function_argumentsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "function_arguments");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Action cFunction_argumentsAction_0_0 = (Action)cGroup_0.eContents().get(0);
		private final Assignment cArgExpAssignment_0_1 = (Assignment)cGroup_0.eContents().get(1);
		private final RuleCall cArgExpExpressionParserRuleCall_0_1_0 = (RuleCall)cArgExpAssignment_0_1.eContents().get(0);
		private final Alternatives cAlternatives_0_2 = (Alternatives)cGroup_0.eContents().get(2);
		private final Assignment cFun_Arg_ExprAssignment_0_2_0 = (Assignment)cAlternatives_0_2.eContents().get(0);
		private final RuleCall cFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0 = (RuleCall)cFun_Arg_ExprAssignment_0_2_0.eContents().get(0);
		private final Assignment cFun_Arg_ForAssignment_0_2_1 = (Assignment)cAlternatives_0_2.eContents().get(1);
		private final RuleCall cFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0 = (RuleCall)cFun_Arg_ForAssignment_0_2_1.eContents().get(0);
		private final Assignment cName_argAssignment_1 = (Assignment)cAlternatives.eContents().get(1);
		private final RuleCall cName_argNamed_argumentsParserRuleCall_1_0 = (RuleCall)cName_argAssignment_1.eContents().get(0);
		
		//function_arguments:
		//	{function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)? |
		//	name_arg=named_arguments;
		public ParserRule getRule() { return rule; }

		//{function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)? |
		//name_arg=named_arguments
		public Alternatives getAlternatives() { return cAlternatives; }

		//{function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)?
		public Group getGroup_0() { return cGroup_0; }

		//{function_arguments}
		public Action getFunction_argumentsAction_0_0() { return cFunction_argumentsAction_0_0; }

		//ArgExp+=expression
		public Assignment getArgExpAssignment_0_1() { return cArgExpAssignment_0_1; }

		//expression
		public RuleCall getArgExpExpressionParserRuleCall_0_1_0() { return cArgExpExpressionParserRuleCall_0_1_0; }

		//(Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)?
		public Alternatives getAlternatives_0_2() { return cAlternatives_0_2; }

		//Fun_Arg_Expr=Fun_Arguments_exp
		public Assignment getFun_Arg_ExprAssignment_0_2_0() { return cFun_Arg_ExprAssignment_0_2_0; }

		//Fun_Arguments_exp
		public RuleCall getFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0() { return cFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0; }

		//Fun_Arg_For=Fun_Arguments_for
		public Assignment getFun_Arg_ForAssignment_0_2_1() { return cFun_Arg_ForAssignment_0_2_1; }

		//Fun_Arguments_for
		public RuleCall getFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0() { return cFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0; }

		//name_arg=named_arguments
		public Assignment getName_argAssignment_1() { return cName_argAssignment_1; }

		//named_arguments
		public RuleCall getName_argNamed_argumentsParserRuleCall_1_0() { return cName_argNamed_argumentsParserRuleCall_1_0; }
	}

	public class Fun_Arguments_expElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Fun_Arguments_exp");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommaKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cArgsAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cArgsFunction_argumentsParserRuleCall_1_0 = (RuleCall)cArgsAssignment_1.eContents().get(0);
		
		//Fun_Arguments_exp:
		//	"," Args=function_arguments;
		public ParserRule getRule() { return rule; }

		//"," Args=function_arguments
		public Group getGroup() { return cGroup; }

		//","
		public Keyword getCommaKeyword_0() { return cCommaKeyword_0; }

		//Args=function_arguments
		public Assignment getArgsAssignment_1() { return cArgsAssignment_1; }

		//function_arguments
		public RuleCall getArgsFunction_argumentsParserRuleCall_1_0() { return cArgsFunction_argumentsParserRuleCall_1_0; }
	}

	public class Fun_Arguments_forElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Fun_Arguments_for");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cForKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cFor_indicesAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cFor_indicesFor_indicesParserRuleCall_1_0 = (RuleCall)cFor_indicesAssignment_1.eContents().get(0);
		
		//Fun_Arguments_for:
		//	"for" For_indices=for_indices;
		public ParserRule getRule() { return rule; }

		//"for" For_indices=for_indices
		public Group getGroup() { return cGroup; }

		//"for"
		public Keyword getForKeyword_0() { return cForKeyword_0; }

		//For_indices=for_indices
		public Assignment getFor_indicesAssignment_1() { return cFor_indicesAssignment_1; }

		//for_indices
		public RuleCall getFor_indicesFor_indicesParserRuleCall_1_0() { return cFor_indicesFor_indicesParserRuleCall_1_0; }
	}

	public class Named_argumentsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "named_arguments");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cNamed_argumentParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cCommaKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cNamed_argumentsAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cNamed_argumentsNamed_argumentsParserRuleCall_1_1_0 = (RuleCall)cNamed_argumentsAssignment_1_1.eContents().get(0);
		
		//named_arguments:
		//	named_argument ("," Named_arguments=named_arguments)?;
		public ParserRule getRule() { return rule; }

		//named_argument ("," Named_arguments=named_arguments)?
		public Group getGroup() { return cGroup; }

		//named_argument
		public RuleCall getNamed_argumentParserRuleCall_0() { return cNamed_argumentParserRuleCall_0; }

		//("," Named_arguments=named_arguments)?
		public Group getGroup_1() { return cGroup_1; }

		//","
		public Keyword getCommaKeyword_1_0() { return cCommaKeyword_1_0; }

		//Named_arguments=named_arguments
		public Assignment getNamed_argumentsAssignment_1_1() { return cNamed_argumentsAssignment_1_1; }

		//named_arguments
		public RuleCall getNamed_argumentsNamed_argumentsParserRuleCall_1_1_0() { return cNamed_argumentsNamed_argumentsParserRuleCall_1_1_0; }
	}

	public class Named_argumentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "named_argument");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cArgAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cArgIDENTTerminalRuleCall_0_0 = (RuleCall)cArgAssignment_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cExprAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cExprExpressionParserRuleCall_2_0 = (RuleCall)cExprAssignment_2.eContents().get(0);
		
		//named_argument:
		//	arg=IDENT "=" expr=expression;
		public ParserRule getRule() { return rule; }

		//arg=IDENT "=" expr=expression
		public Group getGroup() { return cGroup; }

		//arg=IDENT
		public Assignment getArgAssignment_0() { return cArgAssignment_0; }

		//IDENT
		public RuleCall getArgIDENTTerminalRuleCall_0_0() { return cArgIDENTTerminalRuleCall_0_0; }

		//"="
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }

		//expr=expression
		public Assignment getExprAssignment_2() { return cExprAssignment_2; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_2_0() { return cExprExpressionParserRuleCall_2_0; }
	}

	public class For_indicesElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "for_indices");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cFor_indexParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cCommaKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cFor_indexAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cFor_indexFor_indexParserRuleCall_1_1_0 = (RuleCall)cFor_indexAssignment_1_1.eContents().get(0);
		
		//for_indices:
		//	for_index ("," For_index+=for_index)*;
		public ParserRule getRule() { return rule; }

		//for_index ("," For_index+=for_index)*
		public Group getGroup() { return cGroup; }

		//for_index
		public RuleCall getFor_indexParserRuleCall_0() { return cFor_indexParserRuleCall_0; }

		//("," For_index+=for_index)*
		public Group getGroup_1() { return cGroup_1; }

		//","
		public Keyword getCommaKeyword_1_0() { return cCommaKeyword_1_0; }

		//For_index+=for_index
		public Assignment getFor_indexAssignment_1_1() { return cFor_indexAssignment_1_1; }

		//for_index
		public RuleCall getFor_indexFor_indexParserRuleCall_1_1_0() { return cFor_indexFor_indexParserRuleCall_1_1_0; }
	}

	public class For_indexElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "for_index");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cIndexAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cIndexIDENTTerminalRuleCall_0_0 = (RuleCall)cIndexAssignment_0.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cInKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cExprAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_1_1_0 = (RuleCall)cExprAssignment_1_1.eContents().get(0);
		
		//for_index:
		//	index=IDENT ("in" expr=expression)?;
		public ParserRule getRule() { return rule; }

		//index=IDENT ("in" expr=expression)?
		public Group getGroup() { return cGroup; }

		//index=IDENT
		public Assignment getIndexAssignment_0() { return cIndexAssignment_0; }

		//IDENT
		public RuleCall getIndexIDENTTerminalRuleCall_0_0() { return cIndexIDENTTerminalRuleCall_0_0; }

		//("in" expr=expression)?
		public Group getGroup_1() { return cGroup_1; }

		//"in"
		public Keyword getInKeyword_1_0() { return cInKeyword_1_0; }

		//expr=expression
		public Assignment getExprAssignment_1_1() { return cExprAssignment_1_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_1_1_0() { return cExprExpressionParserRuleCall_1_1_0; }
	}

	public class Mul_op_mulElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "mul_op_mul");
		private final Keyword cAsteriskKeyword = (Keyword)rule.eContents().get(1);
		
		/////////////////////////////////Operators/////////////////////////////////////////
		//mul_op_mul:
		//	"*";
		public ParserRule getRule() { return rule; }

		//"*"
		public Keyword getAsteriskKeyword() { return cAsteriskKeyword; }
	}

	public class Mul_op_divElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "mul_op_div");
		private final Keyword cSolidusKeyword = (Keyword)rule.eContents().get(1);
		
		//mul_op_div:
		//	"/";
		public ParserRule getRule() { return rule; }

		//"/"
		public Keyword getSolidusKeyword() { return cSolidusKeyword; }
	}

	public class Mul_op_dotmulElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "mul_op_dotmul");
		private final Keyword cFullStopAsteriskKeyword = (Keyword)rule.eContents().get(1);
		
		//mul_op_dotmul:
		//	".*";
		public ParserRule getRule() { return rule; }

		//".*"
		public Keyword getFullStopAsteriskKeyword() { return cFullStopAsteriskKeyword; }
	}

	public class Mul_op_dotdivElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "mul_op_dotdiv");
		private final Keyword cFullStopSolidusKeyword = (Keyword)rule.eContents().get(1);
		
		//mul_op_dotdiv:
		//	"./";
		public ParserRule getRule() { return rule; }

		//"./"
		public Keyword getFullStopSolidusKeyword() { return cFullStopSolidusKeyword; }
	}

	public class Add_op_plusElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "add_op_plus");
		private final Keyword cPlusSignKeyword = (Keyword)rule.eContents().get(1);
		
		//add_op_plus:
		//	"+";
		public ParserRule getRule() { return rule; }

		//"+"
		public Keyword getPlusSignKeyword() { return cPlusSignKeyword; }
	}

	public class Add_op_minusElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "add_op_minus");
		private final Keyword cHyphenMinusKeyword = (Keyword)rule.eContents().get(1);
		
		//add_op_minus:
		//	"-";
		public ParserRule getRule() { return rule; }

		//"-"
		public Keyword getHyphenMinusKeyword() { return cHyphenMinusKeyword; }
	}

	public class Add_op_dotplusElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "add_op_dotplus");
		private final Keyword cFullStopPlusSignKeyword = (Keyword)rule.eContents().get(1);
		
		//add_op_dotplus:
		//	".+";
		public ParserRule getRule() { return rule; }

		//".+"
		public Keyword getFullStopPlusSignKeyword() { return cFullStopPlusSignKeyword; }
	}

	public class Add_op_dotminusElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "add_op_dotminus");
		private final Keyword cFullStopHyphenMinusKeyword = (Keyword)rule.eContents().get(1);
		
		//add_op_dotminus:
		//	".-";
		public ParserRule getRule() { return rule; }

		//".-"
		public Keyword getFullStopHyphenMinusKeyword() { return cFullStopHyphenMinusKeyword; }
	}

	public class Rel_op_Less_thenElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "rel_op_Less_then");
		private final Keyword cLessThanSignKeyword = (Keyword)rule.eContents().get(1);
		
		//rel_op_Less_then:
		//	"<";
		public ParserRule getRule() { return rule; }

		//"<"
		public Keyword getLessThanSignKeyword() { return cLessThanSignKeyword; }
	}

	public class Rel_op_Less_equalElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "rel_op_Less_equal");
		private final Keyword cLessThanSignEqualsSignKeyword = (Keyword)rule.eContents().get(1);
		
		//rel_op_Less_equal:
		//	"<=";
		public ParserRule getRule() { return rule; }

		//"<="
		public Keyword getLessThanSignEqualsSignKeyword() { return cLessThanSignEqualsSignKeyword; }
	}

	public class Rel_op_greater_thenElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "rel_op_greater_then");
		private final Keyword cGreaterThanSignKeyword = (Keyword)rule.eContents().get(1);
		
		//rel_op_greater_then:
		//	">";
		public ParserRule getRule() { return rule; }

		//">"
		public Keyword getGreaterThanSignKeyword() { return cGreaterThanSignKeyword; }
	}

	public class Rel_op_greater_equalElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "rel_op_greater_equal");
		private final Keyword cGreaterThanSignEqualsSignKeyword = (Keyword)rule.eContents().get(1);
		
		//rel_op_greater_equal:
		//	">=";
		public ParserRule getRule() { return rule; }

		//">="
		public Keyword getGreaterThanSignEqualsSignKeyword() { return cGreaterThanSignEqualsSignKeyword; }
	}

	public class Rel_op_assignmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "rel_op_assignment");
		private final Keyword cEqualsSignEqualsSignKeyword = (Keyword)rule.eContents().get(1);
		
		//rel_op_assignment:
		//	"==";
		public ParserRule getRule() { return rule; }

		//"=="
		public Keyword getEqualsSignEqualsSignKeyword() { return cEqualsSignEqualsSignKeyword; }
	}

	public class Rel_op_OperElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "rel_op_Oper");
		private final Keyword cLessThanSignGreaterThanSignKeyword = (Keyword)rule.eContents().get(1);
		
		//rel_op_Oper:
		//	"<>";
		public ParserRule getRule() { return rule; }

		//"<>"
		public Keyword getLessThanSignGreaterThanSignKeyword() { return cLessThanSignGreaterThanSignKeyword; }
	}

	public class CommentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "comment");
		private final RuleCall cString_commentParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		/////////////////////////////////Comments/////////////////////////////////////
		//comment:
		//	string_comment;
		public ParserRule getRule() { return rule; }

		//string_comment
		public RuleCall getString_commentParserRuleCall() { return cString_commentParserRuleCall; }
	}

	public class String_commentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "string_comment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cSTRINGTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cPlusSignKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final RuleCall cSTRINGTerminalRuleCall_1_1 = (RuleCall)cGroup_1.eContents().get(1);
		
		//string_comment:
		//	(STRING ("+" STRING)*)?;
		public ParserRule getRule() { return rule; }

		//(STRING ("+" STRING)*)?
		public Group getGroup() { return cGroup; }

		//STRING
		public RuleCall getSTRINGTerminalRuleCall_0() { return cSTRINGTerminalRuleCall_0; }

		//("+" STRING)*
		public Group getGroup_1() { return cGroup_1; }

		//"+"
		public Keyword getPlusSignKeyword_1_0() { return cPlusSignKeyword_1_0; }

		//STRING
		public RuleCall getSTRINGTerminalRuleCall_1_1() { return cSTRINGTerminalRuleCall_1_1; }
	}
	
	
	private ExpressionElements pExpression;
	private Simple_expressionElements pSimple_expression;
	private Conditional_exprElements pConditional_expr;
	private Logical_expressionElements pLogical_expression;
	private Logical_termElements pLogical_term;
	private Logical_factorElements pLogical_factor;
	private RelationElements pRelation;
	private Arithmetic_expressionElements pArithmetic_expression;
	private TermElements pTerm;
	private FactorElements pFactor;
	private PrimaryElements pPrimary;
	private Name_FunctionElements pName_Function;
	private Initial_refElements pInitial_ref;
	private ExprDerElements pExprDer;
	private Function_call_argsElements pFunction_call_args;
	private Expression_listElements pExpression_list;
	private NameElements pName;
	private Component_referenceElements pComponent_reference;
	private Output_expression_listElements pOutput_expression_list;
	private Array_subscriptsElements pArray_subscripts;
	private SubscriptElements pSubscript;
	private Function_argumentsElements pFunction_arguments;
	private Fun_Arguments_expElements pFun_Arguments_exp;
	private Fun_Arguments_forElements pFun_Arguments_for;
	private Named_argumentsElements pNamed_arguments;
	private Named_argumentElements pNamed_argument;
	private For_indicesElements pFor_indices;
	private For_indexElements pFor_index;
	private Mul_op_mulElements pMul_op_mul;
	private Mul_op_divElements pMul_op_div;
	private Mul_op_dotmulElements pMul_op_dotmul;
	private Mul_op_dotdivElements pMul_op_dotdiv;
	private Add_op_plusElements pAdd_op_plus;
	private Add_op_minusElements pAdd_op_minus;
	private Add_op_dotplusElements pAdd_op_dotplus;
	private Add_op_dotminusElements pAdd_op_dotminus;
	private Rel_op_Less_thenElements pRel_op_Less_then;
	private Rel_op_Less_equalElements pRel_op_Less_equal;
	private Rel_op_greater_thenElements pRel_op_greater_then;
	private Rel_op_greater_equalElements pRel_op_greater_equal;
	private Rel_op_assignmentElements pRel_op_assignment;
	private Rel_op_OperElements pRel_op_Oper;
	private CommentElements pComment;
	private String_commentElements pString_comment;
	private TerminalRule tUNSIGNED_NUMBER;
	private TerminalRule tBOOL_VAL;
	private TerminalRule tINT;
	private TerminalRule tSTRING;
	private TerminalRule tIDENT;
	
	private final GrammarProvider grammarProvider;

	private TerminalsGrammarAccess gaTerminals;

	@Inject
	public ModeleditorGrammarAccess(GrammarProvider grammarProvider,
		TerminalsGrammarAccess gaTerminals) {
		this.grammarProvider = grammarProvider;
		this.gaTerminals = gaTerminals;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//////////////////////////////////////////Expressions////////////////////////
	//expression:
	//	Expr=simple_expression | conditional_expr;
	public ExpressionElements getExpressionAccess() {
		return (pExpression != null) ? pExpression : (pExpression = new ExpressionElements());
	}
	
	public ParserRule getExpressionRule() {
		return getExpressionAccess().getRule();
	}

	//simple_expression:
	//	Log_Exp=logical_expression (":" S_Logical_expression=logical_expression (":"
	//	L_Logical_expression=logical_expression)?)?;
	public Simple_expressionElements getSimple_expressionAccess() {
		return (pSimple_expression != null) ? pSimple_expression : (pSimple_expression = new Simple_expressionElements());
	}
	
	public ParserRule getSimple_expressionRule() {
		return getSimple_expressionAccess().getRule();
	}

	//conditional_expr:
	//	"if" ifexpr=expression "then" thenexpr=expression ("elseif" elseifexpr+=expression "then" trueexpr+=expression)*
	//	("else" falseexpr=expression);
	public Conditional_exprElements getConditional_exprAccess() {
		return (pConditional_expr != null) ? pConditional_expr : (pConditional_expr = new Conditional_exprElements());
	}
	
	public ParserRule getConditional_exprRule() {
		return getConditional_exprAccess().getRule();
	}

	//logical_expression:
	//	logical_term ("or" Logical_term+=logical_term)*;
	public Logical_expressionElements getLogical_expressionAccess() {
		return (pLogical_expression != null) ? pLogical_expression : (pLogical_expression = new Logical_expressionElements());
	}
	
	public ParserRule getLogical_expressionRule() {
		return getLogical_expressionAccess().getRule();
	}

	//logical_term:
	//	logical_factor ("and" Logical_factor+=logical_factor)*;
	public Logical_termElements getLogical_termAccess() {
		return (pLogical_term != null) ? pLogical_term : (pLogical_term = new Logical_termElements());
	}
	
	public ParserRule getLogical_termRule() {
		return getLogical_termAccess().getRule();
	}

	//logical_factor:
	//	"not"? Relation=relation;
	public Logical_factorElements getLogical_factorAccess() {
		return (pLogical_factor != null) ? pLogical_factor : (pLogical_factor = new Logical_factorElements());
	}
	
	public ParserRule getLogical_factorRule() {
		return getLogical_factorAccess().getRule();
	}

	//relation:
	//	arithmetic_expression (op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal |
	//	rel_op_assignment | rel_op_Oper) Arithmetic_expression=arithmetic_expression)?;
	public RelationElements getRelationAccess() {
		return (pRelation != null) ? pRelation : (pRelation = new RelationElements());
	}
	
	public ParserRule getRelationRule() {
		return getRelationAccess().getRule();
	}

	//arithmetic_expression:
	//	opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)? Term=term (Oper1+=(add_op_plus | add_op_minus |
	//	add_op_dotplus | add_op_dotminus) Term1+=term)*;
	public Arithmetic_expressionElements getArithmetic_expressionAccess() {
		return (pArithmetic_expression != null) ? pArithmetic_expression : (pArithmetic_expression = new Arithmetic_expressionElements());
	}
	
	public ParserRule getArithmetic_expressionRule() {
		return getArithmetic_expressionAccess().getRule();
	}

	//term:
	//	factor (op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*;
	public TermElements getTermAccess() {
		return (pTerm != null) ? pTerm : (pTerm = new TermElements());
	}
	
	public ParserRule getTermRule() {
		return getTermAccess().getRule();
	}

	//factor:
	//	primary (("^" | ".^") Primary=primary)?;
	public FactorElements getFactorAccess() {
		return (pFactor != null) ? pFactor : (pFactor = new FactorElements());
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
	public PrimaryElements getPrimaryAccess() {
		return (pPrimary != null) ? pPrimary : (pPrimary = new PrimaryElements());
	}
	
	public ParserRule getPrimaryRule() {
		return getPrimaryAccess().getRule();
	}

	//name_Function:
	//	name Function_call_args=function_call_args;
	public Name_FunctionElements getName_FunctionAccess() {
		return (pName_Function != null) ? pName_Function : (pName_Function = new Name_FunctionElements());
	}
	
	public ParserRule getName_FunctionRule() {
		return getName_FunctionAccess().getRule();
	}

	//initial_ref:
	//	"initial" function_call_args;
	public Initial_refElements getInitial_refAccess() {
		return (pInitial_ref != null) ? pInitial_ref : (pInitial_ref = new Initial_refElements());
	}
	
	public ParserRule getInitial_refRule() {
		return getInitial_refAccess().getRule();
	}

	//ExprDer:
	//	"der" functionArgs=function_call_args;
	public ExprDerElements getExprDerAccess() {
		return (pExprDer != null) ? pExprDer : (pExprDer = new ExprDerElements());
	}
	
	public ParserRule getExprDerRule() {
		return getExprDerAccess().getRule();
	}

	//function_call_args:
	//	{function_call_args} "(" f_arg=function_arguments? ")";
	public Function_call_argsElements getFunction_call_argsAccess() {
		return (pFunction_call_args != null) ? pFunction_call_args : (pFunction_call_args = new Function_call_argsElements());
	}
	
	public ParserRule getFunction_call_argsRule() {
		return getFunction_call_argsAccess().getRule();
	}

	//expression_list:
	//	expr=expression ("," Expre+=expression)*;
	public Expression_listElements getExpression_listAccess() {
		return (pExpression_list != null) ? pExpression_list : (pExpression_list = new Expression_listElements());
	}
	
	public ParserRule getExpression_listRule() {
		return getExpression_listAccess().getRule();
	}

	/////////////////////////////////Component Reference////////////////////////
	//name:
	//	"."? name_ID=IDENT ("." nam_ID+=IDENT)*;
	public NameElements getNameAccess() {
		return (pName != null) ? pName : (pName = new NameElements());
	}
	
	public ParserRule getNameRule() {
		return getNameAccess().getRule();
	}

	//component_reference:
	//	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
	public Component_referenceElements getComponent_referenceAccess() {
		return (pComponent_reference != null) ? pComponent_reference : (pComponent_reference = new Component_referenceElements());
	}
	
	public ParserRule getComponent_referenceRule() {
		return getComponent_referenceAccess().getRule();
	}

	//output_expression_list:
	//	{output_expression_list} epr=expression? ("," Expr+=expression?)*;
	public Output_expression_listElements getOutput_expression_listAccess() {
		return (pOutput_expression_list != null) ? pOutput_expression_list : (pOutput_expression_list = new Output_expression_listElements());
	}
	
	public ParserRule getOutput_expression_listRule() {
		return getOutput_expression_listAccess().getRule();
	}

	//array_subscripts:
	//	"[" Sub=subscript ("," Subscript+=subscript)* "]";
	public Array_subscriptsElements getArray_subscriptsAccess() {
		return (pArray_subscripts != null) ? pArray_subscripts : (pArray_subscripts = new Array_subscriptsElements());
	}
	
	public ParserRule getArray_subscriptsRule() {
		return getArray_subscriptsAccess().getRule();
	}

	//subscript:
	//	{subscript} ":" | expr=expression;
	public SubscriptElements getSubscriptAccess() {
		return (pSubscript != null) ? pSubscript : (pSubscript = new SubscriptElements());
	}
	
	public ParserRule getSubscriptRule() {
		return getSubscriptAccess().getRule();
	}

	//function_arguments:
	//	{function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)? |
	//	name_arg=named_arguments;
	public Function_argumentsElements getFunction_argumentsAccess() {
		return (pFunction_arguments != null) ? pFunction_arguments : (pFunction_arguments = new Function_argumentsElements());
	}
	
	public ParserRule getFunction_argumentsRule() {
		return getFunction_argumentsAccess().getRule();
	}

	//Fun_Arguments_exp:
	//	"," Args=function_arguments;
	public Fun_Arguments_expElements getFun_Arguments_expAccess() {
		return (pFun_Arguments_exp != null) ? pFun_Arguments_exp : (pFun_Arguments_exp = new Fun_Arguments_expElements());
	}
	
	public ParserRule getFun_Arguments_expRule() {
		return getFun_Arguments_expAccess().getRule();
	}

	//Fun_Arguments_for:
	//	"for" For_indices=for_indices;
	public Fun_Arguments_forElements getFun_Arguments_forAccess() {
		return (pFun_Arguments_for != null) ? pFun_Arguments_for : (pFun_Arguments_for = new Fun_Arguments_forElements());
	}
	
	public ParserRule getFun_Arguments_forRule() {
		return getFun_Arguments_forAccess().getRule();
	}

	//named_arguments:
	//	named_argument ("," Named_arguments=named_arguments)?;
	public Named_argumentsElements getNamed_argumentsAccess() {
		return (pNamed_arguments != null) ? pNamed_arguments : (pNamed_arguments = new Named_argumentsElements());
	}
	
	public ParserRule getNamed_argumentsRule() {
		return getNamed_argumentsAccess().getRule();
	}

	//named_argument:
	//	arg=IDENT "=" expr=expression;
	public Named_argumentElements getNamed_argumentAccess() {
		return (pNamed_argument != null) ? pNamed_argument : (pNamed_argument = new Named_argumentElements());
	}
	
	public ParserRule getNamed_argumentRule() {
		return getNamed_argumentAccess().getRule();
	}

	//for_indices:
	//	for_index ("," For_index+=for_index)*;
	public For_indicesElements getFor_indicesAccess() {
		return (pFor_indices != null) ? pFor_indices : (pFor_indices = new For_indicesElements());
	}
	
	public ParserRule getFor_indicesRule() {
		return getFor_indicesAccess().getRule();
	}

	//for_index:
	//	index=IDENT ("in" expr=expression)?;
	public For_indexElements getFor_indexAccess() {
		return (pFor_index != null) ? pFor_index : (pFor_index = new For_indexElements());
	}
	
	public ParserRule getFor_indexRule() {
		return getFor_indexAccess().getRule();
	}

	/////////////////////////////////Operators/////////////////////////////////////////
	//mul_op_mul:
	//	"*";
	public Mul_op_mulElements getMul_op_mulAccess() {
		return (pMul_op_mul != null) ? pMul_op_mul : (pMul_op_mul = new Mul_op_mulElements());
	}
	
	public ParserRule getMul_op_mulRule() {
		return getMul_op_mulAccess().getRule();
	}

	//mul_op_div:
	//	"/";
	public Mul_op_divElements getMul_op_divAccess() {
		return (pMul_op_div != null) ? pMul_op_div : (pMul_op_div = new Mul_op_divElements());
	}
	
	public ParserRule getMul_op_divRule() {
		return getMul_op_divAccess().getRule();
	}

	//mul_op_dotmul:
	//	".*";
	public Mul_op_dotmulElements getMul_op_dotmulAccess() {
		return (pMul_op_dotmul != null) ? pMul_op_dotmul : (pMul_op_dotmul = new Mul_op_dotmulElements());
	}
	
	public ParserRule getMul_op_dotmulRule() {
		return getMul_op_dotmulAccess().getRule();
	}

	//mul_op_dotdiv:
	//	"./";
	public Mul_op_dotdivElements getMul_op_dotdivAccess() {
		return (pMul_op_dotdiv != null) ? pMul_op_dotdiv : (pMul_op_dotdiv = new Mul_op_dotdivElements());
	}
	
	public ParserRule getMul_op_dotdivRule() {
		return getMul_op_dotdivAccess().getRule();
	}

	//add_op_plus:
	//	"+";
	public Add_op_plusElements getAdd_op_plusAccess() {
		return (pAdd_op_plus != null) ? pAdd_op_plus : (pAdd_op_plus = new Add_op_plusElements());
	}
	
	public ParserRule getAdd_op_plusRule() {
		return getAdd_op_plusAccess().getRule();
	}

	//add_op_minus:
	//	"-";
	public Add_op_minusElements getAdd_op_minusAccess() {
		return (pAdd_op_minus != null) ? pAdd_op_minus : (pAdd_op_minus = new Add_op_minusElements());
	}
	
	public ParserRule getAdd_op_minusRule() {
		return getAdd_op_minusAccess().getRule();
	}

	//add_op_dotplus:
	//	".+";
	public Add_op_dotplusElements getAdd_op_dotplusAccess() {
		return (pAdd_op_dotplus != null) ? pAdd_op_dotplus : (pAdd_op_dotplus = new Add_op_dotplusElements());
	}
	
	public ParserRule getAdd_op_dotplusRule() {
		return getAdd_op_dotplusAccess().getRule();
	}

	//add_op_dotminus:
	//	".-";
	public Add_op_dotminusElements getAdd_op_dotminusAccess() {
		return (pAdd_op_dotminus != null) ? pAdd_op_dotminus : (pAdd_op_dotminus = new Add_op_dotminusElements());
	}
	
	public ParserRule getAdd_op_dotminusRule() {
		return getAdd_op_dotminusAccess().getRule();
	}

	//rel_op_Less_then:
	//	"<";
	public Rel_op_Less_thenElements getRel_op_Less_thenAccess() {
		return (pRel_op_Less_then != null) ? pRel_op_Less_then : (pRel_op_Less_then = new Rel_op_Less_thenElements());
	}
	
	public ParserRule getRel_op_Less_thenRule() {
		return getRel_op_Less_thenAccess().getRule();
	}

	//rel_op_Less_equal:
	//	"<=";
	public Rel_op_Less_equalElements getRel_op_Less_equalAccess() {
		return (pRel_op_Less_equal != null) ? pRel_op_Less_equal : (pRel_op_Less_equal = new Rel_op_Less_equalElements());
	}
	
	public ParserRule getRel_op_Less_equalRule() {
		return getRel_op_Less_equalAccess().getRule();
	}

	//rel_op_greater_then:
	//	">";
	public Rel_op_greater_thenElements getRel_op_greater_thenAccess() {
		return (pRel_op_greater_then != null) ? pRel_op_greater_then : (pRel_op_greater_then = new Rel_op_greater_thenElements());
	}
	
	public ParserRule getRel_op_greater_thenRule() {
		return getRel_op_greater_thenAccess().getRule();
	}

	//rel_op_greater_equal:
	//	">=";
	public Rel_op_greater_equalElements getRel_op_greater_equalAccess() {
		return (pRel_op_greater_equal != null) ? pRel_op_greater_equal : (pRel_op_greater_equal = new Rel_op_greater_equalElements());
	}
	
	public ParserRule getRel_op_greater_equalRule() {
		return getRel_op_greater_equalAccess().getRule();
	}

	//rel_op_assignment:
	//	"==";
	public Rel_op_assignmentElements getRel_op_assignmentAccess() {
		return (pRel_op_assignment != null) ? pRel_op_assignment : (pRel_op_assignment = new Rel_op_assignmentElements());
	}
	
	public ParserRule getRel_op_assignmentRule() {
		return getRel_op_assignmentAccess().getRule();
	}

	//rel_op_Oper:
	//	"<>";
	public Rel_op_OperElements getRel_op_OperAccess() {
		return (pRel_op_Oper != null) ? pRel_op_Oper : (pRel_op_Oper = new Rel_op_OperElements());
	}
	
	public ParserRule getRel_op_OperRule() {
		return getRel_op_OperAccess().getRule();
	}

	/////////////////////////////////Comments/////////////////////////////////////
	//comment:
	//	string_comment;
	public CommentElements getCommentAccess() {
		return (pComment != null) ? pComment : (pComment = new CommentElements());
	}
	
	public ParserRule getCommentRule() {
		return getCommentAccess().getRule();
	}

	//string_comment:
	//	(STRING ("+" STRING)*)?;
	public String_commentElements getString_commentAccess() {
		return (pString_comment != null) ? pString_comment : (pString_comment = new String_commentElements());
	}
	
	public ParserRule getString_commentRule() {
		return getString_commentAccess().getRule();
	}

	/////////////////////////////////////////// Terminals////////////////////////
	//terminal UNSIGNED_NUMBER:
	//	"0".."9"+ "." "0".."9"* (("E" | "e") ("+" | "-")? "0".."9"+)? | "0".."9"+ ("E" | "e") ("+" | "-")? "0".."9"+;
	public TerminalRule getUNSIGNED_NUMBERRule() {
		return (tUNSIGNED_NUMBER != null) ? tUNSIGNED_NUMBER : (tUNSIGNED_NUMBER = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "UNSIGNED_NUMBER"));
	} 

	//terminal BOOL_VAL:
	//	"true" | "false";
	public TerminalRule getBOOL_VALRule() {
		return (tBOOL_VAL != null) ? tBOOL_VAL : (tBOOL_VAL = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "BOOL_VAL"));
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return (tINT != null) ? tINT : (tINT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "INT"));
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"";
	public TerminalRule getSTRINGRule() {
		return (tSTRING != null) ? tSTRING : (tSTRING = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "STRING"));
	} 

	//terminal IDENT:
	//	("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9" | ".")*;
	public TerminalRule getIDENTRule() {
		return (tIDENT != null) ? tIDENT : (tIDENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "IDENT"));
	} 

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	} 
}
