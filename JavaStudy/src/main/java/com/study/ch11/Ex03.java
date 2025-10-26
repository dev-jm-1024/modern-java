package com.study.ch11;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Optional 실전 예제 코드
 * Car, Insurance, Person 도메인 모델을 활용한 다양한 Optional 사용 패턴
 */
public class Ex03 {

    // ============================================
    // 1. 기본 Optional 사용 예제
    // ============================================
    public void basicOptionalExample() {
        // Optional 생성 방법 3가지

        // 1) 빈 Optional 생성
        Optional<Car> emptyCar = Optional.empty();
        System.out.println("빈 Optional: " + emptyCar);

        // 2) null이 아닌 값으로 Optional 생성 (null이면 NPE 발생)
        Car car = new Car(Optional.of(new Insurance("삼성화재")));
        Optional<Car> optCar1 = Optional.of(car);
        System.out.println("Optional.of: " + optCar1);

        // 3) null일 수도 있는 값으로 Optional 생성
        Car nullCar = null;
        Optional<Car> optCar2 = Optional.ofNullable(nullCar);
        System.out.println("Optional.ofNullable (null): " + optCar2);

        Optional<Car> optCar3 = Optional.ofNullable(car);
        System.out.println("Optional.ofNullable (not null): " + optCar3);
    }

    // ============================================
    // 2. map을 이용한 Optional 체이닝
    // ============================================
    public void mapExample() {
        Insurance insurance = new Insurance("현대해상");
        Optional<Insurance> optInsurance = Optional.of(insurance);

        // map을 이용한 값 추출
        Optional<String> name = optInsurance.map(Insurance::getName);
        System.out.println("보험 이름: " + name.orElse("없음"));

        // 빈 Optional에 map 적용 (아무 일도 일어나지 않음)
        Optional<Insurance> emptyInsurance = Optional.empty();
        Optional<String> emptyName = emptyInsurance.map(Insurance::getName);
        System.out.println("빈 Optional에 map 적용: " + emptyName.orElse("없음"));
    }

    // ============================================
    // 3. flatMap을 이용한 Optional 중첩 해결
    // ============================================
    public void flatMapExample() {
        // 정상적인 데이터
        Person person1 = new Person(
                Optional.of(new Car(Optional.of(new Insurance("KB손해보험"))))
        );

        // Car가 없는 Person
        Person person2 = new Person(Optional.empty());

        // flatMap을 사용한 안전한 체이닝
        String insuranceName1 = getCarInsuranceName(Optional.of(person1));
        String insuranceName2 = getCarInsuranceName(Optional.of(person2));
        String insuranceName3 = getCarInsuranceName(Optional.empty());

        System.out.println("Person1의 보험: " + insuranceName1);
        System.out.println("Person2의 보험: " + insuranceName2);
        System.out.println("Person이 없을 때: " + insuranceName3);
    }

