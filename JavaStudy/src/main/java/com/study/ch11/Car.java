package com.study.ch11;

import java.util.Optional;

public class Car {

    //기존
//    private Insurance insurance;
//
//    public Insurance getInsurance() {
//        return insurance;
//    }

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public Car(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
