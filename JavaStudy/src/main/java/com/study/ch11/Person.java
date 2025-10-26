package com.study.ch11;

import java.util.Optional;

public class Person {

//    private Car car;
//
//    public Car getCar() {
//        return car;
//    }

    private Optional<Car> car;
    public Optional<Car> getCar() {
        return car;
    }

    public Person(Optional<Car> car) {
        this.car = car;
    }
}
