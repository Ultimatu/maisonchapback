package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Favoris;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Hidden
public interface FavorisService {

    ResponseEntity<?> addFavoris(Favoris favoris);

    ResponseEntity<?> updateFavoris(Favoris favoris);

    ResponseEntity<?> deleteFavoris(Favoris favoris);

    Favoris getFavorisById(int id);

    List<Favoris> getAllFavoris();

    List<Favoris> getAllFavorisByUserId(int userId);

    List<Favoris> getAllFavorisByHouseId(int houseId);


}
