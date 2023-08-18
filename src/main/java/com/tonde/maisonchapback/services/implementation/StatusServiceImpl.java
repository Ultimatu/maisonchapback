package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.Status;
import com.tonde.maisonchapback.repositories.StatusRepository;
import com.tonde.maisonchapback.services.interfaces.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public ResponseEntity<?> getAllStatus(){
        return ResponseEntity.ok(statusRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getStatusById(Integer id) {
        Optional<Status> status = statusRepository.findById(id);
        if(status.isPresent()){
            return ResponseEntity.ok(status.get());
        }
        return ResponseEntity.badRequest().body("Status not found");
    }

    @Override
    public ResponseEntity<?> createStatus(Status status) {
        Optional<Status> statusOptional = statusRepository
                .findByStatusAndDescription(status.getStatus(), status.getDescription());

        if(statusOptional.isPresent()){
            return ResponseEntity.badRequest().body("Status already exist");
        }
        statusRepository.save(status);
        return ResponseEntity.ok("Status created successfully");

    }

    @Override
    public ResponseEntity<?> updateStatus(Status status) {
        Optional<Status> statusOptional = statusRepository.findById(status.getId());
        if(statusOptional.isPresent()){
            statusOptional.get().setStatus(status.getStatus());
            statusOptional.get().setDescription(status.getDescription());
            statusOptional.get().setUpdatedAt(LocalDateTime.now());
            statusRepository.save(statusOptional.get());
            return ResponseEntity.ok("Status updated successfully");
        }

        return ResponseEntity.badRequest().body("Status not found");
    }

    @Override
    public ResponseEntity<?> deleteStatus(Integer id) {
        Optional<Status> statusOptional = statusRepository.findById(id);
        if(statusOptional.isPresent()){
            statusRepository.delete(statusOptional.get());
            return ResponseEntity.ok("Status deleted successfully");
        }
        return ResponseEntity.badRequest().body("Status not found");
    }
}