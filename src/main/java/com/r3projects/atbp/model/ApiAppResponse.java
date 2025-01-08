package com.r3projects.atbp.model;


import lombok.Data;

@Data
public class ApiAppResponse {
    private int statusCode;
    private String statusMessage;
}
