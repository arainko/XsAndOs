package com.arainko.xno.controller.gamestates.interfaces;

import javafx.scene.control.Button;

public interface InternalGameState {
    void onInternalGameStateClickHandler(Button button);
   default void onInternalGameStateSecondaryClickHandler(Button button) {}
}
