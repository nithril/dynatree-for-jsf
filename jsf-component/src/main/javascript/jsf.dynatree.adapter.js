var Dynatree4Jsf = $.inherit({

    __constructor:function (htmlId, clientId, options) {
        this.clientId = clientId;
        this.htmlId = htmlId;
        this.options = options;

        var self = this;

        var dynatreeOpts = {
            title:"Programming Sample",
            onActivate:function (node) {
                self.activate(node);
            },
            onLazyRead:function (node) {
                self.onLazyRead(node);
            }
        };
        if (options.children) {
            dynatreeOpts["children"] = options.children;
        }

        this.instance = $(this.jq(this.htmlId)).dynatree(dynatreeOpts);
    },


    activate:function (node) {
        var self = this;
        var event = jQuery.Event("activate");
        event.data = {nodeKey:node.data.key};

        if (this.options.activate) {
            this.options.activate(this, node, event);
        }
    },

    onLazyRead:function (node) {
        var self = this;
        var event = jQuery.Event("lazyRead");
        event.data = {nodeKey:node.data.key};

        if (this.options.lazyRead) {
            this.options.lazyRead(this, node, event);
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
    jsfAjaxRequest:function(source, event, name, execute, render, options) {
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

