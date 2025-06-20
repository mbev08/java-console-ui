package com.mbev08.consoleui.core.validators;

import com.mbev08.consoleui.core.UIObject;
import com.mbev08.consoleui.core.View;

public class ViewValidator implements Validator {

    View view;

    public ViewValidator() { }

    @Override
    public boolean isValid(Object object) {
        this.view = (View) object;

        checkForObjectCollisions();


        return true;
    }

    private void checkForObjectCollisions() {
        for (UIObject targetObject : view.uiObjects) {
            for (UIObject refObject : view.uiObjects) {
                if (refObject == targetObject) {
                    continue;
                }

                checkForOriginPositionOverlap(targetObject, refObject);
                //TODO: check for size overlap
                //TODO: check for console app boundaries

            }
        }
    }

    private void checkForOriginPositionOverlap(UIObject obj1, UIObject obj2) {
        if (obj2.position.x == obj1.position.x
                && obj2.position.y == obj1.position.y
                && obj2.position.z == obj1.position.z) {
            throw new RuntimeException(
                    "Object's origin position collision detected ("
                            + obj1.text + " & "
                            + obj2.text + ")"
                    );
        }
    }
}
