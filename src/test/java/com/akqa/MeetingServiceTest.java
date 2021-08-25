package com.akqa;

import com.akqa.model.MeetingBooking;
import com.akqa.service.MeetingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class MeetingServiceTest {

    private MeetingService meetingService;

    private List<String> defaultInputList;
    private List<String> officeOverLapsList;
    private List<String> singleBookingList;
    private LocalDate keyLocalDate;
    private LocalTime valueStartTime;
    private LocalTime valueEndTime;

    @Before
    public void setUp() {
        meetingService = new MeetingService();
        defaultInputList = List.of("0900 1730", "2011-03-16 12:34:56 EMP002", "2011-03-21 09:00 2",
                "2011-03-16 09:28:23 EMP003", "2011-03-22 14:00 2", "2011-03-17 10:17:06 EMP004", "2011-03-22 16:00 1");
        officeOverLapsList = List.of("0900 1730", "2011-03-17 10:17:06 EMP004", "2011-03-22 06:00 1",
                "2011-03-15 17:29:12 EMP005", "2011-03-21 16:00 3");
        singleBookingList = List.of("0900 1730", "2011-03-17 10:17:06 EMP001", "2011-03-21 09:00 2",
                "2011-03-16 12:34:56 EMP002", "2011-03-21 09:00 4", "2011-03-16 15:20:00 EMP003", "2011-03-21 10:00 2");
        keyLocalDate = LocalDate.parse("2011-03-21");
        valueStartTime = LocalTime.parse("09:00");
        valueEndTime = LocalTime.parse("13:00");
    }

    // TestCase to Count the Total number of Booking days
    @Test
    public void testProcessBookingDays() {
        Map<LocalDate, List<MeetingBooking>> results = meetingService.process(defaultInputList);
        Assert.assertSame(results.entrySet().size(), 2);
    }

    // TestCase to Count the Total number of Bookings
    @Test
    public void testProcessTotalBooking() {
        Map<LocalDate, List<MeetingBooking>> results = meetingService.process(defaultInputList);
        Assert.assertSame(results.values().stream().mapToInt(List::size).sum(), 3);
    }

    // Edge TestCase to Count if all the meeting requests before or after office hours
    @Test
    public void testAllOfficeOverLaps() {
        Map<LocalDate, List<MeetingBooking>> results = meetingService.process(officeOverLapsList);
        Assert.assertTrue(results.isEmpty());
    }

    // Edge TestCase to Count if only Single Meetings can be made if rest are meeting cannot be booked
    @Test
    public void testMeetingOverLaps() {
        Map<LocalDate, List<MeetingBooking>> results = meetingService.process(singleBookingList);
        Assert.assertTrue(results.keySet().size() == 1 &&
                results.values().stream().mapToInt(List::size).sum() == 1);

        Assert.assertTrue(results.containsKey(keyLocalDate));

        MeetingBooking testBooking = results.get(keyLocalDate).get(0);
        Assert.assertTrue(testBooking.getEmployeeId().equals("EMP002")
                && testBooking.getDuration() == 4
                && testBooking.getStartTime().toLocalTime().equals(valueStartTime)
                && testBooking.getEndTime().toLocalTime().equals(valueEndTime));
    }


}