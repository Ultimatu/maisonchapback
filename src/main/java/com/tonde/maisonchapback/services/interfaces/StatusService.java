package com.tonde.maisonchapback.services.interfaces;


import com.tonde.maisonchapback.models.workflows.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StatusService {


    public ResponseEntity<?> getAllStatus();

    public ResponseEntity<?> getStatusById(Integer id);

    public ResponseEntity<?> createStatus(Status status);

    public ResponseEntity<?> updateStatus(Status status);

    public ResponseEntity<?> deleteStatus(Integer id);
}
