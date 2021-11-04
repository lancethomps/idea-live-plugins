package plugins.collapsecustom

import com.intellij.codeInsight.folding.impl.actions.CollapseAllRegionsAction
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class CollapseAllThenExpandAction extends AnAction {

  protected final int expandLevel;

  CollapseAllThenExpandAction(int expandLevel) {
    this.expandLevel = expandLevel;
  }

  String getActionText() {
    return "Collapse Custom ${expandLevel}"
  }

  @Override
  void actionPerformed(AnActionEvent event) {
    new CollapseAllRegionsAction().actionPerformed(event)
    getExpandAction().actionPerformed(event)
  }

  AnAction getExpandAction() {
    ActionManager.getInstance().getAction("ExpandAllToLevel${expandLevel}")
  }

}
