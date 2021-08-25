package com.akqa.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class OfficeHours {
    private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    private final LocalTime officeStartTime;
    private final LocalTime officeEndTime;

    public OfficeHours(String officeStartTime, String officeEndTime) {
        this.officeStartTime = LocalTime.parse(officeStartTime, TIME_FORMATTER);
        this.officeEndTime = LocalTime.parse(officeEndTime, TIME_FORMATTER);
    }

    public boolean within(LocalDateTime start, LocalDateTime end) {
        return start.toLocalTime().compareTo(officeStartTime) >= 0 &&
                end.toLocalTime().compareTo(officeEndTime) <= 0;
    }

}
