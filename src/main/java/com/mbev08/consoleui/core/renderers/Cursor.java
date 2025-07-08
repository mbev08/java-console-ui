package com.mbev08.consoleui.core.renderers;

import com.mbev08.consoleui.core.Position;
import com.mbev08.consoleui.core.Size;


public class Cursor {

    public Position position;
    private Position lastPosition;
    // TODO add usage to refSize
    private Size refSize; 

    /**
     * Constructor
     */
    public Cursor() {
        position = new Position(0, 0);
        lastPosition = new Position(0, 0);
        refSize = new Size(0, 0);
    }

    /**
     * Constructor w/ specified size
     */
    public Cursor(Size size) {
        this();
        refSize = new Size(0, 0);
    }

    /** 
     * Records current in {@link #lastPosition}
     */
    private void recordPosition() {
        lastPosition.update(position.x, position.y);
    }

    /**
     * Updates current {@link #position} specified values
     *
     * @param x     =   target x position.
     * @param y     =   target y position.
     */
    private void setPosition(int x, int y) {
        recordPosition();
        position.update(x, y);
    }

    /**
     * Resets cursor back to (0,0)
     */
    public void reset() {
        setPosition(0, 0);
    }

    /**
     * Moves cursor to last recorded {@link #lastPosition}
     */
    public void moveToLastPosition() {
        setPosition(lastPosition.x, lastPosition.y);
    }

    /**
     * Moves cursor to next {@link #x} position
     */
    public void nextX() {
        setPosition(position.x + 1, position.y);
    }

    /**
     * Moves cursor to next {@link #y} position
     */
    public void nextY() {
        setPosition(0, position.y + 1);
    }

    /**
     * Skips <i>n</i> x position starting at current position.
     *
     * @param n     =   number of xs to skip.
     */
    public void skipXPositions(int n) {
        setPosition(position.x + n, position.y);
    }

    /**
     * Skips <i>n</i> y position starting at current position.
     *
     * @param n     =   number of ys to skip.
     */
    public void skipYPositions(int n) {
        int nextX = n > 0 ? 0 : position.x;
        setPosition(nextX, position.y + n);
    }
    

     




}
