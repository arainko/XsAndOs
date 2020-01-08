package com.arainko.xno.controller.gamestates.gamerunning;

import com.arainko.xno.abstracts.InternalClickHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.view.ui.GameButtonBar;
import javafx.scene.control.Button;

import static com.arainko.xno.view.ui.GameButtonBar.*;

public class InternalButtonBarHandler extends InternalClickHandler<GameRunningState> {

    private GameButtonBar gameButtonBar;
    private InternalConnectionBuilder connectionBuilder;

    public InternalButtonBarHandler(GameRunningState parentGameState) {
        super(parentGameState);
        gameButtonBar = parentGameState.getGameController()
                .getUIWrapper()
                .getGameButtonBar();
        connectionBuilder = (InternalConnectionBuilder) getParentGameState().getConnectionBuilder();
    }

    @Override
    public <T extends Button> void onPrimaryClickHandler(T button) {
        BarButton clickedButton = (BarButton) button;
        switch (clickedButton.getFunctionality()) {
            case SAVE:
                if (getParentGameState().getCurrentInternalGameState() == connectionBuilder) {
                    connectionBuilder.pauseState();
                    getParentGameState().getGameController().getBundler().saveBundle();
                    connectionBuilder.resumeState();
                } else getParentGameState().getGameController().getBundler().saveBundle();
                break;
            case MAIN_MENU:
                getParentGameState().onGameStateExit();
                getParentGameState().getGameController().setCurrentGameState(GameController.State.MAIN_MENU);
                break;
        }
    }
}
