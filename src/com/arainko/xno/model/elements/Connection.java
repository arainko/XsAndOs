package com.arainko.xno.model.elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Connection {
    private enum Type {
        LINE, JOINT, END, NONE
    }
    private List<Cell> connectionCells;
    private Map<Cell, Type> connectionTypes;

    public Connection() {
        connectionCells = new ArrayList<>();
        connectionTypes = new LinkedHashMap<>();
    }

    public void addConnectionUnit(Cell cell) {
        connectionCells.add(cell);
        connectionTypes.put(cell, Type.NONE);
    }

    public void setConnectionTypes() {
        calculateConnections();
        calculateJoints();
        calculateEnds();
    }

    private void calculateConnections() {
        BiPredicate<Cell, Cell> nextToOnPaneY = (c1, c2) ->
                Math.abs(c1.getCordY()-c2.getCordY()) == 1 && c1.getCordX()-c2.getCordX() == 0;
        BiPredicate<Cell, Cell> nextToOnPaneX = (c1, c2) ->
                Math.abs(c1.getCordX()-c2.getCordX()) == 1 && c1.getCordY()-c2.getCordY() == 0;
        for (int i = 1; i < connectionCells.size()-1; i++) {
            Cell currCell = connectionCells.get(i);
            Cell nextCell = connectionCells.get(i+1);
            if (currCell.isCell(nextCell, nextToOnPaneX) || currCell.isCell(nextCell, nextToOnPaneY))
                connectionTypes.put(currCell, Type.LINE);
        }
    }

    private void calculateJoints() {
        BiPredicate<Cell, Cell> connectedByJoint = (c1, c2) ->
                c1.getCordX() != c2.getCordX() && c1.getCordY() != c2.getCordY();
        if (connectionCells.size() >= 3)
            for (int i = 1; i + 1 < connectionCells.size(); i++) {
                Cell lastCell = connectionCells.get(i-1);
                Cell currCell = connectionCells.get(i);
                Cell nextCell = connectionCells.get(i+1);
                if (lastCell.isCell(nextCell, connectedByJoint) && connectionTypes.get(currCell) != Type.NONE)
                    connectionTypes.put(currCell, Type.JOINT);
            }
    }

    private void calculateEnds() {
        Predicate<Cell> containsCrossOrCircle = c ->
                c.getCellContents() == Cell.Contents.CROSS || c.getCellContents() == Cell.Contents.CIRCLE;
        int size = connectionCells.size();
        if (size > 1)
            IntStream.of(0, size-1).forEach( i -> {
                if (connectionCells.get(i).isCell(containsCrossOrCircle))
                    connectionTypes.put(connectionCells.get(i), Type.END);
            });
    }

    public boolean isConnectionUpToWinCondition() {
        int jointCount = 0;
        int crossCount = 0;
        int circleCount  = 0;

        for (Cell cell : connectionTypes.keySet()) {
            if (connectionTypes.get(cell) == Type.JOINT)
                jointCount++;
            if (connectionTypes.get(cell) == Type.END)
                if (cell.getCellContents() == Cell.Contents.CIRCLE)
                    circleCount++;
                else if (cell.getCellContents() == Cell.Contents.CROSS)
                    crossCount++;
        }
        return jointCount == 1 && circleCount == 1 && crossCount == 1;
    }

    public List<Cell> getConnectionCells() {
        return this.connectionCells;
    }

    public void print() {
        for (Type cell : connectionTypes.values())
            System.out.println(cell);
    }
}
