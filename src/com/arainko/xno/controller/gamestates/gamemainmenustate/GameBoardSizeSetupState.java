package com.arainko.xno.controller.gamestates.gamemainmenustate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.board.ViewBoard;
import com.arainko.xno.view.menus.SetupMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameBoardSizeSetupState extends GameStateHandler {
    public GameBoardSizeSetupState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        SetupMenu setupMenu = new SetupMenu();
        getGameController().registerButtonsForGameState(setupMenu.getButtonList());
        getGameController().getUIWrapper().changeMainView(setupMenu);
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        int clickedDim = Integer.parseInt(String.valueOf(button.getText().charAt(0)));
        setupGameController(clickedDim);
        getGameController().setCurrentGameState(GameController.State.XO_PLACING);
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(GameController.State.MAIN_MENU);
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
