package com.foodieblog.foodieblog.exceptionHandlers;

public class NotValidJsonBodyException extends Exception{
    public NotValidJsonBodyException(String message) {
            super(message);
    }
}

