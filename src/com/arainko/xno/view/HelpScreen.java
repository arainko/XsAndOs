package com.arainko.xno.view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HelpScreen extends VBox {
    public HelpScreen() {
        setupHelpScreen();
        this.setAlignment(Pos.CENTER);
    }

    private void setupHelpScreen() {
        Text header = new Text("RULES");
        Text point1 = new Text("connect every X and O\n" +
                "with a line that contains only a\n" +
                "SINGLE 90 degree joint and\n" +
                "that doesn't intersect with\n" +
                "other lines");
        header.setId("header-text");
        point1.setId("help-text");
        point1.setTextAlignment(TextAlignment.CENTER);
        getChildren().addAll(header, point1);
    }
}
