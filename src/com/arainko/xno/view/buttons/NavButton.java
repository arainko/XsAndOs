package com.arainko.xno.view.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class NavButton extends Button {
    public NavButton(String text) {
        super(text);
        this.setId("menu-button");
        this.setPrefHeight(300);
        this.setAlignment(Pos.CENTER);
    }

    public void setOnActionHandler(EventHandler<ActionEvent> event) {
        this.setOnAction(event);
        this.setVisible(event != null);
    }

}
