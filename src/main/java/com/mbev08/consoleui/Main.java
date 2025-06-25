package com.mbev08.consoleui;

import com.mbev08.consoleui.core.ConsoleApp;
import com.mbev08.consoleui.core.UIObject;
import com.mbev08.consoleui.core.View;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.Color.*;


public class Main {
    public static void main(String[] args) {

        int w = 100, h = 25;

        ConsoleApp app = new ConsoleApp(w, h);

        // Create main menu view
        {
            View mainMenu = new View(0, 0, 0, w, h, BLUE, BLACK);

            UIObject welcomeText = new UIObject("Welcome to the App!", false);
            welcomeText.position.update((w / 2) - ( welcomeText.text.length() / 2 ), 0, 0);
            mainMenu.addUIObject(welcomeText);

            UIObject welcomeText2 = new UIObject("HOPE YOU ENJOY THE SHOW", false);
            welcomeText2.position.update(0, 5, 1);
            welcomeText2.padding.update(2, 0, 2, 1);
            welcomeText2.updateDefaultAppearance(MAGENTA, WHITE);
            mainMenu.addUIObject(welcomeText2);

            UIObject welcomeText3 = new UIObject("surely this will work.", true);
            welcomeText3.position.update(Math.round((float) w * (float) 0.5), 7, 0);
            welcomeText3.padding.update( Math.round((float) h * (float) 0.2), Math.round((float) h * (float) 0.2), 0, 0 );
            welcomeText3.updateDefaultAppearance(WHITE, BLACK);
            mainMenu.addUIObject(welcomeText3);

            app.addView(mainMenu);
        }


        app.run();


    }
}