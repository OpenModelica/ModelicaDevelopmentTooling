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
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part;


import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.actions.TextViewerAction;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.uml2.uml.Element;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.Activator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.helper.ActiveFocusControlExpression;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing.ISyntheticResourceProvider;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing.OperationHistoryListener;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing.PartialModelEditor;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing.SourceViewerHandle;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing.SourceViewerHandleFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Injector;

// TODO: Auto-generated Javadoc
/**
 * Base class to handle a small in-diagram XtextEditor.
 * 
 * Override the generated <code>performDirectEdit</code> methods in the EditPart of the label to be directly edited, and
 * call {@link #showEditor()} instead of opening the default {@link TextCellEditor}.
 * 
 * @author koehnlein
 * @author CEA LIST - Some modifications for the integration in Papyrus 
 * Changes:
 * - Added "implements" relationship towards IPopupEditorHelper, 
 * 		related to the DirectEditor extension point of Papyrus
 * - Signature of the constructor modified
 * - Method showEditor modified, with the creation of a temporary file, underlying the popup xtext editor
 * - Method closeEditor modified, with new statements for managing the reconciliation between the original
 * 		UML model and the textual specification resulting from the edition in the popup xtext editor. The 
 * 		temporary text file created by showEditor is also deleted.
 * - Method createXTextEditor modified. Now uses the Papyrus IEditorSite, and also adds a focus listener for
 * 		managing the context eobject and current xtext editor.
 * - Method setEditorRegin modified (needs some work...)
 * - Method setEditorBounds modified (needs some work...)
 * - Method activateServices and deactivateServices, for managing the key binding of the context diagram
 * 		editor.
 */

public class PropertiesSectionXtextEditorHelper implements FocusListener, DisposeListener  {

	/** The host edit part. */
	private Element hostEditPart;
	
	/** The xtext editor composite. */
	private Composite xtextEditorComposite;
	
	/** The xtext injector. */
	private final Injector xtextInjector;
	
	/** The text to edit. */
	private String textToEdit ;
	
	/** The file extension used to dynamically select the appropriate xtext editor. */
	public static String fileExtension ;
	
	/** The model reconciler. */
	private IXtextEMFReconciler modelReconciler;
	
	/** The resource provider. */
	private ISyntheticResourceProvider resourceProvider ;
	
	/** The source viewer handle. */
	private SourceViewerHandle sourceViewerHandle ;
	
	/** The partial editor. */
	private PartialModelEditor partialEditor ;

	/** The operation history listener. */
	private OperationHistoryListener operationHistoryListener;
	/**
	 * The context EObject for this editor. It can be used for content assist, verification, etc.
	 */
	
	public EObject context ;
	
	/** The selected uml element. */
	Element selectedUmlElement;
	
	/** The LANGUAGE. */
	protected static String LANGUAGE = "";
	
	/**
	 * This element was originally undocumented in the XText/GMF integration example
	 * 
	 * Modifications performed by CEA LIST
	 * - Signature changed: was public PopupXtextEditorHelper(IGraphicalEditPart editPart, Injector xtextInjector).
	 *
	 * @param editPart The editPart on which a direct edit has been performed.
	 * @param xtextInjector The xtextInjector.
	 * @param modelReconciler The IXtextEMFReconciler, to update the context UML model with changes textually specified in the popup xtext editor
	 * @param textToEdit the initialization text, used as the initial textual content for the popup xtext editor
	 * @param fileExtension the extension for the temporary textual file (underlying the editor)
	 */
	public PropertiesSectionXtextEditorHelper(Element editPart, 
							Injector xtextInjector,
							IXtextEMFReconciler modelReconciler,
							String textToEdit, 
							String fileExtension
							) {
		this.hostEditPart = editPart;
		this.xtextInjector = xtextInjector ;
		this.textToEdit = "" + textToEdit ;
		this.modelReconciler = modelReconciler ;
		this.fileExtension = "" + fileExtension ;
	}

	
	/**
	 * This element was originally not documented in the XText / GMF integration example.
	 * 
	 * Changes from CEA LIST:
	 * - Statements added for the creation of a temporary text file, used as an input for the popup xtext editor
	 *
	 * @param composite the composite
	 * @param style the style
	 */
	public void showEditor(Composite composite, int style) {
		try {
			this.context = hostEditPart ;
			createXtextEditor(null, composite) ;
			
		} catch (Exception e) {
			Activator.logError(e);
		}
	}

