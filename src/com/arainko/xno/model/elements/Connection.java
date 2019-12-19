package com.arainko.xno.model.elements;

import com.arainko.xno.helpers.Cords;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static com.arainko.xno.model.predicates.CellPredicates.*;

public class Connection {
    public enum Type {
        LINE, JOINT, END, NONE
    }
    private List<Cell> connectionCells;
    private Map<Cell, Type> connectionTypes;

    public Connection() {
        connectionCells = new ArrayList<>();
        connectionTypes = new LinkedHashMap<>();
    }

    public boolean isConnection(Predicate<Connection> pred) {
        return pred.test(this);
    }

    public boolean isConnection(BiPredicate<Connection, Connection> pred, Connection that) {
        return pred.test(this, that);
    }

    public void addConnectionUnit(Cell cell) {
        if (cell.isCell(notPartOfConnection())){
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
            if (currCell.isCell(nextToOnPaneX(), nextCell) || currCell.isCell(nextToOnPaneY(), nextCell))
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
