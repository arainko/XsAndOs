package com.arainko.xno.controller.interfaces;

import javafx.scene.control.Button;

public interface InternalGameState {
    <T extends Button> void onInternalGameStatePrimaryClickHandler(T button);
    <T extends Button> void onInternalGameStateSecondaryClickHandler(T button);
}
