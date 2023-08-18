package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Favoris;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public interface FavorisService {

    public ResponseEntity<?> addFavoris(Favoris favoris);

    public ResponseEntity<?> updateFavoris(Favoris favoris);

    public ResponseEntity<?> deleteFavoris(Favoris favoris);

    public Favoris getFavorisById(int id);

    public List<Favoris> getAllFavoris();

    public List<Favoris> getAllFavorisByUserId(int userId);

    public List<Favoris> getAllFavorisByHouseId(int houseId);


}
