package com.arainko.xno.controller.abstracts;

import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.interfaces.GameState;

/* GameStateHandlers handle (as their name implies) game states, that is:
* - they're responsible for the current screen and whatever buttons are currently displayed on it
* - they handle primary and secondary clicks on a given screen's buttons
* - they're responsible for NavButtons and whatever action they are given to execute
* - they monitor when should the control be given to a different GameStateHandler */

public abstract class GameStateHandler implements GameState {
    private GameController gameController;

    public GameStateHandler(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getGameController() {
        return gameController;
    }
}
