package com.r3projects.atbp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserAppCredentialDetails extends UserFullDetails{
    private String userName;
    private String id;
}
