package com.arainko.xno.controller.historian;

import com.arainko.xno.controller.gamestates.gamerunningstate.GameRunningState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.ViewBoard;

public class Bundle {
    private ModelBoard bundledModelBoard;
    private ViewBoard bundledViewBoard;
    private GameRunningState bundledGameRunningState;

    public Bundle(ModelBoard bundledModelBoard, ViewBoard bundledViewBoard, GameRunningState bundledGameRunningState) {
        this.bundledModelBoard = bundledModelBoard;
        this.bundledViewBoard = bundledViewBoard;
        this.bundledGameRunningState = bundledGameRunningState;
    }

    public ModelBoard getBundledModelBoard() {
        return bundledModelBoard;
    }

    public ViewBoard getBundledViewBoard() {
        return bundledViewBoard;
    }

    public GameRunningState getBundledGameRunningState() {
        return bundledGameRunningState;
    }
}
