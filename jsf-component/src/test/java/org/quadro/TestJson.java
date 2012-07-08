package org.quadro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.quadro.jsf.component.dynatree.model.DTreeNode;
import org.quadro.jsf.component.dynatree.model.JsFunction;
import org.testng.annotations.Test;

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

        DTreeNode treeNode = new DTreeNode().key("value").title("a title").href("ref").add("toto" , "titi");

        treeNode.add("func" , new JsFunction("function"));


        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectWriter w = mapper.writer().without(SerializationFeature.INDENT_OUTPUT);


        StringWriter writer = new StringWriter();

        w.writeValue(writer , treeNode);

        System.out.println(writer.toString());

    }
}
