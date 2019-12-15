package com.arainko.xno.model.board;

import com.arainko.xno.abstracts.Board;
import com.arainko.xno.model.elements.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static com.arainko.xno.model.predicates.BoardPredicates.cordsInBounds;
import static com.arainko.xno.model.predicates.CellPredicates.containingNothing;
import static com.arainko.xno.model.predicates.CellPredicates.notPartOfConnection;

public class ModelBoard extends Board<Cell> {
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

    public boolean isBoardCell(Cell boardCell, BiPredicate<ModelBoard, Cell> pred) {
        return pred.test(this, boardCell);
    }

    public void setBoardCellContents(Cell cell, Cell.Contents contents) {
        cell.setCellContents(contents);
    }

    public Cell getCellAt(int cordX, int cordY) {
        if (isBoard(cordsInBounds(cordX, cordY)))
            return getBoardElements().get(cordY).get(cordX);
        else throw new NoSuchElementException("No cell at: (" + cordX +", "+cordY+")");
    }

    public List<Cell> getFreeNeighborsAt(int cordX, int cordY) {
        List<Cell> neighborList = new ArrayList<>();
        for (int i : new int[]{-1, 1}) {
            if (isBoard(cordsInBounds(cordX + i, cordY))
                    && getCellAt(cordX + i, cordY).isCell(containingNothing().and(notPartOfConnection())))
                neighborList.add(getCellAt(cordX + i, cordY));
            if (isBoard(cordsInBounds(cordX, cordY + i))
                    && getCellAt(cordX, cordY + i).isCell(containingNothing().and(notPartOfConnection())))
                neighborList.add(getCellAt(cordX, cordY + i));
        }
        return neighborList;
    }

//
//    public void placeConnectionIfValid(Connection connection) {
//        if (isConnectionValid(connection))
//            placeConnection(connection);
//    }
//
//    public boolean isConnectionValid(Connection connection) {
//        List<ConnectionUnit> units = connection.getConnectionCells();
//        for (List<Element> row : getBoardElements())
//            if (!Collections.disjoint(row, units))
//                return false;
//        return true;
//    }
//
//    public void placeConnection(Connection connection) {
//        connection.getConnectionCells().forEach(this::replaceBoardElementWith);
//    }
}
