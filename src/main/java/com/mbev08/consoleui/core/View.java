package com.mbev08.consoleui.core;

import com.mbev08.consoleui.core.validators.Validator;
import com.mbev08.consoleui.core.validators.ViewValidator;

import static org.fusesource.jansi.Ansi.Color;
import java.util.ArrayList;


public class View {

    public Position position;
    public Size size;
    public ArrayList<UIObject> uiObjects;
    public Appearance appearance;
    private Frame frame;

    public View(int x, int y, int z, int width, int height) {
        this.position = new Position(x, y, z);
        this.size = new Size(width, height);
        this.uiObjects = new ArrayList<>();
        this.frame = new Frame(this);
        this.appearance = new Appearance(Color.BLACK, Color.WHITE);
    }
    public View(int x, int y, int z, int width, int height, Color bg, Color fg) {
        this(x, y, z, width, height);
        this.appearance.update(bg, fg);
    }

    public void load() {
        Validator validator = new ViewValidator();

        if (validator.isValid(this)) {
            frame.paint();
        }
    }

    public void addUIObject(UIObject uiObject) {
        applyViewAppearanceToUIObject(uiObject);
        uiObjects.add(uiObject);
    }

    private void applyViewAppearanceToUIObject(UIObject uiObject) {
        if (appearance != null) {
            if (uiObject.defaultAppearance == null) {
                uiObject.defaultAppearance = appearance;
            }
        }
    }

}
