package it.unicam.cs.mpgc.jtime119685.Model.DateUtilities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class to format dates into specific regional formats.
 *
 * <p>Supported formats:</p>
 * <ul>
 *   <li>European Format: ddMMyyyy (used in Europe, Latin America, Asia, Africa)</li>
 *   <li>USA Format: MMddyyyy (used in the United States, Philippines, Canada)</li>
 * </ul>
 */
public final class DateFormatter {
    // Formatter for European date format (ddMMyyyy)
    private static final DateTimeFormatter EUROPEAN_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");
    // Formatter for USA date format (MMddyyyy)
    private static final DateTimeFormatter USA_FORMAT = DateTimeFormatter.ofPattern("MMddyyyy");

    // Private constructor to prevent instantiation of utility class
    private DateFormatter() {
    }

    /**
     * Formats a Calendar instance into the European date format (ddMMyyyy).
     *
     * @param cal the Calendar instance to format
     * @return the formatted date string in European format
     */
    public static String formatEuropean(Calendar cal) {
        return toLocalDate(cal).format(EUROPEAN_FORMAT);
    }

    /**
     * Formats a Calendar instance into the USA date format (MMddyyyy).
     *
     * @param cal the Calendar instance to format
     * @return the formatted date string in USA format
     */
    public static String formatUSA(Calendar cal) {
        return toLocalDate(cal).format(USA_FORMAT);
    }

    /**
     * Formats a Date instance into the European date format (ddMMyyyy).
     *
     * @param date the Date instance to format
     * @return the formatted date string in European format
     */
    public static String formatEuropean(Date date) {
        return toLocalDate(date).format(EUROPEAN_FORMAT);
    }

    /**
     * Formats a Date instance into the USA date format (MMddyyyy).
     *
     * @param date the Date instance to format
     * @return the formatted date string in USA format
     */
    public static String formatUSA(Date date) {
        return toLocalDate(date).format(USA_FORMAT);
    }

    /**
     * Converts a Calendar instance to a LocalDate.
     *
     * @param cal the Calendar instance to convert
     * @return the corresponding LocalDate
     */
    private static LocalDate toLocalDate(Calendar cal) {
        return cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converts a Date instance to a LocalDate.
     *
     * @param date the Date instance to convert
     * @return the corresponding LocalDate
     */
    private static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // Example usage:
    // Calendar cal = Calendar.getInstance();
    // String eu = DateFormatter.formatEuropean(cal); // "16012026"
    // String us = DateFormatter.formatUSA(cal);      // "01162026"
}
