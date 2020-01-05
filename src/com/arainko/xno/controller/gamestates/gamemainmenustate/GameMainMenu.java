package com.arainko.xno.controller.gamestates.gamemainmenustate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.interfaces.ArrowButtonHandler;
import com.arainko.xno.view.HelpScreen;
import com.arainko.xno.view.menus.MainMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameMainMenu extends GameStateHandler {
    MainMenu mainMenu;
    HelpScreen rulesHelpScreen;
    HelpScreen controlsHelpScreen;
    ArrowButtonHandler rulesScreenHandler;
    ArrowButtonHandler controlsScreenHandler;
    ArrowButtonHandler mainMenuHandler;
    public GameMainMenu(GameController gameController) {
        super(gameController);
        mainMenuHandler = this;
        rulesScreenHandler = getRulesScreenHandler();
        controlsScreenHandler = getControlsScreenHandler();
    }

    @Override
    public void onGameStateSet() {
        mainMenu = new MainMenu();
        rulesHelpScreen = new HelpScreen("RULES", HelpScreen.Type.RULES);
        controlsHelpScreen = new HelpScreen("CONTROLS", HelpScreen.Type.CONTROLS);
        getGameController().registerButtonsForGameState(mainMenu.getButtonList());
        getGameController().getUIWrapper().changeMainView(mainMenu);
    }

    @Override
    public <T extends Button> void onPrimaryClickHandler(T button) {
        MainMenu.MainMenuButton clickedButton = (MainMenu.MainMenuButton) button;
        switch (clickedButton.getButtonFunctionality()) {
            case CREATE:
                getGameController().setCurrentGameState(GameController.State.BOARD_SIZE);
                break;
            case LOAD:
                getGameController().setCurrentGameState(GameController.State.LOADER);
                break;
            case HELP:
                getGameController().getUIWrapper().changeMainView(rulesHelpScreen);
                getGameController().serCurrentArrowButtonComponent(rulesScreenHandler);
                break;
        }
    }

    private ArrowButtonHandler getRulesScreenHandler() {
        return new ArrowButtonHandler() {
            @Override
            public EventHandler<ActionEvent> getLeftButtonActionEvent() {
                return event -> {
                    getGameController().getUIWrapper().changeMainView(mainMenu);
                    getGameController().serCurrentArrowButtonComponent(mainMenuHandler);
                };
            }

            @Override
            public EventHandler<ActionEvent> getRightButtonActionEvent() {
                return event -> {
                    getGameController().getUIWrapper().changeMainView(controlsHelpScreen);
                    getGameController().serCurrentArrowButtonComponent(controlsScreenHandler);
                };
            }
        };
    }

    private ArrowButtonHandler getControlsScreenHandler() {
        return new ArrowButtonHandler() {
            @Override
            public EventHandler<ActionEvent> getLeftButtonActionEvent() {
                return event -> {
                    getGameController().getUIWrapper().changeMainView(rulesHelpScreen);
                    getGameController().serCurrentArrowButtonComponent(rulesScreenHandler);
                };
            }
        };
    }
}
