package com.tonde.maisonchapback.services.impl;

import com.tonde.maisonchapback.domains.Status;
import com.tonde.maisonchapback.repositories.StatusRepository;
import com.tonde.maisonchapback.services.interfaces.StatusService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public Status getStatusById(Integer id) {
        Optional<Status> status = statusRepository.findById(id);
        return status.orElse(null);

    }

    @Override
    public ResponseEntity<Status> createStatus(Status status) {
        Optional<Status> statusOptional = statusRepository
                .findByStatusAndDescription(status.getStatus(), status.getDescription());

        if (statusOptional.isPresent()) {
            return null;
        }

        return ResponseEntity.ok(statusRepository.save(status));

    }

    @Override
    public ResponseEntity<Status> updateStatus(Status status) {
        Optional<Status> statusOptional = statusRepository.findById(status.getId());
        if (statusOptional.isPresent()) {
            statusOptional.get().setStatus(status.getStatus());
            statusOptional.get().setDescription(status.getDescription());
            statusOptional.get().setUpdatedAt(Instant.now());

            return ResponseEntity.ok(statusRepository.save(statusOptional.get()));
        }

        return null;
    }

    @Override
    public void deleteStatus(Integer id) {
        Optional<Status> statusOptional = statusRepository.findById(id);
        statusOptional.ifPresent(statusRepository::delete);
    }
}
