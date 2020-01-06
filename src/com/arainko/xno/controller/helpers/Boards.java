package com.arainko.xno.controller.helpers;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.buttons.BoardButton;
import com.arainko.xno.view.board.ViewBoard;

import java.util.List;

import static com.arainko.xno.abstracts.Board.Cords;
import static com.arainko.xno.model.predicates.ConnectionPredicates.upToWinCondition;

public class Boards {
    public static void handleConnectionBuilding(ModelBoard modelBoard, ViewBoard viewBoard, Connection connection) {
        lightUpConnectionCords(modelBoard, viewBoard, connection);
        modelBoard.addConnection(connection);
    }

    public static void handleConnectionRemoval(ModelBoard modelBoard, ViewBoard viewBoard, Connection connection) {
        List<Cords> connectionCords = modelBoard.getElementsCords(connection.getConnectionCells());
        viewBoard.setButtonsColorAtCords(connectionCords, "default-button");
        modelBoard.removeConnection(connection);
    }

    public static ViewBoard rebuildBoard(ModelBoard modelBoard) {
        ViewBoard viewBoard = new ViewBoard(modelBoard.getDimX(), modelBoard.getDimY());
        modelBoard.getConnections().forEach(connection -> lightUpConnectionCords(modelBoard, viewBoard, connection));
        refreshBoardText(modelBoard, viewBoard);
        return viewBoard;
    }

    public static void refreshBoardText(ModelBoard modelBoard, ViewBoard viewBoard) {
        List<Cell> flattenedCells = modelBoard.getFlattenedBoardElements();
        List<BoardButton> flattenedButtons = viewBoard.getFlattenedBoardElements();
        for (int i = 0; i < flattenedCells.size(); i++) {
            String cellStr = flattenedCells.get(i).toString();
            flattenedButtons.get(i).setText(cellStr);
        }
    }

    private static void lightUpConnectionCords(ModelBoard modelBoard, ViewBoard viewBoard, Connection connection) {
        List<Cords> connectionCords = modelBoard.getElementsCords(connection.getConnectionCells());
        if (!connection.isConnection(upToWinCondition())) {
            viewBoard.setButtonsColorAtCords(connectionCords, "wrong-button");
        } else {
            viewBoard.setButtonsColorAtCords(connectionCords, "right-button");
        }
    }
}
