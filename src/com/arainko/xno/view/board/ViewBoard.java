package com.arainko.xno.view.board;

import com.arainko.xno.abstracts.Board;
import com.arainko.xno.view.buttons.BoardButton;

import java.util.ArrayList;
import java.util.List;

public class ViewBoard extends Board<BoardButton>{
    private ButtonGrid buttonGrid;

    public ViewBoard(int dimX, int dimY) {
        super(dimX, dimY);
        buttonGrid = new ButtonGrid(getBoardElements());
    }

    @Override
    public void setBoardInitialState() {
        for (int i=0; i < getDimY(); i++) {
            List<BoardButton> row = new ArrayList<>();
            for (int j=0; j < getDimX(); j++) {
                BoardButton boardButton = new BoardButton(j,i);
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
