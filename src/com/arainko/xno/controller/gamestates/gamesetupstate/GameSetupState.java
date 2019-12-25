package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.GameController;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import static com.arainko.xno.model.predicates.CellPredicates.containingCircle;
import static com.arainko.xno.model.predicates.CellPredicates.containingCross;

public class GameSetupState extends GameStateHandler {
    private int crossCount;
    private int circleCount;
    public GameSetupState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        crossCount = 0;
        circleCount = 0;
        getGameController().getUIWrapper().getLeftButton().setVisible(true);
        getGameController().getUIWrapper().getRightButton().setVisible(true);
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        Cords clickedButtonCords = getGameController().getViewBoard().getButtonCords(button);
        Cell clickedCell = getGameController().getModelBoard().getCellAt(clickedButtonCords);
        if (clickedCell.isCell(containingCross().or(containingCircle()))) {
            clickedCell.setCellContents(Cell.Contents.EMPTY);
        } else {
            clickedCell.setCellContents(Cell.Contents.CROSS);
            crossCount++;
        }
        getGameController().refreshBoards();
    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
        Cords clickedButtonCords = getGameController().getViewBoard().getButtonCords(button);
        Cell clickedCell = getGameController().getModelBoard().getCellAt(clickedButtonCords);
        if (clickedCell.isCell(containingCircle().or(containingCross()))) {
            clickedCell.setCellContents(Cell.Contents.EMPTY);
        } else {
            clickedCell.setCellContents(Cell.Contents.CIRCLE);
            circleCount++;
        }
        getGameController().refreshBoards();
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(getGameController().getGameMainMenuState());
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(getGameController().getGameRunningState());
    }
}
