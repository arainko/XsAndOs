package com.arainko.xno.abstracts;

import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.ViewBoard;

public abstract class InternalAbstractGameState<T extends GameState> implements InternalGameState {
    private T parentGameState;

    public InternalAbstractGameState(T parentGameState) {
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
