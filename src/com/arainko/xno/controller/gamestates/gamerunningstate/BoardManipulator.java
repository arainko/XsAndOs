package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;

import java.util.function.Predicate;

import static com.arainko.xno.model.predicates.ConnectionPredicates.upToWinCondition;

public class BoardManipulator {
    private ModelBoard modelBoard;
    private ViewBoard viewBoard;

    public BoardManipulator(ModelBoard modelBoard, ViewBoard viewBoard) {
        this.modelBoard = modelBoard;
        this.viewBoard = viewBoard;
    }

    public Connection getBoardConnection(Predicate<Connection> pred) {
        return modelBoard.getConnections().stream()
                .filter(pred)
                .findFirst()
                .get();
    }

    public void handleConnectionBuilding(Connection connection) {
        if (!connection.isConnection(upToWinCondition()))
            viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connection.getConnectionCells()), "wrong-button");
        else
            viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connection.getConnectionCells()), "right-button");
        modelBoard.addConnection(connection);
    }

    public void handleConnectionRemoval(Connection connection) {
        viewBoard.setButtonsColorAtCords(
                Cords.getCordList(connection.getConnectionCells()), "default-button");
        modelBoard.removeConnection(connection);
    }

    public Button spoofButton() {
        return viewBoard.getFlattenedBoardElements().get(0);
    }
}
