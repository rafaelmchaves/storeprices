package com.store.core.activities;

import jakarta.inject.Singleton;

import java.time.LocalDate;

@Singleton
public class GetMonthStartDateActivity {

    public LocalDate execute(LocalDate date) {
        return date.withDayOfMonth(1);
    }
}
