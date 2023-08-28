package com.tonde.maisonchapback.auths.requests;



import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        name = "AuthenticationRequest",
        description = "Request body for authentication",

        oneOf = AuthenticationRequest.class,
        example = """
                {
                  "email": "string",
                  "password": "string"
                }""",
        implementation = AuthenticationRequest.class

)
public class AuthenticationRequest {

    @Column(nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$", message = "Password should be valid")
    String password;
}
