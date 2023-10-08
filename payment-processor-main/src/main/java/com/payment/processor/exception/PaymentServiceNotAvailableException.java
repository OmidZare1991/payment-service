package com.payment.processor.exception;

public class PaymentServiceNotAvailableException extends RuntimeException {

    public PaymentServiceNotAvailableException(String message) {
        super(message);
    }
}