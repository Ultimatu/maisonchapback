package com.tonde.maisonchapback.services.implementation;


import com.tonde.maisonchapback.models.workflows.Abonnement;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.repositories.AbonnementRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.AbonnementService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class AbonnementServiceImpl implements AbonnementService {

    private final AbonnementRepository repository;
    private final UserRepository userRepository;
    @Override
    public List<Abonnement> getAllAbonnements() {

        return repository.findAll();
    }

    @Override
    public Abonnement getAbonnementById(int id) {
        Optional<Abonnement> abonnement = repository.findById(id);
        return abonnement.orElse(null);
    }

    @Override
    public void addAbonnement(Abonnement abonnement) {
        repository.save(abonnement);

        ResponseEntity.ok().body("Abonnement activer");

    }

    @Override
    public ResponseEntity<String> updateAbonnement(Abonnement abonnement) {
        Optional<Abonnement> abonnement1 = repository.findById(abonnement.getId());
        if(abonnement1.isPresent()){
            if (Objects.equals(abonnement.getEtat(), "actif") && Objects.equals(abonnement1.get().getEtat(), "actif") && Objects.equals(abonnement1.get().getType(), abonnement.getType())){
                return ResponseEntity.badRequest().body("Abonnement déjà actif");
            }
            else if (Objects.equals(abonnement.getEtat(), "inactif") && Objects.equals(abonnement1.get().getEtat(), "inactif") && Objects.equals(abonnement1.get().getType(), abonnement.getType())){
                return ResponseEntity.badRequest().body("Abonnement déjà inactif");
            }
            else{
                abonnement1.get().setEtat(abonnement.getEtat());
                abonnement1.get().setType(abonnement.getType());
                abonnement1.get().setDateCreation(abonnement.getDateCreation());
                abonnement1.get().setDateDebut(abonnement.getDateDebut());
                abonnement1.get().setDateFin(abonnement.getDateFin());
                abonnement1.get().setUser(abonnement.getUser());
                abonnement1.get().setTypeAbonnement(abonnement.getTypeAbonnement());
                repository.save(abonnement1.get());
                return ResponseEntity.ok("Abonnement modifié avec succès");
            }

        }

        return ResponseEntity.badRequest().body("Abonnement non trouvé");

    }

    @Override
    public ResponseEntity<String> deleteAbonnement(int id) {
        Optional<Abonnement> abonnement = repository.findById(id);
        if(abonnement.isPresent()){
            repository.delete(abonnement.get());
            return ResponseEntity.ok("Abonnement supprimé avec succès");
        }
        return ResponseEntity.badRequest().body("Abonnement non trouvé");

    }

    @Override
    public List<Abonnement> getAllAbonnementsByUserId(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(repository::findAllByUser).orElse(null);
    }
}
