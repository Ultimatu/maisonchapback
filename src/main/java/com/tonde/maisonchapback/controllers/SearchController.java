package com.tonde.maisonchapback.controllers;


import com.tonde.maisonchapback.services.implementation.SearchServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    private final SearchServiceImpl service;

    @Operation(
            summary = "Public API to search house by city",
            description = "This API is used to search house by city",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "city", description = "city of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByCity"
    )
    @GetMapping("/byCity/{city}")
    public ResponseEntity<?> searchByCity(@PathVariable String city){
        return service.searchHouseByCity(city);
    }

        @Operation(
            summary = "Public API to search house by price",
            description = "This API is used to search house by price",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "price", description = "price of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByPrice"
    ) @GetMapping("/byPrice/{price}")
    public ResponseEntity<?> searchByPrice(@PathVariable String price){
        return service.searchHouseByPrice(price);
    }


    //by status

    @Operation(
            summary = "Public API to search house by status",
            description = "This API is used to search house by status",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "status", description = "status of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "int") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByStatus"
    )
    @GetMapping("/byStatus/{status}")
    public ResponseEntity<?> searchByStatus(@PathVariable int status){
        return service.searchHouseByStatus(status);
    }


    //type status, price

    @Operation(
            summary = "Public API to search house by type, status and price",
            description = "This API is used to search house by type, status and price",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "type", description = "type of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "int") ),
                    @Parameter(name = "status", description = "status of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "int") ),
                    @Parameter(name = "price", description = "price of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByTypeAndStatusAndPrice"
    )
    @GetMapping("/byTypeAndCityAndPrice/{type}/{city}/{price}")
    public ResponseEntity<?> searchByTypeAndCityAndPrice(@PathVariable int type, @PathVariable String city, @PathVariable String price){
        return service.searchHouseByTypeAndCityAndPrice(type, city, price);
    }


    //anuy word

    @Operation(
            summary = "Public API to search house by any word",
            description = "This API is used to search house by any word",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "word", description = "word of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByAnyWord"
    )
    @GetMapping("/byAnyWord/{word}")
    public ResponseEntity<?> searchByAnyWord(@PathVariable String word){
        return service.searchHouseByAnyWord(word);
    }


    //type

    @Operation(
            summary = "Public API to search house by type",
            description = "This API is used to search house by type",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "type", description = "type of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "int") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByType"
    )
    @GetMapping("/byType/{type}")
    public ResponseEntity<?> searchByType(@PathVariable int type){
        return service.searchHouseByType(type);
    }



    @Operation(

            summary = "Public API to search house by city and price",
            description = "This API is used to search house by city and price",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "city", description = "city of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") ),
                    @Parameter(name = "price", description = "price of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByCityAndPrice"
    )
    @GetMapping("/byCityAndPrice/{city}/{price}")
    public ResponseEntity<?> searchByCityAndPrice(@PathVariable String city, @PathVariable String price){
        return service.searchHouseByCityAndPrice(city, price);
    }


    //type, price

    @Operation(
            summary = "Public API to search house by type and price",
            description = "This API is used to search house by type and price",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "type", description = "type of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "int") ),
                    @Parameter(name = "price", description = "price of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByTypeAndPrice"
    )
    @GetMapping("/byTypeAndPrice/{type}/{price}")
    public ResponseEntity<?> searchByTypeAndPrice(@PathVariable int type, @PathVariable String price){
        return service.searchHouseByTypeAndPrice(type, price);
    }



    @Operation(
            summary = "Public API to search house by type, city, price, surface, status and rooms",
            description = "This API is used to search house by type, city, price, surface, status and rooms",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "type", description = "type of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "int") ),
                    @Parameter(name = "city", description = "city of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") ),
                    @Parameter(name = "price", description = "price of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") ),
                    @Parameter(name = "surface", description = "surface of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") ),
                    @Parameter(name = "status", description = "status of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "int") ),
                    @Parameter(name = "rooms", description = "rooms of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByAll"
    )
    @GetMapping("/byAll/{type}/{city}/{price}/{surface}/{status}/{rooms}")
    public ResponseEntity<?> searchByAll(@PathVariable int type, @PathVariable String city, @PathVariable String price, @PathVariable String surface, @PathVariable int status, @PathVariable String rooms){
        return service.searchByAll(type, city, price, surface, status, rooms);
    }


    @Operation(
            summary = "Public API to search house by word",
            description = "This API is used to search house by word",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "word", description = "word of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByWord"
    )

    @GetMapping("/byTitleOrDescription/{word}")
    public ResponseEntity<?> searchByTitleOrDescription(@PathVariable String word){
        return service.searchByTitleOrDescription(word);
    }


    @Operation(
            summary = "Public API to search house by title and description",
            description = "This API is used to search house by title and description",
            tags = {"search API"},
            parameters = {
                    @Parameter(name = "word1", description = "word1 of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") ),
                    @Parameter(name = "word2", description = "word2 of the house", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string") )

            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            operationId = "searchByTitleAndDescription"
    )
    @GetMapping("/byTitleAndDescription/{word1}/{word2}")
    public ResponseEntity<?> searchByTitleAndDescription(@PathVariable String word1, @PathVariable String word2){
        return service.searchByTitleAndDescription(word1, word2);
    }


}
