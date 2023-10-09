package com.store.core.activities;

import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.time.Month;

@Singleton
public class GetMonthEndDateActivity {

    public LocalDate execute(LocalDate date) {
        return date.withDayOfMonth(date.getMonth().equals(Month.FEBRUARY) ? 28 : 30);
    }
}
