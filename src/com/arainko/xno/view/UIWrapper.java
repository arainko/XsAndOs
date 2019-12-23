package com.arainko.xno.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class UIWrapper extends BorderPane {
    public UIWrapper() {
        super();
        this.setBackground(Background.EMPTY);
    }

    public void changeMainView(Node node) {
        this.setCenter(node);
        setAlignment(node, Pos.CENTER);
    }
}
