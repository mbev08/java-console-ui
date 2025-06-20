package com.mbev08.consoleui.core;

import java.util.ArrayList;
import java.util.Scanner;

import com.mbev08.consoleui.enums.AppState;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class ConsoleApp {

    AppState state;
    ArrayList<View> views;
    int activeViewIndex;

    public ConsoleApp() {
        state = AppState.STARTUP;
        this.views = new ArrayList<>();
        activeViewIndex = 0;
    }

    public void addView(View view) {
        views.add(view);
    }

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

    private void clearScreen() {
        System.out.println( ansi().eraseScreen() );
    }

}
