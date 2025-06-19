package com.mbev08.consoleui.core;


/**
 * Abstract UI Object
 * */
public class UIObject {

    Position position;
    Size size;
    Spacing margin;
    Content content;
    Appearance currentAppearance;
    Appearance defaultAppearance;
    Appearance highlightedAppearance;
    Appearance selectedAppearance;
    boolean isSelectable;

    public UIObject(Content content, boolean isSelectable) {
        this.content = content;
        this.isSelectable = isSelectable;

        setUnpopulatedAttributes();
    }

    public void setUnpopulatedAttributes() {
        if (this.content == null) {
            throw new NullPointerException("Content is null");
        }

        setPosition(0, 0, 0);
        setSize(content.text.length(), 1);
        setMargin(0, 0, 0, 0);
    }

    public void setPosition(int x, int y, int z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public void setSize(int width, int height) {
        size.width = width;
        size.height = height;
    }

    public void setMargin(int top, int bottom, int left, int right) {
        margin.top = top;
        margin.bottom = bottom;
        margin.left = left;
        margin.right = right;
    }

    public void setAppearanceBasedOnDefault() {
        if (defaultAppearance == null) {
            throw new NullPointerException("Default appearance is null");
        }

        currentAppearance = new Appearance(defaultAppearance.bg, defaultAppearance.fg);
        highlightedAppearance = new Appearance(defaultAppearance.fg, defaultAppearance.bg);
        selectedAppearance = new Appearance(defaultAppearance.bg, defaultAppearance.fg);
    }
}
