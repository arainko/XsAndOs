package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import javafx.scene.control.Button;

public class GameSetupState extends GameStateHandler {
    boolean isBoardSizeSetupDone;

    InternalGameState boardSizeSetupState;
    InternalGameState circleAdder;
    InternalGameState crossAdder;

    public GameSetupState(GameController gameController) {
        super(gameController);
        boardSizeSetupState = new InternalBoardSizeSetupHandler(this);
        circleAdder = new InternalCircleAdder(this);
        crossAdder = new InternalCrossAdder(this);
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
}
