package com.study.ch12.quiz;

import java.time.temporal.Temporal;

@FunctionalInterface
public interface TemporalAdjuster {
    Temporal adjustInto(Temporal temporal);
}
