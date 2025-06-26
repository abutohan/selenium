package utils.browsers;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BrowserGetter {

    public WebDriver getBrowser(String browserName) throws IOException {
        Browser browser;
        switch (browserName) {
            case "chrome":
                browser = new Chrome();
                break;
            case "edge":
                browser = new Edge();
                break;
            default:
                browser = new Chrome();
                break;
        }
        return browser.getBrowser();
    }
}
