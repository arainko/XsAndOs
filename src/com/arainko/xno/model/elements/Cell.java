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

    public Cell(int cordX, int cordY) {
        super(cordX, cordY);
        setCellContents(Contents.EMPTY);
    }

    public Cell(int cordX, int cordY, Contents contents) {
        super(cordX, cordY);
        setCellContents(contents);
    }

    public boolean isPartOfConnection() {
        return isPartOfConnection;
    }

    public void setCellContents(Contents contents) {
        this.cellContents = contents;
    }

    public Contents getCellContents() {
        return cellContents;
    }

    public boolean isCell(Predicate<Cell> pred) {
        return pred.test(this);
    }

    public boolean isCell(Cell that, BiPredicate<Cell, Cell> pred) {
        return pred.test(this, that);
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
                cellString = "E";
        }
        if (isPartOfConnection)
            return "(" + cellString + ")";
        else return cellString;
    }
}
