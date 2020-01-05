package com.arainko.xno.controller.gamestates.gamemainmenustate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.view.HelpScreen;
import com.arainko.xno.view.menus.MainMenu;
import javafx.scene.control.Button;

public class GameMainMenu extends GameStateHandler {
    boolean isOnHelpScreen;
    MainMenu mainMenu;
    HelpScreen helpScreen;
    public GameMainMenu(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        isOnHelpScreen = false;
        mainMenu = new MainMenu();
        helpScreen = new HelpScreen();
        getGameController().registerButtonsForGameState(mainMenu.getButtonList());
        getGameController().getUIWrapper().changeMainView(mainMenu);
        arrowButtonsSupervisor();
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
                getGameController().getUIWrapper().changeMainView(helpScreen);
                isOnHelpScreen = true;
                arrowButtonsSupervisor();
                break;
        }
    }

    private void arrowButtonsSupervisor() {
        getGameController().getUIWrapper().getLeftButton().setOnActionEnhanced( event -> {
            if (isOnHelpScreen) {
                getGameController().getUIWrapper().changeMainView(mainMenu);
                isOnHelpScreen = false;
            } else getGameController().getUIWrapper().getLeftButton().setVisible(false);
        });
    }

}
