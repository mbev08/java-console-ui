package com.mbev08.consoleui.core.validators;

/**
 * Interface for validators
 */
public interface Validator {

    /**
     * complete confirmation that an object passed all validation steps.
     *
     * @param object    object to validate.
     *
     * @return          pass/fail result for the object.
     */
    public boolean isValid(Object object);
}
