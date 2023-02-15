package com.foodieblog.foodieblog.controllers;

import com.foodieblog.foodieblog.dtos.*;
import com.foodieblog.foodieblog.entities.Comment;
import com.foodieblog.foodieblog.exceptionHandlers.CommentNotFoundException;
import com.foodieblog.foodieblog.exceptionHandlers.NotValidJsonBodyException;
import com.foodieblog.foodieblog.services.CommentService;
import com.foodieblog.foodieblog.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.foodieblog.foodieblog.validators.JsonRequestValidator.validateJsonInput;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final LogService logService;

    @Autowired
    public CommentController(CommentService commentService, LogService logService) {
        this.commentService = commentService;
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<CommentGetAllDto>> getAllComments() throws CommentNotFoundException {
        List<CommentGetAllDto> commentList = commentService.getAllComments();

        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CommentPostDto commentPostDto) throws NotValidJsonBodyException {
        LogLevel logLevel = LogLevel.INFO;
        HttpStatus httpStatus = HttpStatus.CREATED;

        Comment newComment = commentService.setComment(commentPostDto);
        commentService.save(newComment);
        logService.createLog(Comment.class, logLevel);

        return new ResponseEntity<>(httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentGetAllDto> getById(
            @PathVariable(value = "id") int id) throws CommentNotFoundException {

        CommentGetAllDto commentGetAllDto = commentService.getCommentGetAllDtoById(id);

        return ResponseEntity.status(HttpStatus.OK).body(commentGetAllDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentGetAllDto> update(
          @Valid @RequestBody CommentSlimDto commentSlimDto,
          @PathVariable(value = "id") int id) throws CommentNotFoundException, NotValidJsonBodyException {
        LogLevel logLevel = LogLevel.INFO;
        HttpStatus httpStatus = HttpStatus.OK;

        CommentGetAllDto commentGetAllDto = commentService.updateComment(id, commentSlimDto);
        validateJsonInput(commentSlimDto);
        logService.createLog(Comment.class, logLevel);

        return ResponseEntity.status(httpStatus).body(commentGetAllDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(value = "id") int id) throws CommentNotFoundException {
        Comment comment = commentService.findById(id);
        LogLevel logLevel = LogLevel.INFO;
        HttpStatus httpStatus = HttpStatus.GONE;

        commentService.delete(comment);
        logService.createLog(comment.getClass(), logLevel);

        return new ResponseEntity<>(httpStatus);
    }
}
