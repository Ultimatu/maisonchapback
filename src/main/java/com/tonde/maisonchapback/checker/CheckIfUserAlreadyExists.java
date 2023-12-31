package com.tonde.maisonchapback.checker;

import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.requests.RegisterRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Schema(
        name = "CheckIfUserAlreadyExists",
        description = "Check if user already exists",
        oneOf = CheckIfUserAlreadyExists.class,
        example = """
                {
                  "alreadyExist": "boolean"
                }""",
        implementation = CheckIfUserAlreadyExists.class
)
public class CheckIfUserAlreadyExists {

    private static final Logger logger = LoggerFactory.getLogger(CheckIfUserAlreadyExists.class);
    private final UserRepository repository;

    public boolean alreadyExist(RegisterRequest request) {
        logger.info("Checking if user exists By email and phone...");

        return repository.existsByEmailOrPhone(request.getEmail(), request.getPhone());
    }

    public boolean alreadyExist(Integer id) {
        logger.info("Checking if user exists by id...");
        return repository.existsById(id);
    }

    public boolean alreadyExist(String email) {
        logger.info("Checking if user exists by email...");
        return repository.existsByEmail(email);
    }
}
