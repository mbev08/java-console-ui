package com.mbev08.consoleui.core;

import static org.fusesource.jansi.Ansi.Color;


public class Appearance implements AggregatedAttribute{
    public Color bg, fg;

    public Appearance(Color bg, Color fg) {
        this.bg = bg;
        this.fg = fg;
    }

    @Override
    public void update(Object... args) {
        this.bg = (Color)args[0];
        this.fg = (Color)args[1];
    }
}
