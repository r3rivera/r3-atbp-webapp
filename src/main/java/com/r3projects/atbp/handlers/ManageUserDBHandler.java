package com.r3projects.atbp.handlers;

import com.r3projects.atbp.domain.DataDetails;
import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.exception.JDBCException;
import com.r3projects.atbp.handlers.jdbc.IManagerUsers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageUserDBHandler extends BaseDBHandler implements IManagerUsers {

    private final JdbcTemplate jdbcTemplate;

    public DataDetails createUserDetails(final UserInfoDetails infoDetails){
        log.info("Start handling the creation of new user!");

        try {
            final int result = this.jdbcTemplate.update(CREATE_NEW_USER_QRY, ps -> {
                ps.setString(1, infoDetails.getFirstName());
                ps.setString(2, infoDetails.getMiddleName());
                ps.setString(3, infoDetails.getLastName());
                ps.setString(4, infoDetails.getUserName());
            });
            log.debug("Handling creation of new user :: {} status", result);
            final DataDetails details = new DataDetails();
            details.setCreatedBy(infoDetails.getUserName());
            details.setCreatedDate(getSystemDatetime());
            return details;

        }catch(final Exception ex){
            log.error("Error with DB process!", ex);
            throw new JDBCException("User Creation Error");
        }
    }
}
