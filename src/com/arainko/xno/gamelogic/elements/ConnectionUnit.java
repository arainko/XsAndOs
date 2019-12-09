package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.abstracts.Element;

import java.util.ArrayList;
import java.util.List;

public class ConnectionUnit extends Element {
    public enum Type {
        HORIZONTAL,
        VERTICAL,
        JOINT,
        END,
        START,
        NONE
    }

    private Type connectionType;
    private Element container;

    public ConnectionUnit(int cordX, int cordY) {
        super(cordX, cordY);
        setConnectionType(Type.NONE);
    }

    boolean isNextToOnPaneX(ConnectionUnit that) {
        return Math.abs(this.getCordX() - that.getCordX()) == 1 && this.getCordY()-that.getCordY() == 0;
    }

    boolean isNextToOnPaneY(ConnectionUnit that) {
        return Math.abs(this.getCordY() - that.getCordY()) == 1 && this.getCordX()-that.getCordX() == 0;
    }

    public void setConnectionType(Type connectionType) {
        this.connectionType = connectionType;
    }

    public Type getConnectionType() {
        return connectionType;
    }

    public void setContainer(Element element) {
        this.container = element;
    }

    @Override
    public String toString() {
        return "(" + container.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ConnectionUnit))
            return false;

        ConnectionUnit that = (ConnectionUnit) obj;
        return this.getCordX() == that.getCordX() && this.getCordY() == that.getCordY();
    }
}
