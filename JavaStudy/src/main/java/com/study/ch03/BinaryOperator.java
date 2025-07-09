package com.study.ch03;

@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {
    //생략된 static 메소드 있음. (maxBy, minBy, ...)
}
