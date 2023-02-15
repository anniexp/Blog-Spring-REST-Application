package com.foodieblog.foodieblog.controllers;

import com.foodieblog.foodieblog.dtos.AuthorGetAllDto;
import com.foodieblog.foodieblog.dtos.UserGetDto;
import com.foodieblog.foodieblog.dtos.UserPostDto;
import com.foodieblog.foodieblog.entities.User;
import com.foodieblog.foodieblog.exceptionHandlers.NotValidJsonBodyException;
import com.foodieblog.foodieblog.exceptionHandlers.UserAlreadyExistsException;
import com.foodieblog.foodieblog.exceptionHandlers.UserNotFoundException;
import com.foodieblog.foodieblog.mapstruct.mappers.MapStructMapper;
import com.foodieblog.foodieblog.services.LogService;
import com.foodieblog.foodieblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.foodieblog.foodieblog.validators.JsonRequestValidator.validateJsonInput;

@RestController
@RequestMapping("/users")
public class UserController {
    private final MapStructMapper mapstructMapper;
    private final UserService userService;
    private final LogService logService;

    @Autowired
    public UserController(MapStructMapper mapstructMapper, UserService userService, LogService logService) {
        this.mapstructMapper = mapstructMapper;
        this.userService = userService;
        this.logService = logService;
    }

    @PostMapping //DONE
    public ResponseEntity<Void> create(
            @Valid @RequestBody UserPostDto userPostDto) throws UserAlreadyExistsException, NotValidJsonBodyException {
        LogLevel logLevel = LogLevel.INFO;
        HttpStatus httpStatus = HttpStatus.CREATED;

        userService.createUser(userPostDto);
        logService.createLog(User.class, logLevel);

        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGetDto> update(
            @Valid @RequestBody UserPostDto userPostDto,
            @PathVariable(value = "id") int id) throws UserNotFoundException, UserAlreadyExistsException, NotValidJsonBodyException {
        LogLevel logLevel = LogLevel.INFO;
        HttpStatus httpStatus = HttpStatus.OK;

        validateJsonInput(userPostDto);
        User oldUser = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found due to invalid user id: " + id));
        userService.update(oldUser, userPostDto);
        UserGetDto body = mapstructMapper.userGetDto(oldUser);
        body.setRoleName(oldUser.getRole().getRoleName());
        logService.createLog(User.class, logLevel);

        return ResponseEntity.status(httpStatus).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getById(
            @PathVariable(value = "id") int id) throws UserNotFoundException {

        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found due to invalid user id: " + id));
        UserGetDto body = mapstructMapper.userGetDto(user);
        body.setRoleName(user.getRole().getRoleName());

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping
    public ResponseEntity<List<AuthorGetAllDto>> getAllAuthors() {
        List<AuthorGetAllDto> users = mapstructMapper.authorsToAuthorAllDtos(userService.getAllUsers());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(value = "id") int id) throws UserNotFoundException {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found due to invalid user id: " + id));
        LogLevel logLevel = LogLevel.INFO;
        HttpStatus httpStatus = HttpStatus.GONE;

        userService.delete(user);
        logService.createLog(User.class, logLevel);

        return new ResponseEntity<>(httpStatus);
    }
}
