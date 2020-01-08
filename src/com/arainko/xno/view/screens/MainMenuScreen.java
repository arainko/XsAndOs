package com.arainko.xno.view.screens;

import com.arainko.xno.view.buttons.MenuButton;
import javafx.scene.text.Text;

public class MainMenuScreen extends Screen<MenuButton> {


    public MainMenuScreen() {
        setupInfoText();
        setupButtons();
    }

    private void setupButtons() {
        MenuButton createButton = new MenuButton("Create custom board",
                MenuButton.Functionality.CREATE_BOARD,300, 30);
        MenuButton loadButton = new MenuButton("Load board from file",
                MenuButton.Functionality.LOAD_BOARD, 300, 30);
        MenuButton helpButton = new MenuButton("Help",
                MenuButton.Functionality.HELP,300, 30);
        addButtons(createButton, loadButton, helpButton);
    }

    private void setupInfoText() {
        Text text = new Text("Xs and Os");
        text.setId("header-text");
        this.getChildren().add(text);
    }
}
