package plugins.copying

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.vfs.VirtualFile

class CopyPathRelativeToModuleAction extends AbstractCopyFilePathAction {

  CopyPathRelativeToModuleAction() {
    super("Copy Path Relative To Parent Module Directory");
  }

  @Override
  String getCopyStringForFile(AnActionEvent event, VirtualFile file) {
    ProjectFileIndex projectFileIndex = ProjectFileIndex.getInstance(event.getProject());
    VirtualFile contentRoot = projectFileIndex.getContentRootForFile(file);
    return file.getPresentableUrl().replace(contentRoot.getPresentableUrl() + "/", "");
  }

}