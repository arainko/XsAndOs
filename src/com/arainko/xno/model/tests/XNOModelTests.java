package com.arainko.xno.model.tests;

import com.arainko.xno.abstracts.Cell;
import com.arainko.xno.model.board.ModelBoard;
import com.arainko.xno.model.elements.Connection;
import com.arainko.xno.model.elements.ConnectionUnit;
import org.junit.jupiter.api.BeforeEach;

public class XNOModelTests {

    public ModelBoard modelBoard;
    public Connection presetConnection1;
    public Connection presetConnection2;

    public Cell getBoardElementAt(int cordX, int cordY) {
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

}
