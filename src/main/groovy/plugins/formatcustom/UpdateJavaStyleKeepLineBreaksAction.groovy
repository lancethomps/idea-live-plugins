package plugins.formatcustom

import com.intellij.openapi.actionSystem.AnActionEvent

import common.BasePluginAction
import common.PrefsUtil

class UpdateJavaStyleKeepLineBreaksAction extends BasePluginAction {

  private boolean keepLineBreaks;

  UpdateJavaStyleKeepLineBreaksAction(boolean keepLineBreaks) {
    this.keepLineBreaks = keepLineBreaks;
  }

  @Override
  void actionPerformed(AnActionEvent e) {
    super.actionPerformed(e)
    PrefsUtil.updateJavaCodeStyleKeepLineBreaks(keepLineBreaks)
  }
}
