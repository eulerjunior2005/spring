package com.estudo.spring.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{
    public static final String MESSAGE_KEY = "000001";
    @Getter
    private final String notFoundValue;
    @Getter
    private final String messageKey;
    @Getter
    private final HttpStatus httpStatus;

    public NotFoundException(String notFoundId) {
        super(MESSAGE_KEY);
        notFoundValue = notFoundId;
        httpStatus = HttpStatus.NOT_FOUND;
        messageKey = MESSAGE_KEY;
    }
}
