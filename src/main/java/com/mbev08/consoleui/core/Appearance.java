package com.mbev08.consoleui.core;

import static org.fusesource.jansi.Ansi.Color;


public class Appearance {
    Color bg;
    Color fg;

    public Appearance(Color bg, Color fg) {
        this.bg = bg;
        this.fg = fg;
    }
}
