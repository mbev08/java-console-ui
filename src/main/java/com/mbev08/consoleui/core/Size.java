package com.mbev08.consoleui.core;

import com.mbev08.consoleui.enums.AttributeModifier;


public class Size implements AggregatedAttribute {
    public int width, height;
    public AttributeModifier modifier;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
        this.modifier = AttributeModifier.AUTO;
    }
    public Size(int width, int height, AttributeModifier modifier) {
        this.width = width;
        this.height = height;
        this.modifier = modifier;
    }

    @Override
    public void update(Object... args) {
        width = (Integer) args[0];
        height = (Integer) args[1];
    }
}
