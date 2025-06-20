package com.mbev08.consoleui.core;

public class Size implements AggregatedAttribute {
    public int width, height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(Object... args) {
        width = (Integer) args[0];
        height = (Integer) args[1];
    }
}
