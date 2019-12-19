package com.arainko.xno.controller;

import com.arainko.xno.controller.gamestates.gamerunningstate.GameRunningState;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class GameController {
    BorderPane borderPane;

    GameState gameSetupState;
    GameState gameRunningState;
//    GameState gameEndState;
    GameState currentGameState;

    private ViewBoard viewBoard;
    private ModelBoard modelBoard;

    public GameController(int dimX, int dimY) {
        this.borderPane = new BorderPane();
        this.viewBoard = new ViewBoard(dimX, dimY);
        this.modelBoard = new ModelBoard(dimX, dimY);
        this.gameRunningState = new GameRunningState(this);
        this.gameSetupState = new GameRunningState(this);
        currentGameState = gameRunningState;
        setupExampleModelBoard();
        onGameBoardSetup();
    }

    void setupExampleModelBoard() {
        modelBoard.setBoardCellContentsAt(4,0, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(5,0, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(7,0, Cell.Contents.CIRCLE);

        modelBoard.setBoardCellContentsAt(4,1, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(5,1, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(8,1, Cell.Contents.CIRCLE);

        modelBoard.setBoardCellContentsAt(2,2, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(8,2, Cell.Contents.CIRCLE);

        modelBoard.setBoardCellContentsAt(2,3, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(3,3, Cell.Contents.CROSS);

        modelBoard.setBoardCellContentsAt(7,5, Cell.Contents.CROSS);

        modelBoard.setBoardCellContentsAt(0,6, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(6,6, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(7,6, Cell.Contents.CROSS);

        modelBoard.setBoardCellContentsAt(0,7, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(1,7, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(4,7, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(8,7, Cell.Contents.CIRCLE);

        modelBoard.setBoardCellContentsAt(1,8, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(8,8, Cell.Contents.CIRCLE);

        modelBoard.setBoardCellContentsAt(8,9, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(9,9, Cell.Contents.CROSS);
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

        viewBoard.setButtonsOnMouseClicked(mouseEvent -> {
            Button currButton = (Button) mouseEvent.getSource();
            if (mouseEvent.getButton() == MouseButton.PRIMARY)
                currentGameState.onGameStatePrimaryClickHandler(currButton);
            else if (mouseEvent.getButton() == MouseButton.SECONDARY)
                currentGameState.onGameStateSecondaryClickHandler(currButton);
        });
    }

    public ViewBoard getViewBoard() {
        return viewBoard;
    }

    public ModelBoard getModelBoard() {
        return modelBoard;
    }
}

