package com.arainko.xno.model.elements;

import com.arainko.xno.abstracts.Element;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Cell extends Element {
    public enum Contents {
        CROSS, CIRCLE, EMPTY
    }
    private boolean connectionFlag;
    private Contents cellContents;

    public Cell(int cordX, int cordY, Contents contents) {
        super(cordX, cordY);
        setCellContents(contents);
    }

    public void setConnectionFlag(boolean connectionFlag) {
        this.connectionFlag = connectionFlag;
    }

    public void setCellContents(Contents contents) {
        this.cellContents = contents;
    }

    public boolean isCell(Predicate<Cell> pred) {
        return pred.test(this);
    }

    public boolean isCell(BiPredicate<Cell, Cell> pred, Cell that) {
        return pred.test(this, that);
    }

    public boolean getConnectionFlag() {
        return connectionFlag;
    }

    public Contents getCellContents() {
        return cellContents;
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
        if (connectionFlag)
            return "(" + cellString + ")";
        else return cellString;
    }
}
