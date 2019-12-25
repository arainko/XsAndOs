package com.arainko.xno.controller.gamestates.gamemainmenustate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.GameController;
import com.arainko.xno.view.SetupMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameBoardSizeSetupState extends GameStateHandler {
    SetupMenu setupMenu;

    public GameBoardSizeSetupState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        setupMenu = new SetupMenu();
        setupButtonsAction();
        getGameController().getUIWrapper().changeMainView(setupMenu);
    }

    @Override
    public void onGameStatePrimaryClickHandler(Button button) {
        int clickedDim = 5 + setupMenu.getButtonList().indexOf(button);
        getGameController().setupBoards(clickedDim);
        GridPane viewButtons = getGameController()
                .getViewBoard()
                .getButtonGridPane();

        getGameController().setCurrentGameState(getGameController().getGameSetupState());

        getGameController().getUIWrapper()
                .changeMainView(viewButtons);
    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return null;
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }

    private void setupButtonsAction() {
        setupMenu.setButtonsOnMouseClicked(event -> {
            Button clickedButton = (Button) event.getSource();
            this.onGameStatePrimaryClickHandler(clickedButton);
        });
    }
}
