package com.arainko.xno.abstracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Board<T extends BoardElement> implements Serializable {
    public static class Cords extends Element {
        public Cords(int cordX, int cordY) {
            super(cordX, cordY);
        }

        public int X() {
            return this.getCordX();
        }
        public int Y() {
            return this.getCordY();
        }

        @Override
        public String toString() {
            return "("+X()+", "+Y()+")";
        }
    }
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

    public Cords getElementCords(T element) {
        return element.getCords();
    }

    public T getElementAt(Cords cords) {
        return getBoardElements().get(cords.Y()).get(cords.X());
    }

    public List<T> getElementsAt(List<Cords> cordsList) {
        return cordsList.stream()
                .map(this::getElementAt)
                .collect(Collectors.toList());
    }

    public List<Cords> getElementsCords(List<T> elementList) {
        return elementList.stream()
                .map(this::getElementCords)
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
}
