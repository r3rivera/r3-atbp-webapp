package com.r3projects.atbp.controllers;

import com.r3projects.atbp.model.ApiAppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<ApiAppResponse> getHealth(){
        final ApiAppResponse response = new ApiAppResponse();
        log.info("Health API called...");
        response.setStatusCode(200);
        response.setStatusMessage("Success");
        return ResponseEntity.ok(response);
    }

}
