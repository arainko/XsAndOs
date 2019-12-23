package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.abstracts.InternalAbstractGameState;
import com.arainko.xno.view.SetupMenu;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardSizeSetupState extends InternalAbstractGameState<GameSetupState> {
    SetupMenu setupMenu;

    public BoardSizeSetupState(GameSetupState parentGameState) {
        super(parentGameState);
        setupMenu = new SetupMenu();
        setupButtonsAction();
        parentGameState.getGameController().getUIWrapper().setCenter(setupMenu);
    }

    private void setupButtonsAction() {
        setupMenu.setButtonsOnMouseClicked(event -> {
            Button clickedButton = (Button) event.getSource();
            this.onInternalGameStateClickHandler(clickedButton);
        });
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {
        int clickedDim = 5 + setupMenu.getButtonList().indexOf(button);
        getParentGameState().getGameController().setupBoards(clickedDim);
        GridPane viewButtons = getParentGameState().getGameController()
                .getViewBoard()
                .getButtonGridPane();

        getParentGameState().getGameController()
                .getUIWrapper()
                .setCenter(viewButtons);

        getParentGameState().setCurrentInternalGameState();
    }
}
