package Utilities;

import java.util.Calendar;

/**
 * A simple wrapper around {@link java.util.Calendar} that provides convenient
 * accessor methods for individual date/time components.
 *
 * <p>This class exposes getters for year, month, day-of-month, hour (24-hour),
 * minute, second and day-of-week. It also allows retrieval and replacement of
 * the underlying {@code Calendar} instance.</p>
 *
 * <p>Note: This class does not enforce non-nullity of the wrapped Calendar.
 * If a null value is provided to the constructor or via {@link #setScheduledDay},
 * subsequent calls to the accessor methods will result in a {@link NullPointerException}.</p>
 */
public class ScheduledDay {
    /**
     * The wrapped Calendar instance representing the scheduled date/time.
     * May be null if not initialized.
     */
    private Calendar scheduledDay;

    /**
     * Constructs a ScheduledDay that wraps the provided {@link Calendar}.
     *
     * @param scheduledDay the Calendar instance to wrap; may be null. If null,
     *                     the internal field is left unset and accessor methods
     *                     will throw {@link NullPointerException}.
     */
    public ScheduledDay(Calendar scheduledDay) {
        if (scheduledDay != null) {
            this.scheduledDay = scheduledDay;
        }
    }

    /**
     * Returns the year component of the wrapped calendar.
     *
     * @return the year as an int (e.g., 2026)
     * @throws NullPointerException if the wrapped Calendar is null
     */
    public int getYear() {
        return scheduledDay.get(Calendar.YEAR);
    }

    /**
     * Returns the month component of the wrapped calendar.
     *
     * @return the month as an int (0-11, where 0 = January)
     * @throws NullPointerException if the wrapped Calendar is null
     */
    public int getMonth() {
        return scheduledDay.get(Calendar.MONTH);
    }

    /**
     * Returns the day of month component of the wrapped calendar.
     *
     * @return the day of month as an int (1-31)
     * @throws NullPointerException if the wrapped Calendar is null
     */
    public int getDay() {
        return scheduledDay.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns the hour of day (24-hour clock) component of the wrapped calendar.
     *
     * @return the hour of day as an int (0-23)
     * @throws NullPointerException if the wrapped Calendar is null
     */
    public int getHour() {
        return scheduledDay.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Returns the minute component of the wrapped calendar.
     *
     * @return the minutes as an int (0-59)
     * @throws NullPointerException if the wrapped Calendar is null
     */
    public int getMinute() {
        return scheduledDay.get(Calendar.MINUTE);
    }

    /**
     * Returns the second component of the wrapped calendar.
     *
     * @return the seconds as an int (0-59)
     * @throws NullPointerException if the wrapped Calendar is null
     */
    public int getSecond() {
        return scheduledDay.get(Calendar.SECOND);
    }

    /**
     * Returns the day of week component of the wrapped calendar.
     *
     * @return the day of week as an int (1-7, where 1 = Sunday)
     * @throws NullPointerException if the wrapped Calendar is null
     */
    public int getDayOfWeek() {
        return scheduledDay.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Replaces the year component of the scheduled day.
     *
     * @param year the new year to represent
     */
    public void setYear(int year) {
        scheduledDay.set(Calendar.YEAR, year);
    }

    /**
     * Replaces the month component of the scheduled day.
     *
     * @param month the new month to represent
     */
    public void setMonth(int month) {
        scheduledDay.set(Calendar.MONTH, month);
    }

    /**
     * Replaces the day component of the scheduled day.
     *
     * @param day the new day to represent
     */
    public void setDay(int day) {
        scheduledDay.set(Calendar.DAY_OF_MONTH, day);
    }

    /**
     * Replaces the hour component of the scheduled day.
     *
     * @param hour the new hour to represent
     */
    public void setHour(int hour) {
        scheduledDay.set(Calendar.HOUR_OF_DAY, hour);
    }

    /**
     * Replaces the minute component of the scheduled day.
     *
     * @param minute the new minute to represent
     */
    public void setMinute(int minute) {
        scheduledDay.set(Calendar.MINUTE, minute);
    }

    /**
     * Replaces the second component of the scheduled day.
     *
     * @param second the new second to represent
     */
    public void setSecond(int second) {
        scheduledDay.set(Calendar.SECOND, second);
    }

    /**
     * Returns the wrapped {@link Calendar} instance.
     *
     * @return the Calendar instance, may be null
     */
    public Calendar getScheduledDay() {
        return scheduledDay;
    }

    /**
     * Replaces the wrapped {@link Calendar} instance.
     *
     * @param scheduledDay the new Calendar instance to wrap; may be null
     */
    public void setScheduledDay(Calendar scheduledDay) {
        this.scheduledDay = scheduledDay;
    }

    public String toString() {
        return scheduledDay.getTime().toString();
    }
}
