package plugins.formatcustom

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

import common.PrefsUtil

class UpdateJavaStyleKeepLineBreaksAction extends AnAction {

  private boolean keepLineBreaks;

  UpdateJavaStyleKeepLineBreaksAction(boolean keepLineBreaks) {
    this.keepLineBreaks = keepLineBreaks;
  }

  @Override
  void actionPerformed(AnActionEvent e) {
    PrefsUtil.updateJavaCodeStyleKeepLineBreaks(keepLineBreaks)
  }
}
