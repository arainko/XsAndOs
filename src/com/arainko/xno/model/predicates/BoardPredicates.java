package com.arainko.xno.model.predicates;

import com.arainko.xno.model.board.ModelBoard;

import java.util.function.Predicate;

public class BoardPredicates {
    public static Predicate<ModelBoard> ableToAccommodateCords(int cordX, int cordY) {
        return b -> cordX >= 0 && cordY >= 0 && cordX < b.getDimX() && cordY < b.getDimY();
    }
}
