package com.tonde.maisonchapback.services.interfaces;


import com.tonde.maisonchapback.domains.Status;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Hidden
public interface StatusService {


    ResponseEntity<?> getAllStatus();

    ResponseEntity<?> getStatusById(Integer id);

    ResponseEntity<?> createStatus(Status status);

    ResponseEntity<?> updateStatus(Status status);

    ResponseEntity<?> deleteStatus(Integer id);
}
