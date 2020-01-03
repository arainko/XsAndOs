package com.arainko.xno.controller.gamestates.gamemainmenustate;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.File;

public class GameLoaderState extends GameStateHandler {
    String savefileDirPath;
    public GameLoaderState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        savefileDirPath = System.getProperty("user.home")+"/.xnosaves";
        new File(savefileDirPath).mkdir();
    }

    @Override
    public <T extends Button> void onGameStatePrimaryClickHandler(T button) {

    }

    @Override
    public <T extends Button> void onGameStateSecondaryClickHandler(T button) {

    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return null;
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }
}
