package com.study.ch09.strategy;

public class Validator {

    private final ValidationStrategy strategy;
    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean execute(String s) {
        return strategy.execute(s);
    }
}
