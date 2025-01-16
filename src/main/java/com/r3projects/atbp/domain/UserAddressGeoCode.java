package com.r3projects.atbp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAddressGeoCode extends DataDetails{
    private AddressGeocode startAddress;
    private AddressGeocode destinationAddress;
}
