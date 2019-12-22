package com.arainko.xno.controller.gamestates.interfaces;

import com.arainko.xno.controller.GameController;
import javafx.scene.control.Button;

public interface GameState {
    void onGameStatePrimaryClickHandler(Button button);
    void onGameStateSecondaryClickHandler(Button button);
    GameController getGameController();
}
