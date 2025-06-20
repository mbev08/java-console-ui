package com.mbev08.consoleui.core;

import com.mbev08.consoleui.core.validators.Validator;
import com.mbev08.consoleui.core.validators.ViewValidator;

import static org.fusesource.jansi.Ansi.Color;
import java.util.ArrayList;


public class View {

    public ArrayList<UIObject> uiObjects;
    public Appearance appearance;

    public View() {
        uiObjects = new ArrayList<>();
    }
    public View(Color bg, Color fg) {
        uiObjects = new ArrayList<>();
        appearance = new Appearance(bg, fg);
    }

    public void load() {
        Validator validator = new ViewValidator();

        if (validator.isValid(this)) {
            for (UIObject uiObject : uiObjects) {
                uiObject.load();
            }
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
                uiObject.setAppearanceBasedOnDefault();
            }
        }
    }

}
