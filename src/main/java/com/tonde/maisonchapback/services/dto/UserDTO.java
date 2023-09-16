package com.tonde.maisonchapback.services.dto;

import com.tonde.maisonchapback.domains.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;


@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le prenom est obligatoire")
    private String prenom;


    @Email(message = "L'email doit être valide")
    @NotNull(message = "L'email est obligatoire")
    private String email;

    @NotNull(message = "Le numéro de téléphone est obligatoire")
    private String phone;

    private String adresse;

    @NotNull(message = "Le mot de passe est obligatoire")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule et un chiffre")
    private String password;

    private Role role;

    private boolean locked;

    private String photoPath;


}
