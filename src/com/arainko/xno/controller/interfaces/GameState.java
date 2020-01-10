package com.arainko.xno.controller.interfaces;

/* GameStates control whichever screen the user is currently on. */

public interface GameState extends ClickHandler, NavButtonHandler {

    /* Any class implementing this interface should initialize their fields
    * inside onGameStateSet to allow for fluent and non-problematic state-to-state switching */

    void onGameStateSet();
}
