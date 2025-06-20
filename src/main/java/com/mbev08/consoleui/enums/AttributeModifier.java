package com.mbev08.consoleui.enums;

/**
 * Values used to identify whether an attribute
 * <i>(e.g. highlighted appearance [{@link com.mbev08.consoleui.core.Appearance}])</i>
 * can be influenced by another attribute <i>(e.g. default appearance)</i>
 */
public enum AttributeModifier {
    /**
     * Allow other attributes to influence this attribute's values.
     */
    AUTO,

    /**
     * Manually determined attribute's values.
     */
    MANUAL
}
