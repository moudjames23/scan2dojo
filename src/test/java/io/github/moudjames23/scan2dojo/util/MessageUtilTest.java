package io.github.moudjames23.scan2dojo.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MessageUtilTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintMessageWithRedBorder() {
        // Given: a message and the expected output with red border
        String message = "This is a red message";
        String expectedOutput = MessageUtil.RED +
                "--------------------------------------------------------------------------------\n" +
                message + "\n" +
                "--------------------------------------------------------------------------------" +
                MessageUtil.RESET + "\n";

        // When: printlnWithBorder is called with RED color
        MessageUtil.printlnWithBorder(MessageUtil.RED, message);

        // Then: the output should match the expected output
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void shouldPrintMessageWithGreenBorder() {
        // Given: a message and the expected output with green border
        String message = "This is a green message";
        String expectedOutput = MessageUtil.GREEN +
                "--------------------------------------------------------------------------------\n" +
                message + "\n" +
                "--------------------------------------------------------------------------------" +
                MessageUtil.RESET + "\n";

        // When: printlnWithBorder is called with GREEN color
        MessageUtil.printlnWithBorder(MessageUtil.GREEN, message);

        // Then: the output should match the expected output
        assertEquals(expectedOutput, outContent.toString());
    }

}