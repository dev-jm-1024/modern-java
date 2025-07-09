3. 람다 표현식 - 람다..? 어디서 들어봤는데...

# 🚀 Java 8 람다 표현식 완벽 가이드

> **람다 (Lambda)**  
> 이름 없는 함수, 즉 **익명 함수**를 의미한다.  
> 주로 짧고 간단한 함수를 한 줄로 정의할 때 사용된다.

---

## 📌 1. 람다란 무엇인가?

"람다 표현식"은 메소드로 전달할 수 있는 익명 함수를 단순화한 것이다. 표현식에 이름은 없지만 **파라미터 리스트**, **바디**, **반환 형식**, **발생할 수 있는 예외 리스트**를 가질 수 있다.

### ✨ 람다의 특징

| 특징 | 설명 |
|------|------|
| **익명** | 이름이 없어 익명이라 표현한다 |
| **함수** | 특정 클래스에 종속되지 않으므로 함수라고 부른다 |
| **전달** | 람다 표현식을 메소드 인수로 전달할 수 있다 |
| **간결성** | 익명 클래스처럼 많은 코드 구현이 필요 없다 |

### 🔄 기존 코드 vs 람다 표현식

```java
// 기존 방식 (익명 클래스)
Comparator<Apple> byWeight = new Comparator<Apple>(){
    public int compare(Apple a1, Apple a2){
        return a1.getWeight().compareTo(a2.getWeight());
    }
};

// 람다 표현식 사용
Comparator<Apple> byWeight =
        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```

코드가 훨씬 간단해졌다! 💡

---

## 📖 2. 람다 표현식의 구조

람다 표현식은 **파라미터**, **화살표**, **바디**로 이루어진다.

```java
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
```

- **파라미터 리스트**: `(Apple a1, Apple a2)` - Comparator의 compare 메소드 파라미터
- **화살표**: `->` - 람다의 파라미터 리스트와 바디를 구분한다
- **람다 바디**: `a1.getWeight().compareTo(a2.getWeight())` - 람다의 반환값에 해당하는 표현식

### 🎯 자바 8의 유효한 람다 표현식 예시

```java
// 1) String 형식 파라미터 하나, int 반환
(String s) -> s.length()

// 2) Apple 형식 파라미터 하나, boolean 반환
(Apple a) -> a.getWeight() > 150

// 3) int 형식 파라미터 2개, void 반환
(int x, int y) -> {
    System.out.println("Result: ");
    System.out.println(x + y);
}

// 4) 파라미터 없음, int 42 반환
() -> 42
```

---

## 📋 3. 람다 작성 규칙

### 1️⃣ 매개변수 1개, 본문 1줄
```java
x -> x + 1  // 괄호 생략 가능
```

### 2️⃣ 매개변수 2개
```java
(x, y) -> x + y  // 한 줄이면 괄호 생략 가능

(x, y) -> {
    int sum = x + y;
    return sum;  // {} 안이라면 return 필수
}
```

### 3️⃣ 명시적 타입 지정
```java
(int x, int y) -> x * y  // ✅ 올바른 예시
(int x, y) -> x * y      // ❌ 잘못된 예시 - 하나라도 명시하면 전부 명시해야 함
```

### 4️⃣ 변수 캡쳐
```java
int base = 10;
Function<Integer, Integer> addBase = x -> x + base;
```
`base` 변수는 **final** 혹은 **사실상 final**이어야 한다.

---

## 🎯 4. 어디에, 어떻게 람다를 사용할까?

**함수형 인터페이스**라는 문맥에서 람다 표현식을 사용할 수 있다.

### 💡 함수형 인터페이스란?

> **추상 메소드가 단 하나만 선언된 인터페이스**

---

## 📚 5. 주요 함수형 인터페이스

### 1️⃣ Runnable - 매개변수와 반환값이 없다
```java
public interface Runnable {
    void run();
}

Runnable task = () -> System.out.println("Hello Lambda!");
task.run();
```

### 2️⃣ Consumer<T> - 매개변수가 있으며 반환하지 않는다
```java
public interface Consumer<T> {
    void accept(T t);
}

Consumer<String> printer = s -> System.out.println(s);
printer.accept("Hello World");
```

### 3️⃣ Supplier<T> - 매개변수가 없고 T 타입만 반환한다
```java
public interface Supplier<T> {
    T get();
}

Supplier<Double> randomGenerator = () -> Math.random();
System.out.println(randomGenerator.get());
```

### 4️⃣ Function<T, R> - 입력 값 T를 출력 값 R로 변환
```java
public interface Function<T, R> {
    R apply(T t);
}

Function<String, Integer> strLength = s -> s.length();
System.out.println(strLength.apply("Lambda"));
```

### 5️⃣ Predicate<T> - 입력 값 T를 받아 true/false 반환
```java
public interface Predicate<T> {
    boolean test(T t);
}

Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4));
```

