package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated NOT
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SysmlElementTypes.ModelicaClass_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getPackage_PackagedElement());
			}
			return getMSLWrapper(new CreateModelicaClass_1001Command(req));
		}

		if (SysmlElementTypes.ModelicaType_1002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getPackage_PackagedElement());
			}
			return getMSLWrapper(new CreateModelicaType_1002Command(req));
		}

		// contributed code start
		if (SysmlElementTypes.ModelicaModel_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getPackage_PackagedElement());
			}
			return getMSLWrapper(new CreateModelicaClass_1001Command(req));
		}

		if (SysmlElementTypes.ModelicaBlock_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getPackage_PackagedElement());
			}
			return getMSLWrapper(new CreateModelicaClass_1001Command(req));
		}

		if (SysmlElementTypes.ModelicaRecord_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getPackage_PackagedElement());
			}
			return getMSLWrapper(new CreateModelicaClass_1001Command(req));
		}

		if (SysmlElementTypes.ModelicaConnector_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getPackage_PackagedElement());
			}
			return getMSLWrapper(new CreateModelicaClass_1001Command(req));
		}

		if (SysmlElementTypes.ModelicaFunction_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getPackage_PackagedElement());
			}
			return getMSLWrapper(new CreateModelicaClass_1001Command(req));
		}
		// contrubuted code end

		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	private static class CreateModelicaClass_1001Command extends
			CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateModelicaClass_1001Command(CreateElementRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return UMLPackage.eINSTANCE.getModel();
		};

		/**
		 * @generated
		 */
		protected EObject getElementToEdit() {
			EObject container = ((CreateElementRequest) getRequest())
					.getContainer();
			if (container instanceof View) {
				container = ((View) container).getElement();
			}
			return container;
		}
	}

	/**
	 * @generated
	 */
	private static class CreateModelicaType_1002Command extends
			CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateModelicaType_1002Command(CreateElementRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return UMLPackage.eINSTANCE.getModel();
		};

		/**
		 * @generated
		 */
		protected EObject getElementToEdit() {
			EObject container = ((CreateElementRequest) getRequest())
					.getContainer();
			if (container instanceof View) {
				container = ((View) container).getElement();
			}
			return container;
		}
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getMSLWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}
	}
}
