package com.r3projects.atbp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAddressDetail extends DataDetails{
    private String street1;
    private String street2;
    private String city;
    private String zipCode;
    private String state;
    private String country;
    private AddressGeocode location;
}
