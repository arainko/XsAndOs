package com.arainko.xno.controller.gamestates.gamemainmenustate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.view.menus.MainMenu;
import com.arainko.xno.view.menus.MenuButton;
import javafx.scene.control.Button;

public class GameMainMenu extends GameStateHandler {
    MainMenu mainMenu;

    public GameMainMenu(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        mainMenu = new MainMenu();
        getGameController().registerButtonsForGameState(mainMenu.getButtonList());
        getGameController().getUIWrapper().changeMainView(mainMenu);
    }

    @Override
    public <T extends Button> void onGameStatePrimaryClickHandler(T button) {
        MenuButton clickedButton = (MenuButton) button;
        switch (clickedButton.getButtonFunctionality()) {
            case CREATE:
                getGameController().setCurrentGameState(GameController.State.BOARD_SIZE);
                break;
            case LOAD:
                //TODO: Implement save file loader.
        }
    }
}
