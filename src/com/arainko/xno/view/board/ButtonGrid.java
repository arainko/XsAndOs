package com.arainko.xno.view.board;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;

public class ButtonGrid extends GridPane {
    public ButtonGrid(List<List<Button>> buttons) {
        setButtonGrid(buttons);
        this.setAlignment(Pos.CENTER);
    }

    private void setButtonGrid(List<List<Button>> buttons) {
        for (int i=0; i < buttons.size(); i++)
            for (Button button : buttons.get(i)) {
                button.setId("default-button");
                button.setPrefSize(50, 50);
                this.addRow(i, button);
            }
    }
}
