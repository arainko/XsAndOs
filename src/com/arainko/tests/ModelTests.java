package com.arainko.tests;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.arainko.xno.abstracts.Board.*;
import static com.arainko.xno.model.predicates.BoardPredicates.ableToAccommodateCords;
import static com.arainko.xno.model.predicates.BoardPredicates.done;
import static com.arainko.xno.model.predicates.CellPredicates.*;
import static com.arainko.xno.model.predicates.ConnectionPredicates.*;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {

    private ModelBoard modelBoard;
    private Connection exampleConnection;
    private Connection connection1;
    private Connection connection2;
    private Connection connection3;
    private List<Cords> cords1;


    @BeforeEach
    public void setupForTests() {
        modelBoard = new ModelBoard(10,10);
        exampleConnection = new Connection();
        cords1 = List.of(new Cords(0,0), new Cords(1,0), new Cords(2,0),
                new Cords(2,1), new Cords(2,2));
        List<Cell> cells1 = List.of(new Cell(0, 0, Cell.Contents.CROSS), new Cell(1, 0, Cell.Contents.EMPTY),
                new Cell(1, 1, Cell.Contents.EMPTY), new Cell(0, 1, Cell.Contents.CIRCLE));

        List<Cell> cells2 = List.of(new Cell(3, 0, Cell.Contents.CROSS), new Cell(4, 0, Cell.Contents.EMPTY),
                new Cell(5, 0, Cell.Contents.EMPTY), new Cell(6, 0, Cell.Contents.CIRCLE));

        List<Cell> cells3 = List.of(new Cell(3,0, Cell.Contents.CROSS), new Cell(4, 0, Cell.Contents.EMPTY),
                new Cell(4, 1, Cell.Contents.EMPTY), new Cell(4, 2, Cell.Contents.CIRCLE));
        connection1 = new Connection(cells1);
        connection2 = new Connection(cells2);
        connection3 = new Connection(cells3);
    }

    @Test
    public void connectionContainingCellTest() {
        Cell testCell = new Cell(3,0, Cell.Contents.CROSS);
        assertFalse(connection1.isConnection(containingCell(testCell)));
        assertTrue(connection2.isConnection(containingCell(testCell)));
        assertTrue(connection3.isConnection(containingCell(testCell)));

    }

    @Test
    public void connectionUpToWinConditionTest() {
        assertFalse(connection1.isConnection(upToWinCondition()));
        assertFalse(connection2.isConnection(upToWinCondition()));
        assertTrue(connection3.isConnection(upToWinCondition()));
    }

    @Test
    public void connectionEndedTest() {
        assertTrue(connection1.isConnection(ended()));
        assertTrue(connection2.isConnection(ended()));
        assertTrue(connection2.isConnection(ended()));
    }

    @Test
    public void connectionEmptyTest() {
        assertTrue(exampleConnection.isConnection(empty()));
        exampleConnection.addConnectionUnit(new Cell(1,0, Cell.Contents.EMPTY));
        assertFalse(exampleConnection.isConnection(empty()));
        exampleConnection.remove();
        assertTrue(exampleConnection.isConnection(empty()));
    }

    @Test
    public void cellImproperCreationTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new Cell(-1,2, Cell.Contents.EMPTY));
        assertThrows(IllegalArgumentException.class, () ->
                new Cell(-2, -2,Cell.Contents.EMPTY));
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
    public void boardImproperCreationTest() {
        assertThrows(IllegalArgumentException.class, () ->
                 new ModelBoard(-1, 10));
        assertThrows(IllegalArgumentException.class, () ->
                new ModelBoard(10, 0));
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

    @Test
    public void boardSingularElementGetterTest() {
        Cords mockCords = new Cords(5,6);
        Cell mockCell = modelBoard.getElementAt(mockCords);
        assertEquals(mockCell.getCords(), mockCords);
    }

    @Test
    public void boardElementsAtGetterTest() {
        List<Cell> elements = modelBoard.getElementsAt(cords1);
        List<Boolean> areElementsProper = elements.stream()
                .map(cell -> cell.getCords().equals(modelBoard.getElementCords(cell)))
                .collect(Collectors.toList());
        areElementsProper.forEach(Assertions::assertTrue);
    }

    @Test
    public void boardCordGetterTest() {
        assertEquals(new Cords(1,5), modelBoard.getElementCords(new Cell(1,5, Cell.Contents.EMPTY)));
    }

    @Test
    public void boardFlattenedElementsSizeTest() {
        assertEquals(modelBoard.getDimX() * modelBoard.getDimY(),
                modelBoard.getFlattenedBoardElements().size());
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
