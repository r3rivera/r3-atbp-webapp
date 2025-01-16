package com.r3projects.atbp.controllers;

import com.r3projects.atbp.domain.UserAddressGeoCode;
import com.r3projects.atbp.domain.UserTrackingDetails;
import com.r3projects.atbp.model.AppResponse;
import com.r3projects.atbp.model.UserTrackRequest;
import com.r3projects.atbp.services.ManageUserTrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tracker")
public class UserManageTrackerController extends AppBaseController{

    private final ManageUserTrackingService trackingService;

    @PostMapping("/user")
    public ResponseEntity<AppResponse<UserAddressGeoCode>> createUserTrack(@RequestBody UserTrackRequest trackRequest){
        log.info("Creating user tracking information...");
        final UserAddressGeoCode result = this.trackingService
                .createUserTracker(trackRequest.getUserUuid(),
                        trackRequest.getSourceAddress(), trackRequest.getDestinationAddress());
        final AppResponse<UserAddressGeoCode> response = new AppResponse<>(result, createSuccess());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<AppResponse<List<UserTrackingDetails>>> getAllTracks(){
        log.info("Getting all tracking information...");
        final List<UserTrackingDetails> details = this.trackingService.getAllActiveTracks();
        final AppResponse<List<UserTrackingDetails>> response = new AppResponse<>(details, createSuccess());
        return ResponseEntity.ok(response);
    }
}
