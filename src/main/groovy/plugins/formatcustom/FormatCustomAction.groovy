package plugins.formatcustom

import com.intellij.application.options.codeStyle.arrangement.action.RearrangeCodeAction
import com.intellij.codeInsight.actions.OptimizeImportsAction
import com.intellij.codeInsight.actions.ReformatCodeAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.vfs.VirtualFile

class FormatCustomAction extends AnAction {

  static final List<String> OPTIMIZE_IMPORTS_FILE_TYPES = Arrays.asList("py")
  boolean optimizeImports = false
  boolean checkOptimizeImportsFileType = false

  @Override
  void actionPerformed(AnActionEvent event) {
    new ReformatCodeAction().actionPerformed(event)
    new RearrangeCodeAction().actionPerformed(event)

    if (shouldRunOptimizeImports(event)) {
      new OptimizeImportsAction().actionPerformed(event)
    }

  }

  boolean shouldRunOptimizeImports(AnActionEvent event) {
    if (!optimizeImports) {
      return false
    }

    if (!checkOptimizeImportsFileType) {
      return true
    }

    final VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(event.getDataContext())
    if (files == null || files.length == 0) {
      return true
    }

    String ext = files[0].getFileType().getDefaultExtension()
    return OPTIMIZE_IMPORTS_FILE_TYPES.any {it -> it.equalsIgnoreCase(ext) }
  }
}
