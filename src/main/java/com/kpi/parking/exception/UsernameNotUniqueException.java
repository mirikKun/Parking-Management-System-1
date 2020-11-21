package com.kpi.parking.exception;

public class UsernameNotUniqueException extends RuntimeException {

    public UsernameNotUniqueException(String errorMessage) {
        super(errorMessage);
    }
}

