package com.arainko.xno.controller.gamestates;

import com.arainko.xno.controller.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.Boards;
import com.arainko.xno.controller.helpers.StateManager;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.view.buttons.BoardButton;
import com.arainko.xno.view.board.ViewBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

import static com.arainko.xno.abstracts.Board.Cords;
import static com.arainko.xno.model.predicates.CellPredicates.containingCircle;
import static com.arainko.xno.model.predicates.CellPredicates.containingCross;

/* This GameState handles placing of Xs and Os on the boards after
* selecting the 'Create custom board' option. */

public class GameXOPlacingState extends GameStateHandler {
    private Map<Cell.Contents, Integer> contentsCount;

    public GameXOPlacingState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        ViewBoard viewBoard = getGameController().getViewBoard();
        getGameController().registerButtonsForClickHandler(viewBoard.getFlattenedBoardElements());
        getGameController().getUIWrapper().changeMainView(viewBoard.getButtonGrid());
        contentsCount = new HashMap<>();
        contentsCount.put(Cell.Contents.CROSS, 0);
        contentsCount.put(Cell.Contents.CIRCLE, 0);
        cellContentsValidator();
    }

    @Override
    public void onPrimaryClickHandler(Button button) {
        Cords clickedButtonCords = getGameController().getViewBoard().getElementCords((BoardButton) button);
        Cell clickedCell = getGameController().getModelBoard().getElementAt(clickedButtonCords);
        cellContentsSetter(clickedCell, Cell.Contents.CROSS);
    }

    @Override
    public void onSecondaryClickHandler(Button button) {
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
        return event -> {
            getGameController().getUIWrapper().getLeftButton().setDisable(false);
            getGameController().getUIWrapper().getRightButton().setDisable(false);
            getGameController().setCurrentGameState(StateManager.State.BOARD_SIZE);
        };
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(StateManager.State.GAME_RUNNING);
    }

}
