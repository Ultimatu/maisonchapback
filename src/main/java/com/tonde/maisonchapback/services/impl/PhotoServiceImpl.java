package com.tonde.maisonchapback.services.impl;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Photo;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.exceptions.ApiError;
import com.tonde.maisonchapback.exceptions.CustomLogger;
import com.tonde.maisonchapback.exceptions.CustomRestControllerHandler;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.PhotoRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.PhotoService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    @Value("${file.upload-dir}")
    private String uploadPath;


    @Override
    public ResponseEntity<List<String>> addPhotos(List<MultipartFile> photoFiles, int houseId) throws IOException {
        House house = houseRepository.findById(houseId).orElse(null);
        if (house == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<String> successfulPhotoUrls = new ArrayList<>();
        List<String> failedPhotos = new ArrayList<>();
        try {
            for (MultipartFile photoFile : photoFiles) {
                byte[] photoData = photoFile.getBytes();
                String fileName = photoFile.getOriginalFilename();

                String uniqueFileName = savePhotoAndGetUniqueFileName(photoData, fileName, house.getId());
                if (uniqueFileName != null) {

                    CustomLogger.log("INFO", "Photo sauvegarder: " + uniqueFileName);
                    successfulPhotoUrls.add(uniqueFileName);
                } else {
                    failedPhotos.add("Ce fichier n'est pas sauvegardé: " + fileName);
                    CustomLogger.log("ERROR", "Ce fichier n'est pas sauvegardé: " + fileName);
                }
            }

            if (!failedPhotos.isEmpty()) {
                CustomLogger.log("ERROR", "Failed photos: " + failedPhotos);
                return ResponseEntity.badRequest().body(failedPhotos);
            }

            return ResponseEntity.ok(successfulPhotoUrls);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
    private String savePhotoAndGetUniqueFileName(byte[] photoData, String fileName, int id) {
        House house = houseRepository.findById(id).orElse(null);
        assert house != null;

        try {
            String filePath = Paths.get(uploadPath).toString();
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            String fileExtension = FilenameUtils.getExtension(fileName);
            String newFileName = System.currentTimeMillis() + "." + fileExtension;

            Path targetLocation = Paths.get(path.toString(), newFileName);


            Files.write(targetLocation, photoData);
            CustomLogger.log("INFO", "Photo saving 22: " + newFileName);
            String description = "Photo de la maison " + house.getTitle();

            photoRepository.saveAll(newFileName, description, house.getId());
            return newFileName;


        } catch (Exception e) {

            CustomLogger.log("ERROR", "Erreur lors de la sauvegarde de la photo: " + e);
            return null; // Retournez null pour indiquer une erreur
        }
    }



    @Override
    public ResponseEntity<?> updatePhoto(Photo photo, MultipartFile photoFile) throws RuntimeException, ApiError {

        try {
            Optional<Photo> photoOptional = photoRepository.findById(photo.getId());

            if (photoOptional.isPresent()) {
                // Récupérer les données de la photo à partir du MultipartFile
                byte[] photoData = photoFile.getBytes();

                // Générer un chemin de fichier unique pour chaque photo
                String fileName = photoFile.getOriginalFilename();
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                Path filePath = Paths.get("src/main/resources/static/photos/%s".formatted(uniqueFileName));


                // Sauvegarder les données de la photo dans le dossier de ressources
                Files.write(filePath, photoData);

                //supprimer l'ancienne photo
                Files.delete(Paths.get("src/main/resources/static/photos/" + photoOptional.get().getUrl()));

                //mettre à jour la photo
                photoOptional.get().setUrl(uniqueFileName);
                photoRepository.save(photoOptional.get());
                return ResponseEntity.ok("Photo modifiée avec succès");
            } else {
                return ResponseEntity.badRequest().body("Photo non trouvée");
            }


        } catch (IOException e) {
            throw new CustomRestControllerHandler().handleException(e);
        }


    }

    @Override
    public ResponseEntity<String> deletePhoto(int id) {
        try {
            Optional<Photo> photoOptional = photoRepository.findById(id);
            if (photoOptional.isPresent()) {
                //supprimer la photo
                Files.delete(Paths.get("src/main/resources/static/photos/" + photoOptional.get().getUrl()));
                photoRepository.delete(photoOptional.get());
                return ResponseEntity.ok("Photo supprimée avec succès");
            } else {
                return ResponseEntity.badRequest().body("Photo non trouvée");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression de la photo");
        }

    }

    @Override
    public Photo getPhotoById(int id) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        return photoOptional.orElse(null);
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    public List<Photo> getAllPhotosByHouseId(int houseId) {
        Optional<House> houseOptional = houseRepository.findById(houseId);
        return houseOptional.map(photoRepository::findAllByHouse).orElse(null);
    }

    @Override
    public ResponseEntity<?> getAllByHouseUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(photoRepository.findAllByHouseUser(userOptional.get()));
        } else {
            return ResponseEntity.badRequest().body("Utilisateur non trouvé");
        }
    }


}
