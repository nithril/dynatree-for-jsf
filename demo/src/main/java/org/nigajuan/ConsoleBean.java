package org.nigajuan;


import com.google.common.collect.Lists;
import org.nigajuan.dynatree.component.event.DTActivateEvent;
import org.nigajuan.dynatree.component.event.DTLazyReadEvent;
import org.nigajuan.dynatree.model.DTreeNode;

import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * User: malpay
 * Date: 21.08.2011
 * Time: 07:48
 */

@Named
@SessionScoped
public class ConsoleBean implements Serializable {




    private String output;

    private String input;

    private String value;



    private ByteArrayOutputStream out;

    public ConsoleBean() {
        new StringBuffer();
        out = new ByteArrayOutputStream();
        output = out.toString();

        System.out.println("okokokokok");

    }

    public void handleCommand() {

        output = out.toString();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


    public void activate(AjaxBehaviorEvent ajaxBehaviorEvent) {
        DTActivateEvent activateEvent = (DTActivateEvent) ajaxBehaviorEvent;

        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("toRender");

        value = activateEvent.getKey();
        System.out.println("activate");
    }

    public void lazyRead(AjaxBehaviorEvent behaviorEvent) {
        DTLazyReadEvent lazyReadEvent = (DTLazyReadEvent) behaviorEvent;

        /*lazyReadEvent.setData("{title: \"Folder 2\", isFolder: true, key: \"folder2\",\n" +
                "          children: [\n" +
                "            {title: \"Sub-item 2.1\" , isLazy: true,isFolder: true},\n" +
                "            {title: \"Sub-item 2.2\" , isLazy: true,isFolder: true}\n" +
                "          ]\n" +
                "        }");*/

        DTreeNode node = new DTreeNode().title("Folder 2").isFolder(true).key("folder2").children(
                Lists.newArrayList(
                        new DTreeNode().title("Sub-item 2.1").isLazy(true).isFolder(true),
                        new DTreeNode().title("Sub-item 2.1").isLazy(true).isFolder(true)
                )
        );


        lazyReadEvent.setNodes(Lists.newArrayList(node));

        System.out.println("lazyRead " + lazyReadEvent.getKey());

        value = lazyReadEvent.getKey();
    }


    public String getValue() {
        return value;
    }


    public List<DTreeNode> getChild(){
        return Lists.newArrayList(new DTreeNode().title("Folder 2").isFolder(true).key("folder2").children(
                Lists.newArrayList(
                        new DTreeNode().title("Sub-item 2.1").isLazy(true).isFolder(true),
                        new DTreeNode().title("Sub-item 2.1").isLazy(true).isFolder(true)
                )
        ));

    }


}


