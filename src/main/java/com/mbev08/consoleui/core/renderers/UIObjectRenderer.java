package com.mbev08.consoleui.core.renderers;

import com.mbev08.consoleui.core.*;
import com.mbev08.consoleui.enums.AttributeModifier;



public class UIObjectRenderer {

    private Canvas canvas;
    private CanvasCursor cursor;

    /**
     * Constructs an instance using the provided {@link UIObject} 
     *
     */
    public UIObjectRenderer() {
        this.canvas = new Canvas();
        this.cursor = new CanvasCursor();
    }

    /**
     * Maps {@link UIObject#text} and {@link UIObject#padding} to a 
     * {@link Frame#canvas}.
     *
     * @return charMatrix compatible with {@link Frame#canvas}
     */
    public Canvas render(UIObject uiObject) {
        // TODO: Ensure validation against running out of room for text per size
        // TODO: Add consideration of wordwrap

        if (uiObject.size.modifier == AttributeModifier.AUTO) {
            uiObject.refreshSize();
        }

        this.cursor.reset();
        this.canvas.resize(uiObject.size);


        addText(uiObject.text);

        return this.canvas;
    }

    /**
     * Add padding to canvas
     */
    public void renderPadding(Spacing padding) {
        addVerticalPadding(padding.top);
        addVerticalPadding(padding.bottom);
        addHorizontalPadding(padding.left);
        addHorizontalPadding(padding.right);
    }
    
    /**
     * Maps vertical padding to an existing charMatrix
     *
     * @param n      =   count of vertical padding to add
     */
    private void addVerticalPadding(int n) {
        // TODO: move to renderer class
        if (n <= 0) {
            // Offset
            return;
        }

        for (int y = 0; y < charMatrix.length; y++) {
            if(!charIsEmpty(charMatrix[y][0])) {
                continue;
            }

            for (int i = 0; i < n; i++) {
                this.canvas.matrix[y] = newLineToCharArray(y);
                y++;
            }
            break;
        }
    }

    /**
     * Maps horizontal padding to an existing line of a charMatrix
     *
     * @param n      =   count of horizontal padding to add
     */
    private void addHorizontalPadding(int n) {
        if (n <= 0) {
            return line;
        }

        for (int x = 0; x < this.canvas.size.width; x++) {
            if (!charIsEmpty(line[x])) {
                continue;
            }

            for (int i = 0; i < n; i++) {
                line[x] = ' ';
                x++;
            }
            break;
        }
    }

    I LEFT OFF HERE!!!!

    /**
     * Maps {@link UIObject#text} value to an existing line of a charMatrix
     *
     *
     * @param text     =   String for {@link UIObject#text}
     */
    private void addText(String text) {
        char[] textChars = text.toCharArray();

        for (int x = 0; x < this.canvas.size.width; x++) {

            Block block = this.canvas.matrix[cursor.row][x];

            if (!block.isEmpty()) {
                continue;
            }

            for (int i = 0; i < text.length(); i++) {
                block.charValue = textChars[i];
                x++;
            }
            break;
        }
    }

}
