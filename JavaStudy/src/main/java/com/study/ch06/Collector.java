package com.study.ch06;

import com.study.ch03.Supplier;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public interface Collector <T, A, R>{
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    Function<A, R> finisher();
    BinaryOperator<A> combiner();
    Set<java.util.stream.Collector.Characteristics> characteristics();

    //T: 스트림 원소 타입
    //A: 중간 누적 컨테이너 타입
    //R: 최종 결과 타입
}
