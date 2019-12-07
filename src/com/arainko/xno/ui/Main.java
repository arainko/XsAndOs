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
        GridPane gridPane = new GridPane();


        for (int i=0; i < 10; i++) {
            for (int j=0; j<10; j++) {
                Button button = new Button("  ");
                button.setId("custom-button");
                button.setShape(new Rectangle(10,10));
                gridPane.addRow(i, button);
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        scene.getStylesheets().add("style.css");
        gridPane.setAlignment(Pos.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
