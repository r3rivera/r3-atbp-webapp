package com.r3projects.atbp.handlers.jdbc.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBDateUtils {

    //private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final LocalDateTime convertDBDate(final Date dateTime){
        if(dateTime == null){
            return null;
        }
        return dateTime.toLocalDate().atStartOfDay();
    }
    public static final Date covertToDBDate(final LocalDateTime dateTime){
        if(dateTime== null){
           return null;
        }
        return Date.valueOf(dateTime.toLocalDate());
    }

    public static final LocalDateTime convertDBTimeStamp(final Timestamp timestamp){
        if(timestamp == null){
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    public static final Timestamp covertToDBTimeStamp(final LocalDateTime dateTime){
        if(dateTime== null){
            return null;
        }
        return Timestamp.valueOf(dateTime);
    }
}
