package com.study.ch08;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class Ex01 {
    // ====== 8.1 컬렉션 팩토리 ======

    // 8.1.1 Arrays.asList() 사용
    List<String> list = Arrays.asList("apple", "banana", "orange");

    //System.out.println("Arrays.asList() 결과: " + list);

    // 이때 Arrays.asList() 사용하여 고정 크기의 리스트를 만들었으므로 요소 갱신은 가능
    // 새 요소 추가하거나 요소 삭제 불가능 - UnsupportedOperationException 발생



    // Arrays.asSet()이라는 팩토리 메소드는 없으므로 리스트를 인수로 받는 HashSet 생성자 사용
    // Stream API를 사용할 수도 있다
    Set<String> set1 = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
    Set<String> set2 = Stream.of("apple", "banana", "orange").collect(Collectors.toSet());
        //System.out.println("HashSet 생성: " + set1);
        //System.out.println("Stream으로 Set 생성: " + set2);

    // 8.1.2 List.of 팩토리 사용
    List<String> immutableList = List.of("pen", "book", "pencil");
        //System.out.println("List.of() 결과: " + immutableList);


    // 8.1.3 집합 팩토리
    Set<String> friends = Set.of("Jane", "John", "Mark");
        //System.out.println("Set.of() 결과: " + friends);


    // 8.1.4 맵 팩토리
    // 맵은 키와 값이 있어야 한다
    Map<String, Integer> ageMap = Map.of("Jane", 30, "John", 25, "Mark", 28);
        //System.out.println("Map.of() 결과: " + ageMap);

    // Map.of 팩토리 메소드에 키와 값을 번갈아 제공하는 방법으로 맵을 만들 수 있다
    Map<String, Integer> ageMap2 = Map.ofEntries(
            entry("Jane", 30),
            entry("John", 25),
            entry("Mark", 28)
    );
        ///System.out.println("Map.ofEntries() 결과: " + ageMap2);

}
