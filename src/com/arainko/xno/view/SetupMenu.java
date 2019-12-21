package com.arainko.xno.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.stream.IntStream;

public class SetupMenu extends VBox {

     public SetupMenu() {
         this.setSpacing(20);
         Text text = new Text("Choose board size:");
         text.setId("menu-text");
         this.getChildren().add(text);
         IntStream.rangeClosed(5, 10).forEach( i -> {
             Button sizeButton = new Button(i + "x" + i);
             sizeButton.setId("menu-button");
             sizeButton.setPrefSize(150, 1);
             this.getChildren().add(sizeButton);
         });
     }
}
