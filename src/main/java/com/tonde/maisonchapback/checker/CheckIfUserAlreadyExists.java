package com.tonde.maisonchapback.checker;

import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.auths.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckIfUserAlreadyExists {


    private final UserRepository repository;

    public boolean alreadyExist(RegisterRequest request){
        System.out.println("checking if exists");
        return repository.existsByEmailOrPhone(request.getEmail(), request.getPhone());
    }
}
