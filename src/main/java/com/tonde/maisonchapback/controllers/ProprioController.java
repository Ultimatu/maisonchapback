package com.tonde.maisonchapback.controllers;


import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Reservation;
import com.tonde.maisonchapback.exceptions.CustomLogger;
import com.tonde.maisonchapback.services.impl.HouseServiceImpl;
import com.tonde.maisonchapback.services.impl.PhotoServiceImpl;
import com.tonde.maisonchapback.services.impl.ReservationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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


    private final HouseServiceImpl houseService;

    private final PhotoServiceImpl photoService;

    private final ReservationServiceImpl reservationService;


    /*
     *                      House APIs Section
     */


    @Operation(
            tags = "Proprio API",
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
                                    ref = "#/components/schemas/House"
                            )
                    )
            )
    )

    @PostMapping("/addHouse")
    public ResponseEntity<String> addHouse(House house) {
        return houseService.addHouse(house);

    }


    @Operation(
            tags = "Proprio API",
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
                                    ref = "#/components/schemas/House"
                            )
                    )
            )
    )
    @PostMapping("/updateHouse")
    public ResponseEntity<String> updateHouse(House house) {
        return houseService.updateHouse(house);
    }


    @Operation(
            tags = "Proprio API",
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
                                    ref = "#/components/schemas/House"
                            )
                    )
            )
    )
    @DeleteMapping("/deleteHouse")
    public ResponseEntity<String> deleteHouse(int id) {
        return houseService.deleteHouse(id);
    }




    /*
     *                      Photo APIs Section
     */


    @Operation(
            tags = "Proprio API",
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
                                    ref = "#/components/schemas/Photo"

                            )

                    )
            )
    )
    @PostMapping("/addPhoto/{houseId}")
    public ResponseEntity<List<String>> addPhotos(
            @RequestParam("files") MultipartFile[] files,
            @PathVariable int houseId
    ) throws IOException {
        CustomLogger.log("INFO", "Starting add photos controller service");

        return photoService.addPhotos(List.of(files), houseId);
    }


    @Operation(
            tags = "Proprio API",
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
                                    ref = "#/components/schemas/Photo"
                            )
                    )
            )
    )
    @DeleteMapping("photo/del/{id}")
    public ResponseEntity<String> deletePhoto(@PathVariable("id") int id) {
        return photoService.deletePhoto(id);
    }




    /*
     *                      Reservation APIs Section
     */




    //update reservation

    @Operation(
            tags = "Proprio API",
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
    public ResponseEntity<String> updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

}