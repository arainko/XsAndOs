package com.arainko.xno.model.tests;

import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Cell[] cells = {new Cell(0, 0, Cell.Contents.CROSS), new Cell(1, 0, Cell.Contents.EMPTY),
                new Cell(1, 1, Cell.Contents.EMPTY), new Cell(1, 2, Cell.Contents.CIRCLE)};
        for (Cell cell : cells)
            presetConnection1.addConnectionUnit(cell);

        presetConnection1.setConnectionTypes();
        presetConnection1.print();
    }

}
