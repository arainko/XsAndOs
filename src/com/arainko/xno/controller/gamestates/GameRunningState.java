package com.arainko.xno.controller.gamestates;

import com.arainko.xno.helpers.Cords;
import com.arainko.xno.controller.GameController;
import com.arainko.xno.controller.gamestates.interfaces.GameState;
import com.arainko.xno.model.elements.Connection;
import javafx.scene.control.Button;

import java.util.List;
import java.util.function.Consumer;

public class GameRunningState implements GameState {
    private GameController gameController;
    private Button lastClickedButton;
    private List<Cords> lastClickedButtonsNeighbors;
    private Connection connectionBeingBuilt;

    public GameRunningState(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void onGameStateClickHandler(Button button) {
        lastClickCleanUp();
        onRunningGameClickHandler(button);
    }

    private void onRunningGameClickHandler(Button button) {
        Cords buttonCords = gameController.getViewBoard().getButtonCords(button);
        List<Cords> clickedButtonNeighbors = gameController.getModelBoard().getFreeNeighborsAt(buttonCords);
        clickedButtonNeighbors.forEach(cords ->
                gameController.getViewBoard()
                        .getButtonAt(cords)
                        .setId("neighbor-button"));
        lastClickedButton = button;
        lastClickedButtonsNeighbors = clickedButtonNeighbors;
    }

    private void lastClickCleanUp() {
        if (lastClickedButton != null)
            lastClickedButtonsNeighbors.forEach(cords ->
                gameController.getViewBoard()
                        .getButtonAt(cords)
                        .setId("custom-button"));
    }
}
