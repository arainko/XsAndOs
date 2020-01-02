package com.arainko.xno.view.menus;

import javafx.scene.control.Button;

public class MenuButton extends Button {
    public enum Functionality {
        CREATE, LOAD, OTHER;
    }
    private Functionality buttonFunctionality;

    public MenuButton(String text, int sizeX, int sizeY, Functionality functionality) {
        super(text);
        this.buttonFunctionality = functionality;
        this.setId("menu-button");
        this.setPrefSize(sizeX, sizeY);
    }

    public MenuButton(String text, int sizeX, int sizeY) {
        super(text);
        this.buttonFunctionality = Functionality.OTHER;
        this.setId("menu-button");
        this.setPrefSize(sizeX, sizeY);
    }

    public Functionality getButtonFunctionality() {
        return buttonFunctionality;
    }
}
