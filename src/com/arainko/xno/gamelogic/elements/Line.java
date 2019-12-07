package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.supers.Element;

public class Line extends Element {
    private enum Mode {
        HORIZONTAL,
        VERTICAL,
        JOINT,
        NOT_CONNECTED
    }

    private Mode mode;
    private Element elementContainer;

    public Line(int cordX, int cordY) {
        super(cordX, cordY);
        setMode(Mode.NOT_CONNECTED);
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public String toString() {
        String string = "L";
        switch (mode) {
            case JOINT:
                string = "J";
                break;
            case VERTICAL:
                string = "V";
                break;
            case HORIZONTAL:
                string = "H";
                break;
        }
        return string;
    }
}
