package com.arainko.xno.controller;

import com.arainko.xno.controller.gamestates.gamerunningstate.GameRunningState;
import com.arainko.xno.controller.gamestates.gamesetupstate.GameSetupState;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.view.ViewBoard;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
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

    public GameController() {
        this.borderPane = new BorderPane();
        this.gameRunningState = new GameRunningState(this);
        this.gameSetupState = new GameSetupState(this);
        currentGameState = gameSetupState;
//        setupExampleModelBoard();
        setupBorderPane();
    }

    void onGameBoardButtonSetup() {
        viewBoard.setButtonsOnMouseClicked(mouseEvent -> {
            Button currButton = (Button) mouseEvent.getSource();
            if (mouseEvent.getButton() == MouseButton.PRIMARY)
                currentGameState.onGameStatePrimaryClickHandler(currButton);
            else if (mouseEvent.getButton() == MouseButton.SECONDARY)
                currentGameState.onGameStateSecondaryClickHandler(currButton);
        });
    }

    public void refreshBoards() {
        List<List<Cell>> modelElements = modelBoard.getBoardElements();
        List<List<Button>> viewElements = viewBoard.getBoardElements();

        for (int i=0; i < modelBoard.getDimY(); i++)
            for (int j=0; j < modelBoard.getDimX(); j++) {
                String cellStr = modelElements.get(i).get(j).toString();
                viewElements.get(i).get(j).setText(cellStr);
            }
    }

    public void setupBorderPane() {
//        SetupMenu setupMenu = new SetupMenu();
//        borderPane.setCenter(setupMenu);
        borderPane.setBackground(Background.EMPTY);
        Button proceedButton = new Button("Launch Game");
        proceedButton.setAlignment(Pos.CENTER);
        proceedButton.setOnAction(event -> currentGameState = gameRunningState);
        borderPane.setTop(proceedButton);
//        setupMenu.setAlignment(Pos.CENTER);
    }

    public void setBoards(int dimXY) {
        this.viewBoard = new ViewBoard(dimXY, dimXY);
        this.modelBoard = new ModelBoard(dimXY, dimXY);
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public ViewBoard getViewBoard() {
        return viewBoard;
    }

    public ModelBoard getModelBoard() {
        return modelBoard;
    }
}

