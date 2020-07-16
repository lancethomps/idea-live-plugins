package plugins.openinvscode

import java.util.concurrent.TimeUnit

import org.jetbrains.annotations.NotNull

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.vfs.VirtualFile

import common.Logs
import liveplugin.PluginUtil

class OpenInVSCodeAction extends AnAction {

  @Override
  void actionPerformed(@NotNull AnActionEvent e) {
    VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
    if (files != null && files.length > 0) {
      String codeScriptPath = getCodeScriptPath()
      Logs.log("Using code script path: ${codeScriptPath}")
      files.each { vfile ->
        File file = new File(vfile.getPresentableUrl())
        if (file.isFile()) {
          ProcessBuilder processBuilder = new ProcessBuilder(codeScriptPath, "-r", file.getCanonicalPath())
          Process process = processBuilder.start()
          if (process.waitFor(30, TimeUnit.SECONDS) && process.exitValue() != 0) {
            Logs.log("Error running code script (${process.exitValue()})\nstderr\n${process.getErrorStream().readLines().join("\n")}\nstdout\n${process.getText()}")
          }
        } else {
          PluginUtil.show("IntelliJ editor file is not an actual file: ${vfile.toString()}")
        }
      }
    }
  }

  String getCodeScriptPath() {
    File userFile = new File(System.getProperty("user.home", System.getenv("HOME")) + "/bin/code")
    if (userFile.exists() && userFile.canExecute()) {
      return userFile.getCanonicalPath()
    }
    return "/usr/local/bin/code"
  }
}
