package com.study.ch06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class ToListCollector <T> implements Collector<T, List<T>, List<T>> {

    @Override
    //리스트 하나 생성 -- supplier
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    //리스트에 데이터 추가 -- accumulator
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    //병렬일 때 각각 합치는 용도 **병렬일 때 사용**
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    //최종적으로 완성품 꺼내기
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH, CONCURRENT)
        );
    }
}
