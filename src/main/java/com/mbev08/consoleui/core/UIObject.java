package com.mbev08.consoleui.core;


import com.mbev08.consoleui.components.Button;
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

    /**
     * Constructs instance using the provided text and selectability.
     *
     * @param text              =   Text value the object displays.
     * @param isSelectable      =   Toggle of whether or not the cursor can hover 
     *                              over te object for selection (i.e. {@link Button})
     */
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

    /**
     * Constructs instance using the provided text, selectability, 
     * and custom color scheme's colors..
     *
     * @param text              =   Text value the object displays.
     * @param isSelectable      =   Toggle of whether or not the cursor can hover 
     *                              over te object for selection (i.e. {@link Button})
     * @param defaultBg         =   Default background color of UI Object.
     * @param defaultFg         =   Default foreground color of UI Object
     */
    public UIObject(String text, boolean isSelectable, Color defaultBg, Color defaultFg ) {
        this(text, isSelectable);
        this.defaultColorScheme.update(defaultBg, defaultFg);
    }

    /**
     * Change the {@link Color}s of {@link UIObject#defaultColorScheme}.
     *
     * @param bg    =   Background {@link Color} 
     * @param fg    =   Foreground {@link Color} 
     */
    public void updateDefaultColorScheme(Color bg, Color fg) {
        // TODO: Rename updateDefaultColorScheme()
        defaultColorScheme.update(bg, fg);
        currentColorScheme.update(defaultColorScheme.bg, defaultColorScheme.fg);
        setOtherColorSchemesPerDefaultColorScheme();
    }

    /**
     * Changes the other {@link ColorScheme}s of the UI Object according to 
     * what the {@link UIObject#defaultColorScheme} is set to.
     */
    private void setOtherColorSchemesPerDefaultColorScheme() {
        // TODO: Remove/move to different abstract class or interface, as not all UIObjects will have multiple ColorSchemes.
        
        // TODO: Move validation to a dedicated class.
        if (defaultColorScheme == null) {
            throw new NullPointerException("Default Color Scheme is null");
        }

        if (highlightedColorScheme.modifier == AttributeModifier.AUTO) {
            highlightedColorScheme.update(defaultColorScheme.fg, defaultColorScheme.bg);
        }
    }

    /**
     * Calculates the max Y coordinate of the {@link UIObject}
     *
     * @return  calculated {@link Integer} value. 
     */
    public int getMaxYPosition() {
        return position.y + size.height - 1;
    }

    /**
     * Calculates the max X coordinate of the {@link UIObject}
     *
     * @return  calculated {@link Integer} value. 
     */
    public int getMaxXPosition() {
        return position.x + size.width - 1;
    }

    /**
     * Refreshes the {@link UIObject#size} based on length of {@link UIObject#text}
     * and {@link UIObject#padding} values.
     */
    public void refreshSize() {
        int newWidth, newHeight;

        newWidth = text.length() + padding.left + padding.right;
        newHeight = 1 + padding.top + padding.bottom; // TODO: Probably need to add some kind of word wrap consideration here.

        size.update(newWidth, newHeight);
    }

}
