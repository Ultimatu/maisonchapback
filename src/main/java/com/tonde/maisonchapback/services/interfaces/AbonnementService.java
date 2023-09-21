package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.domains.Abonnement;
import com.tonde.maisonchapback.services.dto.AbonnementDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface AbonnementService {

    List<AbonnementDTO> getAllAbonnements();

    Abonnement getAbonnementById(int id);

    void addAbonnement(Abonnement abonnement);

    ResponseEntity<String> updateAbonnement(Abonnement abonnement);

    ResponseEntity<String> deleteAbonnement(int id);

    List<Abonnement> getAllAbonnementsByUserId(int userId);
}
