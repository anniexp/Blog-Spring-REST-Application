package com.foodieblog.foodieblog.exceptionHandlers;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
