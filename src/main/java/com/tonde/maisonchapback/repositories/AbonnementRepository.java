package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.Abonnement;
import com.tonde.maisonchapback.models.workflows.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    List<Abonnement> findAllByUser(User user);
}
