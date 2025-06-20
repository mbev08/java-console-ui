package com.mbev08.consoleui.core;

import com.mbev08.consoleui.enums.AttributeModifier;

import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;


/**
 * Aggregated Attribute responsible for storing cell attributes.
 * <p>
 * <i>(e.g. bg color, fg color)</i>
 */
public class Cell implements AggregatedAttribute {
    // TODO: Rename Cell to Block

    public char charValue;
    public Color bg, fg;

    /**
     * Constructor
     *
     * @param charValue
     * @param bg
     * @param fg
     */
    public Cell(char charValue, Color bg, Color fg) {
        this.charValue = charValue;
        this.bg = bg;
        this.fg = fg;
    }

    /**
     * prints the {@link Cell}'s character with formatting applied.
     */
    public void paint() {
        System.out.print( ansi().bg(bg).fg(fg).a(charValue).reset());
    }

    /**
     * "clears" the {@link Cell}'s attributes
     */
    public void clear() {
        update(' ', null, null);
    }

    /**
     * Updates cell (charValue, bg, fg)
     * @param args [
     *              <ol>
     *                 <li>{@link #charValue} ({@link Character})
     *                 <li>{@link #bg} ({@link Color})
     *                 <li>{@link #fg} ({@link Color})
     *              </ol>
     *             ]
     *
     * @see Color
     */
    @Override
    public void update(Object... args) {
        charValue = (char) args[0];
        bg = (Color) args[1];
        fg = (Color) args[2];
    }

}
