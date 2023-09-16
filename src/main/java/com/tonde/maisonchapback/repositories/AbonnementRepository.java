package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.domains.Abonnement;
import com.tonde.maisonchapback.domains.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Hidden
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    List<Abonnement> findAllByUser(User user);
}
