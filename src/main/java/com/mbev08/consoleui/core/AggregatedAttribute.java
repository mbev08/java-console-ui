package com.mbev08.consoleui.core;

/**
 * Aggregated Attribute responsible for storing multiple related properties.
 * <p>
 * <i>(e.g. bg color, fg color)</i>
 */
public interface AggregatedAttribute {

    /**
     * Updates the {@link AggregatedAttribute} properties passed as args.
     *
     * @param args  values that positionally align with the attributes of the concrete class.
     */
    public void update(Object... args);
}
