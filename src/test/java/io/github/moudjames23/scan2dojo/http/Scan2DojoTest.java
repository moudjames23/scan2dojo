package io.github.moudjames23.scan2dojo.http;

import io.github.moudjames23.scan2dojo.dto.Configuration;
import io.github.moudjames23.scan2dojo.dto.requests.ImportRequest;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class Scan2DojoTest {

    @Mock
    private Configuration config;

    @Mock
    private OkHttpClient httpClient;

    @InjectMocks
    private Scan2Dojo importService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldNotProceedWhenScanTypeIsUnsupported() throws IOException {
        // Given: an import request with an unsupported scan type
        ImportRequest importRequest = new ImportRequest(true, true, "UnsupportedType", "non_existent_file.json", "productName", "engagementName", "Low");

        // When: importScan is called
        importService.importScan(importRequest);

        // Then: config methods should not be called
        verify(config, never()).getEndpoint();
        verify(config, never()).getApiKey();
    }

    @Test
    void shouldNotProceedWhenMinimumSeverityIsInvalid() throws IOException {
        // Given: an import request with an invalid minimum severity
        ImportRequest importRequest = new ImportRequest(true, true, "DYNAMIC", "non_existent_file.json", "productName", "engagementName", "InvalidSeverity");

        // When: importScan is called
        importService.importScan(importRequest);

        // Then: config methods should not be called
        verify(config, never()).getEndpoint();
        verify(config, never()).getApiKey();
    }

    @Test
    void shouldNotProceedWhenFileDoesNotExist() throws IOException {
        // Given: an import request with a non-existent file
        ImportRequest importRequest = new ImportRequest(true, true, "DYNAMIC", "non_existent_file.json", "productName", "engagementName", "Low");

        // When: importScan is called
        importService.importScan(importRequest);

        // Then: config methods should not be called
        verify(config, never()).getEndpoint();
        verify(config, never()).getApiKey();
    }

    /*@Test
    void shouldSendRequestWhenImportScanIsSuccessful() throws IOException {
        // Given: a valid import request and a mock successful response
        ImportRequest importRequest = new ImportRequest(true, true, "Trivy Scan", "/Users/moud/Desktop/Libs/trivy.json", "Moud", "Release", "Low");

        File file = mock(File.class);
        when(file.exists()).thenReturn(true);

        when(config.getBaseUrl()).thenReturn("https://demo.defectdojo.org");
        when(config.getApiKey()).thenReturn("xxxxxxxxxxxxxxxxxxxxxxxxxxx");

        Response response = mock(Response.class);
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(ResponseBody.create("{}", MediaType.parse("application/json")));
        when(response.code()).thenReturn(200);

        Call call = mock(Call.class);
        when(httpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);

        // When: importScan is called
        importService.importScan(importRequest);

        // Then: the HTTP request should be sent with the correct parameters
        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        verify(httpClient).newCall(requestCaptor.capture());

        Request capturedRequest = requestCaptor.getValue();
        assertNotNull(capturedRequest);
        assertEquals("https://demo.defectdojo.org/api/v2/import-scan/", capturedRequest.url().toString());
        assertEquals("Token api_key", capturedRequest.header("Authorization"));
        assertEquals("multipart/form-data", capturedRequest.header("Content-Type"));
    }*/
}
