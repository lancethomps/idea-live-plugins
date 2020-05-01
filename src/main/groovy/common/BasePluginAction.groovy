package common

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

import liveplugin.PluginUtil

abstract class BasePluginAction extends AnAction {

  @Override
  void actionPerformed(AnActionEvent event) {
    PluginUtil.log(getClass().getSimpleName() + ": actionPerformed")
  }
}
