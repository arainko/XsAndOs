package com.arainko.xno.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class ArrowButton extends Button {
    public ArrowButton(String text) {
        super(text);
        this.setId("menu-button");
        this.setPrefHeight(300);
        this.setAlignment(Pos.CENTER);
    }

    public void setOnActionEnhanced(EventHandler<ActionEvent> event) {
        this.setOnAction(event);
        this.setVisible(event != null);
    }

}
