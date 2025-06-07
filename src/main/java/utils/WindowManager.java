package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static utils.Constants.TIMEOUT;

public class WindowManager {

    private final WebDriver driver;
    private final WebDriver.Navigation navigate;

    public WindowManager(WebDriver driver) {
        this.driver = driver;
        navigate = driver.navigate();
    }

    public void refreshPage() {
        System.out.println("refreshing current window...");
        navigate.refresh();
    }

    public void switchToTab(String windowHandle){
        driver.switchTo().window(windowHandle);
    }

    public void switchToNewTab(Set<String> oldWindowHandles) {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.numberOfWindowsToBe(oldWindowHandles.size() + 1));
        Set<String> newWindowHandles = driver.getWindowHandles();

        String newWindowHandle = null;
        for (String handle : newWindowHandles) {
            if (!oldWindowHandles.contains(handle)) {
                newWindowHandle = handle;
                break;
            }
        }

        if (newWindowHandle != null) {
            driver.switchTo().window(newWindowHandle);
            System.out.println("Switched to new tab: " + driver.getTitle());
        } else {
            System.err.println("Error: New tab handle not found!");
        }
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

}