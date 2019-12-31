package com.arainko.xno.model.board;

import com.arainko.xno.abstracts.Board;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.arainko.xno.model.predicates.BoardPredicates.ableToAccommodateCords;
import static com.arainko.xno.model.predicates.CellPredicates.notPartOfConnection;

public class ModelBoard extends Board<Cell> {
    public List<Connection> connections;

    public ModelBoard(int dimX, int dimY) {
        super(dimX, dimY);
        connections = new ArrayList<>();
    }

    @Override
    public void setBoardInitialState() {
        for (int i=0; i < getDimY(); i++) {
            List<Cell> row = new ArrayList<>();
            for (int j=0; j < getDimX(); j++)
                row.add(j, new Cell(j, i, Cell.Contents.EMPTY));
            getBoardElements().add(row);
        }
    }

    public boolean isBoard(Predicate<ModelBoard> pred) {
        return pred.test(this);
    }

    public List<Cords> getFreeNeighborsAt(Cords cords) {
        List<Cords> neighborList = new ArrayList<>();
        IntStream.of(-1, 1).forEach(i -> {
            Cords paneXCords = new Cords(cords.X() + i, cords.Y());
            Cords paneYCords = new Cords(cords.X(), cords.Y() + i);
            if (isBoard(ableToAccommodateCords(paneXCords))
                    && getElementAt(paneXCords).isCell(notPartOfConnection()))
                neighborList.add(paneXCords);
            if (isBoard(ableToAccommodateCords(paneYCords))
                    && getElementAt(paneYCords).isCell(notPartOfConnection()))
                neighborList.add(paneYCords);
        });
        return neighborList;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public void removeConnection(Connection connection) {
        connections.remove(connection);
        connection.remove();
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
