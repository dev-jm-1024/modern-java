package com.study;

import com.study.ch02.*;
import com.study.ch03.*;

import java.lang.Runnable;
import java.util.*;


public class Main{

    public static void main(String[] args) {

        //1. Runnable
        Runnable task = () -> System.out.println("Hello World");
        task.run();

        //2. Consumer
        Consumer<Integer> sum = x -> System.out.println(x);
        sum.accept(3);

        //3. Supplier
        Supplier<Double> randomGenerator = () -> Math.random();
        System.out.println(randomGenerator.get());

        //4. Function
        Function<String, Integer> strLength =  (String s) -> s.length();
        System.out.println(strLength.apply("123"));

        //5. Predicate
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println(isEven.test(3));

        //6. BiFunction
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println(add.apply(1, 2));

        //7. BinaryOperator
        BinaryOperator<String> concat = (a, b) -> a + b;
        System.out.println(concat.apply("hello", "world"));
    }
}