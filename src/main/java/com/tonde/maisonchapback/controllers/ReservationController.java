package com.tonde.maisonchapback.controllers;

import com.tonde.maisonchapback.domains.Reservation;
import com.tonde.maisonchapback.services.impl.ReservationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAuthority('ROLE_FREE_USER') or hasAuthority('ROLE_STANDARD_USER') or hasAuthority('ROLE_PREMIUM_USER')")
public class ReservationController {

    private final ReservationServiceImpl reservationService;


    @Operation(
            tags = "Reservation API",
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
    public ResponseEntity<String> addReservation(@RequestBody Reservation reservation) {

        return reservationService.createReservation(reservation);

    }
}
