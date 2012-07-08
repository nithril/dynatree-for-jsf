package org.nigajuan.dynatree.component.event;

import lombok.Getter;

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
public class DTActivateEvent extends AjaxBehaviorEvent {

    @Getter
    private final String key;


    public DTActivateEvent(UIComponent component, Behavior behavior, String key) {
        super(component, behavior);
        this.key = key;
    }
}
