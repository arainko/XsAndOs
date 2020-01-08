package com.arainko.xno.controller.gamestates.gamerunning;

import com.arainko.xno.abstracts.InternalClickHandler;
import com.arainko.xno.controller.helpers.Boards;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.buttons.BoardButton;
import javafx.scene.control.Button;

import static com.arainko.xno.abstracts.Board.Cords;
import static com.arainko.xno.model.predicates.CellPredicates.*;
import static com.arainko.xno.model.predicates.ConnectionPredicates.containingCell;

public class InternalXOWatcher extends InternalClickHandler<GameRunningState> {
    public InternalXOWatcher(GameRunningState parentGameState) {
        super(parentGameState);
    }

    @Override
    public <T extends Button> void onPrimaryClickHandler(T button) {
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
            getParentGameState().getCurrentInternalGameState().onPrimaryClickHandler(button);
        }
    }

    @Override
    public <T extends Button> void onSecondaryClickHandler(T button) {
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