	/**
	 * Sets the text to be edited.
	 *
	 * @param text the new text to edit
	 */
	
	/*
	 *  @param text Sets the text to be edited.
	 */
	public void setTextToEdit(String text){
		sourceViewerHandle.getViewer().getTextWidget().setText(text);
	}

	/**
	 * Sets the text to be edited.
	 *
	 * @return text
	 */
	
	/*
	 *  @param text Sets the text to be edited.
	 */
	public String getText(){
		if (sourceViewerHandle.getViewer().getTextWidget() != null) {
			String text = sourceViewerHandle.getViewer().getTextWidget().getText();
			if (text == null) {
				return "";
			}
			return text;
		}
		return "";
	}
	
	
	/**
	 * Sets the context element.
	 *
	 * @return XtextResource
	 */
	public XtextResource getXtextResource(){
		return partialEditor.createResource(getText());
	}
	
	/**
	 * Sets the context element.
	 *
	 * @param contextElement the new context element
	 */
	
	public void setContextElement(EObject contextElement){
		this.context= contextElement;
	}

	/**
	 * Sets the context element.
	 *
	 * @return the widgit
	 */
	public StyledText getEditorWidget(){
		return sourceViewerHandle.getViewer().getTextWidget();
	}
	
	
	
	/**
 * This element was originally not documented in the XText/GMF integration example
 * 
 * Changes performed by CEA LIST:
 * - Now uses the Papyrus IEditorSite
 * - adds a focus listener for managing the context eobject and current xtext editor.
 *
 * @param editorInput the editor input
 * @param composite the composite
 * @throws Exception the exception
 */
	private void createXtextEditor(IEditorInput editorInput, Composite composite) throws Exception {
		
		xtextEditorComposite = composite;
		xtextEditorComposite.setLayout(new FillLayout());
		
		resourceProvider = xtextInjector.getInstance(ISyntheticResourceProvider.class) ;
		SourceViewerHandleFactory factory = xtextInjector.getInstance(SourceViewerHandleFactory.class) ;
		sourceViewerHandle = factory.create(xtextEditorComposite, resourceProvider) ;
		partialEditor = sourceViewerHandle.createPartialEditor("", textToEdit, "") ;	
		
//		registerKeyListener();
		
		initializeActions();
		
//		installUndoRedoSupport(sourceViewerHandle.getViewer());
		
//		sourceViewerHandle.getViewer().getTextWidget().addFocusListener(new FocusListener() {
//			
//			public void focusLost(FocusEvent e) {
//				// TODO Auto-generated method stub
//				//context = semanticElement;
////				closeEditor(true) ;
//				storeBody(context, sourceViewerHandle.getViewer().getTextWidget().getText());
//				// TODO: save the model instead closing it.
//			}
//			
//			public void focusGained(FocusEvent e) {
//				// TODO Auto-generated method stub
//				//context = semanticElement ;
//			}
//		}) ;
		
		xtextEditorComposite.setVisible(true);
		sourceViewerHandle.getViewer().showAnnotationsOverview(true) ;
		sourceViewerHandle.getViewer().getTextWidget().setFocus();

		StyledText textWidget = sourceViewerHandle.getViewer().getTextWidget();   
		textWidget.addFocusListener(this);   
		textWidget.addDisposeListener(this);
		
//		if (sourceViewerHandle.getViewer().getTextWidget().isFocusControl()) {    
//			activateContext();   
//		}
	}

