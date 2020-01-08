package com.arainko.xno.controller.gamestates.menustates;

import com.arainko.xno.controller.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.StateManager;
import com.arainko.xno.controller.interfaces.NavButtonHandler;
import com.arainko.xno.view.buttons.MenuButton;
import com.arainko.xno.view.screens.HelpScreen;
import com.arainko.xno.view.screens.MainMenuScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameMainMenu extends GameStateHandler {
    MainMenuScreen mainMenuScreen;
    HelpScreen rulesHelpScreen;
    HelpScreen controlsHelpScreen;
    NavButtonHandler rulesScreenHandler;
    NavButtonHandler controlsScreenHandler;
    NavButtonHandler mainMenuHandler;

    public GameMainMenu(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        mainMenuScreen = new MainMenuScreen();
        mainMenuHandler = this;
        rulesScreenHandler = getRulesScreenHandler();
        controlsScreenHandler = getControlsScreenHandler();
        rulesHelpScreen = new HelpScreen("RULES", HelpScreen.Type.RULES);
        controlsHelpScreen = new HelpScreen("CONTROLS", HelpScreen.Type.CONTROLS);
        getGameController().registerButtonsForGameState(mainMenuScreen.getButtonList());
        getGameController().getUIWrapper().changeMainView(mainMenuScreen);
    }

    @Override
    public <T extends Button> void onPrimaryClickHandler(T button) {
        MenuButton clickedButton = (MenuButton) button;
        switch (clickedButton.getFunctionality()) {
            case CREATE_BOARD:
                getGameController().setCurrentGameState(StateManager.State.BOARD_SIZE);
                break;
            case LOAD_BOARD:
                getGameController().setCurrentGameState(StateManager.State.LOADER);
                break;
            case HELP:
                getGameController().getUIWrapper().changeMainView(rulesHelpScreen);
                getGameController().setCurrentNavButtonHandler(rulesScreenHandler);
                break;
        }
    }

    private NavButtonHandler getRulesScreenHandler() {
        return new NavButtonHandler() {
            @Override
            public EventHandler<ActionEvent> getLeftButtonActionEvent() {
                return event -> {
                    getGameController().getUIWrapper().changeMainView(mainMenuScreen);
                    getGameController().setCurrentNavButtonHandler(mainMenuHandler);
                };
            }

            @Override
            public EventHandler<ActionEvent> getRightButtonActionEvent() {
                return event -> {
                    getGameController().getUIWrapper().changeMainView(controlsHelpScreen);
                    getGameController().setCurrentNavButtonHandler(controlsScreenHandler);
                };
            }
        };

    }

    private NavButtonHandler getControlsScreenHandler() {
        return new NavButtonHandler() {
            @Override
            public EventHandler<ActionEvent> getLeftButtonActionEvent() {
                return event -> {
                    getGameController().getUIWrapper().changeMainView(rulesHelpScreen);
                    getGameController().setCurrentNavButtonHandler(rulesScreenHandler);
                };
            }
        };
    }
}
