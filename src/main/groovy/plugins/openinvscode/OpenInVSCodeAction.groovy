package plugins.openinvscode

import org.jetbrains.annotations.NotNull

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.vfs.VirtualFile

import liveplugin.PluginUtil

class OpenInVSCodeAction extends AnAction {

  @Override
  void actionPerformed(@NotNull AnActionEvent e) {
    VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
    if (files != null && files.length > 0) {
      files.each { vfile ->
        File file = new File(vfile.getPresentableUrl())
        if (file.isFile()) {
          ProcessBuilder processBuilder = new ProcessBuilder("/usr/local/bin/code", "-r", file.getCanonicalPath())
          processBuilder.start()
        } else {
          PluginUtil.show("IntelliJ editor file is not an actual file: ${vfile.toString()}")
        }
      }
    }
  }
}
