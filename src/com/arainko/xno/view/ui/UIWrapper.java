package com.arainko.xno.view.ui;

import com.arainko.xno.view.buttons.NavButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class UIWrapper extends BorderPane {
    private NavButton leftButton;
    private NavButton rightButton;
    private GameButtonBar gameButtonBar;

    public UIWrapper() {
        this.setBackground(Background.EMPTY);
        leftButton = new NavButton("<");
        rightButton = new NavButton(">");
        gameButtonBar = new GameButtonBar();
        BorderPane.setAlignment(leftButton, Pos.CENTER);
        BorderPane.setAlignment(rightButton, Pos.CENTER);
        this.setLeft(leftButton);
        this.setRight(rightButton);
        this.setTop(gameButtonBar);
    }

    public void changeMainView(Node node) {
        this.setCenter(node);
    }

    public NavButton getLeftButton() {
        return leftButton;
    }

    public NavButton getRightButton() {
        return rightButton;
    }

    public GameButtonBar getGameButtonBar() {
        return gameButtonBar;
    }
}
