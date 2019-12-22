package com.arainko.xno.controller.gamestates.gamerunningstate;

import com.arainko.xno.abstracts.InternalAbstractGameState;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import javafx.scene.control.Button;

import static com.arainko.xno.model.predicates.CellPredicates.*;

public class XOWatcher extends InternalAbstractGameState<GameRunningState> {

    public XOWatcher(GameRunningState parentGameState) {
        super(parentGameState);
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {
        Cords clickedButtonCords = getParentGameState().getGameController()
            .getViewBoard()
            .getButtonCords(button);

        Cell clickedCell = getParentGameState().getGameController()
                .getModelBoard()
                .getCellAt(clickedButtonCords);

        boolean isXO = clickedCell
                .isCell((containingCircle().or(containingCross())).and(notPartOfConnection()));

        if (isXO) {
            getParentGameState().setCurrentInternalGameState(getParentGameState().getConnectionBuilder());
            getParentGameState().getCurrentInternalGameState().onInternalGameStateClickHandler(button);
        }
    }
}
