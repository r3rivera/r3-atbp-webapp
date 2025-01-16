package com.r3projects.atbp.exception;

public class ProviderException extends RuntimeException{
    private final int ERROR_STATUS = 8888;
    public ProviderException(final String message){
        super(message);
    }

    public ProviderException(final String message, Throwable ex){
        super(message, ex);
    }

    public int getErrorStatus(){
        return ERROR_STATUS;
    }
}
