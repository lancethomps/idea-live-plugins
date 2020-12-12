package plugins.codegen.all

import com.intellij.codeInsight.generation.actions.GenerateGetterSetterBaseAction

class AllGenerateGetterAndSetterAction extends GenerateGetterSetterBaseAction {

  AllGenerateGetterAndSetterAction() {
    super(new AllGenerateGetterAndSetterHandler())
  }

}
