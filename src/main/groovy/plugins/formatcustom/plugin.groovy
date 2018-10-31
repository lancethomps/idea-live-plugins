package plugins.formatcustom

import static liveplugin.PluginUtil.registerAction
import static liveplugin.PluginUtil.show

import com.intellij.application.options.codeStyle.arrangement.action.RearrangeCodeAction
import com.intellij.codeInsight.actions.ReformatCodeAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

class FormatCustomAction extends AnAction {

  @Override
  void actionPerformed(AnActionEvent e) {
    new ReformatCodeAction().actionPerformed(e)
    new RearrangeCodeAction().actionPerformed(e)
  }
}

registerAction("FormatCustom", "meta shift E", null, "Format Custom - ReformatCode, RearrangeCode", new FormatCustomAction())
if (!isIdeStartup) show("Loaded FormatCustom<br/>Run it through the actions search.")
