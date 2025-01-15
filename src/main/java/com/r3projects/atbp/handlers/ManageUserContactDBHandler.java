package com.r3projects.atbp.handlers;

import com.r3projects.atbp.domain.DataDetails;
import com.r3projects.atbp.domain.UserContactDetail;
import com.r3projects.atbp.domain.UserFullDetails;
import com.r3projects.atbp.domain.UserInfoDetails;
import com.r3projects.atbp.handlers.jdbc.IManagerUsers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManageUserContactDBHandler extends BaseDBHandler implements IManagerUsers {

    private final JdbcTemplate jdbcTemplate;

    public DataDetails createUserContactDetailsBy(final UUID userUUID, final UserContactDetail contactDetail){
        return null;
    }

    public UserFullDetails retrieveUserContactDetailsBy(final String firstName, final String lastName){

        return jdbcTemplate.query(IManagerUsers.GET_USER_CONTACT_DETAILS_QRY, ps -> {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
        }, rs -> {
            final UserFullDetails details = new UserFullDetails();
            int rowCount = 0;
            final List<UserContactDetail> contactDetailList = new ArrayList<>();
            UserContactDetail contactDetail = null;
            while(rs.next()){
                if(rowCount == 0) {
                    final UserInfoDetails infoDetails = new UserInfoDetails();
                    infoDetails.setId(rs.getString(1));
                    infoDetails.setFirstName(rs.getString(2));
                    infoDetails.setMiddleName(rs.getString(3));
                    infoDetails.setLastName(rs.getString(4));
                    details.setUserInfoDetails(infoDetails);
                }
                final String media = rs.getString(5);
                final String type = rs.getString(6);
                if(media != null && type != null){
                    contactDetail = new UserContactDetail();
                    contactDetail.setMedia(media);
                    contactDetail.setType(type);
                    contactDetailList.add(contactDetail);
                }
                rowCount++;
            }
            if(!contactDetailList.isEmpty()){
                details.setContactDetail(contactDetailList);
            }
            return details;
        });
    }
}
