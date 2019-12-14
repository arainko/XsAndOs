package com.arainko.xno.controller;

import com.arainko.xno.abstracts.Element;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;

import java.util.List;

public class GameController {
    private ViewBoard viewBoard;
    private ModelBoard modelBoard;

    public GameController(int dimX, int dimY) {
        this.viewBoard = new ViewBoard(dimX, dimY);
        this.modelBoard = new ModelBoard(dimX, dimY);
        setupExampleModelBoard();
        setupViewBoard();
    }

    void setupExampleModelBoard() {
        modelBoard.setCircleAt(0,0);
        modelBoard.setCircleAt(2,0);
        modelBoard.setCrossAt(9,8);
        modelBoard.setCrossAt(5,8);
    }

    void setupViewBoard() {
        int dimX = modelBoard.getDimX();
        int dimY = modelBoard.getDimY();
        List<List<Element>> modelElements = modelBoard.getBoardElements();
        List<List<Button>> viewElements = viewBoard.getBoardElements();

        for (int i=0; i < dimY; i++)
            for (int j=0; j < dimX; j++) {
                String elementStr = modelElements.get(i).get(j).toString();
                viewElements.get(i).get(j).setText(elementStr);
            }
    }

    public ViewBoard getViewBoard() {
        return viewBoard;
    }
}

