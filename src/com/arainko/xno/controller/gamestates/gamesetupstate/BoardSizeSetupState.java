package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.abstracts.InternalAbstractGameState;
import com.arainko.xno.view.SetupMenu;
import javafx.scene.control.Button;

public class BoardSizeSetupState extends InternalAbstractGameState<GameSetupState> {
    SetupMenu setupMenu;
    int clickedDim;

    public BoardSizeSetupState(GameSetupState parentGameState) {
        super(parentGameState);
        setupMenu = new SetupMenu();
        setupButtonsAction();
        parentGameState.getGameController().getBorderPane().setCenter(setupMenu);
    }

    private void setupButtonsAction() {
        setupMenu.setButtonsOnMouseClicked(event -> {
            Button btn = (Button) event.getSource();
            clickedDim = 5 + setupMenu.getButtonList().indexOf(btn);
            btn.setText(clickedDim + "");
        });
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {}
}
