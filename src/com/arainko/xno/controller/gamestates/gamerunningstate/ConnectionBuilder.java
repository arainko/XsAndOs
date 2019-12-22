package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.InternalAbstractGameState;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

import static com.arainko.xno.model.predicates.ConnectionPredicates.*;

public class ConnectionBuilder extends InternalAbstractGameState<GameRunningState> {
    List<Cords> lastClickedNeighbors;
    Connection connection;

    public ConnectionBuilder(GameRunningState parentGameState) {
        super(parentGameState);
        connection = new Connection();
        lastClickedNeighbors = new ArrayList<>();
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {
        Cords clickedCords = getViewBoard().getButtonCords(button);
        Cell clickedCell = getModelBoard().getCellAt(clickedCords);

        if (connection.isConnection(empty()))
            onStateInitialEntryHandler(button, clickedCell, clickedCords);
        else
            onStateContinuousEntryHandler(button, clickedCell, clickedCords);
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
        getViewBoard().setButtonsColorAtCords(
                Cords.getCordList(connection.getConnectionCells()),
                !connection.isConnection(upToWinCondition()) ? "wrong-button" : "right-button");

        getModelBoard().addConnection(connection);
        getViewBoard().setButtonsColorAtCords(lastClickedNeighbors, "default-button");
        connection = new Connection();
        lastClickedNeighbors = new ArrayList<>();
        getParentGameState().setCurrentInternalGameState(getParentGameState().getXOWatcher());
    }


}
