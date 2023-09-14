package com.tonde.maisonchapback.services.implementation;


import com.tonde.maisonchapback.models.workflows.Statistique;
import com.tonde.maisonchapback.services.interfaces.StatsService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Hidden
public class StatServiceImpl implements StatsService {
    @Override
    public ResponseEntity<List<Statistique>> getAllStats() {
        return null;
    }

    @Override
    public ResponseEntity<Statistique> getStatById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> createStat(Statistique statistique) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateStat(Statistique statistique) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteStat(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Objects> getStatByHouseId(int id) {
        return null;
    }
}
