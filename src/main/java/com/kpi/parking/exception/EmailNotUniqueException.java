package com.kpi.parking.exception;

public class EmailNotUniqueException extends RuntimeException{

    public EmailNotUniqueException(String errorMessage) {
        super(errorMessage);
    }
}
