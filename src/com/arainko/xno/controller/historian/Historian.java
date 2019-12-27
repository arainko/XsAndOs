package com.arainko.xno.controller.historian;

import com.arainko.xno.controller.GameController;

import java.util.ArrayList;
import java.util.List;

public class Historian {
    private GameController gameController;
    private List<Bundle> bundles;

    public Historian(GameController gameController) {
        bundles = new ArrayList<>();
        this.gameController = gameController;
    }

    public void revert() {
        gameController.load(bundles.get(0));
    }

    public void addBundle(Bundle bundle) {
        bundles.add(bundle);
    }
}
