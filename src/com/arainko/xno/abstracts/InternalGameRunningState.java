package com.arainko.xno.abstracts;

import com.arainko.xno.controller.gamestates.gamerunningstate.GameRunningState;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.ViewBoard;

public abstract class InternalGameRunningState implements InternalGameState {
    private GameRunningState parentGameState;
    private ModelBoard modelBoard;
    private ViewBoard viewBoard;

    public InternalGameRunningState(GameRunningState parentGameState) {
        this.parentGameState = parentGameState;
        modelBoard = parentGameState.getGameController().getModelBoard();
        viewBoard = parentGameState.getGameController().getViewBoard();
    }

    public ModelBoard getModelBoard() {
        return modelBoard;
    }

    public ViewBoard getViewBoard() {
        return viewBoard;
    }

    public GameRunningState getParentGameState() {
        return parentGameState;
    }
}
