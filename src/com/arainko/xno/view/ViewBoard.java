package com.arainko.xno.view;

import com.arainko.xno.abstracts.Board;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class ViewBoard extends Board<Button> {

    private GridPane buttonGridPane;

    public ViewBoard(int dimX, int dimY) {
        super(dimX, dimY);
        buttonGridPane = new GridPane();
        setButtonGridPane();
    }

    @Override
    public void setBoardInitialState() {
        for (int i=0; i < getDimY(); i++) {
            List<Button> row = new ArrayList<>();
            for (int j=0; j < getDimX(); j++) {
                Button boardButton = new Button();
                row.add(j, boardButton);
            }
            getBoardElements().add(row);
        }
    }

    private void setButtonGridPane() {
        for (int i=0; i < getDimY(); i++) {
            int finalI = i;
            getBoardElements().get(i).forEach(button -> {
                button.setId("custom-button");
                button.setPrefSize(50,50);
                button.setOnAction(eventHandler -> button.setId("clicked-button"));
                buttonGridPane.addRow(finalI, button);
            });
        }
    }

    public GridPane getButtonGridPane() {
        return buttonGridPane;
    }
}
