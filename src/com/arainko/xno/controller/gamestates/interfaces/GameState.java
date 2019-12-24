package com.arainko.xno.controller.gamestates.interfaces;

import javafx.scene.control.Button;

public interface GameState {
    void onGameStatePrimaryClickHandler(Button button);
    void onGameStateSecondaryClickHandler(Button button);
}
