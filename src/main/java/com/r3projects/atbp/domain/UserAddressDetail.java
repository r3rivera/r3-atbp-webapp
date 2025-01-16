package com.r3projects.atbp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

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

    public String generateAddress(){
        final StringBuilder sb = new StringBuilder();
        sb.append(street1);
        if(StringUtils.hasText(street2)){
            sb.append(", ");
            sb.append(street2);
        }
        sb.append(", ");
        sb.append(city);
        sb.append(", ");
        sb.append(state);
        sb.append(" ");
        sb.append(zipCode);
        if(StringUtils.hasText(country)) {
            sb.append(", ");
            sb.append(country);
        }
        return sb.toString();
    }
}