    /**
     * flatMap을 이용한 안전한 보험 이름 조회
     */
    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    // ============================================
    // 4. filter를 이용한 조건 필터링
    // ============================================
    public void filterExample() {
        Insurance insurance1 = new Insurance("CambridgeInsurance");
        Insurance insurance2 = new Insurance("삼성화재");

        // filter를 이용한 특정 보험사 확인
        Optional<Insurance> opt1 = Optional.of(insurance1);
        opt1.filter(ins -> "CambridgeInsurance".equals(ins.getName()))
                .ifPresent(ins -> System.out.println("CambridgeInsurance 발견: " + ins.getName()));

        Optional<Insurance> opt2 = Optional.of(insurance2);
        opt2.filter(ins -> "CambridgeInsurance".equals(ins.getName()))
                .ifPresent(ins -> System.out.println("CambridgeInsurance 발견: " + ins.getName()));

        System.out.println("삼성화재는 필터를 통과하지 못했으므로 아무것도 출력되지 않음");

        // 여러 조건을 체이닝
        Person person = new Person(
                Optional.of(new Car(Optional.of(new Insurance("KB손해보험"))))
        );

        Optional.of(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .filter(ins -> ins.getName().startsWith("KB"))
                .ifPresent(ins -> System.out.println("KB로 시작하는 보험사: " + ins.getName()));
    }

    // ============================================
    // 5. orElse 계열 메서드 사용
    // ============================================
    public void orElseExample() {
        Optional<String> empty = Optional.empty();
        Optional<String> value = Optional.of("테스트");

        // orElse: 값이 없으면 기본값 반환
        System.out.println("orElse (empty): " + empty.orElse("기본값"));
        System.out.println("orElse (value): " + value.orElse("기본값"));

        // orElseGet: 값이 없으면 Supplier 실행 (Lazy 평가)
        System.out.println("orElseGet (empty): " + empty.orElseGet(() -> {
            System.out.println("  -> Supplier 실행됨");
            return "기본값 from Supplier";
        }));

        System.out.println("orElseGet (value): " + value.orElseGet(() -> {
            System.out.println("  -> 이 메시지는 출력되지 않음");
            return "기본값 from Supplier";
        }));

        // orElseThrow: 값이 없으면 예외 발생
        try {
            empty.orElseThrow(() -> new IllegalStateException("값이 없습니다!"));
        } catch (IllegalStateException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        // ifPresent: 값이 있으면 Consumer 실행
        value.ifPresent(v -> System.out.println("값이 존재함: " + v));

        // ifPresentOrElse: 값이 있으면 첫 번째 실행, 없으면 두 번째 실행
        empty.ifPresentOrElse(
                v -> System.out.println("값 존재: " + v),
                () -> System.out.println("값이 없어서 이 메시지가 출력됨")
        );
    }

    // ============================================
    // 6. 여러 Person의 보험 이름 수집
    // ============================================
    public void collectInsuranceNames() {
        List<Person> persons = Arrays.asList(
                new Person(Optional.of(new Car(Optional.of(new Insurance("삼성화재"))))),
                new Person(Optional.of(new Car(Optional.of(new Insurance("현대해상"))))),
                new Person(Optional.empty()),  // 차가 없는 사람
                new Person(Optional.of(new Car(Optional.empty()))),  // 보험이 없는 차
                new Person(Optional.of(new Car(Optional.of(new Insurance("KB손해보험")))))
        );

        // Optional.stream() 사용
        Set<String> insuranceNames = getCarInsuranceNames(persons);
        System.out.println("모든 보험 이름: " + insuranceNames);
    }

    /**
     * 여러 Person으로부터 보험 이름 수집
     */
    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Car::getInsurance)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Insurance::getName)
                .collect(Collectors.toSet());
    }

    // ============================================
    // 7. NPE가 발생하는 잘못된 코드 vs Optional을 사용한 안전한 코드
    // ============================================
    public void compareNullVsOptional() {
        Person personWithCar = new Person(
                Optional.of(new Car(Optional.of(new Insurance("삼성화재"))))
        );
        Person personWithoutCar = new Person(Optional.empty());

        System.out.println("--- 전통적인 null 체크 방식 ---");
        String name1 = getInsuranceNameNullCheck(personWithCar);
        String name2 = getInsuranceNameNullCheck(personWithoutCar);
        System.out.println("보험 이름1: " + name1);
        System.out.println("보험 이름2: " + name2);

        System.out.println("\n--- Optional을 사용한 방식 ---");
        String name3 = getCarInsuranceName(Optional.of(personWithCar));
        String name4 = getCarInsuranceName(Optional.of(personWithoutCar));
        System.out.println("보험 이름3: " + name3);
        System.out.println("보험 이름4: " + name4);
    }

    /**
     * 전통적인 null 체크 방식 (깊은 의심)
     */
    public String getInsuranceNameNullCheck(Person person) {
        if (person != null) {
            Optional<Car> carOpt = person.getCar();
            if (carOpt != null && carOpt.isPresent()) {
                Car car = carOpt.get();
                Optional<Insurance> insuranceOpt = car.getInsurance();
                if (insuranceOpt != null && insuranceOpt.isPresent()) {
                    Insurance insurance = insuranceOpt.get();
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    // ============================================
    // 8. Optional 생성 메서드 비교
    // ============================================
    public void optionalCreationComparison() {
        System.out.println("=== Optional 생성 방법 비교 ===");

        // empty() - 빈 Optional
        Optional<String> empty = Optional.empty();
        System.out.println("empty: " + empty);

        // of() - null 불가
        try {
            Optional<String> ofNull = Optional.of(null);
        } catch (NullPointerException e) {
            System.out.println("Optional.of(null) -> NPE 발생");
        }

        Optional<String> ofValue = Optional.of("값");
        System.out.println("of(\"값\"): " + ofValue);

        // ofNullable() - null 가능
        Optional<String> ofNullableNull = Optional.ofNullable(null);
        System.out.println("ofNullable(null): " + ofNullableNull);

        Optional<String> ofNullableValue = Optional.ofNullable("값");
        System.out.println("ofNullable(\"값\"): " + ofNullableValue);
    }

    // ============================================
    // 9. Optional과 Stream 연계
    // ============================================
    public void optionalWithStream() {
        System.out.println("=== Optional과 Stream 연계 ===");

        List<Optional<String>> listOfOptionals = Arrays.asList(
                Optional.of("Java"),
                Optional.empty(),
                Optional.of("Python"),
                Optional.empty(),
                Optional.of("Kotlin")
        );

        // 전통적인 방식
        List<String> result1 = listOfOptionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("전통적인 방식: " + result1);

        // Java 9+ stream() 사용
        List<String> result2 = listOfOptionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println("stream() 사용: " + result2);
    }

    // ============================================
    // 10. Optional 체이닝 예제
    // ============================================
    public void optionalChaining() {
        System.out.println("=== Optional 체이닝 예제 ===");

        Person person = new Person(
                Optional.of(new Car(Optional.of(new Insurance("메리츠화재"))))
        );

        // 긴 체이닝
        String result = Optional.of(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .orElse("NO_INSURANCE");

        System.out.println("체이닝 결과: " + result);
    }

    // ============================================
    // 11. Optional or() 메서드 (Java 9+)
    // ============================================
    public void optionalOrMethod() {
        System.out.println("=== Optional or() 메서드 ===");

        Optional<String> primary = Optional.empty();
        Optional<String> secondary = Optional.of("대체 값");

        // or()는 Optional을 반환
        Optional<String> result = primary.or(() -> secondary);
        System.out.println("or() 결과: " + result.orElse("최종 기본값"));

        // orElse()는 값을 직접 반환
        String result2 = primary.orElse("직접 기본값");
        System.out.println("orElse() 결과: " + result2);
    }

    // ============================================
    // 12. Optional 중첩 해제 예제
    // ============================================
    public void nestedOptionalExample() {
        System.out.println("=== Optional 중첩 해제 ===");

        // 잘못된 예: Optional<Optional<String>>
        Optional<Optional<String>> nested = Optional.of(Optional.of("중첩된 값"));

        // map 사용 시 중첩됨
        Optional<Optional<String>> stillNested = Optional.of("값")
                .map(s -> Optional.of(s.toUpperCase()));
        System.out.println("map 사용 (중첩): " + stillNested);

        // flatMap 사용으로 평평하게
        Optional<String> flattened = Optional.of("값")
                .flatMap(s -> Optional.of(s.toUpperCase()));
        System.out.println("flatMap 사용 (평평): " + flattened);
    }

    // ============================================
    // 13. 실무 패턴: null 안전 getter
    // ============================================
    public Optional<String> getInsuranceNameSafely(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName);
    }

    // ============================================
    // 14. 실무 패턴: 조건부 처리
    // ============================================
    public void conditionalProcessing() {
        System.out.println("=== 조건부 처리 ===");

        Optional<Insurance> insurance = Optional.of(new Insurance("KB손해보험"));

        // 조건 충족 시에만 처리
        insurance
                .filter(ins -> ins.getName().startsWith("KB"))
                .ifPresent(ins -> System.out.println("KB 계열 보험: " + ins.getName()));

        // 조건 충족하지 않으면 빈 Optional
        Optional<Insurance> filtered = insurance
                .filter(ins -> ins.getName().startsWith("삼성"));
        System.out.println("삼성으로 필터링: " + filtered);
    }

    // ============================================
    // 15. 실무 패턴: 여러 Optional 결합
    // ============================================
    public void combineOptionals() {
        System.out.println("=== 여러 Optional 결합 ===");

        Optional<String> firstName = Optional.of("홍");
        Optional<String> lastName = Optional.of("길동");

        // 둘 다 있을 때만 결합
        Optional<String> fullName = firstName.flatMap(first ->
                lastName.map(last -> first + last)
        );

        System.out.println("전체 이름: " + fullName.orElse("이름 없음"));
    }
}