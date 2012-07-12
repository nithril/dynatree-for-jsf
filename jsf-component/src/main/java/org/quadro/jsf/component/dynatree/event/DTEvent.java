package org.quadro.jsf.component.dynatree.event;

import lombok.Getter;
import lombok.Setter;
import org.quadro.jsf.component.context.QRequestContext;
import org.quadro.jsf.component.dynatree.DTreeView;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import javax.faces.event.AjaxBehaviorEvent;

/**
 * Created with IntelliJ IDEA.
 * User: nigajuan
 * Date: 03/07/12
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class DTEvent extends AjaxBehaviorEvent {

    @Getter
    private final String key;

    @Getter
    private DTreeView.PropertyKeys propertyKey;

    public DTEvent(UIComponent component, Behavior behavior, String key, DTreeView.PropertyKeys propertyKey) {
        super(component, behavior);
        this.key = key;
        this.propertyKey = propertyKey;
    }

    public void setNodes(Object nodes){
        QRequestContext.getCurrentInstance().setNodes(nodes);
    }
}
