package com.arainko.xno.controller.game;

import com.arainko.xno.controller.gamestates.gamemainmenustate.GameBoardSizeSetupState;
import com.arainko.xno.controller.gamestates.gamerunningstate.GameRunningState;
import com.arainko.xno.controller.gamestates.gamesetupstate.GameSetupState;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.ui.UIWrapper;
import com.arainko.xno.view.board.ViewBoard;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

import java.util.List;

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

    public void registerButtonsForGameState(List<Button> buttonList) {
        buttonList.forEach(button -> button.setOnMouseClicked(mouseEvent -> {
            Button currButton = (Button) mouseEvent.getSource();
            if (mouseEvent.getButton() == MouseButton.PRIMARY)
                currentGameState.onGameStatePrimaryClickHandler(currButton);
            else if (mouseEvent.getButton() == MouseButton.SECONDARY)
                currentGameState.onGameStateSecondaryClickHandler(currButton);
        }));
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
        currentGameState.onGameStateSet();
        UIWrapper.getLeftButton().setOnActionEnhanced(currentGameState.getLeftButtonActionEvent());
        UIWrapper.getRightButton().setOnActionEnhanced(currentGameState.getRightButtonActionEvent());
    }

    public void setModelBoard(int dimX, int dimY) {
        this.modelBoard = new ModelBoard(dimX, dimY);
    }

    public void setViewBoard(int dimX, int dimY) {
        this.viewBoard = new ViewBoard(dimX, dimY);
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

