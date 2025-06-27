package com.mbev08.consoleui;

import com.mbev08.consoleui.core.ConsoleApp;
import com.mbev08.consoleui.core.UIObject;
import com.mbev08.consoleui.core.View;

import static org.fusesource.jansi.Ansi.Color.*;


public class Main {
    public static void main(String[] args) {

        int w = 50, h = 15;

        ConsoleApp app = new ConsoleApp(w, h);

        // Create main menu view
        {
            View mainMenu = new View(BLUE, BLACK);
            app.addView(mainMenu);

            UIObject welcomeText = new UIObject("Welcome to the App!", false);
            welcomeText.position.update((w / 2) - ( welcomeText.text.length() / 2 ), 0, 0);
            mainMenu.addUIObject(welcomeText);

            UIObject welcomeText2 = new UIObject("HOPE YOU ENJOY THE SHOW", false);
            welcomeText2.position.update(0, 5, 1);
            welcomeText2.padding.update(2, 0, 2, 1);
            welcomeText2.updateDefaultColorScheme(MAGENTA, WHITE);
            mainMenu.addUIObject(welcomeText2);

            UIObject welcomeText3 = new UIObject("surely this will work.", true);
            welcomeText3.position.update(Math.round((float) w * (float) 0.5), 7, 0);
            welcomeText3.padding.update( Math.round((float) h * (float) 0.2), Math.round((float) h * (float) 0.2), 0, 0 );
            welcomeText3.updateDefaultColorScheme(WHITE, BLACK);
            mainMenu.addUIObject(welcomeText3);
        }


        app.run();


    }
}