package com.mbev08.consoleui.core;

import com.mbev08.consoleui.core.validators.Validator;
import com.mbev08.consoleui.core.validators.ViewValidator;

import static org.fusesource.jansi.Ansi.Color;
import java.util.ArrayList;


/**
 * Manages a screen's {@link Frame} and list of {@link UIObject}(s)
 */
public class View {

    // TODO: Move Position, Size, and Appearance to Frame's attributes only.
    public Position position;
    public Size size;
    public ArrayList<UIObject> uiObjects;
    public Appearance appearance;
    private Frame frame;

    /**
     * Constructs a {@link View} and sets the provided
     * {@link Position} and {@link Size} values.
     *
     * @param x         X position of {@link View#position}
     * @param y         Y position of {@link View#position}
     * @param z         Z position of {@link View#position}
     *                  <p>
     *                  <t><i>(i.e. <b>Z-Index</b>)</i>
     * @param width     Width of {@link View#size}
     * @param height    Height of {@link View#size}
     */
    public View(int x, int y, int z, int width, int height) {
        this.position = new Position(x, y, z);
        this.size = new Size(width, height);
        this.uiObjects = new ArrayList<>();
        this.frame = new Frame(this);
        this.appearance = new Appearance(Color.BLACK, Color.WHITE);
    }

    /**
     * Constructs a {@link View} and sets the provided
     * {@link Position}, {@link Size}, and {@link Appearance} values.
     *
     * @param x         X position of {@link View#position}
     * @param y         Y position of {@link View#position}
     * @param z         Z position of {@link View#position}
     *                  <p>
     *                  <t><i>(i.e. <b>Z-Index</b>)</i>
     * @param width     Width of {@link View#size}
     * @param height    Height of {@link View#size}
     * @param bg        Background Color of {@link View#appearance}
     * @param fg        Foreground Color of {@link View#appearance}
     */
    public View(int x, int y, int z, int width, int height, Color bg, Color fg) {
        this(x, y, z, width, height);
        this.appearance.update(bg, fg);
    }

    /**
     * As long as the View is valid, paint the {@link View#frame}
     *
     * @see Frame
     */
    public void load() {
        // TODO: Separate the validation stuff.. doing too much.
        Validator validator = new ViewValidator();

        if (validator.isValid(this)) {
            frame.paint();
        }
    }

    /**
     * Normalizes {@link UIObject#defaultAppearance},
     * then <b><i>appends</i></b> target {@link UIObject} to {@link View#uiObjects}.
     *
     * @param uiObject      Target {@link UIObject}
     */
    public void addUIObject(UIObject uiObject) {
        // TODO: Refactor this applyViewAppearanceToUIObject out of the method?
        applyViewAppearanceToUIObject(uiObject);
        uiObjects.add(uiObject);
    }

    /**
     * Applies the {@link View#appearance} to {@link UIObject#defaultAppearance}
     * if needed.
     * @param uiObject
     */
    private void applyViewAppearanceToUIObject(UIObject uiObject) {
        // TODO: Throw exception if appearance is null
        if (appearance != null) {
            if (uiObject.defaultAppearance.bg == null) {
                uiObject.updateDefaultAppearance(appearance.bg, appearance.fg);
            }
        }
    }

}
