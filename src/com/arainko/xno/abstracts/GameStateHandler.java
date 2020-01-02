package com.arainko.xno.abstracts;

import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.interfaces.GameState;

public abstract class GameStateHandler implements GameState {
    private GameController gameController;

    public GameStateHandler(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getGameController() {
        return gameController;
    }
}
