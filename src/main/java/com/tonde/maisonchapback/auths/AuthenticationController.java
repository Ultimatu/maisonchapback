package com.tonde.maisonchapback.auths;


import com.tonde.maisonchapback.checker.CheckIfUserAlreadyExists;
import com.tonde.maisonchapback.config.LogoutService;
import com.tonde.maisonchapback.controllers.ApiResp;
import com.tonde.maisonchapback.exceptions.CustomLogger;
import com.tonde.maisonchapback.requests.AuthenticationRequest;
import com.tonde.maisonchapback.requests.RegisterRequest;
import com.tonde.maisonchapback.services.constant.ConstantCenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private static final String MESSAGE = "message";
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

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResp("Cet Numéro/Email est déjà utilisé"));
        }

        logger.info("Registering user...");
        return ResponseEntity.ok(authentificationService.register(request));
    }


    @Operation(
            summary = "Activation du compte d'un utilisateur",
            description = "Permet à un utilisateur d'activer son compte.",
            tags = {"Authentification"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Activation réussie",
                            content = @Content(schema = @Schema(implementation = Object.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Utilisateur non trouvé",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }
    )

    @GetMapping("act-with-code")
    public ResponseEntity<Object> activateWithCode(@RequestParam("code") String code, @RequestParam("email") String email) {
        if (!check.alreadyExist(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResp("User not found"));
        }

        boolean response = authentificationService.activateAccountWithCode(code, email);
        if (response) {
            return ResponseEntity.ok(new ApiResp("Account activated"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResp("Activation failed"));
        }
    }



    @Operation(
            summary = "Régénération du code d'activation",
            description = "Permet à un utilisateur de régénérer son code d'activation.",
            tags = {"Authentification"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Régénération réussie",
                            content = @Content(schema = @Schema(implementation = Object.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Utilisateur non trouvé",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }
    )


    @GetMapping("regenerate-code")
    public ResponseEntity<Object> reGenerateCode(@RequestParam("email") String email) {
        if (!check.alreadyExist(email)) {
            return ResponseEntity.status(404).body("User not found");
        }

        String code = authentificationService.reGenerateCode(email);
        if (code == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResp("Code generation failed"));
        else return ResponseEntity.ok(new ApiResp("Code generated"));

    }


    @Operation(
            summary = "Vérification de l'activation du compte",
            description = "Permet de vérifier si le compte d'un utilisateur est activé.",
            tags = {"Authentification"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vérification réussie",
                            content = @Content(schema = @Schema(implementation = Object.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Utilisateur non trouvé",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }
    )
    @GetMapping("check-if-account-activated")
    public ResponseEntity<Object> checkIfAccountActivated(@RequestParam("email") String email) {
        if (!check.alreadyExist(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResp("User not found"));
        }
        //make thread who keep checking if account is activated

        boolean response = authentificationService.checkIfAccountActivated(email);
        if (!response) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResp("Account not activated"));
        else return ResponseEntity.ok(new ApiResp("Account activated"));

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
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request, HttpServletResponse response) {

        response.setHeader(HttpHeaders.CONTENT_TYPE, ConstantCenter.JSON_CONTENT_TYPE);
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
        response.setHeader(HttpHeaders.CONTENT_TYPE, ConstantCenter.JSON_CONTENT_TYPE);
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
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        logoutService.logout(request, response, null);
    }


    @Operation(
            summary = "Réinitialisation du mot de passe",
            description = "Permet à un utilisateur de réinitialiser son mot de passe.",
            tags = {"Authentification"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Réinitialisation réussie",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Utilisateur non trouvé",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }
    )

    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@Valid @RequestBody RegisterRequest request, HttpServletResponse response) {
        if (!check.alreadyExist(request)) {
            HashMap<String, String> errorResponse = new HashMap<>();
            errorResponse.put(MESSAGE, "user not found");
            response.setStatus(404);
            response.setHeader(HttpHeaders.CONTENT_TYPE, ConstantCenter.JSON_CONTENT_TYPE);
            return ResponseEntity.status(404).body(errorResponse);
        }

        return ResponseEntity.ok("Not implemented yet");
    }

}
