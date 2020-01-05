package com.arainko.xno.view.menus;

import javafx.scene.text.Text;

public class MainMenu extends Menu<MainMenu.MainMenuButton> {
    public static class MainMenuButton extends MenuButton {
        public enum Functionality {
            CREATE, LOAD, HELP
        }
        private Functionality buttonFunctionality;

        public MainMenuButton(String text, int sizeX, int sizeY, Functionality functionality) {
            super(text, sizeX, sizeY);
            this.buttonFunctionality = functionality;
        }

        public Functionality getButtonFunctionality() {
            return buttonFunctionality;
        }

    }

    public MainMenu() {
        setupInfoText();
        setupButtons();
    }

    private void setupButtons() {
        MainMenuButton createButton = new MainMenuButton("Create custom board",
                300, 30,
                MainMenuButton.Functionality.CREATE);
        MainMenuButton loadButton = new MainMenuButton("Load board from file",
                300, 30,
                MainMenuButton.Functionality.LOAD);
        MainMenuButton helpButton = new MainMenuButton("Help",
                300, 30,
                MainMenuButton.Functionality.HELP);
        addButtons(createButton, loadButton, helpButton);
    }

    private void setupInfoText() {
        Text text = new Text("Xs and Os");
        text.setId("header-text");
        this.getChildren().add(text);
    }
}
