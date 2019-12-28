package com.arainko.xno.view;

import com.arainko.xno.abstracts.Board;
import com.arainko.xno.helpers.Cords;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ViewBoard extends Board<Button> {

    private ButtonGrid buttonGrid;

    public ViewBoard(int dimX, int dimY) {
        super(dimX, dimY);
        buttonGrid = new ButtonGrid(this.getBoardElements());
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

    public void setButtonsColorAtCords(List<Cords> cordList, String styleId) {
        cordList.forEach( cord -> getButtonAt(cord).setId(styleId));
    }

    public ButtonGrid getButtonGrid() {
        return buttonGrid;
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

//   public ViewBoard getSavedState() {
//
//   }
}
