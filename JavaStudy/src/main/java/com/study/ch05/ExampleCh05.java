package com.study.ch05;

import java.util.*;
import java.util.stream.Collectors;

public class ExampleCh05 {

    List<Person> people = Arrays.asList(
            new Person("홍길동", 23, "hong@naver.com", "남자"),
            new Person("김영희", 25, "kimyh@gmail.com", "여자"),
            new Person("이철수", 30, "lee123@daum.net", "남자"),
            new Person("박지민", 22, "jimin_p@naver.com", "여자"),
            new Person("최강찬", 27, "choi_kc@outlook.com", "남자"),
            new Person("장민주", 24, "jangmj@gmail.com", "여자"),
            new Person("오세훈", 29, "sehun_o@hanmail.net", "남자"),
            new Person("윤소라", 26, "sora_y@kakao.com", "여자"),
            new Person("강동원", 28, "dongwon.kang@naver.com", "남자"),
            new Person("배수지", 21, "suzy_b@daum.net", "여자")
    );

    List<Integer> numbers = Arrays.asList(
            1, 2, 3, 1, 2, 3, 4, 4, 4, 5, 6, 5, 6, 7, 7, 8, 9, 9, 9, 9, 10, 11, 11, 11, 12
    );

    //1. filter() 메소드와 //distinct 메소드 사용해서 필터링

    List<Person> malePeople = people.stream()
            .filter(person -> person.getGender().equals("남자"))
            .collect(Collectors.toList());

    List<Integer> removeDistinctNumberList = numbers.stream()
            .filter(i -> i % 2 == 0)
            .distinct()
            .collect(Collectors.toList());

    //2. 스트림 슬라이싱 -- takeWhile, dropWhile, limit

    //2.1 takeWhile : 조건이 true 인 동안 요소 가져옴. -- 정렬되어있을 때 효율적임
    List<Person> result1 = people.stream()
            .sorted(Comparator.comparing(Person::getAge))
            .takeWhile(person -> person.getAge() <= 25)
            .collect(Collectors.toList());


    //2.2 dropWhile : 조건이 true 인 동안 건너뛰고, 이후의 요소를 가져온다
    List<Person> result2 = people.stream()
            .sorted(Comparator.comparing(Person::getAge))
            .dropWhile(person -> person.getAge() <= 25)
            .collect(Collectors.toList());

    //2.3 limit : 앞의 n개만 자른다
    List<Person> result3 = people.stream().limit(2).collect(Collectors.toList());

    //2.4 skip : 처음 n개 요소를 제외한 스트림 반환
    List<Person> result4 = people.stream().skip(2).collect(Collectors.toList());

}
