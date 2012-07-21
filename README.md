Introduction
================

dynatree-for-jsf is a JSF 2 adapter for dynatree jQuery plugin (http://code.google.com/p/dynatree/). dynatree allows to dynamically create html tree view controls using JavaScript.

Why this adapter though Richfaces or Primefaces already implements tree components ? Because dynatree has more features, is more mature, robust and is a prooven component.

Features
================

Here is a typical use of the adapter

```xml
<q:myTree id="myTree" options="{autoCollapse:'true'}" children="#{treeBean.child}" onActivate="alert('activate')" onLazyRead="alert('lazyRead')">
    <f:ajax event="onLazyRead" listener="#{treeBean.lazyRead}" onevent="function(response){cmp.onLazyRead_OnEvent(response,option.node);}"/>
    <f:ajax event="onActivate" listener="#{treeBean.activate}" render="keyNode"/>
</q:myTree>
```

This adapter handles event for all of the dynatree event. All events can be mapped to JSF ajax events. 


Examples
================

This projet includes an example. 


Credits
================
Primefaces, a source of inspiration on how to create an JSF 2 component




