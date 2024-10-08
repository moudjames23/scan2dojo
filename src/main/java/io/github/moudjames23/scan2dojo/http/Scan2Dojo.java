package io.github.moudjames23.scan2dojo.http;

import io.github.moudjames23.scan2dojo.dto.Configuration;
import io.github.moudjames23.scan2dojo.dto.requests.EngagementRequest;
import io.github.moudjames23.scan2dojo.dto.requests.ImportRequest;
import io.github.moudjames23.scan2dojo.dto.requests.ProductRequest;
import io.github.moudjames23.scan2dojo.dto.requests.ProductTypeRequest;
import io.github.moudjames23.scan2dojo.dto.responses.EngagementResponse;
import io.github.moudjames23.scan2dojo.dto.responses.ImportResponse;
import io.github.moudjames23.scan2dojo.dto.responses.ProductResponse;
import io.github.moudjames23.scan2dojo.dto.responses.ProductTypeResponse;
import io.github.moudjames23.scan2dojo.enums.ScanType;
import io.github.moudjames23.scan2dojo.enums.Severity;
import io.github.moudjames23.scan2dojo.util.EnumUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static io.github.moudjames23.scan2dojo.util.MessageUtil.*;
import static io.github.moudjames23.scan2dojo.util.RequestUtil.executeRequest;

@Service
public class Scan2Dojo {

    private Configuration configuration;


    public Scan2Dojo() {

        this.configuration = new Configuration();
    }

    public void version() {
        printlnWithBorder(RED, "Version");
    }

    public void configure(String endpoint, String apiKey) throws IOException {

        configuration.setEndpoint(endpoint);
        configuration.setApiKey(apiKey);

        configuration.save();
    }

    public void createProductType(ProductTypeRequest productTypeRequest) throws IOException {

        configuration.load();

        ProductType product = new ProductType(configuration, productTypeRequest);
        executeRequest(product, ProductTypeResponse.class);
    }

    public void createProduct(ProductRequest productRequest) throws IOException {

        configuration.load();

        Product product = new Product(configuration, productRequest);
        executeRequest(product, ProductResponse.class);
    }

    public void createEngagement(EngagementRequest engagementRequest) throws IOException {

        configuration.load();

        Engagement engagement = new Engagement(configuration, engagementRequest);
        executeRequest(engagement, EngagementResponse.class);
    }

    public void importScan(ImportRequest importRequest) throws IOException {

        configuration.load();

        validateScanType(importRequest.scanType());
        validateSeverity(importRequest.minimumSeverity());

        File file = new File(importRequest.file());
        if (!file.exists()) {
            printlnWithBorder(RED,"File " + importRequest.file() + " doesn't exist");
            return;
        }

        Import importer = new Import(configuration, importRequest);
        executeRequest(importer, ImportResponse.class);
    }



    private void validateScanType(String scanType) {
        if (!EnumUtil.isStringInEnum(scanType, ScanType.class)) {
            printlnWithBorder(RED,scanType + " is not supported. Please find the list of supported scan types.");
            return;
        }
    }

    private void validateSeverity(String severity) {
        if (!EnumUtil.isStringInEnum(severity, Severity.class)) {
            printlnWithBorder(RED,severity + " is not a valid severity level. Supported levels are: Info, Low, Medium, High, Critical.");
            return;
        }
    }
}
