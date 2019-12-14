package com.arainko.xno.model.tests;

import com.arainko.xno.abstracts.Element;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Circle;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.model.elements.ConnectionUnit;
import com.arainko.xno.model.elements.Cross;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class XNOModelTests {

    public ModelBoard modelBoard;
    public Connection presetConnection1;
    public Connection presetConnection2;

    public Element getBoardElementAt(int cordX, int cordY) {
        return modelBoard.getBoardElements().get(cordY).get(cordX);
    }

    @BeforeEach
    public void setupForTests() {
        modelBoard = new ModelBoard(10,10);
        ConnectionUnit[] units1 = {new ConnectionUnit(0,0), new ConnectionUnit(1,0), new ConnectionUnit(2,0) };
        ConnectionUnit[] units2 = {new ConnectionUnit(2,0), new ConnectionUnit(3,0) };
        presetConnection1 = new Connection(units1);
        presetConnection2 = new Connection(units2);
    }

    @Test
    public void boardElementReplacementTest() {
        modelBoard.replaceBoardElement(new Circle(0,0));
        modelBoard.replaceBoardElement(new Cross(9,9));
        modelBoard.replaceBoardElement(new Cross(5,4));
        modelBoard.replaceBoardElement(new ConnectionUnit(5,4));

        assertTrue(modelBoard.getBoardElements().get(0).get(0) instanceof Circle);
        assertTrue(modelBoard.getBoardElements().get(9).get(9) instanceof Cross);
        assertTrue(modelBoard.getBoardElements().get(4).get(5) instanceof ConnectionUnit);
    }

    @Test
    public void boardOutOfBoundsReplacementTest() {
        boolean isExceptionThrown = false;
        try {
            modelBoard.replaceBoardElement(new Circle(10,10));
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
        modelBoard.placeConnectionIfValid(presetConnection1);
        modelBoard.placeConnectionIfValid(presetConnection2);
        modelBoard.printBoard();
        assertFalse(modelBoard.isConnectionValid(presetConnection2));
    }

    @Test
    public void granularBoardSettersTest() {
        modelBoard.setCircleAt(5,4);
        modelBoard.setCrossAt(3,3);
        modelBoard.setConnectionUnitAt(5,4, presetConnection2);

        assertTrue(getBoardElementAt(3,3) instanceof Cross);
        assertTrue(getBoardElementAt(5,4) instanceof ConnectionUnit);

        modelBoard.removeConnectionUnit(presetConnection2.getConnectionUnits().get(2));

        assertTrue(getBoardElementAt(5,4) instanceof Circle);
    }

    @Test
    public void connectionWinConditionTest() {
        modelBoard.setCircleAt(0,0);
        modelBoard.setCrossAt(3,2);

        ConnectionUnit[] testConnUnits = {new ConnectionUnit(0,0), new ConnectionUnit(1,0), new ConnectionUnit(2,0), new ConnectionUnit(3,0),
                new ConnectionUnit(3,1), new ConnectionUnit(3,2)};
        Connection testConn = new Connection(testConnUnits);
        modelBoard.placeConnection(testConn);
        testConn.calculateConnections();
        testConn.calculateJoints();
        testConn.calculateEnds();
        modelBoard.printBoard();
        assertTrue(testConn.isConnectionUpToWinCondition());
    }
}
