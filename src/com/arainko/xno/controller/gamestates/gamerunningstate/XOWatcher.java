package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.helpers.Cords;
import javafx.scene.control.Button;

import static com.arainko.xno.model.predicates.CellPredicates.containingCircle;
import static com.arainko.xno.model.predicates.CellPredicates.containingCross;

public class XOWatcher implements InternalGameState {
    private GameRunningState parentGameState;

    public XOWatcher(GameRunningState parentGameState) {
        this.parentGameState = parentGameState;
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {
        Cords clickedButtonCords = parentGameState.getGameController()
            .getViewBoard()
            .getButtonCords(button);

        boolean isXO = parentGameState.getGameController()
                .getModelBoard()
                .getCellAt(clickedButtonCords)
                .isCell(containingCircle().or(containingCross()));

        if (isXO) {
            button.setId("clicked-button");
            parentGameState.setCurrentInternalGameState(parentGameState.getConnectionBuilder());
            parentGameState.getCurrentInternalGameState().onInternalGameStateClickHandler(button);
        }
    }
}
