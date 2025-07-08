package com.mbev08.consoleui.core;



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


}
