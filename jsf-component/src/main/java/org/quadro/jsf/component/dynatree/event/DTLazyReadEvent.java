package org.quadro.jsf.component.dynatree.event;

import lombok.Getter;
import org.quadro.jsf.component.context.QRequestContext;
import org.quadro.jsf.component.dynatree.model.DTreeNode;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nigajuan
 * Date: 03/07/12
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class DTLazyReadEvent extends AjaxBehaviorEvent {

    @Getter
    private final String key;


    public DTLazyReadEvent(UIComponent component, Behavior behavior, String key) {
        super(component, behavior);
        this.key = key;
    }

    public void setNodes(List<DTreeNode> treeNodes) {
        QRequestContext.getCurrentInstance().setNodes(treeNodes);
    }
}
