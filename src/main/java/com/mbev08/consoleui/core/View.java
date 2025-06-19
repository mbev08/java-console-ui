package com.mbev08.consoleui.core;

import static org.fusesource.jansi.Ansi.Color;
import java.util.ArrayList;


public class View {

    ArrayList<UIObject> uiObjects;
    Appearance appearance;

    public View() {
        uiObjects = new ArrayList<>();
    }
    public View(Color bg, Color fg) {
        uiObjects = new ArrayList<>();
        appearance = new Appearance(bg, fg);
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
