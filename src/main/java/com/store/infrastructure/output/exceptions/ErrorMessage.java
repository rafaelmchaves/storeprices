package com.store.infrastructure.output.exceptions;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;

@Serdeable.Serializable
@Introspected
@Builder
@Getter
public class ErrorMessage {

    private String message;
    private String errorCode;

}
