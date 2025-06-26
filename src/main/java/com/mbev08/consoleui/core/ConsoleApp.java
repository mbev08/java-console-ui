package com.mbev08.consoleui.core;

import java.util.ArrayList;
import java.util.Scanner;

import com.mbev08.consoleui.enums.AppState;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * The console app used to host {@link View}(s)
 */
public class ConsoleApp {

    Size size;
    AppState state;
    ArrayList<View> views;
    int activeViewIndex;

    /**
     * Construct app using default attributes
     */
    public ConsoleApp() {
        size = Config.appSizeDefault;
        state = AppState.STARTUP;
        this.views = new ArrayList<>();
        activeViewIndex = 0;
    }

    /**
     * Construct app using specified size
     *
     * @param width     console app's width
     * @param height    console app's height
     */
    public ConsoleApp(int width, int height) {
        this();
        this.size.width = width;
        this.size.height = height;
    }

    /**
     * Append {@link View} to list of {@link #views}
     *
     * @param view  {@link View} to add.
     */
    public void addView(View view) {
        views.add(view);
    }

    /**
     * Run the application's "main loop".
     */
    public void run() {
        Scanner input = new Scanner(System.in);

        AnsiConsole.systemInstall();

        do {
            clearScreen();
            state = AppState.LOADING;

            views.get(activeViewIndex).load();

            state = AppState.READY;

            if(input.nextLine().equals("EXIT")) {
                state = AppState.EXIT;
            }


        } while (state != AppState.EXIT);





    }

    /**
     * Clear console
     * <p>
     * Typically called to prep prepare for next {@link View#load()}paint.
     */
    private void clearScreen() {
        System.out.println( ansi().eraseScreen() );
    }

}
