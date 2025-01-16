package com.r3projects.atbp.handlers.jdbc;

public interface IManagerUsers {
    String CREATE_NEW_USER_QRY = "INSERT INTO USER_DETAILS(FIRST_NAME,MIDDLE_NAME,LAST_NAME,CREATED_BY) VALUES (?,?,?,?)";

    String GET_USER_DETAIL_QRY = "SELECT USER_UUID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE FROM " +
            "USER_DETAILS WHERE FIRST_NAME = ? AND LAST_NAME = ?";
    String GET_USER_CONTACT_DETAILS_QRY = "SELECT USERINFO.USER_UUID, USERINFO.FIRST_NAME, USERINFO.MIDDLE_NAME, USERINFO.LAST_NAME, CONTACTS.MEDIA, CONTACTS.CONTACT_TYPE FROM " +
            "USER_DETAILS AS USERINFO LEFT JOIN USER_CONTACT_DETAILS AS CONTACTS ON USERINFO.USER_UUID = CONTACTS.USER_UUID " +
            "WHERE USERINFO.FIRST_NAME = ? AND USERINFO.LAST_NAME = ?";

    String CREATE_NEW_USER_TRACKING_QRY = "INSERT INTO USER_GPS_DETAILS(USER_UUID, START_ADDR_LOC, START_LAT, START_LON, DESTI_ADDR_LOC, DESTI_LAT,DESTI_LON,CREATED_BY) " +
            "VALUES (?,?,?,?,?,?,?,?)";

    String GET_ALL_USER_TRACKING_QRY = "SELECT GPS.USER_GPS_UUID, USERINFO.USER_UUID, USERINFO.FIRST_NAME, USERINFO.MIDDLE_NAME, USERINFO.LAST_NAME, " +
            "GPS.START_ADDR_LOC, GPS.START_LAT, GPS.START_LON, GPS.DESTI_ADDR_LOC, GPS.DESTI_LAT, GPS.DESTI_LON, GPS.CREATED_BY, GPS.CREATED_DATE FROM " +
            "USER_DETAILS AS USERINFO JOIN USER_GPS_DETAILS AS GPS ON USERINFO.USER_UUID = GPS.USER_UUID";

    String DELETE_TRACKING_RECORD_QRY = "DELETE FROM USER_GPS_DETAILS WHERE USER_GPS_UUID IN (?)";

}
