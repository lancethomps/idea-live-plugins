package plugins.startup

import org.jetbrains.kotlin.idea.debugger.KotlinDebuggerSettings

import com.intellij.debugger.settings.DebuggerSettings
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.ui.classFilter.ClassFilter
import com.intellij.xdebugger.XDebuggerUtil

import common.BasePluginAction
import common.Logs

class RemoveExtraDebuggerKotlinFiltersAction extends BasePluginAction {

  @Override
  void actionPerformed(AnActionEvent event) {
    run()
  }

  static void logCurrent() {
    KotlinDebuggerSettings kotlinDebuggerSettings = XDebuggerUtil.getInstance().getDebuggerSettings(KotlinDebuggerSettings.class)
    Logs.log("KotlinDebuggerSettings.DEBUG_IS_FILTER_FOR_STDLIB_ALREADY_ADDED: ${kotlinDebuggerSettings.DEBUG_IS_FILTER_FOR_STDLIB_ALREADY_ADDED}")
  }

  static void logExistingKotlinFiltersCount(boolean show = false) {
    def kotlinFiltersCount = 0
    DebuggerSettings debuggerSettings = DebuggerSettings.getInstance()
    if (debuggerSettings.steppingFilters != null) {
      for (def filter in debuggerSettings.steppingFilters) {
        if (filter.pattern == "kotlin.*") {
          kotlinFiltersCount++
        }
      }
    }
    Logs.logOrShow("Existing debugger kotlin filters count: ${kotlinFiltersCount}", show)
  }

  static void run() {
    logCurrent()
    KotlinDebuggerSettings kotlinDebuggerSettings = XDebuggerUtil.getInstance().getDebuggerSettings(KotlinDebuggerSettings.class)
    if (!kotlinDebuggerSettings.DEBUG_IS_FILTER_FOR_STDLIB_ALREADY_ADDED) {
      Logs.log("Setting KotlinDebuggerSettings.DEBUG_IS_FILTER_FOR_STDLIB_ALREADY_ADDED to true.")
      kotlinDebuggerSettings.DEBUG_IS_FILTER_FOR_STDLIB_ALREADY_ADDED = true
      Logs.log("KotlinDebuggerSettings.DEBUG_IS_FILTER_FOR_STDLIB_ALREADY_ADDED: ${kotlinDebuggerSettings.DEBUG_IS_FILTER_FOR_STDLIB_ALREADY_ADDED}")
    }

    DebuggerSettings debuggerSettings = DebuggerSettings.getInstance()
    def kotlinFiltersCount = 0
    List<ClassFilter> filters = []
    if (debuggerSettings.steppingFilters != null) {
      def hasKotlin = false
      for (def filter in debuggerSettings.steppingFilters) {
        if (filter.pattern == "kotlin.*") {
          kotlinFiltersCount++
          if (hasKotlin) {
            continue
          }
          hasKotlin = true
        }
        filters.add(filter)
      }
    }

    if (kotlinFiltersCount >= 2) {
      Logs.log("Multiple debugger kotlin filters found (${kotlinFiltersCount}): removing extra filters.")
      ClassFilter[] filtersArray = filters.toArray(new ClassFilter[0])
      debuggerSettings.setSteppingFilters(filtersArray)
      logExistingKotlinFiltersCount(false)
    } else {
      Logs.log("Less than 2 debugger kotlin filters found (${kotlinFiltersCount}), not modifying debugger settings.")
    }

    //KotlinDebuggerSettings.getInstance().isFilterForStdlibAlreadyAdded = true
  }
}
