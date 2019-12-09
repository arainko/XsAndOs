package com.arainko.xno.gamelogic.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Connection {
    private List<ConnectionUnit> connectionUnits;

    public Connection() {
        connectionUnits = new ArrayList<>();
    }

    public Connection(ConnectionUnit[] units) {
        connectionUnits = new ArrayList<>(Arrays.asList(units));
    }

    public void addConnectionUnit(ConnectionUnit unit) {
        connectionUnits.add(unit);
    }

    public void calculateConnections() {
        connectionUnits.get(0).setConnectionType(ConnectionUnit.Type.START);
        connectionUnits.get(connectionUnits.size()-1).setConnectionType(ConnectionUnit.Type.END);

        for (int i=1; i < connectionUnits.size()-1; i++) {
            ConnectionUnit currUnit = connectionUnits.get(i);
            ConnectionUnit nextUnit = connectionUnits.get(i+1);

            if (currUnit.isNextToOnPaneX(nextUnit))
                currUnit.setConnectionType(ConnectionUnit.Type.HORIZONTAL);
            else if (currUnit.isNextToOnPaneY(nextUnit))
                currUnit.setConnectionType(ConnectionUnit.Type.VERTICAL);
        }
    }

    public void calculateJoints() {
        if (connectionUnits.size() >= 3)
            for (int i=1; i + 1 < connectionUnits.size(); i++) {
                ConnectionUnit lastUnit = connectionUnits.get(i-1);
                ConnectionUnit currUnit = connectionUnits.get(i);
                ConnectionUnit nextUnit = connectionUnits.get(i+1);

                if (lastUnit.getCordX() != nextUnit.getCordX() && lastUnit.getCordY() != nextUnit.getCordY() && currUnit.getConnectionType() != ConnectionUnit.Type.NONE)
                    currUnit.setConnectionType(ConnectionUnit.Type.JOINT);
            }
        for (ConnectionUnit unit : connectionUnits)
            System.out.println(unit.getConnectionType());
    }

    public List<ConnectionUnit> getConnectionUnits() {
        return this.connectionUnits;
    }


}
