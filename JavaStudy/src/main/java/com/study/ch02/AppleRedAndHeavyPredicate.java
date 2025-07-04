package com.study.ch02;

import java.util.List;

import static com.study.ch02.AppleRedAndHeavyPredicate.Color.RED;

public class AppleRedAndHeavyPredicate implements ApplePredicate {

    enum Color {RED, GREEN};

    @Override
    public boolean test(Apple apple) {
        return RED.equals(apple.getColor()) && apple.getWeight() > 150;
    }

}
