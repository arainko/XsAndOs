package com.arainko.xno.controller.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* Created by Aleksander Rainko */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Xs and Os");
        GameController gc = new GameController();
        Scene scene = new Scene(gc.getUIWrapper(), 600, 600);
        scene.setFill(Color.rgb(45, 50, 54));
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
