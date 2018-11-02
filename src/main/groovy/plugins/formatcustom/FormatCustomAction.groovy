package plugins.formatcustom

import com.intellij.application.options.codeStyle.arrangement.action.RearrangeCodeAction
import com.intellij.codeInsight.actions.ReformatCodeAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class FormatCustomAction extends AnAction {

  @Override
  void actionPerformed(AnActionEvent e) {
    new ReformatCodeAction().actionPerformed(e)
    new RearrangeCodeAction().actionPerformed(e)
  }
}
