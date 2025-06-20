package com.mbev08.consoleui;

import com.mbev08.consoleui.core.ConsoleApp;
import com.mbev08.consoleui.core.Content;
import com.mbev08.consoleui.core.UIObject;
import com.mbev08.consoleui.core.View;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;


public class Main {
    public static void main(String[] args) {

        ConsoleApp app = new ConsoleApp();

        // Create main menu view
        {
            View mainMenu = new View(RED, BLACK);

            UIObject welcomeText = new UIObject("Welcome to the App!", false);
            mainMenu.addUIObject(welcomeText);

            UIObject welcomeText2 = new UIObject("HOPE YOU ENJOY THE SHOW", false);
            welcomeText2.position.update(1, 2, 3);
            welcomeText2.defaultAppearance.update(RED, BLUE);
            welcomeText2.setAppearanceBasedOnDefault();
            mainMenu.addUIObject(welcomeText2);

            UIObject welcomeText3 = new UIObject(new Content("wt3", "surely this will work."), false);
            welcomeText3.position.update(2, 2, 3);
            welcomeText3.defaultAppearance.update(WHITE, BLACK);
            welcomeText3.setAppearanceBasedOnDefault();
            mainMenu.addUIObject(welcomeText3);

            app.addView(mainMenu);
        }


        app.run();


    }
}