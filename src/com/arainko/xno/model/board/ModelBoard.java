package com.arainko.xno.model.board;

import com.arainko.xno.abstracts.Board;
import com.arainko.xno.model.elements.Cell;

import java.util.ArrayList;
import java.util.List;

public class ModelBoard extends Board<Cell> {
    public ModelBoard(int dimX, int dimY) {
        super(dimX, dimY);
    }

    @Override
    public void setBoardInitialState() {
        for (int i=0; i < getDimY(); i++) {
            List<Cell> row = new ArrayList<>();
            for (int j=0; j < getDimX(); j++)
                row.add(j, new Cell(j, i, Cell.Contents.EMPTY));
            getBoardElements().add(row);
        }
    }

//    public void replaceBoardElementWith(Element element) {
//    }
//
//    public boolean isElementAt(int cordX, int cordY, Predicate<Element> pred) {
//        if (cordX >= 0 && cordX < getDimX() && cordY >= 0 && cordY < getDimY()) {
//            Element element = getElementAt(cordX, cordY);
//            return pred.test(element);
//        } else return false;
//    }
//
//    public List<Element> getFreeNeighborsOf(int cordX, int cordY) {
//        List<Element> neighborList = new ArrayList<>();
//        Predicate<Element> isCell = el -> el.;
//        IntStream.of(-1, 1).forEach(i -> {
//            if (isElementAt(cordX + i, cordY, isCell))
//                neighborList.add(getElementAt(cordX+1, cordY));
//            if (isElementAt(cordX, cordY + i, isCell))
//                neighborList.add(getElementAt(cordX+1, cordY));
//        });
//        return neighborList;
//    }
//
//    private Element getElementAt(int cordX, int cordY) {
//        return getBoardElements().get(cordY).get(cordX);
//    }
//
//    public void setCircleAt(int cordX, int cordY) {
//        replaceBoardElementWith(new Circle(cordX, cordY));
//    }
//    public void setCrossAt(int cordX, int cordY) {
//        replaceBoardElementWith(new Cross(cordX, cordY));
//    }
//
//    public void setCellAt(int cordX, int cordY) {
//        replaceBoardElementWith(new Cell(cordX, cordY));
//    }
//
//    public void setConnectionUnitAt(int cordX, int cordY, Connection parentConnection) {
//        ConnectionUnit newConnectionUnit = new ConnectionUnit(cordX, cordY);
//        replaceBoardElementWith(newConnectionUnit);
//        parentConnection.addConnectionUnit(newConnectionUnit);
//    }
//
//    public void removeConnectionUnit(ConnectionUnit connectionUnit) {
//        replaceBoardElementWith(connectionUnit.getContainer());
//    }
//
//
//    public void placeConnectionIfValid(Connection connection) {
//        if (isConnectionValid(connection))
//            placeConnection(connection);
//    }
//
//    public boolean isConnectionValid(Connection connection) {
//        List<ConnectionUnit> units = connection.getConnectionCells();
//        for (List<Element> row : getBoardElements())
//            if (!Collections.disjoint(row, units))
//                return false;
//        return true;
//    }
//
//    public void placeConnection(Connection connection) {
//        connection.getConnectionCells().forEach(this::replaceBoardElementWith);
//    }
}
