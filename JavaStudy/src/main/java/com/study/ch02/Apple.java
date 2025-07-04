package com.study.ch02;

public class Apple {
    private int weight;

    private enum Color {RED, GREEN};
    private Color color;

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }
}
