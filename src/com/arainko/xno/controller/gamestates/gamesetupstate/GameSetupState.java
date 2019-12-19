package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import javafx.scene.control.Button;

public class GameSetupState implements GameState {
    private GameController gameController;

    public GameSetupState(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {

    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {

    }
}
