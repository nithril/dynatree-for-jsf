/* Simple JavaScript Inheritance
 * By John Resig http://ejohn.org/
 * MIT Licensed.
 */
// Inspired by base2 and Prototype
(function(){
    var initializing = false, fnTest = /xyz/.test(function(){xyz;}) ? /\b_super\b/ : /.*/;

    // The base DynaClass implementation (does nothing)
    this.DynaClass = function(){};

    // Create a new DynaClass that inherits from this class
    DynaClass.extend = function(prop) {
        var _super = this.prototype;

        // Instantiate a base class (but only create the instance,
        // don't run the init constructor)
        initializing = true;
        var prototype = new this();
        initializing = false;

        // Copy the properties over onto the new prototype
        for (var name in prop) {
            // Check if we're overwriting an existing function
            prototype[name] = typeof prop[name] == "function" &&
                typeof _super[name] == "function" && fnTest.test(prop[name]) ?
                (function(name, fn){
                    return function() {
                        var tmp = this._super;

                        // Add a new ._super() method that is the same method
                        // but on the super-class
                        this._super = _super[name];

                        // The method only need to be bound temporarily, so we
                        // remove it when we're done executing
                        var ret = fn.apply(this, arguments);
                        this._super = tmp;

                        return ret;
                    };
                })(name, prop[name]) :
                prop[name];
        }

        // The dummy class constructor
        function DynaClass() {
            // All construction is actually done in the init method
            if ( !initializing && this.init )
                this.init.apply(this, arguments);
        }

        // Populate our constructed prototype object
        DynaClass.prototype = prototype;

        // Enforce the constructor to be what we expect
        DynaClass.prototype.constructor = DynaClass;

        // And make this class extendable
        DynaClass.extend = arguments.callee;

        return DynaClass;
    };
})();



var Dynatree4Jsf = DynaClass.extend({

    init:function (htmlId, clientId, options) {
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

