package io.github.moudjames23.scan2dojo.command;

import io.github.moudjames23.scan2dojo.dto.requests.EngagementRequest;
import io.github.moudjames23.scan2dojo.dto.requests.ImportRequest;
import io.github.moudjames23.scan2dojo.dto.requests.ProductRequest;
import io.github.moudjames23.scan2dojo.dto.requests.ProductTypeRequest;
import io.github.moudjames23.scan2dojo.http.Scan2Dojo;
import io.github.moudjames23.scan2dojo.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.net.URISyntaxException;


@ShellComponent
public class S2DCommand {

    private final Scan2Dojo scan2Dojo;

    @Value("${APP_VERSION}")
    private String version;


    public S2DCommand(Scan2Dojo importService) {
        this.scan2Dojo = importService;
    }

    /**
     * Displays the current version of the Scan2Dojo application.
     *
     */
    @ShellMethod(key = "version", value = "show the current version of scan2dojo")
    public String showVersion() {
        return "Version: " + version;
    }

    @ShellMethod(value = "Display custom help", key = "help")
    public String displayHelp() {

        return "Scanner for vulnerabilities in container images, file systems, and Git repositories,\n" +
                "as well as for configuration issues and hard-coded secrets\n\n" +
                "Usage:\n" +
                "  scan2dojo [global flags] command [flags] target\n" +
                "  scan2dojo [command]\n\n" +
                "Examples:\n" +
                "  # Show the current version of scan2dojo\n" +
                "  $ scan2dojo version\n\n" +
                "  # Configure endpoint and API key\n" +
                "  $ scan2dojo configure --endpoint https://api.example.com --apiKey your-api-key\n\n" +
                "  # Create a new product type in DefectDojo\n" +
                "  $ scan2dojo create:product-type --name 'Type Name' --description 'Type description' --critical true --key true\n\n" +
                "  # Create a new product in DefectDojo\n" +
                "  $ scan2dojo create:product --name 'My Product' --description 'Product description' --typeId 1 --slaConfiguration 1\n\n" +
                "  # Import a scan result to DefectDojo\n" +
                "  $ scan2dojo import --scanType 'Trivy Scan' --file /path/to/scan_result.json --productName 'My Product' --engagementName 'Release Engagement' --minimumSeverity High\n\n" +
                "Scanning Commands:\n" +
                "  configure          Configure endpoint and API key\n" +
                "  create:product     Create new product\n" +
                "  create:product-type Create new product type\n" +
                "  create:engagement  Create new engagement\n" +
                "  import             Import scan result to DefectDojo\n\n" +
                "Utility Commands:\n" +
                "  version            Show the current version of scan2dojo\n" +
                "  help               Display this custom help message\n\n";
    }


    /**
     * Shell command to configure the endpoint and API key for Scan2Dojo.
     *
     * <p>
     * This method sets the endpoint and API key that will be used by the application
     * to communicate with the Scan2Dojo service. If no endpoint or API key is specified,
     * default values (empty strings) will be used.
     * </p>
     *
     * @param endpoint The URL of the Scan2Dojo service endpoint. Defaults to an empty string.
     * @param apiKey   The API key used to authenticate requests to the endpoint. Defaults to an empty string.
     * @throws IOException If an I/O error occurs during configuration.
     */
    @ShellMethod(key = "configure", value = "Configure endpoint and apikey")
    public void configure(
            @ShellOption(defaultValue = "", help = "The URL of the Scan2Dojo service endpoint.") String endpoint,
            @ShellOption(defaultValue = "", help = "The API key used to authenticate requests to the endpoint.") String apiKey
    ) throws IOException, URISyntaxException {

        this.scan2Dojo.configure(endpoint, apiKey);

    }

    /**
     * Shell command to create a new product type.
     *
     * @ShellMethod(key = "create:product-type", value = "Create a new product type")
     * This command allows the user to create a new product type by providing a name,
     * description, and two boolean flags to specify if the product type is critical
     * and/or considered a key type.
     *
     * @param name The name of the product type. This is a required parameter provided by the user.
     * @param description A brief description of the product type. This is a required parameter provided by the user.
     * @param critical Flag indicating whether the product type is considered critical. This is an optional parameter
     *                 with a default value of "true".
     * @param key Flag indicating whether the product type is considered a key type or primary feature. This is an optional
     *            parameter with a default value of "true".
     * @throws IOException This method may throw an IOException if there is an input/output issue
     *                     during the creation of the product type.
     */
    @ShellMethod(key = "create:product-type", value = "create new product type")
    public void createProductType(
            @ShellOption(help = "The name of the product type") String name,
            @ShellOption(help = "A brief description of the product type.") String description,
            @ShellOption(help = "The ID representing the type of the product.", defaultValue = "true") boolean critical,
            @ShellOption(help = "The ID representing the type of the product.", defaultValue = "true") boolean key
    ) throws IOException {
        ProductTypeRequest productTypeRequest = new ProductTypeRequest(name, description, critical, key);

        this.scan2Dojo.createProductType(productTypeRequest);
    }


