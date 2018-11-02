package plugins.codegen.all

import org.jetbrains.annotations.Nullable

import com.intellij.codeInsight.generation.ClassMember
import com.intellij.codeInsight.generation.GenerateGetterHandler
import com.intellij.codeInsight.generation.actions.GenerateGetterSetterBaseAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class AllGenerateGetterAction extends GenerateGetterSetterBaseAction {

  AllGenerateGetterAction() {
    super(new AllGenerateGetterHandler())
  }

  class AllGenerateGetterHandler extends GenerateGetterHandler {

    AllGenerateGetterHandler() {
      super();
    }

    @Override
    @Nullable
    protected ClassMember[] chooseMembers(ClassMember[] members,
                                          boolean allowEmptySelection,
                                          boolean copyJavadocCheckbox,
                                          Project project,
                                          @Nullable Editor editor) {
      return members;
    }
  }
}
