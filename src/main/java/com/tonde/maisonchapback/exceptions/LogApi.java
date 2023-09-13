package com.tonde.maisonchapback.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LogApi {

    @JsonProperty("type")
    private String type;
    @JsonProperty("message")
    private String message;


}