    /**
     * Shell command to create a new product in the defectdojo.
     *
     * <p>
     * This method allows the user to create a new product by providing the necessary details
     * such as the product name, description, type ID, and SLA configuration. If the SLA
     * configuration is set to 0, it will be defaulted to 1.
     * </p>
     *
     * @param name             The name of the product. This is a required field.
     * @param description      A brief description of the product. This is a required field.
     * @param typeId           The ID representing the type of the product. This is a required field.
     * @param slaConfiguration The Service Level Agreement (SLA) configuration ID for the product. If set to 0, it defaults to 1.
     * @throws IOException If an input/output error occurs during the creation process.
     */
    @ShellMethod(key = "create:product", value = "create new product")
    public void createProduct(
            @ShellOption(help = "The name of the product.") String name,
            @ShellOption(help = "A brief description of the product.") String description,
            @ShellOption(help = "The ID representing the type of the product.") int typeId,
            @ShellOption(help = "The Service Level Agreement (SLA) configuration ID for the product. Defaults to 1 if set to 0.", defaultValue = "1") int slaConfiguration
    ) throws IOException {


        ProductRequest productRequest = new ProductRequest(name, description, typeId, slaConfiguration);

        this.scan2Dojo.createProduct(productRequest);
    }

    /**
     * Shell command to create a new engagement in the defectdojo.
     *
     * <p>
     * This method allows the user to create a new engagement by providing necessary details such as
     * the engagement name, description, start and end dates, and the associated product ID. If the
     * start and end dates are not provided, they default to today's date and one year from today, respectively.
     * </p>
     *
     * @param name        The name of the engagement. This is a required field.
     * @param description A brief description of the engagement. This is a required field.
     * @param start       The start date of the engagement in the format yyyy-MM-dd. Defaults to today's date if not provided.
     * @param end         The end date of the engagement in the format yyyy-MM-dd. Defaults to one year from today's date if not provided.
     * @param productId   The ID of the product associated with this engagement. This is a required field.
     * @throws IOException If an input/output error occurs during the creation process.
     */
    @ShellMethod(key = "create:engagement", value = "create new engagement")
    public void createEngagement(
            @ShellOption(help = "The name of the engagement.") String name,
            @ShellOption(help = "A brief description of the engagement.") String description,
            @ShellOption(help = "The start date of the engagement in the format yyyy-MM-dd. Defaults to today's date if not provided.", defaultValue = "") String start,
            @ShellOption(help = "The end date of the engagement in the format yyyy-MM-dd. Defaults to one year from today's date if not provided.", defaultValue = "") String end,
            @ShellOption(help = "The ID of the product associated with this engagement.") int productId
    ) throws IOException {

        start = start.isEmpty() ? DateUtil.today() : start;
        end = end.isEmpty() ? DateUtil.nextYear() : end;

        EngagementRequest engagementRequest = new EngagementRequest(name, description, productId, start, end);

        this.scan2Dojo.createEngagement(engagementRequest);
    }


    /**
     * Imports a scan result to DefectDojo.
     *
     * @param active          Indicates if the scan result should be marked as active. Default is true.
     *                        Example: --active true
     * @param verified        Indicates if the scan result should be marked as verified. Default is true.
     *                        Example: --verified true
     * @param scanType        The type of the scan. This should correspond to the scanner used.
     *                        Example: --scanType "Trivy Scan"
     * @param file            The path to the scan result file to be imported.
     *                        Example: --file /path/to/scan_result.json
     * @param productName     The name of the product in DefectDojo to which the scan result belongs.
     *                        Example: --productName "My Product"
     * @param engagementName  The name of the engagement in DefectDojo under which the scan result will be recorded.
     *                        Example: --engagementName "Release Engagement"
     * @param minimumSeverity The minimum severity of findings to be imported. Possible values are Low, Medium, High, etc.
     *                        Example: --minimumSeverity High
     */
    @ShellMethod(key = "import", value = "import scan result to defectdojo")
    public void importer(
            @ShellOption(defaultValue = "true", help = "Indicates if the scan result should be marked as active. Default is true.") Boolean active,
            @ShellOption(defaultValue = "true", help = "Indicates if the scan result should be marked as verified. Default is true.") Boolean verified,
            @ShellOption(help = "The type of the scan. This should correspond to the scanner used.") String scanType,
            @ShellOption(help = "The path to the scan result file to be imported.") String file,
            @ShellOption(help = "The name of the product in DefectDojo to which the scan result belongs.") String productName,
            @ShellOption(help = "The name of the engagement in DefectDojo under which the scan result will be recorded.") String engagementName,
            @ShellOption(help = "The minimum severity of findings to be imported. Possible values are Low, Medium, High, etc.") String minimumSeverity
    ) throws IOException {

        ImportRequest request = new ImportRequest(active, verified, scanType, file, productName, engagementName, minimumSeverity);

        this.scan2Dojo.importScan(request);

    }


}
