package com.tonde.maisonchapback.checker;

import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.auths.requests.RegisterRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
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


    private final UserRepository repository;

    public boolean alreadyExist(RegisterRequest request){
        System.out.println("checking if exists");
        return repository.existsByEmailOrPhone(request.getEmail(), request.getPhone());
    }
}
