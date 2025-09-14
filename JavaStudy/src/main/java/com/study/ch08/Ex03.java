package com.study.ch08;

import java.util.*;

import static java.util.Map.entry;

public class Ex03 {

    // 8.3.1 forEach 메소드
    Map<String, Integer> ageOfFriends = new HashMap<>();
    ageOfFriends.put("Alice", 25);
    ageOfFriends.put("Bob", 30);
    ageOfFriends.put("Charlie", 28);

    ageOfFriends.forEach((friend, age) ->
            System.out.println(friend + " is " + age + " years old"));

    // 8.3.2 정렬 메소드
    ageOfFriends.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
            .forEachOrdered(System.out::println);

        ageOfFriends.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
            .forEachOrdered(System.out::println);

    // 8.3.3 getOrDefault 메소드
    Map<String, String> movies = Map.ofEntries(
            entry("Raphael", "Star Wars"),
            entry("Olivia", "James Bond")
    );

        System.out.println("Olivia: " + movies.getOrDefault("Olivia", "Matrix"));     // James Bond 출력
        System.out.println("Thibaut: " + movies.getOrDefault("Thibaut", "Matrix"));   // Matrix 출력

    // 8.3.4 계산 패턴
    Map<String, List<String>> friendsToMovies = new HashMap<>();

    // computeIfAbsent 사용
    friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>()).add("Star Wars");
    friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>()).add("Matrix");
    friendsToMovies.computeIfAbsent("Olivia", name -> new ArrayList<>()).add("James Bond");

    System.out.println("\ncomputeIfAbsent 결과:");
        friendsToMovies.forEach((friend, movieList) ->
            System.out.println(friend + ": " + movieList));

    // 8.3.5 삭제 패턴
    Map<String, String> favoriteMovies = new HashMap<>();
    favoriteMovies.put("Raphael", "Jack Reacher");
    favoriteMovies.put("Olivia", "James Bond");

    System.out.println("\n삭제 전: " + favoriteMovies);

    boolean removed = favoriteMovies.remove("Raphael", "Jack Reacher");  // 키와 값이 모두 일치할 때만 제거
        System.out.println("삭제 성공: " + removed);
        System.out.println("삭제 후: " + favoriteMovies);

    // 8.3.6 교체 패턴
        favoriteMovies.put("Olivia", "James Bond");
        favoriteMovies.put("Alice", "Matrix");

        System.out.println("\nreplaceAll 전: " + favoriteMovies);
        favoriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println("replaceAll 후 (대문자 변환): " + favoriteMovies);

    // 8.3.7 합침: merge
    Map<String, String> family = Map.ofEntries(
            entry("Teo", "Star Wars"),
            entry("Cristina", "Matrix")
    );

    Map<String, String> friends2 = Map.ofEntries(
            entry("Raphael", "Star Wars")
    );

    Map<String, String> everyone = new HashMap<>(family);
        friends2.forEach((k, v) ->
            everyone.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));

        System.out.println("\nmerge 결과: " + everyone);

        System.out.println("\n====== 8.4 ConcurrentHashMap 개선 ======");

    // ConcurrentHashMap 예제
    Map<String, Long> map = new HashMap<>();
        map.put("apple", 10L);
        map.put("banana", 20L);
        map.put("orange", 15L);

    // reduce 연산 예제 (일반 HashMap으로 시뮬레이션)
    long sum = map.values().stream().reduce(0L, Long::sum);
        System.out.println("전체 합계: " + sum);

    // search 연산 예제 (일반 HashMap으로 시뮬레이션)
    Optional<String> found = map.entrySet().stream()
            .filter(entry -> entry.getValue() > 15L)
            .map(Map.Entry::getKey)
            .findFirst();

        System.out.println("15보다 큰 값을 가진 첫 번째 키: " + found.orElse("없음"));
}
