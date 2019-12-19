package com.arainko.xno.helpers;

import com.arainko.xno.abstracts.Element;
import com.arainko.xno.model.elements.Cell;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Cords> getCordList(List<Cell> cellList) {
        List<Cords> outputList = new ArrayList<>();
        cellList.forEach( cell -> outputList.add(cell.getCellCords()));
        return outputList;
    }
}
