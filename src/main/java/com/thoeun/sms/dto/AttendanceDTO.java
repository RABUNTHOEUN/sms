package com.thoeun.sms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDTO {
    private Long studentId;
    private Long classId;
    private String date;
    private String status;
    private String remarks;
}