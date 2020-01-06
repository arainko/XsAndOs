package com.arainko.xno.controller.gamestates.mainmenu;

import com.arainko.xno.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.Bundler;
import com.arainko.xno.view.screens.LoadScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameLoaderState extends GameStateHandler {
    private String saveFileDirPath;
    public GameLoaderState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        saveFileDirPath = System.getProperty("user.home")+"/.xnosaves";
        new File(saveFileDirPath).mkdir();
        LoadScreen loadScreen = new LoadScreen();
        getGameController().registerButtonsForGameState(loadScreen.getButtonList());
        getGameController().getUIWrapper().setCenter(loadScreen.getWrapper());
    }

    @Override
    public <T extends Button> void onPrimaryClickHandler(T button) {
        try {
            FileInputStream fis = new FileInputStream(saveFileDirPath + "/" + button.getText());
            ObjectInputStream ois = new ObjectInputStream(fis);
            Bundler.Bundle savedBundle = (Bundler.Bundle) ois.readObject();
            ois.close();
            getGameController().getBundler().loadBundle(savedBundle);
            getGameController().setCurrentGameState(GameController.State.GAME_RUNNING);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public <T extends Button> void onSecondaryClickHandler(T button) {

    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(GameController.State.MAIN_MENU);
    }

    @Override
    public EventHandler<ActionEvent> getRightButtonActionEvent() {
        return null;
    }
}
