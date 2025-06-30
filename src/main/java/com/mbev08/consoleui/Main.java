package com.mbev08.consoleui;

import com.mbev08.consoleui.core.ConsoleApp;
import com.mbev08.consoleui.core.UIObject;
import com.mbev08.consoleui.core.View;

import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.Color.*;


public class Main {
    public static void main(String[] args) {

        int w = 50, h = 15;
        Color bg = BLUE, fg = BLACK;

        ConsoleApp app = new ConsoleApp(w, h);

        // Create main menu view
        {
            View mainMenu = new View(bg, fg);
            app.addView(mainMenu);

            UIObject welcomeBanner = new UIObject("Welcome to the App!", false);
            welcomeBanner.position.update((w / 2) - ( welcomeBanner.text.length() / 2 ), 0, 0);
            mainMenu.addUIObject(welcomeBanner);

            // TODO: Create Builder for UIObject 
            UIObject menuOption1 = new UIObject("Option 1", true);
            menuOption1.position.update(2, 3, 0);
            menuOption1.padding.update(1, 1, 1, 1);
            menuOption1.refreshSize();
            menuOption1.updateDefaultColorScheme(fg, bg);
            mainMenu.addUIObject(menuOption1);

            UIObject menuOption2 = new UIObject("Option 2", true);
            menuOption2.position.update(2, menuOption1.getEndingYPosition() + 2, 0);
            menuOption2.padding.update(1, 1, 1, 1);
            menuOption2.refreshSize();
            menuOption2.updateDefaultColorScheme(fg, bg);
            mainMenu.addUIObject(menuOption2);

            UIObject menuOption3 = new UIObject("Option 3", true);
            menuOption3.position.update(2, menuOption2.getEndingYPosition() + 2, 0);
            menuOption3.padding.update(1, 1, 1, 1);
            menuOption3.refreshSize();
            menuOption3.updateDefaultColorScheme(fg, bg);
            mainMenu.addUIObject(menuOption3);

        }


        app.run();


    }
}
