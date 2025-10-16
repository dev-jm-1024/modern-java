package com.study.ch09.refactor;

import com.study.common.Product;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.groupingBy;

public class Ex01 {

    private static final Logger logger = Logger.getLogger(Ex01.class.getName());

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

    public void convertLambdaByAnonymousClass(){

        //익명 클래스 버전
        Runnable r1 = new Runnable() {

            public void run(){
                System.out.println("Hello - Old Version");
            }
        };

        //람다버전
        Runnable r2 = () -> System.out.println("Hello - Lambda Version");

        System.out.println("익명 클래스: "+r1 + "\n람다로 리팩토링: " + r2);

        //고려해야할 조건
        /*
        1. 함수형 인터페이스
        2. this 고려
        3. 클래스 필드 / 메소드 오버라이딩
        4. 지역변수
        5. 모호함
         */
    }

    public void lambdaExpressionByMethodReference(){

        //1. 일반적인 그룹화
        products.stream().collect(
                groupingBy(
                        p -> {
                            if(p.getPrices() < 100000) {
                                return PriceLevel.INEXPENSIVE;
                            } else if(p.getPrices() < 500000) {
                                return PriceLevel.REGULAR;
                            } else {
                                return PriceLevel.EXPENSIVE;
                            }
                        }
                )
        );

        //2. 메소드 참조로 리펙토링 하기
        products.stream().collect(groupingBy(this::getPriceLevel));

    }

    // 조건부 로깅 예제
    public void conditionalDeferredExecution() {
        // 단순한 메시지 로깅
        logger.info("Start conditional logging test...");

        // 조건부 로깅 (비싼 연산 포함될 경우)
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Problem : " + generateDiagnostic());
        }

        // 람다 스타일 (Java 8 이상)
        logIf(Level.FINE, () -> "Problem (lambda) : " + generateDiagnostic());
    }

    private PriceLevel getPriceLevel(Product p) {
        if(p.getPrices() < 100000) {
            return PriceLevel.INEXPENSIVE;
        } else if (p.getPrices() < 500000) {
            return PriceLevel.REGULAR;
        }else {
            return PriceLevel.EXPENSIVE;
        }
    }

    // Supplier<String> 활용 → 지연 실행
    private void logIf(Level level, java.util.function.Supplier<String> msgSupplier) {
        if (logger.isLoggable(level)) {
            logger.log(level, msgSupplier.get());
        }
    }

    // 더미 진단 메시지 생성 메소드 (무거운 연산이라고 가정)
    private String generateDiagnostic() {
        try {
            Thread.sleep(500); // 무거운 연산 흉내
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "CPU Usage High, Memory Leak suspected!";
    }


}
