package com.akqa.service;

import com.akqa.model.MeetingBooking;
import com.akqa.model.MeetingRequest;
import com.akqa.model.OfficeHours;

import java.time.LocalDate;
import java.util.*;

public class MeetingService {

    public Map<LocalDate, List<MeetingBooking>> process(List<String> lines) {

        if (lines == null || lines.isEmpty()) {
            return Collections.emptyMap();
        }

        final Iterator<String> it = lines.iterator();

        // Extracting Office Hours from Input
        String officeHoursLine = it.next();
        String[] officeHoursParts = officeHoursLine.split(" ");
        String officeStartTime = officeHoursParts[0];
        String officeEndTime = officeHoursParts[1];

        // Initializing Office Hours Per Input
        OfficeHours officeHours = new OfficeHours(officeStartTime, officeEndTime);

        final List<MeetingRequest> requests = new ArrayList<>();

        while (it.hasNext()) {
            // 1st Line for Request reading 2nd Line for Booking Reading
            String request = it.next();
            String booking = it.next();

            String[] requestParts = request.split(" ");
            String[] bookingParts = booking.split(" ");

            String requestDate = requestParts[0];
            String requestTime = requestParts[1];
            String employeeId = requestParts[2];

            String bookingDate = bookingParts[0];
            String bookingTime = bookingParts[1];
            long meetingDuration = Long.parseLong(bookingParts[2]);

            //Initialize booking and Requests objects
            MeetingBooking meetingBooking = new MeetingBooking(bookingDate, bookingTime, meetingDuration, employeeId);
            MeetingRequest meetingRequest = new MeetingRequest(requestDate, requestTime, meetingBooking);

            // Validation for meeting for office timings and storing to DS.
            if (officeHours.within(meetingBooking.getStartTime(), meetingBooking.getEndTime())) {
                requests.add(meetingRequest);
            }
        }

        final SortedSet<MeetingBooking> bookings = new TreeSet<>();

        //comparator will not add if overlaps within meting booking time.
        requests.stream().sorted().forEach(r -> {
            bookings.add(r.getBooking());
        });

        final LinkedHashMap<LocalDate, List<MeetingBooking>> result = new LinkedHashMap<>();

        // group the booking by date storing into DS.
        for (MeetingBooking booking : bookings) {
            final LocalDate startDate = booking.getStartTime().toLocalDate();
            final List<MeetingBooking> meetingBookings = result.computeIfAbsent(startDate, k -> new ArrayList<>());

            meetingBookings.add(booking);
        }

        return result;
    }
}
