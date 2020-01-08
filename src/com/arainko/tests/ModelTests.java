package com.arainko.tests;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.arainko.xno.model.predicates.CellPredicates.*;
import static com.arainko.xno.model.predicates.ConnectionPredicates.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void cellPredicatesTest() {
        Cell cell1 = new Cell(0,0, Cell.Contents.EMPTY);

        assertFalse(cell1.isCell(containingCircle()));
        assertFalse(cell1.isCell(containingCross()));
        cell1.setCellContents(Cell.Contents.CIRCLE);
        assertTrue(cell1.isCell(containingCircle()));
        assertFalse(cell1.isCell(containingCross()));

        assertFalse(cell1.isCell(partOfConnection()));
        exampleConnection.addConnectionUnit(cell1);
        assertTrue(cell1.isCell(partOfConnection()));

        assertTrue(cell1.isCell(connectedByJoint(), new Cell(1,1, Cell.Contents.EMPTY)));
        assertFalse(cell1.isCell(connectedByJoint(), new Cell(1,0, Cell.Contents.EMPTY)));
        assertFalse(cell1.isCell(nextToOnPlaneX(), new Cell(1,0, Cell.Contents.EMPTY)));


    }

}
