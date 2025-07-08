package com.mbev08.consoleui.core.renderers;

import com.mbev08.consoleui.core.*;
import com.mbev08.consoleui.enums.AttributeModifier;



public class UIObjectRenderer {

    private Canvas canvas;
    private Cursor cursor;

    /**
     * Constructs an instance using the provided {@link UIObject} 
     *
     */
    public UIObjectRenderer() {
        this.canvas = new Canvas();
        this.cursor = new Cursor();
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
        
        renderContent(uiObject);

        return this.canvas;
    }

    /**
     * Render the content of {@link UIObject}.
     *
     * @param uiObject      =   target {@link UIObject}
     */
    private void renderContent(UIObject uiObject) {
        addVerticalPadding(uiObject.padding.top);
        addHorizontalPadding(uiObject.padding.left);

        addText(uiObject.text);

        addHorizontalPadding(uiObject.padding.right);
        addVerticalPadding(uiObject.padding.bottom);
    }
    
    /**
     * Maps vertical padding to an existing charMatrix
     *
     * @param n      =   count of vertical padding to add
     */
    private void addVerticalPadding(int n) {
        this.cursor.skipYPositions(n);
    }

    /**
     * Maps horizontal padding to an existing line of a charMatrix
     *
     * @param n      =   count of horizontal padding to add
     */
    private void addHorizontalPadding(int n) {
        this.cursor.skipXPositions(n);
    }

    /**
     * Maps {@link UIObject#text} value to an existing line of a charMatrix
     *
     * @param text     =   String for {@link UIObject#text}
     */
    private void addText(String text) {
        char[] textChars = text.toCharArray();

        cursor.moveToLastPosition();

        for (int i = 0; i < text.length(); i++) {
            cursor.nextX();

            Block targetBlock = this.canvas.getBlock(this.cursor);

            targetBlock.charValue = textChars[i];
        }


        for (int x = 0; x < this.canvas.size.width; x++) {

            Block targetBlock = ;

            if (!targetBlock.isEmpty()) {
                continue;
            }

            break;
        }
    }

}
