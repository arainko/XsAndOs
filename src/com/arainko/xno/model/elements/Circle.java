package com.arainko.xno.model.elements;

import com.arainko.xno.abstracts.Element;

public class Circle extends Element {
    public Circle(int cordX, int cordY) {
        super(cordX, cordY);
    }

    @Override
    public String toString() {
        return "O";
    }
}
