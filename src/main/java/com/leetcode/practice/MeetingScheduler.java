package com.leetcode.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * You’re building a feature for an internal tool
 * where employees can schedule 1:1 meetings.
 * Each person has a calendar of their existing meetings,
 * and you need to suggest available time slots for a new meeting.
 *
 *  2.  Each person has a working day from 09:00 to 17:00.
 3.  All times are in 24-hour "HH:mm" format, aligned to 30-minute intervals (e.g. 09:00, 09:30, 10:00, etc.).
 4.  getAvailableSlots() returns all possible start times where a meeting of given duration can be scheduled.
 5.  The scheduler should handle multiple people independently.

 🕒 Time Plan:
 •   10 min: Plan data structure (Map + sorted list or TreeSet).
 •   30 min: Implement core logic.
 •   10 min: Write helper methods for time parsing and formatting.
 •   10 min: Optional tests and cleanup.

 */
public class MeetingScheduler {
    Map<String, TreeSet<TimeSlot>> storage;

    public MeetingScheduler () {
        this.storage = new HashMap<>();
    }

    private static class TimeSlot {
        int start;
        int end;

        public TimeSlot(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        public static TimeSlot allDay() {
            return new TimeSlot(toMinutes("09:00"), toMinutes("17:00"));
        }

        public List<String> splitBy(int meetingDurationMinutes) {
            int time = start;
            List<String> startTimes = new ArrayList<>();
            while((time + meetingDurationMinutes) <= end) {
                startTimes.add(toHourRepresentation(time));
                time += meetingDurationMinutes;
            }
            return startTimes;
        }
    }



    public void addMeeting(String person, String startTime, String endTime){ // e.g. "09:00", "09:30"
        var availableSlots = storage.computeIfAbsent(person, k -> getAllDay());
        int start = toMinutes(startTime);
        int end = toMinutes(endTime);
        if (start == end) {
            return;
        }
        TimeSlot availableSlot = null;
        for (var slot : availableSlots) {
            if (slot.start <= start && end <= slot.end) {
                availableSlot = slot;
                break;
            }
        }
        if(availableSlot == null) {
            return;
        }
        availableSlots.remove(availableSlot);
        if (availableSlot.start == start) {
            availableSlots.add(new TimeSlot(end, availableSlot.end));
        } else if (availableSlot.end == end) {
            availableSlots.add(new TimeSlot(availableSlot.start, start));
        } else {
            availableSlots.add(new TimeSlot(availableSlot.start, start));
            availableSlots.add(new TimeSlot(end, availableSlot.end));
        }

    }

    public List<String> getAvailableSlots(String person, int meetingDurationMinutes) {
        return storage.computeIfAbsent(person, k -> getAllDay())
            .stream()
            .map(t -> t.splitBy(meetingDurationMinutes))
            .flatMap(Collection::stream)
            .toList();

    }

    private static TreeSet<TimeSlot> getAllDay() {
        var allDay = new TreeSet<TimeSlot>(Comparator.comparingInt(o -> o.start));
        allDay.add(TimeSlot.allDay());
        return allDay;
    }

    private static int toMinutes(String time) {
        var hour = Integer.parseInt(time.substring(0, 2));
        var minute = Integer.parseInt(time.substring(3, 5));
        return hour * 60 + minute;
    }

    private static String toHourRepresentation(int time) {
        var hour = time / 60;
        int minutes = time % 60;
        return "%02d:%02d".formatted(hour, minutes);
    }

    public static void main(String[] args) {
        MeetingScheduler scheduler = new MeetingScheduler();
        scheduler.addMeeting("Alice", "09:00", "10:00");
        scheduler.addMeeting("Alice", "10:30", "11:30");
        scheduler.addMeeting("Alice", "12:00", "13:00");

        List<String> availableSlots = scheduler.getAvailableSlots("Alice", 30);
        System.out.println("Available slots for Alice: " + availableSlots);

        // Add more meetings and check available slots
        scheduler.addMeeting("Bob", "09:30", "10:30");
        List<String> bobAvailableSlots = scheduler.getAvailableSlots("Bob", 30);
        System.out.println("Available slots for Bob: " + bobAvailableSlots);
    }
}
