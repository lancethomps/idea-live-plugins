package plugins.copying

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.vfs.VirtualFile

class CopyPathRelativeToTopModuleAction extends AbstractCopyFilePathAction {

  CopyPathRelativeToTopModuleAction() {
    super("Copy Path Relative To Top-Level Parent Module Directory");
  }

  @Override
  String getCopyStringForFile(AnActionEvent event, VirtualFile file) {
    ProjectFileIndex projectFileIndex = ProjectFileIndex.getInstance(event.getProject());
    int count = 0;
    VirtualFile contentRoot = projectFileIndex.getContentRootForFile(file);
    while (contentRoot != null && count < 10) {
      count++;
      VirtualFile parentContentRoot = projectFileIndex.getContentRootForFile(contentRoot.getParent());
      if (parentContentRoot == null) {
        break;
      }
      contentRoot = parentContentRoot;
    }
    return file.getPresentableUrl().replace(contentRoot.getPresentableUrl() + "/", "");
  }

}