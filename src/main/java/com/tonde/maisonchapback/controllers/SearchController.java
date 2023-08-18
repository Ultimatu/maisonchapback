package com.tonde.maisonchapback.controllers;


import com.tonde.maisonchapback.services.implementation.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    private final SearchServiceImpl service;


    /*
    * @GetMapping("/byCity/{city}")
    * @GetMapping("/byPrice/{price}")
    * @GetMapping("/byStatus/{status}")
    * @GetMapping("/byTypeAndCityAndPrice/{type}/{city}/{price}")
    * @GetMapping("/byAnyWord/{word}")
    * @GetMapping("/byType/{type}")
    * @GetMapping("/byCityAndPrice/{city}/{price}")
    * @GetMapping("/byTypeAndPrice/{type}/{price}")
    * @GetMapping("/byAll/{type}/{city}/{price}/{surface}/{status}/{rooms}")
    * @GetMapping("/byTitleOrDescription/{word}")
    * @GetMapping("/byTitleAndDescription/{word1}/{word2}")
    *
    * SEARCH APIS
     */

    @GetMapping("/byCity/{city}")
    public ResponseEntity<?> searchByCity(@PathVariable String city){
        return service.searchHouseByCity(city);
    }

    @GetMapping("/byPrice/{price}")
    public ResponseEntity<?> searchByPrice(@PathVariable String price){
        return service.searchHouseByPrice(price);
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<?> searchByStatus(@PathVariable int status){
        return service.searchHouseByStatus(status);
    }

    @GetMapping("/byTypeAndCityAndPrice/{type}/{city}/{price}")
    public ResponseEntity<?> searchByTypeAndCityAndPrice(@PathVariable int type, @PathVariable String city, @PathVariable String price){
        return service.searchHouseByTypeAndCityAndPrice(type, city, price);
    }

    @GetMapping("/byAnyWord/{word}")
    public ResponseEntity<?> searchByAnyWord(@PathVariable String word){
        return service.searchHouseByAnyWord(word);
    }

    @GetMapping("/byType/{type}")
    public ResponseEntity<?> searchByType(@PathVariable int type){
        return service.searchHouseByType(type);
    }

    @GetMapping("/byCityAndPrice/{city}/{price}")
    public ResponseEntity<?> searchByCityAndPrice(@PathVariable String city, @PathVariable String price){
        return service.searchHouseByCityAndPrice(city, price);
    }

    @GetMapping("/byTypeAndPrice/{type}/{price}")
    public ResponseEntity<?> searchByTypeAndPrice(@PathVariable int type, @PathVariable String price){
        return service.searchHouseByTypeAndPrice(type, price);
    }

    @GetMapping("/byAll/{type}/{city}/{price}/{surface}/{status}/{rooms}")
    public ResponseEntity<?> searchByAll(@PathVariable int type, @PathVariable String city, @PathVariable String price, @PathVariable String surface, @PathVariable int status, @PathVariable String rooms){
        return service.searchByAll(type, city, price, surface, status, rooms);
    }

    @GetMapping("/byTitleOrDescription/{word}")
    public ResponseEntity<?> searchByTitleOrDescription(@PathVariable String word){
        return service.searchByTitleOrDescription(word);
    }

    @GetMapping("/byTitleAndDescription/{word1}/{word2}")
    public ResponseEntity<?> searchByTitleAndDescription(@PathVariable String word1, @PathVariable String word2){
        return service.searchByTitleAndDescription(word1, word2);
    }







}
