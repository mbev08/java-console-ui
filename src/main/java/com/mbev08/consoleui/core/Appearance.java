package com.mbev08.consoleui.core;

import com.mbev08.consoleui.enums.AttributeModifier;

import static org.fusesource.jansi.Ansi.Color;


public class Appearance implements AggregatedAttribute{
    public Color bg, fg;
    public AttributeModifier modifier;

    public Appearance(Color bg, Color fg) {
        this.bg = bg;
        this.fg = fg;
        this.modifier = AttributeModifier.AUTO;
    }
    public Appearance(Color bg, Color fg, AttributeModifier modifier) {
        this(bg, fg);
        this.modifier = modifier;
    }

    @Override
    public void update(Object... args) {
        this.bg = (Color)args[0];
        this.fg = (Color)args[1];
    }
}
