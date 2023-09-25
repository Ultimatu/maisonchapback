package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.domains.Abonnement;
import com.tonde.maisonchapback.domains.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Hidden
public interface UserService {

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserById(int id);

    ResponseEntity<?> updateUser(int id, User user);

    ResponseEntity<?> updatePhoto(int id, MultipartFile file);
    void deleteUser(int id);

    User getconnectedUser( String email);

    ResponseEntity<?> upgradeUserToStandard(int id, Abonnement abonnement);

    ResponseEntity<?> upgradeUserToPremium(int id, Abonnement abonnement);

    ResponseEntity<?> upgradeUserToFree(int id, Abonnement abonnement);

    ResponseEntity<?> updateProprioToStandard(int id, Abonnement abonnement);

    ResponseEntity<?> updateProprioToPremium(int id, Abonnement abonnement);

    ResponseEntity<?> updateProprioToFree(int id, Abonnement abonnement);

}
