package com.r3projects.atbp.handlers.jdbc.domain;

import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.handlers.jdbc.utils.DBDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserInfoDetailRowMapper implements RowMapper<UserInfoDetails> {
    @Override
    public UserInfoDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        final UserInfoDetails details = new UserInfoDetails();
        details.setId(rs.getString(1));
        details.setFirstName(rs.getString(2));
        details.setMiddleName(rs.getString(3));
        details.setLastName(rs.getString(4));
        details.setCreatedBy(rs.getString(5));
        details.setCreatedDate(DBDateUtils.convertDBTimeStamp(rs.getTimestamp(6)));
        details.setModifiedBy(rs.getString(7));
        details.setModifiedDate(DBDateUtils.convertDBTimeStamp(rs.getTimestamp(8)));
        return details;
    }
}
