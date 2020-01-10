package com.arainko.xno.controller.gamestates;

import com.arainko.xno.controller.abstracts.InternalClickHandler;
import com.arainko.xno.controller.helpers.Boards;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.buttons.BoardButton;
import javafx.scene.control.Button;

import static com.arainko.xno.abstracts.Board.Cords;
import static com.arainko.xno.model.predicates.CellPredicates.*;
import static com.arainko.xno.model.predicates.ConnectionPredicates.containingCell;
import static java.util.function.Predicate.not;

/* This InternalClickHandler decides whether a tile the user clicked is an X or an O,
* its secondary click behavior is removing selected connections */

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
                .isCell((containingCircle().or(containingCross())).and(not(partOfConnection())));

        if (isXO) {
            getParentGameState().setCurrentInternalGameState(getParentGameState().getConnectionBuilder());
            getParentGameState().getCurrentInternalGameState().onPrimaryClickHandler(button);
        }
    }

    @Override
    public <T extends Button> void onSecondaryClickHandler(T button) {
        Cords clickedButtonCords = getViewBoard().getElementCords((BoardButton) button);
        Cell clickedCell = getModelBoard().getElementAt(clickedButtonCords);

        if (clickedCell.isCell(partOfConnection())) {
            Connection connectionToRemove = getModelBoard()
                    .getSpecificConnection(containingCell(clickedCell));
            getParentGameState().getMoveKeeper().deleteFurtherMoves();
            getParentGameState().getMoveKeeper()
                    .keepMove(connectionToRemove, MoveKeeper.Operation.REMOVE);
            Boards.handleConnectionRemoval(getModelBoard(), getViewBoard(), connectionToRemove);
        }
    }
}
