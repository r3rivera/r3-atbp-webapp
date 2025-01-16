package com.r3projects.atbp.services;

import com.r3projects.atbp.domain.AddressGeocode;
import com.r3projects.atbp.domain.UserAddressDetail;
import com.r3projects.atbp.domain.UserAddressGeoCode;
import com.r3projects.atbp.providers.GoogleGeocodeApiProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageUserTrackingService {
    private final GoogleGeocodeApiProvider geocodeApiProvider;

    public UserAddressGeoCode createUserTracker(final String userUuid,
                                                final UserAddressDetail start,
                                                final UserAddressDetail destination){

        final AddressGeocode startLocation = geocodeApiProvider.getGeoCode(generateAddress(start));
        final AddressGeocode destinationLocation = geocodeApiProvider.getGeoCode(generateAddress(destination));

        return new UserAddressGeoCode();
    }

    private String generateAddress(final UserAddressDetail addressDetail){
        final StringBuilder sb = new StringBuilder();
        sb.append(addressDetail.getStreet1());
        if(StringUtils.hasText(addressDetail.getStreet2())){
            sb.append(", ");
            sb.append(addressDetail.getStreet2());
        }
        sb.append(", ");
        sb.append(addressDetail.getCity());
        sb.append(", ");
        sb.append(addressDetail.getState());
        sb.append(" ");
        sb.append(addressDetail.getZipCode());
        if(StringUtils.hasText(addressDetail.getCountry())) {
            sb.append(", ");
            sb.append(addressDetail.getCountry());
        }
        return sb.toString();
    }
}
