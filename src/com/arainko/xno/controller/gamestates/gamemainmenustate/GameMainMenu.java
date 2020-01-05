package com.arainko.xno.controller.gamestates.gamemainmenustate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.view.HelpScreen;
import com.arainko.xno.view.menus.MainMenu;
import javafx.scene.control.Button;

public class GameMainMenu extends GameStateHandler {

    public GameMainMenu(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        MainMenu mainMenu = new MainMenu();
        getGameController().registerButtonsForGameState(mainMenu.getButtonList());
        getGameController().getUIWrapper().changeMainView(mainMenu);
    }

    @Override
    public <T extends Button> void onGameStatePrimaryClickHandler(T button) {
        MainMenu.MainMenuButton clickedButton = (MainMenu.MainMenuButton) button;
        switch (clickedButton.getButtonFunctionality()) {
            case CREATE:
                getGameController().setCurrentGameState(GameController.State.BOARD_SIZE);
                break;
            case LOAD:
                getGameController().setCurrentGameState(GameController.State.LOADER);
                break;
            case HELP:
                getGameController().getUIWrapper().changeMainView(new HelpScreen());
                break;
        }
    }
}
