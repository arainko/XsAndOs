package com.arainko.xno.controller.helpers;

import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.view.board.ViewBoard;

import java.io.*;

public class Bundler implements Serializable {
    public class Bundle implements Serializable {
        private ModelBoard modelBoard;
        private ViewBoard viewBoard;
        private MoveKeeper moveKeeper;

        public Bundle(ModelBoard modelBoard, ViewBoard viewBoard, MoveKeeper moveKeeper) {
            this.modelBoard = modelBoard;
            this.viewBoard = viewBoard;
            this.moveKeeper = moveKeeper;
        }

        public ModelBoard getModelBoard() {
            return modelBoard;
        }

        public ViewBoard getViewBoard() {
            return viewBoard;
        }

        public MoveKeeper getMoveKeeper() {
            return moveKeeper;
        }
    }
    private GameController gameController;
    private String saveFileDirPath;

    public Bundler(GameController gameController) {
        this.gameController = gameController;
        saveFileDirPath = System.getProperty("user.home")+"/.xnosaves";
        new File(saveFileDirPath).mkdir();
    }

    public void saveBundle() throws IOException, ClassNotFoundException {
        ModelBoard modelBoard = gameController.getModelBoard();
        ViewBoard viewBoard = gameController.getViewBoard();
        BoardManipulator boardManipulator = gameController.getBoardManipulator();
        MoveKeeper moveKeeper = gameController.getMoveKeeper();
        saveBundleToFile(new Bundle(modelBoard, viewBoard, boardManipulator, moveKeeper));
    }

    public void loadBundle(Bundle bundle) {
        gameController.setModelBoard(bundle.getModelBoard());
        gameController.setViewBoard(bundle.getViewBoard());
        gameController.setBoardManipulator(bundle.getBoardManipulator());
        gameController.setMoveKeeper(bundle.getMoveKeeper());
    }

    private void saveBundleToFile(Bundle bundle) {
        try {
            FileOutputStream fos = new FileOutputStream(saveFileDirPath + "/bundle.xno");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bundle);
            oos.flush();
            oos.close();
            FileInputStream fis = new FileInputStream(saveFileDirPath + "/bundle.xno");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Bundle savedBundle = (Bundle) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

}
