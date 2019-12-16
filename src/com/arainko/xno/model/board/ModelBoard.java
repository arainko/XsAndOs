package com.arainko.xno.model.board;

import com.arainko.xno.helpers.Cords;
import com.arainko.xno.abstracts.Board;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static com.arainko.xno.model.predicates.BoardPredicates.ableToAccommodateCords;
import static com.arainko.xno.model.predicates.CellPredicates.containingNothing;
import static com.arainko.xno.model.predicates.CellPredicates.notPartOfConnection;
import static com.arainko.xno.model.predicates.ConnectionPredicates.interferingWith;

public class ModelBoard extends Board<Cell> {
    private List<Connection> connections;
    public ModelBoard(int dimX, int dimY) {
        super(dimX, dimY);
    }

    @Override
    public void setBoardInitialState() {
        for (int i=0; i < getDimY(); i++) {
            List<Cell> row = new ArrayList<>();
            for (int j=0; j < getDimX(); j++)
                row.add(j, new Cell(j, i));
            getBoardElements().add(row);
        }
    }

    public boolean isBoard(Predicate<ModelBoard> pred) {
        return pred.test(this);
    }

    public void setBoardCellContentsAt(int cordX, int cordY, Cell.Contents contents) {
        if (isBoard(ableToAccommodateCords(cordX, cordY)))
            getCellAt(cordX, cordY).setCellContents(contents);
    }

    public Cell getCellAt(int cordX, int cordY) {
            return getBoardElements().get(cordY).get(cordX);
    }

    public Cell getCellAt(Cords cords) {
        return getBoardElements().get(cords.Y()).get(cords.X());
    }

    public List<Cords> getFreeNeighborsAt(Cords cords) {
        List<Cords> neighborList = new ArrayList<>();
        for (int i : new int[]{-1, 1}) {
            if (isBoard(ableToAccommodateCords(cords.X() + i, cords.Y()))
                    && getCellAt(cords.X() + i, cords.Y()).isCell(notPartOfConnection()))
                neighborList.add(new Cords(cords.X() + i, cords.Y()));
            if (isBoard(ableToAccommodateCords(cords.X(), cords.Y() + i))
                    && getCellAt(cords.X(), cords.Y() + i).isCell(notPartOfConnection()))
                neighborList.add(new Cords(cords.X(), cords.Y() + i));
        }
        return neighborList;
    }

    private boolean isConnectionValid(Connection connection) {
        return connections.stream().noneMatch(conn -> conn.isConnection(interferingWith(), connection));
    }

    public void placeConnection(Connection connection) {
        connection.getConnectionCells().forEach( cell -> cell.setConnectionFlag(true));
    }
}
