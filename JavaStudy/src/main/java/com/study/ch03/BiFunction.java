package com.study.ch03;

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
