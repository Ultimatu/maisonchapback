package com.tonde.maisonchapback.auths.controller;



import com.tonde.maisonchapback.auths.requests.AuthenticationRequest;
import com.tonde.maisonchapback.auths.requests.RegisterRequest;
import com.tonde.maisonchapback.auths.response.AuthenticationResponse;
import com.tonde.maisonchapback.auths.service.AuthenticationService;
import com.tonde.maisonchapback.checker.CheckIfUserAlreadyExists;
import com.tonde.maisonchapback.config.LogoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authentificationService;
    private final LogoutService logoutService;
    private final CheckIfUserAlreadyExists check;


   @Operation(
            summary = "Inscription d'un utilisateur",
            description = "Permet à un nouvel utilisateur de s'inscrire.",
           tags = {"Authentification"},
           responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Inscription réussie",
                            content = @Content(schema = @Schema(implementation = Object.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Accès Interdit: Utilisateur existe",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }

    )

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest request, HttpServletResponse response) {
        if (check.alreadyExist(request)) {
            return ResponseEntity.badRequest().body("Utilisateur existe");
        }

        return ResponseEntity.ok( authentificationService.register(request));
    }

    @Operation(
            summary = "Authentification d'un utilisateur",
            description = "Permet à un utilisateur de s'authentifier.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authentification réussie",
                            content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = AuthenticationRequest.class))
            ),
            parameters = {
                    @Parameter(
                            name = "request",
                            description = "Requête d'authentification",
                            required = true,
                            schema = @Schema(implementation = AuthenticationRequest.class)
                    )
            },
            tags = {"Authentification"}
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authentificationService.authenticate(request));
    }


    @Operation(
            summary = "Renouvellement du jeton d'authentification",
            description = "Permet de renouveler le jeton d'authentification expiré.",
            tags = {"Authentification"},

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Renouvellement réussi",
                            content = @Content(schema = @Schema(hidden = true))

                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(hidden = true))
            ),
            parameters = {
                    @Parameter(
                            name = "request",
                            description = "Requête de renouvellement",
                            required = true,
                            schema = @Schema(hidden = true)
                    )
            }

    )

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authentificationService.refreshToken(request, response);
    }



    @Operation(
            summary = "Déconnexion de l'utilisateur",
            tags = {"Authentification"},
            description = "Permet à un utilisateur de se déconnecter.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Déconnexion réussie",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        logoutService.logout(request, response, null);
    }

}
