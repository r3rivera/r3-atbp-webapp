package com.r3projects.atbp.controllers;

import com.r3projects.atbp.domain.DataDetails;
import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.model.ApiAppResponse;
import com.r3projects.atbp.model.AppResponse;
import com.r3projects.atbp.model.UserRequest;
import com.r3projects.atbp.services.ManageUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/manage")
public class UserManageController {

    private final ManageUserService manageUserService;

    @PostMapping("/user")
    public ResponseEntity<AppResponse<String>> createUser(@RequestBody UserRequest request){
        log.info("Creating user information...");
        final DataDetails details = manageUserService.createUserName(request);
        return ResponseEntity.ok(new AppResponse( details.getCreatedBy(), createSuccess()));
    }

    @GetMapping("/user")
    public ResponseEntity<AppResponse<UserInfoDetails>> getUserDetails(@RequestBody UserRequest request){
        log.info("Querying user information...");
        UserInfoDetails infoDetails = request.getUserInfo();
        infoDetails = manageUserService.getUserInfoDetails(infoDetails.getFirstName(), infoDetails.getLastName());
        return ResponseEntity.ok(new AppResponse( infoDetails, createSuccess()));
    }

    private ApiAppResponse createSuccess(){
        final ApiAppResponse appResponse = new ApiAppResponse();
        appResponse.setStatusCode(0);
        appResponse.setStatusMessage("Success");
        return  appResponse;
    }

}
