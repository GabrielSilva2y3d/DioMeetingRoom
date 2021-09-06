package com.dio.crud.meetingroom.meetingRoom.exception;

import java.util.Date;
import lombok.Data;

@Data
public class ErrorDetails {
    
    public ErrorDetails(Date date) {
    }
    public ErrorDetails(Date date, String message2, String description) {
    }
    private Date timeStamp;
    private String message;
    private String details;
}
