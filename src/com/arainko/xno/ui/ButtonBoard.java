package com.arainko.xno.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ButtonBoard extends GridPane {
    int dimX;
    int dimY;
    Button[][] board;

    public ButtonBoard(int dimX, int dimY) {
        //TODO: Add setters for dimensions
        this.dimX = dimX;
        this.dimY = dimY;
        board = new Button[dimY][dimX];
        setupButtonBoard(dimX, dimY);
        setupButtonEventHandlers();
    }

    private void setupButtonBoard(int dimX, int dimY) {
        for (int i=0; i < dimY; i++) {
            for (int j=0; j < dimX; j++) {
                Button button = new Button("  ");
                button.setId("custom-button");
                button.setPrefSize(50,50);
                this.addRow(i, button);
                board[i][j] = button;
            }
        }
//        Button button = new Button("COS");
//        button.setId("custom-button");
//
//        button.setPrefSize(300, 300);
//        this.addRow(1, button);
    }

    private void setupButtonEventHandlers() {
        for (Button[] row : board)
            for (Button button : row) {
                button.setOnAction(eventHandler -> {
                    button.setId("clicked-button");
                });
            }
    }

}
