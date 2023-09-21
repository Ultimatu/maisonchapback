package com.tonde.maisonchapback.controllers;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Photo;
import com.tonde.maisonchapback.exceptions.CustomLogger;
import com.tonde.maisonchapback.services.impl.HouseServiceImpl;
import com.tonde.maisonchapback.services.impl.PhotoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/houses")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicController{

    private final HouseServiceImpl houseService;
    private final PhotoServiceImpl photoService;



    @GetMapping("/all")
    public List<House> allHouses() {
        CustomLogger.log("INFO", "Getting all houses");
        return houseService.getAllHouses();
    }


    /**
     * {@code GET  /api/public/houses/renting} : Recuperer toutes les maisons en location
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all renting houses.
     * @return the {@link ResponseEntity} with status {@code 404 (Not Found)} if no renting houses found.
     * @return
     */
    @GetMapping("/renting")

    public List<House> allRentingHouses() {
        return houseService.getAllRentingHouses();
    }


    /**
     * {@code GET  /api/public/houses/selling} : Recuperer toutes les maisons en vente
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all selling houses.
     * @return the {@link ResponseEntity} with status {@code 404 (Not Found)} if no selling houses found.
     * @return
     */
    @GetMapping("/selling")
    public List<House> allSellingHouses() {
        return houseService.getAllSellingHouses();
    }


    @Operation(
            tags = "Public API",
            summary = "Récupérer une photo par l'id de la maison",
            description = "Récupérer une photo par l'id de la maison",
            operationId = "getAllPhotosByHouseId",
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Photo récupérée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de la récupération de la photo",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            )
                    ),
            },
            security = {
                    @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                            name = "bearerAuth",
                            scopes = {"read", "write"}
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Photo à récupérer par l'id de la maison",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "multipart/form-data",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    ref = "#/components/schemas/Photo"
                            )
                    )
            )
    )

    @GetMapping("/get-all-by-house-id/{id}")
    public List<Photo> getAllPhotosByHouseId(@PathVariable int id) {
        return photoService.getAllPhotosByHouseId(id);
    }


    @Operation(
            tags = "Public API",
            summary = "Récupérer les maisons d'un utilisateur",
            description = "Récupérer les maisons d'un utilisateur",
            operationId = "getHouseById",
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Maison récupérée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de la récupération de la maison",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            )
                    ),
            },
            security = {
                    @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                            name = "bearerAuth",
                            scopes = {"read", "write"}
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Maison à récupérer par son id",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    ref = "#/components/schemas/House"
                            )
                    )
            )
    )

    @GetMapping("/by-user/{userId}")
    public List<House> getAllHousesByUserId(@PathVariable  int userId) {
        return houseService.getAllHousesByUserId(userId);
    }


    @Operation(
            tags = "Public API",
            summary = "Récupérer une maison par son status",
            description = "Récupérer une maison par son status",
            operationId = "getAllHousesByStatus",
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Maison récupérée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de la récupération de la maison",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            )
                    ),
            },
            security = {
                    @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                            name = "bearerAuth",
                            scopes = {"read", "write"}
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Maison à récupérer par son status",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    ref = "#/components/schemas/House"
                            )
                    )
            )
    )
    @GetMapping("/by-status/{id}")
    public List<House> getAllHousesByStatus(@PathVariable int id) {
        return houseService.getAllHousesByStatus(id);
    }


    //by disponibility

    @Operation(
            tags = "Public API",
            summary = "Récupérer une maison par sa disponibilité",
            description = "Récupérer une maison par sa disponibilité",
            operationId = "getAllHousesByDisponibility",
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Maison récupérée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de la récupération de la maison",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            )
                    ),
            },
            security = {
                    @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                            name = "bearerAuth",
                            scopes = {"read", "write"}
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Maison à récupérer par sa disponibilité",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    ref = "#/components/schemas/House"
                            )
                    )
            )
    )
    @GetMapping("/dispo/{dispo}")
    public List<House> getAllHousesByDisponibility(@PathVariable String dispo) {
        return houseService.getAllHousesByDisponibility(dispo);
    }


}
