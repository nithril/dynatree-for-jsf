package org.quadro.jsf.component.dynatree;

import org.quadro.jsf.component.dynatree.model.JsFunction;
import org.quadro.jsf.component.utils.QUtils;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import java.io.IOException;
import java.util.*;

@FacesRenderer(componentFamily = DTreeView.COMPONENT_FAMILY, rendererType = DTreeRenderer.RENDER_TYPE)
public class DTreeRenderer extends Renderer {

    public static final String RENDER_TYPE = "org.quadro.jsf.component.dynatree.DTreeRenderer";

    public DTreeRenderer() {
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        decodeBehaviors(context, component);
    }


    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        DTreeView comp = (DTreeView) component;

        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", null);
        writer.writeAttribute("id", comp.getClientId(), null);
        writer.writeAttribute("name", comp.getClientId(), null);
        if (comp.getStyle() != null) {
            writer.writeAttribute("style", comp.getStyle(), null);
        }
        if (comp.getStyleClass() != null) {
            writer.writeAttribute("class", comp.getStyleClass(), null);
        }
    }


    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {

        DTreeView myTreeView = (DTreeView) component;



        String widgetVar = (String) myTreeView.getAttribute(DTreeView.PropertyKeys.widgetVar);
        String children = convertObjectToScript(myTreeView.getAttribute(DTreeView.PropertyKeys.children));
        String dynaOptions = convertObjectToScript(myTreeView.getAttribute(DTreeView.PropertyKeys.options));

        StringBuilder scriptBuilder = new StringBuilder();

        //widgetVar
        if (widgetVar != null && !widgetVar.isEmpty()) {
            scriptBuilder.append(widgetVar).append("=");
        }
        //instanciate component
        scriptBuilder.append("new Dynatree4Jsf('")
                .append(component.getClientId()).append("','")
                .append(component.getClientId()).append("',")
                .append("{");

        //options
        boolean hasOption = false;
        if (children != null && !children.isEmpty()){
            scriptBuilder.append("children:").append(children);
            hasOption = true;
        }
        if (dynaOptions != null && !dynaOptions.isEmpty()){
            if (hasOption) {
                scriptBuilder.append(",");
            }
            scriptBuilder.append("dynaOptions:").append(dynaOptions);
            hasOption = true;
        }

        for (DTreeView.PropertyKeys event : DTreeView.PropertyKeys.events()){
            String script = convertBehaviourToScript(event, myTreeView, context);
            if (script != null && !script.isEmpty()){
                if (hasOption) {
                    scriptBuilder.append(",");
                }
                scriptBuilder.append(event.name()).append(":function(cmp,event,option){").append(script).append("}");
                hasOption = true;
            }
        }

        scriptBuilder.append("});");




        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");

        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", null);

        writer.writeText(scriptBuilder.toString(), null);

        writer.endElement("script");
    }


    private String convertObjectToScript(Object value) throws IOException {
        String script = null;

        if (value != null) {
            if (value instanceof Collection) {
                script = QUtils.getObjectWriter().writeValueAsString(value);
            } else {
                script = value.toString();
            }
        }
        return script;
    }

    private String convertBehaviourToScript(DTreeView.PropertyKeys keys, DTreeView component, FacesContext context) {

        StringBuilder script = new StringBuilder();

        if (component.getAttribute(keys) != null) {
            script.append(component.getAttribute(keys)).append(";");
        }

        String behavior = getBehaviors(context, component, keys.name());
        if (behavior != null) {
            script.append(behavior).append(";");
        }

        return script.toString();
    }

    protected String getBehaviors(FacesContext context, ClientBehaviorHolder cbh, String eventName) {
        List<ClientBehavior> behaviors = cbh.getClientBehaviors().get(eventName);

        StringBuilder sb = new StringBuilder();


        if (behaviors != null && !behaviors.isEmpty()) {
            UIComponent component = (UIComponent) cbh;
            List<ClientBehaviorContext.Parameter> params = new ArrayList<ClientBehaviorContext.Parameter>();

            for (Iterator<ClientBehavior> behaviorIter = behaviors.iterator(); behaviorIter.hasNext(); ) {
                ClientBehavior behavior = behaviorIter.next();
                ClientBehaviorContext cbc = ClientBehaviorContext.createClientBehaviorContext(context, component, eventName, component.getClientId(), params);

                String script = buildScriptFromAjaxBehavior(cbc, (AjaxBehavior) behavior);

                if (script != null) {
                    sb.append(script).append(";");
                }
            }
        }
        return sb.length() == 0 ? null : sb.toString();
    }


    protected void decodeBehaviors(FacesContext context, UIComponent component) {

        if (!(component instanceof ClientBehaviorHolder)) {
            return;
        }

        Map<String, List<ClientBehavior>> behaviors = ((ClientBehaviorHolder) component).getClientBehaviors();
        if (behaviors.isEmpty()) {
            return;
        }

        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String behaviorEvent = params.get("javax.faces.behavior.event");

        if (behaviorEvent == null) {
            behaviorEvent = params.get("javax.faces.partial.event");
        }

        if (behaviorEvent != null) {
            List<ClientBehavior> behaviorsForEvent = behaviors.get(behaviorEvent);

            if (behaviorsForEvent != null && !behaviorsForEvent.isEmpty()) {
                String behaviorSource = params.get("javax.faces.source");
                String clientId = component.getClientId();

                if (behaviorSource != null && clientId.startsWith(behaviorSource)) {
                    for (ClientBehavior behavior : behaviorsForEvent) {
                        behavior.decode(context, component);
                    }
                }
            }
        }
    }


    private static String buildScriptFromAjaxBehavior(ClientBehaviorContext behaviorContext, AjaxBehavior ajaxBehavior) {


        UIComponent component = behaviorContext.getComponent();
        String eventName = behaviorContext.getEventName();

        StringBuilder scriptBuilder = new StringBuilder();

        Collection<ClientBehaviorContext.Parameter> params = behaviorContext.getParameters();


        //JS function
        scriptBuilder.append("cmp.jsfAjaxRequest(");
        //source id
        scriptBuilder.append("'").append(behaviorContext.getSourceId()).append("'");
        //event and event name
        scriptBuilder.append(",event,'").append(eventName).append("',");
        //execute ids
        String executeIds = resolveAndJoinIds(component, ajaxBehavior.getExecute());
        if (executeIds != null) {
            scriptBuilder.append("'").append(executeIds).append("',");
        } else {
            scriptBuilder.append("null").append(",");
        }
        //render ids
        String renderIds = resolveAndJoinIds(component, ajaxBehavior.getRender());
        if (renderIds != null) {
            scriptBuilder.append("'").append(renderIds).append("',");
        } else {
            scriptBuilder.append("null").append(",");
        }


        Map<String, Object> options = new HashMap<String, Object>();

        String onevent = ajaxBehavior.getOnevent();
        if (onevent != null) {
            options.put("onevent", new JsFunction(onevent));
        }

        String onerror = ajaxBehavior.getOnerror();
        if (onerror != null) {
            options.put("onerror", new JsFunction(onerror));
        }

        if (!params.isEmpty()) {
            for (ClientBehaviorContext.Parameter param : params) {
                options.put(param.getName(), param.getValue());
            }
        }

        try {
            scriptBuilder.append(QUtils.getObjectWriter().writeValueAsString(options));
        } catch (IOException e) {
            scriptBuilder.append("{}");
        }

        scriptBuilder.append(")");

        return scriptBuilder.toString();
    }


    private static final List<String> RESERVED_IDS = Arrays.asList("@all", "@none", "@form", "@this");

    private static String resolveAndJoinIds(UIComponent component, Collection<String> ids) {

        if ((ids == null) || ids.isEmpty()) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        for (String id : ids) {
            if (builder.length() > 0) {
                builder.append(" ");
            }

            if (RESERVED_IDS.contains(id)) {
                builder.append(id);
            } else {
                UIComponent foundComponent = component.findComponent(id);
                if (foundComponent != null) {
                    builder.append(foundComponent.getClientId());
                }
            }
        }

        return builder.toString();
    }
}

