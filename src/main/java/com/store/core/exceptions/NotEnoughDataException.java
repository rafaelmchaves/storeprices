package com.store.core.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class NotEnoughDataException extends RuntimeException {

    private String errorCode;

    public NotEnoughDataException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        log.error(message);
    }

}
