package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.ViewBoard;

import java.util.ArrayList;
import java.util.List;

import static com.arainko.xno.model.predicates.ConnectionPredicates.containingCell;
import static com.arainko.xno.model.predicates.ConnectionPredicates.upToWinCondition;

public class MoveKeeper {
    private class Move {
        private List<Cell> cellList;
        private Operation opType;
        public Move(List<Cell> cellList, Operation opType) {
            this.cellList = cellList;
            this.opType = opType;
        }

        public List<Cell> getCellList() {
            return cellList;
        }

        public Operation getOpType() {
            return opType;
        }

        public void setOperationType(Operation opType) {
            this.opType = opType;
        }
    }
    public enum Operation {
        REMOVE, BUILD
    }
    private ViewBoard viewBoard;
    private ModelBoard modelBoard;
    private List<Move> keptConnectionCells;
    private int currentIndex;

    public MoveKeeper(GameRunningState parentGameState) {
        this.viewBoard = parentGameState.getGameController().getViewBoard();
        this.modelBoard = parentGameState.getGameController().getModelBoard();
        this.keptConnectionCells = new ArrayList<>();
        this.currentIndex = -1;
    }

    public void keepMove(Connection connection, Operation opType) {
        keptConnectionCells.add(new Move(connection.getConnectionCells(), opType));
        currentIndex++;
        System.out.println(currentIndex);
    }

    public void removeLastConnection() {
        Connection connectionToRemove =  modelBoard.getConnections().stream()
                .filter(containingCell(keptConnectionCells.get(currentIndex).get(0)))
                .findFirst()
                .get();

        viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connectionToRemove.getConnectionCells()), "default-button");
        modelBoard.removeConnection(connectionToRemove);
        currentIndex--;
        System.out.println(currentIndex);

    }

    public void rebuildLastConnection() {
       currentIndex++;
//        System.out.println(keptConnectionCells.get(currentIndex));
        Connection connection = new Connection(keptConnectionCells.get(currentIndex));
        if (!connection.isConnection(upToWinCondition())) viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connection.getConnectionCells()), "wrong-button");
        else viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connection.getConnectionCells()), "right-button");

        modelBoard.addConnection(connection);
        System.out.println(currentIndex);

    }



}
