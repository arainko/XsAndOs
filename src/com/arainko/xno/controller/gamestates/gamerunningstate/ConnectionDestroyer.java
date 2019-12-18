package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;

public class ConnectionDestroyer implements InternalGameState {

    private GameRunningState parentGameState;
    private ModelBoard modelBoard;
    private ViewBoard viewBoard;

    public ConnectionDestroyer(GameRunningState parentGameState) {
        this.parentGameState = parentGameState;
        this.modelBoard = parentGameState.getGameController().getModelBoard();
        this.viewBoard = parentGameState.getGameController().getViewBoard();
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {
        button.setId("wrong-button");
    }
}
