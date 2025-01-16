package com.r3projects.atbp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserTrackingDetails extends DataDetails{
    private UserInfoDetails userInfo;
    private String sourceAddress;
    private String targetAddress;
    private AddressGeocode sourceLocation;
    private AddressGeocode targetLocation;
}
