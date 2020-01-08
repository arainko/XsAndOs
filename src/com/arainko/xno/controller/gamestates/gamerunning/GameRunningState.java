package com.arainko.xno.controller.gamestates.gamerunning;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.MoveKeeper;
import com.arainko.xno.controller.interfaces.ClickHandler;
import com.arainko.xno.view.ui.GameButtonBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import static com.arainko.xno.model.predicates.BoardPredicates.done;

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
        getGameController().registerButtonsForGameState(getGameController()
                .getViewBoard()
                .getFlattenedBoardElements());
        getGameController().registerButtonsForGameState(gameButtonBar.getButtonList());
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
            getGameController().setCurrentGameState(GameController.State.END);
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

//    private void setupGameButtonBar() {
//        getGameController().getUIWrapper().getGameButtonBar().setVisible(true);
//        List<BarButton> buttonList = getGameController().getUIWrapper()
//                .getGameButtonBar()
//                .getButtonList();
//        for (BarButton button : buttonList) {
//            if (button.getFunctionality() == BarButton.Functionality.SAVE)
//                button.setOnAction(event -> {
//                if (currentInternalGameState == connectionBuilder) {
//                    ((InternalConnectionBuilder) connectionBuilder).pauseState();
//                    getGameController().getBundler().saveBundle();
//                    ((InternalConnectionBuilder) connectionBuilder).resumeState();
//                } else getGameController().getBundler().saveBundle();
//            });
//            else if (button.getFunctionality() == BarButton.Functionality.MAIN_MENU)
//                button.setOnAction(event -> {
//                onGameStateExit();
//                getGameController().setCurrentGameState(GameController.State.MAIN_MENU);
//            });
//        }
//    }

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
