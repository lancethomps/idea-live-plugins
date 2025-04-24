package plugins.codegen.all

import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.project.DumbAware

class AllGenerateGetterAction extends BaseGenerateAction implements DumbAware {

  AllGenerateGetterAction() {
    super(new AllGenerateGetterHandler())
  }

}
