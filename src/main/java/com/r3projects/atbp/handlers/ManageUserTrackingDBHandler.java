package com.r3projects.atbp.handlers;

import com.r3projects.atbp.domain.AddressGeocode;
import com.r3projects.atbp.domain.DataDetails;
import com.r3projects.atbp.exception.JDBCException;
import com.r3projects.atbp.handlers.jdbc.IManagerUsers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageUserTrackingDBHandler extends BaseDBHandler implements IManagerUsers {

    private final JdbcTemplate jdbcTemplate;

    @Transactional("DBTxnManager")
    public DataDetails createUserTracking(String userUuid, String startAddress, AddressGeocode startLoc,
                                          String destinationAddress, AddressGeocode destLoc, String updatedBy){
        log.info("Start handling the creation of new user!");
        try {
            final KeyHolder trackingIdKey = new GeneratedKeyHolder();
            final int result = this.jdbcTemplate.update(conn -> {
                final PreparedStatement ps = conn.prepareStatement(IManagerUsers.CREATE_NEW_USER_TRACKING_QRY, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, UUID.fromString(userUuid));
                ps.setString(2, startAddress);
                ps.setDouble(3, startLoc.getLat());
                ps.setDouble(4, startLoc.getLng());
                ps.setString(5, destinationAddress);
                ps.setDouble(6, destLoc.getLat());
                ps.setDouble(7, destLoc.getLng());
                ps.setString(8, updatedBy);
                return ps;
            }, trackingIdKey);
            final String trackingId = trackingIdKey.getKeys().get("user_gps_uuid").toString();
            log.debug("Handling creation of new tracking entry :: trackingId is {}, SQL update with {} status",trackingId, result);

            final DataDetails details = new DataDetails();
            details.setId(trackingId);
            details.setCreatedBy(updatedBy);
            details.setCreatedDate(getSystemDatetime());
            return details;

        }catch(final Exception ex){
            log.error("Error with DB process!", ex);
            throw new JDBCException("User Tracking Creation Error");
        }
    }

}
