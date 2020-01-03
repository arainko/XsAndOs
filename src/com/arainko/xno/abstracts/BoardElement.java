package com.arainko.xno.abstracts;

import java.io.Serializable;

public interface BoardElement extends Serializable {
    Board.Cords getCords();
}
