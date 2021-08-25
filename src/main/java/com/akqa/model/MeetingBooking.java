package com.akqa.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MeetingBooking implements Comparable<MeetingBooking> {

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final long duration;
    private final String employeeId;

    public MeetingBooking(String bookingDate, String bookingTime, long duration, String employeeId) {
        this.startTime = LocalDateTime.parse(bookingDate + " " + bookingTime, DATE_TIME_FORMATTER);
        this.endTime = startTime.plusHours(duration);
        this.duration = duration;
        this.employeeId = employeeId;
    }

    @Override
    public int compareTo(MeetingBooking b) {
        if (this.timeOverlap(b)) {
            return 0;
        }
        return this.startTime.compareTo(b.startTime);
    }

    public boolean timeOverlap(MeetingBooking b) {
        return this.startTime.isBefore(b.endTime) && this.endTime.isAfter(b.startTime);
    }
}
