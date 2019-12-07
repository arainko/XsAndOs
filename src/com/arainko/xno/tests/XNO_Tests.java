package com.arainko.xno.tests;

import com.arainko.xno.gamelogic.elements.Cross;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XNO_Tests {

    @Test
    public void equalsCordPairElementTest() {
        Cell cell1 = new Cell(1,1);
        Cross cross1 = new Cross(1,1);
        assertEquals( cell1, cross1);
    }

}
