package com.study.ch08;

import java.util.*;

public class Ex02 {

    // removeIf: 프레디케이트를 만족하는 요소 제거. List/Set 구현 혹은 구현을 상속받은 모든 클래스 사용 가능
    List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David"));
    List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

    //names.removeIf(name -> name.length() <= 3);  // 길이가 3이하인 이름들 제거
    //numbers.removeIf(n -> n % 2 == 0);  // 숫자 리스트에서 짝수 제거

    // replaceAll: 리스트에서 이용할 수 있는 기능으로 UnaryOperator 함수를 이용해 바꿈
    List<String> codes = new ArrayList<>(Arrays.asList("a1", "b2", "c3"));
    //codes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));

    // sort: List 인터페이스에서 제공하는 기능. 리스트를 정렬함
    List<String> fruits = new ArrayList<>(Arrays.asList("banana", "apple", "orange", "grape"));
    //fruits.sort(String::compareTo);  // 알파벳 순으로 정렬

}
