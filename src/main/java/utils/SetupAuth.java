package utils;

import org.openqa.selenium.Credentials;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SetupAuth {

    /**
     * Registers basic authentication credentials with the WebDriver using Selenium 4's HasAuthentication interface.
     * This method should be called BEFORE navigating to the authenticated URL.
     *
     * @param driver   The WebDriver instance (must be a Chromium-based driver supporting HasAuthentication).
     * @param baseUrl  The base URL of the application. The authentication will be applied to requests
     *                 whose host matches the host of this base URL.
     * @param username The username for basic authentication.
     * @param password The password for basic authentication.
     * @throws IllegalArgumentException If the provided WebDriver does not support HasAuthentication.
     */
    public static void setupAuthentication(WebDriver driver, String baseUrl, String username, String password) {
        if (!(driver instanceof HasAuthentication)) {
            throw new IllegalArgumentException("The provided WebDriver does not support HasAuthentication. " + "This feature is primarily for Chromium-based browsers (Chrome, Edge) with Selenium 4+.");
        }
        URI baseUri = URI.create(baseUrl);
        String host = baseUri.getHost();
        Predicate<URI> uriPredicate = uri -> uri.getHost().contains(host);
        Supplier<Credentials> credentialsSupplier = () -> new UsernameAndPassword(username, password);
        ((HasAuthentication) driver).register(uriPredicate, credentialsSupplier);
        System.out.println("Authentication registered for host: " + host + " with username: " + username);
    }
}
