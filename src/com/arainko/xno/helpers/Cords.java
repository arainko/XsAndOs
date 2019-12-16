package com.arainko.xno.helpers;

import com.arainko.xno.abstracts.Element;

public class Cords extends Element {
    public Cords(int cordX, int cordY) {
        super(cordX, cordY);
    }

    public int X() {
        return this.getCordX();
    }
    public int Y() {
        return this.getCordY();
    }


}
