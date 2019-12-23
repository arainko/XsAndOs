package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import javafx.scene.control.Button;

public class GameSetupState implements GameState {
    private GameController gameController;
    boolean isBoardSizeSetupDone;

    InternalGameState boardSizeSetupState;
    InternalGameState circleAdder;
    InternalGameState crossAdder;


    public GameSetupState(GameController gameController) {
        this.gameController = gameController;
        boardSizeSetupState = new BoardSizeSetupState(this);
        circleAdder = new CircleAdder(this);
        crossAdder = new CrossAdder(this);
        isBoardSizeSetupDone = false;
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        if (isBoardSizeSetupDone)
            crossAdder.onInternalGameStateClickHandler(button);
    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
        if (isBoardSizeSetupDone)
            circleAdder.onInternalGameStateClickHandler(button);
    }

    public void setBoardSizeSetupDone(boolean boardSizeSetupDone) {
        isBoardSizeSetupDone = boardSizeSetupDone;
    }

    @Override
    public GameController getGameController() {
        return gameController;
    }
}
