package com.study.ch11;

import java.util.Optional;

public class Ex02 {

    //Optional 객체 만들기

    //빈 Optional 만들기
    Optional<Car> makeEmptyOptionalCar(){
        return Optional.empty();
    }

    //null 이 아닌 값으로 Optional 만들기 : of()
    Optional<Car> makeOptionalCar(Car car){
        return Optional.of(car);
    }

    //null 값으로 Optional 만들기
    //car가 null 이면 빈 Optional객체가 반환된다

    Optional<Car> makeOptionalCarWithNull(Car car){
        return Optional.ofNullable(car);
    }
}
