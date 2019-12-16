package com.arainko.xno.view;

import com.arainko.xno.helpers.Cords;
import com.arainko.xno.abstracts.Board;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
            for (Button button : getBoardElements().get(i)) {
                button.setId("custom-button");
                button.setPrefSize(50, 50);
//                button.setOnAction(eventHandler -> button.setId("clicked-button"));
                buttonGridPane.addRow(i, button);
            }
        }
    }

    public void setButtonsOnAction(EventHandler<ActionEvent> onActionEvent) {
        getBoardElements().forEach(row -> row.forEach( button -> {
            button.setOnAction(onActionEvent);
        }));
    }

    public GridPane getButtonGridPane() {
        return buttonGridPane;
    }

    public Button getButtonAt(Cords cords) {
        return getBoardElements().get(cords.Y()).get(cords.X());
    }

    public Cords getButtonCords(Button button) {
        for (int i = 0; i < getDimY(); i++) {
            if (getBoardElements().get(i).contains(button))
                return new Cords(getBoardElements().get(i).indexOf(button), i);
        } throw new NoSuchElementException("Button not found.");
    }

}
