package com.kpi.parking.exception;

public class PhoneNotUniqueException extends RuntimeException{

    public PhoneNotUniqueException(String errorMessage) {
        super(errorMessage);
    }
}
