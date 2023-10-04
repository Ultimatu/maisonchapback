package com.tonde.maisonchapback.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResp{

    @JsonProperty("message")
    private String message;

    public ApiResp(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
