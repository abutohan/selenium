package pages.javascriptonloadeventerror;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.JavaScriptOnLoadEventErrorPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class JavaScriptOnLoadEventErrorTest extends BaseTest {

    private JavaScriptOnLoadEventErrorPage javaScriptOnLoadEventErrorPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("JavaScript On Load Event Error");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        javaScriptOnLoadEventErrorPage = homePage.clickJavaScriptOnLoadEventErrorPage();
    }

    @Test(testName = "Browser Logs", priority = 1, dataProvider = "getOnloadEventStatus", groups = {"smoke", "regression"})
    public void testBrowserLogs(JSONObject testData) {
        assertEquals(javaScriptOnLoadEventErrorPage.onError(), testData.getBoolean("onload_event_status"));
    }

    @DataProvider(name = "getOnloadEventStatus")
    private Object[][] getOnloadEventStatus(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("onload-event-error"));
    }

}
