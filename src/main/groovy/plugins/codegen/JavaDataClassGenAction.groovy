package plugins.codegen

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

import plugins.codegen.all.AllGenerateConstructorAction
import plugins.codegen.all.AllGenerateGetterAction
import plugins.formatcustom.FormatCustomAction

class JavaDataClassGenAction extends AnAction {

  @Override
  void actionPerformed(AnActionEvent e) {
    new AllGenerateConstructorAction().actionPerformed(e)
    new AllGenerateGetterAction().actionPerformed(e)
    new FormatCustomAction().actionPerformed(e)
  }

}