package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.model.elements.Connection;
import javafx.scene.control.Button;

import java.util.List;

public class GameRunningState implements GameState {
    private GameController gameController;

    private InternalGameState XOWatcher;
    private InternalGameState connectionBuilder;
    private InternalGameState currentInternalGameState;

    public GameRunningState(GameController gameController) {
        this.gameController = gameController;
        XOWatcher = new XOWatcher(this);
        connectionBuilder = new ConnectionBuilder(this);
        this.currentInternalGameState = XOWatcher;
    }

    @Override
    public void onGameStateClickHandler(Button button) {
        onRunningGameClickHandler(button);
    }

    private void onRunningGameClickHandler(Button button) {
       currentInternalGameState.onInternalGameStateClickHandler(button);
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

    public GameController getGameController() {
        return gameController;
    }
}
