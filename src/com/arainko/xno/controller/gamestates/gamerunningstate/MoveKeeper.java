package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.ViewBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.arainko.xno.model.predicates.ConnectionPredicates.*;

public class MoveKeeper {
    private class Move {
        private List<Cell> cellList;
        private Operation opType;

        private Move(List<Cell> cellList, Operation opType) {
            this.cellList = cellList;
            this.opType = opType;
        }

        private List<Cell> getCellList() {
            return cellList;
        }

        private Operation getOperationType() {
            return opType;
        }

        private void switchOperationType() {
            this.opType = opType.getOpposite();
        }
    }
    public enum MoveType {
        UNDO, REDO
    }
    public enum Operation {
        REMOVE, BUILD;
        public Operation getOpposite() {
            return this == REMOVE ? BUILD : REMOVE;
        }
    }

    private ViewBoard viewBoard;
    private ModelBoard modelBoard;
    private List<Move> keptMoves;
    private int currentIndex;

    public MoveKeeper(GameRunningState parentGameState) {
        this.viewBoard = parentGameState.getGameController().getViewBoard();
        this.modelBoard = parentGameState.getGameController().getModelBoard();
        this.keptMoves = new ArrayList<>();
        this.currentIndex = -1;
    }

    public void keepMove(Connection connection, Operation opType) {
        keptMoves.add(new Move(connection.getConnectionCells(), opType));
        currentIndex++;
        System.out.println(currentIndex);
    }

    public void deleteFurtherMoves() {
        this.keptMoves = keptMoves.stream()
                .limit(currentIndex+1)
                .collect(Collectors.toList());
    }

    public void evaluateMoveType(MoveType type) {
        if (type == MoveType.REDO)
            currentIndex++;

        Move move = keptMoves.get(currentIndex);

        if (move.getOperationType() == Operation.REMOVE)
            removeConnection(move.getCellList(), type);
        else
            rebuildConnection(move.getCellList(), type);

        move.switchOperationType();
    }

    private void removeConnection(List<Cell> connectionCells, MoveType type) {
        Connection connectionToRemove =  modelBoard.getConnections().stream()
                .filter(containingCell(connectionCells.get(0)))
                .findFirst()
                .get();

        viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connectionToRemove.getConnectionCells()), "default-button");
        modelBoard.removeConnection(connectionToRemove);
        moveTypeDelegator(type);
    }

    private void rebuildConnection(List<Cell> connectionCells, MoveType type) {
        Connection connection = new Connection(connectionCells);
        if (!connection.isConnection(upToWinCondition())) viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connection.getConnectionCells()), "wrong-button");
        else viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connection.getConnectionCells()), "right-button");

        modelBoard.addConnection(connection);
        moveTypeDelegator(type);
    }

    private void moveTypeDelegator(MoveType type) {
        if (type == MoveType.UNDO)
            currentIndex--;
    }

    public boolean areMoves(Predicate<MoveKeeper> pred) {
        return pred.test(this);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getKeptMovesSize() {
        return keptMoves.size();
    }
}
