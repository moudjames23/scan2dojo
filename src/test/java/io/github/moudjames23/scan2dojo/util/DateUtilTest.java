package io.github.moudjames23.scan2dojo.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void testToday() {
        // Given: the expected formatted date for today
        LocalDate expectedDate = LocalDate.now();
        String expectedFormattedDate = expectedDate.format(FORMATTER);

        // When: we call the today() method
        String actualFormattedDate = DateUtil.today();

        // Then: the result should match the expected formatted date
        assertEquals(expectedFormattedDate, actualFormattedDate, "The formatted date should match today's date");
    }

    @Test
    public void testNextYear() {
        // Given: the expected formatted date for one year from today
        LocalDate expectedDate = LocalDate.now().plusYears(1);
        String expectedFormattedDate = expectedDate.format(FORMATTER);

        // When: we call the nextYear() method
        String actualFormattedDate = DateUtil.nextYear();

        // Then: the result should match the expected formatted date one year from today
        assertEquals(expectedFormattedDate, actualFormattedDate, "The formatted date should match the date one year from today");
    }
}