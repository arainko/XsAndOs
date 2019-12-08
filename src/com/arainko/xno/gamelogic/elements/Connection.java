package com.arainko.xno.gamelogic.elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Connection {
    private List<ConnectionUnit> connectionUnits;

    public Connection() {
        connectionUnits = new LinkedList<>();
    }

    public void addConnectionUnit(ConnectionUnit unit) {
        connectionUnits.add(unit);
    }

    public void calculateConnections() {
        for (int i=1; i < connectionUnits.size(); i++) {
            ConnectionUnit lastUnit = connectionUnits.get(i-1);
            ConnectionUnit currUnit = connectionUnits.get(i);

            if (Math.abs(lastUnit.getCordX() - currUnit.getCordX()) == 1) {
                lastUnit.setConnectionType(ConnectionUnit.Type.HORIZONTAL);
                currUnit.setConnectionType(ConnectionUnit.Type.HORIZONTAL);
            }
        }
    }

}
