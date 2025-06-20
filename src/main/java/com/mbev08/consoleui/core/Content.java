package com.mbev08.consoleui.core;

public class Content implements AggregatedAttribute {
    public String label, text;

    public Content(String label, String text) {
        this.label = label;
        this.text = text;
    }

    @Override
    public void update(Object... args) {
        this.label = (String) args[0];
        this.text = (String) args[1];
    }
}
