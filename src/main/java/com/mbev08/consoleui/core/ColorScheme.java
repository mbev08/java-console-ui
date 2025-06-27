package com.mbev08.consoleui.core;

import com.mbev08.consoleui.enums.AttributeModifier;

import static org.fusesource.jansi.Ansi.Color;


/**
 * Aggregated Attribute responsible for storing color attributes.
 * <p>
 * <i>(e.g. bg color, fg color)</i>
 */
public class ColorScheme implements AggregatedAttribute{

    public Color bg, fg;
    public AttributeModifier modifier;

    /**
     * Constructs instance with <i><b>NULL</b></i> using default {@link AttributeModifier#AUTO:}
     */
    public ColorScheme() {
        this.bg = null;
        this.fg = null;
        this.modifier = AttributeModifier.AUTO;
    }

    /**
     * Constructs instance using default {@link AttributeModifier#AUTO:}
     * @param bg
     * @param fg
     */
    public ColorScheme(Color bg, Color fg) {
        this();
        update(bg, fg);
    }

    /**
     * Constructs instance using provided (presumably {@link AttributeModifier#MANUAL})
     *
     * @param bg
     * @param fg
     * @param modifier
     */
    public ColorScheme(Color bg, Color fg, AttributeModifier modifier) {
        this(bg, fg);
        this.modifier = modifier;
    }

    /**
     * Updates color scheme (bg color, fg color)
     * @param args [
     *              <ol>
     *                 <li>{@link #bg} ({@link Color})
     *                 <li>{@link #fg} ({@link Color})
     *              </ol>
     *             ]
     *
     * @see Color
     */
    @Override
    public void update(Object... args) {
        this.bg = (Color)args[0];
        this.fg = (Color)args[1];
    }
}
