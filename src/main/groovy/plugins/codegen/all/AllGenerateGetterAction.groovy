package plugins.codegen.all

import com.intellij.codeInsight.generation.actions.GenerateGetterSetterBaseAction

class AllGenerateGetterAction extends GenerateGetterSetterBaseAction {

  AllGenerateGetterAction() {
    super(new AllGenerateGetterHandler())
  }

}
