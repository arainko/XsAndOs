package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import javafx.scene.control.Button;

import java.util.List;

public class ConnectionBuilder implements InternalGameState {

    private GameRunningState parentGameState;

    List<Cords> lastClickedButtonNeighbors;
    Connection connection;

    public ConnectionBuilder(GameRunningState parentGameState) {
        this.parentGameState = parentGameState;
        this.connection = new Connection();
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {
        Cords clickedButtonCords = parentGameState.getGameController()
                .getViewBoard()
                .getButtonCords(button);

        Cell clickedCell = parentGameState.getGameController()
                .getModelBoard()
                .getCellAt(clickedButtonCords);

        switch (clickedCell.getCellContents()) {
            case CROSS:
                crossClickedHandler(clickedCell, button);
                break;
            case CIRCLE:
                circleClickedHandler(clickedCell, button);
                break;
            case EMPTY:
                emptyClickedHandler(clickedCell, button);
                break;
        }
    }

    private void emptyClickedHandler(Cell clickedCell, Button clickedButton) {
        clickedButton.setId("clicked-button");
    }

    private void circleClickedHandler(Cell clickedCell, Button clickedButton) {

    }

    private void crossClickedHandler(Cell clickedCell, Button clickedButton) {
    }



}
