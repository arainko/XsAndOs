package com.arainko.xno.view.menus;

import javafx.scene.text.Text;

public class MainMenu extends Menu<MenuButton> {
    public MainMenu() {
        setupInfoText();
        setupButtons();
    }

    private void setupButtons() {
        MenuButton createButton = new MenuButton("Create custom board",
                300, 30,
                MenuButton.Functionality.CREATE);
        MenuButton loadButton = new MenuButton("Load board from file",
                300, 30,
                MenuButton.Functionality.LOAD);
        addButtons(createButton, loadButton);
    }

    private void setupInfoText() {
        Text text = new Text("Xs and Os");
        text.setId("header-text");
        this.getChildren().add(text);
    }

}
