package com.tonde.maisonchapback.services.interfaces;


import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Hidden
public interface SearchService {


    ResponseEntity<?> searchHouseByCity(String city);

    ResponseEntity<?> searchHouseByPrice(String price);

    ResponseEntity<?> searchHouseByStatus(int status);

    ResponseEntity<?> searchHouseByTypeAndCityAndPrice(int type, String city, String price);

    ResponseEntity<?> searchHouseByAnyWord(String word);

    ResponseEntity<?> searchHouseByType(int type);

    ResponseEntity<?> searchHouseByCityAndPrice(String city, String price);

    ResponseEntity<?> searchHouseByTypeAndPrice(int type, String price);

    ResponseEntity<?> searchByAll(int type, String city, String price, String surface, int status, String rooms);

    ResponseEntity<?> searchByTitleOrDescription(String word);

    ResponseEntity<?> searchByTitleAndDescription(String word1, String word2);



}
