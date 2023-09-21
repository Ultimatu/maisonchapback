package com.tonde.maisonchapback.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountActivationRequest {
    @JsonProperty("key")
    @Column(nullable = false)
    private String key;

    @JsonProperty("userId")
    @Column(nullable = false)
    private Integer userId;
}