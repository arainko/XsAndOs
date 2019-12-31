package com.arainko.xno.abstracts;

import com.arainko.xno.helpers.Cords;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Board<T> implements Serializable {
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

    public T getElementAt(Cords cords) {
        return getBoardElements().get(cords.Y()).get(cords.X());
    }

    public List<T> getElementsAt(List<Cords> cordsList) {
        return cordsList.stream()
                .map(this::getElementAt)
                .collect(Collectors.toList());
    }

    public List<List<T>> getBoardElements() {
        return boardElements;
    }

    public List<T> getFlattenedBoardElements() {
        return getBoardElements().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public void printBoard() {
        for (List<T> row : boardElements)
            System.out.println(row);
    }
}
