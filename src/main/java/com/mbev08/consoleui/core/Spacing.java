package com.mbev08.consoleui.core;

public class Spacing implements AggregatedAttribute {
    public int top, bottom, left, right;

    public Spacing(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    @Override
    public void update(Object... args) {
        this.top = (Integer) args[0];
        this.bottom = (Integer) args[1];
        this.left = (Integer) args[2];
        this.right = (Integer) args[3];
    }
}
