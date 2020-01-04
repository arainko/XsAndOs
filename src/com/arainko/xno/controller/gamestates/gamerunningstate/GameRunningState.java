package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.controller.interfaces.InternalGameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameRunningState extends GameStateHandler {
    private InternalGameState XOWatcher;
    private InternalGameState connectionBuilder;
    private InternalGameState currentInternalGameState;

    private MoveKeeper moveKeeper;

    public GameRunningState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        XOWatcher = new InternalXOWatcher(this);
        connectionBuilder = new InternalConnectionBuilder(this);
        moveKeeper = getGameController().getMoveKeeper();
        setCurrentInternalGameState(XOWatcher);
        getGameController().registerButtonsForGameState(getGameController()
                .getViewBoard()
                .getFlattenedBoardElements());
        getGameController().getUIWrapper().changeMainView(getGameController()
                .getViewBoard()
                .getButtonGrid());

        Button btn = new Button("TEST");
        btn.setOnAction(event -> {
            getGameController().getBundler().saveBundle();
        });
        getGameController().getUIWrapper().setBottom(btn);
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
                ((InternalConnectionBuilder) connectionBuilder).onStateAbortHandler();
            } else {
                getMoveKeeper().evaluateCommand(MoveKeeper.Command.UNDO);
                arrowButtonsSupervisor();
            }
        };
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return event -> {
            if (getCurrentInternalGameState() == connectionBuilder) {
                ((InternalConnectionBuilder) connectionBuilder).onStateAbortHandler();
            } else {
                getMoveKeeper().evaluateCommand(MoveKeeper.Command.REDO);
                arrowButtonsSupervisor();
            }
        };
    }

    private void arrowButtonsSupervisor() {
        getGameController().getUIWrapper().getLeftButton()
                .setDisable(getMoveKeeper().keeperPred(keeper -> keeper.getCurrentIndex() > -1));
        getGameController().getUIWrapper().getRightButton()
                .setDisable(getMoveKeeper().keeperPred(keeper -> keeper.getCurrentIndex() + 1 < keeper.getKeptMovesSize()));
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
}
