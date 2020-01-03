package com.arainko.xno.controller.helpers;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.board.ViewBoard;
import javafx.scene.control.Button;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

import static com.arainko.xno.abstracts.Board.Cords;
import static com.arainko.xno.model.predicates.ConnectionPredicates.upToWinCondition;

public class BoardManipulator implements Serializable {
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
        List<Cords> connectionCords = getBoardCords(connection.getConnectionCells());
        if (!connection.isConnection(upToWinCondition())) {
            viewBoard.setButtonsColorAtCords(connectionCords, "wrong-button");
        } else {
            viewBoard.setButtonsColorAtCords(connectionCords, "right-button");
        }
        modelBoard.addConnection(connection);
    }

    public void handleConnectionRemoval(Connection connection) {
        List<Cords> connectionCords = getBoardCords(connection.getConnectionCells());
        viewBoard.setButtonsColorAtCords(connectionCords, "default-button");
        modelBoard.removeConnection(connection);
    }

    public Button spoofButton() {
        return viewBoard.getFlattenedBoardElements().get(0);
    }

    public List<Cell> getBoardCells(List<Cords> cords) {
        return modelBoard.getElementsAt(cords);
    }
    public List<Cords> getBoardCords(List<Cell> cells) { return modelBoard.getElementsCords(cells); }
}
