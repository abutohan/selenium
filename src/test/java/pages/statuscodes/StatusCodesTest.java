package pages.statuscodes;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.StatusCodePage;
import pages.StatusCodesPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class StatusCodesTest extends BaseTest {

    private StatusCodesPage statusCodesPage;

    @BeforeClass
    public void initTest() {
        test = extent.createTest("Status Codes");
    }

    @BeforeMethod
    public void initPage() {
        statusCodesPage = homePage.clickStatusCodesPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = statusCodesPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Status Code", priority = 2, dataProvider = "getStatusCodesData")
    public void testStatusCode(JSONObject testData) {
        String expectedStatusCode = testData.getString("status_code");
        StatusCodePage statusCodePage = statusCodesPage.getStatusCodePage(expectedStatusCode);
        String url = statusCodePage.getDriver().getCurrentUrl();
        int actualStatusCode = statusCodePage.getUrlStatusCode(url);
        assertEquals(String.valueOf(actualStatusCode), expectedStatusCode,
                onFailure(expectedStatusCode, String.valueOf(actualStatusCode)));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.status-codes.header");
    }

    @DataProvider(name = "getStatusCodesData")
    private Object[][] getStatusCodesData() throws IOException {
        return getTestDataFromJSON("test-data.status-codes.status-codes");
    }

}
