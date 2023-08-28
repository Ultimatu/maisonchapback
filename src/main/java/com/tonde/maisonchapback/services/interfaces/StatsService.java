package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Statistique;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Hidden
public interface StatsService {

    ResponseEntity<List<Statistique>> getAllStats();

    ResponseEntity<Statistique> getStatById(Long id);

    ResponseEntity<?> createStat(Statistique statistique);

    ResponseEntity<?> updateStat(Statistique statistique);

    ResponseEntity<?> deleteStat(Long id);

    ResponseEntity<Objects> getStatByHouseId(int id);
}
