package plugins.copying

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vfs.VirtualFile

class CopyPathRelativeToGitRepoAction extends AbstractCopyFilePathAction {

  CopyPathRelativeToGitRepoAction() {
    super("Copy Path Relative To Git Repository");
  }

  @Override
  String getCopyStringForFile(AnActionEvent event, VirtualFile file) {
    VirtualFile gitDir = findGitDir(file);
    if (gitDir == null) {
      throw new IllegalArgumentException("file is not part of a git repository: " + file.getPresentableUrl());
    }
    return file.getPresentableUrl().replace(gitDir.getPresentableUrl() + "/", "");
  }

  VirtualFile findGitDir(VirtualFile file) {
    if (file.findChild(".git") != null) {
      return file;
    }
    def parent = file.getParent();
    if (parent == null) {
      return null;
    }
    return findGitDir(parent);
  }

}