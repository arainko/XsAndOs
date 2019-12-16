package com.arainko.xno.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class Board<T> {
    private int dimX;
    private int dimY;
    private List<List<T>> boardElements;

    public Board(int dimX, int dimY) {
        setDimX(dimX);
        setDimY(dimY);
        boardElements = new ArrayList<>();
        setBoardInitialState();
    }

    private void setDimX(int dimX) {
        if (dimX > 0)
            this.dimX = dimX;
        else throw new IllegalStateException("Negative or zero value: dimX = " + dimX);
    }

    private void setDimY(int dimY) {
        if (dimY > 0)
            this.dimY = dimY;
        else throw new IllegalStateException("Negative or zero value: dimY = " + dimY);
    }

    public abstract void setBoardInitialState();

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public List<List<T>> getBoardElements() {
        return boardElements;
    }
    
    public void printBoard() {
        for (List<T> row : boardElements)
            System.out.println(row);
    }
}
