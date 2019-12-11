package com.arainko.xno.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game Board");
        ButtonBoard buttonBoard = new ButtonBoard(10,10);


        Scene scene = new Scene(buttonBoard, 400, 400);
        scene.getStylesheets().add("style.css");
        buttonBoard.setAlignment(Pos.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
