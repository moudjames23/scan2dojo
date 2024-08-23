package io.github.moudjames23.scan2dojo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Returns the current date formatted as yyyy-MM-dd.
     *
     * @return A string representing today's date in the format yyyy-MM-dd.
     */
    public static String today() {
        LocalDate today = LocalDate.now();
        return today.format(FORMATTER);
    }

    /**
     * Returns the date one year from now formatted as yyyy-MM-dd.
     *
     * @return A string representing the date one year from today in the format yyyy-MM-dd.
     */
    public static String nextYear() {
        LocalDate nextYear = LocalDate.now().plusYears(1);
        return nextYear.format(FORMATTER);
    }
}
