package com.r3projects.atbp.handlers.jdbc.domain;

import com.r3projects.atbp.domain.UserAppCredentialDetails;
import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.handlers.jdbc.utils.DBDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserAppCredentialRowMapper implements RowMapper<UserAppCredentialDetails> {
    @Override
    public UserAppCredentialDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        log.info("Processing User ResultSet!");
        final UserAppCredentialDetails appCredentialDetails = new UserAppCredentialDetails();

        final UserInfoDetails infoDetails = new UserInfoDetails();
        infoDetails.setId(rs.getString(1));
        infoDetails.setFirstName(rs.getString(2));
        infoDetails.setMiddleName(rs.getString(3));
        infoDetails.setLastName(rs.getString(4));
        infoDetails.setCreatedBy(rs.getString(5));
        infoDetails.setCreatedDate(DBDateUtils.convertDBTimeStamp(rs.getTimestamp(6)));

        appCredentialDetails.setUserInfoDetails(infoDetails);
        appCredentialDetails.setId(rs.getString(7));
        appCredentialDetails.setUserName(rs.getString(8));
        return appCredentialDetails;
    }
}
