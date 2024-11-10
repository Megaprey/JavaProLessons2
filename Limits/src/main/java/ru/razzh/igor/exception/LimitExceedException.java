package ru.razzh.igor.exception;

public class LimitExceedException extends RuntimeException{
    public LimitExceedException(String message){
        super(message);
    }
}
