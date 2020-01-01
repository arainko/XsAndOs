package com.arainko.xno.abstracts;

import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.board.ViewBoard;

public abstract class InternalGameStateHandler<T extends GameStateHandler> implements InternalGameState {
    private T parentGameState;

    public InternalGameStateHandler(T parentGameState) {
        this.parentGameState = parentGameState;
    }

    public ModelBoard getModelBoard() {
        return parentGameState.getGameController().getModelBoard();
    }

    public ViewBoard getViewBoard() {
        return parentGameState.getGameController().getViewBoard();
    }

    public T getParentGameState() {
        return parentGameState;
    }

}
