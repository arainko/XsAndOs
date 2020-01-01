package com.arainko.xno.view.board;

import com.arainko.xno.abstracts.Board;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

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
        cordList.forEach(cord -> getElementAt(cord).setId(styleId));
    }

    public ButtonGrid getButtonGrid() {
        return buttonGrid;
    }
}
