package com.study;

import com.study.ch02.*;
import com.study.ch03.*;
import com.study.ch05.Person;

import java.lang.Runnable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class Main{

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("홍길동", 23, "hong@naver.com", "남자"),
                new Person("김영희", 45, "kimyh@gmail.com", "여자"),
                new Person("이철수", 30, "lee123@daum.net", "남자"),
                new Person("박지민", 52, "jimin_p@naver.com", "여자"),
                new Person("최강찬", 27, "choi_kc@outlook.com", "남자"),
                new Person("장민주", 44, "jangmj@gmail.com", "여자"),
                new Person("오세훈", 29, "sehun_o@hanmail.net", "남자"),
                new Person("윤소라", 36, "sora_y@kakao.com", "여자"),
                new Person("강동원", 28, "dongwon.kang@naver.com", "남자"),
                new Person("배수지", 11, "suzy_b@daum.net", "여자")
        );

        List<Integer> numbers = Arrays.asList(
                1, 2, 3, 1, 2, 3, 4, 4, 4, 5, 6, 5, 6, 7, 7, 8, 9, 9, 9, 9, 10, 11, 11, 11, 12
        );


        Comparator<Person> personComparator = Comparator.comparingInt(Person::getAge);

        //리듀싱: maxBy, minBy를 통해 최대 나이와 최소 나이 구하기
        Optional<Person> maxAgePerson = people.stream().collect(Collectors.maxBy(personComparator));
        Optional<Person> minAgePerson = people.stream().collect(Collectors.minBy(personComparator));
        maxAgePerson.ifPresent(System.out::println);
        minAgePerson.ifPresent(System.out::println);


        //요약

        //1. summingInt - 다 더함
        int totalAge = people.stream().collect(summingInt(Person::getAge));
        System.out.println(totalAge);

        //2. averagingInt - 평균 ==> averagingDouble, averagingLong 등 가능

        double avgDouble = people.stream().collect(averagingDouble(Person::getAge));
        System.out.println(avgDouble);

        //3. 문자열 연결 - joining()

        String str = people.stream().map(Person::getName).limit(4).collect(joining(", "));
        System.out.println(str);
    }
}