### 6️⃣ BiFunction<T, U, R> - 두 개의 입력 값을 하나의 출력 값으로 변환
```java
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}

BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
System.out.println(add.apply(3, 4));
```

### 7️⃣ BinaryOperator<T> - 같은 타입의 두 인자를 받아 같은 타입의 결과 반환
```java
public interface BinaryOperator<T> extends BiFunction<T, T, T> {
    // 생략된 static 메소드들 (maxBy, minBy 등)
}

BinaryOperator<String> concat = (a, b) -> a + b;
System.out.println(concat.apply("Ja", "va"));
```

### 📊 함수형 인터페이스 요약표

| 함수형 인터페이스 | 함수 디스크립터 | 기본형 특화 |
|-------------------|------------------|-------------|
| `Predicate<T>` | `T -> boolean` | `IntPredicate`, `LongPredicate`, `DoublePredicate` |
| `Consumer<T>` | `T -> void` | `IntConsumer`, `LongConsumer`, `DoubleConsumer` |
| `Function<T, R>` | `T -> R` | `IntFunction<R>`, `IntToDoubleFunction`, `IntToLongFunction` |
| `Supplier<T>` | `() -> T` | `BooleanSupplier`, `IntSupplier`, `LongSupplier`, `DoubleSupplier` |
| `UnaryOperator<T>` | `T -> T` | `IntUnaryOperator`, `LongUnaryOperator`, `DoubleUnaryOperator` |
| `BinaryOperator<T>` | `(T, T) -> T` | `IntBinaryOperator`, `LongBinaryOperator`, `DoubleBinaryOperator` |
| `BiPredicate<L, R>` | `(L, R) -> boolean` | |
| `BiConsumer<T, U>` | `(T, U) -> void` | `ObjIntConsumer<T>`, `ObjLongConsumer<T>`, `ObjDoubleConsumer<T>` |
| `BiFunction<T, U, R>` | `(T, U) -> R` | `ToIntBiFunction<T, U>`, `ToLongBiFunction<T, U>`, `ToDoubleBiFunction<T, U>` |

### 🔍 @FunctionalInterface 어노테이션

```java
@FunctionalInterface
public interface MyFunctionalInterface {
    void doSomething();
}
```

**사용 이유:**
1. **컴파일 타임 검증** - 추상 메소드가 2개 이상이면 컴파일 에러 발생
2. **문서화 용도** - 해당 인터페이스가 람다식의 대상임을 명시
3. **실수 방지** - default/static 메소드 추가는 가능하지만 추상 메소드는 오직 1개만

---

## 🔄 6. 람다 활용: 실행 어라운드 패턴

자원 처리에 사용하는 순환 패턴은 **자원 열기 → 처리 → 자원 닫기** 순서로 이루어진다. 설정과 정리 과정은 대부분 비슷하다.

```
====================
   초기화/준비 코드
====================
       작업 A
====================
   정리/마무리 코드
====================
```

### 🎯 실행 어라운드 패턴 구현 단계

#### 1단계: 동작 파라미터화
```java
String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
```

#### 2단계: 함수형 인터페이스를 이용해 람다 전달
```java
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}

public String processFile(BufferedReaderProcessor p) throws IOException {
    // 구현 내용
}
```

#### 3단계: 동작 실행
```java
public String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
        return p.process(br);
    }
}
```

#### 4단계: 람다 전달
```java
String oneLine = processFile((BufferedReader br) -> br.readLine());
String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
```

---

## 🔍 7. 형식 검사와 형식 추론

### 📋 형식 검사 과정

```java
List<Apple> heavierThan150g = 
    filter(inventory, (Apple apple) -> apple.getWeight() > 150);
```

1. 람다가 사용된 컨텍스트 확인 → `filter` 메소드 정의 확인
2. 대상 형식은 `Predicate<Apple>`
3. `Predicate<Apple>` 인터페이스의 추상 메소드 확인 → `boolean test(Apple apple)`
4. 함수 디스크립터는 `Apple -> boolean`
5. 람다의 시그니처와 일치하는지 확인

### 🎯 같은 람다, 다른 함수형 인터페이스

```java
Runnable r = () -> System.out.println("Hello");          // () -> void
Consumer<String> c = s -> System.out.println("Hello");   // (String) -> void
```

### 📊 형식 추론

```java
// 형식을 명시적으로 지정
List<Apple> greenApples = 
    filter(inventory, (Apple apple) -> GREEN.equals(apple.getColor()));

// 형식 추론 활용
List<Apple> greenApples = 
    filter(inventory, apple -> GREEN.equals(apple.getColor()));
```

### 🔒 지역변수 사용 제약

```java
int port = 1337;
Runnable r = () -> System.out.println(port);  // port는 final이거나 사실상 final이어야 함
```

