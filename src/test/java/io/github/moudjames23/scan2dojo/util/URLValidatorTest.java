package io.github.moudjames23.scan2dojo.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class URLValidatorTest {

    @Test
    public void shouldReturnUrlWithoutTrailingSlashWhenUrlIsValid() throws MalformedURLException {
        // Given: a valid URL with a trailing slash
        String url = "https://example.com/";

        // When: checkURL is called
        String result = URLValidator.checkURL(url);

        // Then: the result should be the URL without the trailing slash
        assertEquals("https://example.com", result);
    }

    @Test
    public void shouldReturnUrlAsIsWhenUrlHasNoTrailingSlashAndIsValid() throws MalformedURLException {
        // Given: a valid URL without a trailing slash
        String url = "https://example.com";

        // When: checkURL is called
        String result = URLValidator.checkURL(url);

        // Then: the result should be the URL as is
        assertEquals(url, result);
    }

    @Test
    public void shouldPrintErrorWhenUrlIsInvalid() throws MalformedURLException {
        // Given: an invalid URL
        String url = "invalid-url";
        String expectedMessage = "Endpoint is not an URL";

        // Redirect the output stream to capture the printed message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // When: checkURL is called with an invalid URL
        URLValidator.checkURL(url);

        // Then: an error message should be printed
        assertTrue(outContent.toString().contains(expectedMessage));

        // Restore the original output stream
        System.setOut(System.out);
    }

    @Test
    public void shouldReturnFalseForInvalidURL() {
        // Given: an invalid URL string
        String invalidUrl = "invalid-url";

        // When: isValidURL is called
        boolean result = URLValidator.isValidURL(invalidUrl);

        // Then: the result should be false
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueForValidHttpURL() {
        // Given: a valid HTTP URL
        String validUrl = "http://example.com";

        // When: isValidURL is called
        boolean result = URLValidator.isValidURL(validUrl);

        // Then: the result should be true
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueForValidHttpsURL() {
        // Given: a valid HTTPS URL
        String validUrl = "https://example.com";

        // When: isValidURL is called
        boolean result = URLValidator.isValidURL(validUrl);

        // Then: the result should be true
        assertTrue(result);
    }

    @Test
    public void shouldRemoveTrailingSlash() {
        // Given: a string with a trailing slash
        String input = "https://example.com/";

        // When: removeTrailingSlash is called
        String result = URLValidator.removeTrailingSlash(input);

        // Then: the result should be the string without the trailing slash
        assertEquals("https://example.com", result);
    }

    @Test
    public void shouldNotRemoveTrailingSlashWhenThereIsNone() {
        // Given: a string without a trailing slash
        String input = "https://example.com";

        // When: removeTrailingSlash is called
        String result = URLValidator.removeTrailingSlash(input);

        // Then: the result should be the same string
        assertEquals(input, result);
    }

}