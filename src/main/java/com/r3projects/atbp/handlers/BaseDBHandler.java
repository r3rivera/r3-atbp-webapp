package com.r3projects.atbp.handlers;

import java.time.LocalDateTime;

public class BaseDBHandler {
    protected LocalDateTime getSystemDatetime(){
        return LocalDateTime.now();
    }
}
