package com.tonde.maisonchapback.models.workflows.user;


import com.tonde.maisonchapback.models.token.Token;
import com.tonde.maisonchapback.models.roles.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.validation.constraints.Email;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_users")
@Schema(
        name = "User",
        description = "User model",
        example = """
                {
                  "id": "integer",
                  "nom": "string",
                  "prenom": "string",
                  "email": "string",
                  "phone": "string",
                  "adresse": "string",
                  "password": "string",
                  "role": "Role",
                  "tokens": "List<Token>",
                  "locked": "boolean",
                  "photoPath": "string"
                }""",
        requiredProperties = {"nom", "prenom", "email", "phone", "adresse", "password", "role", "locked", "photoPath"}
        
)

public class User implements UserDetails , Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;


    @Email(message = "L'email doit être valide")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String  adresse;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Token> tokens;


    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean locked = false;

    @Column(nullable = false, columnDefinition = "varchar(1000) default 'default.png'")
    private String photoPath;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.locked;
    }

}
