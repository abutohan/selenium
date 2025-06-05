package pages.basicauth;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasicAuthPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class BasicAuthTest extends BaseTest {

    private BasicAuthPage basicAuthPage;

    @BeforeMethod
    public void initPage() {
        basicAuthPage = new BasicAuthPage(driver);
    }

//    @Test(testName = "Page Displayed Correctly", dataProvider = "getHeaderTitle")
//    public void testHeaderTitle(JSONObject testData) {
//        assertEquals(basicAuthPage.getHeaderTitle(testData.getString("username"), testData.getString("password")), testData.getString("header_title"),
//                String.format("Expected: %s - Actual: %s", testData.getString("header_title"),
//                        basicAuthPage.getHeaderTitle(testData.getString("username"), testData.getString("password"))));
//
//    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.basic-auth.header");
    }
}
