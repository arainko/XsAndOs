package com.arainko.xno.view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UIWrapper extends BorderPane {
    public UIWrapper() {
        this.setBackground(Background.EMPTY);
    }

    public void changeMainView(Pane pane) {
        this.setCenter(pane);
    }
}
