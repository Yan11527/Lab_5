package com.yan.common.element;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private double y; //Значение поля должно быть больше -969

    public Coordinates(Integer x, double y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x = " + x + ", y = " + y + "}";
    }
}
