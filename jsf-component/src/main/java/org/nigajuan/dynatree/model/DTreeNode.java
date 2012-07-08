package org.nigajuan.dynatree.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.FluentSetter;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nigajuan
 * Date: 05/07/12
 * Time: 20:36
 * To change this template use File | Settings | File Templates.
 */

public class DTreeNode {

    private Map<String, Object> properties = new HashMap<String, Object>();

    @Getter @FluentSetter
    private String title = null; // (required) Displayed name of the node (html is allowed here)
    @Getter @FluentSetter
    private String key = null; // May be used with activate(), select(), find(), ...
    @Getter @FluentSetter
    private Boolean isFolder = null; // Use a folder icon. Also the node is expandable but not selectable.
    @Getter @FluentSetter
    private Boolean isLazy = null; // Call onLazyRead(), when the node is expanded for the first time to allow for delayed creation of children.
    @Getter @FluentSetter
    private String tooltip = null; // Show this popup text.
    @Getter @FluentSetter
    private String href = null; // Added to the generated <a> tag.
    @Getter @FluentSetter
    private String icon = null; // Use a custom image (filename relative to tree.options.imagePath). 'null' for default icon, 'false' for no icon.
    @Getter @FluentSetter
    private String addClass = null; // Class name added to the node's span tag.
    @Getter @FluentSetter
    private Boolean noLink = null; // Use <span> instead of <a> tag for this node
    @Getter @FluentSetter
    private Boolean activate = null; // Initial active status.
    @Getter @FluentSetter
    private Boolean focus = null; // Initial focused status.
    @Getter @FluentSetter
    private Boolean expand = null; // Initial expanded status.
    @Getter @FluentSetter
    private Boolean select = null; // Initial selected status.
    @Getter @FluentSetter
    private Boolean hideCheckbox = null; // Suppress checkbox display for this node.
    @Getter @FluentSetter
    private Boolean unselectable = null; // Prevent selection.
    // The following attributes are only valid if passed to some functions:
    @Getter @FluentSetter
    private List<DTreeNode> children = null; // Array of child nodes.

    @JsonAnySetter
    public DTreeNode add(String key, Object value) { // name does not matter
        properties.put(key, value);
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> properties() { // note: for 1.6.0 MUST use non-getter name; otherwise doesn't matter
        return properties;
    }

}
