package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.InternalGameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameRunningState extends GameStateHandler {
    private InternalGameState XOWatcher;
    private InternalGameState connectionBuilder;
    private InternalGameState currentInternalGameState;

    private MoveKeeper moveKeeper;
    private BoardManipulator boardManipulator;

    public GameRunningState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        XOWatcher = new InternalXOWatcher(this);
        connectionBuilder = new InternalConnectionBuilder(this);
        boardManipulator = new BoardManipulator(getGameController().getModelBoard(), getGameController().getViewBoard());
        moveKeeper = new MoveKeeper(boardManipulator);
        setCurrentInternalGameState(XOWatcher);
        arrowButtonsSupervisor();
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        getCurrentInternalGameState().onInternalGameStatePrimaryClickHandler(button);
        arrowButtonsSupervisor();
    }

    @Override
    public void onGameStateSecondaryClickHandler(Button button) {
        getCurrentInternalGameState().onInternalGameStateSecondaryClickHandler(button);
        arrowButtonsSupervisor();
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return event -> {
            if (getCurrentInternalGameState() == connectionBuilder) {
                getCurrentInternalGameState()
                        .onInternalGameStateSecondaryClickHandler(getBoardManipulator().spoofButton());
            } else {
                getMoveKeeper().evaluateCommand(MoveKeeper.Command.UNDO);
                arrowButtonsSupervisor();
                System.out.println("Current index: " + moveKeeper.getCurrentIndex());
            }
        };
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return event -> {
            if (getCurrentInternalGameState() == connectionBuilder) {
                getCurrentInternalGameState()
                        .onInternalGameStateSecondaryClickHandler(getBoardManipulator().spoofButton());
            } else {
                getMoveKeeper().evaluateCommand(MoveKeeper.Command.REDO);
                arrowButtonsSupervisor();
                System.out.println("Current index: " + moveKeeper.getCurrentIndex());
            }
        };
    }

    private void arrowButtonsSupervisor() {
        getGameController().getUIWrapper().getLeftButton()
                .setDisable(!getMoveKeeper().areMoves(keeper -> keeper.getCurrentIndex() > -1));
        getGameController().getUIWrapper().getRightButton()
                .setDisable(!getMoveKeeper().areMoves(keeper -> keeper.getCurrentIndex()+1 < keeper.getKeptMovesSize()));
    }

    public void setCurrentInternalGameState(InternalGameState currentInternalGameState) {
        this.currentInternalGameState = currentInternalGameState;
    }

    public InternalGameState getXOWatcher() {
        return XOWatcher;
    }

    public InternalGameState getConnectionBuilder() {
        return connectionBuilder;
    }

    public InternalGameState getCurrentInternalGameState() {
        return currentInternalGameState;
    }

    public MoveKeeper getMoveKeeper() {
        return moveKeeper;
    }

    public BoardManipulator getBoardManipulator() {
        return boardManipulator;
    }
}
