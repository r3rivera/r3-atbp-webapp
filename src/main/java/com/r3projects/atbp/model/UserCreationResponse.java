package com.r3projects.atbp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class UserCreationResponse {
    private final String userName;
    private final ApiAppResponse response;
}
