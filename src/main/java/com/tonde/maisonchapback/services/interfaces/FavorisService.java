package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.domains.Favoris;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Hidden
public interface FavorisService {

    ResponseEntity<String> addFavoris(Favoris favoris);

    ResponseEntity<String> updateFavoris(Favoris favoris);

    ResponseEntity<String> deleteFavoris(Favoris favoris);

    Favoris getFavorisById(int id);

    List<Favoris> getAllFavoris();

    List<Favoris> getAllFavorisByUserId(int userId);

    List<Favoris> getAllFavorisByHouseId(int houseId);


}
