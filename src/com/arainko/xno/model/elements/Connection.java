package com.arainko.xno.model.elements;

import java.util.ArrayList;
import java.util.Arrays;
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
        for (int i=1; i < connectionUnits.size()-1; i++) {
            ConnectionUnit currUnit = connectionUnits.get(i);
            ConnectionUnit nextUnit = connectionUnits.get(i+1);

            if (currUnit.isNextToOnPaneX(nextUnit) || currUnit.isNextToOnPaneY(nextUnit))
                currUnit.setConnectionType(ConnectionUnit.Type.LINE);
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
    }

    public void calculateEnds() {
        int size = connectionUnits.size();
        if (size > 1) {
            ConnectionUnit[] firstAndLastUnit = {connectionUnits.get(0), connectionUnits.get(size-1)};
            for (ConnectionUnit unit : firstAndLastUnit)
                if (unit.getContainer() instanceof Cross || unit.getContainer() instanceof Circle)
                    unit.setConnectionType(ConnectionUnit.Type.END);
        }
//        for (ConnectionUnit unit : connectionUnits)
//            System.out.println(unit.getConnectionType());
    }

    public boolean isConnectionUpToWinCondition() {
        int jointCount = 0;
        int crossCount = 0;
        int circleCount  = 0;

        for (ConnectionUnit unit : connectionUnits) {
            if (unit.getConnectionType() == ConnectionUnit.Type.JOINT)
                jointCount++;
            if (unit.getConnectionType() == ConnectionUnit.Type.END)
                if (unit.getContainer() instanceof Circle)
                    circleCount++;
                else if (unit.getContainer() instanceof Cross)
                    crossCount++;
        }
        return jointCount == 1 && circleCount == 1 && crossCount == 1;
    }

    public List<ConnectionUnit> getConnectionUnits() {
        return this.connectionUnits;
    }

}
