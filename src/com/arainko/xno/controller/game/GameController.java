package com.arainko.xno.controller.game;

import com.arainko.xno.controller.gamestates.gamemainmenustate.GameBoardSizeSetupState;
import com.arainko.xno.controller.gamestates.gamemainmenustate.GameMainMenu;
import com.arainko.xno.controller.gamestates.gamerunningstate.GameRunningState;
import com.arainko.xno.controller.gamestates.gamesetupstate.GameSetupState;
import com.arainko.xno.controller.helpers.BoardManipulator;
import com.arainko.xno.controller.helpers.Bundler;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.controller.interfaces.GameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.board.ViewBoard;
import com.arainko.xno.view.ui.UIWrapper;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

import java.util.List;

public class GameController {
    public enum State {
        MAIN_MENU, BOARD_SIZE, XO_PLACING, GAME_RUNNING
    }
    UIWrapper UIWrapper;

    GameState gameMainMenuState;
    GameState gameBoardSizeSetupState;
    GameState gameSetupState;
    GameState gameRunningState;
//    GameState gameEndState;
    GameState currentGameState;

    private ViewBoard viewBoard;
    private ModelBoard modelBoard;
    private MoveKeeper moveKeeper;
    private BoardManipulator boardManipulator;
    private Bundler bundler;

    public GameController() {
        this.UIWrapper = new UIWrapper();
        this.bundler = new Bundler(this);
        this.gameMainMenuState = new GameMainMenu(this);
        this.gameBoardSizeSetupState = new GameBoardSizeSetupState(this);
        this.gameRunningState = new GameRunningState(this);
        this.gameSetupState = new GameSetupState(this);
        setCurrentGameState(State.MAIN_MENU);
    }

    public <T extends Button> void registerButtonsForGameState(List<T> buttonList) {
        buttonList.forEach(button -> button.setOnMouseClicked(mouseEvent -> {
            T currButton = (T) mouseEvent.getSource();
            if (mouseEvent.getButton() == MouseButton.PRIMARY)
                currentGameState.onGameStatePrimaryClickHandler(currButton);
            else if (mouseEvent.getButton() == MouseButton.SECONDARY)
                currentGameState.onGameStateSecondaryClickHandler(currButton);
        }));
    }

    public void setCurrentGameState(State gameState) {
        this.currentGameState = getState(gameState);
        currentGameState.onGameStateSet();
        UIWrapper.getLeftButton().setOnActionEnhanced(currentGameState.getLeftButtonActionEvent());
        UIWrapper.getRightButton().setOnActionEnhanced(currentGameState.getRightButtonActionEvent());
    }

    private GameState getState(State gameState) {
        switch (gameState) {
            case MAIN_MENU:
                return gameMainMenuState;
            case BOARD_SIZE:
                return gameBoardSizeSetupState;
            case XO_PLACING:
                return gameSetupState;
            case GAME_RUNNING:
                return gameRunningState;
            default:
                return currentGameState;
        }
    }

    public void setModelBoard(ModelBoard modelBoard) {
        this.modelBoard = modelBoard;
    }

    public void setViewBoard(ViewBoard viewBoard) {
        this.viewBoard = viewBoard;
    }

    public void setBoardManipulator(BoardManipulator boardManipulator) {
        this.boardManipulator = boardManipulator;
    }

    public void setMoveKeeper(MoveKeeper moveKeeper) {
        this.moveKeeper = moveKeeper;
    }

    public MoveKeeper getMoveKeeper() {
        return moveKeeper;
    }

    public BoardManipulator getBoardManipulator() {
        return boardManipulator;
    }

    public Bundler getBundler() {
        return bundler;
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

