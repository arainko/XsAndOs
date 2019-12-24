package com.arainko.xno.abstracts;

import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.GameState;

public abstract class GameStateHandler implements GameState {
    private GameController gameController;

    public GameStateHandler(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getGameController() {
        return gameController;
    }
}
