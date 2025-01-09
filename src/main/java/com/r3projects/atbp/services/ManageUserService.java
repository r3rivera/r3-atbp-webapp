package com.r3projects.atbp.services;

import com.r3projects.atbp.domain.DataDetails;
import com.r3projects.atbp.handlers.ManageUserDBHandler;
import com.r3projects.atbp.model.UserCreationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageUserService {

    private final ManageUserDBHandler manageUserDBHandler;
    public DataDetails createUserName(UserCreationRequest request){
        log.debug("Servicing user creation request...");
        return manageUserDBHandler.createUserDetails(request.getUserInfo());
    }
}
