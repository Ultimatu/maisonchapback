package com.tonde.maisonchapback.checker;

import com.tonde.maisonchapback.requests.RegisterRequest;
import com.tonde.maisonchapback.repositories.UserRepository;
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
        logger.info("Checking if user exists...");

        return repository.existsByEmailOrPhone(request.getEmail(), request.getPhone());
    }
}
