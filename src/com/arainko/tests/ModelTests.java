package com.arainko.tests;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.arainko.xno.abstracts.Board.*;
import static com.arainko.xno.model.predicates.BoardPredicates.ableToAccommodateCords;
import static com.arainko.xno.model.predicates.BoardPredicates.done;
import static com.arainko.xno.model.predicates.CellPredicates.*;
import static com.arainko.xno.model.predicates.ConnectionPredicates.*;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {

    public ModelBoard modelBoard;
    public Connection exampleConnection;

    @BeforeEach
    public void setupForTests() {
        modelBoard = new ModelBoard(10,10);
        exampleConnection = new Connection();
    }

    @Test
    public void connectionPredicatesTest() {
        Cell testCell = new Cell(3,0, Cell.Contents.CROSS);
        List<Cell> cells1 = List.of(new Cell(0, 0, Cell.Contents.CROSS), new Cell(1, 0, Cell.Contents.EMPTY),
                new Cell(1, 1, Cell.Contents.EMPTY), new Cell(0, 1, Cell.Contents.CIRCLE));

        List<Cell> cells2 = List.of(new Cell(3, 0, Cell.Contents.CROSS), new Cell(4, 0, Cell.Contents.EMPTY),
                new Cell(5, 0, Cell.Contents.EMPTY), new Cell(6, 0, Cell.Contents.CIRCLE));

        List<Cell> cells3 = List.of(testCell, new Cell(4, 0, Cell.Contents.EMPTY),
                new Cell(4, 1, Cell.Contents.EMPTY), new Cell(4, 2, Cell.Contents.CIRCLE));

        Connection connection1 = new Connection(cells1);
        Connection connection2 = new Connection(cells2);
        Connection connection3 = new Connection(cells3);

        assertFalse(connection1.isConnection(upToWinCondition()));
        assertFalse(connection2.isConnection(upToWinCondition()));
        assertTrue(connection3.isConnection(upToWinCondition()));

        assertFalse(connection1.isConnection(containingCell(testCell)));
        assertTrue(connection2.isConnection(containingCell(testCell)));
        assertTrue(connection3.isConnection(containingCell(testCell)));

        assertTrue(exampleConnection.isConnection(empty()));

        assertTrue(connection1.isConnection(ended()));
        assertTrue(connection2.isConnection(ended()));
        assertTrue(connection2.isConnection(ended()));

        connection1.remove();
        assertTrue(connection1.isConnection(empty()));
    }

    @Test
    public void cellContentsTests() {
        Cell cell1 = new Cell(0,0, Cell.Contents.EMPTY);
        assertFalse(cell1.isCell(containingCircle()));
        assertFalse(cell1.isCell(containingCross()));
        cell1.setCellContents(Cell.Contents.CIRCLE);
        assertTrue(cell1.isCell(containingCircle()));
        assertFalse(cell1.isCell(containingCross()));
    }

    @Test
    public void cellConnectionFlagTest() {
        Cell cell1 = new Cell(0,0, Cell.Contents.EMPTY);
        assertFalse(cell1.isCell(partOfConnection()));
        exampleConnection.addConnectionUnit(cell1);
        assertTrue(cell1.isCell(partOfConnection()));
    }

    @Test
    public void cellConnectionJointDetectionTest() {
        Cell cell1 = new Cell(0,0, Cell.Contents.EMPTY);
        assertTrue(cell1.isCell(connectedByJoint(), new Cell(1,1, Cell.Contents.EMPTY)));
        assertFalse(cell1.isCell(connectedByJoint(), new Cell(1,0, Cell.Contents.EMPTY)));
    }

    @Test
    public void cellPlaneCalculationTest() {
        Cell cell1 = new Cell(0,0, Cell.Contents.EMPTY);
        assertTrue(cell1.isCell(nextToOnPlaneX(), new Cell(1,0, Cell.Contents.EMPTY)));
        assertTrue(cell1.isCell(nextToOnPlaneY(), new Cell(0,1, Cell.Contents.EMPTY)));
        assertFalse(cell1.isCell(nextToOnPlaneY(), new Cell(1,1, Cell.Contents.EMPTY)));
        assertFalse(cell1.isCell(nextToOnPlaneX(), new Cell(1,1, Cell.Contents.EMPTY)));
    }

    @Test
    public void boardCompletenessTest() {
        mockConnection();
        assertFalse(modelBoard.isBoard(done()));
        modelBoard.addConnection(exampleConnection);
        assertTrue(modelBoard.isBoard(done()));
    }

    @Test
    public void boardCordsAccommodationTest() {
        assertFalse(modelBoard.isBoard(ableToAccommodateCords(-1, 10)));
        assertFalse(modelBoard.isBoard(ableToAccommodateCords(5, 20)));
        assertTrue(modelBoard.isBoard(ableToAccommodateCords(5, 5)));
    }

    @Test
    public void boardConnectionAdditionTest() {
        mockConnection();
        modelBoard.addConnection(exampleConnection);
        assertFalse(modelBoard.getConnections().isEmpty());
    }

    @Test
    public void boardConnectionRemovalTest() {
        mockConnection();
        modelBoard.addConnection(exampleConnection);
        assertFalse(modelBoard.getConnections().isEmpty());
        modelBoard.removeConnection(exampleConnection);
        assertTrue(modelBoard.getConnections().isEmpty());
    }

    @Test
    public void boardSpecificConnectionGetterTest() {
        mockConnection();
        modelBoard.addConnection(exampleConnection);
        assertSame(exampleConnection, modelBoard
                .getSpecificConnection(containingCell(new Cell(1, 0, Cell.Contents.EMPTY))));
    }

    @Test
    public void boardNeighborGetterTest() {
        mockConnection();
        modelBoard.addConnection(exampleConnection);
        assertEquals(2, modelBoard.getFreeNeighborsAt(new Cords(1, 1)).size());
        assertEquals(4, modelBoard.getFreeNeighborsAt(new Cords(5, 5)).size());
        assertEquals(2, modelBoard.getFreeNeighborsAt(new Cords(9, 9)).size());
    }

    private void mockConnection() {
        List<Cords> cordList = List.of(new Cords(0,0), new Cords(1,0), new Cords(2,0),
                new Cords(2,1), new Cords(2,2));
        modelBoard.getElementAt(new Cords(0,0)).setCellContents(Cell.Contents.CIRCLE);
        modelBoard.getElementAt(new Cords(2,2)).setCellContents(Cell.Contents.CROSS);
        List<Cell> connectionCells = modelBoard.getElementsAt(cordList);
        exampleConnection = new Connection(connectionCells);
    }
}
