package com.mbev08.consoleui.core;


import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * Abstract UI Object
 * */
public class UIObject {

    public Position position;
    public Size size;
    public Spacing margin;
    public Content content;
    public Appearance currentAppearance;
    public Appearance defaultAppearance;
    public Appearance highlightedAppearance;
    public Appearance selectedAppearance;
    public boolean isSelectable;

    public UIObject(String contentStr, boolean isSelectable) {
        this.content = new Content(contentStr, contentStr);
        this.isSelectable = isSelectable;

        resolveAttributes();
    }
    public UIObject(Content content, boolean isSelectable) {
        this.content = content;
        this.isSelectable = isSelectable;

        resolveAttributes();
    }

    public void resolveAttributes() {
        if (this.content == null) {
            throw new NullPointerException("Content is null");
        }

        position = new Position(0, 0, 0);
        size = new Size(content.text.length(), 1);
        margin = new Spacing(0, 0, 0, 0);
        currentAppearance = new Appearance(BLACK, WHITE);
        defaultAppearance = new Appearance(BLACK, WHITE);
        highlightedAppearance = new Appearance(WHITE, BLACK);
        selectedAppearance = new Appearance(BLACK, WHITE);
    }

    public void setAppearanceBasedOnDefault() {
        if (defaultAppearance == null) {
            throw new NullPointerException("Default appearance is null");
        }

        currentAppearance.update(defaultAppearance.bg, defaultAppearance.fg);
        selectedAppearance.update(defaultAppearance.bg, defaultAppearance.fg);
        highlightedAppearance.update(defaultAppearance.fg, defaultAppearance.bg);
    }

    public void load() {
        System.out.println( ansi()
                .bg(defaultAppearance.bg).fg(defaultAppearance.fg)
                .bold()
                .a("(" + content.label + ")")
                .boldOff()
                .bg(highlightedAppearance.bg).fg(highlightedAppearance.fg)
                .a(content.text)
                .reset()
        );
    }
}
