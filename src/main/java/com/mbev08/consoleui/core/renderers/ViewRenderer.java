package com.mbev08.consoleui.core.renderers;

import java.util.ArrayList;
import com.mbev08.consoleui.core.*;
import com.mbev08.consoleui.enums.AttributeModifier;


public class ViewRenderer {

    private View view;
    private Canvas canvas;
    

    /**
     * Constructs an instance using the provided {@link View} 
     *
     */
    public ViewRenderer(View view) {
        this.view = view;
        this.canvas = new Canvas(this.view.frame.size);
    }

    /**
     * Updates {@link View}
     */
    public void changeView(View view) {
        this.view = view;
        this.render();
    }

    /**
     * Renders target {@link #view}
     */
    public void render() {

        this.canvas.clear();

    }


}
