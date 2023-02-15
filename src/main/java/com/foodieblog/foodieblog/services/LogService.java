package com.foodieblog.foodieblog.services;

import com.foodieblog.foodieblog.entities.Log;
import com.foodieblog.foodieblog.entities.User;
import com.foodieblog.foodieblog.repositories.LogRepository;
import com.foodieblog.foodieblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final LogRepository logRepository;
    private final UserRepository userRepository;

    @Autowired
    public LogService(LogRepository logRepository, UserRepository userRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
    }

    public <T> void createLog(Class<T> cl, LogLevel logLevel) {
        Log log = new Log();
        User user = userRepository.findById(1L).orElse(null);
        String action = new Throwable().getStackTrace()[1].getMethodName();
        String entityName = cl.getSimpleName();

        log.setAction(action);
        log.setLevel(String.valueOf(logLevel));
        log.setEntity(entityName);
        assert user != null;
        log.setUserId(Math.toIntExact(user.getUserId()));
        logRepository.save(log);
    }
}
