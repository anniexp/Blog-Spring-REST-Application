package com.foodieblog.foodieblog.controllers;

import com.foodieblog.foodieblog.dtos.*;
import com.foodieblog.foodieblog.entities.Post;
import com.foodieblog.foodieblog.exceptionHandlers.NotValidJsonBodyException;
import com.foodieblog.foodieblog.exceptionHandlers.PostNotFoundException;
import com.foodieblog.foodieblog.services.LogService;
import com.foodieblog.foodieblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final LogService logService;
    @Autowired
    public PostController(PostService postService, LogService logService) {
        this.postService = postService;
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<PostGetDto>> getAllPosts() throws PostNotFoundException {
        List<PostGetDto> postGetDtos = postService.getAllDtos();

        return new ResponseEntity<>(postGetDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(
           @Valid @RequestBody PostPostDto postPostDto) throws NotValidJsonBodyException {
        LogLevel logLevel = LogLevel.INFO;
        HttpStatus httpStatus = HttpStatus.CREATED;

        postService.create(postPostDto);
        logService.createLog(Post.class, logLevel);

        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostGetDto> update(
            @Valid @RequestBody PostPostDto post,
            @PathVariable(value = "id") int id) throws NotValidJsonBodyException {
        LogLevel logLevel = LogLevel.INFO;
        HttpStatus httpStatus = HttpStatus.OK;
        long userId = post.getAuthorId();
        PostGetDto body = postService.updatePost(id, post, userId);
        logService.createLog(Post.class, logLevel);

        return ResponseEntity.status(httpStatus).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostGetDto> getById(
            @PathVariable(value = "id") int id) throws PostNotFoundException {
        HttpStatus httpStatus = HttpStatus.OK;
        PostGetDto body = postService.setPostGetDto(id);

        return new ResponseEntity<>(body, httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostGetDto> delete(
            @PathVariable(value = "id") int id) throws PostNotFoundException {
        HttpStatus status = HttpStatus.GONE;
        LogLevel logLevel = LogLevel.INFO;

        PostGetDto postGetDto = postService.getByIdd(id);
        postService.delete(postGetDto);
        logService.createLog(Post.class, logLevel);

        return new ResponseEntity<>(status);
    }
}
