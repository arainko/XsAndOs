package com.arainko.xno.model.board;

import com.arainko.xno.abstracts.Board;
import com.arainko.xno.abstracts.Element;
import com.arainko.xno.model.elements.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelBoard extends Board<Element> {
    List<Connection> connectionsOnBoard;
    public ModelBoard(int dimX, int dimY) {
        super(dimX, dimY);
    }

    @Override
    public void setBoardInitialState() {
        for (int i=0; i < getDimY(); i++) {
            List<Element> row = new ArrayList<>();
            for (int j=0; j < getDimX(); j++) {
                row.add(j, new Cell(i,j));
            }
            getBoardElements().add(row);
        }
    }

    public void placeConnectionIfValid(Connection connection) {
        if (isConnectionValid(connection))
            placeConnection(connection);
    }

    public boolean isConnectionValid(Connection connection) {
        List<ConnectionUnit> units = connection.getConnectionUnits();
        for (List<Element> row : getBoardElements())
            if (!Collections.disjoint(row, units))
                return false;
        return true;
    }

    public void placeConnection(Connection connection) {
        for (ConnectionUnit unit : connection.getConnectionUnits())
            replaceBoardElement(unit);
    }

    public void replaceBoardElement(Element element) {
        int elementCordX = element.getCordX();
        int elementCordY = element.getCordY();
        if (elementCordX < getDimX() && elementCordY < getDimY()) {
            if (element instanceof ConnectionUnit) {
                Element elementToContain = getBoardElements().get(elementCordY).get(elementCordX);
                ((ConnectionUnit) element).setContainer(elementToContain);
            } this.getBoardElements().get(elementCordY).set(elementCordX, element);
        } else throw new IndexOutOfBoundsException("Element cords are out of bounds: cordX = " + elementCordX + ", cordY = " + elementCordY);
    }

    public void setCircleAt(int cordX, int cordY) {
        replaceBoardElement(new Circle(cordX, cordY));
    }

    public void setCrossAt(int cordX, int cordY) {
        replaceBoardElement(new Cross(cordX, cordY));
    }

    public void setCellAt(int cordX, int cordY) {
        replaceBoardElement(new Cell(cordX, cordY));
    }

    public void setConnectionUnitAt(int cordX, int cordY, Connection parentConnection) {
        ConnectionUnit newConnectionUnit = new ConnectionUnit(cordX, cordY);
        replaceBoardElement(newConnectionUnit);
        parentConnection.addConnectionUnit(newConnectionUnit);
    }

    public void removeConnectionUnit(ConnectionUnit connectionUnit) {
        replaceBoardElement(connectionUnit.getContainer());
    }

}
