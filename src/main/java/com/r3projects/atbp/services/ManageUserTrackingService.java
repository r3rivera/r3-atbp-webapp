package com.r3projects.atbp.services;

import com.r3projects.atbp.domain.*;
import com.r3projects.atbp.handlers.ManageUserTrackingDBHandler;
import com.r3projects.atbp.providers.GoogleGeocodeApiProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageUserTrackingService {

    private final GoogleGeocodeApiProvider geocodeApiProvider;
    private final ManageUserTrackingDBHandler trackingDBHandler;

    public UserAddressGeoCode createUserTracker(final String userUuid,
                                                final UserAddressDetail start,
                                                final UserAddressDetail destination){
        log.debug("Servicing user tracking request...");
        final String startStringAdd = start.generateAddress();
        final String destStringAdd = destination.generateAddress();
        final AddressGeocode startLocation = geocodeApiProvider.getGeoCode(startStringAdd);
        final AddressGeocode destinationLocation = geocodeApiProvider.getGeoCode(destStringAdd);
        final DataDetails result = trackingDBHandler.createUserTracking(userUuid,
                startStringAdd, startLocation,destStringAdd, destinationLocation, "System");

        final UserAddressGeoCode addressGeoCode = new UserAddressGeoCode();
        addressGeoCode.setId(result.getId());
        addressGeoCode.setStartAddress(startLocation);
        addressGeoCode.setDestinationAddress(destinationLocation);
        addressGeoCode.setCreatedBy(result.getCreatedBy());
        return addressGeoCode;
    }

    public List<UserTrackingDetails> getAllActiveTracks(){
        log.debug("Servicing getting all tracking request...");
        return trackingDBHandler.getAllActiveTracking();
    }


}
