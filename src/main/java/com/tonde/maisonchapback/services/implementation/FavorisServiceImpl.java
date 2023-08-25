package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.Favoris;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.repositories.FavorisRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.FavorisService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class FavorisServiceImpl implements FavorisService {

    private final FavorisRepository favorisRepository;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> addFavoris(Favoris favoris) {
        Optional<Favoris> favorisOptional = favorisRepository.findByHouseIdAndUser(favoris.getHouse().getId(), favoris.getUser());
        if(favorisOptional.isPresent()){
            return ResponseEntity.badRequest().body("Cette maison est déjà dans vos favoris");
        }
        else{
            favorisRepository.save(favoris);
            return ResponseEntity.ok("Maison ajoutée dans vos favoris");
        }
    }

    @Override
    public ResponseEntity<?> updateFavoris(Favoris favoris) {
        Optional<Favoris> favorisOptional = favorisRepository.findById(favoris.getId());
        if(favorisOptional.isPresent()){
            favorisOptional.get().setUser(favoris.getUser());
            favorisOptional.get().setHouse(favoris.getHouse());
            favorisRepository.save(favorisOptional.get());
            return ResponseEntity.ok("Favoris modifié avec succès");
        }
        else{
            return ResponseEntity.badRequest().body("Favoris non trouvé");
        }
    }

    @Override
    public ResponseEntity<?> deleteFavoris(Favoris favoris) {
        Optional<Favoris> favorisOptional = favorisRepository.findById(favoris.getId());
        if(favorisOptional.isPresent()){
            favorisRepository.delete(favorisOptional.get());
            return ResponseEntity.ok("Favoris supprimé avec succès");
        }
        else{
            return ResponseEntity.badRequest().body("Favoris non trouvé");
        }
    }

    @Override
    public Favoris getFavorisById(int id) {
        Optional<Favoris> favorisOptional = favorisRepository.findById(id);
        return favorisOptional.orElse(null);
    }

    @Override
    public List<Favoris> getAllFavoris() {
        return favorisRepository.findAll();
    }

    @Override
    public List<Favoris> getAllFavorisByUserId(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(favorisRepository::findAllByUser).orElse(null);

    }

    @Override
    public List<Favoris> getAllFavorisByHouseId(int houseId) {
        Optional<User> userOptional = userRepository.findById(houseId);
        return userOptional.map(favorisRepository::findAllByUser).orElse(null);
    }
}
