package com.arainko.xno.model.tests;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.arainko.xno.model.predicates.ConnectionPredicates.interferingWith;
import static com.arainko.xno.model.predicates.ConnectionPredicates.upToWinCondition;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XNOModelTests {

    public ModelBoard modelBoard;
    public Connection presetConnection1;
    public Connection presetConnection2;

    @BeforeEach
    public void setupForTests() {
        modelBoard = new ModelBoard(10,10);
        presetConnection1 = new Connection();
        presetConnection2 = new Connection();
    }

    @Test
    public void connectionTest() {
        Cell[] cells1 = {new Cell(0, 0, Cell.Contents.CROSS), new Cell(1, 0, Cell.Contents.EMPTY),
                new Cell(1, 1, Cell.Contents.EMPTY), new Cell(0, 1, Cell.Contents.CIRCLE)};

        Cell[] cells2 = {new Cell(3, 0, Cell.Contents.CROSS), new Cell(4, 0, Cell.Contents.EMPTY),
                new Cell(5, 0, Cell.Contents.EMPTY), new Cell(6, 0, Cell.Contents.CIRCLE)};

        for (Cell cell : cells1)
            presetConnection1.addConnectionUnit(cell);
        for (Cell cell : cells2)
            presetConnection2.addConnectionUnit(cell);

        presetConnection1.setConnectionTypes();
        presetConnection2.setConnectionTypes();
        assertFalse(presetConnection1.isConnection(upToWinCondition()));
        assertFalse(presetConnection2.isConnection(upToWinCondition()));
        assertFalse(presetConnection1.isConnection(interferingWith(), presetConnection2));
    }

//    @Test
//    public void neighborGetterTest() {
//        List<Cell> cells = modelBoard.getFreeNeighborsAt(5,5);
//        cells.forEach(n -> System.out.println(n +" "+ n.getCordX()+" "+n.getCordY()));
//    }

}
