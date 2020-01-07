package com.arainko.xno.view.ui;

import com.arainko.xno.view.buttons.MenuButton;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class GameButtonBar extends HBox {
    public static class BarButton extends MenuButton {
        public enum Functionality {
            SAVE, MAIN_MENU
        }
        private Functionality functionality;

        public BarButton(String text, Functionality functionality, int sizeX, int sizeY) {
            super(text, sizeX, sizeY);
            this.functionality = functionality;
            this.setId("bar-button");
        }

        public Functionality getFunctionality() {
            return functionality;
        }
    }
    List<BarButton> buttonList;

    public GameButtonBar() {
        buttonList = new ArrayList<>();
        setupButtons();
        this.setVisible(false);
    }

    private void setupButtons() {
        BarButton saveButton = new BarButton("Save", BarButton.Functionality.SAVE, 50, 30);
        BarButton mainMenuButton = new BarButton("Main Menu", BarButton.Functionality.MAIN_MENU, 75, 30);
        getChildren().addAll(saveButton, mainMenuButton);
        buttonList.add(saveButton);
        buttonList.add(mainMenuButton);
    }

    public List<BarButton> getButtonList() {
        return buttonList;
    }
}
