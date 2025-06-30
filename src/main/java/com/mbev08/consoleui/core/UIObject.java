package com.mbev08.consoleui.core;


import com.mbev08.consoleui.enums.AttributeModifier;
import static org.fusesource.jansi.Ansi.Color;

// TODO: Make an abstract class.
public class UIObject {

    public Position position;
    public Size size;
    public Spacing padding;
    public String text;

    // TODO: Consolidate into a singular activeColorScheme
    public ColorScheme currentColorScheme;
    public ColorScheme defaultColorScheme;
    public ColorScheme highlightedColorScheme;

    // TODO: Remove isSelectable or redesign this type of categorization
    public boolean isSelectable;

    // TODO: Remove isSelectable as an arg
    public UIObject(String text, boolean isSelectable) {
        if (text == null) {
            throw new NullPointerException("Text is null");
        }

        this.text = text;
        this.isSelectable = isSelectable;

        this.position = new Position(Config.uiObjectPositionDefault.x, Config.uiObjectPositionDefault.y, Config.uiObjectPositionDefault.z);
        this.size = new Size(text.length(), Config.uiObjectSizeDefault.height);
        this.padding = new Spacing(Config.uiObjectPaddingDefault.top, Config.uiObjectPaddingDefault.bottom, Config.uiObjectPaddingDefault.left, Config.uiObjectPaddingDefault.right);

        this.defaultColorScheme = new ColorScheme(null, null );
        this.highlightedColorScheme = new ColorScheme(null, null );
        this.currentColorScheme = this.defaultColorScheme;
    }

    // TODO: Remove isSelectable as an arg
    public UIObject(String text, boolean isSelectable, Color defaultBg, Color defaultFg ) {
        this(text, isSelectable);
        this.defaultColorScheme.update(defaultBg, defaultFg);
    }

    // TODO: Rename updateDefaultColorScheme()
    public void updateDefaultColorScheme(Color bg, Color fg) {
        defaultColorScheme.update(bg, fg);
        currentColorScheme.update(defaultColorScheme.bg, defaultColorScheme.fg);
        setOtherColorSchemesPerDefaultColorScheme();
    }

    // TODO: Remove/move to different abstract class or interface, as not all UIObjects will have multiple ColorSchemes.
    private void setOtherColorSchemesPerDefaultColorScheme() {
        if (defaultColorScheme == null) {
            throw new NullPointerException("Default Color Scheme is null");
        }

        if (highlightedColorScheme.modifier == AttributeModifier.AUTO) {
            highlightedColorScheme.update(defaultColorScheme.fg, defaultColorScheme.bg);
        }
    }

    public int getEndingYPosition() {
        return position.y + size.height - 1;
    }

    public int getEndingXPosition() {
        return position.x + size.width - 1;
    }

    // TODO: this is bad logic/design.. need to revise.
    public void refreshSize() {
        int newWidth, newHeight;

        newWidth = text.length() + padding.left + padding.right;
        newHeight = 1 + padding.top + padding.bottom; // TODO: Probably need to add some kind of word wrap consideration here.

        size.update(newWidth, newHeight);
    }

    // TODO: move to extensions
    private boolean charIsEmpty(char ch) {
        return ch == 0;
    }

    // TODO: move to extensions
    private char[] newLineToCharArray(char[] line) {
        for (int x = 0; x < line.length; x++) {
            line[x] = ' ';
        }

        return line;
    }

    // TODO: move to extensions
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

    // TODO: move to extensions
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

    // TODO: move to extensions
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

    // TODO: move to extensions
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
