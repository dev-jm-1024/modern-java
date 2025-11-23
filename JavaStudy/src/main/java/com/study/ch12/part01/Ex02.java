package com.study.ch12.part01;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Ex02 {

    // 1. Instant 사용 - 기계의 날짜와 시간
    public static void useInstant() {

        System.out.println("===== Instant 사용 =====");

        // 현재 시각
        Instant now = Instant.now();
        System.out.println("현재 Instant: " + now);

        // epoch second로 Instant 생성
        Instant instant1 = Instant.ofEpochSecond(3);
        System.out.println("3초: " + instant1);

        Instant instant2 = Instant.ofEpochSecond(3, 0);
        System.out.println("3초 + 0나노초: " + instant2);

        // 2초 + 10억 나노초 = 3초
        Instant instant3 = Instant.ofEpochSecond(2, 1_000_000_000);
        System.out.println("2초 + 10억 나노초: " + instant3);

        // 4초 - 10억 나노초 = 3초
        Instant instant4 = Instant.ofEpochSecond(4, -1_000_000_000);
        System.out.println("4초 - 10억 나노초: " + instant4);

        // Instant는 사람이 읽을 수 있는 시간 정보를 제공하지 않음
        System.out.println("epoch second: " + now.getEpochSecond());
        System.out.println("nano: " + now.getNano());
    }

    // 2. Duration 사용 - 시간 기반 간격
    public static void useDuration() {

        System.out.println("\n===== Duration 사용 =====");

        LocalTime time1 = LocalTime.of(13, 45, 20);
        LocalTime time2 = LocalTime.of(15, 30, 10);

        Duration d1 = Duration.between(time1, time2);
        System.out.println("time1과 time2 사이의 Duration: " + d1);
        System.out.println("초 단위: " + d1.getSeconds() + "초");
        System.out.println("분 단위: " + d1.toMinutes() + "분");

        // LocalDateTime으로도 Duration 계산 가능
        LocalDateTime dateTime1 = LocalDateTime.of(2017, 9, 21, 13, 45);
        LocalDateTime dateTime2 = LocalDateTime.of(2017, 9, 21, 15, 30);

        Duration d2 = Duration.between(dateTime1, dateTime2);
        System.out.println("dateTime1과 dateTime2 사이의 Duration: " + d2);

        // Instant로도 Duration 계산 가능
        Instant instant1 = Instant.now();
        Instant instant2 = instant1.plusSeconds(3600);

        Duration d3 = Duration.between(instant1, instant2);
        System.out.println("instant1과 instant2 사이의 Duration: " + d3);

        // Duration 직접 생성
        Duration threeMinutes = Duration.ofMinutes(3);
        System.out.println("3분 Duration: " + threeMinutes);

        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println("3분 Duration(ChronoUnit 사용): " + threeMinutes2);

        Duration oneDay = Duration.ofDays(1);
        System.out.println("1일 Duration: " + oneDay);
        System.out.println("1일을 시간으로: " + oneDay.toHours() + "시간");
    }

    // 3. Period 사용 - 날짜 기반 간격
    public static void usePeriod() {

        System.out.println("\n===== Period 사용 =====");

        LocalDate date1 = LocalDate.of(2017, 9, 11);
        LocalDate date2 = LocalDate.of(2017, 9, 21);

        Period tenDays = Period.between(date1, date2);
        System.out.println("date1과 date2 사이의 Period: " + tenDays);
        System.out.println("일 수: " + tenDays.getDays() + "일");

        // 더 긴 기간
        LocalDate date3 = LocalDate.of(2017, 1, 1);
        LocalDate date4 = LocalDate.of(2019, 3, 15);

        Period period = Period.between(date3, date4);
        System.out.println("date3과 date4 사이의 Period: " + period);
        System.out.println("년: " + period.getYears() + "년");
        System.out.println("월: " + period.getMonths() + "월");
        System.out.println("일: " + period.getDays() + "일");

        // Period 직접 생성
        Period tenDays2 = Period.ofDays(10);
        System.out.println("10일 Period: " + tenDays2);

        Period threeWeeks = Period.ofWeeks(3);
        System.out.println("3주 Period: " + threeWeeks);
        System.out.println("3주를 일로: " + threeWeeks.getDays() + "일");

        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println("2년 6월 1일 Period: " + twoYearsSixMonthsOneDay);
    }

    // 4. Duration vs Period 비교
    public static void compareDurationAndPeriod() {

        System.out.println("\n===== Duration vs Period 비교 =====");

        // Duration은 시간 기반 - LocalDate에 사용 불가
        try {
            LocalDate date1 = LocalDate.of(2017, 9, 11);
            LocalDate date2 = LocalDate.of(2017, 9, 21);
            Duration d = Duration.between(date1, date2); // 예외 발생
        } catch (Exception e) {
            System.out.println("Duration은 LocalDate에 사용 불가: " + e.getClass().getSimpleName());
        }

        // Period는 날짜 기반 - LocalTime에 사용 불가
        try {
            LocalTime time1 = LocalTime.of(13, 45);
            LocalTime time2 = LocalTime.of(15, 30);
            //Period p = Period.between(time1, time2); // 컴파일 에러
        } catch (Exception e) {
            System.out.println("Period는 LocalTime에 사용 불가");
        }

        System.out.println("Duration: 시간 기반 (초, 나노초)");
        System.out.println("Period: 날짜 기반 (년, 월, 일)");
    }

}