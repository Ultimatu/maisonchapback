package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Photo;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.PhotoRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    @Override

    public ResponseEntity<?> addPhotos(List<MultipartFile> photoFiles, Photo photo) throws IOException {

       try {
           boolean finish = false;
           int count = 0;
           for (MultipartFile photoFile : photoFiles) {
               // Récupérer les données de la photo à partir du MultipartFile
               byte[] photoData = photoFile.getBytes();

               // Générer un chemin de fichier unique pour chaque photo
               String fileName = photoFile.getOriginalFilename();
               String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
               Path filePath = Paths.get("src/main/resources/photos/" + uniqueFileName);

               // Sauvegarder les données de la photo dans le dossier de ressources
               Files.write(filePath, photoData);


               photo.setUrl(uniqueFileName);
               photoRepository.save(photo);

               count++;



           }

           if(count == photoFiles.size()){
               finish = true;
           }


           return finish ? ResponseEntity.ok("Photos ajoutées avec succès") :
                   ResponseEntity.badRequest().body("Erreur lors de l'ajout des photos");
       }
         catch (Exception e){
              return ResponseEntity.badRequest().body("Erreur lors de l'ajout des photos");
         }
    }

    @Override
    public ResponseEntity<?> updatePhoto(Photo photo, MultipartFile photoFile) {

        try {
            Optional<Photo> photoOptional = photoRepository.findById(photo.getId());

            if (photoOptional.isPresent()) {
                // Récupérer les données de la photo à partir du MultipartFile
                byte[] photoData = photoFile.getBytes();

                // Générer un chemin de fichier unique pour chaque photo
                String fileName = photoFile.getOriginalFilename();
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                Path filePath = Paths.get("src/main/resources/photos/" + uniqueFileName);


                // Sauvegarder les données de la photo dans le dossier de ressources
                Files.write(filePath, photoData);

                //supprimer l'ancienne photo
                Files.delete(Paths.get("src/main/resources/photos/" + photoOptional.get().getUrl()));

                //mettre à jour la photo
                photoOptional.get().setUrl(uniqueFileName);
                photoRepository.save(photoOptional.get());
                return ResponseEntity.ok("Photo modifiée avec succès");
            }
            else{
                return ResponseEntity.badRequest().body("Photo non trouvée");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @Override
    public ResponseEntity<?>  deletePhoto(int id) {
        try {
            Optional<Photo> photoOptional = photoRepository.findById(id);
            if(photoOptional.isPresent()){
                //supprimer la photo
                Files.delete(Paths.get("src/main/resources/photos/" + photoOptional.get().getUrl()));
                photoRepository.delete(photoOptional.get());
                return ResponseEntity.ok("Photo supprimée avec succès");
            }
            else{
                return ResponseEntity.badRequest().body("Photo non trouvée");
            }
        }
        catch (Exception e){
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
        if(userOptional.isPresent()){
            return ResponseEntity.ok(photoRepository.findAllByHouseUser(userOptional.get()));
        }
        else{
            return ResponseEntity.badRequest().body("Utilisateur non trouvé");
        }
    }


}
