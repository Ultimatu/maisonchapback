package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Abonnement;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface AbonnementService {

    public List<Abonnement> getAllAbonnements();

    public Abonnement getAbonnementById(int id);

    public void addAbonnement(Abonnement abonnement);

    public ResponseEntity<?> updateAbonnement(Abonnement abonnement);

    public ResponseEntity<?> deleteAbonnement(int id);

    public List<Abonnement> getAllAbonnementsByUserId(int userId);
}
