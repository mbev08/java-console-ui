package com.mbev08.consoleui.core;

import com.mbev08.consoleui.core.renderers.Cursor;

public class Canvas {

    public Size size;
    public Block[][] matrix;

    /**
     * Constructor
     */
    public Canvas() {
        this.matrix = new Block[size.height][size.width];
        this.size = null;
    }

    /**
     * Constructor
     */
    public Canvas(Size size) {
        this();
        this.size = size;
    }
    
    /**
     * Clears existing {@link #matrix}
     */
    public void clear() {

        for (int y = 0; y < this.size.height; y++) {
            for (int x = 0; x < this.size.width; x++) {
                this.matrix[y][x] = new Block(' ', null, null);
            }
        }

    }

    /**
     * Resizes {@link Canvas}
     */
    public void resize(Size size) {
        this.size = size;
        this.clear();
    }

    /**
     * Gets Block from specified row and column
     *
     * @param row       =   target row
     * @param column    =   target column
     *
     * @return {@link Block}
     */
    public Block getBlock(int row, int column) {
        return matrix[row][column];
    }

    /**
     * Gets Block from {@link Cursor}
     *
     * @param cursor    =   target {@link Cursor}
     *
     * @return {@link Block}
     */
    public Block getBlock(Cursor cursor) {
        return matrix[cursor.position.y][cursor.position.x];
    }

}
