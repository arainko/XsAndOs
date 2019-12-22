package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;

public class GameSetupState implements GameState {
    private GameController gameController;
    private ModelBoard modelBoard;
    private ViewBoard viewBoard;

    InternalGameState boardSizeSetupState;
    InternalGameState boardElementSetupState;

    public GameSetupState(GameController gameController) {
        this.gameController = gameController;
        this.modelBoard = gameController.getModelBoard();
        this.viewBoard = gameController.getViewBoard();
        boardElementSetupState = new BoardSizeSetupState(this);
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
//        Cords clickedButtonCords = viewBoard.getButtonCords(button);
//        Cell clickedCell = modelBoard.getCellAt(clickedButtonCords);
//        if (clickedCell.isCell(containingCross().or(containingCircle())))
//            clickedCell.setCellContents(Cell.Contents.EMPTY);
//        else clickedCell.setCellContents(Cell.Contents.CROSS);
//        gameController.refreshBoards();

    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
//        Cords clickedButtonCords = viewBoard.getButtonCords(button);
//        Cell clickedCell = modelBoard.getCellAt(clickedButtonCords);
//        if (clickedCell.isCell(containingCircle().or(containingCross())))
//            clickedCell.setCellContents(Cell.Contents.EMPTY);
//        else clickedCell.setCellContents(Cell.Contents.CIRCLE);
//        gameController.refreshBoards();
    }

    @Override
    public GameController getGameController() {
        return gameController;
    }
}
