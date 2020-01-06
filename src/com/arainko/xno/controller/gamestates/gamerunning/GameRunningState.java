package com.arainko.xno.controller.gamestates.gamerunning;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.controller.interfaces.ClickHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameRunningState extends GameStateHandler {
    private ClickHandler XOWatcher;
    private ClickHandler connectionBuilder;
    private ClickHandler currentInternalGameState;

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
        btn.setOnAction(event -> getGameController().getBundler().saveBundle());
        getGameController().getUIWrapper().setBottom(btn);
        arrowButtonsSupervisor();
    }

    @Override
    public void onPrimaryClickHandler(Button button) {
        getCurrentInternalGameState().onPrimaryClickHandler(button);
        arrowButtonsSupervisor();
    }

    @Override
    public void onSecondaryClickHandler(Button button) {
        getCurrentInternalGameState().onSecondaryClickHandler(button);
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

    public void setCurrentInternalGameState(ClickHandler currentInternalGameState) {
        this.currentInternalGameState = currentInternalGameState;
    }

    public ClickHandler getXOWatcher() {
        return XOWatcher;
    }

    public ClickHandler getConnectionBuilder() {
        return connectionBuilder;
    }

    public ClickHandler getCurrentInternalGameState() {
        return currentInternalGameState;
    }

    public MoveKeeper getMoveKeeper() {
        return moveKeeper;
    }
}
