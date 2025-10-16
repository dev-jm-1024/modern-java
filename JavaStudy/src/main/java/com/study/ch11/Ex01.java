package com.study.ch11;

public class Ex01 {

    private static final String DEFAULT_VALUE = "Unknown";

    //1. 해당 메소드를 실행하면 어떠한 결과가 나타날까?
    //만약 Person이 null 이면 어떻게 될까?
    public String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }

    //2. 개선된 getCarInsuranceName()
    public String getCarInsuranceName2(Person person) {

        if(person != null){
            Car car = person.getCar();
            if(car != null){
                Insurance insurance = car.getInsurance();
                if(insurance != null){
                    return insurance.getName();
                }
            }
        }

        return DEFAULT_VALUE;
    }

    //3. 개선된 getCarInsuranceName()
    public String getCarInsuranceName3(Person person) {

        if(person == null){
            return DEFAULT_VALUE;
        }
        Car car = person.getCar();

        if(car == null){
            return DEFAULT_VALUE;
        }

        Insurance insurance = car.getInsurance();

        if(insurance == null){
            return DEFAULT_VALUE;
        }

        return insurance.getName();
    }

    //해당 메소드는 null 확인 코드마다 출구가 생긴다.
    //게다가 null 일 때 반환되는 기본값이 세 곳에서 반복되고 있는데 오타등의 이슈가 생길 수 있다.
    //물론 내가 쓴 방식처럼 문자열을 상수로 만들어서 이 문제를 해결할 수 있다.

    
}
