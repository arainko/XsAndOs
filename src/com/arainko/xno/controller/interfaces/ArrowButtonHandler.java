package com.arainko.xno.controller.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface ArrowButtonHandler {
    default EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return null;
    }
    default EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }
}