package com.foodieblog.foodieblog.controllers;

import com.foodieblog.foodieblog.dtos.LikeDto;
import com.foodieblog.foodieblog.dtos.LikePostDto;
import com.foodieblog.foodieblog.entities.Like;
import com.foodieblog.foodieblog.exceptionHandlers.LikeNotFoundException;
import com.foodieblog.foodieblog.exceptionHandlers.NotValidJsonBodyException;
import com.foodieblog.foodieblog.mapstruct.mappers.MapStructMapper;
import com.foodieblog.foodieblog.repositories.LikeRepository;
import com.foodieblog.foodieblog.services.LikeService;
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
@RequestMapping("/likes")
public class LikeController {
    private final LikeRepository likeRepository;
    private final MapStructMapper mapStructMapper;
    private final LikeService likeService;
    private final LogService logService;

    @Autowired
    public LikeController(LikeRepository likeRepository, MapStructMapper mapStructMapper, LikeService likeService, LogService logService) {
        this.likeRepository = likeRepository;
        this.mapStructMapper = mapStructMapper;
        this.likeService = likeService;
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<LikeDto>> getAll() throws LikeNotFoundException {
        List<LikeDto> likeDtos = mapStructMapper.allLikeDtos(likeRepository.findAll());
        for (int i = 0; i < likeDtos.size(); i++) {
            int id = Math.toIntExact(likeDtos.get(i).getLikeId());
            likeDtos.set(i, likeService.getByIdd(id));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(likeDtos);
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody LikePostDto likePostDto) throws NotValidJsonBodyException {
        validateJsonInput(likePostDto);
        HttpStatus status = HttpStatus.CREATED;
        LogLevel logLevel = LogLevel.INFO;

        likeService.create(likePostDto);
        logService.createLog(Like.class, logLevel);

        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(value = "id") int id) throws LikeNotFoundException {
        Like like = mapStructMapper.likeDtoToLike(likeService.getByIdd(id));
        HttpStatus status = HttpStatus.GONE;
        LogLevel logLevel = LogLevel.INFO;

        likeRepository.delete(like);
        logService.createLog(Like.class, logLevel);

        return new ResponseEntity<>(status);
    }
}
