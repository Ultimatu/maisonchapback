package com.tonde.maisonchapback.requests;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PasswordResetRequest {

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    @NotNull
    private String key;


    public boolean isPasswordMatch() {
        return password.equals(confirmPassword);
    }


}
