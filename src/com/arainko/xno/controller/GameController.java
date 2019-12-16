package com.arainko.xno.controller;

import com.arainko.xno.controller.gamestates.gamerunningstate.GameRunningState;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;

import java.util.List;

public class GameController {

//    GameState gameSetupState;
    GameState gameRunningState;
//    GameState gameEndState;

    GameState currentGameState;


    private ViewBoard viewBoard;
    private ModelBoard modelBoard;

    public GameController(int dimX, int dimY) {
        this.viewBoard = new ViewBoard(dimX, dimY);
        this.modelBoard = new ModelBoard(dimX, dimY);
        currentGameState = new GameRunningState(this);
        setupExampleModelBoard();
        onGameBoardSetup();
    }

    void setupExampleModelBoard() {
        modelBoard.setBoardCellContentsAt(0,1, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(5,5, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(9,9, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(3,8, Cell.Contents.CROSS);
    }
//
    void onGameBoardSetup() {
        List<List<Cell>> modelElements = modelBoard.getBoardElements();
        List<List<Button>> viewElements = viewBoard.getBoardElements();

        for (int i=0; i < modelBoard.getDimY(); i++)
            for (int j=0; j < modelBoard.getDimX(); j++) {
                String cellStr = modelElements.get(i).get(j).toString();
                viewElements.get(i).get(j).setText(cellStr);
            }

        viewBoard.setButtonsOnAction(eventHandler -> {
            Button currButton = (Button) eventHandler.getSource();
            currentGameState.onGameStateClickHandler(currButton);
        });
    }

    public ViewBoard getViewBoard() {
        return viewBoard;
    }

    public ModelBoard getModelBoard() {
        return modelBoard;
    }
}

