package com.mbev08.consoleui.core;

import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;


public class Cell implements AggregatedAttribute {

    public char charValue;
    public Color bg, fg;

    public Cell(char charValue, Color bg, Color fg) {
        this.charValue = charValue;
        this.bg = bg;
        this.fg = fg;
    }

    public void paint() {
        System.out.print( ansi().bg(bg).fg(fg).a(charValue).reset());
    }

    public void clear() {
        update(' ', null, null);
    }

    @Override
    public void update(Object... args) {
        charValue = (char) args[0];
        bg = (Color) args[1];
        fg = (Color) args[2];
    }

}
