package com.r3projects.atbp.controllers;

import com.r3projects.atbp.model.ApiAppResponse;
import com.r3projects.atbp.model.UserCreationRequest;
import com.r3projects.atbp.model.UserCreationResponse;
import com.r3projects.atbp.services.ManageUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/manage")
public class UserManageController {

    private final ManageUserService manageUserService;

    @PostMapping("/user")
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody UserCreationRequest request){
        log.info("Creating user information...");
        final String userName = manageUserService.createUserName(request);
        final ApiAppResponse appResponse = new ApiAppResponse();
        appResponse.setStatusCode(0);
        appResponse.setStatusMessage("Success");

        final UserCreationResponse userCreationResponse = new UserCreationResponse(userName, appResponse);
        return ResponseEntity.ok(userCreationResponse);
    }
}
