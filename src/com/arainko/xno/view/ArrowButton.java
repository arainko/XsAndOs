package com.arainko.xno.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class ArrowButton extends Button {
    public ArrowButton(String text) {
        super(text);
        this.setId("menu-button");
        this.setPrefHeight(300);
        this.setAlignment(Pos.CENTER);
    }
}
