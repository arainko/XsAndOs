package com.arainko.xno.view.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UIWrapper extends BorderPane {
    private ArrowButton leftButton;
    private ArrowButton rightButton;

    public UIWrapper() {
        this.setBackground(Background.EMPTY);
        leftButton = new ArrowButton("<");
        rightButton = new ArrowButton(">");
        BorderPane.setAlignment(leftButton, Pos.CENTER);
        BorderPane.setAlignment(rightButton, Pos.CENTER);
        this.setLeft(leftButton);
        this.setRight(rightButton);
    }

    public void changeMainView(Pane pane) {
        this.setCenter(pane);
    }

    public ArrowButton getLeftButton() {
        return leftButton;
    }

    public ArrowButton getRightButton() {
        return rightButton;
    }
}
