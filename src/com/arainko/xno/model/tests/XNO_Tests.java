package com.arainko.xno.model.tests;

import com.arainko.xno.model.abstracts.Element;
import com.arainko.xno.model.board.Board;
import com.arainko.xno.model.elements.Circle;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.model.elements.ConnectionUnit;
import com.arainko.xno.model.elements.Cross;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class XNO_Tests {

    public Board board;
    public Connection presetConnection1;
    public Connection presetConnection2;

    public Element getBoardElementAt(int cordX, int cordY) {
        return board.getBoard().get(cordY).get(cordX);
    }

    @BeforeEach
    public void setupForTests() {
        board = new Board(10,10);
        ConnectionUnit[] units1 = {new ConnectionUnit(0,0), new ConnectionUnit(1,0), new ConnectionUnit(2,0) };
        ConnectionUnit[] units2 = {new ConnectionUnit(2,0), new ConnectionUnit(3,0) };
        presetConnection1 = new Connection(units1);
        presetConnection2 = new Connection(units2);
    }

    @Test
    public void boardElementReplacementTest() {
        board.replaceBoardElement(new Circle(0,0));
        board.replaceBoardElement(new Cross(9,9));
        board.replaceBoardElement(new Cross(5,4));
        board.replaceBoardElement(new ConnectionUnit(5,4));

        assertTrue(board.getBoard().get(0).get(0) instanceof Circle);
        assertTrue(board.getBoard().get(9).get(9) instanceof Cross);
        assertTrue(board.getBoard().get(4).get(5) instanceof ConnectionUnit);
    }

    @Test
    public void boardOutOfBoundsReplacementTest() {
        boolean isExceptionThrown = false;
        try {
            board.replaceBoardElement(new Circle(10,10));
        } catch (IndexOutOfBoundsException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    public void connectionCalculationTest() {
        ConnectionUnit[] units = { new ConnectionUnit(0,0), new ConnectionUnit(1,0),
                new ConnectionUnit(2,0), new ConnectionUnit(3,0),
                new ConnectionUnit(3,1), new ConnectionUnit(3,2),
                new ConnectionUnit(2,2), new ConnectionUnit(1,2)};
        Connection connection = new Connection(units);

        connection.calculateConnections();
        connection.calculateJoints();

        assertEquals(units[3].getConnectionType(), ConnectionUnit.Type.JOINT);
        assertEquals(units[5].getConnectionType(), ConnectionUnit.Type.JOINT);
    }

    @Test
    public void connectionOnBoardValidityTest() {
        board.placeConnectionIfValid(presetConnection1);
        board.placeConnectionIfValid(presetConnection2);
        board.printBoard();
        assertFalse(board.isConnectionValid(presetConnection2));
    }

    @Test
    public void granularBoardSettersTest() {
        board.setCircleAt(5,4);
        board.setCrossAt(3,3);
        board.setConnectionUnitAt(5,4, presetConnection2);

        assertTrue(getBoardElementAt(3,3) instanceof Cross);
        assertTrue(getBoardElementAt(5,4) instanceof ConnectionUnit);

        board.removeConnectionUnit(presetConnection2.getConnectionUnits().get(2));

        assertTrue(getBoardElementAt(5,4) instanceof Circle);
    }
}
