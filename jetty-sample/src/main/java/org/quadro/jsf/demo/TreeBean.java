package org.quadro.jsf.demo;


import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.quadro.jsf.component.dynatree.event.DTEvent;
import org.quadro.jsf.component.dynatree.model.DTreeNode;

import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Random;


public class TreeBean implements Serializable {

    @Getter @Setter
    private String value;

    private Random random = new Random();

    public TreeBean() {
    }



    public void activate(AjaxBehaviorEvent ajaxBehaviorEvent) {
        DTEvent activateEvent = (DTEvent) ajaxBehaviorEvent;

        value = activateEvent.getKey();
        System.out.println("activate");
    }

    public void lazyRead(AjaxBehaviorEvent behaviorEvent) {
        DTEvent lazyReadEvent = (DTEvent) behaviorEvent;

        //randomize
        lazyReadEvent.setNodes(randomize(0,2));

        System.out.println("lazyRead " + lazyReadEvent.getKey());

        value = lazyReadEvent.getKey();
    }

    private List<DTreeNode> randomize(int depth , int maxDepth){
        if (depth >= maxDepth) return null;
        int childCount = random.nextInt()&31;
        List<DTreeNode> childs = Lists.newArrayList();
        for (int childIte = 1 ; childIte <= childCount ; childIte++){
            childs.add(new DTreeNode().children(randomize(depth+1,maxDepth)).isLazy(true).isFolder(true).title(childIte + " " + String.valueOf(System.nanoTime())));
        }
        return childs;
    }



    public String getValue() {
        return value;
    }


    public List<DTreeNode> getChild(){
        return Lists.newArrayList(randomize(0,2));
    }

}


