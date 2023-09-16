package com.tonde.maisonchapback.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;


@Builder
@RequiredArgsConstructor
public class ApiError extends Throwable {


    @JsonProperty("status_code")
    private final HttpStatus status;
    @JsonProperty("message")
    private final String message;
    @JsonProperty("errors")
    private final List<String> errors;


}
