package com.store.infrastructure.output.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {

    COD01("List of prices during the period is empty or it's single"),
    COD02("It's necessary more than one month of data to calculate the inflation"),
    COD03("The given month has not enough data for this product type"),
    COD04("The given month has not enough data for this store id");


    private final String errorDescription;

    ErrorCode(String errorDescription) {
        this.errorDescription = errorDescription;
    }


}
