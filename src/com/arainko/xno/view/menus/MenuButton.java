package com.arainko.xno.view.menus;

import javafx.scene.control.Button;

public class MenuButton extends Button {
    public MenuButton(String text, int sizeX, int sizeY) {
        super(text);
        this.setId("menu-button");
        this.setPrefSize(sizeX, sizeY);
    }
}
