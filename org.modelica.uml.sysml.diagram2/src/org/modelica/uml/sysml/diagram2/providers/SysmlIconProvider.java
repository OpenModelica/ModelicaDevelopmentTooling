package org.modelica.uml.sysml.diagram2.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.GetIconOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class SysmlIconProvider extends AbstractProvider implements
		IIconProvider {

	/**
	 * @generated
	 */
	public Image getIcon(IAdaptable hint, int flags) {
		return SysmlElementTypes.getImage(hint);
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetIconOperation) {
			return ((GetIconOperation) operation).execute(this) != null;
		}
		return false;
	}
}
