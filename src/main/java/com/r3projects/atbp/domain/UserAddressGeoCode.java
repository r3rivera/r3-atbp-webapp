package com.r3projects.atbp.domain;

import lombok.Data;

@Data
public class UserAddressGeoCode {
    private AddressGeocode startAddress;
    private AddressGeocode destinationAddress;
}
