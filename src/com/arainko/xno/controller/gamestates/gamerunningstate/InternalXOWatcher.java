package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.InternalGameStateHandler;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import javafx.scene.control.Button;

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
                .getElementAt(clickedButtonCords);

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
        Cell clickedCell = getModelBoard().getElementAt(clickedButtonCords);

        if (!clickedCell.isCell(notPartOfConnection())) {
            Connection connectionToRemove = getParentGameState().getBoardManipulator()
                    .getBoardConnection(containingCell(clickedCell));
            getParentGameState().getMoveKeeper().deleteFurtherMoves();
            getParentGameState().getMoveKeeper()
                    .keepMove(connectionToRemove, MoveKeeper.Operation.REMOVE);
            getParentGameState().getBoardManipulator()
                    .handleConnectionRemoval(connectionToRemove);
        }
    }
}