	public void focusLost(FocusEvent e) {
		deactivateContext();
	}

	public void focusGained(FocusEvent e) {
		activateContext();
	}

	public void widgetDisposed(DisposeEvent e) {
		deactivateContext();
	}
	
	
	
//	/** The key listener. */
//	private PropertiesSectionXtextEditorKeyListener keyListener ;
//	
//	/**
//	 * Register key listener.
//	 */
//	private void registerKeyListener() {
//		//XtextSourceViewer sourceViewer = (XtextSourceViewer) xtextEditor.getInternalSourceViewer();
//		final StyledText xtextTextWidget = sourceViewerHandle.getViewer().getTextWidget();
//		keyListener = new PropertiesSectionXtextEditorKeyListener (this, sourceViewerHandle.getViewer().getContentAssistant());
////		keyListener.installUndoRedoSupport(sourceViewerHandle.getViewer()) ;
//		xtextTextWidget.addVerifyKeyListener(keyListener);
//		xtextTextWidget.addKeyListener(keyListener);
//	}

	
	/**
	 * Checks if is document has errors.
	 *
	 * @return boolean
	 */
//	public boolean isDocumentHasErrors() {
//		IXtextDocument xtextDocument = sourceViewerHandle.getDocument();
//		return (xtextDocument.readOnly(new IUnitOfWork<Boolean, XtextResource>() {
//			public Boolean exec(XtextResource state) throws Exception {
//				IParseResult parseResult = state.getParseResult();
//				return !state.getErrors().isEmpty() || parseResult == null;
//			}
//		}));
//	}
//	

	/**
	 * Checks if is document has errors.
	 *
	 * @return boolean
	 */
	public boolean isDocumentHasErrors() {
//		partialEditor.createResource(getText());
		if (getXtextResource().getContents() != null && getXtextResource().getContents().size() > 0) {
			EObject myModel = getXtextResource().getContents().get(0);
			Diagnostic diagnostic = Diagnostician.INSTANCE.validate(myModel);
			switch (diagnostic.getSeverity()) {
			  case Diagnostic.ERROR: {
				  return true;
//				System.err.println("Model has errors: ",diagnostic);
			  }
			  case Diagnostic.WARNING:
				  return false;
//			    System.err.println("Model has warnings: ",diagnostic);
			}
		}
		
		IXtextDocument xtextDocument = sourceViewerHandle.getDocument();
		return (xtextDocument.readOnly(new IUnitOfWork<Boolean, XtextResource>() {
			public Boolean exec(XtextResource state) throws Exception {
				IParseResult parseResult = state.getParseResult();
				return !state.getErrors().isEmpty() || parseResult == null;
			}
		}));
		
	}

	



	
	
	/**
	 * Creates the error status.
	 *
	 * @param message the message
	 * @param e the e
	 * @return the status
	 */
	protected Status createErrorStatus(String message, TemplateException e) {
		return new Status(IStatus.ERROR, 
			"org.eclipse.papyrus.property.editor.xtext",message, e);
		
	}
	
	
//	/**
//	 * Install undo redo support.
//	 *
//	 * @param viewer the viewer
//	 */
//	protected void installUndoRedoSupport(SourceViewer viewer) {
//		IDocumentUndoManager undoManager = DocumentUndoManagerRegistry.getDocumentUndoManager(viewer.getDocument());
//		final IUndoContext context = undoManager.getUndoContext();
//		IOperationHistory operationHistory = OperationHistoryFactory.getOperationHistory() ;
//		operationHistoryListener = new OperationHistoryListener(context, new IUpdate() {
//			public void update() {
//				updateAction(ITextEditorActionConstants.REDO);
//				updateAction(ITextEditorActionConstants.UNDO);
//			}
//		});
//		operationHistory.addOperationHistoryListener(operationHistoryListener);
//	}
	
