package com.arainko.xno.view.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Template for different menu screens */

public abstract class Screen<T extends Button> extends VBox {
    private List<T> buttonList;

    public Screen() {
        buttonList = new ArrayList<>();
        this.setId("setup-menu");
        this.setAlignment(Pos.CENTER);
    }

    @SafeVarargs
    public final void addButtons(T... buttons) {
        List<T> listButtons = Arrays.asList(buttons);
        buttonList.addAll(listButtons);
        this.getChildren().addAll(listButtons);
    }

    public void addButtons(List<T> buttons) {
        buttonList.addAll(buttons);
        this.getChildren().addAll(buttons);
    }

    public void addButton(T button) {
        buttonList.add(button);
        this.getChildren().add(button);
    }

    public List<T> getButtonList() {
        return buttonList;
    }
}
