package com.arainko.xno.model.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.arainko.xno.model.predicates.CellPredicates.*;
import static java.util.function.Predicate.not;

public class Connection implements Serializable {
    public enum Type {
        LINE, JOINT, END, NONE
    }
    private List<Cell> connectionCells;
    private Map<Cell, Type> connectionTypes;

    public Connection() {
        connectionCells = new ArrayList<>();
        connectionTypes = new LinkedHashMap<>();
    }

    public Connection(List<Cell> connectionUnits) {
        connectionCells = new ArrayList<>();
        connectionTypes = new LinkedHashMap<>();
        connectionUnits.forEach(this::addConnectionUnit);
        setConnectionTypes();
    }

    public boolean isConnection(Predicate<Connection> pred) {
        return pred.test(this);
    }

    public void addConnectionUnit(Cell cell) {
        if (cell.isCell(not(partOfConnection()))){
            connectionCells.add(cell);
            connectionTypes.put(cell, Type.NONE);
            cell.setConnectionFlag(true);
        }
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
            if (currCell.isCell(nextToOnPlaneX(), nextCell) || currCell.isCell(nextToOnPlaneY(), nextCell))
                connectionTypes.put(currCell, Type.LINE);
        }
    }

    private void calculateJoints() {
        if (connectionCells.size() >= 3)
            for (int i = 1; i + 1 < connectionCells.size(); i++) {
                Cell lastCell = connectionCells.get(i-1);
                Cell currCell = connectionCells.get(i);
                Cell nextCell = connectionCells.get(i+1);
                if (lastCell.isCell(connectedByJoint(), nextCell) && connectionTypes.get(currCell) != Type.NONE)
                    connectionTypes.put(currCell, Type.JOINT);
            }
    }

    private void calculateEnds() {
        int size = connectionCells.size();
        if (size > 1) {
            for (int i : new int[]{0, size-1})
                if (connectionCells.get(i).isCell(containingCross().or(containingCircle())))
                    connectionTypes.put(connectionCells.get(i), Type.END);
        }
    }

    public void remove() {
        connectionCells.forEach(cell -> cell.setConnectionFlag(false));
        connectionCells = new ArrayList<>();
        connectionTypes = new LinkedHashMap<>();
    }

    public List<Cell> getConnectionCells() {
        return this.connectionCells;
    }

    public Map<Cell, Type> getConnectionTypes() {
        return connectionTypes;
    }

    public void print() {
        for (Type cell : connectionTypes.values())
            System.out.println(cell);
    }
}
