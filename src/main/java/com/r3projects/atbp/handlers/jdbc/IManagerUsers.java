package com.r3projects.atbp.handlers.jdbc;

public interface IManagerUsers {
    String CREATE_NEW_USER_QRY = "INSERT INTO TRACKER.USER_DETAILS(FIRST_NAME,MIDDLE_NAME,LAST_NAME,CREATED_BY) VALUES (?,?,?,?)";
}
