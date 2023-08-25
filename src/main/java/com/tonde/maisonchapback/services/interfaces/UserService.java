package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Abonnement;
import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Hidden
public interface UserService {

    public ResponseEntity<List<User>> getAllUsers();

    public ResponseEntity<?> getUserById(int id);

    public ResponseEntity<?> updateUser(int id, User user);

    public ResponseEntity<?> updatePhoto(int id, MultipartFile file);

    public ResponseEntity<?> deleteUser(int id);

    public ResponseEntity<?> upgradeUserToStandard(int id, Abonnement abonnement);

    public ResponseEntity<?> upgradeUserToPremium(int id , Abonnement abonnement);

    public ResponseEntity<?> upgradeUserToFree(int id , Abonnement abonnement);

    public ResponseEntity<?> updateProprioToStandard(int id , Abonnement abonnement);

    public ResponseEntity<?> updateProprioToPremium(int id , Abonnement abonnement);

    public ResponseEntity<?> updateProprioToFree(int id , Abonnement abonnement);

}
