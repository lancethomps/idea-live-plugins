package plugins.codegen.all

import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.project.DumbAware

class AllGenerateGetterAndSetterAction extends BaseGenerateAction implements DumbAware {

  AllGenerateGetterAndSetterAction() {
    super(new AllGenerateGetterAndSetterHandler())
  }

}
