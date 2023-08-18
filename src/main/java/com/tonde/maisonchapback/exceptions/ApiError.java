package com.tonde.maisonchapback.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiError {


    @JsonProperty("status_code")
    private HttpStatus status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("errors")
    private List<String> errors;


}
