package com.arainko.xno.controller;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;

import java.util.List;
import java.util.function.Function;

public class GameController {
    private ViewBoard viewBoard;
    private ModelBoard modelBoard;
    private Button clickedButton;

    public GameController(int dimX, int dimY) {
        this.viewBoard = new ViewBoard(dimX, dimY);
        this.modelBoard = new ModelBoard(dimX, dimY);
        setupExampleModelBoard();
        setupViewBoard();
    }

    void setupExampleModelBoard() {
        modelBoard.setBoardCellContentsAt(0,1, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(5,5, Cell.Contents.CIRCLE);
        modelBoard.setBoardCellContentsAt(9,9, Cell.Contents.CROSS);
        modelBoard.setBoardCellContentsAt(3,8, Cell.Contents.CROSS);
    }
//
    void setupViewBoard() {
        int dimX = modelBoard.getDimX();
        int dimY = modelBoard.getDimY();
        List<List<Cell>> modelElements = modelBoard.getBoardElements();
        List<List<Button>> viewElements = viewBoard.getBoardElements();

        for (int i=0; i < dimY; i++)
            for (int j=0; j < dimX; j++) {
                String elementStr = modelElements.get(i).get(j).toString();
                viewElements.get(i).get(j).setText(elementStr);
            }

        viewBoard.setButtonsOnAction(eventHandler -> {
            Button currButton = (Button) eventHandler.getSource();
            int[] rowCol = viewBoard.getButtonRowCol(currButton);
            List<Cell> neighbors = modelBoard.getFreeNeighborsAt(rowCol[1], rowCol[0]);
            for (Cell cell : neighbors) {
                int cordX = cell.getCordX();
                int cordY = cell.getCordY();
                viewBoard.getButtonAt(cordX, cordY).setId("neighbor-button");
            }
            currButton.setId("clicked-button");
            clickedButton = currButton;
        });
    }

    public ViewBoard getViewBoard() {
        return viewBoard;
    }
}

