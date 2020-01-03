package com.arainko.xno.controller.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public interface GameState {
    void onGameStateSet();
    <T extends Button> void onGameStatePrimaryClickHandler(T button);
    default <T extends Button> void onGameStateSecondaryClickHandler(T button) {
        // in case a GameState doesn't make use of secondary clicks
    }

    // defaults to null in case GameState doesn't implement the behavior of ArrowButtons
    default EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return null;
    }
    default EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }
}
