package com.arainko.xno.controller.interfaces;

import javafx.scene.control.Button;

/* ClickHandlers handle primary and secondary clicks on UI elements
* - the UI elements must be previously registered inside GameController with the registerForClickHandler method
* to make use of ClickHandlers in a proper way */

public interface ClickHandler {
    <T extends Button> void onPrimaryClickHandler(T button);
    default <T extends Button> void onSecondaryClickHandler(T button) {
    }
}
