package com.arainko.xno.model.elements;

import com.arainko.xno.abstracts.Element;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Cell extends Element {
    public enum Contents {
        CROSS, CIRCLE, EMPTY
    }
    private boolean isPartOfConnection;
    private Contents cellContents;

    public Cell(int cordX, int cordY, Contents contents) {
        super(cordX, cordY);
        setCellContents(contents);
    }

    public void setCellContents(Contents contents) {
        this.cellContents = contents;
    }

    public Contents getCellContents() {
        return cellContents;
    }

    public boolean isCell(Cell that, BiPredicate<Cell, Cell> pred) {
        return pred.test(this, that);
    }

    public boolean isCell(Predicate<Cell> pred) {
        return pred.test(this);
    }

    @Override
    public String toString() {
        String cellString = "";
        switch (cellContents) {
            case CROSS:
                cellString = "X";
                break;
            case CIRCLE:
                cellString = "O";
                break;
            case EMPTY:
                cellString = " ";
        }
        if (isPartOfConnection)
            return "(" + cellString + ")";
        else return cellString;
    }
}
