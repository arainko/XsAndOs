package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.supers.Element;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimX, dimY;
    private List<List<Element>> board = new ArrayList<>();

    public Board(int dimX, int dimY) {
        this.setDimX(dimX);
        this.setDimY(dimY);
        this.setBoard();
    }

    private void setDimX(int dimX) {
        if (dimX > 0)
            this.dimX = dimX;
        else throw new IllegalStateException("Unexpected value: dimX = " + dimX);
    }

    private void setDimY(int dimY) {
        if (dimY > 0)
            this.dimY = dimY;
        else throw new IllegalStateException("Unexpected value: dimY = " + dimY);
    }

    private void setBoard() {
        for (int i = 0; i < this.dimY; i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < this.dimX; j++)
                this.board.get(i).add(new Element(i, j));
        }
    }

    public void setRowCol(int row, int col, Element element) {
        this.board.get(row).set(col, element);
    }

    public int getDimX() {
        return this.dimX;
    }

    public int getDimY() {
        return this.dimY;
    }


}
