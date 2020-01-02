package com.arainko.xno.controller.gamestates.gamemainmenustate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.view.menus.SetupMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameBoardSizeSetupState extends GameStateHandler {
    SetupMenu setupMenu;

    public GameBoardSizeSetupState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        setupMenu = new SetupMenu();
        getGameController().registerButtonsForGameState(setupMenu.getButtonList());
        getGameController().getUIWrapper().changeMainView(setupMenu);
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        int clickedDim = 5 + setupMenu.getButtonList().indexOf(button);
        getGameController().setViewBoard(clickedDim,clickedDim);
        getGameController().setModelBoard(clickedDim,clickedDim);
        getGameController().registerButtonsForGameState(getGameController()
                .getViewBoard()
                .getFlattenedBoardElements());
        GridPane viewButtons = getGameController()
                .getViewBoard()
                .getButtonGrid();
        getGameController().setCurrentGameState(GameController.State.XO_PLACING);
        getGameController().getUIWrapper()
                .changeMainView(viewButtons);
    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
        //game state doesn't use secondary clicks
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(GameController.State.MAIN_MENU);
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }

}
