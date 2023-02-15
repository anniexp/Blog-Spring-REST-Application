package com.foodieblog.foodieblog.exceptionHandlers;

public class PostNotFoundException extends Exception{
    public PostNotFoundException(String message) {
        super(message);
    }
}
