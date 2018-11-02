package plugins.fontsizeupdater

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

import common.PrefsUtil

class UpdateFontSizeAction extends AnAction {

  private int fontSize;

  UpdateFontSizeAction(int fontSize) {
    this.fontSize = fontSize;
  }

  @Override
  void actionPerformed(AnActionEvent e) {
    PrefsUtil.updateEditorFont(fontSize)
    PrefsUtil.updateSystemFont(fontSize)
    PrefsUtil.reloadUI()
  }
}