package com.arainko.xno.controller.gamestates.boardstates;

import com.arainko.xno.abstracts.Board.Cords;
import com.arainko.xno.abstracts.Element;
import com.arainko.xno.abstracts.InternalClickHandler;
import com.arainko.xno.controller.helpers.Boards;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.view.buttons.BoardButton;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.arainko.xno.model.predicates.ConnectionPredicates.empty;
import static com.arainko.xno.model.predicates.ConnectionPredicates.ended;

public class InternalConnectionBuilder extends InternalClickHandler<GameRunningState> {
    private List<Cords> lastClickedNeighbors;
    private Connection connection;
    private List<Cords> pausedConnectionCords;

    public InternalConnectionBuilder(GameRunningState parentGameState) {
        super(parentGameState);
        connection = new Connection();
        pausedConnectionCords = new ArrayList<>();
        lastClickedNeighbors = new ArrayList<>();
    }

    @Override
    public void onPrimaryClickHandler(Button button) {
        Cords clickedCords = getViewBoard().getElementCords((BoardButton) button);
        Cell clickedCell = getModelBoard().getElementAt(clickedCords);

        if (connection.isConnection(empty()))
            onStateInitialEntryHandler(button, clickedCell, clickedCords);
        else
            onStateContinuousEntryHandler(button, clickedCell, clickedCords);
    }

    @Override
    public void onSecondaryClickHandler(Button button) {
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
        Boards.handleConnectionBuilding(getModelBoard(), getViewBoard(), connection);
        getViewBoard().setButtonsColorAtCords(lastClickedNeighbors, "default-button");
        onStateExitCleanUp();
    }

    public void onStateAbortHandler() {
        List<Cords> cordsToColorAt = getModelBoard().getElementsCords(connection.getConnectionCells());
        getViewBoard().setButtonsColorAtCords(cordsToColorAt, "default-button");
        getViewBoard().setButtonsColorAtCords(lastClickedNeighbors, "default-button");
        connection.remove();
        onStateExitCleanUp();
    }

    public void pauseState() {
        pausedConnectionCords = connection.getConnectionCells().stream()
                .map(Element::getCords)
                .collect(Collectors.toList());
        connection.remove();
    }

    public void resumeState() {
        connection = new Connection(getModelBoard().getElementsAt(pausedConnectionCords));
        pausedConnectionCords = new ArrayList<>();
    }

    private void onStateExitCleanUp() {
        connection = new Connection();
        lastClickedNeighbors = new ArrayList<>();
        getParentGameState().setCurrentInternalGameState(getParentGameState().getXOWatcher());
    }


}
