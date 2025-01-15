package com.r3projects.atbp.services;

import com.r3projects.atbp.domain.DataDetails;
import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.handlers.ManageUserDBHandler;
import com.r3projects.atbp.model.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageUserService {

    private final ManageUserDBHandler manageUserDBHandler;
    public DataDetails createUserName(UserRequest request){
        log.debug("Servicing user creation request...");
        return manageUserDBHandler.createUserDetails(request.getUserInfo());
    }

    public UserInfoDetails getUserInfoDetails(final String firstName, final String lastName){
        log.debug("Servicing user query request...");
        return manageUserDBHandler.getUserInfoDetailsBy(firstName, lastName);
    }

}
