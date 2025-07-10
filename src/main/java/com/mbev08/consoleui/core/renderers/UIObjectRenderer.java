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
        canvas = new Canvas();
        cursor = new Cursor();
    }

    /**
     * Maps {@link UIObject#text} and {@link UIObject#padding} to a 
     * {@link Frame#canvas}.
     *
     * @return charMatrix compatible with {@link Frame#canvas}
     */
    public Canvas render(UIObject uiObject) {
        if (uiObject.size.modifier == AttributeModifier.AUTO) {
            uiObject.refreshSize();
        }

        cursor.reset();
        canvas.resize(uiObject.size);
        
        compile(uiObject);

        return canvas;
    }

    /**
     * Render the content of {@link UIObject}.
     *
     * @param uiObject      =   target {@link UIObject}
     */
    private void compile(UIObject uiObject) {
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
        cursor.skipYPositions(n);
    }

    /**
     * Maps horizontal padding to an existing line of a charMatrix
     *
     * @param n      =   count of horizontal padding to add
     */
    private void addHorizontalPadding(int n) {
        cursor.skipXPositions(n);
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

            Block targetBlock = canvas.getBlock(cursor);

            targetBlock.charValue = textChars[i];
        }
    }
}
