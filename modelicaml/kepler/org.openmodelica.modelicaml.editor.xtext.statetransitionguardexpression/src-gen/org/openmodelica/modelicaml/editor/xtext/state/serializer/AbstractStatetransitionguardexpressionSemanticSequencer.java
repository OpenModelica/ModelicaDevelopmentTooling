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
package org.openmodelica.modelicaml.editor.xtext.state.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_exp;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.subscript;
import org.openmodelica.modelicaml.editor.xtext.model.serializer.ModeleditorSemanticSequencer;
import org.openmodelica.modelicaml.editor.xtext.state.services.StatetransitionguardexpressionGrammarAccess;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.guardExpression;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary;

import com.google.inject.Inject;
import com.google.inject.Provider;

@SuppressWarnings("restriction")
public class AbstractStatetransitionguardexpressionSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected StatetransitionguardexpressionGrammarAccess grammarAccess;
	
	@Inject
	protected ISemanticSequencerDiagnosticProvider diagnosticProvider;
	
	@Inject
	protected ITransientValueService transientValues;
	
	@Inject
	@GenericSequencer
	protected Provider<ISemanticSequencer> genericSequencerProvider;
	
	protected ISemanticSequencer genericSequencer;
	
	@Inject
	protected Provider<ModeleditorSemanticSequencer> superSequencerProvider;
	 
	protected ModeleditorSemanticSequencer superSequencer; 
	
	@Override
	public void init(ISemanticSequencer sequencer, ISemanticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {
		super.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.genericSequencer = genericSequencerProvider.get();
		this.genericSequencer.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.superSequencer = superSequencerProvider.get();
		this.superSequencer.init(sequencer, sequenceAcceptor, errorAcceptor); 
	}
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == ModeleditorPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ModeleditorPackage.EXPR_DER:
				if(context == grammarAccess.getExprDerRule()) {
					sequence_ExprDer(context, (ExprDer) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FUN_ARGUMENTS_EXP:
				if(context == grammarAccess.getFun_Arguments_expRule()) {
					sequence_Fun_Arguments_exp(context, (Fun_Arguments_exp) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FUN_ARGUMENTS_FOR:
				if(context == grammarAccess.getFun_Arguments_forRule()) {
					sequence_Fun_Arguments_for(context, (Fun_Arguments_for) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.ARITHMETIC_EXPRESSION:
				if(context == grammarAccess.getArithmetic_expressionRule()) {
					sequence_arithmetic_expression(context, (arithmetic_expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getRelationRule()) {
					sequence_relation(context, (arithmetic_expression) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.ARRAY_SUBSCRIPTS:
				if(context == grammarAccess.getArray_subscriptsRule()) {
					sequence_array_subscripts(context, (array_subscripts) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.COMPONENT_REFERENCE:
				if(context == grammarAccess.getComponent_referenceRule()) {
					sequence_component_reference(context, (component_reference) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.CONDITIONAL_EXPR:
				if(context == grammarAccess.getConditional_exprRule()) {
					sequence_conditional_expr(context, (conditional_expr) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.EXPRESSION_LIST:
				if(context == grammarAccess.getExpression_listRule()) {
					sequence_expression_list(context, (expression_list) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FOR_INDEX:
				if(context == grammarAccess.getFor_indexRule()) {
					sequence_for_index(context, (for_index) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getFor_indicesRule()) {
					sequence_for_indices(context, (for_index) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FUNCTION_ARGUMENTS:
				if(context == grammarAccess.getFunction_argumentsRule()) {
					sequence_function_arguments(context, (function_arguments) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FUNCTION_CALL_ARGS:
				if(context == grammarAccess.getFunction_call_argsRule() ||
				   context == grammarAccess.getInitial_refRule()) {
					sequence_function_call_args(context, (function_call_args) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.LOGICAL_FACTOR:
				if(context == grammarAccess.getLogical_expressionRule()) {
					sequence_logical_expression(context, (logical_factor) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getLogical_factorRule()) {
					sequence_logical_factor(context, (logical_factor) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getLogical_termRule()) {
					sequence_logical_term(context, (logical_factor) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.NAME:
				if(context == grammarAccess.getName_FunctionRule()) {
					sequence_name_Function(context, (name) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getNameRule()) {
					sequence_name(context, (name) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.NAMED_ARGUMENT:
				if(context == grammarAccess.getNamed_argumentRule()) {
					sequence_named_argument(context, (named_argument) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getNamed_argumentsRule()) {
					sequence_named_arguments(context, (named_argument) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.OUTPUT_EXPRESSION_LIST:
				if(context == grammarAccess.getOutput_expression_listRule()) {
					sequence_output_expression_list(context, (output_expression_list) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.SIMPLE_EXPRESSION:
				if(context == grammarAccess.getSimple_expressionRule()) {
					sequence_simple_expression(context, (simple_expression) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.SUBSCRIPT:
				if(context == grammarAccess.getSubscriptRule()) {
					sequence_subscript(context, (subscript) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == StatetransitionguardexpressionPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case StatetransitionguardexpressionPackage.MACRO_AFTER:
				if(context == grammarAccess.getMacro_AfterRule()) {
					sequence_Macro_After(context, (Macro_After) semanticObject); 
					return; 
				}
				else break;
			case StatetransitionguardexpressionPackage.MACRO_BEFORE:
				if(context == grammarAccess.getMacro_BEFORERule()) {
					sequence_Macro_BEFORE(context, (Macro_BEFORE) semanticObject); 
					return; 
				}
				else break;
			case StatetransitionguardexpressionPackage.MACRO_CHANGE:
				if(context == grammarAccess.getMacro_CHANGERule()) {
					sequence_Macro_CHANGE(context, (Macro_CHANGE) semanticObject); 
					return; 
				}
				else break;
			case StatetransitionguardexpressionPackage.MACRO_EVENT:
				if(context == grammarAccess.getMacro_EVENTRule()) {
					sequence_Macro_EVENT(context, (Macro_EVENT) semanticObject); 
					return; 
				}
				else break;
			case StatetransitionguardexpressionPackage.MACRO_SIGNAL:
				if(context == grammarAccess.getMacro_SIGNALRule()) {
					sequence_Macro_SIGNAL(context, (Macro_SIGNAL) semanticObject); 
					return; 
				}
				else break;
			case StatetransitionguardexpressionPackage.EXPRESSION:
				if(context == grammarAccess.getExpressionRule()) {
					sequence_expression(context, (expression) semanticObject); 
					return; 
				}
				else break;
			case StatetransitionguardexpressionPackage.GUARD_EXPRESSION:
				if(context == grammarAccess.getGuardExpressionRule()) {
					sequence_guardExpression(context, (guardExpression) semanticObject); 
					return; 
				}
				else break;
			case StatetransitionguardexpressionPackage.PRIMARY:
				if(context == grammarAccess.getFactorRule()) {
					sequence_factor(context, (primary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getPrimaryRule()) {
					sequence_primary(context, (primary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTermRule()) {
					sequence_term(context, (primary) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     functionArgs=function_call_args
	 *
	 * Features:
	 *    functionArgs[1, 1]
	 */
	protected void sequence_ExprDer(EObject context, ExprDer semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Args=function_arguments
	 *
	 * Features:
	 *    Args[1, 1]
	 */
	protected void sequence_Fun_Arguments_exp(EObject context, Fun_Arguments_exp semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     For_indices=for_indices
	 *
	 * Features:
	 *    For_indices[1, 1]
	 */
	protected void sequence_Fun_Arguments_for(EObject context, Fun_Arguments_for semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference)
	 *
	 * Features:
	 *    int[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET Component_reference
	 *    num[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET Component_reference
	 *    Component_reference[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET num
	 */
	protected void sequence_Macro_After(EObject context, Macro_After semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (int=INT | num=UNSIGNED_NUMBER | Component_reference=component_reference)
	 *
	 * Features:
	 *    int[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET Component_reference
	 *    num[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET Component_reference
	 *    Component_reference[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET num
	 */
	protected void sequence_Macro_BEFORE(EObject context, Macro_BEFORE semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Component_reference=component_reference
	 *
	 * Features:
	 *    Component_reference[1, 1]
	 */
	protected void sequence_Macro_CHANGE(EObject context, Macro_CHANGE semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, StatetransitionguardexpressionPackage.Literals.MACRO_CHANGE__COMPONENT_REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, StatetransitionguardexpressionPackage.Literals.MACRO_CHANGE__COMPONENT_REFERENCE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMacro_CHANGEAccess().getComponent_referenceComponent_referenceParserRuleCall_2_0(), semanticObject.getComponent_reference());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     Component_reference=component_reference
	 *
	 * Features:
	 *    Component_reference[1, 1]
	 */
	protected void sequence_Macro_EVENT(EObject context, Macro_EVENT semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, StatetransitionguardexpressionPackage.Literals.MACRO_EVENT__COMPONENT_REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, StatetransitionguardexpressionPackage.Literals.MACRO_EVENT__COMPONENT_REFERENCE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMacro_EVENTAccess().getComponent_referenceComponent_referenceParserRuleCall_2_0(), semanticObject.getComponent_reference());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     Component_reference=component_reference
	 *
	 * Features:
	 *    Component_reference[1, 1]
	 */
	protected void sequence_Macro_SIGNAL(EObject context, Macro_SIGNAL semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, StatetransitionguardexpressionPackage.Literals.MACRO_SIGNAL__COMPONENT_REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, StatetransitionguardexpressionPackage.Literals.MACRO_SIGNAL__COMPONENT_REFERENCE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMacro_SIGNALAccess().getComponent_referenceComponent_referenceParserRuleCall_2_0(), semanticObject.getComponent_reference());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (opr=add_op_plus | opr=add_op_minus | opr=add_op_dotplus | opr=add_op_dotminus)? 
	 *         Term=term 
	 *         ((Oper1+=add_op_plus | Oper1+=add_op_minus | Oper1+=add_op_dotplus | Oper1+=add_op_dotminus) Term1+=term)*
	 *     )
	 *
	 * Features:
	 *    opr[0, 4]
	 *    Term[1, 1]
	 *    Oper1[0, *]
	 *         SAME_OR_LESS Term1
	 *    Term1[0, *]
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 */
	protected void sequence_arithmetic_expression(EObject context, arithmetic_expression semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Sub=subscript Subscript+=subscript*)
	 *
	 * Features:
	 *    Sub[1, 1]
	 *    Subscript[0, *]
	 */
	protected void sequence_array_subscripts(EObject context, array_subscripts semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ref=IDENT subscripts1=array_subscripts? (ref1+=IDENT subscripts+=array_subscripts?)*)
	 *
	 * Features:
	 *    ref[1, 1]
	 *    subscripts1[0, 1]
	 *    ref1[0, *]
	 *         SAME_OR_MORE subscripts
	 *    subscripts[0, *]
	 *         SAME_OR_LESS ref1
	 */
	protected void sequence_component_reference(EObject context, component_reference semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ifexpr=expression thenexpr=expression (elseifexpr+=expression trueexpr+=expression)* falseexpr=expression)
	 *
	 * Features:
	 *    ifexpr[1, 1]
	 *    thenexpr[1, 1]
	 *    elseifexpr[0, *]
	 *         SAME trueexpr
	 *    trueexpr[0, *]
	 *         SAME elseifexpr
	 *    falseexpr[1, 1]
	 */
	protected void sequence_conditional_expr(EObject context, conditional_expr semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     simple_Expr=simple_expression
	 *
	 * Features:
	 *    simple_Expr[1, 1]
	 */
	protected void sequence_expression(EObject context, expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expr=expression Expre+=expression*)
	 *
	 * Features:
	 *    expr[1, 1]
	 *    Expre[0, *]
	 */
	protected void sequence_expression_list(EObject context, expression_list semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             num=UNSIGNED_NUMBER | 
	 *             int=INT | 
	 *             str=STRING | 
	 *             Bool=BOOL_VAL | 
	 *             Name_Function=name_Function | 
	 *             Initial_ref=initial_ref | 
	 *             Expr=ExprDer | 
	 *             Mac_A=Macro_After | 
	 *             Mac_E=Macro_EVENT | 
	 *             Mac_C=Macro_CHANGE | 
	 *             Mac_S=Macro_SIGNAL | 
	 *             Component_reference=component_reference | 
	 *             output_expr_list=output_expression_list | 
	 *             (Expre_list=expression_list Expression_list+=expression_list*) | 
	 *             f_arguments=function_arguments | 
	 *             End='end' | 
	 *             Else='else'
	 *         ) 
	 *         Primary=primary?
	 *     )
	 *
	 * Features:
	 *    Primary[0, 1]
	 *    num[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    int[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    str[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Bool[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Component_reference[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    output_expr_list[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expre_list[0, 1]
	 *         MANDATORY_IF_SET Expression_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expression_list[0, *]
	 *         EXCLUDE_IF_UNSET Expre_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    f_arguments[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    End[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET Else
	 *    Name_Function[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Initial_ref[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expr[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_A[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_E[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_C[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_S[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Else[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 */
	protected void sequence_factor(EObject context, primary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (index=IDENT expr=expression?)
	 *
	 * Features:
	 *    index[1, 1]
	 *    expr[0, 1]
	 */
	protected void sequence_for_index(EObject context, for_index semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (index=IDENT expr=expression? For_index+=for_index*)
	 *
	 * Features:
	 *    For_index[0, *]
	 *    index[1, 1]
	 *    expr[0, 1]
	 */
	protected void sequence_for_indices(EObject context, for_index semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)?)
	 *
	 * Features:
	 *    ArgExp[1, 1]
	 *    Fun_Arg_Expr[0, 1]
	 *         EXCLUDE_IF_SET Fun_Arg_For
	 *    Fun_Arg_For[0, 1]
	 *         EXCLUDE_IF_SET Fun_Arg_Expr
	 */
	protected void sequence_function_arguments(EObject context, function_arguments semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (f_arg=function_arguments?)
	 *
	 * Features:
	 *    f_arg[0, 1]
	 */
	protected void sequence_function_call_args(EObject context, function_call_args semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Expression=expression?
	 *
	 * Features:
	 *    Expression[0, 1]
	 */
	protected void sequence_guardExpression(EObject context, guardExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Relation=relation Logical_factor+=logical_factor* Logical_term+=logical_term*)
	 *
	 * Features:
	 *    Logical_term[0, *]
	 *    Logical_factor[0, *]
	 *    Relation[1, 1]
	 */
	protected void sequence_logical_expression(EObject context, logical_factor semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Relation=relation
	 *
	 * Features:
	 *    Relation[1, 1]
	 */
	protected void sequence_logical_factor(EObject context, logical_factor semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Relation=relation Logical_factor+=logical_factor*)
	 *
	 * Features:
	 *    Logical_factor[0, *]
	 *    Relation[1, 1]
	 */
	protected void sequence_logical_term(EObject context, logical_factor semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name_ID=IDENT nam_ID+=IDENT* Function_call_args=function_call_args)
	 *
	 * Features:
	 *    Function_call_args[1, 1]
	 *    name_ID[1, 1]
	 *    nam_ID[0, *]
	 */
	protected void sequence_name_Function(EObject context, name semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name_ID=IDENT nam_ID+=IDENT*)
	 *
	 * Features:
	 *    name_ID[1, 1]
	 *    nam_ID[0, *]
	 */
	protected void sequence_name(EObject context, name semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (arg=IDENT expr=expression)
	 *
	 * Features:
	 *    arg[1, 1]
	 *    expr[1, 1]
	 */
	protected void sequence_named_argument(EObject context, named_argument semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (arg=IDENT expr=expression Named_arguments=named_arguments?)
	 *
	 * Features:
	 *    Named_arguments[0, 1]
	 *    arg[1, 1]
	 *    expr[1, 1]
	 */
	protected void sequence_named_arguments(EObject context, named_argument semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (epr=expression? Expr+=expression*)
	 *
	 * Features:
	 *    epr[0, 1]
	 *    Expr[0, *]
	 */
	protected void sequence_output_expression_list(EObject context, output_expression_list semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         num=UNSIGNED_NUMBER | 
	 *         int=INT | 
	 *         str=STRING | 
	 *         Bool=BOOL_VAL | 
	 *         Name_Function=name_Function | 
	 *         Initial_ref=initial_ref | 
	 *         Expr=ExprDer | 
	 *         Mac_A=Macro_After | 
	 *         Mac_E=Macro_EVENT | 
	 *         Mac_C=Macro_CHANGE | 
	 *         Mac_S=Macro_SIGNAL | 
	 *         Component_reference=component_reference | 
	 *         output_expr_list=output_expression_list | 
	 *         (Expre_list=expression_list Expression_list+=expression_list*) | 
	 *         f_arguments=function_arguments | 
	 *         End='end' | 
	 *         Else='else'
	 *     )
	 *
	 * Features:
	 *    num[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    int[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    str[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Bool[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Component_reference[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    output_expr_list[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expre_list[0, 1]
	 *         MANDATORY_IF_SET Expression_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expression_list[0, *]
	 *         EXCLUDE_IF_UNSET Expre_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    f_arguments[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    End[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET Else
	 *    Name_Function[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Initial_ref[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expr[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_A[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_E[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_C[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_S[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Else[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 */
	protected void sequence_primary(EObject context, primary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (opr=add_op_plus | opr=add_op_minus | opr=add_op_dotplus | opr=add_op_dotminus)? 
	 *         Term=term 
	 *         ((Oper1+=add_op_plus | Oper1+=add_op_minus | Oper1+=add_op_dotplus | Oper1+=add_op_dotminus) Term1+=term)* 
	 *         (
	 *             (
	 *                 op=rel_op_Less_then | 
	 *                 op=rel_op_Less_equal | 
	 *                 op=rel_op_greater_then | 
	 *                 op=rel_op_greater_equal | 
	 *                 op=rel_op_assignment | 
	 *                 op=rel_op_Oper
	 *             ) 
	 *             Arithmetic_expression=arithmetic_expression
	 *         )?
	 *     )
	 *
	 * Features:
	 *    op[0, 6]
	 *         EXCLUDE_IF_UNSET Arithmetic_expression
	 *    Arithmetic_expression[0, 1]
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *    opr[0, 4]
	 *    Term[1, 1]
	 *    Oper1[0, *]
	 *         SAME_OR_LESS Term1
	 *    Term1[0, *]
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 */
	protected void sequence_relation(EObject context, arithmetic_expression semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Log_Exp=logical_expression (S_Logical_expression=logical_expression L_Logical_expression=logical_expression?)?)
	 *
	 * Features:
	 *    Log_Exp[1, 1]
	 *    S_Logical_expression[0, 1]
	 *         MANDATORY_IF_SET L_Logical_expression
	 *    L_Logical_expression[0, 1]
	 *         EXCLUDE_IF_UNSET S_Logical_expression
	 */
	protected void sequence_simple_expression(EObject context, simple_expression semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {subscript}
	 *
	 * Features:
	 */
	protected void sequence_subscript(EObject context, subscript semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             num=UNSIGNED_NUMBER | 
	 *             int=INT | 
	 *             str=STRING | 
	 *             Bool=BOOL_VAL | 
	 *             Name_Function=name_Function | 
	 *             Initial_ref=initial_ref | 
	 *             Expr=ExprDer | 
	 *             Mac_A=Macro_After | 
	 *             Mac_E=Macro_EVENT | 
	 *             Mac_C=Macro_CHANGE | 
	 *             Mac_S=Macro_SIGNAL | 
	 *             Component_reference=component_reference | 
	 *             output_expr_list=output_expression_list | 
	 *             (Expre_list=expression_list Expression_list+=expression_list*) | 
	 *             f_arguments=function_arguments | 
	 *             End='end' | 
	 *             Else='else'
	 *         ) 
	 *         Primary=primary? 
	 *         ((op+=mul_op_mul | op+=mul_op_div | op+=mul_op_dotmul | op+=mul_op_dotdiv) Factor+=factor)*
	 *     )
	 *
	 * Features:
	 *    op[0, *]
	 *         SAME_OR_LESS Factor
	 *    Factor[0, *]
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *    Primary[0, 1]
	 *    num[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    int[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    str[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Bool[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Component_reference[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    output_expr_list[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expre_list[0, 1]
	 *         MANDATORY_IF_SET Expression_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expression_list[0, *]
	 *         EXCLUDE_IF_UNSET Expre_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    f_arguments[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    End[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET Else
	 *    Name_Function[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Initial_ref[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Expr[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_A[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_E[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_C[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Mac_S[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *         EXCLUDE_IF_SET Else
	 *    Else[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Name_Function
	 *         EXCLUDE_IF_SET Initial_ref
	 *         EXCLUDE_IF_SET Expr
	 *         EXCLUDE_IF_SET Mac_A
	 *         EXCLUDE_IF_SET Mac_E
	 *         EXCLUDE_IF_SET Mac_C
	 *         EXCLUDE_IF_SET Mac_S
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 */
	protected void sequence_term(EObject context, primary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
