package com.arainko.xno.model.elements;

import com.arainko.xno.model.abstracts.Element;

public class Cross extends Element {
    public Cross(int cordX, int cordY) {
        super(cordX, cordY);
    }

    @Override
    public String toString() {
        return "X";
    }
}
