package com.arainko.xno.controller.gamestates.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public interface GameState {
    void onGameStateSet();
    void onGameStatePrimaryClickHandler(Button button);
    void onGameStateSecondaryClickHandler(Button button);
    EventHandler<ActionEvent> getLeftButtonActionEvent();
    EventHandler<ActionEvent> getRightButtonActionEvent();
}
