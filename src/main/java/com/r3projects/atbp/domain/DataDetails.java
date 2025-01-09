package com.r3projects.atbp.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DataDetails {
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
}
