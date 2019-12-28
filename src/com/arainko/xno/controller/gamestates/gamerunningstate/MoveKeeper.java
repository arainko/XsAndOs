package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.view.ViewBoard;

import java.util.ArrayList;
import java.util.List;

public class MoveKeeper {
    private ViewBoard viewBoard;
    private ModelBoard modelBoard;
    private List<List<Cell>> keptConnectionCells;
    private int currentIndex;

    public MoveKeeper(GameRunningState parentGameState) {
        this.viewBoard = parentGameState.getGameController().getViewBoard();
        this.modelBoard = parentGameState.getGameController().getModelBoard();
        this.keptConnectionCells = new ArrayList<>();
        this.currentIndex = 0;
    }


}
