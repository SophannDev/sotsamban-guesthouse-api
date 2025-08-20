package com.sgh.sotsamban_guesthouse_api.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    // Predefined formatters
    private static final DateTimeFormatter COMPACT_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter STANDARD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Format LocalDateTime to compact string: yyyyMMddHHmmss
     * Example: 2025-08-20T10:54:23.626727 -> 20250820105423
     */
    public static String toCompactString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(COMPACT_FORMATTER);
    }

    /**
     * Format LocalDateTime to standard string: yyyy-MM-dd HH:mm:ss
     * Example: 2025-08-20T10:54:23.626727 -> 2025-08-20 10:54:23
     */
    public static String toStandardString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(STANDARD_FORMATTER);
    }

    /**
     * Format LocalDateTime with custom pattern
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Get current date time as compact string
     */
    public static String nowAsCompactString() {
        return toCompactString(LocalDateTime.now());
    }
}