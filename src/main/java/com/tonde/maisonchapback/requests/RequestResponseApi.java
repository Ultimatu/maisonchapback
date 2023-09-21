package com.tonde.maisonchapback.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestResponseApi{
    @JsonProperty("message")
    private String message;
    @JsonProperty("code")
    private int code;


    public RequestResponseApi(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
