package com.mbev08.consoleui;

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


    }
}