package com.mbev08.consoleui.core.validators;

import com.mbev08.consoleui.core.UIObject;
import com.mbev08.consoleui.core.View;


/**
 * Validator for instances of {@link View}.
 */
public class ViewValidator implements Validator {

    /**
     * Target {@link View} currently being validated.
     */
    View view;

    /**
     * Construct instances of validator
     */
    public ViewValidator() { }


    /**
     * Complete confirmation that an object passed all validation steps.
     *
     * @param object    {@link View} to validate.
     *
     * @return          pass/fail result for the object.
     */
    @Override
    public boolean isValid(Object object) {
        this.view = (View) object;

        checkForObjectCollisions();


        return true;
    }

    /**
     * Checks for any {@link UIObject}(s) that would inappropriately collide with another object or boundary.
     *
     * <ol>
     *     <li>{@link #checkForOriginPositionOverlap}
     *     <li>checkForObjectOnObjectCollisions
     *     <li>checkForConsoleAppBoundaryCollisions
     * </ol>
     */
    private void checkForObjectCollisions() {
        for (UIObject targetObject : view.uiObjects) {
            for (UIObject refObject : view.uiObjects) {
                if (refObject == targetObject) {
                    continue;
                }

                //TODO: delete checkForOriginPositionOverlap and consolidate into checkForObjectOnObjectCollisions
                checkForOriginPositionOverlap(targetObject, refObject);
                //TODO: checkForObjectOnObjectCollisions
                //TODO: checkForConsoleAppBoundaryCollisions

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
