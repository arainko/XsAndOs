package com.arainko.xno.view.buttons;

import javafx.scene.control.Button;

public class MenuButton extends Button {
    public enum Functionality {
        CREATE_BOARD, LOAD_BOARD, MAIN_MENU, HELP, SAVE, EXIT, UNSPECIFIED
    }
    private Functionality functionality;

    public MenuButton(String text, Functionality functionality, int sizeX, int sizeY) {
        super(text);
        this.setId("menu-button");
        this.setPrefSize(sizeX, sizeY);
        this.functionality = functionality;
    }

    public MenuButton(String text, int sizeX, int sizeY) {
        super(text);
        this.setId("menu-button");
        this.setPrefSize(sizeX, sizeY);
        this.functionality = Functionality.UNSPECIFIED;
    }

    public Functionality getFunctionality() {
        return functionality;
    }
}
