package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.abstracts.CordPairElement;

public class Line extends CordPairElement {
    private enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    private Orientation orientation;
    private CordPairElement elementContainer;

    public Line(int cordX, int cordY, Orientation orientation) {
        super(cordX, cordY);
        setOrientation(orientation);
    }

    private void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
