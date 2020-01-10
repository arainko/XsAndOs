package com.arainko.xno.controller.gamestates;

import com.arainko.xno.controller.abstracts.GameStateHandler;
import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.helpers.Bundler;
import com.arainko.xno.controller.helpers.StateManager;
import com.arainko.xno.view.screens.LoadScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/* This GameState handles the load button from file screen allowing
* the user to specify which file to load and commence the game on */

public class GameLoaderState extends GameStateHandler {
    private String saveFileDirPath;
    private LoadScreen loadScreen;
    public GameLoaderState(GameController gameController) {
        super(gameController);
    }

    @Override
    public void onGameStateSet() {
        saveFileDirPath = System.getProperty("user.home")+"/.xnosaves";
        new File(saveFileDirPath).mkdir();
        loadScreen = new LoadScreen();
        getGameController().registerButtonsForClickHandler(loadScreen.getButtonList());
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
            getGameController().setCurrentGameState(StateManager.State.GAME_RUNNING);
        } catch (IOException | ClassNotFoundException e) {
            loadScreen.setInfoText("File's corrupted,\nchoose a different one:");
        }

    }

    @Override
    public EventHandler<ActionEvent> getLeftButtonActionEvent() {
        return event -> getGameController().setCurrentGameState(StateManager.State.MAIN_MENU);
    }

}
