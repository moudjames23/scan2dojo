package io.github.moudjames23.scan2dojo.util;

import io.github.moudjames23.scan2dojo.enums.ScanType;
import io.github.moudjames23.scan2dojo.enums.Severity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumUtilTest {

    @Test
    public void shouldReturnTrueForValidStringInEnum() {
        // Given: valid strings that correspond to enum values
        String validSeverityValue1 = "Low";
        String validSeverityValue2 = "low";
        String validSeverityValue3 = "HIGH";

        String validScanTypeValue1 = "Trivy Scan";
        String validScanTypeValue2 = "Anchore Enterprise Policy Check";
        String validScanTypeValue3 = "CredSCAN scan";

        // When & Then: the method should return true for valid strings
        assertTrue(EnumUtil.isStringInEnum(validSeverityValue1, Severity.class));
        assertTrue(EnumUtil.isStringInEnum(validSeverityValue2, Severity.class));
        assertTrue(EnumUtil.isStringInEnum(validSeverityValue3, Severity.class));

        assertTrue(EnumUtil.isStringInEnum(validScanTypeValue1, ScanType.class));
        assertTrue(EnumUtil.isStringInEnum(validScanTypeValue2, ScanType.class));
        assertTrue(EnumUtil.isStringInEnum(validScanTypeValue3, ScanType.class));
    }

    @Test
    public void shouldReturnFalseForInvalidStringInEnum() {
        // Given: invalid strings that do not correspond to any enum values
        String invalidSeverityValue = "Unknown";
        String invalidScanTypeValue = "invalid";

        // When & Then: the method should return false for invalid strings
        assertFalse(EnumUtil.isStringInEnum(invalidSeverityValue, Severity.class));
        assertFalse(EnumUtil.isStringInEnum(invalidScanTypeValue, ScanType.class));
    }

    @Test
    public void shouldReturnFalseForNullStringInEnum() {
        // Given: a null string
        String nullValue = null;

        // When & Then: the method should return false for a null string
        assertFalse(EnumUtil.isStringInEnum(nullValue, ScanType.class));
    }

    @Test
    public void shouldReturnFalseForNullEnumClass() {
        // Given: a valid string but a null enum class
        String validValue = "Info";

        // When & Then: the method should return false for a null enum class
        assertFalse(EnumUtil.isStringInEnum(validValue, null));
    }

}