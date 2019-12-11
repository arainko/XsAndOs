package com.arainko.xno.model.board;

import com.arainko.xno.model.abstracts.Element;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.model.elements.ConnectionUnit;

import java.util.ArrayList;
import java.util.Collections;
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
        else throw new IllegalStateException("Negative or zero value: dimX = " + dimX);
    }

    private void setDimY(int dimY) {
        if (dimY > 0)
            this.dimY = dimY;
        else throw new IllegalStateException("Negative or zero value: dimY = " + dimY);
    }

    private void setBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < this.dimY; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < this.dimX; j++)
                board.get(i).add(new Cell(i, j));
        }
    }

    public void placeConnectionIfValid(Connection connection) {
        if (isConnectionValid(connection))
            placeConnection(connection);
    }

    public boolean isConnectionValid(Connection connection) {
        List<ConnectionUnit> units = connection.getConnectionUnits();
        for (List<Element> row : board)
            if (!Collections.disjoint(row, units))
                return false;
        return true;
    }

    public void placeConnection(Connection connection) {
        for (ConnectionUnit unit : connection.getConnectionUnits())
            replaceBoardElement(unit);
    }

    public void replaceBoardElement(Element element) {
        int elementCordX = element.getCordX();
        int elementCordY = element.getCordY();
        if (elementCordX < dimX && elementCordY < dimY) {
            if (element instanceof ConnectionUnit) {
                Element elementToContain = board.get(elementCordY).get(elementCordX);
                ((ConnectionUnit) element).setContainer(elementToContain);
            } this.board.get(elementCordY).set(elementCordX, element);
        } else throw new IndexOutOfBoundsException("Element cords are out of bounds: cordX = " + elementCordX + ", cordY = " + elementCordY);
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
