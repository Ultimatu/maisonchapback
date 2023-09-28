package com.tonde.maisonchapback.auths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonde.maisonchapback.config.JwtService;
import com.tonde.maisonchapback.domains.AccountActivation;
import com.tonde.maisonchapback.domains.Token;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.domains.enums.Role;
import com.tonde.maisonchapback.domains.enums.TokenType;
import com.tonde.maisonchapback.exceptions.BadCredentialsException;
import com.tonde.maisonchapback.exceptions.CustomLogger;
import com.tonde.maisonchapback.repositories.AccountActivationRepository;
import com.tonde.maisonchapback.repositories.TokenRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.requests.AccountActivationRequest;
import com.tonde.maisonchapback.requests.AuthenticationRequest;
import com.tonde.maisonchapback.requests.RegisterRequest;
import com.tonde.maisonchapback.services.constant.ConstantCenter;
import com.tonde.maisonchapback.services.mail.KeyGenerator;
import com.tonde.maisonchapback.services.mail.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository repository;
    private final AccountActivationRepository activationRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;


    public ResponseEntity<Object> register(@Validated RegisterRequest request)
            throws BadCredentialsException {
        if (request.getRole() == null) {
            request.setRole(Role.FREE_USER);
        }

        User user = User.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .phone(request.getPhone())
                .adresse(request.getAdresse())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        if (user == null) {
            throw new BadCredentialsException(ConstantCenter.BAD_CREDENTIALS);
        }

        repository.save(user);
        String key = KeyGenerator.generateUniqueKey();
        String code = KeyGenerator.generateRandomCode();
        AccountActivation accountCred = AccountActivation
                .builder()
                .key(key)
                .code(code)
                .userId(user.getId())
                .expirationAt(LocalDateTime.now().plusMinutes(10))
                .createdAt(LocalDateTime.now())
                .isUsed(false)
                .build();
        activationRepository.save(accountCred);
        mailService.sendActivationEmail(user, key, code);
        CustomLogger.log("INFO", "mail sended to user");

        return ResponseEntity.status(201).body(ConstantCenter.ACCOUNT_CREATED);

    }


    public boolean activateAccount(
            @Valid
            AccountActivationRequest request
    ) {
        Optional<User> user = repository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new BadCredentialsException(ConstantCenter.BAD_CREDENTIALS);
        }
        Optional<AccountActivation> activation = activationRepository.findByUserIdAndKey(request.getUserId(), request.getKey());

        if (activation.isEmpty()) {
            throw new BadCredentialsException(ConstantCenter.BAD_CREDENTIALS);
        }


        if (activation.get().isUsed()) {
            throw new BadCredentialsException(ConstantCenter.BAD_CREDENTIALS);
        }
        if (activation.get().isExpired()) {
            //
            // activationRepository.delete(activation.get());
            // String key = KeyGenerator.generateUniqueKey();
            // String code = KeyGenerator.generateRandomCode();
            // var accountCred = AccountActivation
            //         .builder()
            //         .key(key)
            //         .code(code)
            //         .userId(user.get().getId())
            //         .expirationAt(LocalDateTime.now().plusMinutes(10))
            //         .createdAt(LocalDateTime.now())
            //         .isUsed(false)
            //         .build();
            // activationRepository.save(accountCred);
            // mailService.sendActivationEmail(user.get(), key, code);
            return false;
        }

        user.get().setActive(true);
        activation.get().setUsed(true);
        activationRepository.save(activation.get());
        repository.save(user.get());

        return true;

    }

    public boolean activateAccountWithCode(String code, String email) {
        Optional<User> userOptional = repository.findByEmail(email);
        if (userOptional.isPresent() && !userOptional.get().isActive()){
            Optional<AccountActivation> activation = activationRepository.findByUserIdAndCode(userOptional.get().getId(), code);
            if (activation.isPresent() && !activation.get().isUsed()){
                userOptional.get().setActive(true);
                activation.get().setUsed(true);
                activationRepository.save(activation.get());
                repository.save(userOptional.get());
                return true;
            }
            return false;

        }
        return false;

    }

    public String reGenerateCode(String email) {
        Optional<User> userOptional = repository.findByEmail(email);
        if (userOptional.isPresent() && !userOptional.get().isActive()){
            Optional<AccountActivation> activation = activationRepository.findByUserIdAndCode(userOptional.get().getId(), null);
            if (activation.isPresent() && !activation.get().isUsed() && activation.get().isExpired()){
                String code = KeyGenerator.generateRandomCode();
                String key = KeyGenerator.generateUniqueKey();
                activation.get().setKey(key);
                activation.get().setCode(code);
                activation.get().setExpirationAt(LocalDateTime.now().plusMinutes(10));

                activationRepository.save(activation.get());
                mailService.sendActivationEmail(userOptional.get(), key, code);
                return code;
            }
            return null;

        }
        return null;
    }


    public AuthenticationResponse authenticate(
            @Validated
            AuthenticationRequest request
    ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            Optional<User> user = repository.findByEmail(request.getEmail());
            if (user.isPresent() && !user.get().isActive()) {
                throw new BadCredentialsException(ConstantCenter.PENDING_ACCOUNT);
            }
        } catch (Exception e) {
            throw new BadCredentialsException(ConstantCenter.BAD_CREDENTIALS);
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
            @NonNull User user,
            @NonNull String jwtToken
    ) {

        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        if (token == null) {
            throw new BadCredentialsException(ConstantCenter.BAD_CREDENTIALS);
        }

        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
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
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstantCenter.INVALID_TOKEN);
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();

            if (jwtService.isTokenValid(refreshToken, user)) {
                var jwtToken = jwtService.generateToken(user);
                var newRefreshToken = jwtService.generateRefreshToken(user);
                revokeAllUserTokens(user);
                savedUserToken(user, jwtToken);

                AuthenticationResponse authResponse = AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(newRefreshToken)
                        .build();

                if (authResponse == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstantCenter.INVALID_TOKEN);
                    return;
                }

                new ObjectMapper()
                        .writeValue(
                                response.getOutputStream(),
                                authResponse
                        );
            }


        }
    }


    /**
     * Cette fonction sera appélée dans un traitement asynchrone pour detecter si le compte est activé lors de la création du compte
     * @param email email de l'utilisateur
     * @return true si le compte est activé, false sinon
     */

    public Boolean checkIfAccountActivated(String email) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isEmpty()) {
            throw new BadCredentialsException(ConstantCenter.BAD_CREDENTIALS);
        }
        return user.get().isActive();
    }
}
