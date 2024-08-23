package io.github.moudjames23.scan2dojo.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static io.github.moudjames23.scan2dojo.util.MessageUtil.RED;
import static io.github.moudjames23.scan2dojo.util.MessageUtil.printlnWithBorder;

public class URLValidator {

    private URLValidator() {
    }

    public static String checkURL(String url) throws MalformedURLException {

        if (!isValidURL(url))
            printlnWithBorder(RED, "Endpoint is not an URL");

        return removeTrailingSlash(url);
    }

    public static boolean isValidURL(String urlString) {
        try {
            URI uri = new URI(urlString);

            String scheme = uri.getScheme();
            return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
        } catch (URISyntaxException e) {

            return false;
        }
    }

    public static String removeTrailingSlash(String input) {
        if (input != null && input.endsWith("/")) {
            return input.substring(0, input.length() - 1);
        }
        return input;
    }
}
