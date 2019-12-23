package com.arainko.xno.view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SetupMenu extends VBox {
    private List<Button> buttonList;
    private Button proceedButton;

     public SetupMenu() {
         buttonList = new ArrayList<>();
         this.setId("setup-menu");
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
         proceedButton = new Button("Proceed with:");
         this.getChildren().add(proceedButton);
     }

     public void setButtonsOnMouseClicked(EventHandler<MouseEvent> onMouseActionEvent) {
         buttonList.forEach( button -> button.setOnMouseClicked(onMouseActionEvent));
     }

    public Button getProceedButton() {
        return proceedButton;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }
}
