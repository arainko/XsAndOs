package com.arainko.xno.controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game Board");
        GameController gc = new GameController(10,10);
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(Background.EMPTY);
        Text text = new Text("COS");
        borderPane.setBottom(text);
        borderPane.setCenter(gc.getViewBoard().getButtonGridPane());
        Scene scene = new Scene(borderPane, 600, 600);
        scene.setFill(Color.rgb(66,66,66));
        scene.getStylesheets().add("style.css");
        gc.getViewBoard().getButtonGridPane().setAlignment(Pos.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
