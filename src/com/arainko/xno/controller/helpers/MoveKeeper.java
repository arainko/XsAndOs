package com.arainko.xno.controller.helpers;

import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.arainko.xno.abstracts.Board.Cords;
import static com.arainko.xno.model.predicates.ConnectionPredicates.containingCell;

public class MoveKeeper implements Serializable {
    public class Move implements Serializable {
        private List<Cords> cellCords;
        private Operation opType;

        private Move(List<Cords> cellCords, Operation opType) {
            this.cellCords = cellCords;
            this.opType = opType.getOpposite();
        }

        private List<Cords> getCellCords() {
            return cellCords;
        }

        private Operation getOperationType() {
            return opType;
        }

        private void switchOperationType() {
            this.opType = opType.getOpposite();
        }

        @Override
        public String toString() {
            return opType.toString() /*+":\n"+ cellCords.toString()+"\n"*/;
        }
    }
    public enum Command {
        UNDO, REDO
    }
    public enum Operation {
        REMOVE, BUILD;
        public Operation getOpposite() {
            return this == REMOVE ? BUILD : REMOVE;
        }
    }

    private BoardManipulator boardManipulator;
    private List<Move> keptMoves;
    private int currentIndex;

    public MoveKeeper(BoardManipulator boardManipulator) {
        this.boardManipulator = boardManipulator;
        this.keptMoves = new ArrayList<>();
        this.currentIndex = -1;
    }

    public void keepMove(Connection connection, Operation operation) {
        keptMoves.add(new Move(boardManipulator.getBoardCords(connection.getConnectionCells()), operation));
        currentIndex++;
    }

    public void deleteFurtherMoves() {
        if (currentIndex+1 != keptMoves.size()) {
            this.keptMoves = keptMoves.stream()
                    .limit(currentIndex+1)
                    .collect(Collectors.toList());
        }
    }

    public void evaluateCommand(Command command) {
        Move move = command == Command.UNDO ? getMove(currentIndex) : getMove(currentIndex + 1);
        operationTypeDelegator(move, move.getOperationType());
        currentIndexUpdater(command);
        move.switchOperationType();
    }

    private void operationTypeDelegator(Move move, Operation operation) {
        switch (operation) {
            case REMOVE:
                removeConnection(move);
                break;
            case BUILD:
                rebuildConnection(move);
                break;
        }
    }

    private void removeConnection(Move move) {
        List<Cell> cells = boardManipulator
                .getBoardCells(move.getCellCords());
        Connection connectionToRemove = boardManipulator
                .getBoardConnection(containingCell(cells.get(0)));
        boardManipulator.handleConnectionRemoval(connectionToRemove);
    }

    private void rebuildConnection(Move move) {
        List<Cell> cells = boardManipulator
                .getBoardCells(move.getCellCords());
        Connection connectionToRebuild = new Connection(cells);
        boardManipulator.handleConnectionBuilding(connectionToRebuild);
    }

    private void currentIndexUpdater(Command command) {
        if (command == Command.REDO)
            currentIndex++;
        else currentIndex--;
    }

    public boolean keeperPred(Predicate<MoveKeeper> pred) {
        return !pred.test(this);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getKeptMovesSize() {
        return keptMoves.size();
    }

    public List<Move> getKeptMoves() {
        return keptMoves;
    }

    public void setKeptMoves(List<Move> moves) {
        this.keptMoves = moves;
    }

    public void setCurrentIndex(int index) {

    }

    private Move getMove(int index) {
        return keptMoves.get(index);
    }
}
