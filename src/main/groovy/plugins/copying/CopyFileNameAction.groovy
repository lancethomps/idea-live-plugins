package plugins.copying

import java.awt.datatransfer.StringSelection

import org.jetbrains.annotations.NotNull

import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.vfs.VirtualFile

class CopyFileNameAction extends AnAction implements DumbAware {

  CopyFileNameAction() {
    setEnabledInModalContext(true);
  }

  @Override
  void actionPerformed(@NotNull AnActionEvent e) {
    VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
    if (files != null && files.length > 0) {
      CopyPasteManager.getInstance().setContents(new StringSelection(getPaths(files)));
    }
  }

  private static String getPaths(VirtualFile[] files) {
    StringBuilder buf = new StringBuilder(files.length * 64);
    for (VirtualFile file : files) {
      if (buf.length() > 0)
        buf.append('\n');
      buf.append(file.getName());
    }
    return buf.toString();
  }

  @Override
  void update(@NotNull AnActionEvent event) {
    VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(event.getDataContext());
    int num = files != null ? files.length : 0;
    Presentation presentation = event.getPresentation();
    presentation.setEnabled(num > 0);
    presentation.setVisible(num > 0 || !ActionPlaces.isPopupPlace(event.getPlace()));
    presentation.setText("Copy File Name");
  }
}