package org.nigajuan.dynatree.context;


import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextFactory;

/**
 * Created with IntelliJ IDEA.
 * User: nigajuan
 * Date: 02/07/12
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */
public class DTPartialViewContextFactory extends PartialViewContextFactory {


    private PartialViewContextFactory parent;

    public DTPartialViewContextFactory(PartialViewContextFactory parent) {
        this.parent = parent;
    }

    @Override
     public PartialViewContextFactory getWrapped() {
         return this.parent;
     }

    @Override
    public PartialViewContext getPartialViewContext(FacesContext fc) {
        PartialViewContext parentContext = getWrapped().getPartialViewContext(fc);

        return new DTPartialViewContext(parentContext);
    }
}
