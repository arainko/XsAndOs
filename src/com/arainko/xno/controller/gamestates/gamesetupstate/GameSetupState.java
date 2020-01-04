package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.Boards;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.view.board.BoardButton;
import com.arainko.xno.view.board.ViewBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

import static com.arainko.xno.abstracts.Board.Cords;
import static com.arainko.xno.model.predicates.CellPredicates.containingCircle;
import static com.arainko.xno.model.predicates.CellPredicates.containingCross;

public class GameSetupState extends GameStateHandler {
    private Map<Cell.Contents, Integer> contentsCount;
    public GameSetupState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        contentsCount = new HashMap<>();
        contentsCount.put(Cell.Contents.CROSS, 0);
        contentsCount.put(Cell.Contents.CIRCLE, 0);
        cellContentsValidator();
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        Cords clickedButtonCords = getGameController().getViewBoard().getElementCords((BoardButton) button);
        Cell clickedCell = getGameController().getModelBoard().getElementAt(clickedButtonCords);
        cellContentsSetter(clickedCell, Cell.Contents.CROSS);
    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
        Cords clickedButtonCords = getGameController().getViewBoard().getElementCords((BoardButton) button);
        Cell clickedCell = getGameController().getModelBoard().getElementAt(clickedButtonCords);
        cellContentsSetter(clickedCell, Cell.Contents.CIRCLE);
    }

    private void cellContentsSetter(Cell clickedCell, Cell.Contents contentsToSet) {
        ModelBoard modelBoard = getGameController().getModelBoard();
        ViewBoard viewBoard = getGameController().getViewBoard();
        Cell.Contents currContents = clickedCell.getCellContents();
        if (clickedCell.isCell(containingCircle().or(containingCross()))) {
            contentsCount.put(currContents, contentsCount.get(currContents)-1);
            clickedCell.setCellContents(Cell.Contents.EMPTY);
        } else {
            clickedCell.setCellContents(contentsToSet);
            contentsCount.put(contentsToSet, contentsCount.get(contentsToSet)+1);
        }
        Boards.refreshBoardText(modelBoard, viewBoard);
        cellContentsValidator();
    }

    private void cellContentsValidator() {
        int circleCount = contentsCount.get(Cell.Contents.CIRCLE);
        int crossCount = contentsCount.get(Cell.Contents.CROSS);
        boolean isDisabled = crossCount > 0 && circleCount == crossCount;
        getGameController().getUIWrapper().getRightButton().setDisable(!isDisabled);
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(GameController.State.BOARD_SIZE);
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(GameController.State.GAME_RUNNING);
    }

}
