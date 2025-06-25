package com.mbev08.consoleui.core;

import java.util.ArrayList;

/**
 * Manages each {@link Block} in the {@link Frame#blockMatrix}
 * according to the {@link Frame#view}'s {@link View#uiObjects}
 * and other attributes (e.g. {@link Frame#size}
 */
public class Frame {

    private View view;
    public Position position;
    public Size size;
    // TODO: Add defaultColorScheme
    // TODO: Rename cellMatrix... Canvas?
    private Block[][] blockMatrix;

    /**
     * Constructs instance only using the provided {@link View}'s Attributes.
     *
     * @param view      =   Target {@link View}
     */
    public Frame(View view) {
        this.view = view;
        this.position = view.position;
        this.size = view.size;
        this.blockMatrix = new Block[size.height][size.width];

        // create new Cells for cellMatrix
        {
            for (int y = 0; y < size.height; y++) {
                for (int x = 0; x < size.width; x++) {
                    this.blockMatrix[y][x] = new Block(' ', null, null);
                }
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
     *     <li>Clear {@link Block}s in {@link Frame#blockMatrix},
     *     <li>Compile new attributes for each {@link Block}s in {@link Frame#blockMatrix},
     *     <li>Paint (output) the canvas to the screen.
     * </ol>
     */
    public void paint() {
        clearBlockMatrix();
        compile(view.uiObjects);

        for (Block[] row : blockMatrix) {
            for (Block block : row) {
                block.paint();
            }
            System.out.println();
        }


    }

    /**
     * Clear {@link Block}s in {@link Frame#blockMatrix},
     */
    private void clearBlockMatrix() {
        for (Block[] row : blockMatrix) {
            for (Block block : row) {
                block.clear();
            }
        }
    }

    /**
     * Compile new attributes for each {@link Block}s in {@link Frame#blockMatrix},
     * <ol>
     *     <li>Populates blocks to allocate space for {@link View#uiObjects}
     *     <li>Populates whitespace in unallocated space
     * </ol>
     *
     * @param uiObjects     =   Target list of {@link UIObject}
     */
    private void compile(ArrayList<UIObject> uiObjects) {
        // Populates blocks to allocate space for View#uiObjects
        {
            for (UIObject uiObject : uiObjects) {
                char[][] objMatrix = uiObject.toCharMatrix();

                for (int y = uiObject.position.y; y <= uiObject.getEndingYPosition(); y++) {
                    for (int x = uiObject.position.x; x <= uiObject.getEndingXPosition(); x++) {
                        char objChar = objMatrix[y - uiObject.position.y][x - uiObject.position.x];
                        blockMatrix[y][x].update(objChar, uiObject.currentAppearance.bg, uiObject.currentAppearance.fg);
                    }
                }
            }
        }

        // Populates whitespace in unallocated space
        {
            for (int y = 0; y < size.height; y++) {
                for (int x = 0; x < size.width; x++) {
                    if (blockMatrix[y][x].bg == null) {
                        blockMatrix[y][x].update(' ', view.appearance.bg, view.appearance.fg);
                    }
                }
            }
        }
    }

}
