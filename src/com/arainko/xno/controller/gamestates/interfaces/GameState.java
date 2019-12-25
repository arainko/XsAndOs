package com.arainko.xno.controller.gamestates.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public interface GameState {
    void onGameStateSet();
    void onGameStatePrimaryClickHandler(Button button);
    default void onGameStateSecondaryClickHandler(Button button) {
        // in case a GameState doesn't use secondary clicks
    }
    EventHandler<ActionEvent> getLeftButtonActionEvent();
    EventHandler<ActionEvent> getRightButtonActionEvent();
}
