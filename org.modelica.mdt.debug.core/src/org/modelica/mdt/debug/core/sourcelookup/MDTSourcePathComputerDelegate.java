package org.modelica.mdt.debug.core.sourcelookup;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate;
import org.eclipse.debug.core.sourcelookup.containers.FolderSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.WorkspaceSourceContainer;

import org.modelica.mdt.debug.core.launcher.IMDTConstants;

/**
 * Computes the default source lookup path for a MDT launch configuration. The
 * default source lookup path is the folder or project containing the MDT
 * program being launched. If the program is not specified, the workspace is
 * searched by default.
 */
public class MDTSourcePathComputerDelegate implements ISourcePathComputerDelegate {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.internal.core.sourcelookup.ISourcePathComputerDelegate#computeSourceContainers(org.eclipse.debug.core.ILaunchConfiguration,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public ISourceContainer[] computeSourceContainers(ILaunchConfiguration configuration, IProgressMonitor monitor)	throws CoreException {
		String path = configuration.getAttribute(IMDTConstants.ATTR_MDT_PROGRAM, (String) null);
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
		ISourceContainer sourceContainer = null;
		if (path != null) {
			IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));
			if (resource == null) 
			{
				// try via the file!
				resource = ResourcesPlugin.getWorkspace().getRoot().findMember(f.getProjectRelativePath());
			}
			if (resource != null) {
				IContainer container = resource.getProject();
				if (container.getType() == IResource.PROJECT) {
					sourceContainer = new ProjectSourceContainer((IProject) container, false);
				} else if (container.getType() == IResource.FOLDER) {
					sourceContainer = new FolderSourceContainer(container,
							false);
				}
			}
			else
			{
				IContainer container = f.getProject();
				if (container.getType() == IResource.PROJECT) {
					sourceContainer = new ProjectSourceContainer((IProject) container, false);
				} else if (container.getType() == IResource.FOLDER) {
					sourceContainer = new FolderSourceContainer(container,
							false);
				}				
			}
		}
		if (sourceContainer == null) {
			sourceContainer = new WorkspaceSourceContainer();
		}
		return new ISourceContainer[] { sourceContainer };
	}

}
