package com.study.ch02;

import static com.study.ch02.AppleGreenColorPredicate.Color.GREEN;

public class AppleGreenColorPredicate implements ApplePredicate {

    enum Color {GREEN, RED};

    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }
}
