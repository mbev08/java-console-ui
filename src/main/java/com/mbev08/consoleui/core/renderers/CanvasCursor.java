package com.mbev08.consoleui.core.renderers;


public class CanvasCursor {

    public int column, row;

    /**
     * Constructor
     */
    public CanvasCursor() {
        this.column = 0;
        this.row = 0;
    }

    /**
     * Resets cursor back to (0,0)
     */
    public void reset() {
        this.column = 0;
        this.row = 0;
    }

}
