package com.arainko.xno.model.elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.arainko.xno.model.predicates.CellPredicates.*;

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
        for (int i = 1; i < connectionCells.size()-1; i++) {
            Cell currCell = connectionCells.get(i);
            Cell nextCell = connectionCells.get(i+1);
            if (currCell.isCell(nextCell, nextToOnPaneX()) || currCell.isCell(nextCell, nextToOnPaneY()))
                connectionTypes.put(currCell, Type.LINE);
        }
    }

    private void calculateJoints() {
        if (connectionCells.size() >= 3)
            for (int i = 1; i + 1 < connectionCells.size(); i++) {
                Cell lastCell = connectionCells.get(i-1);
                Cell currCell = connectionCells.get(i);
                Cell nextCell = connectionCells.get(i+1);
                if (lastCell.isCell(nextCell, connectedByJoint()) && connectionTypes.get(currCell) != Type.NONE)
                    connectionTypes.put(currCell, Type.JOINT);
            }
    }

    private void calculateEnds() {
        int size = connectionCells.size();
        if (size > 1)
            IntStream.of(0, size-1).forEach( i -> {
                if (connectionCells.get(i).isCell(containingCross().or(containingCircle())))
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
                if (cell.isCell(containingCircle()))
                    circleCount++;
                else if (cell.isCell(containingCross()))
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
