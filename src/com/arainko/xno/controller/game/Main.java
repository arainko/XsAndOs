package com.arainko.xno.controller.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        primaryStage.setTitle("Game Board");
        GameController gc = new GameController();
        Scene scene = new Scene(gc.getUIWrapper(), 600, 600);
        scene.setFill(Color.rgb(66,66,66));
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
