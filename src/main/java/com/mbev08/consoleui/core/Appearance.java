package com.mbev08.consoleui.core;

import com.mbev08.consoleui.enums.AttributeModifier;

import static org.fusesource.jansi.Ansi.Color;


/**
 * Aggregated Attribute responsible for storing appearance attributes.
 * <p>
 * <i>(e.g. bg color, fg color)</i>
 */
public class Appearance implements AggregatedAttribute{
    public Color bg, fg;
    public AttributeModifier modifier;

    /**
     * Constructs instance using default {@link AttributeModifier:} (AUTO)
     * @param bg
     * @param fg
     */
    public Appearance(Color bg, Color fg) {
        this.bg = bg;
        this.fg = fg;
        this.modifier = AttributeModifier.AUTO;
    }

    /**
     * Constructs instance using provided Attribute Modifier (presumably MANUAL)
     * @param bg
     * @param fg
     * @param modifier
     */
    public Appearance(Color bg, Color fg, AttributeModifier modifier) {
        this(bg, fg);
        this.modifier = modifier;
    }

    /**
     * Updates appearance (bg color, fg color)
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
