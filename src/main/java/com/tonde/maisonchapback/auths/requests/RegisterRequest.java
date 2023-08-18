package com.tonde.maisonchapback.auths.requests;



import com.tonde.maisonchapback.models.roles.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "Nom should be valid")
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "Prenom should be valid")
    private String prenom;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{3}-?[0-9]{6,12}$", message = "Telephone should be valid")
    private String phone;
    private String adresse;
    private Role role;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password should be valid") // min 8 chars, 1 uppercase, 1 lowercase, 1 number
    private String password;

}
