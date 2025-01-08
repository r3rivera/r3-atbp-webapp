package com.r3projects.atbp.services;

import com.r3projects.atbp.model.UserCreationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManageUserService {

    public String createUserName(UserCreationRequest request){
        log.debug("Servicing user creation request...");
        return request.getUserInfo().getUserName();
    }
}
