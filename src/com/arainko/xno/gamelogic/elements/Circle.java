package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.abstracts.Element;

public class Circle extends Element {
    public Circle(int cordX, int cordY) {
        super(cordX, cordY);
    }

    @Override
    public String toString() {
        return "O";
    }
}
