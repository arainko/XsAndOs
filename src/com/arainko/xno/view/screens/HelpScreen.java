package com.arainko.xno.view.screens;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HelpScreen extends VBox {
    public enum Type {
        RULES, CONTROLS
    }
    public HelpScreen(String header, Type type) {
        setupHelpScreen(header, type);
        this.setAlignment(Pos.CENTER);
    }

    private void setupHelpScreen(String headerText, Type type) {
        Text header = new Text(headerText);
        Text point1 = new Text(getTypeText(type));
        header.setId("header-text");
        point1.setId("help-text");
        point1.setTextAlignment(TextAlignment.CENTER);
        getChildren().addAll(header, point1);
    }

    private String getTypeText(Type type) {
        switch (type) {
            case RULES:
                return getRulesText();
            case CONTROLS:
                return getControlsText();
            default:
                return "";
        }
    }

    private String getRulesText() {
        return "connect every X and O\n" +
                "with a line that contains only a\n" +
                "SINGLE 90 degree joint and\n" +
                "that doesn't intersect with\n" +
                "other lines";
    }

    private String getControlsText() {
        return "click LMB on either X or O\n" +
                "to start building a line,\n" +
                "a GREEN line means a properly\n" +
                "placed connection while\n" +
                "a RED one means the opposite,\n" +
                "you can also click RMB\n" +
                "on GREEN or RED tiles to\n" +
                "remove their corresponding\n" +
                "connections";
    }
}
