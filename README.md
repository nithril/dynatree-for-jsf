Introduction
================

dynatree-for-jsf is a JSF 2 adapter for dynatree jQuery plugin (http://code.google.com/p/dynatree/). dynatree allows to dynamically create html tree view controls using JavaScript.

Why this adapter though Richfaces or Primefaces already implements tree components ? Because dynatree has more features, is more mature, robust and is a prooven component.

Features
================

This adapter handles events for all of the dynatree events. All events can be mapped to JSF ajax events. 

Here is a typical use of the adapter

```xml
<q:myTree id="myTree" options="{autoCollapse:'true'}" children="#{treeBean.child}" onActivate="alert('activate')" onLazyRead="alert('lazyRead')">
    <f:ajax event="onLazyRead" listener="#{treeBean.lazyRead}" onevent="function(response){cmp.onLazyRead_OnEvent(response,option.node);}"/>
    <f:ajax event="onActivate" listener="#{treeBean.activate}" render="keyNode"/>
</q:myTree>
```

onLazyRead and onActivate ajax events are binded to methods of the managed bean treeBean. 
- lazyRead populates the tree dynamicaly.
- activate refresh a JSF panel with the selected nod edynkey.

All with Ajax.

This project does not include jquery, nor dynatree. You should include their by yourself.


Example
================
Have a look at the sample.  

```shell
> mvn clean install
> cd jetty-mojarra / cd jetty-myfaces
> mvn jetty:run
```
Browser to http://localhost:8080/tree/console.jsf

Licenses
================
Apache License, Version 2.0 
http://www.apache.org/licenses/LICENSE-2.0

Credits
================
Authors of dynatree, a great component
Authors of Primefaces, a source of inspiration on how to create an JSF 2 component with Ajax behaviour





