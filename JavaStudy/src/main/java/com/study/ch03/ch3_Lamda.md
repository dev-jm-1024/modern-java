# 3. 람다 표현식 : 람다? 어디서 들어봤는데....

> **람다 (Lamda)**

- 이름 없는 함수, 익명 함수
- 주로 짧고 간단한 함수 한 줄로 정의할 때 사용

## 3.1 람다란 무엇인가

"람다 표현식"은 메소드로 전달할 수 있는 익명 함수를 단순화한 것이다.
표현식에 이름은 없지만 파라미터 리스트, 바디, 반환 형식, 발생할 수 있는 예외 리스트를 가질 수 있다.

**특징**
- 익명 : 이름이 없어 익명이라 표현
- 함수 : 특정 클래스에 종속되지 않으므로 함수라고 부른다.
- 전달 : 람다 표현식을 메소드 인수로 많은 코드 구현 필요 없다
- 간결성 : 익명 클래스처럼 많운 코드 구현 필요 없다

```java
Comparator<Apple> byWeight = new Comparator<Apple>(){
    public int compareTo(Apple a1, Apple a2){
        return a1.getWeight().compareTo(a2.getWeoght());
    }
};

Comparator<Apple> byWeight =
        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```

코드가 훨씬 간단해졌다.

람다 표현식은 파라미터, 화살표, 바디로 이루어진다

- 파라미터 리스트 : (Apple a1, Apple a2)
> Comparator의 compare 메소드 파라미터(사과 2개)

- 화살표 : ->
> 화살표는 람다의 파라미터 리스트와 바디를 구분한다.

- 람다 바디 : a1.getWeight().compareTo(a2.getWeight())
> 람다의 반환값에 해당하는 표현식이다


**자바 8의 유효한 람다 표현식**

```
(String s) -> s.length();

: String 형식의 파라미터 하나 가지며 int를 반환한다
: 람다 표현식에는 return이 함축되어 있으므로 return 문 명시적으로 사용하지 않아도 된다

2) 
(Apple a) -> a.getWeight() > 150;

: Apple 형식의 파라미터 하나 가지며 boolean 반환한다

3)
(int x, int y) -> {
	System.out.println("Result: ");
	System.out.println(x+y);
}

: int 형식 파라미터 2개 가지며 void 리턴
: 람다 표현식은 여러 행의 문장 포함 가능

4)

() -> 42;

: 파라미터 없으며 int 42 반환

**람다 규칙**

1) 매개변수 1개, 본문 1줄

> x -> x+1

: 괄호 생략 가능

2) 매개변수 2개

> (x, y) -> x+y;
> (x, y) -> {
	int sum = x+ y;
	return sum;
}

: 한 줄이면 괄호 생략 가능하며 {} 안이라면 return 필수이다

3) 명시적 타입 지정

(int x, int y) -> x*y; --(0)
(int x, y) -> x*y --(x)

: 단 하나라도 명시하면 전부 명시해야함

4) 변수 캡쳐
int base = 10;
Function<Integer, Integer> addBase = x -> x + base;

"int base = 10;" 부분은 final 혹은 사실상 final 이어야한다.


## 3.2 어디에, 어떻게 람다를 사용할까?

함수형 인터페이스라는 문맥에서 표현식 사용할 수 있다.

### 3.2.1 함수형 인터페이스

> **추상 메소드가 단 하나만 선언된 인터페이스 **

1) Runnable - 매개변수와 반환값이 없다

> 아무것도 받지 않고 아무것도 반환하지 않는다

```
public interface Runnable{
void run();
}

Runnable task = () -> System.out.println(" ~~~~~ ");
task.run();

```

2) Consumer<T> - 매개변수가 있으며 반환하지 않는다 -> 값을 받아 사용하기만 한다

```
public interface Consumer<T> {
void accept(T t);
}

Consumer<Integer> sum = (x, y) -> System.out.println(x+y);
sum.accept(2, 3);

```


3) Supplier<T> - 매개변수가 없고 T 타입 반환만 한다 -> 무언가 공급

```
public interface Supplier<T> {
T get();
}

Supplier<Double> randomGenerate = () -> Math.random();
System.out.println(randomGenerate.get());

```

4) Function<T, R> - 입력 값 T , 출력 값 R로 변환

```
public interface Function<T, R> {
R apply(T t);
}

Function<String, Integer> strLength = s -> s.length();
System.out.println(strLength("Lamda"));

``

5) Predicate<T> - 입력 값 T, true/false 반환

```
public interface Predicate<T> {
boolean test(T t);
}

Predicate<Integer> isEven = n -> n%2 == 0;
System.out.println(isEven.test(4));

```

6) BiFunction<F, U, R> - 두 개의 입력 값 T, U -> 하나의 출력 값 R