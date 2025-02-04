package com.r3projects.atbp.handlers;

import com.r3projects.atbp.domain.DataDetails;
import com.r3projects.atbp.domain.UserAppCredentialDetails;
import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.exception.JDBCException;
import com.r3projects.atbp.handlers.jdbc.IManagerUsers;
import com.r3projects.atbp.handlers.jdbc.domain.UserAppCredentialRowMapper;
import com.r3projects.atbp.handlers.jdbc.domain.UserInfoDetailRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageUserDBHandler extends BaseDBHandler implements IManagerUsers {

    private final JdbcTemplate jdbcTemplate;

    @Transactional("DBTxnManager")
    public DataDetails createUserDetails(final UserInfoDetails infoDetails){
        log.info("Start handling the creation of new user!");
        try {
            final KeyHolder userIdKey = new GeneratedKeyHolder();
            final int result = this.jdbcTemplate.update(conn -> {
                final PreparedStatement ps = conn.prepareStatement(IManagerUsers.CREATE_NEW_USER_QRY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, infoDetails.getFirstName());
                ps.setString(2, infoDetails.getMiddleName());
                ps.setString(3, infoDetails.getLastName());
                ps.setString(4, infoDetails.getUserName());
                return ps;
            }, userIdKey);
            final String userId = userIdKey.getKeys().get("user_uuid").toString();
            log.debug("Handling creation of new user :: userId is {}, SQL update with {} status",userId, result);

            final DataDetails details = new DataDetails();
            details.setId(userId);
            details.setCreatedBy(infoDetails.getUserName());
            details.setCreatedDate(getSystemDatetime());
            return details;

        }catch(final Exception ex){
            log.error("Error with DB process!", ex);
            throw new JDBCException("User Creation Error");
        }
    }

    public List<UserAppCredentialDetails> getAllActiveUser(){
        log.info("Start getting all active user info!");
        try{
            final List<UserAppCredentialDetails> userAppCredentialDetails =
                    this.jdbcTemplate.query(IManagerUsers.GET_ALL_ACTIVE_USER_QRY, new UserAppCredentialRowMapper());
            return userAppCredentialDetails;
        }catch(final Exception ex){
            log.error("Error with DB process!", ex);
            throw new JDBCException("User Query Error");
        }
    }


    public UserInfoDetails getUserInfoDetailsBy(final String firstName, final String lastName){
        log.info("Start getting user info by first and last name!");
        try{
            final List<UserInfoDetails> userInfoDetailsList = this.jdbcTemplate
                    .query(IManagerUsers.GET_USER_DETAIL_QRY, ps -> {
                        ps.setString(1, firstName);
                        ps.setString(2, lastName);
                    }, new UserInfoDetailRowMapper());
            if(userInfoDetailsList.isEmpty()){
                log.info("No records found for a given query!");
               return null;
            }
            return userInfoDetailsList.get(0);
        }catch(final Exception ex){
            log.error("Error with DB process!", ex);
            throw new JDBCException("User Query Error");
        }
    }
}
