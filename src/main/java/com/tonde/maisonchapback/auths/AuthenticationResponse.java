package com.tonde.maisonchapback.auths;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "AuthenticationResponse",
        description = "Response body for authentication",

        oneOf = AuthenticationResponse.class,
        example = """
                {
                  "access_token": "string",
                  "refresh_token": "string"
                }""",
        implementation = AuthenticationResponse.class

)
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

}
