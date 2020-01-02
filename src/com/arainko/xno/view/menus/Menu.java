package com.arainko.xno.view.menus;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Menu<T extends Button> extends VBox {
    private List<T> buttonList;

    public Menu() {
        buttonList = new ArrayList<>();
        this.setId("setup-menu");
        this.setAlignment(Pos.CENTER);
    }

    public void addButtons(T... buttons){
        buttonList.addAll(Arrays.asList(buttons));
    }

    public void addButton(T button) {
        buttonList.add(button);
    }

    public List<T> getButtonList() {
        return buttonList;
    }
}
