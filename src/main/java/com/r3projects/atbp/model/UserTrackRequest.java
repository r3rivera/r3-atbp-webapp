package com.r3projects.atbp.model;

import com.r3projects.atbp.domain.UserAddressDetail;
import lombok.Data;

@Data
public class UserTrackRequest {
    private String userUuid;
    private UserAddressDetail sourceAddress;
    private UserAddressDetail destinationAddress;
}
