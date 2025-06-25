package com.mbev08.consoleui.core;


import com.mbev08.consoleui.enums.AttributeModifier;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.Color.*;


public class UIObject {

    public Position position;
    public Size size;
    public Spacing padding;
    public String text;

    // TODO: Replace *Appearance vars with ColorSchemeService
    public Appearance currentAppearance;
    public Appearance defaultAppearance;
    public Appearance highlightedAppearance;
    public Appearance selectedAppearance;

    // TODO: Replace isSelectable w/ Enum ObjectType
    public boolean isSelectable;

    public UIObject(String text, boolean isSelectable) {
        if (text == null) {
            throw new NullPointerException("Text is null");
        }

        this.text = text;
        this.isSelectable = isSelectable;

        this.position = new Position(Config.uiObjectPositionDefault.x, Config.uiObjectPositionDefault.y, Config.uiObjectPositionDefault.z);
        this.size = new Size(text.length(), Config.uiObjectSizeDefault.height);
        this.padding = new Spacing(Config.uiObjectPaddingDefault.top, Config.uiObjectPaddingDefault.bottom, Config.uiObjectPaddingDefault.left, Config.uiObjectPaddingDefault.right);

        this.defaultAppearance = new Appearance(null, null );
        this.highlightedAppearance = new Appearance(null, null );
        this.selectedAppearance = new Appearance(null, null );
        this.currentAppearance = this.defaultAppearance;
    }

    public UIObject(String text, boolean isSelectable, Color defaultBg, Color defaultFg ) {
        this(text, isSelectable);
        this.defaultAppearance.update(defaultBg, defaultFg);
    }

    public void updateDefaultAppearance(Color bg, Color fg) {
        defaultAppearance.update(bg, fg);
        currentAppearance.update(defaultAppearance.bg, defaultAppearance.fg);
        setAppearanceBasedOnDefault();
    }

    private void setAppearanceBasedOnDefault() {
        if (defaultAppearance == null) {
            throw new NullPointerException("Default appearance is null");
        }

        if (highlightedAppearance.modifier == AttributeModifier.AUTO) {
            highlightedAppearance.update(defaultAppearance.fg, defaultAppearance.bg);
        }

        if (selectedAppearance.modifier == AttributeModifier.AUTO) {
            selectedAppearance.update(defaultAppearance.bg, defaultAppearance.fg);
        }
    }

    public int getEndingYPosition() {
        return position.y + size.height - 1;
    }

    public int getEndingXPosition() {
        return position.x + size.width - 1;
    }

    private void refreshSize() {
        int newWidth, newHeight;

        newWidth = text.length() + padding.left + padding.right;
        newHeight = 1 + padding.top + padding.bottom; // TODO: Probably need to add some kind of word wrap consideration here.

        size.update(newWidth, newHeight);
    }

    private boolean charIsEmpty(char ch) {
        return ch == 0;
    }

    private char[] newLineToCharArray(char[] line) {
        for (int x = 0; x < line.length; x++) {
            line[x] = ' ';
        }

        return line;
    }

    private char[][] verticalPaddingToCharArray(int paddingStack, char[][] charMatrix) {
        if (paddingStack <= 0) {
            return charMatrix;
        }

        for (int y = 0; y < charMatrix.length; y++) {
            if(!charIsEmpty(charMatrix[y][0])) {
                continue;
            }

            for (int i = 0; i < paddingStack; i++) {
                charMatrix[y] = newLineToCharArray(charMatrix[y]);
                y++;
            }
            break;
        }

        return charMatrix;
    }

    private char[] horizontalPaddingToCharArray(int paddingStack, char[] line) {
        if (paddingStack <= 0) {
            return line;
        }

        for (int x = 0; x < line.length; x++) {
            if (!charIsEmpty(line[x])) {
                continue;
            }

            for (int i = 0; i < paddingStack; i++) {
                line[x] = ' ';
                x++;
            }
            break;
        }

        return line;
    }

    private char[] textToCharArray(int textStack, char[] line) {
        char[] textChars = text.toCharArray();

        for (int x = 0; x < line.length; x++) {
            if (!charIsEmpty(line[x])) {
                continue;
            }

            for (int i = 0; i < textStack; i++) {
                line[x] = textChars[i];
                x++;
            }
            break;
        }

        return line;
    }

    public char[][] toCharMatrix() {
        if (size.modifier == AttributeModifier.AUTO) {
            refreshSize();
        }

        char[][] charMatrix = new char[size.height][size.width];
        int x = 0, y = 0;

        // TODO: Add consideration of wordwrap
        // TODO: Ensure validation against running out of room for text per size
        charMatrix = verticalPaddingToCharArray(padding.top, charMatrix);
        y += padding.top;

        charMatrix[y] = horizontalPaddingToCharArray(padding.left, charMatrix[y]);
        x += padding.left;

        charMatrix[y] = textToCharArray(text.length(), charMatrix[y]);
        x += text.length();

        charMatrix[y] = horizontalPaddingToCharArray(padding.right, charMatrix[y]);
        x += padding.right;

        charMatrix = verticalPaddingToCharArray(padding.bottom, charMatrix);
        y = padding.bottom;

        return charMatrix;
    }

}
