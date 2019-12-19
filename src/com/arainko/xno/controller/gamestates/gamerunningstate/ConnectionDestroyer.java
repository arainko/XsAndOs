package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.InternalGameRunningState;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.arainko.xno.model.predicates.CellPredicates.notPartOfConnection;
import static com.arainko.xno.model.predicates.ConnectionPredicates.containingCell;

public class ConnectionDestroyer extends InternalGameRunningState {

    public ConnectionDestroyer(GameRunningState parentGameState) {
        super(parentGameState);
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {
        Cords clickedButtonCords = getViewBoard().getButtonCords(button);
        Cell clickedCell = getModelBoard().getCellAt(clickedButtonCords);

        if (clickedCell.isCell(Predicate.not(notPartOfConnection()))) {
            Connection connectionToRemove =  getModelBoard().getConnections().stream()
                    .filter(containingCell(clickedCell))
                    .findFirst()
                    .get();

           getViewBoard().setButtonsColorAtCords(Cords.getCordList(connectionToRemove.getConnectionCells()), "default-button");
           getModelBoard().removeConnection(connectionToRemove);
        }
    }
}
