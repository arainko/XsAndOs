package com.arainko.xno.view.screens;

import com.arainko.xno.view.buttons.MenuButton;
import javafx.scene.text.Text;

public class EndGameScreen extends Screen<EndGameScreen.EndGameMenuButton> {
    public static class EndGameMenuButton extends MenuButton {
        public enum Functionality {
            MAIN_MENU, EXIT
        }
        private Functionality functionality;

        public EndGameMenuButton(String text, Functionality functionality, int sizeX, int sizeY) {
            super(text, sizeX, sizeY);
            this.functionality = functionality;
        }

        public Functionality getFunctionality() {
            return functionality;
        }
    }

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
        EndGameMenuButton mainMenuButton = new EndGameMenuButton("Go to main menu", EndGameMenuButton.Functionality.MAIN_MENU, 300, 30);
        EndGameMenuButton exitButton = new EndGameMenuButton("Exit", EndGameMenuButton.Functionality.EXIT, 300, 30);
        addButtons(mainMenuButton, exitButton);
    }
}
