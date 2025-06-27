package com.mbev08.consoleui.core;

import com.mbev08.consoleui.core.validators.Validator;
import com.mbev08.consoleui.core.validators.ViewValidator;

import static org.fusesource.jansi.Ansi.Color;
import java.util.ArrayList;


/**
 * Manages a screen's {@link Frame} and list of {@link UIObject}(s)
 */
public class View {

    public ArrayList<UIObject> uiObjects;
    private Frame frame;

    /**
     * Constructs a {@link View} and sets the default
     * {@link Position} (0,0,0) and {@link Size} values (see {@link Config#appSizeDefault}).
     */
    public View() {
        this.uiObjects = new ArrayList<>();
        this.frame = new Frame();
    }

    /**
     * Constructs a {@link View} and overrides the
     * {@link Frame#defaultColorScheme} using the provided
     * {@link ColorScheme} values.
     *
     * @param bg        Background {@link Color} of {@link Frame#defaultColorScheme}
     * @param fg        Foreground {@link Color} of {@link Frame#defaultColorScheme}
     */
    public View(Color bg, Color fg) {
        this();
        this.frame.defaultColorScheme.update(bg, fg);
    }

    /**
     * As long as the View is valid, paint the {@link View#frame}
     *
     * @see Frame
     */
    public void load() {
        Validator validator = new ViewValidator();

        if (validator.isValid(this)) {
            frame.paint(this.uiObjects);
        }
    }

    /**
     * Normalizes {@link UIObject#defaultColorScheme},
     * then <b><i>appends</i></b> target {@link UIObject} to {@link View#uiObjects}.
     *
     * @param uiObject      Target {@link UIObject}
     */
    public void addUIObject(UIObject uiObject) {
        frame.applyFrameColorSchemeToUIObject(uiObject);
        uiObjects.add(uiObject);
    }

}
