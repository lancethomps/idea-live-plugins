package plugins.codegen.all

import org.jetbrains.annotations.Nullable

import com.intellij.codeInsight.generation.ClassMember
import com.intellij.codeInsight.generation.GenerateGetterAndSetterHandler
import com.intellij.codeInsight.generation.actions.GenerateGetterSetterBaseAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class AllGenerateGetterAndSetterAction extends GenerateGetterSetterBaseAction {

  AllGenerateGetterAndSetterAction() {
    super(new AllGenerateGetterAndSetterHandler())
  }

  class AllGenerateGetterAndSetterHandler extends GenerateGetterAndSetterHandler {

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


