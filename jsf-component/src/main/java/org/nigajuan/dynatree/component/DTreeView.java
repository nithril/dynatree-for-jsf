package org.nigajuan.dynatree.component;


import org.nigajuan.dynatree.component.event.DTActivateEvent;
import org.nigajuan.dynatree.component.event.DTLazyReadEvent;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.FacesEvent;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: nigajuan
 * Date: 27/06/12
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */

@FacesComponent(value = DTreeView.COMPONENT_TYPË)
@ResourceDependencies({
        @ResourceDependency(name = "jsf.dynatree.adapter.js", library = "dynatree", target = "head"),
        @ResourceDependency(name = "jsf.js", library = "javax.faces", target = "head")})
public class DTreeView extends UIComponentBase implements ClientBehaviorHolder {
    private static final String OPTIMIZED_PACKAGE = "org.nigajuan.dynatree.";

    public static final String COMPONENT_FAMILY = "org.nigajuan.jsf.tree";

    public static final String COMPONENT_TYPË = "org.nigajuan.dynatree.component.DTreeView";
    public static final String JAVAX_FACES_PARTIAL_EVENT = "javax.faces.partial.event";


    protected enum PropertyKeys {
        activate,
        select,
        lazyRead,
        child,
        styleClass,
        style;
    }


    public DTreeView() {
        setRendererType(DTreeRenderer.RENDER_TYPE);
    }


    public String getActivate() {
        return (String) getAttribute(PropertyKeys.activate);
    }

    public void setActivate(String activate) {
        setAttribute(PropertyKeys.activate, activate);
    }

    public String getLazyRead() {
        return (String) getAttribute(PropertyKeys.lazyRead);
    }

    public void setLazyRead(String lazyRead) {
        setAttribute(PropertyKeys.lazyRead, lazyRead);
    }

    public List getChild() {
        return (List) getAttribute(PropertyKeys.child);
    }

    public void setChild(List child) {
        setAttribute(PropertyKeys.child, child);
    }

    public String getStyle() {
        return (String) getAttribute(PropertyKeys.style);
    }

    public void setStyle(String style) {
        setAttribute(PropertyKeys.style, style);
    }

    public String getStyleClass() {
        return (String) getAttribute(PropertyKeys.styleClass);
    }

    public void setStyleClass(String styleClass) {
        setAttribute(PropertyKeys.styleClass, styleClass);
    }


    public Object getAttribute(PropertyKeys key) {
        return getStateHelper().eval(key, null);
    }

    public void setAttribute(PropertyKeys key, Object value) {
        getStateHelper().put(key, value);
        handleAttribute(key.name(), value);
    }

    public void handleAttribute(String name, Object value) {
        List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
        if (setAttributes == null) {
            String cname = this.getClass().getName();
            if (cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
                setAttributes = new ArrayList<String>(6);
                this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
            }
        }
        if (setAttributes != null) {
            if (value == null) {
                ValueExpression ve = getValueExpression(name);
                if (ve == null) {
                    setAttributes.remove(name);
                } else if (!setAttributes.contains(name)) {
                    setAttributes.add(name);
                }
            }
        }
    }

    @Override
    public void queueEvent(FacesEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        if (params.get(JAVAX_FACES_PARTIAL_EVENT).equals(PropertyKeys.lazyRead.name())) {
            AjaxBehaviorEvent ajaxBehaviorEvent = (AjaxBehaviorEvent) event;


            DTLazyReadEvent lazyReadEvent = new DTLazyReadEvent(event.getComponent(), ajaxBehaviorEvent.getBehavior(),
                    params.get("nodeKey"));

            super.queueEvent(lazyReadEvent);
        } else if (params.get(JAVAX_FACES_PARTIAL_EVENT).equals(PropertyKeys.activate.name())) {
            AjaxBehaviorEvent ajaxBehaviorEvent = (AjaxBehaviorEvent) event;

            DTActivateEvent activateEvent = new DTActivateEvent(event.getComponent(), ajaxBehaviorEvent.getBehavior(),
                    params.get("nodeKey"));
            super.queueEvent(activateEvent);
        } else {
            super.queueEvent(event);
        }
    }


    @Override
    public Collection<String> getEventNames() {
        return Collections.unmodifiableCollection(Arrays.asList(PropertyKeys.activate.name(), PropertyKeys.select.name(), PropertyKeys.lazyRead.name()));
    }


    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
}
