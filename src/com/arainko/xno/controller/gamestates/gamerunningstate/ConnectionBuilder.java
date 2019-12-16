package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import javafx.scene.control.Button;

import java.util.List;

public class ConnectionBuilder implements InternalGameState {

    private GameRunningState parentGameState;

    List<Cords> lastClickedButtonNeighbors;

    public ConnectionBuilder(GameRunningState parentGameState) {
        this.parentGameState = parentGameState;
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
                crossClickedHandler(clickedCell);
                break;
            case CIRCLE:
                circleClickedHandler(clickedCell);
                break;
            case EMPTY:
                emptyClickedHandler(clickedCell);
                break;
        }
    }

    private void emptyClickedHandler(Cell clickedCell) {

    }

    private void circleClickedHandler(Cell clickedCell) {

    }

    private void crossClickedHandler(Cell clickedCell) {
    }



}
