package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game Board");
        GridPane gridPane = new GridPane();


        for (int i=0; i < 10; i++) {
            for (int j=0; j<10; j++) {
                Button button = new Button("cos");
                button.setId("custom-button");
                gridPane.addRow(i, button);
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        scene.getStylesheets().add("/styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
