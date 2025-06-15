package utils.browsers;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public interface Browser {

    public WebDriver getBrowser() throws IOException;

}
