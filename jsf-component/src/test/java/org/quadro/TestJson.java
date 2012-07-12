package org.quadro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.quadro.jsf.component.dynatree.DTreeView;
import org.quadro.jsf.component.dynatree.model.DTreeNode;
import org.quadro.jsf.component.dynatree.model.JsFunction;
import org.testng.annotations.Test;

import javax.faces.component.html.HtmlOutcomeTargetLink;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: nigajuan
 * Date: 05/07/12
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public class TestJson {

    @Test
    public void test() throws IOException {

        DTreeNode treeNode = new DTreeNode().key("value").title("a title").href("ref").add("custom" , "value");

        treeNode.add("func" , new JsFunction("function(){}"));


        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectWriter w = mapper.writer().without(SerializationFeature.INDENT_OUTPUT);


        StringWriter writer = new StringWriter();

        w.writeValue(writer , treeNode);

        System.out.println(writer.toString());
    }



    @Test
    public void generateTagLib(){

        String attribute = "<attribute>\n" +
                "\t<description></description>\n" +
                "\t<name>{{event}}</name>\n" +
                "\t<required>false</required>\n" +
                "\t<type>java.lang.Object</type>\n" +
                "</attribute>";

        System.out.println();

        for (DTreeView.PropertyKeys key : DTreeView.PropertyKeys.events()){

            System.out.println(attribute.replaceAll("\\{\\{Event\\}\\}", StringUtils.capitalize(key.name()))
                    .replaceAll("\\{\\{event\\}\\}", key.name()));
            System.out.println();
        }
    }

    @Test
    public void generateGetterSetter(){

        String getter = "public String get{{Event}}() {\n" +
                "\treturn (String) getAttribute(PropertyKeys.{{event}});\n" +
                "}";


        String setter = "public void set{{Event}}(String {{event}}) {\n" +
                "\tsetAttribute(PropertyKeys.{{event}}, {{event}});\n" +
                "}";

        for (DTreeView.PropertyKeys key : DTreeView.PropertyKeys.events()){

            System.out.println(getter.replaceAll("\\{\\{Event\\}\\}", StringUtils.capitalize(key.name()))
                    .replaceAll("\\{\\{event\\}\\}", key.name()));
            System.out.println(setter.replaceAll("\\{\\{Event\\}\\}", StringUtils.capitalize(key.name()))
                    .replaceAll("\\{\\{event\\}\\}", key.name()));
            System.out.println();
        }
    }
}
