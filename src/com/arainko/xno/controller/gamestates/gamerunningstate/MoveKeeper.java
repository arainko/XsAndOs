package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.arainko.xno.model.predicates.ConnectionPredicates.containingCell;

public class MoveKeeper {
    private class Move {
        private List<Cell> cellList;
        private Operation opType;

        private Move(List<Cell> cellList, Operation opType) {
            this.cellList = cellList;
            this.opType = opType.getOpposite();
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

        @Override
        public String toString() {
            return opType.toString();
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
        keptMoves.add(new Move(connection.getConnectionCells(), operation));
        currentIndex++;
        System.out.println(currentIndex);
    }

    public void deleteFurtherMoves() {
        this.keptMoves = keptMoves.stream()
                .limit(currentIndex+1)
                .collect(Collectors.toList());
        System.out.println(keptMoves);
        System.out.println(currentIndex);

    }

    public void evaluateCommand(Command command) {
        if (command == Command.REDO)
            currentIndex++;

        Move move = keptMoves.get(currentIndex);

        if (move.getOperationType() == Operation.REMOVE)
            removeConnection(move.getCellList(), command);
        else
            rebuildConnection(move.getCellList(), command);

        move.switchOperationType();
        System.out.println(currentIndex);

    }

    private void removeConnection(List<Cell> connectionCells, Command command) {
        Connection connectionToRemove = boardManipulator
                .getBoardConnection(containingCell(connectionCells.get(0)));
        boardManipulator.handleConnectionRemoval(connectionToRemove);
        updateCurrentIndexBasedOnCommand(command);
        System.out.println(currentIndex);

    }

    private void rebuildConnection(List<Cell> connectionCells, Command command) {
        Connection connectionToRebuild = new Connection(connectionCells);
        boardManipulator.handleConnectionBuilding(connectionToRebuild);
        updateCurrentIndexBasedOnCommand(command);
        System.out.println(currentIndex);

    }

    private void updateCurrentIndexBasedOnCommand(Command command) {
        if (command == Command.UNDO)
            currentIndex--;
        System.out.println(currentIndex);

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
