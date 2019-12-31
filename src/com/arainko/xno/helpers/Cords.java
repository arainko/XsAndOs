package com.arainko.xno.helpers;

import com.arainko.xno.abstracts.Element;
import com.arainko.xno.model.elements.Cell;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Function<Cell, Cords> toCords = cell -> new Cords(cell.getCordX(), cell.getCordY());
        return cellList.stream()
                .map(cell -> cell.convertCell(toCords))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "("+X()+", "+Y()+")";
    }
}
