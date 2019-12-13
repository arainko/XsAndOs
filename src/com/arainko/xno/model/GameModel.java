package com.arainko.xno.model;

import com.arainko.xno.model.board.Board;
import com.arainko.xno.model.elements.Connection;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private Board gameBoard;
    private List<Connection> connectionsOnBoard;

    public GameModel(int dimX, int dimY) {
        this.gameBoard = new Board(dimX, dimY);
        connectionsOnBoard = new ArrayList<>();
    }
}
