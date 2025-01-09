package com.r3projects.atbp.exception;

public class JDBCException extends RuntimeException{

    private final int ERROR_STATUS = 9999;
    public JDBCException(final String message){
        super(message);
    }

    public JDBCException(final String message, Throwable ex){
        super(message, ex);
    }

    public int getErrorStatus(){
        return ERROR_STATUS;
    }
}
