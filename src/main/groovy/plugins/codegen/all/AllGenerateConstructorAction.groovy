package plugins.codegen.all

import com.intellij.codeInsight.generation.actions.BaseGenerateAction

class AllGenerateConstructorAction extends BaseGenerateAction {

  AllGenerateConstructorAction() {
    super(new AllGenerateConstructorHandler())
  }

}
