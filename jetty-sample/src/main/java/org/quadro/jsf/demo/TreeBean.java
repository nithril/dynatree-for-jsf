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

    private String[] words = "Praesent tellus elit, tincidunt ac feugiat nec, rhoncus sed nibh. Duis consequat, odio nec volutpat lacinia, velit mi pharetra tortor, a faucibus orci tellus nec nunc. Sed in lectus dolor. Duis interdum, tortor et egestas tincidunt, dui turpis convallis massa, eu interdum arcu turpis id orci. Fusce facilisis porta justo a tempus. Quisque faucibus mauris a lacus luctus interdum. Donec ac porttitor arcu. Sed mi felis, blandit nec imperdiet vitae, bibendum id nisl. Maecenas eget sem ipsum, sit amet pellentesque orci. Sed egestas nunc in ligula accumsan pulvinar. Ut tempus dapibus eros eget elementum. Proin dui erat, lacinia fringilla consectetur a, elementum sed lacus. Integer sit amet ligula augue, sed venenatis est. Aliquam tincidunt egestas justo in pellentesque. Vivamus mauris turpis, sagittis et commodo a, ultrices ut diam. Sed dignissim tempor est, ac viverra neque faucibus non. ".toLowerCase().replaceAll("[^\\w]+", " ").split("\\s");

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
            childs.add(new DTreeNode().children(randomize(depth+1,maxDepth)).isLazy(true).isFolder(true).title(childIte + " " + words[Math.abs(random.nextInt()%words.length)]));
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


