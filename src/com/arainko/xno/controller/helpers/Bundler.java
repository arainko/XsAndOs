package com.arainko.xno.controller.helpers;

import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.board.ViewBoard;

import java.io.*;
import java.util.List;

import static com.arainko.xno.controller.helpers.MoveKeeper.*;

public class Bundler {
    public static class Bundle implements Serializable {
        private ModelBoard modelBoard;
        private List<Move> moveHistory;
        private int currentHistoryIndex;

        public Bundle(ModelBoard modelBoard, MoveKeeper moveKeeper) {
            this.modelBoard = modelBoard;
            this.moveHistory = moveKeeper.getKeptMoves();
            this.currentHistoryIndex = moveKeeper.getCurrentIndex();
        }

        public ModelBoard getBundledModelBoard() {
            return modelBoard;
        }

        public List<Move> getBundledMoveHistory() {
            return moveHistory;
        }

        public int getBundledHistoryIndex() {
            return currentHistoryIndex;
        }
    }
    private GameController gameController;
    private String saveFileDirPath;

    public Bundler(GameController gameController) {
        this.gameController = gameController;
        saveFileDirPath = System.getProperty("user.home")+"/.xnosaves";
        new File(saveFileDirPath).mkdir();
    }

    public void saveBundle() {
        ModelBoard modelBoard = gameController.getModelBoard();
        MoveKeeper moveKeeper = gameController.getMoveKeeper();
        saveBundleToFile(new Bundle(modelBoard, moveKeeper));
    }

    public void loadBundle(Bundle bundle) {
        ModelBoard modelBoard = bundle.getBundledModelBoard();
        ViewBoard viewBoard = Boards.rebuildBoard(modelBoard, gameController);
        List<Move> moveHistory = bundle.getBundledMoveHistory();
        int currentHistoryIndex = bundle.getBundledHistoryIndex();
        gameController.setModelBoard(modelBoard);
        gameController.setViewBoard(viewBoard);
        gameController.setMoveKeeper(new MoveKeeper(modelBoard, viewBoard, moveHistory, currentHistoryIndex));
    }

    private void saveBundleToFile(Bundle bundle) {
        try {
            FileOutputStream fos = new FileOutputStream(saveFileDirPath + "/bundle.xno");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bundle);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
