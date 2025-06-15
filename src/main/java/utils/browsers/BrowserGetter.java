package utils.browsers;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BrowserGetter {


    public WebDriver getBrowser(String browser) throws IOException {
        Browser browser1;
        switch (browser) {
            case "chrome":
                browser1 = new Chrome();
                break;
            case "edge":
                browser1 = new Edge();
                break;
            default:
                browser1 = new Chrome();
                break;
        }
        return browser1.getBrowser();
    }
}
