package com.tonde.maisonchapback.controllers;


import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Photo;
import com.tonde.maisonchapback.models.workflows.Reservation;
import com.tonde.maisonchapback.services.implementation.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.OnError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/proprio")
@PreAuthorize("hasRole('ROLE_FREE_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_PROPRIO')")
@CrossOrigin(origins = "http://localhost:4200")

public class ProprioController {

    private final UserServiceImpl userService;

    private final HouseServiceImpl houseService;

    private final PhotoServiceImpl photoService;

    private final ReservationServiceImpl reservationService;


    /*
     *                      House APIs Section
     */


    @Operation(
            tags = "{ProprioController}",
            summary = "Ajouter une maison",
            description = "Ajouter une maison",
            operationId = "addHouse",
            method = "POST",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Maison ajoutée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de l'ajout de la maison",
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
                    description = "Maison à ajouter",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    implementation = House.class
                            )
                    )
            )
    )

    @PostMapping("/addHouse")
    public ResponseEntity<?> addHouse(House house) {
        return houseService.addHouse(house);

    }



    @Operation(
            tags = "{ProprioController}",
            summary = "Modifier une maison",
            description = "Modifier une maison",
            operationId = "updateHouse",
            method = "POST",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Maison modifiée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de la modification de la maison",
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
                    description = "Maison à modifier",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    implementation = House.class
                            )
                    )
            )
    )
    @PostMapping("/updateHouse")
    public ResponseEntity<?> updateHouse(House house) {
        return houseService.updateHouse(house);
    }



    @Operation(
            tags = "{ProprioController}",
            summary = "Supprimer une maison",
            description = "Supprimer une maison",
            operationId = "deleteHouse",
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Maison supprimée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de la suppression de la maison",
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
                    description = "Maison à supprimer",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    implementation = House.class
                            )
                    )
            )
    )
    @DeleteMapping("/deleteHouse")
    public ResponseEntity<?> deleteHouse(int id) {
        return houseService.deleteHouse(id);
    }


    @Operation(
            tags = "{ProprioController}",
            summary = "Récupérer une maison par son id",
            description = "Récupérer une maison par son id",
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
                                    implementation = House.class
                            )
                    )
            )
    )

    @GetMapping("/getAllHousesByUserId")
    public ResponseEntity<?> getAllHousesByUserId(int userId) {
        return houseService.getAllHousesByUserId(userId);
    }



    @Operation(
            tags = "{ProprioController}",
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
                                    implementation = House.class
                            )
                    )
            )
    )
    public ResponseEntity<?> getAllHousesByStatus(int status) {
        return houseService.getAllHousesByStatus(status);
    }


    //by disponibility

    @Operation(
            tags = "{ProprioController}",
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
                                    implementation = House.class
                            )
                    )
            )
    )
    @GetMapping("/getAllHousesByDisponibility")

    public ResponseEntity<?> getAllHousesByDisponibility(String disponibility) {
        return houseService.getAllHousesByDisponibility(disponibility);
    }



    /*
     *                      Photo APIs Section
     */


    @Operation(
            tags = "{ProprioController}",
            summary = "Ajouter une photo",
            description = "Ajouter une photo",
            operationId = "addPhoto",
            method = "POST",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Photo ajoutée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de l'ajout de la photo",
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
                    description = "Photo à ajouter",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "multipart/form-data",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    implementation = Photo.class
                            )
                    )
            )
    )
    @PostMapping("/addPhoto")
    public ResponseEntity<?> addPhotos(List<MultipartFile>  files, Photo photo) throws IOException {
        return photoService.addPhotos(files, photo);
    }


    @Operation(
            tags = "{ProprioController}",
            summary = "Supprimer une photo",
            description = "Supprimer une photo",
            operationId = "deletePhoto",
            method = "DELETE",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Photo supprimée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Erreur lors de la suppression de la photo",
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
                    description = "Photo à supprimer",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "multipart/form-data",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    implementation = Photo.class
                            )
                    )
            )
    )
    public ResponseEntity<?> deletePhoto(int id) {
        return photoService.deletePhoto(id);
    }


    @Operation(
            tags = "{ProprioController}",
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
                                    implementation = Photo.class
                            )
                    )
            )
    )

    @GetMapping("/getAllPhotosByHouseId")
    public ResponseEntity<?> getAllPhotosByHouseId(int houseId) {
        return (ResponseEntity<?>) photoService.getAllPhotosByHouseId(houseId);
    }

    /*
     *                      Reservation APIs Section
     */

    @Operation(
            tags = "{ProprioController}",
            summary = "Ajouter une réservation",
            description = "Ajouter une réservation",
            operationId = "addReservation",
            method = "POST",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Réservation ajoutée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Erreur lors de l'ajout de la réservation",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            )
                    ),
            }
    )
    @PostMapping("/addReservation")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);

    }


    //update reservation

    @Operation(
            tags = "{ProprioController}",
            summary = "Modifier une réservation",
            description = "Modifier une réservation",
            operationId = "updateReservation",
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Réservation modifiée avec succès",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            ),
                            useReturnTypeSchema = true
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Erreur lors de la modification de la réservation",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            )
                    ),
            }
    )
    @PutMapping("/updateReservation")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

}