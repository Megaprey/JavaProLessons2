package ru.irazzhivin.payment.exception;

public class PayException extends RuntimeException {
    public PayException(String message) {
        super(message);
    }
}
