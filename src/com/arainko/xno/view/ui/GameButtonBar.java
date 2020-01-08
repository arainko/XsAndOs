package com.arainko.xno.view.ui;

import com.arainko.xno.view.buttons.MenuButton;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class GameButtonBar extends HBox {
    List<MenuButton> buttonList;

    public GameButtonBar() {
        buttonList = new ArrayList<>();
        setupButtons();
        this.setVisible(false);
    }

    private void setupButtons() {
        MenuButton saveButton = new MenuButton("Save", MenuButton.Functionality.SAVE, 50, 30);
        MenuButton mainMenuButton = new MenuButton("Main Menu", MenuButton.Functionality.MAIN_MENU, 75, 30);
        saveButton.setId("bar-button");
        mainMenuButton.setId("bar-button");
        getChildren().addAll(saveButton, mainMenuButton);
        buttonList.add(saveButton);
        buttonList.add(mainMenuButton);
    }

    public List<MenuButton> getButtonList() {
        return buttonList;
    }
}
