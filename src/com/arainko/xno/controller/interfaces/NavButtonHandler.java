package com.arainko.xno.controller.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/* NavButtonHandlers control the behavior of navigation buttons
* - defaults to null which means that the buttons won't be visible by default */

public interface NavButtonHandler {
    default EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return null;
    }
    default EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }
}
