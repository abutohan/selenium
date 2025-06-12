package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StatusCodePage extends BasePage {

    public StatusCodePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getUrlStatusCode(String urlString) {
        int statusCode = -1;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            statusCode = connection.getResponseCode();
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL encountered: " + urlString + " - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error connecting to URL: " + urlString + " - " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return statusCode;
    }

}
