package com.r3projects.atbp.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.r3projects.atbp.domain.AddressGeocode;
import com.r3projects.atbp.exception.ProviderException;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoogleGeocodeApiProvider {
    private final GeoApiContext geoApiContext;
    private final Gson gson;
    private GoogleGeocodeApiProvider(@Value("${r3app.google.map.api.key}") final String apiKey){
        this.geoApiContext = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public AddressGeocode getGeoCode(final String locationAddress){
        try {
           final GeocodingResult[] response = GeocodingApi.geocode(this.geoApiContext,locationAddress).await();
           if(response.length > 0){
               return this.gson.fromJson(String.valueOf(response[0].geometry.location), AddressGeocode.class);
           }
            return null;
        } catch (Exception e) {
            log.error("Error processing the google api call!");
            throw new ProviderException(e.getMessage());
        }
    }

    @PreDestroy
    public void cleanup(){
        if(this.geoApiContext != null){
            this.geoApiContext.shutdown();
        }
    }


}
