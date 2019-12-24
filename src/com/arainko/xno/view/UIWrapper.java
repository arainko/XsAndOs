package com.arainko.xno.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UIWrapper extends BorderPane {
    private Button leftButton;
    private Button rightButton;

    public UIWrapper() {
        this.setBackground(Background.EMPTY);
        leftButton = new Button("<");
        rightButton = new Button(">");
        leftButton.setId("menu-button");
        rightButton.setId("menu-button");
        BorderPane.setAlignment(leftButton, Pos.CENTER);
        BorderPane.setAlignment(rightButton, Pos.CENTER);
        this.setLeft(leftButton);
        this.setRight(rightButton);
        leftButton.setAlignment(Pos.CENTER);
        rightButton.setAlignment(Pos.CENTER);
    }

    public void changeMainView(Pane pane) {
        this.setCenter(pane);
        leftButton.setPrefHeight(300);
        rightButton.setPrefHeight(300);
    }


}
