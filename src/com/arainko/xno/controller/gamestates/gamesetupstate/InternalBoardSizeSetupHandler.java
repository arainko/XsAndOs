package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.abstracts.InternalGameStateHandler;
import com.arainko.xno.view.SetupMenu;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class InternalBoardSizeSetupHandler extends InternalGameStateHandler<GameSetupState> {
    SetupMenu setupMenu;

    public InternalBoardSizeSetupHandler(GameSetupState parentGameState) {
        super(parentGameState);
        setupMenu = new SetupMenu();
        setupButtonsAction();
        parentGameState.getGameController().getUIWrapper().changeMainView(setupMenu);
//        parentGameState.getGameController().getUIWrapper().disableLeftButton();
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
                .changeMainView(viewButtons);

        getParentGameState().setBoardSizeSetupDone(true);
    }
}
