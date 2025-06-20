package com.mbev08.consoleui.core;

/**
 * Aggregated Attribute responsible for storing coordinates
 */
public class Position implements AggregatedAttribute {

    public int x, y, z;

    /**
     * Constructs instance using provided X and Y coordinates.
     * <p>
     * <i><b>Z position defaults to 0. </b></i>
     * <p>
     * <i><b>Unit of Measurement = 1 char</b></i>
     *
     * @param x X position
     * @param y Y position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    /**
     * Constructs instance using all 3 coordinates (x, y, z).
     * <p>
     * <i><b>Unit of Measurement = 1 char</b></i>
     *
     * @param x X position
     * @param y Y position
     * @param z Z position (aka z-index)
     */
    public Position(int x, int y, int z) {
        this(x, y);
        this.z = z;
    }

    /**
     * Updates position (x, y, z)
     * @param args [
     *              <ol>
     *                 <li>{@link #x} ({@link Integer})
     *                 <li>{@link #y} ({@link Integer})
     *                 <li>{@link #z} ({@link Integer})
     *              </ol>
     *             ]
     */
    @Override
    public void update(Object... args) {
        this.x = (Integer) args[0];
        this.y = (Integer) args[1];
        this.z = (Integer) args[2];
    }
}
