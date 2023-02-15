package com.foodieblog.foodieblog.services;

import com.foodieblog.foodieblog.dtos.AuthorGetAllDto;
import com.foodieblog.foodieblog.dtos.UserPostDto;
import com.foodieblog.foodieblog.entities.Role;
import com.foodieblog.foodieblog.entities.User;
import com.foodieblog.foodieblog.exceptionHandlers.NotValidJsonBodyException;
import com.foodieblog.foodieblog.exceptionHandlers.RoleNotFoundException;
import com.foodieblog.foodieblog.exceptionHandlers.UserAlreadyExistsException;
import com.foodieblog.foodieblog.mapstruct.mappers.MapStructMapper;
import com.foodieblog.foodieblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.foodieblog.foodieblog.validators.JsonRequestValidator.validateJsonInput;

@Service
public class UserService {
    private final MapStructMapper mapStructMapper;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Autowired
    public UserService(MapStructMapper mapStructMapper, RoleService roleService, UserRepository userRepository) {
        this.mapStructMapper = mapStructMapper;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }
    public void createUser(UserPostDto userPostDto) throws NotValidJsonBodyException, UserAlreadyExistsException {
        User user = setUser(userPostDto);
        validateJsonInput(user);
        checkIfUserExists(user);
        save(user);
    }

    public User setUser(UserPostDto userPostDto) throws NotValidJsonBodyException {
        User user = mapStructMapper.userPostDtoToUser(userPostDto);
        String roleName = userPostDto.getRoleName();

        try {
            Role role = roleService.findByRoleName(roleName);
            user.setRole(role);
        } catch (RoleNotFoundException e) {
            throw new NotValidJsonBodyException("Role not valid");
        }

        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public void update(User user, UserPostDto userPostDto) throws UserAlreadyExistsException, NotValidJsonBodyException {
        User newUser = setUser(userPostDto);

        if (!user.getUsername().equals(newUser.getUsername())) {
            checkIfUserExists(newUser);
        }

        user.setPassword(newUser.getPassword());
        user.setUsername(newUser.getUsername());
        user.setEnabled(newUser.isEnabled());
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void checkIfUserExists(User user) throws UserAlreadyExistsException {
        List<AuthorGetAllDto> userList = mapStructMapper.authorsToAuthorAllDtos(userRepository.findAll());

        for (AuthorGetAllDto authorGetAllDto : userList) {
            if (user.getUsername().equals(authorGetAllDto.getUsername())) {
                throw new UserAlreadyExistsException("User with this username already exists!");
            }
        }
    }
}
