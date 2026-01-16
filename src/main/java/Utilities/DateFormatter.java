package Utilities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility to format date in:
 * European Format: Europe, Latin America, Asia, Africa (ddMMyyyy)
 * USA Format: United States of America, Filippine, Canada (MMddyyyy).
 */
public final class DateFormatter {
    private static final DateTimeFormatter EUROPEAN_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final DateTimeFormatter USA_FORMAT = DateTimeFormatter.ofPattern("MMddyyyy");

    private DateFormatter() {}

    private static LocalDate toLocalDate(Calendar cal) {
        return cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String formatEuropean(Calendar cal) {
        return toLocalDate(cal).format(EUROPEAN_FORMAT);
    }

    public static String formatUSA(Calendar cal) {
        return toLocalDate(cal).format(USA_FORMAT);
    }

    public static String formatEuropean(Date date) {
        return toLocalDate(date).format(EUROPEAN_FORMAT);
    }

    public static String formatUSA(Date date) {
        return toLocalDate(date).format(USA_FORMAT);
    }

    // Example usage:
    // Calendar cal = Calendar.getInstance();
    // String eu = DateFormatUtils.formatEuropean(cal); // "16012026"
    // String en = DateFormatUtils.formatEnglish(cal);  // "01162026"
}
