package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.InternalGameStateHandler;
import com.arainko.xno.controller.helpers.Boards;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.board.BoardButton;
import javafx.scene.control.Button;

import static com.arainko.xno.abstracts.Board.Cords;
import static com.arainko.xno.model.predicates.CellPredicates.*;
import static com.arainko.xno.model.predicates.ConnectionPredicates.containingCell;

public class InternalXOWatcher extends InternalGameStateHandler<GameRunningState> {
    public InternalXOWatcher(GameRunningState parentGameState) {
        super(parentGameState);
    }

    @Override
    public <T extends Button> void onInternalGameStatePrimaryClickHandler(T button) {
        Cords clickedButtonCords = getParentGameState().getGameController()
            .getViewBoard()
            .getElementCords((BoardButton) button);

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
    public <T extends Button> void onInternalGameStateSecondaryClickHandler(T button) {
        Cords clickedButtonCords = getViewBoard().getElementCords((BoardButton) button);
        Cell clickedCell = getModelBoard().getElementAt(clickedButtonCords);

        if (!clickedCell.isCell(notPartOfConnection())) {
            Connection connectionToRemove = getModelBoard()
                    .getSpecificConnection(containingCell(clickedCell));
            getParentGameState().getMoveKeeper().deleteFurtherMoves();
            getParentGameState().getMoveKeeper()
                    .keepMove(connectionToRemove, MoveKeeper.Operation.REMOVE);
            Boards.handleConnectionRemoval(getModelBoard(), getViewBoard(), connectionToRemove);
        }
    }
}
