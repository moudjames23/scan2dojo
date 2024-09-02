package io.github.moudjames23.scan2dojo.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.github.moudjames23.scan2dojo.util.MessageUtil.*;
import static io.github.moudjames23.scan2dojo.util.URLValidator.checkURL;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Configuration {

    private static final String CONFIG_FILE_NAME = "config.json";
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private String endpoint;


    private String apiKey;

    private static final Path configPath = Paths.get(System.getProperty("user.home"), ".config", "scan2dojo", CONFIG_FILE_NAME);


    public void load() throws IOException {



        if (Files.exists(configPath)) {
            Map<String, String> data = objectMapper.readValue(Files.newInputStream(configPath), Map.class);

            if (data.get("endpoint") == null || data.get("endpoint").isEmpty()) {
                printlnWithBorder(RED, "Endpoint is not defined, please provide a valid endpoint.");
                return;
            }

            if (data.get("apikey") == null || data.get("apikey").isEmpty()) {
                printlnWithBorder(RED, "API key is not defined, please provide a valid API key.");
                return;
            }

            this.endpoint = data.get("endpoint");
            this.apiKey = data.get("apikey");
        } else {
            printlnWithBorder(RED, "Configuration file not found. Please run 'scan2dojo configure' to set up the configuration.");
        }
    }


    public void save() throws IOException {

        if (this.endpoint == null || this.endpoint.isEmpty()) {
            printlnWithBorder(RED, "Endpoint is not defined, please provide a valid endpoint.");
            return;
        }

        checkURL(endpoint);

        if (this.apiKey == null || this.apiKey.isEmpty()) {
            printlnWithBorder(RED, "API key is not defined, please provide a valid API key.");
            return;
        }

        Files.createDirectories(configPath.getParent());

        Map<String, String> data = new HashMap<>();
        data.put("endpoint", this.endpoint);
        data.put("apikey", this.apiKey);

        objectMapper.writeValue(configPath.toFile(), data);

        printlnWithBorder(GREEN, "Configuration updated successfully 🎉🎉🎉");

    }

}
