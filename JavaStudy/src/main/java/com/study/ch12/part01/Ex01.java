package com.study.ch12.part01;

import java.time.*;
import java.time.temporal.ChronoField;

public class Ex01 {

    //1. LocalDate, LocalTime 사용

    //1.1 LocalDate 사용
    public static void useLocalDate(){

        LocalDate date = LocalDate.of(2017, 9, 21); //2017-09-21
        System.out.println(date);

        int year = date.getYear(); //2017
        System.out.println("year: " + year);

        Month month = date.getMonth(); // SEPTEMBER
        System.out.println("Month: " + month);

        int day = date.getDayOfMonth(); //21
        System.out.println("day: " + day);

        DayOfWeek dow = date.getDayOfWeek();
        System.out.println("dow: " + dow);

        int len = date.lengthOfMonth(); //31 : 해당 월이 며칠까지 있는 지
        System.out.println("해당 월은" + len + "일 까지 있음");

        boolean leapYear = date.isLeapYear(); //윤년여부
        System.out.println("윤년여부: " + leapYear);

        //시스템 시계의 정보 이용해서 현재 날짜 정보 얻음 : LocalDate.now();
        System.out.println("현재 날짜 정보: " + LocalDate.now());
    }

    //1.2 TemporalField / ChronoField 사용
    // get메소드에 TemporalField 를 전달해서 정보 얻을 수 있음
    //Temporal: 시간 관련 객체에서 어떤 필드의 값에 접근할 지 정의하는 인터페이스

    //열거자 ChronoField 는 TemporalField 인터페이스 정의해서 ChronoField 열거자 요소 이용해 정보 얻을 수 있음

    public static void userChronoField(){

        LocalDate date = LocalDate.of(2017, 9, 21);

        int year = date.get(ChronoField.YEAR); //연도
        System.out.println("date: " + date);
        System.out.println("year(ChronoField.YEAR): " + year);

        int month = date.get(ChronoField.MONTH_OF_YEAR); //월(1~12)
        System.out.println("month(ChronoField.MONTH_OF_YEAR): " + month);

        int day = date.get(ChronoField.DAY_OF_MONTH); //일(1~31)
        System.out.println("day(ChronoField.DAY_OF_MONTH): " + day);

        //내장 메소드를 이용해 가독성 높일 수 있다.
        System.out.println("******내장 메소드 사용*******");

        int year2 = date.getYear();
        System.out.println("year2: " + year2);

        int month2 = date.getMonthValue();
        System.out.println("month2: " + month2);

        int day2 = date.getDayOfMonth();
        System.out.println("day2: " + day2);
    }

    /*
    ChronoField.YEAR : 연도
    ChronoField.MONTH_OF_YEAR : 월 (1~12)
    ChronoField.DAY_OF_MONTH: 일(1~31)
    ChronoField.HOUR_OF_DAY: 시(0~23)
    ChronoField.MINUTE_OF_HOUR: 분(0~69)
     */

    //2. LocalTime  : 시간 다룸
    public static void useLocalTime(){

        //현재시간
        System.out.println("현재시간: "+LocalTime.now());

        //특정시간
        System.out.println(LocalTime.of(14, 30)); //14:30
        System.out.println(LocalTime.of(14, 30, 45)); //14:30:45

        //문자열 파싱
        String time = "09:15:30";
        System.out.println(LocalTime.parse(time));

        //LocalTime 만들고 값 읽기도 가능
        LocalTime time2 = LocalTime.of(14, 30, 45);
        int hour = time2.getHour(); //14
        System.out.println("hour: " + hour);

        int minute = time2.getMinute(); //30
        System.out.println("minute: " + minute);

        int second = time2.getSecond();
        System.out.println("second" + second);

    }

    //3. LocalDateTime : LocalDate와 LocalTime을 쌍으로 갖는 복합 클래스.
    //날짜와 시간 모두 표현 가능

    public static void useLocalDateTime(){

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        System.out.println("LocalDateTime.of(2017, 10, 24, 14, 30, 45);");
        LocalDateTime dt1 = LocalDateTime.of(2017, 10, 24, 14, 30, 45);

        System.out.println("LocalDateTime.of(2071, Month.OCTOBER, 24, 21 , 20, 19);");
        LocalDateTime dt11 = LocalDateTime.of(2071, Month.OCTOBER, 24, 21 , 20, 19);

        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);

        //atTime 메소드에 시간 제공하거나 atDate메소드에 날짜 제공해서 LocalDateTime 생성
        System.out.println("atTime 메소드에 시간 제공하거나 atDate메소드에 날짜 제공해서 LocalDateTime 생성");
        LocalDateTime dt4 = time.atDate(date);
        LocalDateTime dt5 = date.atTime(time);

        LocalDateTime dt6 = LocalDateTime.now();

        //LocalDate나 LocalTime 인스턴스 추출 가능
        LocalDate ld = dt6.toLocalDate();
        LocalTime lt = dt6.toLocalTime();

    }

}
