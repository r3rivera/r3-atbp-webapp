package com.r3projects.atbp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserContactDetail extends DataDetails{
    private String media;
    private String type;
}
