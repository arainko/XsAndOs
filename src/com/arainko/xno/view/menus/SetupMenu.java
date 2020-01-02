package com.arainko.xno.view.menus;

import javafx.scene.text.Text;

public class SetupMenu extends Menu<MenuButton> {
     public SetupMenu() {
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
             MenuButton sizeButton = new MenuButton(i + "x" + i, 150, 30);
             this.getChildren().add(sizeButton);
             addButton(sizeButton);
         }
     }
}
