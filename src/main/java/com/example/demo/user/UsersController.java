package com.example.demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    private static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<User> list(Pageable page) {
        return userRepository.findAll(page);
    }

    @PostMapping
    public Optional<User> create(@RequestBody User user, HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_CREATED);

            return Optional.of(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            logger.warn("Failed to create new user due to a constraint violation.", e);
        }

        response.setStatus(HttpServletResponse.SC_CONFLICT);
        return Optional.empty();
    }

    @PostMapping("/authenticate")
    public boolean authenticate(@RequestBody UserAuthenticateRequest userAuthenticateRequest) {
        return userRepository.existsByUsernameAndPassword(userAuthenticateRequest.getUsername(), userAuthenticateRequest.getPassword());
    }
}
