package com.arainko.xno.tests;

import com.arainko.xno.gamelogic.elements.Circle;
import com.arainko.xno.gamelogic.elements.Cross;
import com.arainko.xno.gamelogic.board.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XNO_Tests {

    @Test
    public void equalsCordPairElementTest() {
        Circle cell1 = new Circle(1,1);
        Cross cross1 = new Cross(1,1);

        assertEquals( cell1, cross1);
    }

    @Test
    public void boardElementReplacementTest() {
        Board board = new Board(10, 10);

        board.replaceBoardElement(new Circle(0,0));
        board.replaceBoardElement(new Cross(9,9));
        board.replaceBoardElement(new Cross(5,4));

        board.printBoard();

        assertTrue(board.getBoard().get(0).get(0) instanceof Circle);
        assertTrue(board.getBoard().get(9).get(9) instanceof Cross);
        assertTrue(board.getBoard().get(4).get(5) instanceof Cross);
    }

    @Test
    public void boardOutOfBoundsReplacementTest() {
        boolean isExceptionThrown = false;
        Board board = new Board(10, 10);
        try {
            board.replaceBoardElement(new Circle(10,10));
        } catch (IndexOutOfBoundsException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);
    }

}
