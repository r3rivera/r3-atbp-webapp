package com.r3projects.atbp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoDetails extends DataDetails{
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;
}
