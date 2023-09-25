package com.tonde.maisonchapback.auths;


import com.tonde.maisonchapback.checker.CheckIfUserAlreadyExists;
import com.tonde.maisonchapback.config.LogoutService;
import com.tonde.maisonchapback.requests.AccountActivationRequest;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private static final String MESSAGE = "message";
    private final AuthenticationService authentificationService;
    private final LogoutService logoutService;
    private final CheckIfUserAlreadyExists check;
    @Value("${application.frontend.url}")
    private String frontendUrl;

    @Value("${application.frontend.login.endpoint}")
    private String loginEndpoint;

    @Value("${application.frontend.renew-account.endpoint}")
    private String renewAccountEndpoint;


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
            HashMap<String, String> errorResponse = new HashMap<>();
            errorResponse.put(MESSAGE, "User already exists");
            response.setStatus(403);
            response.setHeader(HttpHeaders.CONTENT_TYPE, ConstantCenter.JSON_CONTENT_TYPE);
            return ResponseEntity.status(403).body(errorResponse);
        }

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

    @GetMapping("/activate")

    public RedirectView activate(@RequestParam("key") String key, @RequestParam("userId") Integer userId, HttpServletResponse response) {
        AccountActivationRequest request = new AccountActivationRequest();
        request.setKey(key);
        request.setUserId(userId);

        if (!check.alreadyExist(request.getUserId())) {
            return new RedirectView(frontendUrl + loginEndpoint);
        }

        if (!authentificationService.activateAccount(request)) return new RedirectView(frontendUrl + loginEndpoint);
        else return new RedirectView(frontendUrl + renewAccountEndpoint);

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
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
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
