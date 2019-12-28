package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.ViewBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameRunningState extends GameStateHandler {
    private InternalGameState XOWatcher;
    private InternalGameState connectionBuilder;
    private InternalGameState currentInternalGameState;

    private MoveKeeper moveKeeper;

    public GameRunningState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        XOWatcher = new InternalXOWatcher(this);
        connectionBuilder = new InternalConnectionBuilder(this);
        setCurrentInternalGameState(XOWatcher);
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        getCurrentInternalGameState().onInternalGameStatePrimaryClickHandler(button);
    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
        getCurrentInternalGameState().onInternalGameStateSecondaryClickHandler(button);
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        ViewBoard viewBoard = getGameController().getViewBoard();
        ModelBoard modelBoard = getGameController().getModelBoard();
        return event -> {
            if (currentInternalGameState == connectionBuilder)
                getCurrentInternalGameState().onInternalGameStatePrimaryClickHandler(new Button());
            else {
                getCurrentInternalGameState().onInternalGameStateSecondaryClickHandler(
                        getGameController().getViewBoard().getButtonAt(
                                getGameController().getModelBoard().getConnections().get(0)
                                .getConnectionCells().get(0).getCellCords())
                );
            }
        };
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }

    public void setCurrentInternalGameState(InternalGameState currentInternalGameState) {
        this.currentInternalGameState = currentInternalGameState;
    }

    public InternalGameState getXOWatcher() {
        return XOWatcher;
    }

    public InternalGameState getConnectionBuilder() {
        return connectionBuilder;
    }

    public InternalGameState getCurrentInternalGameState() {
        return currentInternalGameState;
    }
}
