package com.arainko.xno.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SetupMenu extends VBox {
    private List<Button> buttonList;

     public SetupMenu() {
         buttonList = new ArrayList<>();
         this.setId("setup-menu");
         this.setAlignment(Pos.CENTER);
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
             Button sizeButton = new Button(i + "x" + i);
             sizeButton.setId("menu-button");
             sizeButton.setPrefSize(150, 30);
             this.getChildren().add(sizeButton);
             buttonList.add(sizeButton);
         }
     }

     public void setButtonsOnMouseClicked(EventHandler<MouseEvent> onMouseActionEvent) {
         buttonList.forEach( button -> button.setOnMouseClicked(onMouseActionEvent));
     }

    public List<Button> getButtonList() {
        return buttonList;
    }
}
