package com.arainko.xno.controller.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public interface GameState {
    void onGameStateSet();
    <T extends Button> void onGameStatePrimaryClickHandler(T button);
    <T extends Button> void onGameStateSecondaryClickHandler(T button);
    EventHandler<ActionEvent> getLeftButtonActionEvent();
    EventHandler<ActionEvent> getRightButtonActionEvent();
}
