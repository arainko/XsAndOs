package com.arainko.xno.controller.gamestates;

import com.arainko.xno.controller.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.controller.helpers.StateManager;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.board.ViewBoard;
import com.arainko.xno.view.screens.SetupScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/* This GameState handles the board size menu and allows the user to pick a board size.*/

public class GameBoardSizeSetupState extends GameStateHandler {
    public GameBoardSizeSetupState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        SetupScreen setupMenu = new SetupScreen();
        getGameController().registerButtonsForClickHandler(setupMenu.getButtonList());
        getGameController().getUIWrapper().changeMainView(setupMenu);
    }

    @Override
    public void onPrimaryClickHandler(Button button) {
        int clickedDim = ((SetupScreen.SizeButton) button).getDimContainer();
        setupGameController(clickedDim);
        getGameController().setCurrentGameState(StateManager.State.XO_PLACING);
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(StateManager.State.MAIN_MENU);
    }

    private void setupGameController(int boardDim) {
        ViewBoard viewBoard = new ViewBoard(boardDim, boardDim);
        ModelBoard modelBoard = new ModelBoard(boardDim, boardDim);
        MoveKeeper moveKeeper = new MoveKeeper(modelBoard, viewBoard);
        getGameController().setViewBoard(viewBoard);
        getGameController().setModelBoard(modelBoard);
        getGameController().setMoveKeeper(moveKeeper);
    }


}
