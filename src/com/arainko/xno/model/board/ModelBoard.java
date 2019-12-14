package com.arainko.xno.model.board;

import com.arainko.xno.abstracts.Board;
import com.arainko.xno.abstracts.Cell;
import com.arainko.xno.model.elements.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class ModelBoard extends Board<Cell> {
    List<Connection> connectionsOnBoard;
    public ModelBoard(int dimX, int dimY) {
        super(dimX, dimY);
    }

    @Override
    public void setBoardInitialState() {
        for (int i=0; i < getDimY(); i++) {
            List<Cell> row = new ArrayList<>();
            for (int j=0; j < getDimX(); j++)
                row.add(j, new com.arainko.xno.model.elements.Cell(i,j, com.arainko.xno.model.elements.Cell.Content.EMPTY));
            getBoardElements().add(row);
        }
    }

    public void replaceBoardElementWith(Cell cell) {
        int elementCordX = cell.getCordX();
        int elementCordY = cell.getCordY();
        if (elementCordX < getDimX() && elementCordY < getDimY()) {
            if (cell instanceof ConnectionUnit) {
                Cell cellToContain = getElementAt(elementCordX, elementCordY);
                ((ConnectionUnit) cell).setContainer(cellToContain);
            }
            this.getBoardElements().get(elementCordY).set(elementCordX, cell);
        } else throw new IndexOutOfBoundsException("Element cords are out of bounds: cordX = " + elementCordX + ", cordY = " + elementCordY);
    }

    public boolean isElementAt(int cordX, int cordY, Predicate<Cell> pred) {
        if (cordX >= 0 && cordX < getDimX() && cordY >= 0 && cordY < getDimY()) {
            Cell cell = getElementAt(cordX, cordY);
            return pred.test(cell);
        } else return false;
    }

    public List<Cell> getFreeNeighborsOf(int cordX, int cordY) {
        List<Cell> neighborList = new ArrayList<>();
        Predicate<Cell> isCell = el -> el.;
        IntStream.of(-1, 1).forEach(i -> {
            if (isElementAt(cordX + i, cordY, isCell))
                neighborList.add(getElementAt(cordX+1, cordY));
            if (isElementAt(cordX, cordY + i, isCell))
                neighborList.add(getElementAt(cordX+1, cordY));
        });
        return neighborList;
    }

    private Cell getElementAt(int cordX, int cordY) {
        return getBoardElements().get(cordY).get(cordX);
    }

    public void setCircleAt(int cordX, int cordY) {
        replaceBoardElementWith(new Circle(cordX, cordY));
    }
    public void setCrossAt(int cordX, int cordY) {
        replaceBoardElementWith(new Cross(cordX, cordY));
    }

    public void setCellAt(int cordX, int cordY) {
        replaceBoardElementWith(new com.arainko.xno.model.elements.Cell(cordX, cordY));
    }

    public void setConnectionUnitAt(int cordX, int cordY, Connection parentConnection) {
        ConnectionUnit newConnectionUnit = new ConnectionUnit(cordX, cordY);
        replaceBoardElementWith(newConnectionUnit);
        parentConnection.addConnectionUnit(newConnectionUnit);
    }

    public void removeConnectionUnit(ConnectionUnit connectionUnit) {
        replaceBoardElementWith(connectionUnit.getContainer());
    }


    public void placeConnectionIfValid(Connection connection) {
        if (isConnectionValid(connection))
            placeConnection(connection);
    }

    public boolean isConnectionValid(Connection connection) {
        List<ConnectionUnit> units = connection.getConnectionUnits();
        for (List<Cell> row : getBoardElements())
            if (!Collections.disjoint(row, units))
                return false;
        return true;
    }

    public void placeConnection(Connection connection) {
        connection.getConnectionUnits().forEach(this::replaceBoardElementWith);
    }
}
