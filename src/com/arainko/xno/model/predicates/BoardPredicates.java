package com.arainko.xno.model.predicates;

import com.arainko.xno.model.board.ModelBoard;

import java.util.function.Predicate;

import static com.arainko.xno.model.predicates.CellPredicates.containingCross;
import static com.arainko.xno.model.predicates.ConnectionPredicates.upToWinCondition;
import static java.util.function.Predicate.not;

public class BoardPredicates {
    public static Predicate<ModelBoard> ableToAccommodateCords(int cordX, int cordY) {
        return b -> cordX >= 0 && cordY >= 0 && cordX < b.getDimX() && cordY < b.getDimY();
    }

    public static Predicate<ModelBoard> done() {
        return b -> {
            long crossCount = b.getFlattenedBoardElements().stream()
                    .filter(containingCross())
                    .count();
            long goodConnectionsCount = b.getConnections().stream()
                    .filter(upToWinCondition())
                    .count();
            return goodConnectionsCount != 0 && crossCount == goodConnectionsCount;
        };
    }
}
