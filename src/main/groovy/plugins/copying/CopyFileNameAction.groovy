package plugins.copying


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vfs.VirtualFile

class CopyFileNameAction extends AbstractCopyFilePathAction {

  CopyFileNameAction() {
    super("Copy File Name");
  }

  @Override
  String getCopyStringForFile(AnActionEvent event, VirtualFile file) {
    return file.getName();
  }
}