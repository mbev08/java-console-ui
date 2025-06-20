package com.mbev08.consoleui.enums;

// TODO: Decide on whether or not I should keep related logic.
/**
 * State of the application. <p>
 * * STARTUP = initializing application / required properties unknown (e.g. width/height) <p>
 * * LOADING = loading a view / user input not available. <p>
 * * READY = screen is loaded and ready for user input
 */
public enum AppState {
    /**
     * Startup app state.
     */
    STARTUP,
    /**
     * Loading app state.
     */
    LOADING,
    /**
     * Ready app state.
     */
    READY,
    /**
     * Exiting app state.
     */
    EXIT
}
