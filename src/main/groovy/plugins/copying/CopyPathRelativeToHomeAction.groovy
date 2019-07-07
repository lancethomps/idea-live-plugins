package plugins.copying


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vfs.VirtualFile


class CopyPathRelativeToHomeAction extends AbstractCopyFilePathAction {

  CopyPathRelativeToHomeAction() {
    super("Copy Path Relative To Home Directory");
  }

  @Override
  String getCopyStringForFile(AnActionEvent event, VirtualFile file) {
    String home = System.getenv("HOME");
    if (home == null || home.isEmpty()) {
      home = System.getProperty('user.home');
    }
    return file.getPresentableUrl().replace(home + "/", "");
  }

}