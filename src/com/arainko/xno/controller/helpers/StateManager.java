package com.arainko.xno.controller.helpers;

import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.gamestates.GameRunningState;
import com.arainko.xno.controller.gamestates.GameXOPlacingState;
import com.arainko.xno.controller.gamestates.GameBoardSizeSetupState;
import com.arainko.xno.controller.gamestates.GameEndState;
import com.arainko.xno.controller.gamestates.GameLoaderState;
import com.arainko.xno.controller.gamestates.GameMainMenu;
import com.arainko.xno.controller.interfaces.GameState;

/* StateManager is GameController's data class that consists solely
* of all the possible game states while also providing a getter interface
* to retrieve a specific GameState. */

public class StateManager {
    public enum State {
        MAIN_MENU, LOADER, BOARD_SIZE, XO_PLACING, GAME_RUNNING, END
    }

    private GameController gameController;

    GameState gameMainMenuState;
    GameState gameLoaderState;
    GameState gameBoardSizeSetupState;
    GameState gameSetupState;
    GameState gameRunningState;
    GameState gameEndState;

    public StateManager(GameController gameController) {
        this.gameController = gameController;
        this.gameMainMenuState = new GameMainMenu(gameController);
        this.gameLoaderState = new GameLoaderState(gameController);
        this.gameBoardSizeSetupState = new GameBoardSizeSetupState(gameController);
        this.gameRunningState = new GameRunningState(gameController);
        this.gameSetupState = new GameXOPlacingState(gameController);
        this.gameEndState = new GameEndState(gameController);
    }

    public GameState getState(State gameState) {
        switch (gameState) {
            case MAIN_MENU:
                return gameMainMenuState;
            case BOARD_SIZE:
                return gameBoardSizeSetupState;
            case XO_PLACING:
                return gameSetupState;
            case GAME_RUNNING:
                return gameRunningState;
            case LOADER:
                return gameLoaderState;
            case END:
                return gameEndState;
            default:
                return gameController.getCurrentGameState();
        }
    }
}
