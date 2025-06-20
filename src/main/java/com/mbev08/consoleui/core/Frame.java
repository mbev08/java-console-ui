package com.mbev08.consoleui.core;

import java.util.ArrayList;

public class Frame {

    private View view;
    public Position position;
    public Size size;
    private Cell[][] cellMatrix;

    public Frame(View view) {
        this.view = view;
        this.position = view.position;
        this.size = view.size;
        this.cellMatrix = new Cell[size.height][size.width];

        for (int y = 0; y < size.height; y++) {
            for (int x = 0; x < size.width; x++) {
                this.cellMatrix[y][x] = new Cell(' ', null, null);
            }
        }
    }
    public Frame(View view, Position position, Size size) {
        this(view);
        this.position = position;
        this.size = size;
    }

    public void paint() {
        ready();
        compile(view.uiObjects);

        for (Cell[] row : cellMatrix) {
            for (Cell cell : row) {
                cell.paint();
            }
            System.out.println();
        }


    }

    private void ready() {
        for (Cell[] row : cellMatrix) {
            for (Cell cell : row) {
                cell.clear();
            }
        }
    }

    private void compile(ArrayList<UIObject> uiObjects) {
        for (UIObject uiObject : uiObjects) {
            char[][] objMatrix = uiObject.toCharMatrix();

            for (int y = uiObject.position.y; y <= uiObject.getEndingYPosition(); y++) {
                for ( int x = uiObject.position.x; x <= uiObject.getEndingXPosition(); x++) {
                    char objChar = objMatrix[y - uiObject.position.y][x - uiObject.position.x];
                    cellMatrix[y][x].update(objChar, uiObject.currentAppearance.bg, uiObject.currentAppearance.fg);
                }
            }
        }

        for (int y = 0; y < size.height; y++) {
            for ( int x = 0; x < size.width; x++) {
                if (cellMatrix[y][x].bg == null) {
                    cellMatrix[y][x].update(' ', view.appearance.bg, view.appearance.fg);
                }
            }
        }
    }

}
