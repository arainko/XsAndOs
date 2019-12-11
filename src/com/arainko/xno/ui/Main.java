package com.arainko.xno.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game Board");
        ButtonBoard buttonBoard = new ButtonBoard(10,10);
        buttonBoard.setBackground(Background.EMPTY);
        Scene scene = new Scene(buttonBoard, 800, 600);
        scene.setFill(Color.rgb(66,66,66));
        scene.getStylesheets().add("style.css");
        buttonBoard.setAlignment(Pos.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
