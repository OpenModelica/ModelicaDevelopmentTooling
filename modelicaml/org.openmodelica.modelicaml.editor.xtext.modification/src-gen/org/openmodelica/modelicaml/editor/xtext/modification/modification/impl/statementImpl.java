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
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl#getIf_statement <em>If statement</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl#getState_OutputExprList <em>State Output Expr List</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl#getState_Comp_Ref <em>State Comp Ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl#getFor_statement <em>For statement</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl#getWhen_statement <em>When statement</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl#getWhile_statement <em>While statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class statementImpl extends MinimalEObjectImpl.Container implements statement
{
  /**
   * The cached value of the '{@link #getIf_statement() <em>If statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIf_statement()
   * @generated
   * @ordered
   */
  protected if_statement if_statement;

  /**
   * The cached value of the '{@link #getState_OutputExprList() <em>State Output Expr List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getState_OutputExprList()
   * @generated
   * @ordered
   */
  protected state_OutputExprList state_OutputExprList;

  /**
   * The cached value of the '{@link #getState_Comp_Ref() <em>State Comp Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getState_Comp_Ref()
   * @generated
   * @ordered
   */
  protected state_Comp_Ref state_Comp_Ref;

  /**
   * The cached value of the '{@link #getFor_statement() <em>For statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFor_statement()
   * @generated
   * @ordered
   */
  protected for_statement for_statement;

  /**
   * The cached value of the '{@link #getWhen_statement() <em>When statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhen_statement()
   * @generated
   * @ordered
   */
  protected when_statement when_statement;

  /**
   * The cached value of the '{@link #getWhile_statement() <em>While statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhile_statement()
   * @generated
   * @ordered
   */
  protected while_statement while_statement;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected statementImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ModificationPackage.Literals.STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public if_statement getIf_statement()
  {
    return if_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIf_statement(if_statement newIf_statement, NotificationChain msgs)
  {
    if_statement oldIf_statement = if_statement;
    if_statement = newIf_statement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__IF_STATEMENT, oldIf_statement, newIf_statement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIf_statement(if_statement newIf_statement)
  {
    if (newIf_statement != if_statement)
    {
      NotificationChain msgs = null;
      if (if_statement != null)
        msgs = ((InternalEObject)if_statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__IF_STATEMENT, null, msgs);
      if (newIf_statement != null)
        msgs = ((InternalEObject)newIf_statement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__IF_STATEMENT, null, msgs);
      msgs = basicSetIf_statement(newIf_statement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__IF_STATEMENT, newIf_statement, newIf_statement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state_OutputExprList getState_OutputExprList()
  {
    return state_OutputExprList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetState_OutputExprList(state_OutputExprList newState_OutputExprList, NotificationChain msgs)
  {
    state_OutputExprList oldState_OutputExprList = state_OutputExprList;
    state_OutputExprList = newState_OutputExprList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST, oldState_OutputExprList, newState_OutputExprList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setState_OutputExprList(state_OutputExprList newState_OutputExprList)
  {
    if (newState_OutputExprList != state_OutputExprList)
    {
      NotificationChain msgs = null;
      if (state_OutputExprList != null)
        msgs = ((InternalEObject)state_OutputExprList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST, null, msgs);
      if (newState_OutputExprList != null)
        msgs = ((InternalEObject)newState_OutputExprList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST, null, msgs);
      msgs = basicSetState_OutputExprList(newState_OutputExprList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST, newState_OutputExprList, newState_OutputExprList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state_Comp_Ref getState_Comp_Ref()
  {
    return state_Comp_Ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetState_Comp_Ref(state_Comp_Ref newState_Comp_Ref, NotificationChain msgs)
  {
    state_Comp_Ref oldState_Comp_Ref = state_Comp_Ref;
    state_Comp_Ref = newState_Comp_Ref;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__STATE_COMP_REF, oldState_Comp_Ref, newState_Comp_Ref);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setState_Comp_Ref(state_Comp_Ref newState_Comp_Ref)
  {
    if (newState_Comp_Ref != state_Comp_Ref)
    {
      NotificationChain msgs = null;
      if (state_Comp_Ref != null)
        msgs = ((InternalEObject)state_Comp_Ref).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__STATE_COMP_REF, null, msgs);
      if (newState_Comp_Ref != null)
        msgs = ((InternalEObject)newState_Comp_Ref).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__STATE_COMP_REF, null, msgs);
      msgs = basicSetState_Comp_Ref(newState_Comp_Ref, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__STATE_COMP_REF, newState_Comp_Ref, newState_Comp_Ref));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_statement getFor_statement()
  {
    return for_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFor_statement(for_statement newFor_statement, NotificationChain msgs)
  {
    for_statement oldFor_statement = for_statement;
    for_statement = newFor_statement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__FOR_STATEMENT, oldFor_statement, newFor_statement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFor_statement(for_statement newFor_statement)
  {
    if (newFor_statement != for_statement)
    {
      NotificationChain msgs = null;
      if (for_statement != null)
        msgs = ((InternalEObject)for_statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__FOR_STATEMENT, null, msgs);
      if (newFor_statement != null)
        msgs = ((InternalEObject)newFor_statement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__FOR_STATEMENT, null, msgs);
      msgs = basicSetFor_statement(newFor_statement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__FOR_STATEMENT, newFor_statement, newFor_statement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public when_statement getWhen_statement()
  {
    return when_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhen_statement(when_statement newWhen_statement, NotificationChain msgs)
  {
    when_statement oldWhen_statement = when_statement;
    when_statement = newWhen_statement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__WHEN_STATEMENT, oldWhen_statement, newWhen_statement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhen_statement(when_statement newWhen_statement)
  {
    if (newWhen_statement != when_statement)
    {
      NotificationChain msgs = null;
      if (when_statement != null)
        msgs = ((InternalEObject)when_statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__WHEN_STATEMENT, null, msgs);
      if (newWhen_statement != null)
        msgs = ((InternalEObject)newWhen_statement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__WHEN_STATEMENT, null, msgs);
      msgs = basicSetWhen_statement(newWhen_statement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__WHEN_STATEMENT, newWhen_statement, newWhen_statement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public while_statement getWhile_statement()
  {
    return while_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhile_statement(while_statement newWhile_statement, NotificationChain msgs)
  {
    while_statement oldWhile_statement = while_statement;
    while_statement = newWhile_statement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__WHILE_STATEMENT, oldWhile_statement, newWhile_statement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhile_statement(while_statement newWhile_statement)
  {
    if (newWhile_statement != while_statement)
    {
      NotificationChain msgs = null;
      if (while_statement != null)
        msgs = ((InternalEObject)while_statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__WHILE_STATEMENT, null, msgs);
      if (newWhile_statement != null)
        msgs = ((InternalEObject)newWhile_statement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.STATEMENT__WHILE_STATEMENT, null, msgs);
      msgs = basicSetWhile_statement(newWhile_statement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.STATEMENT__WHILE_STATEMENT, newWhile_statement, newWhile_statement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModificationPackage.STATEMENT__IF_STATEMENT:
        return basicSetIf_statement(null, msgs);
      case ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST:
        return basicSetState_OutputExprList(null, msgs);
      case ModificationPackage.STATEMENT__STATE_COMP_REF:
        return basicSetState_Comp_Ref(null, msgs);
      case ModificationPackage.STATEMENT__FOR_STATEMENT:
        return basicSetFor_statement(null, msgs);
      case ModificationPackage.STATEMENT__WHEN_STATEMENT:
        return basicSetWhen_statement(null, msgs);
      case ModificationPackage.STATEMENT__WHILE_STATEMENT:
        return basicSetWhile_statement(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ModificationPackage.STATEMENT__IF_STATEMENT:
        return getIf_statement();
      case ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST:
        return getState_OutputExprList();
      case ModificationPackage.STATEMENT__STATE_COMP_REF:
        return getState_Comp_Ref();
      case ModificationPackage.STATEMENT__FOR_STATEMENT:
        return getFor_statement();
      case ModificationPackage.STATEMENT__WHEN_STATEMENT:
        return getWhen_statement();
      case ModificationPackage.STATEMENT__WHILE_STATEMENT:
        return getWhile_statement();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModificationPackage.STATEMENT__IF_STATEMENT:
        setIf_statement((if_statement)newValue);
        return;
      case ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST:
        setState_OutputExprList((state_OutputExprList)newValue);
        return;
      case ModificationPackage.STATEMENT__STATE_COMP_REF:
        setState_Comp_Ref((state_Comp_Ref)newValue);
        return;
      case ModificationPackage.STATEMENT__FOR_STATEMENT:
        setFor_statement((for_statement)newValue);
        return;
      case ModificationPackage.STATEMENT__WHEN_STATEMENT:
        setWhen_statement((when_statement)newValue);
        return;
      case ModificationPackage.STATEMENT__WHILE_STATEMENT:
        setWhile_statement((while_statement)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ModificationPackage.STATEMENT__IF_STATEMENT:
        setIf_statement((if_statement)null);
        return;
      case ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST:
        setState_OutputExprList((state_OutputExprList)null);
        return;
      case ModificationPackage.STATEMENT__STATE_COMP_REF:
        setState_Comp_Ref((state_Comp_Ref)null);
        return;
      case ModificationPackage.STATEMENT__FOR_STATEMENT:
        setFor_statement((for_statement)null);
        return;
      case ModificationPackage.STATEMENT__WHEN_STATEMENT:
        setWhen_statement((when_statement)null);
        return;
      case ModificationPackage.STATEMENT__WHILE_STATEMENT:
        setWhile_statement((while_statement)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ModificationPackage.STATEMENT__IF_STATEMENT:
        return if_statement != null;
      case ModificationPackage.STATEMENT__STATE_OUTPUT_EXPR_LIST:
        return state_OutputExprList != null;
      case ModificationPackage.STATEMENT__STATE_COMP_REF:
        return state_Comp_Ref != null;
      case ModificationPackage.STATEMENT__FOR_STATEMENT:
        return for_statement != null;
      case ModificationPackage.STATEMENT__WHEN_STATEMENT:
        return when_statement != null;
      case ModificationPackage.STATEMENT__WHILE_STATEMENT:
        return while_statement != null;
    }
    return super.eIsSet(featureID);
  }

} //statementImpl
