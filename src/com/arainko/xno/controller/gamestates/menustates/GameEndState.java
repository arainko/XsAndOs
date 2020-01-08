package com.arainko.xno.controller.gamestates.menustates;

import com.arainko.xno.controller.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.StateManager;
import com.arainko.xno.view.buttons.MenuButton;
import com.arainko.xno.view.screens.EndGameScreen;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class GameEndState extends GameStateHandler {
    public GameEndState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        EndGameScreen endGameScreen = new EndGameScreen();
        getGameController().getUIWrapper().changeMainView(endGameScreen);
        getGameController().registerButtonsForGameState(endGameScreen.getButtonList());
    }

    @Override
    public <T extends Button> void onPrimaryClickHandler(T button) {
        MenuButton clickedButton = (MenuButton) button;
        switch (clickedButton.getFunctionality()) {
            case MAIN_MENU:
                getGameController().setCurrentGameState(StateManager.State.MAIN_MENU);
                break;
            case EXIT:
                Platform.exit();
                System.exit(0);
        }
    }
}
