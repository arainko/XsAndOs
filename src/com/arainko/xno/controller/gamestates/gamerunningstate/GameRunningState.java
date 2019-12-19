package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import javafx.scene.control.Button;

public class GameRunningState implements GameState {
    private GameController gameController;

    private InternalGameState XOWatcher;
    private InternalGameState connectionBuilder;
    private InternalGameState connectionDestroyer;
    private InternalGameState currentInternalGameState;

    public GameRunningState(GameController gameController) {
        this.gameController = gameController;
        XOWatcher = new XOWatcher(this);
        connectionBuilder = new ConnectionBuilder(this);
        connectionDestroyer = new ConnectionDestroyer(this);
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

    public void setCurrentInternalGameState(InternalGameState currentInternalGameState) {
        this.currentInternalGameState = currentInternalGameState;
    }

    public GameController getGameController() {
        return gameController;
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
