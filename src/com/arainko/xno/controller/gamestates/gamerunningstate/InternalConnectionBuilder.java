package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.InternalGameStateHandler;
import com.arainko.xno.abstracts.Board.Cords;
import com.arainko.xno.controller.helpers.BoardManipulator;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.board.BoardButton;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

import static com.arainko.xno.model.predicates.ConnectionPredicates.*;

public class InternalConnectionBuilder extends InternalGameStateHandler<GameRunningState> {
    List<Cords> lastClickedNeighbors;
    Connection connection;

    public InternalConnectionBuilder(GameRunningState parentGameState) {
        super(parentGameState);
        connection = new Connection();
        lastClickedNeighbors = new ArrayList<>();
    }

    @Override
    public void onInternalGameStatePrimaryClickHandler(Button button) {
        Cords clickedCords = getViewBoard().getElementCords((BoardButton) button);
        Cell clickedCell = getModelBoard().getElementAt(clickedCords);

        if (connection.isConnection(empty()))
            onStateInitialEntryHandler(button, clickedCell, clickedCords);
        else
            onStateContinuousEntryHandler(button, clickedCell, clickedCords);
    }

    @Override
    public void onInternalGameStateSecondaryClickHandler(Button button) {
        onStateAbortHandler();
    }

    private void onStateInitialEntryHandler(Button clickedButton, Cell clickedCell, Cords clickedCords) {
        clickedButton.setId("clicked-button");
        connection.addConnectionUnit(clickedCell);
        lastClickedNeighbors = getModelBoard().getFreeNeighborsAt(clickedCords);
        getViewBoard().setButtonsColorAtCords(lastClickedNeighbors, "neighbor-button");
    }

    private void onStateContinuousEntryHandler(Button button, Cell clickedCell, Cords clickedCords) {
        if (lastClickedNeighbors.contains(clickedCords)) {
            connection.addConnectionUnit(clickedCell);
            getViewBoard().setButtonsColorAtCords(lastClickedNeighbors, "default-button");
            lastClickedNeighbors = getModelBoard().getFreeNeighborsAt(clickedCords);
            button.setId("clicked-button");
            getViewBoard().setButtonsColorAtCords(lastClickedNeighbors, "neighbor-button");
            connection.setConnectionTypes();
        }
        if (connection.isConnection(ended()) || lastClickedNeighbors.isEmpty())
            onStateExitHandler();
    }

    private void onStateExitHandler() {
        getParentGameState().getMoveKeeper().deleteFurtherMoves();
        getParentGameState().getMoveKeeper().keepMove(connection, MoveKeeper.Operation.BUILD);
        BoardManipulator.handleConnectionBuilding(getModelBoard(), getViewBoard(), connection);
        getViewBoard().setButtonsColorAtCords(lastClickedNeighbors, "default-button");
        onStateExitCleanUp();
    }

    private void onStateAbortHandler() {
        List<Cords> cordsToColorAt = getModelBoard().getElementsCords(connection.getConnectionCells());
        getViewBoard().setButtonsColorAtCords(cordsToColorAt, "default-button");
        getViewBoard().setButtonsColorAtCords(lastClickedNeighbors, "default-button");
        connection.remove();
        onStateExitCleanUp();
    }

    private void onStateExitCleanUp() {
        connection = new Connection();
        lastClickedNeighbors = new ArrayList<>();
        getParentGameState().setCurrentInternalGameState(getParentGameState().getXOWatcher());
    }


}
