package com.arainko.xno.gamelogic.elements;

import com.arainko.xno.gamelogic.abstracts.Element;

public class ConnectionUnit extends Element {
    public enum Type {
        HORIZONTAL,
        VERTICAL,
        JOIN,
        NONE
    }

    private Type connectionType;

    public ConnectionUnit(int cordX, int cordY) {
        super(cordX, cordY);
        setConnectionType(Type.NONE);
    }

    public void setConnectionType(Type connectionType) {
        this.connectionType = connectionType;
    }

    public Type getConnectionType() {
        return connectionType;
    }
}
