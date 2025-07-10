package com.mbev08.consoleui.core.renderers;

import java.util.ArrayList;
import com.mbev08.consoleui.core.*;


public class ViewRenderer {

    private View view;
    private Canvas canvas;
    private UIObjectRenderer objRenderer;
    
    /**
     * Constructs an instance using the provided {@link View} 
     *
     */
    public ViewRenderer(View view) {
        this.view = view;
        canvas = new Canvas(view.size);
        objRenderer = new UIObjectRenderer();
    }

    /**
     * Updates {@link View}
     */
    public void changeView(View view) {
        this.view = view;
        render();
    }

    /**
     * Renders target {@link #view}
     */
    public void render() {
        canvas.clear();
        output();
    }

    /**
     * Populates blocks to allocate space for {@link View#uiObjects}
     *
     * @param uiObjects
     */
    private void loadUIObjects(ArrayList<UIObject> uiObjects) {
        for (UIObject uiObject : uiObjects) {
            applyFrameColorSchemeToUIObject(uiObject);
            insertUIObject(uiObject);
        }
    }

    /**
     * Applies the {@link Frame#defaultColorScheme} to {@link UIObject#defaultColorScheme}
     * if needed.
     * @param uiObject
     */
    public void applyFrameColorSchemeToUIObject(UIObject uiObject) {
        if (uiObject.defaultColorScheme.bg == null) {
            uiObject.updateDefaultColorScheme(view.defaultColorScheme.bg, view.defaultColorScheme.fg);
        }
    }

    /**
     * Populates blocks to allocate space for {@link View#uiObjects}
     *
     * @param uiObjects
     */
    private void insertUIObject(UIObject uiObject) {

        Canvas objCanvas = objRenderer.render(uiObject);

        for (int y = uiObject.position.y; y <= uiObject.getMaxYPosition(); y++) {
            for (int x = uiObject.position.x; x <= uiObject.getMaxXPosition(); x++) {
                
                Block block = objCanvas.matrix[y - uiObject.position.y][x - uiObject.position.x];
                canvas.matrix[y][x].update(block.charValue, block.bg, block.fg);

            }
        }
    }

    /**
     * Populates whitespace in unallocated space
     */
    private void loadBlankBlocksToCanvas() {
        for (int y = 0; y < view.size.height; y++) {
            for (int x = 0; x < view.size.width; x++) {

                Block block = canvas.matrix[y][x];

                if (block.bg == null) {
                    block.update(' ', view.defaultColorScheme.bg, view.defaultColorScheme.fg);
                }
            }
        }
    }

    /**
     * Handles the prep and completion of painting each block to the canvas
     * <ol>
     *     <li>Clear {@link Block}s in {@link Frame#canvas},
     *     <li>Compile new attributes for each {@link Block}s in {@link Frame#canvas},
     *     <li>Paint (output) the canvas to the screen.
     * </ol>
     */
    public void output() {
        for (Block[] row : canvas.matrix) {
            for (Block block : row) {
                block.paint();
            }
            System.out.println();
        }
    }

}
