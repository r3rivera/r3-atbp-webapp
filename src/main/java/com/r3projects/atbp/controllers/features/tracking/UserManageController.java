package com.r3projects.atbp.controllers.features.tracking;

import com.r3projects.atbp.controllers.AppBaseController;
import com.r3projects.atbp.domain.DataDetails;
import com.r3projects.atbp.domain.UserAppCredentialDetails;
import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.model.AppResponse;
import com.r3projects.atbp.model.UserRequest;
import com.r3projects.atbp.services.ManageUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/manage")
public class UserManageController extends AppBaseController {

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

    @GetMapping("/users")
    public ResponseEntity<AppResponse<List<UserAppCredentialDetails>>> getAllActiveUserDetails(){
        log.info("Querying user information...");
        List<UserAppCredentialDetails> userList = manageUserService.getAllActiveUser();
        return ResponseEntity.ok(new AppResponse( userList, createSuccess()));
    }

}