	/** The global actions. */
	private Map<String, org.eclipse.ui.console.actions.TextViewerAction> fGlobalActions= Maps.newHashMapWithExpectedSize(10);
	
	/** The selection actions. */
	private List<String> fSelectionActions = Lists.newArrayListWithExpectedSize(3);
	
//	/**
//	 * Update action.
//	 *
//	 * @param actionId the action id
//	 */
//	protected void updateAction(String actionId) {
//		IAction action= fGlobalActions.get(actionId);
//		if (action instanceof IUpdate)
//			((IUpdate) action).update();
//	}
//	
//	/**
//	 * Uninstall undo redo support.
//	 */
//	protected void uninstallUndoRedoSupport() {
//		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
//		operationHistory.removeOperationHistoryListener(operationHistoryListener);
//		operationHistoryListener = null;
//	}

	
	final List<IHandlerActivation> handlerActivations= Lists.newArrayListWithExpectedSize(3);
	final IHandlerService handlerService= (IHandlerService) PlatformUI.getWorkbench().getAdapter(IHandlerService.class);
	
	/**
	 * Initialize actions.
	 */
	private void initializeActions() {
		
//		OperationHistoryActionHandler undoAction = new UndoActionHandler(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite(), 
//				PlatformUI.getWorkbench().getOperationSupport().getUndoContext());
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(undoAction, IAbstractTextEditorHelpContextIds.UNDO_ACTION);
//		undoAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.UNDO);
//		undoAction.setId(ITextEditorActionConstants.UNDO);
//		IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
//		handlerService.activateHandler(undoAction.getActionDefinitionId(), new ActionHandler(undoAction));
		
		
//		final List<IHandlerActivation> handlerActivations= Lists.newArrayListWithExpectedSize(3);
//		final IHandlerService handlerService= (IHandlerService) PlatformUI.getWorkbench().getAdapter(IHandlerService.class);
//		final Expression expression= new ActiveShellExpression(sourceViewerHandle.getViewer().getControl().getShell());

//		diagramShell.addDisposeListener(new DisposeListener() {
//			public void widgetDisposed(DisposeEvent e) {
//				handlerService.deactivateHandlers(handlerActivations);
//				}
//		});

//		TextViewerAction action= new TextViewerAction(sourceViewerHandle.getViewer(), ITextOperationTarget.UNDO);
//		action.setText("UNDO");
//		fGlobalActions.put(ITextEditorActionConstants.UNDO, action);
//
//		action= new TextViewerAction(sourceViewerHandle.getViewer(), ITextOperationTarget.REDO);
//		action.setText("REDO");
//		fGlobalActions.put(ITextEditorActionConstants.REDO, action);

		TextViewerAction action= new TextViewerAction(sourceViewerHandle.getViewer(), ITextOperationTarget.CUT);
		action.setText("CUT");
		fGlobalActions.put(ITextEditorActionConstants.CUT, action);

		action= new TextViewerAction(sourceViewerHandle.getViewer(), ITextOperationTarget.COPY);
		action.setText("COPY");
		fGlobalActions.put(ITextEditorActionConstants.COPY, action);

		action= new TextViewerAction(sourceViewerHandle.getViewer(), ITextOperationTarget.PASTE);
		action.setText("PASTE");
		fGlobalActions.put(ITextEditorActionConstants.PASTE, action);

		action= new TextViewerAction(sourceViewerHandle.getViewer(), ITextOperationTarget.SELECT_ALL);
		action.setText("SELECT_ALL");
		fGlobalActions.put(ITextEditorActionConstants.SELECT_ALL, action);

		action= new TextViewerAction(sourceViewerHandle.getViewer(), ISourceViewer.CONTENTASSIST_PROPOSALS);
		action.setText("CONTENTASSIST_PROPOSALS");
		fGlobalActions.put(ITextEditorActionConstants.CONTENT_ASSIST, action);

		fSelectionActions.add(ITextEditorActionConstants.CUT);
		fSelectionActions.add(ITextEditorActionConstants.COPY);
		fSelectionActions.add(ITextEditorActionConstants.PASTE);
		
//		sourceViewerHandle.getViewer().getTextWidget().addFocusListener(new FocusListener() {
//			public void focusLost(FocusEvent e) {
//				handlerService.deactivateHandlers(handlerActivations);
//			}
//			public void focusGained(FocusEvent e) {
//				IAction action= fGlobalActions.get(ITextEditorActionConstants.REDO);
//				handlerActivations.add(handlerService.activateHandler(IWorkbenchCommandConstants.EDIT_REDO, new ActionHandler(action), new ActiveFocusControlExpression(getEditorWidget())));
//				action= fGlobalActions.get(ITextEditorActionConstants.UNDO);
//				handlerActivations.add(handlerService.activateHandler(IWorkbenchCommandConstants.EDIT_UNDO, new ActionHandler(action), new ActiveFocusControlExpression(getEditorWidget())));
//				action= fGlobalActions.get(ITextEditorActionConstants.CONTENT_ASSIST);
//				handlerActivations.add(handlerService.activateHandler(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new ActionHandler(action), expression));
//			}
//		});

	}
	
	
	protected void activateContext() {
		if (handlerActivations.isEmpty()) {
			
			IAction action= fGlobalActions.get(ITextEditorActionConstants.CONTENT_ASSIST);
			handlerActivations.add(handlerService.activateHandler(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new ActionHandler(action), new ActiveFocusControlExpression(getEditorWidget())));

//			activateHandler(ISourceViewer.QUICK_ASSIST, ITextEditorActionDefinitionIds.QUICK_ASSIST);
//			activateHandler(ISourceViewer.CONTENTASSIST_PROPOSALS, ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
//			activateHandler(ITextOperationTarget.CUT, ITextEditorActionDefinitionIds.CUT);
//			activateHandler(ITextOperationTarget.COPY, ITextEditorActionDefinitionIds.COPY);
//			activateHandler(ITextOperationTarget.PASTE, ITextEditorActionDefinitionIds.PASTE);
//			activateHandler(ITextOperationTarget.DELETE, ITextEditorActionDefinitionIds.DELETE);

//			activateHandler(ITextOperationTarget.UNDO, ITextEditorActionDefinitionIds.UNDO);
//			activateHandler(ITextOperationTarget.REDO, ITextEditorActionDefinitionIds.REDO);
			activateHandler(ITextOperationTarget.UNDO, IWorkbenchCommandConstants.EDIT_UNDO);
			activateHandler(ITextOperationTarget.REDO, IWorkbenchCommandConstants.EDIT_REDO);

		}
	}

	protected void activateHandler(int operation, String actionDefinitionId) {
		StyledText textWidget = sourceViewerHandle.getViewer().getTextWidget();
		IHandler actionHandler = createActionHandler(operation, actionDefinitionId);
		IHandlerActivation handlerActivation = handlerService.activateHandler(actionDefinitionId, actionHandler, new ActiveFocusControlExpression(textWidget));
		handlerActivations.add(handlerActivation);
	}

	private IHandler createActionHandler(final int operation, String actionDefinitionId) {
		Action action = new Action() {
			@Override
			public void run() {
				if (sourceViewerHandle.getViewer().canDoOperation(operation)) {
					sourceViewerHandle.getViewer().doOperation(operation);
				}
			}
		};
		action.setActionDefinitionId(actionDefinitionId);
		return new ActionHandler(action);
	}

	protected void deactivateContext() {
		if (!handlerActivations.isEmpty()) {
			for (IHandlerActivation activation : handlerActivations) {
				handlerService.deactivateHandler(activation);
				activation.getHandler().dispose();
			}
			handlerActivations.clear();
		}
	}
	
}
