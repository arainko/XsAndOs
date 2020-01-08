package com.arainko.xno.controller.abstracts;

import com.arainko.xno.controller.abstracts.GameStateHandler;
import com.arainko.xno.controller.interfaces.ClickHandler;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.board.ViewBoard;

public abstract class InternalClickHandler<T extends GameStateHandler> implements ClickHandler {
    private T parentGameState;

    public InternalClickHandler(T parentGameState) {
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
