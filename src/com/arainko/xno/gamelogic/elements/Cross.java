package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.abstracts.Element;

public class Cross extends Element {
    public Cross(int cordX, int cordY) {
        super(cordX, cordY);
    }

    @Override
    public String toString() {
        return "X";
    }
}
