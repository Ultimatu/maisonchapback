package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Abonnement;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface AbonnementService {

    public List<Abonnement> getAllAbonnements();

    public Abonnement getAbonnementById(int id);

    public ResponseEntity<?> addAbonnement(Abonnement abonnement);

    public ResponseEntity<?> updateAbonnement(Abonnement abonnement);

    public ResponseEntity<?> deleteAbonnement(int id);

    public List<Abonnement> getAllAbonnementsByUserId(int userId);
}
