package utils.browsers;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BrowserGetter {

    public WebDriver getBrowser(String browserName, String headless) throws IOException {
        Browser browser;
        switch (browserName) {
            case "chrome":
                browser = new Chrome(headless);
                break;
            case "edge":
                browser = new Edge(headless);
                break;
            default:
                browser = new Chrome(headless);
                break;
        }
        return browser.getBrowser();
    }
}
