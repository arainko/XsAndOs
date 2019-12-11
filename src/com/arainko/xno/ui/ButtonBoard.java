package com.arainko.xno.ui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

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
                this.addRow(i, button);
                board[i][j] = button;
            }
        }
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
