package plugins.codegen.all

import org.jetbrains.annotations.Nullable

import com.intellij.codeInsight.generation.ClassMember
import com.intellij.codeInsight.generation.GenerateGetterAndSetterHandler
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class AllGenerateGetterAndSetterHandler extends GenerateGetterAndSetterHandler {

  @Override
  @Nullable
  protected ClassMember[] chooseMembers(
    ClassMember[] members,
    boolean allowEmptySelection,
    boolean copyJavadocCheckbox,
    Project project,
    @Nullable Editor editor
  ) {
    return members;
  }
}
