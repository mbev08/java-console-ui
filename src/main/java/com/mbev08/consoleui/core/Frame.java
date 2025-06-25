package com.mbev08.consoleui.core;

import java.util.ArrayList;

/**
 * Manages each {@link Block} in the {@link Frame#canvas}
 * according to the {@link Frame#view}'s {@link View#uiObjects}
 * and other attributes (e.g. {@link Frame#size}
 */
public class Frame {

    private View view;
    public Position position;
    public Size size;
    // TODO: Add defaultColorScheme
    private Block[][] canvas;

    /**
     * Constructs instance only using the provided {@link View}'s Attributes.
     *
     * @param view      =   Target {@link View}
     */
    public Frame(View view) {
        this.view = view;
        this.position = view.position;
        this.size = view.size;
        this.canvas = new Block[size.height][size.width];

        // create new Cells for canvas
        for (int y = 0; y < size.height; y++) {
            for (int x = 0; x < size.width; x++) {
                this.canvas[y][x] = new Block(' ', null, null);
            }
        }
    }

    /**
     * Constructs instance using the provided {@link View}'s Attributes
     * and override the {@link View#position} and {@link View#size}.
     * with user-defined values.
     *
     * @param view          =   Target {@link View}
     * @param position      =   Specified {@link Position}
     * @param size          =   Specified {@link Size}
     */
    public Frame(View view, Position position, Size size) {
        this(view);
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
    public void paint() {
        clearCanvas();
        compile(view.uiObjects);

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
                    canvas[y][x].update(' ', view.appearance.bg, view.appearance.fg);
                }
            }
        }
    }
}
