package com.tonde.maisonchapback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@ControllerAdvice
public class CustomRestControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public ApiError handleException(Exception e) {
        return ApiError.builder()
                .message("Erreur interne du serveur")
                .build();
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST, reason = "Bad Request")
    public ApiError handleIllegalArgumentException(IllegalArgumentException e) {
        return ApiError.builder()
                .message("Requête invalide")
                .status(org.springframework.http.HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST, reason = "Bad Request")
    public ApiError handleIllegalStateException(IllegalStateException e) {
        return ApiError.builder()
                .message("Requête illégale")
                .status(org.springframework.http.HttpStatus.BAD_REQUEST)
                .errors(List.of(e.getMessage()))
                .build();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST, reason = "Bad Request")
    public ApiError handleNullPointerException(NullPointerException e) {
        return ApiError.builder()
                .message("Requête invalide")
                .status(org.springframework.http.HttpStatus.BAD_REQUEST)
                .errors(List.of(e.getMessage()))
                .build();
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.FORBIDDEN, reason = "Forbidden")
    public ApiError handleAccessDeniedException(org.springframework.security.access.AccessDeniedException e) {
        return ApiError.builder()
                .message("Accès interdit")
                .status(org.springframework.http.HttpStatus.FORBIDDEN)
                .errors(List.of(e.getMessage()))
                .build();
    }

    @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    public ApiError handleBadCredentialsException(org.springframework.security.authentication.BadCredentialsException e) {
        return ApiError.builder()
                .message("Accès non autorisé, veuillez vérifier vos identifiants")
                .errors(List.of(e.getMessage()))
                .status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(org.springframework.security.authentication.DisabledException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    public ApiError handleDisabledException(org.springframework.security.authentication.DisabledException e) {
        return ApiError.builder()
                .message("Accès non autorisé, Vous n'êtes pas autorisé à accéder à cette ressource")
                .errors(List.of(e.getMessage()))
                .status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(org.springframework.security.authentication.LockedException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    public ApiError handleLockedException(org.springframework.security.authentication.LockedException e) {
        return ApiError.builder()
                .message("Accès non autorisé, Votre compte est bloqué")
                .errors(List.of(e.getMessage()))
                .status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(org.springframework.security.authentication.CredentialsExpiredException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    public ApiError handleCredentialsExpiredException(org.springframework.security.authentication.CredentialsExpiredException e) {
        return ApiError.builder()
                .message("Accès non autorisé, Vos identifiants ont expiré")
                .errors(List.of(e.getMessage()))
                .status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(org.springframework.security.authentication.AccountExpiredException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    public ApiError handleAccountExpiredException(org.springframework.security.authentication.AccountExpiredException e) {
        return ApiError.builder()
                .message("Accès non autorisé, Votre compte a expiré")
                .errors(List.of(e.getMessage()))
                .status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(org.springframework.security.authentication.AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    public ApiError handleAuthenticationCredentialsNotFoundException(org.springframework.security.authentication.AuthenticationCredentialsNotFoundException e) {
        return ApiError.builder()
                .message("Accès non autorisé")
                .errors(List.of(e.getMessage()))
                .status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(org.springframework.security.authentication.AuthenticationServiceException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    public ApiError handleAuthenticationServiceException(org.springframework.security.authentication.AuthenticationServiceException e) {
        return ApiError.builder()
                .message("Accès non autorisé")
                .errors(List.of(e.getMessage()))
                .status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Token Expired")
    public ApiError handleJsonWebException(org.springframework.security.authentication.AuthenticationServiceException e) {
        return ApiError
                .builder()
                .message("Le token est expirer, veuillez saisir le token de renouvellement")
                .errors(List.of(e.getMessage()))
                .status(HttpStatus.NOT_ACCEPTABLE)
                .build();


    }


}
