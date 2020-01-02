package com.arainko.xno.view.board;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.List;

public class ButtonGrid extends GridPane {
    public ButtonGrid(List<List<BoardButton>> buttons) {
        setButtonGrid(buttons);
        this.setAlignment(Pos.CENTER);
    }

    private void setButtonGrid(List<List<BoardButton>> buttons) {
        for (int i=0; i < buttons.size(); i++)
            for (BoardButton button : buttons.get(i))
                this.addRow(i, button);
    }
}
