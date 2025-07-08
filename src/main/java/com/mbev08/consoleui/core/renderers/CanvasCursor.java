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

    /**
     * Skips <i>n</i> columns starting at current position.
     *
     * @param n     =   number of columns to skip.
     */
    public void skipColumns(int n) {
        this.column += n;
    }

    /**
     * Skips <i>n</i> rows starting at current position.
     *
     * @param n     =   number of rows to skip.
     */
    public void skipRows(int n) {
        this.row += n;
    }


}
