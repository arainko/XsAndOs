package com.arainko.xno.model.elements;

public class Cell extends com.arainko.xno.abstracts.Cell {
    public enum Content {
        CROSS, CIRCLE, EMPTY
    }
    private Content cellType;

    public Cell(int cordX, int cordY, Content content) {
        super(cordX, cordY);
        setCellType(content);
    }

    public void setCellType(Content content) {
        this.cellType = content;
    }

    public Content getCellType() {
        return cellType;
    }

    @Override
    public String toString() {
        switch (cellType) {
            case CROSS:
                return "X";
            case CIRCLE:
                return "O";
            default:
                return " ";
        }
    }
}
