package com.arainko.xno.abstracts;

import java.io.Serializable;
import java.util.Objects;

import static com.arainko.xno.abstracts.Board.*;

public abstract class Element implements Serializable {
    private int cordX;
    private int cordY;

    public Element(int cordX, int cordY) {
        this.setCordX(cordX);
        this.setCordY(cordY);
    }

    private void setCordX(int cordX) {
        if (cordX >= 0)
            this.cordX = cordX;
        else throw new IllegalArgumentException("Negative value: cordX = " + cordX);
    }

    private void setCordY(int cordY) {
        if (cordY >= 0)
            this.cordY = cordY;
        else throw new IllegalArgumentException("Negative value: cordY = " + cordY);
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public Cords getCords() {
        return new Cords(getCordX(), getCordY());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Element))
            return false;

        Element that = (Element) obj;
        return this.getCordX() == that.getCordX() && this.getCordY() == that.getCordY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cordX, cordY);
    }

    @Override
    public String toString() {
//        return "cordX = " + this.getCordX() + ", cordY = " + this.getCordY();
//        return cordX + " " + getCordY();
        return "E";
    }
}

