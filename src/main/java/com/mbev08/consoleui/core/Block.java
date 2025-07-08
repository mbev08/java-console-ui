package com.mbev08.consoleui.core;

import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;


/**
 * Aggregated Attribute responsible for storing block attributes.
 * <p>
 * <i>(e.g. bg color, fg color)</i>
 */
public class Block implements AggregatedAttribute {

    public char charValue;
    public Color bg, fg;

    /**
     * Constructor
     *
     * @param charValue
     * @param bg
     * @param fg
     */
    public Block(char charValue, Color bg, Color fg) {
        this.charValue = charValue;
        this.bg = bg;
        this.fg = fg;
    }

    /**
     * prints the {@link Block}'s character with formatting applied.
     */
    public void paint() {
        System.out.print( ansi().bg(bg).fg(fg).a(charValue).reset());
    }

    /**
     * "clears" the {@link Block}'s attributes
     */
    public void clear() {
        update(' ', null, null);
    }

    /**
     * Checks if {@link Block} is empty
     *
     * @return {@link Boolean} indicating if the {@link Block#charValue} is empty.
     */
    public boolean isEmpty() {
        return this.charValue == 0;
    }

    /**
     * Updates block (charValue, bg, fg)
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
