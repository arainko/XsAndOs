package com.arainko.xno.controller.gamestates;

import com.arainko.xno.controller.abstracts.InternalClickHandler;
import com.arainko.xno.controller.helpers.StateManager;
import com.arainko.xno.view.buttons.MenuButton;
import javafx.scene.control.Button;

/* This ClickHandler handles the behavior of GameButtonBar (Save and Main Menu buttons visible on the top-left) */

public class InternalButtonBarHandler extends InternalClickHandler<GameRunningState> {

    private InternalConnectionBuilder connectionBuilder;

    public InternalButtonBarHandler(GameRunningState parentGameState) {
        super(parentGameState);
        connectionBuilder = (InternalConnectionBuilder) getParentGameState().getConnectionBuilder();
    }

    @Override
    public <T extends Button> void onPrimaryClickHandler(T button) {
        MenuButton clickedButton = (MenuButton) button;
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
                getParentGameState().getGameController().setCurrentGameState(StateManager.State.MAIN_MENU);
                break;
        }
    }
}
