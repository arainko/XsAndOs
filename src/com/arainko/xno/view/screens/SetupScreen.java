package com.arainko.xno.view.screens;

import com.arainko.xno.view.buttons.MenuButton;
import javafx.scene.text.Text;

public class SetupScreen extends Screen<SetupScreen.SizeButton> {
    public static class SizeButton extends MenuButton {
        private int dimContainer;

        public SizeButton(String text, int dimContainer, int sizeX, int sizeY) {
            super(text, sizeX, sizeY);
            this.dimContainer = dimContainer;
        }

        public int getDimContainer() {
            return dimContainer;
        }
    }

     public SetupScreen() {
         setupInfoText();
         setupButtons();
     }

     private void setupInfoText() {
         Text text = new Text("Choose board size:");
         text.setId("menu-text");
         this.getChildren().add(text);
     }

     private void setupButtons() {
         for (int i = 5; i <= 10; i++) {
             SizeButton sizeButton = new SizeButton(i + "x" + i, i, 150, 30);
             addButton(sizeButton);
         }
     }
}
