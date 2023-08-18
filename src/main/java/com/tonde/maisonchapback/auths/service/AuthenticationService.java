package com.tonde.maisonchapback.auths.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonde.maisonchapback.auths.requests.AuthenticationRequest;
import com.tonde.maisonchapback.auths.requests.RegisterRequest;
import com.tonde.maisonchapback.config.JwtService;
import com.tonde.maisonchapback.models.token.Token;
import com.tonde.maisonchapback.models.token.TokenType;
import com.tonde.maisonchapback.models.roles.Role;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.repositories.TokenRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.auths.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(@Valid RegisterRequest request)
            throws RuntimeException
    {
        //verifier s'il a un role vide si oui on lui attribue le role USER
        if (request.getRole() == null){
            request.setRole(Role.FREE_USER);
        }

        var user = User.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .phone(request.getPhone())
                .adresse(request.getAdresse())
                .locked(false)

                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        System.out.println("builded, going to insert");
        var savedUser = repository.save(user);

        System.out.println("user saved");
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        savedUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    public AuthenticationResponse authenticate(
            @Validated
            AuthenticationRequest request
    ){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }
        catch (Exception e){
            System.out.println("Request: " + request);
            throw new RuntimeException("Bad credentials");
        }
        var user = repository.findByEmail(request.getEmail()).orElseThrow(null);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        savedUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();


    }

    public void savedUserToken(
            User user,
            String jwtToken
    ){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()){
            return;
        }

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    )throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow(); //var est une variable locale de type implicite

            if (jwtService.isTokenValid(refreshToken, user)) {
                var jwtToken = jwtService.generateToken(user);
                var newRefreshToken = jwtService.generateRefreshToken(user);
                revokeAllUserTokens(user);
                savedUserToken(user, jwtToken);

                var authResponse = AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(newRefreshToken)
                        .build();

                new ObjectMapper()
                        .writeValue(
                                response.getOutputStream(),
                                authResponse
                        );
            }


        }
    }


}