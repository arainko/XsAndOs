package com.arainko.xno.controller.interfaces;

import javafx.scene.control.Button;

public interface ClickHandler {
    <T extends Button> void onPrimaryClickHandler(T button);
    default <T extends Button> void onSecondaryClickHandler(T button) {
    }
}
