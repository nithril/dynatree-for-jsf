var Dynatree4Jsf = $.inherit({

    __constructor:function (htmlId, clientId, options) {
        this.clientId = clientId;
        this.htmlId = htmlId;
        this.options = options;

        var self = this;

        var gatewayFlagDtnode = "gatewayFlagDtnode";
        var gatewayIsReloadingIsError = "gatewayIsReloadingIsError";
        var gatewayDtnode = "gatewayDtnode";
        var gatewayDtnodeNodeSpan = "gatewayDtnodeNodeSpan";

        this.events = {"onQueryActivate":gatewayFlagDtnode, // Callback(flag, dtnode) before a node is (de)activated.
            "onQuerySelect":gatewayFlagDtnode, // Callback(flag, dtnode) before a node is (de)selected.
            "onQueryExpand":gatewayFlagDtnode, // Callback(flag, dtnode) before a node is expanded/collpsed.
            // High level event handlers
            "onPostInit":gatewayIsReloadingIsError, // Callback(isReloading, isError) when tree was (re)loaded.
            "onActivate":gatewayDtnode, // Callback(dtnode) when a node is activated.
            "onDeactivate":gatewayDtnode, // Callback(dtnode) when a node is deactivated.
            "onSelect":gatewayFlagDtnode, // Callback(flag, dtnode) when a node is (de)selected.
            "onExpand":gatewayFlagDtnode, // Callback(flag, dtnode) when a node is expanded/collapsed.
            "onLazyRead":gatewayDtnode, // Callback(dtnode) when a lazy node is expanded for the first time.
            "onCustomRender":gatewayDtnode, // Callback(dtnode) before a node is rendered. Return a HTML string to override.
            "onCreate":gatewayDtnodeNodeSpan, // Callback(dtnode, nodeSpan) after a node was rendered for the first time.
            "onRender":gatewayDtnodeNodeSpan, // Callback(dtnode, nodeSpan) after a node was rendered.
            "postProcess":gatewayDtnodeNodeSpan};

        var dynaOptions = {};

        if (options.dynaOptions){
            for (var name in options.dynaOptions) {
                dynaOptions[name] = options.dynaOptions[name];
            }
        }

        $.each(this.events , function(event,val){
            dynaOptions[event] = function(a,b,c,d) {
                self[self.events[event]](event,a,b,c,d);
            };
        });

        if (options.children) {
            dynaOptions["children"] = options.children;
        }

        this.instance = $(this.jq(this.htmlId)).dynatree(dynaOptions);
    },



    gatewayFlagDtnode:function(eventName,flag,node){
        if (this.options[eventName]) {
            var event = jQuery.Event(eventName);
            event.data = {flag : flag , nodeKey:node.data.key};
            this.options[eventName](this, event, {flag:flag, node:node});
        }
    },

    gatewayIsReloadingIsError:function(eventName,isReloading, isError){
        if (this.options[eventName]) {
            var event = jQuery.Event(eventName);
            event.data = {isReloading : isReloading , isError:isError};
            this.options[eventName](this, event, {isReloading:isReloading, isError:isError});
        }
    },

    gatewayDtnode:function(eventName,node){
        if (this.options[eventName]) {
            var event = jQuery.Event(eventName);
            event.data = {nodeKey:node.data.key};
            this.options[eventName](this, event, {node:node});
        }
    },

    gatewayDtnodeNodeSpan:function(eventName,node,nodeSpan){
        if (this.options[eventName]) {
            var event = jQuery.Event(eventName);
            event.data = {nodeKey:node.data.key};
            this.options[eventName](this, event, {node:node, nodeSpan:nodeSpan});
        }
    },



    onLazyRead_OnEvent:function (response, node) {
        if (response.status == "success") {
            var jsonNode = $(response.responseXML).find('extension[dynatree="lazyLoad"]');
            node.addChild(eval("(" + jsonNode.text() + ")"), null)
        }
    },

    /* From mojarra.ab
     */
    jsfAjaxRequest:function (source, event, name, execute, render, options) {
        if (!options) {
            options = {};
        }

        if (event.data) {
            for (var i in event.data) {
                options[i] = event.data[i];
            }
        }

        if (name) {
            options["javax.faces.behavior.event"] = name;
        }

        if (execute) {
            options["execute"] = execute;
        }

        if (render) {
            options["render"] = render;
        }

        jsf.ajax.request(source, event, options);
    },

    jq:function (myid) {
        return '#' + myid.replace(/(:|\.)/g, '\\$1');
    }

});

