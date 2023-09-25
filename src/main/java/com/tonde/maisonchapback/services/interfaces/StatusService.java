package com.tonde.maisonchapback.services.interfaces;


import com.tonde.maisonchapback.domains.Status;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface StatusService {


    List<Status> getAllStatus();

    Status getStatusById(Integer id);

    ResponseEntity<Status> createStatus(Status status);

    ResponseEntity<Status> updateStatus(Status status);

    void deleteStatus(Integer id);
}
