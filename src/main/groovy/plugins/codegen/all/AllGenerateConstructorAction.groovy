package plugins.codegen.all

import org.jetbrains.annotations.Nullable

import com.intellij.codeInsight.generation.ClassMember
import com.intellij.codeInsight.generation.GenerateConstructorHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiAnonymousClass
import com.intellij.psi.PsiClass

class AllGenerateConstructorAction extends BaseGenerateAction {

  AllGenerateConstructorAction() {
    super(new AllGenerateConstructorHandler())
  }

  @Override
  protected boolean isValidForClass(final PsiClass targetClass) {
    return super.isValidForClass(targetClass) && !(targetClass instanceof PsiAnonymousClass);
  }

  class AllGenerateConstructorHandler extends GenerateConstructorHandler {

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
