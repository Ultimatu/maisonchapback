package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.domains.AccountActivation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountActivationRepository extends JpaRepository<AccountActivation, Integer> {

    Optional<AccountActivation> findByUserIdAndKey(Integer userId, String key);
}
