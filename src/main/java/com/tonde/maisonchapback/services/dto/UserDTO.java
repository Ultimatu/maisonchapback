package com.tonde.maisonchapback.services.dto;

import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.domains.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;


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

    private boolean active;

    private String photoPath;

    private String langkey;


    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getLangkey() {
        return langkey;
    }

    public void setLangkey(String langkey) {
        this.langkey = langkey;
    }
}
