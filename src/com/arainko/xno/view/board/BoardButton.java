package com.arainko.xno.view.board;

import com.arainko.xno.abstracts.BoardElement;
import javafx.scene.control.Button;

import static com.arainko.xno.abstracts.Board.Cords;

public class BoardButton extends Button implements BoardElement {
    private Cords cords;

    public BoardButton(int cordX, int cordY) {
        this.cords = new Cords(cordX, cordY);
        this.setId("default-button");
        this.setPrefSize(50, 50);
    }

    @Override
    public Cords getCords() {
        return cords;
    }
}
