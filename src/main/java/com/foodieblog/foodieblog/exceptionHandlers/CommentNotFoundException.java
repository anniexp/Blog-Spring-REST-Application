package com.foodieblog.foodieblog.exceptionHandlers;

public class CommentNotFoundException extends Exception{
    public CommentNotFoundException(String message) {
        super(message);
    }
}
