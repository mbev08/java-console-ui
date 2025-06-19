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

        System.out.println("Hello and welcome!");

        AnsiConsole.systemInstall();

        System.out.println( ansi().fg(RED).a("Hello").fg(GREEN).a(" World").reset() );

        System.out.println( ansi().eraseScreen() );
        System.out.println( ansi().eraseScreen().fg(YELLOW).bg(BLUE).a("Hello").fg(BLUE).bg(YELLOW).a(" World").reset() );

        System.out.println("Hello and welcome!");

        AnsiConsole.systemUninstall();

        ConsoleApp app = new ConsoleApp();

        // Create main menu view
        {
            View mainMenu = new View(RED, BLACK);

            UIObject welcomeText = new UIObject(new Content(""))
        }


    }
}