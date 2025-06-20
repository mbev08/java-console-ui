package com.mbev08.consoleui.core;

import com.mbev08.consoleui.enums.AttributeModifier;


/**
 * Aggregated Attribute responsible for storing generic sizing
 */
public class Size implements AggregatedAttribute {

    public int width, height;
    public AttributeModifier modifier;

    /**
     * Constructs instance using default {@link AttributeModifier:} (AUTO)
     * <p>
     * <i><b>Unit of Measurement = 1 char</b></i>
     *
     * @param width width of an object (unit of measurement = character)
     * @param height height of an object (unit of measurement = character)
     *
     * @see AttributeModifier
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
        this.modifier = AttributeModifier.AUTO;
    }

    /**
     * Constructs instance using provided {@link AttributeModifier} (presumably MANUAL)
     * <p>
     * <i><b>Unit of Measurement = 1 char</b></i>
     *
     * @param width     width of object
     * @param height    height of object
     * @param modifier  attribute modifier
     *
     * @see AttributeModifier
     */
    public Size(int width, int height, AttributeModifier modifier) {
        this(width, height);
        this.modifier = modifier;
    }

    /**
     * Updates size (width, height)
     * @param args [
     *              <ol>
     *                 <li>{@link #width} ({@link Integer})
     *                 <li>{@link #height} ({@link Integer})
     *              </ol>
     *             ]
     */
    @Override
    public void update(Object... args) {
        width = (Integer) args[0];
        height = (Integer) args[1];
    }
}
