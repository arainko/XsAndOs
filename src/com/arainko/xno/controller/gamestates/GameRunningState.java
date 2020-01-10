package com.arainko.xno.controller.gamestates;

import com.arainko.xno.controller.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.controller.helpers.StateManager;
import com.arainko.xno.controller.interfaces.ClickHandler;
import com.arainko.xno.view.ui.GameButtonBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import static com.arainko.xno.model.predicates.BoardPredicates.done;

/* This GameStateHandler controls the flow of the game itself while also
* providing ClickHandlers for its InternalClickHandlers and the GameButtonBar.
*
* NavButtonHandlers provided in this GameState allow the user to traverse the history of
* moves he made while playing the game. */

public class GameRunningState extends GameStateHandler {
    private ClickHandler XOWatcher;
    private ClickHandler connectionBuilder;
    private ClickHandler buttonBarHandler;
    private ClickHandler currentInternalGameState;

    private MoveKeeper moveKeeper;
    private GameButtonBar gameButtonBar;

    public GameRunningState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        XOWatcher = new InternalXOWatcher(this);
        connectionBuilder = new InternalConnectionBuilder(this);
        buttonBarHandler = new InternalButtonBarHandler(this);
        moveKeeper = getGameController().getMoveKeeper();
        gameButtonBar = getGameController().getUIWrapper().getGameButtonBar();
        setCurrentInternalGameState(XOWatcher);
        getGameController().registerButtonsForClickHandler(getGameController()
                .getViewBoard()
                .getFlattenedBoardElements());
        getGameController().registerButtonsForClickHandler(gameButtonBar.getButtonList());
        getGameController().getUIWrapper().changeMainView(getGameController()
                .getViewBoard()
                .getButtonGrid());
        getGameController().getUIWrapper().getGameButtonBar().setVisible(true);
        arrowButtonsSupervisor();
    }

    void onGameStateExit() {
        getGameController().getUIWrapper().getLeftButton().setDisable(false);
        getGameController().getUIWrapper().getRightButton().setDisable(false);
        getGameController().getUIWrapper().getGameButtonBar().setVisible(false);
    }

    @Override
    public void onPrimaryClickHandler(Button button) {
        if (button.getParent() == gameButtonBar)
            buttonBarHandler.onPrimaryClickHandler(button);
        else {
            getCurrentInternalGameState().onPrimaryClickHandler(button);
            arrowButtonsSupervisor();
        }
        if (getGameController().getModelBoard().isBoard(done())) {
            onGameStateExit();
            getGameController().setCurrentGameState(StateManager.State.END);
        }
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
