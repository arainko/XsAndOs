package com.arainko.xno.controller;

import com.arainko.xno.controller.gamestates.gamemainmenustate.GameBoardSizeSetupState;
import com.arainko.xno.controller.gamestates.gamerunningstate.GameRunningState;
import com.arainko.xno.controller.gamestates.gamesetupstate.GameSetupState;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.UIWrapper;
import com.arainko.xno.view.ViewBoard;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class GameController {
    UIWrapper UIWrapper;

    GameState gameMainMenuState;
    GameState gameSetupState;
    GameState gameRunningState;
//    GameState gameEndState;
    GameState currentGameState;

    private ViewBoard viewBoard;
    private ModelBoard modelBoard;

    public GameController() {
        this.UIWrapper = new UIWrapper();
        this.gameMainMenuState = new GameBoardSizeSetupState(this);
        this.gameRunningState = new GameRunningState(this);
        this.gameSetupState = new GameSetupState(this);
        setCurrentGameState(gameMainMenuState);
    }

    public void refreshBoards() {
        for (int i=0; i < modelBoard.getDimY(); i++)
            for (int j=0; j < modelBoard.getDimX(); j++) {
                String cellStr = modelBoard.getBoardElements().get(i).get(j).toString();
                viewBoard.getBoardElements().get(i).get(j).setText(cellStr);
            }
    }

    public void setupBoards(int squareDim) {
        this.viewBoard = new ViewBoard(squareDim, squareDim);
        this.modelBoard = new ModelBoard(squareDim, squareDim);
        onGameBoardButtonSetup();
    }

    private void onGameBoardButtonSetup() {
        viewBoard.setButtonsOnMouseClicked(mouseEvent -> {
            Button currButton = (Button) mouseEvent.getSource();
            if (mouseEvent.getButton() == MouseButton.PRIMARY)
                currentGameState.onGameStatePrimaryClickHandler(currButton);
            else if (mouseEvent.getButton() == MouseButton.SECONDARY)
                currentGameState.onGameStateSecondaryClickHandler(currButton);
        });
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
        currentGameState.onGameStateSet();
        UIWrapper.getLeftButton().setOnActionEnhanced(currentGameState.getLeftButtonActionEvent());
        UIWrapper.getRightButton().setOnActionEnhanced(currentGameState.getRightButtonActionEvent());
    }

    public GameState getGameMainMenuState() {
        return gameMainMenuState;
    }

    public GameState getGameSetupState() {
        return gameSetupState;
    }

    public GameState getGameRunningState() {
        return gameRunningState;
    }

    public UIWrapper getUIWrapper() {
        return UIWrapper;
    }

    public ViewBoard getViewBoard() {
        return viewBoard;
    }

    public ModelBoard getModelBoard() {
        return modelBoard;
    }
}

