package com.tonde.maisonchapback.services.interfaces;


import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Hidden
public interface SearchService {


    public ResponseEntity<?> searchHouseByCity(String city);

    public ResponseEntity<?> searchHouseByPrice(String price);

    public ResponseEntity<?> searchHouseByStatus(int status);

    public ResponseEntity<?> searchHouseByTypeAndCityAndPrice(int type, String city, String price);

    public ResponseEntity<?> searchHouseByAnyWord(String word);

    public ResponseEntity<?> searchHouseByType(int type);

    public ResponseEntity<?> searchHouseByCityAndPrice(String city, String price);

    public ResponseEntity<?> searchHouseByTypeAndPrice(int type, String price);

    public ResponseEntity<?> searchByAll(int type, String city, String price, String surface, int status, String rooms);

    public ResponseEntity<?> searchByTitleOrDescription(String word);

    public ResponseEntity<?> searchByTitleAndDescription(String word1, String word2);



}
