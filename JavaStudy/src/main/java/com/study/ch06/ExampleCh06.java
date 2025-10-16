package com.study.ch06;

import com.study.common.Product;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ExampleCh06 {

    // Product 리스트
    List<Product> products = Arrays.asList(
            new Product(1, "Laptop", 1200000, Product.ProductType.ELECTRONICS),
            new Product(2, "Smartphone", 900000, Product.ProductType.ELECTRONICS),
            new Product(3, "Headphones", 150000, Product.ProductType.ELECTRONICS),
            new Product(4, "Monitor", 300000, Product.ProductType.ELECTRONICS),
            new Product(5, "Keyboard", 50000, Product.ProductType.ELECTRONICS),
            new Product(6, "Mouse", 30000, Product.ProductType.ELECTRONICS),
            new Product(7, "Printer", 200000, Product.ProductType.ELECTRONICS),
            new Product(8, "Webcam", 80000, Product.ProductType.ELECTRONICS),
            new Product(9, "Tablet", 600000, Product.ProductType.ELECTRONICS),
            new Product(10, "Smartwatch", 250000, Product.ProductType.ELECTRONICS),
            new Product(11, "Banana", 1500, Product.ProductType.FOOD),
            new Product(12, "Milk", 2300, Product.ProductType.FOOD),
            new Product(13, "Bread", 2800, Product.ProductType.FOOD),
            new Product(14, "Jeans", 49000, Product.ProductType.CLOTHING),
            new Product(15, "T-shirt", 19000, Product.ProductType.CLOTHING),
            new Product(16, "Jacket", 85000, Product.ProductType.CLOTHING),
            new Product(17, "Novel", 12000, Product.ProductType.BOOK),
            new Product(18, "Notebook", 4000, Product.ProductType.BOOK),
            new Product(19, "Lipstick", 27000, Product.ProductType.BEAUTY),
            new Product(20, "Perfume", 99000, Product.ProductType.BEAUTY)
    );

    public enum PriceLevel {
        EXPENSIVE,
        REGULAR,
        INEXPENSIVE
    }

    //1. 갯수 세기
    long total = products.stream().count();

    //2.최댓값과 최솟값 검색
    Comparator<Product> maxPriceComp = Comparator.comparing(Product::getPrices);
    Optional<Product> resultMax = products.stream().collect(Collectors.maxBy(maxPriceComp));

    Comparator<Product> mimPriceComp = Comparator.comparing(Product::getPrices);
    Optional<Product> resultMin = products.stream().collect(Collectors.minBy(maxPriceComp));

    //3. 요약연산

    //3.1 summingInt
    int totalPricesInt = products.stream().collect(Collectors.summingInt(Product::getPrices));
    long totalPricesLong = products.stream().collect(Collectors.summingLong(Product::getPrices));
    double totalPricesDouble = products.stream().collect(Collectors.summingDouble(Product::getPrices));

    //3.2 averagingInt
    double avgInt = products.stream().collect(Collectors.averagingInt(Product::getPrices));

    //3.3 SummaryStatistics
    IntSummaryStatistics stats = products.stream().collect(Collectors.summarizingInt(Product::getPrices));

    //4. 문자열 연결
    String s1 = products.stream().map(Product::getName).collect(Collectors.joining(", "));
    String s2 = products.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));

    //plus. StringBuiler

    //String 사용
//    String s = "a";
//    s += "b";
//    s += "c";

    //String Builder
//    StringBuilder sb = new StringBuilder();
//    sb.append("a");
//    sb.append("b");
//    sb.append("c");
//
//    String result = sb.toString(); // "abc"


    //5. 범용 리듀싱 요약 연산
    // 모든 컬렉터는 reducing 팩토리 메소드로 정의 가능하다

    int a = products.stream().collect(reducing(
            0, Product::getPrices, (a1, a2) -> a1 + a2
    ));

    Optional<Product> max = products.stream().collect(reducing(
            (p1, p2) -> p1.getPrices() > p2.getPrices() ? p1:p2
    ));

    //하지만 Integer클래스의 sum 메소드 참조를 이용하면 좀 더 코드를 단순화 할 수 있다
    int a2 = products.stream().collect(reducing(0, Product::getPrices, Integer::sum));


    //6. 그룹화
    //Collectors.groupingBy를 이용해서 쉽게 그룹화 할 수 있다.
    Map<Product.ProductType, List<Product>> groupedProducts = products.stream()
            .collect(groupingBy(Product::getType));


    //6.1 그룹화된 요소 조작
    //각 결과 그룹의 요소를 조작하는 연산이 필요할 때 사용한다.

    //filtering 메소드: Collectors 클래스의 또 다른 정적 팩토리 메소드로 프레디케이를 인수로 받는다
    Map<Product.ProductType, List<Product>> map1 = products.stream()
            .collect(
                    groupingBy(
                            Product::getType,
                            filtering(p -> p.getPrices() > 500000, toList())
                    )
            );

    //mapping: Collectors 클래스는 매핑 함수와 각 항목에 적용한 함수를 모으는데
    //사용하는 또 다른 컬렉터를 인수로 받는 mapping 메소드 제공

    Map<Product.ProductType, List<String>> map2  = products.stream()
            .collect(groupingBy(Product::getType, mapping(Product::getName, toList())));

    //6.2 다수준 그룹화
    Map<Product.ProductType, Map<PriceLevel, List<Product>>> groupedByPriceLevel = products.stream()
            .collect(groupingBy(
                    Product::getType,
                    groupingBy(p -> {
                        if (p.getPrices() < 100000) {
                            return PriceLevel.INEXPENSIVE;
                        } else if (p.getPrices() < 500000) {
                            return PriceLevel.REGULAR;
                        } else {
                            return PriceLevel.EXPENSIVE;
                        }
                    })
            ));

    //collectionAndThen
    Map<Product.ProductType, Product> map3 = products.stream()
            .collect(
                    groupingBy(
                            Product::getType,
                            collectingAndThen(
                                    maxBy(Comparator.comparing(Product::getPrices)),
                                    Optional::get
                            )
                    )
            );

    //mapping
    Map<Product.ProductType, Integer> totalPricesByType = products.stream()
            .collect(groupingBy(Product::getType, mapping(Product::getPrices, summingInt(Integer::intValue))));

    //6.3 분할
    Map<Boolean, List<Product>> partitionedByType = products.stream()
            .collect(partitioningBy(p -> p.getPrices() < 100000));

}
