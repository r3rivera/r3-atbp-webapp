package com.r3projects.atbp.controllers;

import com.r3projects.atbp.model.AppResponse;
import com.r3projects.atbp.model.UserTrackRequest;
import com.r3projects.atbp.services.ManageUserTrackingService;
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
@RequestMapping("/tracker")
public class UserManageTrackerController extends AppBaseController{

    private final ManageUserTrackingService trackingService;

    @PostMapping("/user")
    public ResponseEntity<AppResponse<String>> createUserTrack(@RequestBody UserTrackRequest trackRequest){
        log.info("Creating user tracking information...");
        this.trackingService.createUserTracker(trackRequest.getUserUuid(), trackRequest.getSourceAddress(), trackRequest.getDestinationAddress());
        final AppResponse<String> response = new AppResponse<>("Dummy", createSuccess());
        return ResponseEntity.ok(response);
    }
}
