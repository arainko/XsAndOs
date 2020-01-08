package com.arainko.xno.view.screens;

import com.arainko.xno.view.buttons.MenuButton;
import javafx.scene.text.Text;

public class EndGameScreen extends Screen<MenuButton> {
    public EndGameScreen() {
        setupInfoText();
        setupButtons();
    }

    private void setupInfoText() {
        Text infoText = new Text("You've beaten the puzzle");
        infoText.setId("menu-text");
        getChildren().add(infoText);
    }

    private void setupButtons() {
        MenuButton mainMenuButton = new MenuButton("Go to main menu", MenuButton.Functionality.MAIN_MENU, 300, 30);
        MenuButton exitButton = new MenuButton("Exit", MenuButton.Functionality.EXIT, 300, 30);
        addButtons(mainMenuButton, exitButton);
    }
}
