package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.abstracts.Element;

public class Cell extends Element {
    public Cell(int cordX, int cordY) {
        super(cordX, cordY);
    }

    @Override
    public String toString() {
        return " ";
    }
}
