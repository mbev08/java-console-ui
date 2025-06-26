package com.mbev08.consoleui.core;

import java.util.ArrayList;

/**
 * Manages each {@link Block} in the {@link Frame#canvas}
 * according to the App's parameters and {@link View#uiObjects} to load
 * and other attributes (e.g. {@link Frame#size}).
 */
public class Frame {

    public Position position;
    public Size size;
    public Appearance defaultColorScheme;
    private Block[][] canvas;

    /**
     * Constructs instance only using the default app attributes
     */
    public Frame() {
        this.position = new Position(0, 0, 0);
        this.size = Config.appSizeDefault;
        this.defaultColorScheme = Config.appColorSchemeDefault;
        this.canvas = new Block[size.height][size.width];

        // create new Cells for canvas
        for (int y = 0; y < size.height; y++) {
            for (int x = 0; x < size.width; x++) {
                this.canvas[y][x] = new Block(' ', null, null);
            }
        }
    }

    /**
     * Constructs instance using the default app attributes
     * and override the default {@link Config#appSizeDefault}.
     * with user-defined values.
     *
     * @param size          =   Specified {@link Size}
     */
    public Frame(Size size) {
        this();
        this.size = size;
    }

    /**
     * Constructs instance using the default app attributes
     * and override the default (0, 0, 0) position and {@link Config#appSizeDefault}.
     * with user-defined values.
     *
     * @param position      =   Specified {@link Position}
     * @param size          =   Specified {@link Size}
     */
    public Frame(Position position, Size size) {
        this();
        this.position = position;
        this.size = size;
    }

    /**
     * Handles the prep and completion of painting each block to the canvas
     * <ol>
     *     <li>Clear {@link Block}s in {@link Frame#canvas},
     *     <li>Compile new attributes for each {@link Block}s in {@link Frame#canvas},
     *     <li>Paint (output) the canvas to the screen.
     * </ol>
     */
    public void paint(ArrayList<UIObject> uiObjects) {
        clearCanvas();
        compile(uiObjects);

        for (Block[] row : canvas) {
            for (Block block : row) {
                block.paint();
            }
            System.out.println();
        }
    }

    /**
     * Clear {@link Block}s in {@link Frame#canvas},
     */
    private void clearCanvas() {
        for (Block[] row : canvas) {
            for (Block block : row) {
                block.clear();
            }
        }
    }

    /**
     * Compile new attributes for each {@link Block}s in {@link Frame#canvas},
     * <ol>
     *     <li>Populates blocks to allocate space for {@link View#uiObjects}
     *     <li>Populates whitespace in unallocated space
     * </ol>
     *
     * @param uiObjects     =   Target list of {@link UIObject}
     */
    private void compile(ArrayList<UIObject> uiObjects) {
        loadUIObjectsToCanvas(uiObjects);
        loadBlankBlocksToCanvas();
    }

    /**
     * Populates blocks to allocate space for {@link View#uiObjects}
     *
     * @param uiObjects
     */
    private void loadUIObjectsToCanvas(ArrayList<UIObject> uiObjects) {

        for (UIObject uiObject : uiObjects) {
            char[][] objAsBlockMatrix = uiObject.toCharMatrix();

            for (int y = uiObject.position.y; y <= uiObject.getEndingYPosition(); y++) {
                for (int x = uiObject.position.x; x <= uiObject.getEndingXPosition(); x++) {
                    char objChar = objAsBlockMatrix[y - uiObject.position.y][x - uiObject.position.x];
                    canvas[y][x].update(objChar, uiObject.currentAppearance.bg, uiObject.currentAppearance.fg);
                }
            }
        }

    }

    /**
     * Populates whitespace in unallocated space
     */
    private void loadBlankBlocksToCanvas() {
        for (int y = 0; y < size.height; y++) {
            for (int x = 0; x < size.width; x++) {
                if (canvas[y][x].bg == null) {
                    canvas[y][x].update(' ', this.defaultColorScheme.bg, this.defaultColorScheme.fg);
                }
            }
        }
    }

    /**
     * Applies the {@link Frame#defaultColorScheme} to {@link UIObject#defaultAppearance}
     * if needed.
     * @param uiObject
     */
    public void applyViewAppearanceToUIObject(UIObject uiObject) {
        if (uiObject.defaultAppearance.bg == null) {
            uiObject.updateDefaultAppearance(defaultColorScheme.bg, defaultColorScheme.fg);
        }
    }
}
