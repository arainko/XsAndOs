package com.arainko.xno.view;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HelpScreen extends VBox {
    public HelpScreen() {
        setupHelpScreen();
    }

    private void setupHelpScreen() {
        Text header = new Text("GAME RULES:");
        Text point1 = new Text("- connect every X and O with a line that contains a SINGLE 90 degree joint");
        Text point2 = new Text("- the lines cannot intersect");
        header.setId("header-text");
        point1.setId("menu-text");
        point2.setId("menu-text");
        getChildren().addAll(header, point1, point2);
//        Text point3 = new Text("- the lines cannot intersect");
    }
}
