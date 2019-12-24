package com.arainko.xno.controller.gamestates.gamesetupstate;

import com.arainko.xno.abstracts.InternalGameStateHandler;
import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.elements.Cell;
import javafx.scene.control.Button;

import static com.arainko.xno.model.predicates.CellPredicates.containingCircle;
import static com.arainko.xno.model.predicates.CellPredicates.containingCross;

public class InternalCircleAdder extends InternalGameStateHandler<GameSetupState> {
    public InternalCircleAdder(GameSetupState parentGameState) {
        super(parentGameState);
    }

    @Override
    public void onInternalGameStateClickHandler(Button button) {
        Cords clickedButtonCords = getViewBoard().getButtonCords(button);
        Cell clickedCell = getModelBoard().getCellAt(clickedButtonCords);
        if (clickedCell.isCell(containingCircle().or(containingCross())))
            clickedCell.setCellContents(Cell.Contents.EMPTY);
        else clickedCell.setCellContents(Cell.Contents.CIRCLE);
        getParentGameState().getGameController().refreshBoards();
    }
}
