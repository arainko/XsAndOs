package com.arainko.xno.model.elements;

import com.arainko.xno.abstracts.Element;
import com.arainko.xno.helpers.Cords;

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

    public void setConnectionFlag(boolean partOfConnection) {
        isPartOfConnection = partOfConnection;
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

    public boolean isPartOfConnection() {
        return isPartOfConnection;
    }

    public Contents getCellContents() {
        return cellContents;
    }

    public Cords getCellCords() {
        return new Cords(this.getCordX(), this.getCordY());
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
