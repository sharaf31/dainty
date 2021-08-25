package com.akqa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@EqualsAndHashCode(of = "requestDateTime")
public class MeetingRequest implements Comparable<MeetingRequest> {

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final LocalDateTime requestDateTime;
    private final MeetingBooking booking;

    public MeetingRequest(String requestDate,
                          String requestTime,
                          MeetingBooking booking) {
        this.booking = booking;
        this.requestDateTime = LocalDateTime.parse(requestDate + " " + requestTime, DATE_TIME_FORMATTER);
    }

    @Override
    public int compareTo(MeetingRequest mr) {
        return this.requestDateTime.compareTo(mr.requestDateTime);
    }
}
