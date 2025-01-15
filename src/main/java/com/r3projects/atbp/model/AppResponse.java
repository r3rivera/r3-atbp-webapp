package com.r3projects.atbp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AppResponse<T> {
    private final T data;
    private final ApiAppResponse response;
}
