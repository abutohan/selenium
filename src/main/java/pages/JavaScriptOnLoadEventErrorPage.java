
package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;

public class JavaScriptOnLoadEventErrorPage extends BasePage {

    public JavaScriptOnLoadEventErrorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean onError() {
        boolean errorFound = false;
        LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
        System.out.println("--- Browser Console Logs ---");
        for (LogEntry entry : logEntries) {
            if (entry.getMessage().contains("/javascript_error")) {
                System.err.println(entry.getLevel() + " " + entry.getMessage());
                errorFound = true;

//            if (entry.getLevel().equals(Level.SEVERE) && entry.getMessage().contains("Simulated JavaScript error during onload!")) {
//                errorFound = true;
            }
        }
        System.out.println("----------------------------");
        return errorFound;
    }

}
