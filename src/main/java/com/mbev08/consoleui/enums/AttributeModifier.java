package com.mbev08.consoleui.enums;

import com.mbev08.consoleui.core.ColorScheme;

/**
 * Values used to identify whether an attribute
 * <i>(e.g. highlighted {@link ColorScheme})</i>
 * can be influenced by another attribute <i>(e.g. default {@link ColorScheme})</i>
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
