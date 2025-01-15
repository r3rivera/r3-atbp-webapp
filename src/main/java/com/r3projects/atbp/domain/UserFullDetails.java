package com.r3projects.atbp.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserFullDetails {
    private UserInfoDetails userInfoDetails;
    private List<UserContactDetail> contactDetail;
}
