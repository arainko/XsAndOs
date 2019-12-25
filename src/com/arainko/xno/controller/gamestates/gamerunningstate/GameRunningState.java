package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameRunningState extends GameStateHandler {
    private InternalGameState XOWatcher;
    private InternalGameState connectionBuilder;
    private InternalGameState connectionDestroyer;
    private InternalGameState currentInternalGameState;

    public GameRunningState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        XOWatcher = new InternalXOWatcher(this);
        connectionBuilder = new InternalConnectionBuilder(this);
        connectionDestroyer = new InternalConnectionDestroyer(this);
        this.currentInternalGameState = XOWatcher;
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        getCurrentInternalGameState().onInternalGameStateClickHandler(button);
    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
        if (currentInternalGameState != connectionBuilder)
            getConnectionDestroyer().onInternalGameStateClickHandler(button);
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return null;
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }

    public void setCurrentInternalGameState(InternalGameState currentInternalGameState) {
        this.currentInternalGameState = currentInternalGameState;
    }

    public InternalGameState getConnectionDestroyer() {
        return connectionDestroyer;
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
