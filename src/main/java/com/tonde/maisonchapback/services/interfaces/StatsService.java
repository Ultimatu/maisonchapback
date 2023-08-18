package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Statistique;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public interface StatsService {

    public ResponseEntity<List<Statistique>> getAllStats();

    public ResponseEntity<Statistique> getStatById(Long id);

    public ResponseEntity<?> createStat(Statistique statistique);

    public ResponseEntity<?> updateStat(Statistique statistique);

    public ResponseEntity<?> deleteStat(Long id);

    public ResponseEntity<Objects> getStatByHouseId(int id);
}