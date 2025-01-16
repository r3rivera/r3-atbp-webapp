package com.r3projects.atbp.handlers.jdbc.domain;

import com.r3projects.atbp.domain.AddressGeocode;
import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.domain.UserTrackingDetails;
import com.r3projects.atbp.handlers.jdbc.utils.DBDateUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTrackingDetailRowMapper implements RowMapper<UserTrackingDetails> {
    @Override
    public UserTrackingDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        final UserTrackingDetails trackingDetails = new UserTrackingDetails();
        trackingDetails.setId(rs.getString(1));

        final UserInfoDetails userInfo = new UserInfoDetails();
        userInfo.setId(rs.getString(2));
        userInfo.setFirstName(rs.getString(3));
        userInfo.setMiddleName(rs.getString(4));
        userInfo.setLastName(rs.getString(5));
        trackingDetails.setUserInfo(userInfo);

        trackingDetails.setSourceAddress(rs.getString(6));
        final AddressGeocode sourceGeo = new AddressGeocode();
        sourceGeo.setLat(rs.getDouble(7));
        sourceGeo.setLng(rs.getDouble(8));
        trackingDetails.setSourceLocation(sourceGeo);

        trackingDetails.setTargetAddress(rs.getString(9));
        final AddressGeocode targetGeo = new AddressGeocode();
        targetGeo.setLat(rs.getDouble(10));
        targetGeo.setLng(rs.getDouble(11));
        trackingDetails.setTargetLocation(targetGeo);

        trackingDetails.setCreatedBy(rs.getString(12));
        trackingDetails.setCreatedDate(DBDateUtils.convertDBTimeStamp(rs.getTimestamp(13)));
        return trackingDetails;
    }
}
