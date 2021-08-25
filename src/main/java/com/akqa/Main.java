package com.akqa;

import com.akqa.model.MeetingBooking;
import com.akqa.service.MeetingService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        final List<String> lines = new ArrayList<>();

        String nextLine;
        while (!isStringBlank(nextLine = scanner.nextLine())) {
            lines.add(nextLine);
        }

        final MeetingService meetingService = new MeetingService();
        Map<LocalDate, List<MeetingBooking>> bookings = meetingService.process(lines);
        bookings.forEach((k, v) -> {
            System.out.println(k);
            for (MeetingBooking booking : v) {
                LocalTime startTime = booking.getStartTime().toLocalTime();
                LocalTime endTime = booking.getEndTime().toLocalTime();
                System.out.println(startTime + " " + endTime + " " + booking.getEmployeeId());
            }
        });
    }

    public static boolean isStringBlank(String str) {
        return str == null || str.isBlank();
    }
}


