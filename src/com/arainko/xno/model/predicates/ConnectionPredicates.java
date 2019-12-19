package com.arainko.xno.model.predicates;

import com.arainko.xno.model.elements.Cell;
import com.arainko.xno.model.elements.Connection;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static com.arainko.xno.model.predicates.CellPredicates.containingCircle;
import static com.arainko.xno.model.predicates.CellPredicates.containingCross;

public class ConnectionPredicates {

    public static Predicate<Connection> upToWinCondition() {
        return conn -> {
            Map<Cell, Connection.Type> connectionTypes = conn.getConnectionTypes();
            int jointCount = 0;
            int crossCount = 0;
            int circleCount  = 0;

            for (Cell cell : connectionTypes.keySet()) {
                if (connectionTypes.get(cell) == Connection.Type.JOINT)
                    jointCount++;
                if (connectionTypes.get(cell) == Connection.Type.END)
                    if (cell.isCell(containingCircle()))
                        circleCount++;
                    else if (cell.isCell(containingCross()))
                        crossCount++;
            }
            return jointCount == 1 && circleCount == 1 && crossCount == 1;
        };
    }

    public static Predicate<Connection> empty() {
        return conn -> conn.getConnectionCells().isEmpty();
    }

    public static Predicate<Connection> ended() {
        return conn -> {
            Map<Cell, Connection.Type> connectionTypes = conn.getConnectionTypes();
            int endsCount = 0;

            for (Cell cell : connectionTypes.keySet()) {
                if (connectionTypes.get(cell) == Connection.Type.END)
                    endsCount++;
            }
            return endsCount == 2;
        };
    }

    public static Predicate<Connection> containingCell(Cell cell) {
        return conn -> conn.getConnectionCells().contains(cell);
    }

    public static Predicate<Connection> withCell(Predicate<Cell> cellPred) {
        return conn -> conn.getConnectionCells().stream()
                .anyMatch(cellPred);
    }

    public static BiPredicate<Connection, Connection> interferingWith() {
        return (conn1, conn2) -> !Collections.disjoint(conn1.getConnectionCells(), conn2.getConnectionCells());
    }

}
