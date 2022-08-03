package com.example.demo.stream.application;

import com.example.demo.stream.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 정원식 (wonsik.cheung)
 */
@Slf4j
@Service
public class LogService {

    public void logUser(User user) {
        log.info("User: {}", user);
    }
}
