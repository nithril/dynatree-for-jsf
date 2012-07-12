package org.quadro.jsf.demo;


import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.quadro.jsf.component.dynatree.event.DTEvent;
import org.quadro.jsf.component.dynatree.model.DTreeNode;

import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.List;


public class TreeBean implements Serializable {

    @Getter @Setter
    private String value;



    public TreeBean() {
        System.out.println("okokokokok");

    }



    public void activate(AjaxBehaviorEvent ajaxBehaviorEvent) {
        DTEvent activateEvent = (DTEvent) ajaxBehaviorEvent;

        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("toRender");

        value = activateEvent.getKey();
        System.out.println("activate");
    }

    public void lazyRead(AjaxBehaviorEvent behaviorEvent) {
        DTEvent lazyReadEvent = (DTEvent) behaviorEvent;

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


