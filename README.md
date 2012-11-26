Introduction
================

dynatree-for-jsf is a JSF 2 adapter for dynatree jQuery plugin (http://code.google.com/p/dynatree/). dynatree allows to dynamically create html tree view controls using JavaScript.

Why this adapter though Richfaces or Primefaces already implements tree components ? Because dynatree has more features, is more mature, robust and is a prooven component.

Features
================

This adapter handles events for all of the dynatree events. All events can be mapped to JSF ajax events. 

Here is a typical use of the adapter

```xml
<q:dynatree id="myTree" options="{autoCollapse:'true'}" children="#{treeBean.child}" onActivate="alert('activate')" onLazyRead="alert('lazyRead')">
    <f:ajax event="onLazyRead" listener="#{treeBean.lazyRead}" onevent="function(response){cmp.onLazyRead_OnEvent(response,option.node);}"/>
    <f:ajax event="onActivate" listener="#{treeBean.activate}" render="keyNode"/>
</q:dynatree>
```

onLazyRead and onActivate ajax events are binded to methods of the managed bean treeBean. 
- lazyRead populates the tree dynamicaly.
- activate refresh a JSF panel with the selected node key.

All with Ajax.

This project does not include jquery, nor dynatree. You should include their by yourself.


Example
================
I have create an example with an infinite ajax tree

```shell
> mvn clean install -P mojarra / -P myfaces
> cd jetty-sample
> mvn jetty:run -P mojarra / -P myfaces
```
Browse to http://localhost:8080/tree/tree.jsf

![alt Screenshot](https://raw.github.com/nithril/dynatree-for-jsf/develop/sample.png)

Licenses
================
Apache License, Version 2.0 
http://www.apache.org/licenses/LICENSE-2.0

Credits
================
Authors of dynatree, a great component
Authors of Primefaces and Mojarra, a source of inspiration on how to create an JSF 2 component with Ajax behaviour





