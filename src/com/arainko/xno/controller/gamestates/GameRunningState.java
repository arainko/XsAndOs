package com.arainko.xno.controller.gamestates;

import com.arainko.xno.controller.GameController;
import com.arainko.xno.interfaces.GameState;
import com.arainko.xno.model.elements.Cell;
import javafx.scene.control.Button;

import java.util.List;

public class GameRunningState implements GameState {
    private GameController gameController;
    private Button lastClickedButton;
    private List<Cell> lastClickedButtonsNeighbors;


    public GameRunningState(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void onGameStateClickHandler(Button button) {
        if (lastClickedButton != null) {
            lastClickedButtonsNeighbors.forEach(cell ->
                    gameController.getViewBoard()
                            .getButtonAt(cell.getCordX(), cell.getCordY())
                            .setId("custom-button"));
        }
        int[] buttonPos = gameController.getViewBoard().getButtonRowCol(button);
        List<Cell> clickedButtonNeighbors = gameController.getModelBoard().getFreeNeighborsAt(buttonPos[0], buttonPos[1]);
        clickedButtonNeighbors.forEach(cell ->
                gameController.getViewBoard()
                        .getButtonAt(cell.getCordX(), cell.getCordY())
                        .setId("neighbor-button"));
        lastClickedButton = button;
        lastClickedButtonsNeighbors = clickedButtonNeighbors;
    }
}
