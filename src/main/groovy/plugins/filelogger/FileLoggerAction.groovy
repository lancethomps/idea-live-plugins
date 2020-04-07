package plugins.filelogger

import org.jetbrains.annotations.NotNull

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.vfs.VirtualFile

import common.Logs
import liveplugin.PluginUtil

class FileLoggerAction extends AnAction {

  @Override
  void actionPerformed(@NotNull AnActionEvent e) {
    VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
    if (files != null && files.length > 0) {
      files.each { vfile ->
        File file = new File(vfile.getPresentableUrl())
        if (file.isFile()) {
          def infos = [
            type: vfile.getFileType().getName(),
            ext: vfile.getExtension(),
            url: vfile.getPresentableUrl(),
            path: vfile.getPath()
          ]
          def messages = infos.collect { key, val -> "${key.padRight(10)} -> ${val}"}
          Logs.showMessagesInConsole("File Info", messages)
        } else {
          PluginUtil.show("IntelliJ editor file is not an actual file: ${vfile.toString()}")
        }
      }
    }
  }
}
