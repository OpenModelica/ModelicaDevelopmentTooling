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
package org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.openmodelica.modelicaml.editor.xtext.valuebinding.services.MediatorGrammarAccess;

public class MediatorParser extends AbstractContentAssistParser {
	
	@Inject
	private MediatorGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist.antlr.internal.InternalMediatorParser createParser() {
		org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist.antlr.internal.InternalMediatorParser result = new org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist.antlr.internal.InternalMediatorParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getPrimaryAccess().getAlternatives(), "rule__Primary__Alternatives");
					put(grammarAccess.getComponent_referenceAccess().getAlternatives(), "rule__Component_reference__Alternatives");
					put(grammarAccess.getExpressionAccess().getAlternatives(), "rule__Expression__Alternatives");
					put(grammarAccess.getRelationAccess().getOpAlternatives_1_0_0(), "rule__Relation__OpAlternatives_1_0_0");
					put(grammarAccess.getArithmetic_expressionAccess().getOprAlternatives_0_0(), "rule__Arithmetic_expression__OprAlternatives_0_0");
					put(grammarAccess.getArithmetic_expressionAccess().getOper1Alternatives_2_0_0(), "rule__Arithmetic_expression__Oper1Alternatives_2_0_0");
					put(grammarAccess.getTermAccess().getOpAlternatives_1_0_0(), "rule__Term__OpAlternatives_1_0_0");
					put(grammarAccess.getFactorAccess().getAlternatives_1_0(), "rule__Factor__Alternatives_1_0");
					put(grammarAccess.getSubscriptAccess().getAlternatives(), "rule__Subscript__Alternatives");
					put(grammarAccess.getFunction_argumentsAccess().getAlternatives(), "rule__Function_arguments__Alternatives");
					put(grammarAccess.getFunction_argumentsAccess().getAlternatives_0_2(), "rule__Function_arguments__Alternatives_0_2");
					put(grammarAccess.getPrimaryAccess().getGroup_19(), "rule__Primary__Group_19__0");
					put(grammarAccess.getPrimaryAccess().getGroup_20(), "rule__Primary__Group_20__0");
					put(grammarAccess.getPrimaryAccess().getGroup_20_2(), "rule__Primary__Group_20_2__0");
					put(grammarAccess.getPrimaryAccess().getGroup_21(), "rule__Primary__Group_21__0");
					put(grammarAccess.getProductFunctionAccess().getGroup(), "rule__ProductFunction__Group__0");
					put(grammarAccess.getSumFunctionAccess().getGroup(), "rule__SumFunction__Group__0");
					put(grammarAccess.getMinFunctionAccess().getGroup(), "rule__MinFunction__Group__0");
					put(grammarAccess.getMaxFunctionAccess().getGroup(), "rule__MaxFunction__Group__0");
					put(grammarAccess.getAverageFunctionAccess().getGroup(), "rule__AverageFunction__Group__0");
					put(grammarAccess.getSizeFunctionAccess().getGroup(), "rule__SizeFunction__Group__0");
					put(grammarAccess.getToArrayFunctionAccess().getGroup(), "rule__ToArrayFunction__Group__0");
					put(grammarAccess.getANDFunctionAccess().getGroup(), "rule__ANDFunction__Group__0");
					put(grammarAccess.getORFunctionAccess().getGroup(), "rule__ORFunction__Group__0");
					put(grammarAccess.getXORFunctionAccess().getGroup(), "rule__XORFunction__Group__0");
					put(grammarAccess.getGetSingleProviderFunctionAccess().getGroup(), "rule__GetSingleProviderFunction__Group__0");
					put(grammarAccess.getReductionFunctionCallArgsAccess().getGroup(), "rule__ReductionFunctionCallArgs__Group__0");
					put(grammarAccess.getBracketsAccess().getGroup(), "rule__Brackets__Group__0");
					put(grammarAccess.getComponent_referenceAccess().getGroup_0(), "rule__Component_reference__Group_0__0");
					put(grammarAccess.getComponent_referenceAccess().getGroup_0_2(), "rule__Component_reference__Group_0_2__0");
					put(grammarAccess.getComponent_referenceAccess().getGroup_1(), "rule__Component_reference__Group_1__0");
					put(grammarAccess.getComponent_referenceAccess().getGroup_1_3(), "rule__Component_reference__Group_1_3__0");
					put(grammarAccess.getSimple_expressionAccess().getGroup(), "rule__Simple_expression__Group__0");
					put(grammarAccess.getSimple_expressionAccess().getGroup_1(), "rule__Simple_expression__Group_1__0");
					put(grammarAccess.getSimple_expressionAccess().getGroup_1_2(), "rule__Simple_expression__Group_1_2__0");
					put(grammarAccess.getConditional_exprAccess().getGroup(), "rule__Conditional_expr__Group__0");
					put(grammarAccess.getConditional_exprAccess().getGroup_4(), "rule__Conditional_expr__Group_4__0");
					put(grammarAccess.getConditional_exprAccess().getGroup_5(), "rule__Conditional_expr__Group_5__0");
					put(grammarAccess.getLogical_expressionAccess().getGroup(), "rule__Logical_expression__Group__0");
					put(grammarAccess.getLogical_expressionAccess().getGroup_1(), "rule__Logical_expression__Group_1__0");
					put(grammarAccess.getLogical_termAccess().getGroup(), "rule__Logical_term__Group__0");
					put(grammarAccess.getLogical_termAccess().getGroup_1(), "rule__Logical_term__Group_1__0");
					put(grammarAccess.getLogical_factorAccess().getGroup(), "rule__Logical_factor__Group__0");
					put(grammarAccess.getRelationAccess().getGroup(), "rule__Relation__Group__0");
					put(grammarAccess.getRelationAccess().getGroup_1(), "rule__Relation__Group_1__0");
					put(grammarAccess.getArithmetic_expressionAccess().getGroup(), "rule__Arithmetic_expression__Group__0");
					put(grammarAccess.getArithmetic_expressionAccess().getGroup_2(), "rule__Arithmetic_expression__Group_2__0");
					put(grammarAccess.getTermAccess().getGroup(), "rule__Term__Group__0");
					put(grammarAccess.getTermAccess().getGroup_1(), "rule__Term__Group_1__0");
					put(grammarAccess.getFactorAccess().getGroup(), "rule__Factor__Group__0");
					put(grammarAccess.getFactorAccess().getGroup_1(), "rule__Factor__Group_1__0");
					put(grammarAccess.getName_FunctionAccess().getGroup(), "rule__Name_Function__Group__0");
					put(grammarAccess.getInitial_refAccess().getGroup(), "rule__Initial_ref__Group__0");
					put(grammarAccess.getExprDerAccess().getGroup(), "rule__ExprDer__Group__0");
					put(grammarAccess.getFunction_call_argsAccess().getGroup(), "rule__Function_call_args__Group__0");
					put(grammarAccess.getExpression_listAccess().getGroup(), "rule__Expression_list__Group__0");
					put(grammarAccess.getExpression_listAccess().getGroup_1(), "rule__Expression_list__Group_1__0");
					put(grammarAccess.getNameAccess().getGroup(), "rule__Name__Group__0");
					put(grammarAccess.getNameAccess().getGroup_2(), "rule__Name__Group_2__0");
					put(grammarAccess.getOutput_expression_listAccess().getGroup(), "rule__Output_expression_list__Group__0");
					put(grammarAccess.getOutput_expression_listAccess().getGroup_2(), "rule__Output_expression_list__Group_2__0");
					put(grammarAccess.getArray_subscriptsAccess().getGroup(), "rule__Array_subscripts__Group__0");
					put(grammarAccess.getArray_subscriptsAccess().getGroup_2(), "rule__Array_subscripts__Group_2__0");
					put(grammarAccess.getSubscriptAccess().getGroup_0(), "rule__Subscript__Group_0__0");
					put(grammarAccess.getFunction_argumentsAccess().getGroup_0(), "rule__Function_arguments__Group_0__0");
					put(grammarAccess.getFun_Arguments_expAccess().getGroup(), "rule__Fun_Arguments_exp__Group__0");
					put(grammarAccess.getFun_Arguments_forAccess().getGroup(), "rule__Fun_Arguments_for__Group__0");
					put(grammarAccess.getNamed_argumentsAccess().getGroup(), "rule__Named_arguments__Group__0");
					put(grammarAccess.getNamed_argumentsAccess().getGroup_1(), "rule__Named_arguments__Group_1__0");
					put(grammarAccess.getNamed_argumentAccess().getGroup(), "rule__Named_argument__Group__0");
					put(grammarAccess.getFor_indicesAccess().getGroup(), "rule__For_indices__Group__0");
					put(grammarAccess.getFor_indicesAccess().getGroup_1(), "rule__For_indices__Group_1__0");
					put(grammarAccess.getFor_indexAccess().getGroup(), "rule__For_index__Group__0");
					put(grammarAccess.getFor_indexAccess().getGroup_1(), "rule__For_index__Group_1__0");
					put(grammarAccess.getString_commentAccess().getGroup(), "rule__String_comment__Group__0");
					put(grammarAccess.getString_commentAccess().getGroup_1(), "rule__String_comment__Group_1__0");
					put(grammarAccess.getMediatorOperationAccess().getExprAssignment(), "rule__MediatorOperation__ExprAssignment");
					put(grammarAccess.getPrimaryAccess().getNumAssignment_0(), "rule__Primary__NumAssignment_0");
					put(grammarAccess.getPrimaryAccess().getIntAssignment_1(), "rule__Primary__IntAssignment_1");
					put(grammarAccess.getPrimaryAccess().getStrAssignment_2(), "rule__Primary__StrAssignment_2");
					put(grammarAccess.getPrimaryAccess().getBoolAssignment_3(), "rule__Primary__BoolAssignment_3");
					put(grammarAccess.getPrimaryAccess().getName_FunctionAssignment_4(), "rule__Primary__Name_FunctionAssignment_4");
					put(grammarAccess.getPrimaryAccess().getInitial_refAssignment_5(), "rule__Primary__Initial_refAssignment_5");
					put(grammarAccess.getPrimaryAccess().getExprAssignment_6(), "rule__Primary__ExprAssignment_6");
					put(grammarAccess.getPrimaryAccess().getSumAssignment_7(), "rule__Primary__SumAssignment_7");
					put(grammarAccess.getPrimaryAccess().getProdAssignment_8(), "rule__Primary__ProdAssignment_8");
					put(grammarAccess.getPrimaryAccess().getMinAssignment_9(), "rule__Primary__MinAssignment_9");
					put(grammarAccess.getPrimaryAccess().getMaxAssignment_10(), "rule__Primary__MaxAssignment_10");
					put(grammarAccess.getPrimaryAccess().getAvgAssignment_11(), "rule__Primary__AvgAssignment_11");
					put(grammarAccess.getPrimaryAccess().getSizeAssignment_12(), "rule__Primary__SizeAssignment_12");
					put(grammarAccess.getPrimaryAccess().getToArrayAssignment_13(), "rule__Primary__ToArrayAssignment_13");
					put(grammarAccess.getPrimaryAccess().getAndAssignment_14(), "rule__Primary__AndAssignment_14");
					put(grammarAccess.getPrimaryAccess().getOrAssignment_15(), "rule__Primary__OrAssignment_15");
					put(grammarAccess.getPrimaryAccess().getXorAssignment_16(), "rule__Primary__XorAssignment_16");
					put(grammarAccess.getPrimaryAccess().getSingleProviderAssignment_17(), "rule__Primary__SingleProviderAssignment_17");
					put(grammarAccess.getPrimaryAccess().getComponent_referenceAssignment_18(), "rule__Primary__Component_referenceAssignment_18");
					put(grammarAccess.getPrimaryAccess().getOutput_expr_listAssignment_19_1(), "rule__Primary__Output_expr_listAssignment_19_1");
					put(grammarAccess.getPrimaryAccess().getExpre_listAssignment_20_1(), "rule__Primary__Expre_listAssignment_20_1");
					put(grammarAccess.getPrimaryAccess().getExpression_listAssignment_20_2_1(), "rule__Primary__Expression_listAssignment_20_2_1");
					put(grammarAccess.getPrimaryAccess().getF_argumentsAssignment_21_1(), "rule__Primary__F_argumentsAssignment_21_1");
					put(grammarAccess.getPrimaryAccess().getEndAssignment_22(), "rule__Primary__EndAssignment_22");
					put(grammarAccess.getComponent_referenceAccess().getRefAssignment_0_0(), "rule__Component_reference__RefAssignment_0_0");
					put(grammarAccess.getComponent_referenceAccess().getSubscripts1Assignment_0_1(), "rule__Component_reference__Subscripts1Assignment_0_1");
					put(grammarAccess.getComponent_referenceAccess().getRef1Assignment_0_2_1(), "rule__Component_reference__Ref1Assignment_0_2_1");
					put(grammarAccess.getComponent_referenceAccess().getSubscriptsAssignment_0_2_2(), "rule__Component_reference__SubscriptsAssignment_0_2_2");
					put(grammarAccess.getComponent_referenceAccess().getRefAssignment_1_1(), "rule__Component_reference__RefAssignment_1_1");
					put(grammarAccess.getComponent_referenceAccess().getSubscripts1Assignment_1_2(), "rule__Component_reference__Subscripts1Assignment_1_2");
					put(grammarAccess.getComponent_referenceAccess().getRef1Assignment_1_3_1(), "rule__Component_reference__Ref1Assignment_1_3_1");
					put(grammarAccess.getComponent_referenceAccess().getSubscriptsAssignment_1_3_2(), "rule__Component_reference__SubscriptsAssignment_1_3_2");
					put(grammarAccess.getExpressionAccess().getExprAssignment_0(), "rule__Expression__ExprAssignment_0");
					put(grammarAccess.getSimple_expressionAccess().getLog_ExpAssignment_0(), "rule__Simple_expression__Log_ExpAssignment_0");
					put(grammarAccess.getSimple_expressionAccess().getS_Logical_expressionAssignment_1_1(), "rule__Simple_expression__S_Logical_expressionAssignment_1_1");
					put(grammarAccess.getSimple_expressionAccess().getL_Logical_expressionAssignment_1_2_1(), "rule__Simple_expression__L_Logical_expressionAssignment_1_2_1");
					put(grammarAccess.getConditional_exprAccess().getIfexprAssignment_1(), "rule__Conditional_expr__IfexprAssignment_1");
					put(grammarAccess.getConditional_exprAccess().getThenexprAssignment_3(), "rule__Conditional_expr__ThenexprAssignment_3");
					put(grammarAccess.getConditional_exprAccess().getElseifexprAssignment_4_1(), "rule__Conditional_expr__ElseifexprAssignment_4_1");
					put(grammarAccess.getConditional_exprAccess().getTrueexprAssignment_4_3(), "rule__Conditional_expr__TrueexprAssignment_4_3");
					put(grammarAccess.getConditional_exprAccess().getFalseexprAssignment_5_1(), "rule__Conditional_expr__FalseexprAssignment_5_1");
					put(grammarAccess.getLogical_expressionAccess().getLogical_termAssignment_1_1(), "rule__Logical_expression__Logical_termAssignment_1_1");
					put(grammarAccess.getLogical_termAccess().getLogical_factorAssignment_1_1(), "rule__Logical_term__Logical_factorAssignment_1_1");
					put(grammarAccess.getLogical_factorAccess().getRelationAssignment_1(), "rule__Logical_factor__RelationAssignment_1");
					put(grammarAccess.getRelationAccess().getOpAssignment_1_0(), "rule__Relation__OpAssignment_1_0");
					put(grammarAccess.getRelationAccess().getArithmetic_expressionAssignment_1_1(), "rule__Relation__Arithmetic_expressionAssignment_1_1");
					put(grammarAccess.getArithmetic_expressionAccess().getOprAssignment_0(), "rule__Arithmetic_expression__OprAssignment_0");
					put(grammarAccess.getArithmetic_expressionAccess().getTermAssignment_1(), "rule__Arithmetic_expression__TermAssignment_1");
					put(grammarAccess.getArithmetic_expressionAccess().getOper1Assignment_2_0(), "rule__Arithmetic_expression__Oper1Assignment_2_0");
					put(grammarAccess.getArithmetic_expressionAccess().getTerm1Assignment_2_1(), "rule__Arithmetic_expression__Term1Assignment_2_1");
					put(grammarAccess.getTermAccess().getOpAssignment_1_0(), "rule__Term__OpAssignment_1_0");
					put(grammarAccess.getTermAccess().getFactorAssignment_1_1(), "rule__Term__FactorAssignment_1_1");
					put(grammarAccess.getFactorAccess().getPrimaryAssignment_1_1(), "rule__Factor__PrimaryAssignment_1_1");
					put(grammarAccess.getName_FunctionAccess().getFunction_call_argsAssignment_1(), "rule__Name_Function__Function_call_argsAssignment_1");
					put(grammarAccess.getExprDerAccess().getFunctionArgsAssignment_1(), "rule__ExprDer__FunctionArgsAssignment_1");
					put(grammarAccess.getFunction_call_argsAccess().getF_argAssignment_2(), "rule__Function_call_args__F_argAssignment_2");
					put(grammarAccess.getExpression_listAccess().getExprAssignment_0(), "rule__Expression_list__ExprAssignment_0");
					put(grammarAccess.getExpression_listAccess().getExpreAssignment_1_1(), "rule__Expression_list__ExpreAssignment_1_1");
					put(grammarAccess.getNameAccess().getName_IDAssignment_1(), "rule__Name__Name_IDAssignment_1");
					put(grammarAccess.getNameAccess().getNam_IDAssignment_2_1(), "rule__Name__Nam_IDAssignment_2_1");
					put(grammarAccess.getOutput_expression_listAccess().getEprAssignment_1(), "rule__Output_expression_list__EprAssignment_1");
					put(grammarAccess.getOutput_expression_listAccess().getExprAssignment_2_1(), "rule__Output_expression_list__ExprAssignment_2_1");
					put(grammarAccess.getArray_subscriptsAccess().getSubAssignment_1(), "rule__Array_subscripts__SubAssignment_1");
					put(grammarAccess.getArray_subscriptsAccess().getSubscriptAssignment_2_1(), "rule__Array_subscripts__SubscriptAssignment_2_1");
					put(grammarAccess.getSubscriptAccess().getExprAssignment_1(), "rule__Subscript__ExprAssignment_1");
					put(grammarAccess.getFunction_argumentsAccess().getArgExpAssignment_0_1(), "rule__Function_arguments__ArgExpAssignment_0_1");
					put(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprAssignment_0_2_0(), "rule__Function_arguments__Fun_Arg_ExprAssignment_0_2_0");
					put(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForAssignment_0_2_1(), "rule__Function_arguments__Fun_Arg_ForAssignment_0_2_1");
					put(grammarAccess.getFunction_argumentsAccess().getName_argAssignment_1(), "rule__Function_arguments__Name_argAssignment_1");
					put(grammarAccess.getFun_Arguments_expAccess().getArgsAssignment_1(), "rule__Fun_Arguments_exp__ArgsAssignment_1");
					put(grammarAccess.getFun_Arguments_forAccess().getFor_indicesAssignment_1(), "rule__Fun_Arguments_for__For_indicesAssignment_1");
					put(grammarAccess.getNamed_argumentsAccess().getNamed_argumentsAssignment_1_1(), "rule__Named_arguments__Named_argumentsAssignment_1_1");
					put(grammarAccess.getNamed_argumentAccess().getArgAssignment_0(), "rule__Named_argument__ArgAssignment_0");
					put(grammarAccess.getNamed_argumentAccess().getExprAssignment_2(), "rule__Named_argument__ExprAssignment_2");
					put(grammarAccess.getFor_indicesAccess().getFor_indexAssignment_1_1(), "rule__For_indices__For_indexAssignment_1_1");
					put(grammarAccess.getFor_indexAccess().getIndexAssignment_0(), "rule__For_index__IndexAssignment_0");
					put(grammarAccess.getFor_indexAccess().getExprAssignment_1_1(), "rule__For_index__ExprAssignment_1_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist.antlr.internal.InternalMediatorParser typedParser = (org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist.antlr.internal.InternalMediatorParser) parser;
			typedParser.entryRuleMediatorOperation();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public MediatorGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(MediatorGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
