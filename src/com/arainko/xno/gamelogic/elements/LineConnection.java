package com.arainko.xno.gamelogic.elements;

import java.util.ArrayList;
import java.util.List;

public class LineConnection {

    private List<Line> lines;

    public LineConnection() {
        lines = new ArrayList<>();
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    private void jointLines() {
    }
}
