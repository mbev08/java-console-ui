package com.mbev08.consoleui.core;

public class Position implements AggregatedAttribute {
    public int x, y, z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void update(Object... args) {
        this.x = (Integer) args[0];
        this.y = (Integer) args[1];
        this.z = (Integer) args[2];
    }
}
