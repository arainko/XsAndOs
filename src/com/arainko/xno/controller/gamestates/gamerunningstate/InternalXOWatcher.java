package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.InternalGameStateHandler;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import javafx.scene.control.Button;

import java.util.function.Predicate;

import static com.arainko.xno.model.predicates.CellPredicates.*;
import static com.arainko.xno.model.predicates.ConnectionPredicates.containingCell;

public class InternalXOWatcher extends InternalGameStateHandler<GameRunningState> {

    public InternalXOWatcher(GameRunningState parentGameState) {
        super(parentGameState);
    }

    @Override
    public void onInternalGameStatePrimaryClickHandler(Button button) {
        Cords clickedButtonCords = getParentGameState().getGameController()
            .getViewBoard()
            .getButtonCords(button);

        Cell clickedCell = getParentGameState().getGameController()
                .getModelBoard()
                .getCellAt(clickedButtonCords);

        boolean isXO = clickedCell
                .isCell((containingCircle().or(containingCross())).and(notPartOfConnection()));

        if (isXO) {
            getParentGameState().setCurrentInternalGameState(getParentGameState().getConnectionBuilder());
            getParentGameState().getCurrentInternalGameState().onInternalGameStatePrimaryClickHandler(button);
        }
    }

    @Override
    public void onInternalGameStateSecondaryClickHandler(Button button) {
        Cords clickedButtonCords = getViewBoard().getButtonCords(button);
        Cell clickedCell = getModelBoard().getCellAt(clickedButtonCords);

        if (clickedCell.isCell(Predicate.not(notPartOfConnection()))) {
            Connection connectionToRemove =  getModelBoard().getConnections().stream()
                    .filter(containingCell(clickedCell))
                    .findFirst()
                    .get();

            getViewBoard().setButtonsColorAtCords(
                    Cords.getCordList(connectionToRemove.getConnectionCells()), "default-button");
            getModelBoard().removeConnection(connectionToRemove);
        }
    }
}
