package com.arainko.xno.controller.game;

import com.arainko.xno.controller.gamestates.menustates.GameEndState;
import com.arainko.xno.controller.gamestates.menustates.GameBoardSizeSetupState;
import com.arainko.xno.controller.gamestates.menustates.GameLoaderState;
import com.arainko.xno.controller.gamestates.menustates.GameMainMenu;
import com.arainko.xno.controller.gamestates.boardstates.GameRunningState;
import com.arainko.xno.controller.gamestates.boardstates.GameXOPlacingState;
import com.arainko.xno.controller.helpers.Bundler;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.controller.helpers.StateManager;
import com.arainko.xno.controller.helpers.StateManager.State;
import com.arainko.xno.controller.interfaces.NavButtonHandler;
import com.arainko.xno.controller.interfaces.GameState;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.board.ViewBoard;
import com.arainko.xno.view.ui.UIWrapper;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

import java.util.List;

public class GameController {
    private UIWrapper UIWrapper;
    private ViewBoard viewBoard;
    private ModelBoard modelBoard;
    private StateManager stateManager;
    private MoveKeeper moveKeeper;
    private Bundler bundler;

    private GameState currentGameState;

    public GameController() {
        this.UIWrapper = new UIWrapper();
        this.stateManager = new StateManager(this);
        this.bundler = new Bundler(this);
        setCurrentGameState(State.MAIN_MENU);
    }

    public <T extends Button> void registerButtonsForGameState(List<T> buttonList) {
        buttonList.forEach(button -> button.setOnMouseClicked(mouseEvent -> {
            T currButton = (T) mouseEvent.getSource();
            if (mouseEvent.getButton() == MouseButton.PRIMARY)
                currentGameState.onPrimaryClickHandler(currButton);
            else if (mouseEvent.getButton() == MouseButton.SECONDARY)
                currentGameState.onSecondaryClickHandler(currButton);
        }));
    }

    public void setCurrentGameState(State gameState) {
        this.currentGameState = stateManager.getState(gameState);
        currentGameState.onGameStateSet();
        setCurrentNavButtonHandler(stateManager.getState(gameState));
    }

    public void setCurrentNavButtonHandler(NavButtonHandler handler) {
        UIWrapper.getLeftButton().setOnActionHandler(handler.getLeftButtonActionEvent());
        UIWrapper.getRightButton().setOnActionHandler(handler.getRightButtonActionEvent());
    }

    public void setModelBoard(ModelBoard modelBoard) {
        this.modelBoard = modelBoard;
    }

    public void setViewBoard(ViewBoard viewBoard) {
        this.viewBoard = viewBoard;
    }

    public void setMoveKeeper(MoveKeeper moveKeeper) {
        this.moveKeeper = moveKeeper;
    }

    public MoveKeeper getMoveKeeper() {
        return moveKeeper;
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

    public GameState getCurrentGameState() {
        return currentGameState;
    }
}

