package com.arainko.xno.model.predicates;

import com.arainko.xno.helpers.Cords;
import com.arainko.xno.model.board.ModelBoard;

import java.util.function.Predicate;

public class BoardPredicates {
    public static Predicate<ModelBoard> ableToAccommodateCords(Cords cords) {
        return b -> cords.X() >= 0 && cords.Y() >= 0 && cords.X() < b.getDimX() && cords.Y() < b.getDimY();
    }
}
