package com.r3projects.atbp.model;

import com.r3projects.atbp.domain.UserContactDetail;
import com.r3projects.atbp.domain.UserInfoDetails;
import lombok.Data;

@Data
public class UserRequest {
    private UserInfoDetails userInfo;
    private UserContactDetail contactDetail;
}