**제약 이유:**
- 인스턴스 변수는 **힙**에 저장
- 지역변수는 **스택**에 저장
- 람다는 지역변수의 **복사본**을 사용하므로 변경되면 안 됨

---

## 🔗 8. 메소드 참조

메소드 참조를 이용하면 기존 메소드 정의를 재활용해서 람다처럼 전달할 수 있다.

### 🔄 변환 예시

```java
// 람다 표현식
inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

// 메소드 참조
inventory.sort(comparing(Apple::getWeight));
```

### 📋 메소드 참조 유형

#### 1️⃣ 정적 메소드 참조
```java
Integer::parseInt
```

#### 2️⃣ 다양한 형식의 인스턴스 메소드 참조
```java
String::length
```

#### 3️⃣ 기존 객체의 인스턴스 메소드 참조
```java
expensiveTransaction::getValue
```

### 🏗️ 생성자 참조

```java
// 인수 없는 생성자
Supplier<Apple> c1 = Apple::new;
Apple a1 = c1.get();

// 인수 있는 생성자
Function<Integer, Apple> c2 = Apple::new;  // Apple(Integer weight) 생성자 참조
Apple a2 = c2.apply(110);
```

---

## ⚡ 9. 람다 표현식 조합하기

### 🔀 Comparator 조합

```java
inventory.sort(comparing(Apple::getWeight)    // 무게 기준 정렬
    .reversed()                               // 내림차순 정렬
    .thenComparing(Apple::getCountry));       // 무게가 같으면 국가별 정렬
```

### 🎯 Predicate 조합

```java
// 기본 프레디케이트
Predicate<Apple> redApple = apple -> RED.equals(apple.getColor());

// negate (반전)
Predicate<Apple> notRedApple = redApple.negate();

// and (그리고)
Predicate<Apple> redAndHeavyApple = 
    redApple.and(apple -> apple.getWeight() > 150);

// or (또는)
Predicate<Apple> redAndHeavyAppleOrGreen = 
    redApple.and(apple -> apple.getWeight() > 150)
            .or(apple -> GREEN.equals(apple.getColor()));
```

**우선순위:** `a.or(b).and(c)`는 `(a || b) && c`와 같다.

### 🔄 Function 조합

```java
Function<Integer, Integer> f = x -> x + 1;
Function<Integer, Integer> g = x -> x * 2;

// andThen (f 적용 후 g 적용)
Function<Integer, Integer> h = f.andThen(g);  // g(f(x))

// compose (g 적용 후 f 적용)
Function<Integer, Integer> h = f.compose(g);  // f(g(x))
```

---

## 🎯 10. 실전 예제: 정렬 코드 개선

### 1단계: 코드 전달
```java
public class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
}

inventory.sort(new AppleComparator());
```

### 2단계: 익명 클래스 사용
```java
inventory.sort(new Comparator<Apple>() {
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
});
```

### 3단계: 람다 표현식 사용
```java
inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

// 또는
inventory.sort(comparing(apple -> apple.getWeight()));
```

### 4단계: 메소드 참조 사용
```java
inventory.sort(comparing(Apple::getWeight));
```

---

## 🚨 11. 예외 처리

함수형 인터페이스는 확인된 예외를 던지는 동작을 허용하지 않는다. 예외를 던지는 람다 표현식을 만들려면:

### 방법 1: 확인된 예외를 선언하는 함수형 인터페이스 정의
```java
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
```

### 방법 2: 람다를 try-catch 블록으로 감싸기
```java
Function<BufferedReader, String> f = (BufferedReader b) -> {
    try {
        return b.readLine();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
};
```

---

## 🎯 12. 마무리

### ✅ 핵심 정리

- **람다 표현식**은 익명 함수의 일종으로 간결한 코드 구현이 가능하다
- **함수형 인터페이스**는 하나의 추상 메소드만 정의하는 인터페이스이다
- 람다 표현식 전체가 **함수형 인터페이스의 인스턴스**로 취급된다
- **메소드 참조**를 이용하면 기존 메소드 구현을 재사용하고 직접 전달할 수 있다
- `Comparator`, `Predicate` 등의 함수형 인터페이스는 람다 표현식을 조합할 수 있는 **디폴트 메소드**를 제공한다

### 🚀 람다가 유용한 상황

- 컬렉션 정렬
- 필터링 조건 전달
- 매핑/변환 작업
- 행동 파라미터화

---



> 💡 **람다 표현식을 마스터하면 더 함수형 프로그래밍에 가까운 자바 코드를 작성할 수 있다!**

---

### 📚 공부 자료

- Modern Java In Action
- 옮긴이 : 우정은
- 펴낸이 : 전태호
- 지은지 : 라울-게이브리얼 우르마, 마리오 푸스코, 앨런 마이크로프트

---

**다음 포스팅에서는 스트림 API에 대해 알아보자!** 🌊