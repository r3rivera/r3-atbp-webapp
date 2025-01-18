package com.r3projects.atbp.controllers;

import com.r3projects.atbp.model.ApiAppResponse;
import com.r3projects.atbp.model.AppResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthController {

    private final String releaseDescription;

    public HealthController(@Value("${r3app.release.desc}") String releaseDescription){
        this.releaseDescription = releaseDescription;
    }

    @GetMapping("/health")
    public ResponseEntity<AppResponse<String>> getHealth(){
        final ApiAppResponse response = new ApiAppResponse();
        log.info("Health API called...");
        response.setStatusCode(200);
        response.setStatusMessage("Success");
        final AppResponse<String> appResponse = new AppResponse<>(this.releaseDescription, response);
        return ResponseEntity.ok(appResponse);
    }

}
