package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import javafx.scene.control.Button;

public class GameSetupState implements GameState {
    private GameController gameController;

    InternalGameState boardSizeSetupState;
    InternalGameState circleAdder;
    InternalGameState crossAdder;
    InternalGameState currentInternalGameState;

    public GameSetupState(GameController gameController) {
        this.gameController = gameController;
        boardSizeSetupState = new BoardSizeSetupState(this);
        circleAdder = new CircleAdder(this);
        crossAdder = new CrossAdder(this);
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        if (currentInternalGameState != boardSizeSetupState)
            crossAdder.onInternalGameStateClickHandler(button);
    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
        if (currentInternalGameState != boardSizeSetupState)
            circleAdder.onInternalGameStateClickHandler(button);
    }

    public void setCurrentInternalGameState() {
        currentInternalGameState = circleAdder;
    }

    @Override
    public GameController getGameController() {
        return gameController;
    }
}
