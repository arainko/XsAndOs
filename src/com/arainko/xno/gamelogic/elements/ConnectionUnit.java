package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.abstracts.Element;

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

    @Override
    public String toString() {
        return "(" + getCordX() +", "+ getCordY() + ")";
    }
}
