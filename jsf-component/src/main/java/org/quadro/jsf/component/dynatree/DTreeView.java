package org.quadro.jsf.component.dynatree;


import org.quadro.jsf.component.dynatree.event.DTEvent;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.FacesEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    private static final String OPTIMIZED_PACKAGE = "org.quadro.jsf.component.dynatree.";

    public static final String COMPONENT_FAMILY = "org.quadro.jsf.component.dynatree";

    public static final String COMPONENT_TYPË = "org.quadro.jsf.component.dynatree.DTreeView";

    public static final String JAVAX_FACES_PARTIAL_EVENT = "javax.faces.partial.event";

    private static String PARAMETER_MAP_NODEKEY = "nodeKey";

    public enum PropertyKeys {
        children(false),
        options(false),
        widgetVar(false),
        styleClass(false),
        style(false),


        onClick(true), // null: generate focus(true), expand(true), activate(true), select events.
        onDblClick(true), // (No default actions.)
        onKeydown(true), // null: generate keyboard navigation (focus(true), expand(true), activate).
        onKeypress(true), // (No default actions.)
        onFocus(true), // null: set focus to node.
        onBlur(true), // null: remove focus from node.

        // Pre-event handlers onQueryEvent(flag(true), dtnode): return false(true), to stop processing
        onQueryActivate(true), // Callback(flag(true), dtnode) before a node is (de)activated.
        onQuerySelect(true), // Callback(flag(true), dtnode) before a node is (de)selected.
        onQueryExpand(true), // Callback(flag(true), dtnode) before a node is expanded/collpsed.

        // High level event handlers
        onPostInit(true), // Callback(isReloading(true), isError) when tree was (re)loaded.
        onActivate(true), // Callback(dtnode) when a node is activated.
        onDeactivate(true), // Callback(dtnode) when a node is deactivated.
        onSelect(true), // Callback(flag(true), dtnode) when a node is (de)selected.
        onExpand(true), // Callback(flag(true), dtnode) when a node is expanded/collapsed.
        onLazyRead(true), // Callback(dtnode) when a lazy node is expanded for the first time.
        onCustomRender(true), // Callback(dtnode) before a node is rendered. Return a HTML string to override.
        onCreate(true), // Callback(dtnode(true), nodeSpan) after a node was rendered for the first time.
        onRender(true), // Callback(dtnode(true), nodeSpan) after a node was rendered.
        postProcess(true) // Callback(data(true), dataType) before an Ajax result is passed to dynatree.
        ;

        private boolean event;

        PropertyKeys(boolean event) {
            this.event = event;
        }

        public boolean isEvent() {
            return event;
        }

        public static List<PropertyKeys> events() {
            List<PropertyKeys> eventKeys = new ArrayList<PropertyKeys>();
            for (PropertyKeys key : PropertyKeys.values()) {
                if (key.isEvent()) {
                    eventKeys.add(key);
                }
            }
            return eventKeys;
        }

        public static List<String> eventsName() {
            List<String> eventKeys = new ArrayList<String>();
            for (PropertyKeys key : PropertyKeys.events()) {
                eventKeys.add(key.name());
            }
            return eventKeys;
        }
    }


    public DTreeView() {
        setRendererType(DTreeRenderer.RENDER_TYPE);
    }


    public List getChildren() {
        return (List) getAttribute(PropertyKeys.children);
    }

    public void setChildren(List child) {
        setAttribute(PropertyKeys.children, child);
    }

    public Object getOptions() {
        return (Object) getAttribute(PropertyKeys.options);
    }

    public void setOptions(Object option) {
        setAttribute(PropertyKeys.options, option);
    }

    public String getWidgetVar() {
        return (String) getAttribute(PropertyKeys.widgetVar);
    }

    public void setWidgetVar(String widgetVar) {
        setAttribute(PropertyKeys.style, widgetVar);
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



    //***************************************************************************************
    public String getOnClick() {
    	return (String) getAttribute(PropertyKeys.onClick);
    }
    public void setOnClick(String onClick) {
    	setAttribute(PropertyKeys.onClick, onClick);
    }

    public String getOnDblClick() {
    	return (String) getAttribute(PropertyKeys.onDblClick);
    }
    public void setOnDblClick(String onDblClick) {
    	setAttribute(PropertyKeys.onDblClick, onDblClick);
    }

    public String getOnKeydown() {
    	return (String) getAttribute(PropertyKeys.onKeydown);
    }
    public void setOnKeydown(String onKeydown) {
    	setAttribute(PropertyKeys.onKeydown, onKeydown);
    }

    public String getOnKeypress() {
    	return (String) getAttribute(PropertyKeys.onKeypress);
    }
    public void setOnKeypress(String onKeypress) {
    	setAttribute(PropertyKeys.onKeypress, onKeypress);
    }

    public String getOnFocus() {
    	return (String) getAttribute(PropertyKeys.onFocus);
    }
    public void setOnFocus(String onFocus) {
    	setAttribute(PropertyKeys.onFocus, onFocus);
    }

    public String getOnBlur() {
    	return (String) getAttribute(PropertyKeys.onBlur);
    }
    public void setOnBlur(String onBlur) {
    	setAttribute(PropertyKeys.onBlur, onBlur);
    }

    public String getOnQueryActivate() {
    	return (String) getAttribute(PropertyKeys.onQueryActivate);
    }
    public void setOnQueryActivate(String onQueryActivate) {
    	setAttribute(PropertyKeys.onQueryActivate, onQueryActivate);
    }

    public String getOnQuerySelect() {
    	return (String) getAttribute(PropertyKeys.onQuerySelect);
    }
    public void setOnQuerySelect(String onQuerySelect) {
    	setAttribute(PropertyKeys.onQuerySelect, onQuerySelect);
    }

    public String getOnQueryExpand() {
    	return (String) getAttribute(PropertyKeys.onQueryExpand);
    }
    public void setOnQueryExpand(String onQueryExpand) {
    	setAttribute(PropertyKeys.onQueryExpand, onQueryExpand);
    }

    public String getOnPostInit() {
    	return (String) getAttribute(PropertyKeys.onPostInit);
    }
    public void setOnPostInit(String onPostInit) {
    	setAttribute(PropertyKeys.onPostInit, onPostInit);
    }

    public String getOnActivate() {
    	return (String) getAttribute(PropertyKeys.onActivate);
    }
    public void setOnActivate(String onActivate) {
    	setAttribute(PropertyKeys.onActivate, onActivate);
    }

    public String getOnDeactivate() {
    	return (String) getAttribute(PropertyKeys.onDeactivate);
    }
    public void setOnDeactivate(String onDeactivate) {
    	setAttribute(PropertyKeys.onDeactivate, onDeactivate);
    }

    public String getOnSelect() {
    	return (String) getAttribute(PropertyKeys.onSelect);
    }
    public void setOnSelect(String onSelect) {
    	setAttribute(PropertyKeys.onSelect, onSelect);
    }

    public String getOnExpand() {
    	return (String) getAttribute(PropertyKeys.onExpand);
    }
    public void setOnExpand(String onExpand) {
    	setAttribute(PropertyKeys.onExpand, onExpand);
    }

    public String getOnLazyRead() {
    	return (String) getAttribute(PropertyKeys.onLazyRead);
    }
    public void setOnLazyRead(String onLazyRead) {
    	setAttribute(PropertyKeys.onLazyRead, onLazyRead);
    }

    public String getOnCustomRender() {
    	return (String) getAttribute(PropertyKeys.onCustomRender);
    }
    public void setOnCustomRender(String onCustomRender) {
    	setAttribute(PropertyKeys.onCustomRender, onCustomRender);
    }

    public String getOnCreate() {
    	return (String) getAttribute(PropertyKeys.onCreate);
    }
    public void setOnCreate(String onCreate) {
    	setAttribute(PropertyKeys.onCreate, onCreate);
    }

    public String getOnRender() {
    	return (String) getAttribute(PropertyKeys.onRender);
    }
    public void setOnRender(String onRender) {
    	setAttribute(PropertyKeys.onRender, onRender);
    }

    public String getPostProcess() {
    	return (String) getAttribute(PropertyKeys.postProcess);
    }
    public void setPostProcess(String postProcess) {
    	setAttribute(PropertyKeys.postProcess, postProcess);
    }
    //***************************************************************************************












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

        for (PropertyKeys key : PropertyKeys.events()) {

            if (params.get(JAVAX_FACES_PARTIAL_EVENT).equals(key.name())) {
                AjaxBehaviorEvent ajaxBehaviorEvent = (AjaxBehaviorEvent) event;
                event = new DTEvent(event.getComponent(), ajaxBehaviorEvent.getBehavior(), params.get(PARAMETER_MAP_NODEKEY), key);
                break;
            }
        }

        super.queueEvent(event);
    }


    @Override
    public Collection<String> getEventNames() {
        return PropertyKeys.eventsName();
    }


    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
}
