package com.arainko.xno.gamelogic.board;

import com.arainko.xno.gamelogic.abstracts.Element;
import com.arainko.xno.gamelogic.elements.Cell;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimX, dimY;
    private List<List<Element>> board;

    public Board(int dimX, int dimY) {
        this.setDimX(dimX);
        this.setDimY(dimY);
        this.setBoard();
    }

    private void setDimX(int dimX) {
        if (dimX > 0)
            this.dimX = dimX;
        else throw new IllegalStateException("Negative value: dimX = " + dimX);
    }

    private void setDimY(int dimY) {
        if (dimY > 0)
            this.dimY = dimY;
        else throw new IllegalStateException("Negative value: dimY = " + dimY);
    }

    private void setBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < this.dimY; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < this.dimX; j++)
                board.get(i).add(new Cell(i, j));
        }
    }

    public void replaceBoardElement(Element element) {
        int elementCordX = element.getCordX();
        int elementCordY = element.getCordY();
        if (elementCordX < dimX && elementCordY < dimY)
            this.board.get(elementCordY).set(elementCordX, element);
        else throw new IndexOutOfBoundsException("Element's cords are out of bounds: cordX = " + elementCordX + ", cordY = " + elementCordY);
    }

    public int getDimX() {
        return this.dimX;
    }

    public int getDimY() {
        return this.dimY;
    }

    public List<List<Element>> getBoard() {
        return board;
    }

    public void printBoard() {
        for (List<Element> row : board)
            System.out.println(row);
    }


}
