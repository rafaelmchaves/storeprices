package com.store.infrastructure.output.exceptions;

import com.store.core.exceptions.NotEnoughDataException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = { NotEnoughDataException.class, ExceptionHandler.class })
public class CustomExceptionHandler implements ExceptionHandler<NotEnoughDataException, HttpResponse<ErrorMessage>> {

    @Override
    public HttpResponse<ErrorMessage> handle(HttpRequest request, NotEnoughDataException exception) {

        final var errorMessage = ErrorMessage.builder().message(exception.getMessage()).errorCode(exception.getErrorCode()).build();
        return HttpResponse.badRequest().body(errorMessage);
    }

}
