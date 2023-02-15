package com.foodieblog.foodieblog.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
    @ExceptionHandler(NotValidJsonBodyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage notValidJsonBodyException(NotValidJsonBodyException ex, WebRequest request) {
        return new ErrorMessage( ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage userAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        return new ErrorMessage( ex.getMessage(),request.getDescription(false));
    }

    @ExceptionHandler({PostNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage postNotFoundException(PostNotFoundException ex, WebRequest request) {
        return new ErrorMessage( ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage roleNotFoundException(RoleNotFoundException ex, WebRequest request) {
        return new ErrorMessage(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage userNotFoundException(UserNotFoundException ex, WebRequest request) {
        return new ErrorMessage( ex.getMessage(),request.getDescription(false));
    }

    @ExceptionHandler(LikeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage likeNotFoundException(LikeNotFoundException ex, WebRequest request) {
        return new ErrorMessage( ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage commentNotFoundException(CommentNotFoundException ex, WebRequest request) {
        return new ErrorMessage( ex.getMessage(),request.getDescription(false));
    }
}
