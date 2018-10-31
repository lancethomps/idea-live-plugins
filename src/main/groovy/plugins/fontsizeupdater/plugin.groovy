package plugins.fontsizeupdater

import static liveplugin.PluginUtil.registerAction
import static liveplugin.PluginUtil.show

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import common.PrefsUtil

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

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

static def registerUpdateFontSize(String type, int fontSize, boolean isIdeStartup) {
  def id = String.format("UpdateFontSize%s", type.replaceAll(" ", ""))
  def displayText = String.format("Update Font Size - %s (%s)", type, fontSize);
  registerAction(id, "", null, displayText, new UpdateFontSizeAction(fontSize))
  if (!isIdeStartup) show(String.format("Loaded '%s'<br/>Run it through the actions search", displayText))
}

registerUpdateFontSize("Extra Small", 7, isIdeStartup)
registerUpdateFontSize("Small", 9, isIdeStartup)
registerUpdateFontSize("Medium", 10, isIdeStartup)
registerUpdateFontSize("Large", 11, isIdeStartup)
