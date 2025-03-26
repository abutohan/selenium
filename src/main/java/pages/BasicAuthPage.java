package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static utils.Constants.BASE_URL;

public class BasicAuthPage extends BasePage {

    private final By headerTitle = By.tagName("h3");

    public BasicAuthPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle(String username, String password) {
        DevTools devTools = ((HasDevTools) getDriver()).getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        Map<String, Object> headers = new HashMap<>(1);
        headers.put("Authorization", "Basic " + encodedAuth);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        getDriver().get(String.format("%s/basic_auth", BASE_URL));

        devTools.disconnectSession();

        return getWait().until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

}
