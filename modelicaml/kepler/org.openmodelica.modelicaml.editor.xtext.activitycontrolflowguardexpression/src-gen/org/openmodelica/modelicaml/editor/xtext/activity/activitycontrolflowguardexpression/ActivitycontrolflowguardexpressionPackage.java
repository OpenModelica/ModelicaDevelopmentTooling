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
package org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionFactory
 * @model kind="package"
 * @generated
 */
public interface ActivitycontrolflowguardexpressionPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "activitycontrolflowguardexpression";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/activity/Activitycontrolflowguardexpression";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "activitycontrolflowguardexpression";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ActivitycontrolflowguardexpressionPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.ActivitycontrolflowguardexpressionPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.activityControlFlowExpressionImpl <em>activity Control Flow Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.activityControlFlowExpressionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.ActivitycontrolflowguardexpressionPackageImpl#getactivityControlFlowExpression()
   * @generated
   */
  int ACTIVITY_CONTROL_FLOW_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Epression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION = 0;

  /**
   * The number of structural features of the '<em>activity Control Flow Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY_CONTROL_FLOW_EXPRESSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.expressionImpl <em>expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.expressionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.ActivitycontrolflowguardexpressionPackageImpl#getexpression()
   * @generated
   */
  int EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__EXPR = ModeleditorPackage.EXPRESSION__EXPR;

  /**
   * The feature id for the '<em><b>Else Keyword</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__ELSE_KEYWORD = ModeleditorPackage.EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = ModeleditorPackage.EXPRESSION_FEATURE_COUNT + 1;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression <em>activity Control Flow Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>activity Control Flow Expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression
   * @generated
   */
  EClass getactivityControlFlowExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression#getEpression <em>Epression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Epression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression#getEpression()
   * @see #getactivityControlFlowExpression()
   * @generated
   */
  EReference getactivityControlFlowExpression_Epression();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression <em>expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression
   * @generated
   */
  EClass getexpression();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression#getElseKeyword <em>Else Keyword</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Else Keyword</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression#getElseKeyword()
   * @see #getexpression()
   * @generated
   */
  EAttribute getexpression_ElseKeyword();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ActivitycontrolflowguardexpressionFactory getActivitycontrolflowguardexpressionFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.activityControlFlowExpressionImpl <em>activity Control Flow Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.activityControlFlowExpressionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.ActivitycontrolflowguardexpressionPackageImpl#getactivityControlFlowExpression()
     * @generated
     */
    EClass ACTIVITY_CONTROL_FLOW_EXPRESSION = eINSTANCE.getactivityControlFlowExpression();

    /**
     * The meta object literal for the '<em><b>Epression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION = eINSTANCE.getactivityControlFlowExpression_Epression();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.expressionImpl <em>expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.expressionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.ActivitycontrolflowguardexpressionPackageImpl#getexpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getexpression();

    /**
     * The meta object literal for the '<em><b>Else Keyword</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__ELSE_KEYWORD = eINSTANCE.getexpression_ElseKeyword();

  }

} //ActivitycontrolflowguardexpressionPackage
