package com.study.ch09.refactor;

import com.study.ch09.strategy.IsAllLowerCase;
import com.study.ch09.strategy.IsNumeric;
import com.study.ch09.strategy.Validator;

public class Ex02 {

    //전략패턴

    //1. 일반적으로 사용
    Validator numericValidator = new Validator(new IsNumeric());
    boolean b1 = numericValidator.execute("123456");

    Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
    boolean b2 = lowerCaseValidator.execute("ABCDE");

    //2. 람다를 이용
    //다양한 전략을 구현하는 새로운 클래스를 구현할 필요 없이 람다 표현식을 직접 전달하면 코드가 간결해진다
    Validator numericValidator2 = new Validator((String s) -> s.matches("\\d+"));
    boolean b3 = numericValidator2.execute("123456");

    Validator lowerCaseValidator2 = new Validator((String s) -> s.matches("[a-z]+"));
    boolean b4 = lowerCaseValidator2.execute("ABCDE");
}
