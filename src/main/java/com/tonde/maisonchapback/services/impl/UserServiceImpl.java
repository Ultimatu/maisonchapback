package com.tonde.maisonchapback.services.impl;


import com.tonde.maisonchapback.domains.Abonnement;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.domains.enums.Role;
import com.tonde.maisonchapback.exceptions.BadCredentialsException;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.AbonnementService;
import com.tonde.maisonchapback.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AbonnementService abonnementService;

    @Override
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElse(null);
    }

    @Override
    public User getconnectedUser(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);

    }

    @Override
    public ResponseEntity<?> updateUser(int id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setEmail(user.getEmail());
            user1.setPhotoPath(user.getPhotoPath());
            user1.setAdresse(user.getAdresse());
            user1.setPhone(user.getPhone());
            user1.setLangkey(user.getLangkey());
            userRepository.save(user1);
            return ResponseEntity.ok("User updated successfully");
        }
        return null;
    }

    @Override
    public ResponseEntity<?> updatePhoto(int id, MultipartFile photoFile) {

        try {
            Optional<User> userOptional = userRepository.findById(id);

            if (userOptional.isPresent()) {
                // Récupérer les données de la photo à partir du MultipartFile
                byte[] photoData = photoFile.getBytes();

                // Générer un chemin de fichier unique pour chaque photo
                String fileName = photoFile.getOriginalFilename();
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

                Path filePath = Paths.get("src/main/resources/photos/" + uniqueFileName);


                // Sauvegarder les données de la photo dans le dossier de ressources
                Files.write(filePath, photoData);

                //supprimer l'ancienne photo
                if (userOptional.get().getPhotoPath() != null) {
                    Files.delete(Paths.get("src/main/resources/photos/" + userOptional.get().getPhotoPath()));

                }
                userOptional.get().setPhotoPath(uniqueFileName);
                userRepository.save(userOptional.get());
                return ResponseEntity.ok("Photo modifiée avec succès");
            } else {
                return ResponseEntity.badRequest().body("Photo non trouvée");
            }


        } catch (IOException e) {
            throw new BadCredentialsException("Impossible de modifier la photo");
        }


    }

    @Override
    public void deleteUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);

        }
    }

    @Override
    public ResponseEntity<?> upgradeUserToStandard(int id, Abonnement abonnement) {
        return updateUserRoleAndAddAbonnement(id, Role.STANDARD_USER, abonnement);
    }

    @Override
    public ResponseEntity<?> upgradeUserToPremium(int id, Abonnement abonnement) {
        return updateUserRoleAndAddAbonnement(id, Role.PREMIUM_USER, abonnement);
    }


    @Override
    public ResponseEntity<?> upgradeUserToFree(int id, Abonnement abonnement) {
        return updateUserRoleAndAddAbonnement(id, Role.FREE_USER, abonnement);
    }

    @Override
    public ResponseEntity<?> updateProprioToStandard(int id, Abonnement abonnement) {
        return updateUserRoleAndAddAbonnement(id, Role.STANDARD_PROPRIO, abonnement);
    }

    @Override
    public ResponseEntity<?> updateProprioToPremium(int id, Abonnement abonnement) {
        return updateUserRoleAndAddAbonnement(id, Role.PREMIUM_PROPRIO, abonnement);
    }

    @Override
    public ResponseEntity<?> updateProprioToFree(int id, Abonnement abonnement) {
        return updateUserRoleAndAddAbonnement(id, Role.FREE_PROPRIO, abonnement);
    }


    private ResponseEntity<?> updateUserRoleAndAddAbonnement(int id, Role newRole, Abonnement abonnement) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(newRole);
            userRepository.save(user);

            abonnementService.addAbonnement(abonnement);

            String successMessage = String.format("User upgraded to %s successfully", newRole);
            return ResponseEntity.ok(successMessage);
        }
        return ResponseEntity.badRequest().body("User not found");
    }

}
