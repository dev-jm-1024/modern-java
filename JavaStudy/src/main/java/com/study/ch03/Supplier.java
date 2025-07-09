package com.study.ch03;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
